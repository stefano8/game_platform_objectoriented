package Business;

import DAO.DaoFactory;
import MODEL.Group;
import MODEL.GroupService;
import MODEL.Service;
import MODEL.UserGroup;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tom
 */
public class BoMgr {
    
    
    private DaoFactory dbFactory ;  
    
    
    /**
     * 
     * @throws Exception 
     */
    private BoMgr() throws Exception{
    dbFactory = DaoFactory.getDaoFactory(1);
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public static BoMgr getInstance()throws Exception{
    
    return new BoMgr();
    
    }
    
    /**
     * Metodo per la creazione di un servizio
     * @param object
     * @return >0 or if error 0
     * @throws SQLException 
     */
     public int createService(Service object) throws SQLException {
    
        return  dbFactory.getServiceDao().create(object);
    
    }
    /**
     * Metodo per la creazione di un servizio
     * @param object
     * @return return >0 or if error 0
     * @throws SQLException 
     */
    public int createGroup(Group object) throws SQLException {
    
        return  dbFactory.getGroupDao().create(object);
    
    }
    /**
     * metodo per ottenere la lista di gruppi
     * @return
     * @throws SQLException 
     */
    public List<Group> getListGroup() throws SQLException{
    
        List<Group> listGroup = dbFactory.getGroupDao().loadAll();
        
        return listGroup;
        
    }
    /**
     * metodo per ottenere la lista di gruppi <> da admin
     * @return
     * @throws Exception 
     */
     public List<Group> getListGroupMod() throws Exception{
    
        List<Group> listGroup = dbFactory.getGroupDao().loadAllForMod();
        
        return listGroup;
        
    }
    /**
     * metodo per ottenere tutti i servizi
     * @return
     * @throws SQLException 
     */
    public List<Service> getListService() throws SQLException{
    
        List<Service> listservice = dbFactory.getServiceDao().loadAll();
        
        return listservice ;
    }
    /**
     * metodo per aggiornare un servizio
     * @param service
     * @throws SQLException 
     */
    public void storeService(Service service) throws SQLException{
    
        dbFactory.getServiceDao().store(service);
    
    }
    /**
     * metodo per aggiornare un gruppo
     * @param group
     * @throws SQLException 
     */
    public void storeGroup(Group group) throws SQLException{
    
        dbFactory.getGroupDao().store(group);
    
    }
    /**
     * metodo per rimuoverre un gruppo
     * @param group
     * @throws SQLException 
     */
    public void removeGroup(Group group) throws SQLException{
    
        dbFactory.getGroupDao().remove(group);
    
    }
    /**
     * metodo per rimuovere un servizio
     * @param key
     * @throws SQLException 
     */
    public void removeService(Service key) throws SQLException {
    
        dbFactory.getServiceDao().remove(key);
        
    }
    
    /**
     * metodo per ottenere la lista dei  gruppiservizi
     * @return
     * @throws SQLException 
     */
    public List <GroupService> getListGroupService() throws SQLException{
    
        return dbFactory.getGroupServiceDao().loadAll();
    
    }
   
    /**
     * rimuovere un grupposervizio
     * @param gs
     * @throws SQLException 
     */
    public void removeGroupService(GroupService gs) throws SQLException{
    
        dbFactory.getGroupServiceDao().remove(gs);
    
    }
    /**
     * inserire un grupposervizio
     * @param gs
     * @throws SQLException 
     */
    public void createGroupService(GroupService gs) throws SQLException{
    
        dbFactory.getGroupServiceDao().create(gs);
    
    }
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public List<UserGroup> getListUserGroup() throws SQLException{
    
       return dbFactory.getUserGroupDao().loadAll();
    
    }
    /**
     * metodo per aggiornare il gruppo di un utente
     * @param ug
     * @throws SQLException 
     */
    public void updateUserGroup(UserGroup ug) throws SQLException{
    
    
        dbFactory.getUserGroupDao().store(ug);
    }
    /**
     * metodo per ottenere lista di UserGroup <> da admin
     * @return  List UserGroup 
     * @throws Exception 
     */
    public List<UserGroup> getListUserOnly() throws Exception{
    
        return   dbFactory.getUserGroupDao().loadAllOnlyUser();
    
    }

}
