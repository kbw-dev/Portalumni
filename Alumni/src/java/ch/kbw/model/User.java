/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.model;

/**
 *
 * @author Adel
 */

public class User {
    private int userID;
    private int companyManagementID;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private String emailPassword;
    private boolean admin;
    private boolean newsletter;
    private boolean isAllowed;

    public User(int userID, int companyManagementID, String firstName, String lastName, String userName, String password, String email, String emailPassword, boolean admin, boolean newsletter, boolean isAllowed) {
        this.userID = userID;
        this.companyManagementID = companyManagementID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.emailPassword = emailPassword;
        this.admin = admin;
        this.newsletter = newsletter;
        this.isAllowed = isAllowed;
    }
    public User(){
        
    }

    public int getId() {
        return userID;
    }

    public void setId(int userID) {
        this.userID = userID;
    }

    public int getCompanyManagementID() {
        return companyManagementID;
    }

    public void setCompanyManagementID(int companyManagementID) {
        this.companyManagementID = companyManagementID;
    }
    

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailPassword() {
        return emailPassword;
    }

    public void setEmailPassword(String emailPassword) {
        this.emailPassword = emailPassword;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean wantsNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public boolean isIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(boolean isAllowed) {
        this.isAllowed = isAllowed;
    }
    
    
    
        
}
