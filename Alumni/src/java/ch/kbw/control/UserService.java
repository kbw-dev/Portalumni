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
    
    public List<User> selectAllUser(){
        return userDAO.getAllUsers();
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }
    
    
}
