package DAO.Mysql;


import DAO.Mysql.MySqlDaoFactory;
import DAO.ServiceDao;
import EXCEPTION.DatabaseException;
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
public class ServiceDaoMysql implements ServiceDao{

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAll() throws DatabaseException {
    
         List<Service> listservizi = new ArrayList();
    
    try {
            PreparedStatement   sListServizi = this.getConnection().prepareStatement("SELECT * FROM servizi");
            ResultSet list = sListServizi.executeQuery();
            
            while(list.next()){
            
                Service servizio =(Service) this.load(list.getInt("ID_SERVIZI")) ;
                
                listservizi.add(servizio);
                
            }
    }  catch (SQLException ex) {
            Logger.getLogger(LivelloDaoMySql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    
    return listservizi;
        
    
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object) throws DatabaseException {
    // insert into servizi (NAME) value ('asd');
        Service service = (Service) object;
        
        try {
            PreparedStatement  insertService = this.getConnection().prepareStatement("insert into servizi(NAME) value (?)",com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
            insertService.setString(1, service.getName());
            insertService.execute();
            
            ResultSet rs = insertService.getGeneratedKeys();
                 if(rs.next())
                 {
                     return rs.getInt(1);
                 }
                 
            
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
    
        int id = (Integer) key ;
        Service service = new Service(); 
        try {
            PreparedStatement  selectservice = this.getConnection().prepareStatement("select * from servizi where ID_SERVIZI = ?");
            
            selectservice.setInt(1, id);
            
            ResultSet rs = selectservice.executeQuery();
            
            if(rs.next()){
            
                service.setName(rs.getString("NAME"));
                service.setIdService(id);
            
            }
            
            return service ;
            
        }  catch (SQLException ex) {
            Logger.getLogger(ServiceDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        throw new DatabaseException("impossibile caricare servizio") ;
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object) throws DatabaseException {
        
        try {
            Service service = (Service) object ;
            
            PreparedStatement storeservice = this.getConnection().prepareStatement(" update servizi set name = ? where ID_SERVIZI= ?");
            
            storeservice.setString(1, service.getName());
            storeservice.setInt(2, service.getIdService());
            
            storeservice.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param key
     * @throws DatabaseException
     */
    @Override
    public void remove(Object key) throws DatabaseException {
    
        Service service = (Service) key ;
            
        try {
            PreparedStatement storeservice = this.getConnection().prepareStatement(" delete from servizi where ID_SERVIZI= ?");
        
            storeservice.setInt(1, service.getIdService());
            
            storeservice.execute();
        
        } catch (SQLException ex) {
            Logger.getLogger(ServiceDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    
    }
    
    /**
     *
     * @param ser
     * @param group
     * @throws DatabaseException
     */
    public void addServiceToGroup(int ser, int group) throws DatabaseException{
    
        try {
            PreparedStatement add = this.getConnection().prepareStatement(" INSERT INTO groupservice VALUE (?,?)");
            add.setInt(2, ser);
            add.setInt(1,group);
            add.execute();
        
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