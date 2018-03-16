package ch.kbw.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ch.kbw.model.User;

/**
 *
 * @author Adel
 */
@Named
@RequestScoped
public class UserDAO implements Serializable {

    //FOR FURTHER INFORMATION, FOR TESTING
    private static Logger log = Logger.getLogger(UserDAO.class.getSimpleName());
    // LIST OF USER
    private List<User> customers = new ArrayList<>();

    // DATABASE CONNECTION
    private Connection connection;

    // HOLDS THE CURRENT USER - JUST FOR TESTING
    private User currentUser = getAllUsers().get(4);

    //GETS INVOKED AFTER CONSTRUCTOR
    @PostConstruct
    private void init() {

        log.info("---------- CONNECTION TESTING ------------");

        try {
            // LOAD DRIVER
            Class.forName("com.mysql.jdbc.Driver");
            log.info("the driver is loaded");
        } catch (ClassNotFoundException e) {
            log.info("No JDBC Driver found ...");
            e.printStackTrace();

        }

        log.info("EVERYTHING OKAY WITH DRIVER");

        try {
            // CONNECT WITH THE DATABASE db_portalumni
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_portalumni", "root", "");

        } catch (SQLException e) {
            log.info("Connection Failed!");
            e.printStackTrace();

        }

        if (connection != null) {
            log.info("DB CONNECTION OKAY");
        } else {
            log.info("Failed to make connection ...");

        }
    }

    public List<User> getAllUsers() {
        init();
        customers.clear();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String query = "Select * from benutzer";

        try {
            pst = connection.prepareStatement(query);
            pst.execute();
            rs = pst.getResultSet();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt(1));
                user.setCompanyManagementID(rs.getInt(2));
                user.setFirstName(rs.getString(3));
                user.setLastName(rs.getString(4));
                user.setUserName(rs.getString(5));
                user.setPassword(rs.getString(6));
                user.setEmail(rs.getString(7));
                user.setEmailPassword(rs.getString(8));
                user.setAdmin(rs.getBoolean(9));
                user.setNewsletter(rs.getBoolean(10));
                customers.add(user);
            }

            pst.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    public void changeSettingsMail(String newEMail) {
        if (notEmptyInput(newEMail)) {
            if (!sameEMail(currentUser.getEmail(), newEMail)) {
                PreparedStatement pst = null;
                String query = "UPDATE benutzer "
                        + "SET Email = ? WHERE userID = ?";
                try {
                    pst = connection.prepareStatement(query);
                    pst.setString(1, newEMail);
                    pst.setInt(2, currentUser.getId());
                    currentUser.setEmail(newEMail);
                    pst.executeUpdate();
                    log.info("SUCESSFULLY UPDATED EMAIL OF CURRENT USER: " + currentUser.getUserName());
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (sameEMail(currentUser.getEmail(), newEMail)) {
                //Fehleranzeige "Gleiche Mail eingegeben"
            }

        }

    }

    public void changeSettingsPassword(String newPassword) {
        if (notEmptyInput(newPassword)) {
            
            PreparedStatement pst = null;
            String query = "UPDATE benutzer "
                    + "SET Passwort = ? WHERE userID = ?";
            try {
                pst = connection.prepareStatement(query);
                pst.setString(1, newPassword);
                pst.setInt(2, currentUser.getId());
                currentUser.setPassword(newPassword);
                pst.executeUpdate();
                log.info("SUCESSFULLY UPDATED PASSWORD OF CURRENT USER: " + currentUser.getUserName());
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    public void changeSettingsUsername(String newUsername) {
        if (notEmptyInput(newUsername)) {
            if (!sameUserName(currentUser.getUserName(), newUsername)) {
                PreparedStatement pst = null;
                String query = "UPDATE benutzer "
                        + "SET Username = ? WHERE userID = ?";
                try {
                    pst = connection.prepareStatement(query);
                    pst.setString(1, newUsername);
                    pst.setInt(2, currentUser.getId());
                    currentUser.setUserName(newUsername);
                    pst.executeUpdate();
                    log.info("SUCESSFULLY UPDATED USERNAME OF CURRENT USER: " + currentUser.getFirstName());
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if(sameUserName(currentUser.getUserName(), newUsername)){
                // Fehlerannzeige "Gleicher Username eingegeben"
            }

        }

    }

    public boolean notEmptyInput(String input) {
        if (!input.equals("")) {
            return true;
        }
        return false;
    }

    public boolean samePassword(String newPassword, String repeatedNewPassword) {
        if (newPassword.equals(repeatedNewPassword)) {
            return true;
        }
        return false;
    }

    public boolean sameEMail(String oldEMail, String newEMail) {
        if (oldEMail.equals(newEMail)) {
            return true;
        }
        return false;
    }

    public boolean sameUserName(String oldUserName, String newUserName) {
        if (oldUserName.equals(newUserName)) {
            return true;
        }
        return false;
    }
    //method for username that already exists in progress

    @PreDestroy
    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }

}
