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
public interface LevelI {

    /**
     *
     * @return
     */
    int getLevelnumber();

    /**
     *
     * @return
     */
    int getPointnextlevel();

    /**
     *
     * @return
     */
    Trophy getTrophy();

    /**
     *
     * @param levelnumber
     */
    void setLevelnumber(int levelnumber);

    /**
     *
     * @param pointnextlevel
     */
    void setPointnextlevel(int pointnextlevel);

    /**
     *
     * @param trophy
     */
    void setTrophy(Trophy trophy);
    
    /**
     *
     * @return
     */
    public int getId_level();
    
    /**
     *
     * @param id_level
     */
    public void setId_level(int id_level);
    
}
