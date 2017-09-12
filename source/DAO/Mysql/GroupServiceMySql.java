/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Mysql;

import DAO.GroupServiceDao;
import DAO.Mysql.ServiceDaoMysql;
import DAO.Mysql.MySqlDaoFactory;
import EXCEPTION.DatabaseException;
import MODEL.Group;
import MODEL.GroupService;
import MODEL.GroupServiceImpl;
import MODEL.Service;
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
public class GroupServiceMySql implements GroupServiceDao{

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAll() throws DatabaseException {
        
        List<GroupService> listGroupService = new ArrayList<>();
        
        try {
            PreparedStatement all = this.getConnection().prepareStatement(" select * from groupservice ");
       
           ResultSet rs = all.executeQuery();
           
           while(rs.next()){
           
               GroupService groupservice = new GroupServiceImpl();
                              
               groupservice.setIDgroupservice(rs.getInt("ID"));
               
               groupservice =(GroupService) this.load(groupservice);
               
               listGroupService.add(groupservice);
           }
        }  catch (SQLException ex) {
            Logger.getLogger(GroupServiceMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
          return listGroupService;
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object) throws DatabaseException {
       
        GroupService groupservice =  (GroupServiceImpl) object ;
     
        
        System.out.println("idService :" + groupservice.getService().getIdService() );
        System.out.println("idGroup :" + groupservice.getGroup().getIdgroup());
        try {
            PreparedStatement add = this.getConnection().prepareStatement(" INSERT INTO groupservice(ID_GROUP,ID_SERVIZI) VALUE (?,?)");
            
            add.setInt(2,groupservice.getGroup().getIdgroup());
            
            add.setInt(1, groupservice.getService().getIdService());
            
            
            System.out.println(add);
            add.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
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
    
        GroupService groupservice = (GroupService) key;
        
        PreparedStatement stat;
        try {
            stat = this.getConnection().prepareStatement("select * from groupservice where id = ?");
        
            stat.setInt(1, groupservice.getIDgroupservice());
            
            ResultSet rs = stat.executeQuery();
            
            if(rs.next()){
            
                Group group = (Group) new GroupDaoMysql().load(rs.getInt("ID_GROUP"));

                Service service = (Service) new ServiceDaoMysql().load(rs.getInt("ID_SERVIZI"));

                
                groupservice.setGroup(group);

                groupservice.setService(service);
            
            
            }
        } catch (SQLException ex) {
            Logger.getLogger(GroupServiceMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       

        return groupservice ;
    
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     * @throws DatabaseException
     */
    @Override
    public void remove(Object key) throws DatabaseException {
    
        GroupService groupservice = (GroupService) key ;
            
        try {
            PreparedStatement removegroupservice = this.getConnection().prepareStatement(" delete from groupservice where ID = ?");
        
            removegroupservice.setInt(1, groupservice.getIDgroupservice());
            removegroupservice.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
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
    
}
