/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Mysql;

import DAO.DaoFactory;
import DAO.GiocoDao;
import DAO.GroupDao;
import DAO.GroupServiceDao;
import DAO.ImmagineDao;
import DAO.LivelloDao;
import DAO.Mysql.UserDaoMySql;
import DAO.Mysql.UserGroupMySql;
import DAO.PartitaDao;
import DAO.PermissionHandlerDao;
import DAO.RankDao;
import DAO.RecensioneDao;
import DAO.ServiceDao;
import DAO.TrofeoDao;
import DAO.UserDao;
import DAO.UserGroupDao;
import EXCEPTION.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 *
 * @author tom
 */
public class MySqlDaoFactory extends DaoFactory {

    /**
     *
     * @return
     * @throws DatabaseException
     */
    public static Connection getConnection() throws DatabaseException{
         
        Connection connection = null ;
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/gameplatform", "root", "tomtom");
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            throw new DatabaseException("errore connessione");
        }
        return connection;
    }

    static void closeDbConnection(ResultSet rs, Statement stmt, Connection conn) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public MySqlDaoFactory() {
    }

    /**
     *
     * @return
     */
    @Override
    public LivelloDao getLivelloDao() {
        
        return new LivelloDaoMySql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public TrofeoDao getTrofeoDao() {
    
        return new TrofeoDaoMySql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public UserDao getUserDao() {
    
        return new UserDaoMySql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public GiocoDao getGiocoDao() {
    
        return new GiocoDaoMySql();
    
    }

    /**
     *
     * @return
     */
    @Override
    
    public ImmagineDao getImmagineDao() {
    
        return new ImmagineDaoMySql();
    }

    /**
     *
     * @return
     */
    @Override
    public RecensioneDao getRecensioneDao() {
        
        return new RecensioneDaoMySql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public PartitaDao getPartitaDao() {
    
        return new PartitaDaoMySql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public RankDao getRankDao() {
      
        return new RankDaoMysql();
        
    }

    /**
     *
     * @return
     */
    @Override
    public PermissionHandlerDao getPermissionHandlerDao() {
    
        return new PermissionHandlerDaoMysql();
    }

    /**
     *
     * @return
     */
    @Override
    public GroupDao getGroupDao() {
        return new GroupDaoMysql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public ServiceDao getServiceDao() {
    
        return new ServiceDaoMysql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public GroupServiceDao getGroupServiceDao() {        
    
        return new GroupServiceMySql();
    
    }

    /**
     *
     * @return
     */
    @Override
    public UserGroupDao getUserGroupDao() {
    
        return new UserGroupMySql();
    
    }
    

}
