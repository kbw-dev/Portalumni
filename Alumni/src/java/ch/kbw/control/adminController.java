/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.kbw.control;

import java.awt.image.BufferedImage;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author anon
 */
@Named
@RequestScoped
public class adminController {
    
    private BufferedImage bImage;
    
    public void uploadPicture(){
        
    }

    /**
     * @return the bImage
     */
    public BufferedImage getbImage() {
        return bImage;
    }

    /**
     * @param bImage the bImage to set
     */
    public void setbImage(BufferedImage bImage) {
        this.bImage = bImage;
    }
    
}
