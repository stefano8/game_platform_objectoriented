package Business;

import DAO.DaoFactory;
import MODEL.Service;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author tom
 */
public class PermissionMgr {
    
    
    private DaoFactory dbfactory ;
    
    private PermissionMgr()throws Exception{
    
        dbfactory = DaoFactory.getDaoFactory(1);
    
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public static PermissionMgr getIstance()throws Exception{
    
        return new PermissionMgr();
    
    }
    
    /**
     * 
     * @param username
     * @return
     * @throws Exception 
     */
    public List<Service> getListService(String username) throws Exception{
    
        List<Service> list = dbfactory.getPermissionHandlerDao().listapermessi(username);
    
        return list;
        
    }
    /**
     * 
     * @param listservice
     * @param service
     * @return 
     */
    public boolean isPermitted(List<Service> listservice,String service){
    
   
        Iterator <Service> it = listservice.iterator();
        while(it.hasNext()){
            if(it.next().getName().equals(service)){
                return true;
            }
        }
    
    return false;
    }
    
    
   /**
    * 
    * @param serv
    * @param group
    * @throws SQLException 
    */
    public void addServiceToGroup(int serv, int group) throws SQLException{
    
        dbfactory.getServiceDao().addServiceToGroup(serv, group);
    
    }
}
