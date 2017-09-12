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
public interface Trophy {

    /**
     *
     * @return
     */
    int getId_trophy();

    /**
     *
     * @return
     */
    Image getImage();

    /**
     *
     * @return
     */
    String getName();

    /**
     *
     * @param id_trophy
     */
    void setId_trophy(int id_trophy);

    /**
     *
     * @param image
     */
    void setImage(Image image);

    /**
     *
     * @param name
     */
    void setName(String name);
    
}
