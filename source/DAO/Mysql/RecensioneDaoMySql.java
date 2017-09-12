/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Mysql;


import DAO.Mysql.MySqlDaoFactory;
import DAO.RecensioneDao;
import EXCEPTION.DatabaseException;
import MODEL.Review;
import MODEL.ReviewImpl;
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
public class RecensioneDaoMySql  implements RecensioneDao{

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
        
        
         List<Review> listaRec = new ArrayList();
        try {
           
            
            
            PreparedStatement sListRecensioniBO = this.getConnection().prepareStatement("SELECT * FROM review where approved = false order by date");
            
            ResultSet rsa = sListRecensioniBO.executeQuery();
            
            while (rsa.next()){
                
                Review rec = new ReviewImpl();
                

                rec = (Review)this.getRecensione(rsa.getInt("id_review"));
                listaRec.add(rec);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RecensioneDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
      
        
    return listaRec;
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object)  throws DatabaseException{
        
        int k = 0;
        
        try {
        
            Review review =  (Review) object;
            
            PreparedStatement iRecensione = iRecensione = this.getConnection().prepareStatement("INSERT INTO review ( titlereview,textreview,username,titlegame )value (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
            iRecensione.setString(1, review.getTitlereview());
            
            iRecensione.setString(2,review.getTextreview());
            
            iRecensione.setString(3,review.getUsername());
            
            iRecensione.setString(4,review.getTitlegame());
                
            iRecensione.execute();
            
            ResultSet rs = iRecensione.getGeneratedKeys();
            
            if(rs.next())
                k = rs.getInt(1);
        }
        catch (SQLException ex) {
        
            Logger.getLogger(RecensioneDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        
        }
    
        return k;
    }
    
    /**
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object key)  throws DatabaseException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object) throws DatabaseException {
    
        try {
            int id_rec = (Integer)object;
            
            PreparedStatement approvaRec= this.getConnection().prepareStatement ("UPDATE review SET approved = true WHERE id_review = ?");;
            
            approvaRec.setInt(1, id_rec);
            
            approvaRec.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RecensioneDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }
    
    /**
     *
     * @param id_recObj
     * @throws DatabaseException
     */
    @Override
    public void remove(Object id_recObj)  throws DatabaseException{
        int id_rec = (int)id_recObj;
        
        try {
            PreparedStatement declineRec = this.getConnection().prepareStatement("DELETE FROM review WHERE id_review = ?");
            
            declineRec.setInt(1, id_rec);
            declineRec.execute();
        } catch (SQLException ex) {
            Logger.getLogger(RecensioneDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
}
  
    /**
     *
     * @param id_recensione
     * @return
     * @throws DatabaseException
     */
    @Override
    public Review getRecensione(int id_recensione)  throws DatabaseException{
        
          Review rec = new ReviewImpl();
          
        try {
            PreparedStatement sRecensione = this.getConnection().prepareStatement("SELECT * FROM review where id_review = ? ");
            
            
            sRecensione.setInt(1, id_recensione);
            ResultSet rs = sRecensione.executeQuery();
            
            if(rs.next())
            {
                
                rec.setId_review(rs.getInt("id_review"));
                
                rec.setUsername(rs.getString("username"));
                
                rec.setTextreview(rs.getString("textreview"));
                
                rec.setTitlereview(rs.getString("titlereview"));
                
                rec.setDate(rs.getDate("date"));
                
                rec.setTitlegame(rs.getString("titlegame"));
                
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(RecensioneDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
         return rec ;
        }
   
    /**
     *
     * @param titologioco
     * @return
     * @throws DatabaseException
     */
    @Override//impostato a false momentaneament in fase di creazione BO rimettere a true
    public List<Review> listaRecensioni(String titologioco)  throws DatabaseException{
    List<Review> listaRec = new ArrayList();
            
        try {
            
            PreparedStatement  sListRecensione = this.getConnection().prepareStatement("SELECT * FROM review where titlegame = ? and approved = true order by date");
            
            sListRecensione.setString(1, titologioco);
            
            ResultSet rsa = sListRecensione.executeQuery();
            
            while (rsa.next()){
                
                Review rec = this.getRecensione(rsa.getInt("id_review"));
                
                listaRec.add(rec);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(RecensioneDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaRec;
    }
    
}
