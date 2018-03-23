package ch.kbw.alumni;

import ch.kbw.dao.UserDAO;
import ch.kbw.model.User;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable {

    private static final long serialVersionUID = -2324232432423423432L;
    private String name, password, input, passwordInput;

    private boolean isLoggedIn;
    private String message;
    //private ArrayList<Address> adresse = new ArrayList<>();
    private int count, value;
    private String output, property, progress;
    private boolean hide = true;
    int i = 0;
    UserDAO userDao = new UserDAO();
    ArrayList<User> users = new ArrayList<>();

    public LoginController() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        super();
        name = null;
        password = null;
        isLoggedIn = false;
        //adresse.add(new Address(0, "", ""));
        message = "";
        count = 0;
        users = (ArrayList<User>) userDao.getAllUsers();
    }

    // Methodes
    public String checkUser() {

        if (checkAccount() && !getName().equals("")) {
            isLoggedIn = true;
            message = "Sie sind eingeloggt!";
            return "index.xhtml?faces-redirect=true";
        } else {
            message = "Ihr Loginname oder Ihr Passwort ist falsch!";
            return "login.xhtml?faces-redirect=true";
        }
    }

    public String logout() {
        isLoggedIn = false;
        return "logout.xhtml?faces-redirect=true";
    }

    // private boolean validateUser() {
    // AddressService as = new AddressService();
    // for (Address adress : as.getAllAddresses()) {
    // System.out.println(adress.getName());
    // if (getName().equals(adress.getName()) &&
    // getPassword().equals(adress.getPassword()) ) {
    // return true;
    // }
    // }
    //
    // return false;
    //
    // }
    // Getters
    public String getName() {
        return name;
    }

    public String getPasswordInput() {
        return passwordInput;
    }

    public void setPasswordInput(String passwordInput) {
        this.passwordInput = passwordInput;
    }

    public String getPassword() {

        return password;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    private boolean checkAccount() {
        if (users.size() != 0) {
            for (User user : users) {
                System.out.println(user.getUserName());
                if (getName().equals(user.getUserName()) && getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        }

        return false;

    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
