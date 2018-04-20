/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import ch.kbw.dao.UserDAO;
import ch.kbw.model.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Adel
 */
@Named
@ApplicationScoped
public class AccessController {

    @Inject
    private UserDAO userDAO;
    private Map<User, List<String>> accessForUser = new HashMap<>();
    private String currentPage = "indexForUser.xhtml";
    private int counter = 0;

    public void addAllPagesFor(User u, List c) {
        if (u.getId() == 0) {
            //ANONYM USER
            c.add("indexForAnonymUser.xhtml");
            c.add("events.xhtml");
            c.add("fotogalerie.xhtml");
            c.add("login.xhtml");
            c.add("news.xhtml");
            c.add("registrierung.xhtml");
        } else if (u.getId() != 0 && !u.isAdmin()) {
            //STANDARD USER
            c.add("indexForStandardUser.xhtml");
            c.add("events.xhtml");
            c.add("fotogalerie.xhtml");
            c.add("login.xhtml");
            c.add("news.xhtml");
            c.add("registrierung.xhtml");
            c.add("settings.xhtml");
        } else if (u.isAdmin()) {
            //ADMIN
            c.add("index.xhtml");
            c.add("adminpage.xhtml");
            c.add("events.xhtml");
            c.add("fotogalerie.xhtml");
            c.add("login.xhtml");
            c.add("mail.xhtml");
            c.add("news.xhtml");
            c.add("registrierung.xhtml");
            c.add("settings.xhtml");
            c.add("adminpage.xhtml");
        }

    }

    public void addAllAccesses() {
        User anonymUser = new User();
        User standardUser = new User();
        User admin = new User();
        //ANONYM USER HAS THE ID 0
        //STANDARD DOESNT HAVE THE ID 0
        standardUser.setId(69);
        //ADMIN HAS A TRUE VALUE IN ADMIN BOOLEAN
        admin.setAdmin(true);
        admin.setId(1);
        //ALL PAGES FOR THE USERS
        List<String> pagesForAnonymUser = new ArrayList<>();
        List<String> pagesForStandardUser = new ArrayList<>();
        List<String> pagesForAdmin = new ArrayList<>();
        addAllPagesFor(anonymUser, pagesForAnonymUser);
        addAllPagesFor(standardUser, pagesForStandardUser);
        addAllPagesFor(admin, pagesForAdmin);

        accessForUser.put(anonymUser, pagesForAnonymUser);
        accessForUser.put(standardUser, pagesForStandardUser);
        accessForUser.put(admin, pagesForAdmin);

    }

    public String jumpOutOfPageByAccess(User u) {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        String[] parts = null;
        accessForUser.clear();
        addAllAccesses();
        for (User c : accessForUser.keySet()) {
            if(u.getId() == 0 && c.getId() == 0){
                u = c;
                break;
            }else if(u.getId() != 0 && !c.isAdmin() && !u.isAdmin() && c.getId() != 0){
                u = c;
                break;
            }else if(u.isAdmin() && c.isAdmin()){
                u = c;
                break;
            }
        }

        if (counter++ != 0) {
            currentPage = request.getRequestURL().toString();
            parts = currentPage.split("/");
            currentPage = parts[parts.length - 1];
        }

        if (!accessForUser.get(u).contains(currentPage)) {
            if(u.getId() == 0){
                return "indexForAnonymUser.xhtml";
            }else if(u.getId() != 0 && !u.isAdmin()){
                return "indexForStandardUser.xhtml";
            }else if(u.isAdmin()){
                 return "index.xhtml";
            }
            
        }
        return currentPage;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

}
