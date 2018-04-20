package ch.kbw.control;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import ch.kbw.dao.UserDAO;
import java.util.List;
import ch.kbw.model.User;

/**
 *
 * @author Adel
 */
@Named
@RequestScoped
public class UserService implements Serializable {

    
    @Inject
    private UserDAO userDAO;
    private ActionMessagesController amc = new ActionMessagesController();
    
    public List<User> selectAllUser(){
        return userDAO.getAllUsers();
    }
    public List<User> selectAllNonAllowedUsers(){
        System.out.println("TEST: " + userDAO.getAllNotAllowedPersons().size());
        return userDAO.getAllNotAllowedPersons();
    }
    public void allow(User user){
        userDAO.allowUser(user);
        amc.showMessage("Aktion durchgeführt", "Der User mit dem Usernamen " + user.getUserName() + " wurde zugelassen.");
    }
    public void deny(User user){
        //userDAO.getAllUsers().remove(user);
        userDAO.denyUser(user);
        amc.showMessage("Aktion durchgeführt", "Der User mit dem Usernamen " + user.getUserName() + " wurde abgelehnt.");
    }
    public UserDAO getUserDAO() {
        return userDAO;
    }
    
    
}
