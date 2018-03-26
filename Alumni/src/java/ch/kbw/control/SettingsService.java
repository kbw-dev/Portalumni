/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import ch.kbw.dao.UserDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Adel
 */
@Named
@RequestScoped
public class SettingsService {

    @Inject
    private UserDAO userDAO;

    private String newEMail;
    private String newUserName;
    private String newPassword;
    private String againNewPassword;
    private boolean newsLetterChecked;
    
    private final String jdbcDriver = "com.mysql.jdbc.Driver";
    private final String dbUrl = "jdbc:mysql://localhost/db_portalumni";
    private final String caller = "root";
    private final String password = "";
    private Connection connection;

    public void saveSettings() {

        userDAO.changeSettingsMail(newEMail);
        userDAO.changeSettingsUsername(newUserName);
        if (userDAO.samePassword(newPassword, againNewPassword)) {
            userDAO.changeSettingsPassword(newPassword);
        }
        

    }
    
    public void deleteUser(){
        userDAO.deleteUser();
    }
    
    public String getNewEMail() {
        return newEMail;
    }

    public void setNewEMail(String newEMail) {
        this.newEMail = newEMail;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewUserName() {
        return newUserName;
    }

    public void setNewUserName(String newUserName) {
        this.newUserName = newUserName;
    }

    public String getAgainNewPassword() {
        return againNewPassword;
    }

    public void setAgainNewPassword(String againNewPassword) {
        this.againNewPassword = againNewPassword;
    }

    /**
     * @return the newsLetterChecked
     */
    public boolean isNewsLetterChecked() {
        return newsLetterChecked;
    }

    /**
     * @param newsLetterChecked the newsLetterChecked to set
     */
    public void setNewsLetterChecked(boolean newsLetterChecked) {
        this.newsLetterChecked = newsLetterChecked;
    }

}
