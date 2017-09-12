/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import MODEL.Image;
import MODEL.Trophy;



/**
 *
 * @author tom
 */
public class TrophyImpl implements Trophy  {
    
    private int id_trophy;
    
    private String name;
    
    private Image image;
    
    /**
     *
     */
    public TrophyImpl(){
        
            this.id_trophy=0;
            
            this.name = "";
            
            this.image = null;
        }

    /**
     *
     * @return
     */
    @Override
    public int getId_trophy() {
        return id_trophy;
    }

    /**
     *
     * @param id_trophy
     */
    @Override
    public void setId_trophy(int id_trophy) {
        this.id_trophy = id_trophy;
    }

    /**
     *
     * @return
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    @Override
    public Image getImage() {
        return image;
    }

    /**
     *
     * @param image
     */
    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    
        
}
