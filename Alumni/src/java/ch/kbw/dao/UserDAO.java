package ch.kbw.dao;

import ch.kbw.control.MailService;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PreDestroy;

import javax.inject.Named;

import ch.kbw.model.User;
import java.sql.Statement;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Adel
 */
@Named
@SessionScoped
public class UserDAO implements Serializable {
    
    //INJECTION POINT OF OVERALL DATA ACCESS OBJECT
    @Inject
    private OverallDAO overallDAO;
    // LIST OF USER
    private List<User> allUsers = new ArrayList<>();

    // HOLDS THE CURRENT STANDARD USER - JUST FOR TESTING
    private User currentUser = new User();

    

    public List<User> getAllUsers() {
        overallDAO.init();
        allUsers.clear();
        ResultSet rs = null;
        PreparedStatement pst = null;
        String query = "Select * from benutzer";

        try {
            pst = overallDAO.getConnection().prepareStatement(query);
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
                allUsers.add(user);
            }

            pst.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public void changeSettingsNewsLetterChecked(boolean isNewsLetterChecked) {
        if (!sameNewsLetterChecked(currentUser.wantsNewsletter(), isNewsLetterChecked)) {
            PreparedStatement pst = null;
            String query = "UPDATE benutzer "
                    + "SET NEWSLETTERCHECKED = ? WHERE userID = ?";
            try {
                pst = overallDAO.getConnection().prepareStatement(query);
                pst.setBoolean(1, isNewsLetterChecked);
                pst.setInt(2, currentUser.getId());
                currentUser.setNewsletter(isNewsLetterChecked);
                pst.executeUpdate();
                overallDAO.getLog().info("SUCESSFULLY UPDATED NEWSLETTERACTIVATION OF CURRENT USER: " + currentUser.getUserName());
            } catch (SQLException ex) {
                Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (sameNewsLetterChecked(currentUser.wantsNewsletter(), isNewsLetterChecked)) {
            //Fehleranzeige "Gleiche wantsNewsLetter eingegeben"
        }
    }

    public void changeSettingsMail(String newEMail) {
        if (notEmptyInput(newEMail)) {
            if (!sameEMail(currentUser.getEmail(), newEMail)) {
                PreparedStatement pst = null;
                String query = "UPDATE benutzer "
                        + "SET Email = ? WHERE userID = ?";
                try {
                    pst = overallDAO.getConnection().prepareStatement(query);
                    pst.setString(1, newEMail);
                    pst.setInt(2, currentUser.getId());
                    currentUser.setEmail(newEMail);
                    pst.executeUpdate();
                    overallDAO.getLog().info("SUCESSFULLY UPDATED EMAIL OF CURRENT USER: " + currentUser.getUserName());
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
                pst = overallDAO.getConnection().prepareStatement(query);
                pst.setString(1, newPassword);
                pst.setInt(2, currentUser.getId());
                currentUser.setPassword(newPassword);
                pst.executeUpdate();
                overallDAO.getLog().info("SUCESSFULLY UPDATED PASSWORD OF CURRENT USER: " + currentUser.getUserName());
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
                    pst = overallDAO.getConnection().prepareStatement(query);
                    pst.setString(1, newUsername);
                    pst.setInt(2, currentUser.getId());
                    currentUser.setUserName(newUsername);
                    pst.executeUpdate();
                    overallDAO.getLog().info("SUCESSFULLY UPDATED USERNAME OF CURRENT USER: " + currentUser.getFirstName());
                } catch (SQLException ex) {
                    Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (sameUserName(currentUser.getUserName(), newUsername)) {
                // Fehlerannzeige "Gleicher Username eingegeben"
            }

        }

    }
    
    public void deleteUser() {
        MailService ms = new MailService();
        ms.mailToAdmin("User deleted his account", "User " + this.currentUser.getFirstName() + " " + this.currentUser.getLastName() + " with the username " + this.currentUser.getUserName() + " deleted his account. If you want to contact him, his last registered mail adress was: " + this.currentUser.getEmail());
        String sql = "DELETE FROM benutzer WHERE userID = '" + this.getCurrentUser().getId() + "';";
        try {
            Statement stmt = overallDAO.getConnection().createStatement();
            stmt.execute(sql);
            overallDAO.getConnection().close();
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean sameNewsLetterChecked(boolean oldNewsLetterChecked, boolean newNewsLetterChecked) {
        if (oldNewsLetterChecked == newNewsLetterChecked) {
            return true;
        }
        return false;
    }

    @PreDestroy
    public void destroy() {
        try {
            overallDAO.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

}
