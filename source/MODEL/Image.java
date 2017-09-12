/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

/**
 *
 * @author tom
 */
public interface Image {
     
    /**
     *
     * @return
     */
    int getId_image();

    /**
     *
     * @return
     */
    String getPath();

    /**
     *
     * @return
     */
    String getTitle();

    /**
     *
     * @param id_image
     */
    void setId_image(int id_image);

    /**
     *
     * @param path
     */
    void setPath(String path);

    /**
     *
     * @param title
     */
    void setTitle(String title);
    
}
