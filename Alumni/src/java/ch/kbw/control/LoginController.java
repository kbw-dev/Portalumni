package ch.kbw.control;

import ch.kbw.dao.AgendaDAO;
import ch.kbw.dao.UserDAO;
import ch.kbw.model.User;
import java.io.Serializable;
import java.sql.SQLException;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
// LET IT BE SESSIONSCOPED
@SessionScoped
public class LoginController implements Serializable {
    
    //INJECTION POINT --> NO NEED FOR INSTANTIATION 
    @Inject
    private UserDAO userDAO;
    @Inject
    private AgendaDAO agendaDAO;
    
    private static final long serialVersionUID = -2324232432423423432L;
    private String name, password, input, passwordInput;
    private boolean isLoggedIn;
    private String message;
    //private ArrayList<Address> adresse = new ArrayList<>();
    private int count, value;
    private String output, property, progress;
    private boolean hide = true;
    int i = 0;

    public LoginController() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        this.name = "";
        this.password = "";
        this.message = "";
        this.isLoggedIn = false;
        this.count = 0;
        
    }

    // Methods
    public String checkUser() {

        if (checkAccount() && !getName().equals("")) {
            isLoggedIn = true;
            this.message = "Sie sind eingeloggt!";
            this.name = "";
            this.password = "";
            return "index.xhtml?faces-redirect=true";
        } else {
            this.message = "Ihr Loginname oder Ihr Passwort ist falsch!";
            this.name = "";
            this.password = "";
            return "login.xhtml?faces-redirect=true";
        }
    }
   
    public String logout() {
        this.isLoggedIn = false;
        return "logout.xhtml?faces-redirect=true";
    }
    
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
        if (!userDAO.getAllUsers().isEmpty()) {
            for (User user : userDAO.getAllUsers()) {
                System.out.println(user.getUserName());
                if (getName().equals(user.getUserName()) && getPassword().equals(user.getPassword())) {
                    userDAO.setCurrentUser(user);
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
