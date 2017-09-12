package MODEL.IMPL;

import MODEL.Game;
import MODEL.Game;
import MODEL.Image;
import MODEL.Image;

/**
 *
 * @author tom
 */
public class GameImpl  implements Game  {  
    
    private String title;
    
    private String type;
    
    private float Average_vote;
    
    private String description;
    
    private int value;
    
    private String template;
    
    private Image image;

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

    /**
     *
     * @return
     */
    @Override
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    @Override
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    @Override
    public float getAverage_vote() {
        return Average_vote;
    }

    /**
     *
     * @param Average_vote
     */
    @Override
    public void setAverage_vote(float Average_vote) {
        this.Average_vote = Average_vote;
    }

    /**
     *
     * @return
     */
    @Override
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     *
     * @param value
     */
    @Override
    public void setValue(int value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTemplate() {
        return template;
    }

    /**
     *
     * @param template
     */
    @Override
    public void setTemplate(String template) {
        this.template = template;
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

