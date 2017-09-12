
package Business;

import DAO.DaoFactory;
import java.sql.SQLException;
import java.util.List;
import MODEL.LevelI;

/**
 *
 * @author tom
 */
public class PlatformMgr {
  
     private DaoFactory dbFactory = DaoFactory.getDaoFactory(1);  
    
    
    
    private PlatformMgr(){
    
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public static PlatformMgr getInstance()throws Exception{
    
    return new PlatformMgr();
    
    }

    /**
     * 
     * @param numlevel
     * @return
     * @throws Exception 
     */
    public LevelI getLevel(int numlevel) throws Exception{
   
        return (LevelI) dbFactory.getLivelloDao().load(numlevel);

    }

  /**
   * 
   * @return
   * @throws SQLException 
   */
    public List<LevelI> LoadAllLevel() throws SQLException{
   
        List<LevelI> listlevels= dbFactory.getLivelloDao().loadAll();
   
        return listlevels;
    }
    
    /**
     * 
     * @param level
     * @return
     * @throws SQLException 
     */
    public int insertLevel(LevelI level) throws SQLException{
   
       return  dbFactory.getLivelloDao().create(level);
   
    }
   
}
