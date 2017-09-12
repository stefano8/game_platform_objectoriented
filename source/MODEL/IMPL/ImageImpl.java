/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL.IMPL;

import MODEL.Image;

/**
 *
 * @author tom
 */
public class ImageImpl implements Image{
    
    
    private int id_image ;
    
    private String path;
    
    private String title;

    /**
     *
     */
    public ImageImpl(){
            this.id_image = 0;
            
            this.path="";
    
            this.title="";
        }

    /**
     *
     * @return
     */
    @Override
        public int getId_image() {
            
            return id_image;
        }

    /**
     *
     * @param id_image
     */
    @Override
        public void setId_image(int id_image) {
           
            this.id_image = id_image;
        }

    /**
     *
     * @return
     */
    @Override
        public String getPath() {
            
            return path;
        }

    /**
     *
     * @param path
     */
    @Override
        public void setPath(String path) {
            
            this.path = path;
        }

    /**
     *
     * @return
     */
    @Override
        public String getTitle() {
            
            return title;
        }

    /**
     *
     * @param title
     */
    @Override
        public void setTitle(String title) {
            
            this.title = title;
        }
    
    
    
}
