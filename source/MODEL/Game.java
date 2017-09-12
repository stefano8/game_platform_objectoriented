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

public interface Game {

    /**
     *
     * @return
     */
    float getAverage_vote();

    /**
     *
     * @return
     */
    String getDescription();

    /**
     *
     * @return
     */
    Image getImage();

    /**
     *
     * @return
     */
    String getTemplate();

    /**
     *
     * @return
     */
    String getTitle();

    /**
     *
     * @return
     */
    String getType();

    /**
     *
     * @return
     */
    int getValue();

    /**
     *
     * @param Average_vote
     */
    void setAverage_vote(float Average_vote);

    /**
     *
     * @param description
     */
    void setDescription(String description);

    /**
     *
     * @param image
     */
    void setImage(Image image);

    /**
     *
     * @param template
     */
    void setTemplate(String template);

    /**
     *
     * @param title
     */
    void setTitle(String title);

    /**
     *
     * @param type
     */
    void setType(String type);

    /**
     *
     * @param value
     */
    void setValue(int value);
    
}
