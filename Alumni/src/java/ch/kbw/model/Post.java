/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.model;

import java.sql.Date;

/**
 * Post equals to Agenda, Post = Beitrag
 * @author Adel
 */
public class Post {
   private int postID;
   private String title;
   private Date date;
   private String place;
   private String content;

    public Post() {
        this.title = "kein Titel";
        this.place = "kein Ort";
        this.content = "kein Inhalt";
    }

   

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
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
    
   
}
