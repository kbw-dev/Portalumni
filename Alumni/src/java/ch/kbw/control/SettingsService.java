/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import ch.kbw.dao.UserDAO;
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

    public void saveSettings() {

        userDAO.changeSettingsMail(newEMail);
        userDAO.changeSettingsUsername(newUserName);
        if (userDAO.samePassword(newPassword, againNewPassword)) {
            userDAO.changeSettingsPassword(newPassword);
        }
        

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
