/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODEL;

import java.util.Date;

/**
 *
 * @author tom
 */
public interface Review {
    
    /**
     *
     * @return
     */
    Date getDate();

    /**
     *
     * @return
     */
    int getId_review();

    /**
     *
     * @return
     */
    String getTextreview();

    /**
     *
     * @return
     */
    String getTitlegame();

    /**
     *
     * @return
     */
    String getTitlereview();

    /**
     *
     * @return
     */
    String getUsername();

    /**
     *
     * @param date
     */
    void setDate(Date date);

    /**
     *
     * @param id_review
     */
    void setId_review(int id_review);

    /**
     *
     * @param textreview
     */
    void setTextreview(String textreview);

    /**
     *
     * @param titlegame
     */
    void setTitlegame(String titlegame);

    /**
     *
     * @param titlereview
     */
    void setTitlereview(String titlereview);

    /**
     *
     * @param username
     */
    void setUsername(String username);
}
