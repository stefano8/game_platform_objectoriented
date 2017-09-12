package DAO.Mysql;

import DAO.Mysql.MySqlDaoFactory;
import DAO.PartitaDao;
import EXCEPTION.DatabaseException;
import MODEL.Match;
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
public class PartitaDaoMySql implements PartitaDao{

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
    
        int id_partita = 0 ;
       
        Match match = (Match) object;
        
        try {
        
            PreparedStatement cMatch = this.getConnection()
        
                    .prepareStatement("INSERT INTO gameplay (point,username,titlegame) value (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
            cMatch.setInt(1, 0);
            
            cMatch.setString(2, match.getUsername());
            
            cMatch.setString(3, match.getTitologioco());
            
            if (cMatch.executeUpdate() == 1) {
                    
                    ResultSet keys = cMatch.getGeneratedKeys();
                    
                    if (keys.next()) {
            
                        id_partita = keys.getInt(1);
            
                    }
            } 
        }catch (SQLException ex) {
        
            Logger.getLogger(PartitaDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id_partita;
    }

    /**
     *
     * @param keyobj
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object keyobj) throws DatabaseException {
        
        int key = (int)keyobj;
        Match match = new Match();
        
        try {
        
            PreparedStatement sMatch = this.getConnection().prepareStatement("select * from gameplay where id_gameplay= ?");
            
            sMatch.setInt(1, key);
            
            ResultSet rs = sMatch.executeQuery();
            
            if(rs.next()){
            
                match.setData(rs.getDate("date"));
            
                match.setUsername(rs.getString("username"));
            
                match.setId_match(key);
            
                match.setPunteggionuovo(rs.getInt("point"));
            
                match.setPunteggiototale(this.sumTotalPoint(match.getUsername()));
            
                match.setTitologioco(rs.getString("titlegame"));
           
            }
        
        } catch (SQLException ex) {
       
            Logger.getLogger(PartitaDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return match;
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object)  throws DatabaseException {
    
        try {
            Match match= (Match)object;
            
            PreparedStatement uMatch = this.getConnection().prepareStatement("update gameplay set point = ? where id_gameplay= ?");
            
            uMatch.setInt(1, match.getPunteggionuovo());
            
            uMatch.setInt(2, match.getId_match());
            
            uMatch.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PartitaDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    /**
     *
     * @param key
     * @throws DatabaseException
     */
    @Override
    public void remove(Object key)  throws DatabaseException{
    
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
    }

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public int sumTotalPoint(String username) throws SQLException {
    
        int totalpoint = 0;
        
        try {
            
            PreparedStatement sumPoint = this.getConnection().prepareStatement("SELECT sum(point) FROM gameplay WHERE username = ? ");
            
            sumPoint.setString(1, username);
            
            ResultSet rs = sumPoint.executeQuery();
            
            if(rs.next()){
            
                totalpoint = rs.getInt(1);
        
            }
            
        } catch (SQLException ex) {
        
            Logger.getLogger(PartitaDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return totalpoint;
    
    }
    
    /**
     *
     * @param username
     * @return
     * @throws DatabaseException
     */
    @Override
    public int currentLevel(String username)  throws DatabaseException{
        
        int numlvl = 0;
    
        try {
           
            PreparedStatement sNumLvl = this.getConnection().prepareStatement("SELECT numberlvl from gameplay where username = ? order by numberlvl desc limit 1 ");
        
            sNumLvl.setString(1, username);
            
            ResultSet rs = sNumLvl.executeQuery();
            
            if(rs.next()){
            
                numlvl = rs.getInt(1);
            
            }
            
        } catch (SQLException ex) {
        
            Logger.getLogger(PartitaDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
            return numlvl;
    
    }
    
    /**
     *
     * @param username
     * @param numberlvl
     * @return
     * @throws DatabaseException
     */
    @Override
    public int sumTotalPointForLevel(String username,int numberlvl) throws DatabaseException {
    
        int totalpoint = 0;
        
        try {
            
            PreparedStatement sumPointForLvl = this.getConnection().prepareStatement("SELECT sum(point) FROM gameplay WHERE username = ? and numberlvl = ?");
            
            sumPointForLvl.setString(1, username);
            sumPointForLvl.setInt(2, numberlvl);
            
            ResultSet rs = sumPointForLvl.executeQuery();
            System.out.println(sumPointForLvl);
           
            if(rs.next()){
            
                 totalpoint = rs.getInt(1);
                 
            }
            
               
        
            System.out.println("totalpoint :" + totalpoint);
            
        } catch (SQLException ex) {
        
            Logger.getLogger(PartitaDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return totalpoint;
    
    }
    
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public List getlistMatch(String username) throws Exception{
    
        List<Match> listmatch = new ArrayList();
        
        try {
            
            PreparedStatement query  = this.getConnection().prepareStatement("SELECT * FROM GAMEPLAY WHERE username = ? ORDER BY date");
            
            query.setString(1, username);
            
            ResultSet rs = query.executeQuery();
            
            while(rs.next()){
            
                Match match = (Match)this.load(rs.getInt("id_gameplay"));
            listmatch.add(match);
            }
        }
        catch (SQLException ex) {
        
            Logger.getLogger(PartitaDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
        return listmatch;
    
    
    }
   
}
