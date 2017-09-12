/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Mysql;

import DAO.Mysql.MySqlDaoFactory;
import DAO.PermissionHandlerDao;
import EXCEPTION.DatabaseException;
import MODEL.Service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tom
 */
public class PermissionHandlerDaoMysql implements PermissionHandlerDao{

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAll() throws DatabaseException {
    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object) throws DatabaseException {
    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    /**
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object key) throws DatabaseException {
    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
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
     */
    @Override
    public void closeDbConnection(ResultSet rs, Statement stmt, Connection conn) {
    
          MySqlDaoFactory.closeDbConnection(rs, stmt, conn);
          
    }
    
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public List listapermessi(String username)throws Exception{
    
        List<Service> listaServizi = new ArrayList();
      
        PreparedStatement a = this.getConnection()
                .prepareStatement("select servizi.* from user \n" +
"	left join usergroup on user.id_user = usergroup.id_user\n" +
"	left join gruppo on usergroup.id_group = gruppo.ID_GROUP\n" +
"	left join groupservice on gruppo.id_group= groupservice.id_group\n" +
"	left join servizi on groupservice.id_servizi = servizi.id_servizi\n" +
"	where user.username = ?");

        a.setString(1, username);
        ResultSet rs = a.executeQuery();
        
        while(rs.next()){
            Service servizio = new Service();
            servizio.setName(rs.getString("name"));
            servizio.setIdService(rs.getInt("ID_SERVIZI"));
            listaServizi.add(servizio);
        }
        return listaServizi;
    
    }
    /*questo metodo inserisci nel db l'user con i privilegi di utente*/

    /**
     *
     * @param id_user
     * @throws Exception
     */

    public void insertIntoGroup(int id_user) throws Exception{
    
        PreparedStatement a = this.getConnection().prepareStatement("insert into usergroup(ID_USER,ID_GROUP) value (?,1)");
    
        a.setInt(1, id_user);
        a.execute();
    
    }
    
    
    
}
