package DAO.Mysql;

import DAO.GroupDao;
import DAO.Mysql.MySqlDaoFactory;
import EXCEPTION.DatabaseException;
import MODEL.Group;
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
public class GroupDaoMysql implements GroupDao{

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAll() throws DatabaseException {
      
    List<Group> listgroup = new ArrayList();
    
    try {
            PreparedStatement   sListLevel = this.getConnection().prepareStatement("SELECT * FROM gruppo");
            
            
            ResultSet list = sListLevel.executeQuery();
            
            while(list.next()){
            
                Group group = new Group();
                group.setName(list.getString("name"));
                group.setIdgroup(list.getInt("ID_GROUP"));
                
                listgroup.add(group);
                
            }
    }  catch (SQLException ex ) {
    
        throw new DatabaseException("");
    
    }
    
    return listgroup;
    }

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List<Group> loadAllForMod() throws DatabaseException{
     
    
        List<Group> listgroup = new ArrayList();
    
    try {
            PreparedStatement   sListLevel = this.getConnection().prepareStatement("SELECT * FROM gruppo where name <> 'admin'");
            
            
            ResultSet list = sListLevel.executeQuery();
            
            while(list.next()){
            
                Group group = new Group();
                group.setName(list.getString("name"));
                group.setIdgroup(list.getInt("ID_GROUP"));
                
                listgroup.add(group);
                
            }
    }   catch (SQLException ex ) {
    
        throw new DatabaseException("");
    
    }
    
    return listgroup;
    }
     
    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object) throws DatabaseException {
    
        Group group = (Group)object;
        
        
        
          try {
            PreparedStatement a = this.getConnection().prepareStatement("insert into gruppo(name) value (?)",Statement.RETURN_GENERATED_KEYS);
            
            a.setString(1, group.getName());
            a.execute();
            
            ResultSet rs = a.getGeneratedKeys();
            
            if(rs.next()){
            
            return rs.getInt(1);
            }
        }  catch (SQLException ex ) {
    
        throw new DatabaseException("");
    
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
    
         int id = (Integer) key ;
        Group group = new Group(); 
        try {
            PreparedStatement  selectgroup = this.getConnection().prepareStatement("select * from gruppo where ID_GROUP = ?");
            
            selectgroup.setInt(1, id);
            
            ResultSet rs = selectgroup.executeQuery();
            
            if(rs.next()){
            
                group.setName(rs.getString("NAME"));
                group.setIdgroup(id);
            
            }
            
            return group ;
            
        } catch (SQLException ex) {
        
             throw new DatabaseException("impossibile caricare gruppo") ;
        }
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object) throws DatabaseException {
    
         try {
            Group gruppo = (Group) object ;
            
            PreparedStatement storegroup = this.getConnection().prepareStatement(" update gruppo set name = ? where ID_GROUP = ?");
            
            storegroup.setString(1, gruppo.getName());
            storegroup.setInt(2, gruppo.getIdgroup());
            
            storegroup.execute();
        }  catch (SQLException ex ) {
    
        throw new DatabaseException("");
    
    }
    }

    /**
     *
     * @param key
     * @throws DatabaseException
     */
    @Override
    public void remove(Object key) throws DatabaseException {
    
        try {
            Group id = (Group)key ;
            
            
            PreparedStatement   deletegroup = this.getConnection().prepareStatement("delete FROM gruppo where ID_GROUP= ?");
            
            deletegroup.setInt(1, id.getIdgroup());
            
            deletegroup.execute();
            
        }  catch (SQLException ex ) {
    
        throw new DatabaseException("");
    
    }
        
    
    }

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public Connection getConnection() throws DatabaseException    {    
        Connection conn = MySqlDaoFactory.getConnection();
        return conn;
    }

    /**
     *
     * @param rs
     * @param stmt
     * @param conn
     */
    @Override
    public void closeDbConnection(ResultSet rs, Statement stmt, Connection conn) {
        MySqlDaoFactory.closeDbConnection(rs, stmt, conn);
    }
    
}
