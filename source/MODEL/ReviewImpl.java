/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import MODEL.Review;
import java.util.Date;

/**
 *
 * @author tom
 */
public class ReviewImpl implements Review{
    
    
     private int id_review;
    
    private String titlereview;
    
    
    private String textreview;
        
    private String username;
        
    private String titlegame;
    
    private Date date;

    /**
     *
     * @param titolorec
     * @param message
     * @param username
     * @param titlegame
     */
    public ReviewImpl(String titolorec ,String message,String username, String titlegame){
        
        this.titlereview =titolorec;
        this.textreview= message;
        this.username=username;
        this.titlegame=titlegame;
    }

    /**
     *
     */
    public ReviewImpl(){
        
            this.id_review = 0;
            
            this.titlereview = "";
            
            this.textreview = "";
            
            this.date = null ;
            
            this.username = "";
            
            this.titlegame="";
           
        
        }

    /**
     *
     * @return
     */
    @Override
    public int getId_review() {
        return id_review;
    }

    /**
     *
     * @param id_review
     */
    @Override
    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTitlereview() {
        return titlereview;
    }

    /**
     *
     * @param titlereview
     */
    @Override
    public void setTitlereview(String titlereview) {
        this.titlereview = titlereview;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTextreview() {
        return textreview;
    }

    /**
     *
     * @param textreview
     */
    @Override
    public void setTextreview(String textreview) {
        this.textreview = textreview;
    }

    /**
     *
     * @return
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    @Override
    public String getTitlegame() {
        return titlegame;
    }

    /**
     *
     * @param titlegame
     */
    @Override
    public void setTitlegame(String titlegame) {
        this.titlegame = titlegame;
    }

    /**
     *
     * @return
     */
    @Override
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    @Override
    public void setDate(Date date) {
        this.date = date;
    }
    
}
