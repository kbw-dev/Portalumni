/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Adel
 */
public class ActionMessagesController {
    private FacesContext context;
    
    public void showMessage(String summary, String message){
        context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(summary, message));
    }
}
