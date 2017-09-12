/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.DaoFactory;
import MODEL.Trophy;
import MODEL.User;
import java.sql.Date;
import java.util.Map;

/**
 *
 * @author tom
 */
public class UserMgr {
    
    private DaoFactory dbfactory;
    
    
    private UserMgr(){
    
        dbfactory = DaoFactory.getDaoFactory(1);
    
    }
    
    /**
     *
     * @return
     */
    public static UserMgr getIstance(){
        
        return new UserMgr();
    
    }
    
    /**
     * 
     * @param user
     * @return
     * @throws Exception 
     */
    public int registerUser(User user) throws Exception{
    
        int id_user = 0;
    
        id_user = dbfactory.getUserDao().create(user);
        
        dbfactory.getPermissionHandlerDao().insertIntoGroup(id_user);
        
        return id_user;
    
    }
    
    /**
     * 
     * @param id_user
     * @return
     * @throws Exception 
     */
    public User getUser(int id_user) throws Exception{
    
        User user = (User)dbfactory.getUserDao().load(id_user);
    
        return user;
    
    }
    /**
     * 
     * @param username
     * @param password
     * @return
     * @throws Exception 
     */
    public int checkLogin(String username,String password) throws Exception{
    
        int id_user= dbfactory.getUserDao().checkLogin(username, password);
    
        return id_user;
    }
    /**
     * 
     * @param username
     * @return
     * @throws Exception 
     */
    public Map<Integer,Map<Date,Trophy>> getMyTrophyWin(String username)throws Exception{
    
        Map<Integer,Map<Date,Trophy>> mappa = dbfactory.getTrofeoDao().trofeiVinti(username);
    
        return mappa;
    
    }

}
