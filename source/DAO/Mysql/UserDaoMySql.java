package DAO.Mysql;

import DAO.UserDao;
import EXCEPTION.DatabaseException;
import MODEL.Group;
import MODEL.User;
import MODEL.UserImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author tom
 */
public class UserDaoMySql implements UserDao{

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public Connection getConnection()  throws DatabaseException{
    
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
     * @param key
     * @throws DatabaseException
     */
    @Override
    public void remove(Object key) throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    /**
    *METODO PER l'inserimento di un utente
     * @param object
     * @return 
     * @throws EXCEPTION.DatabaseException 
    */
    @Override
    public int create(Object object) throws DatabaseException {
        
        int id = 0 ;
        try {
            
            User user = (User) object ;
            
            PreparedStatement insertUser = this.getConnection().prepareStatement("insert into user"
                    + "  (username,name,surname,password,address,email,city )"
                    + " value(?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
            
            insertUser.setString(1, user.getUsername());
            insertUser.setString(2, user.getName());
            insertUser.setString(3, user.getSurname());
            insertUser.setString(4, user.getPassword());
            insertUser.setString(5, user.getAddress());
            insertUser.setString(6, user.getEmail());
            insertUser.setString(7, user.getCity());
            
            
            
            if(insertUser.executeUpdate()== 1){
                
                ResultSet rs =  insertUser.getGeneratedKeys();
                
                if(rs.next()){
                    
                    id = rs.getInt(1);
                }
                System.out.println(id +"valore ritornato");
            }
            
            
            return id ;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return id ;

    }

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
    * METODO PER IL RECUPERO DI UN UTENTE DATO LO USERNAME
     * @param usernameObj
    *
    * 
    * @return interfaccia user
     * @throws EXCEPTION.DatabaseException
    */
   
    @Override
    public Object load(Object usernameObj) throws DatabaseException{
      
           User user = new UserImpl();
        try {
         
            int id = (Integer) usernameObj ;
            PreparedStatement stat = this.getConnection().prepareStatement(" SELECT * FROM user left join usergroup on user.id_user = usergroup.ID_USER where user.id_user = ?");
            
            stat.setInt(1, id);
            ResultSet rs = stat.executeQuery();
            
            if(rs.next()){
                
                user.setId(id);
                user.setUsername(rs.getString("username"));
                user.setName(rs.getString("name"));
                user.setSurname(rs.getString("surname"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setCity(rs.getString("city"));
                Group group = (Group) new GroupDaoMysql().load(rs.getInt("ID_GROUP"));
                user.setGroup(group);
                
                
            }
            
            return user ;
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return user ;
    
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object)  throws DatabaseException {
              
    }//end metodo

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws DatabaseException
     */
    @Override
    public int checkLogin(String username, String password) throws DatabaseException {
    
        try {
            PreparedStatement stat = this.getConnection().prepareStatement(" SELECT * FROM user  where username = ? and password = ? ");
            
            stat.setString(1, username);
            stat.setString(2, password);
            
            ResultSet rs = stat.executeQuery();
            
            if(rs.next()){
                
                return rs.getInt("id_user");
                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
         return 0 ;
    }


            
        
    
}
