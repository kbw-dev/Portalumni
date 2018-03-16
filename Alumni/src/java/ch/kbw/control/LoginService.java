/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


/**
 *
 * @author Adel
 */
@Named
@RequestScoped
public class LoginService {
    public String goToMailing(){
        return "mail.xhtml";
    }
    public String goToSettings(){
        return "settings.xhtml";
    }
}
