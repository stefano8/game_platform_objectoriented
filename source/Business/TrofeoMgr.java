package Business;

import DAO.DaoFactory;
import MODEL.Trophy;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tom
 */
public class TrofeoMgr {
    
    private DaoFactory dbfactory = DaoFactory.getDaoFactory(1);
    
    
    private TrofeoMgr(){
    
        
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static TrofeoMgr getIstance()throws Exception{
    
        return new TrofeoMgr();
    
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public List<Trophy> getListaTrofei() throws SQLException{
    
        return dbfactory.getTrofeoDao().loadAll();
    
    }
    /**
     * 
     * @param trofeo
     * @return
     * @throws SQLException 
     */
    public int createTrofeo(Trophy trofeo) throws SQLException{
    
        return dbfactory.getTrofeoDao().create(trofeo);
        
    }
    /**
     * 
     * @param trofeo
     * @throws SQLException 
     */
    public void removeTrofeo(Trophy trofeo) throws SQLException{
        
        dbfactory.getTrofeoDao().remove(trofeo);
        
    }
    /**
     * 
     * @param trofeo
     * @throws SQLException 
     */
    public void storeTrofeo(Trophy trofeo) throws SQLException{
    
        dbfactory.getTrofeoDao().store(trofeo);
    
    }
}
