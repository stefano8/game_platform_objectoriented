/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Mysql;

import DAO.UserGroupDao;
import EXCEPTION.DatabaseException;
import MODEL.Group;
import MODEL.User;
import MODEL.UserGroup;
import MODEL.UserGroupImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tom
 */
public class UserGroupMySql implements UserGroupDao{

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAll() throws DatabaseException {
    
        List<UserGroup> listusergroup =new ArrayList();
    
        try {
            PreparedStatement all = this.getConnection().prepareStatement(" select * from usergroup ");
       
           ResultSet rs = all.executeQuery();
           
           while(rs.next()){
           
               UserGroup usergroup = new UserGroupImpl();
                              
               usergroup.setId(rs.getInt("ID"));
               
               usergroup =(UserGroup) this.load(usergroup);
               
               listusergroup.add(usergroup);
           }
        } catch (SQLException ex) {
            
        throw new DatabaseException("aaaaaaaaaaaa");
        }
        
        return listusergroup;
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object) throws DatabaseException {
    
        try {
            User user = (User)object ;
            PreparedStatement insertusergroup = this.getConnection().prepareStatement("insert into usergroup(ID_USER,ID_GROUP) value (?,?)",Statement.RETURN_GENERATED_KEYS);
            
            insertusergroup.setInt(1, user.getId());
            insertusergroup.setInt(2, 1);
            insertusergroup.execute();
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return 0;
    }

    /**
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object key) throws DatabaseException {
    
        UserGroup usergroup = (UserGroup) key;
        
        PreparedStatement stat;
        try {
            stat = this.getConnection().prepareStatement("select * from usergroup where id = ?");
        
            stat.setInt(1, usergroup.getId());
            
            ResultSet rs = stat.executeQuery();
            
            if(rs.next()){
            
                Group group = (Group) new GroupDaoMysql().load(rs.getInt("ID_GROUP"));

                User user = (User) new UserDaoMySql().load(rs.getInt("ID_USER"));
                
                usergroup.setGroup(group);

                usergroup.setUser(user);
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usergroup ;
    
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object) throws DatabaseException {
    
        try {
            UserGroup ug = (UserGroup) object;
            
            PreparedStatement stat = this.getConnection().prepareStatement("update usergroup set ID_GROUP= ? where id = ? ");
            
            stat.setInt(1, ug.getGroup().getIdgroup());
            stat.setInt(2, ug.getId());
            
            stat.execute();
            
        }  catch (SQLException ex) {
            Logger.getLogger(UserGroupMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    /**
     *
     * @param key
     * @throws DatabaseException
     */
    @Override
    public void remove(Object key) throws DatabaseException {
    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public Connection getConnection() throws DatabaseException {
    
        Connection conn = MySqlDaoFactory.getConnection();
       
        return conn;
    
    }

    /**
     *
     * @param rs
     * @param stmt
     * @param conn
     * @throws DatabaseException
     */
    @Override
    public void closeDbConnection(ResultSet rs, Statement stmt, Connection conn) throws DatabaseException {
    
         MySqlDaoFactory.closeDbConnection(rs, stmt, conn);
    
    }

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAllOnlyUser() throws DatabaseException {
    
            List<UserGroup> listusergroup =new ArrayList();
    
        try {
            PreparedStatement all = this.getConnection().prepareStatement(" select * from usergroup WHERE ID_GROUP<>3");
       
           ResultSet rs = all.executeQuery();
           
           while(rs.next()){
           
               UserGroup usergroup = new UserGroupImpl();
                              
               usergroup.setId(rs.getInt("ID"));
               
               usergroup =(UserGroup) this.load(usergroup);
               
               listusergroup.add(usergroup);
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserGroupMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listusergroup;
    }
    
}
