/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Mysql;


import DAO.Mysql.MySqlDaoFactory;
import DAO.RankDao;
import EXCEPTION.DatabaseException;
import MODEL.Match;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author tom
 */
public class RankDaoMysql implements RankDao{

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
    public int create(Object object)  throws DatabaseException {

        int key =0;
        
        try {
            
            PreparedStatement insertRank = this.getConnection().prepareStatement("insert into rank value (0,?,0)");
            
            String username = (String)object ;
            
            insertRank.setString(1, username);
                       
            insertRank.executeUpdate();
            
            
            
            
        } catch (SQLException ex) {
           throw new DatabaseException("as");
        } catch (Exception ex) {
            Logger.getLogger(RankDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return key;
    }

    /**
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object key)  throws DatabaseException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object)  throws DatabaseException {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param username
     * @return
     * @throws DatabaseException
     */
    @Override
    public int recuperaNumeroLivello(String username) throws DatabaseException {
    
            int numberlvl = 0;
         try {
            PreparedStatement sNumberLvl = this.getConnection().prepareStatement("SELECT numberlevel FROM rank WHERE username = ?");
            sNumberLvl.setString(1, username);
            ResultSet rs = sNumberLvl.executeQuery();
            
            if(rs.next()){
            
                numberlvl = rs.getInt("numberlevel");
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(RankDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return numberlvl;
    }

    /**
     *
     * @param username
     * @return
     * @throws DatabaseException
     */
    @Override
    public int recuperaPunteggioTotale(String username) throws DatabaseException {
       
        int totalpoint = 0;
        
        try {    
            PreparedStatement sNumberLvl = this.getConnection().prepareStatement("SELECT point FROM rank WHERE username = ?");
            
            ResultSet rs = sNumberLvl.executeQuery();
            
            if(rs.next()){
            
                totalpoint = rs.getInt("point");
               
            }
            
        } catch (SQLException ex) {
        
        //gestire eccezzioneeeeeee
        }
        return totalpoint;
        
    }

    /**
     *
     * @param match
     * @throws DatabaseException
     */
    @Override
    public void updatePoint(Match match) throws DatabaseException {
    
        try {
            
            PreparedStatement uPoint = this.getConnection().prepareStatement("UPDATE rank set point = ? where username = ? ");
            
            uPoint.setInt(1, match.getPunteggiototale());
        
            uPoint.setString(2, match.getUsername());
            
            uPoint.execute();
        } 
        catch (SQLException ex) {
        
            Logger.getLogger(RankDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    
    }

    /**
     *
     * @param match
     * @throws DatabaseException
     */
    @Override
    public void updateLevel(Match match)  throws DatabaseException{
     
        try {
            
            PreparedStatement uPoint = this.getConnection().prepareStatement("UPDATE rank set numberlevel = ? where username = ? ");
            
            uPoint.setInt(1, match.getLivello().getLevelnumber()+1);
        
            uPoint.setString(2, match.getUsername());
        
            uPoint.execute();
        } 
        catch (SQLException ex) {
        
            Logger.getLogger(RankDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }
    
    /**
     *
     * @return
     * @throws DatabaseException
     */
    public Map<String,Integer> getRanking() throws DatabaseException{
    
        Map<String,Integer> mappa = new LinkedHashMap<>();
        try {
            
            PreparedStatement sRank = this.getConnection().prepareStatement("SELECT * FROM RANK ORDER BY POINT desc");
            
            ResultSet result = sRank.executeQuery();
            
            while(result.next()){
            
                String username = result.getString("username");
                int point = result.getInt("point");
                mappa.put(username, point);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(RankDaoMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return mappa;
    }    
}
