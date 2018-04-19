/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import ch.kbw.dao.AgendaDAO;
import ch.kbw.model.Post;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Business Logik also here
 *
 * @author Adel
 */
@Named
@RequestScoped
public class AgendaService implements Serializable {

    @Inject
    private AgendaDAO agendaDAO;

    private ActionMessagesController amc = new ActionMessagesController();
    private String title;
    private Date date;
    private String place;
    private String content;
    private String newContent;
    private String newTitle;
    private String newPlace;
    

    public void updateList() {
        agendaDAO.listAllPosts();
    }

    public void createNewPost() {
        java.sql.Date date1 = new java.sql.Date(this.date.getTime());
        agendaDAO.createPost(title, date1, place, content);
        amc.showMessage("Aktion durchgeführt", "Beitrag mit dem Titel " + title + " wurde erstellt.");
        this.title = "";
        this.place = "";
        this.content = "";
        this.date = null;
        
        this.newContent = content;
        this.newTitle = title;
        this.newPlace = place;

    }

    public Set<Post> selectAllPosts() {
        return agendaDAO.listAllPosts();
    }

    public void deletePost(Post post) {
        agendaDAO.deleteByID(post);
        amc.showMessage("Aktion durchgeführt", "Beitrag mit dem Titel " + post.getTitle() + " wurde gelöscht.");
    }

    public void editPost(Post post) {
        
        newContent = post.getContent();
        newTitle = post.getTitle();
        newPlace = post.getPlace();
        agendaDAO.makeChanges(post, newContent, newTitle, newPlace);
        updateList();
        amc.showMessage("Aktion durchgeführt", "Beitrag mit dem Titel " + post.getTitle() + " wurde bearbeitet.");
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewContent() {
        return newContent;
    }

    public void setNewContent(String newContent) {
        this.newContent = newContent;
    }

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public void setNewPlace(String newPlace) {
        this.newPlace = newPlace;
    }
    

   
    
}
