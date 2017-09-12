
package MODEL;

import MODEL.LevelI;
import MODEL.Trophy;

/**
 *
 * @author tom
 */
public class LevelImpl implements LevelI  {
 
    
    
    private int id_level;

   
    private int levelnumber;
    
    private int pointnextlevel;
    
    private Trophy trophy;
    
    /**
     *
     */
    public LevelImpl (){
                
                this.id_level = 0 ;
                this.levelnumber = 0;
                this.pointnextlevel = 0;
                this.trophy=null;
            }

    /**
     *
     * @return
     */
    @Override
    public int getId_level() {
    
        return id_level;
    }

    /**
     *
     * @param id_level
     */
    @Override
    public void setId_level(int id_level) {
        
        this.id_level = id_level;
    }
    
    /**
     *
     * @return
     */
    @Override
            public int getLevelnumber() {
                return levelnumber;
            }

    /**
     *
     * @param levelnumber
     */
    @Override
            public void setLevelnumber(int levelnumber) {
                this.levelnumber = levelnumber;
            }

    /**
     *
     * @return
     */
    @Override
            public int getPointnextlevel() {
                return pointnextlevel;
            }

    /**
     *
     * @param pointnextlevel
     */
    @Override
            public void setPointnextlevel(int pointnextlevel) {
                this.pointnextlevel = pointnextlevel;
            }

    /**
     *
     * @return
     */
    @Override
            public Trophy getTrophy() {
                return trophy;
            }

    /**
     *
     * @param trophy
     */
    @Override
            public void setTrophy(Trophy trophy) {
                this.trophy = trophy;
            }




}
