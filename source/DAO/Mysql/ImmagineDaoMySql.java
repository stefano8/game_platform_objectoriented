/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO.Mysql;

import DAO.ImmagineDao;
import DAO.Mysql.MySqlDaoFactory;
import EXCEPTION.DatabaseException;
import MODEL.Image;
import MODEL.IMPL.ImageImpl;
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
public class ImmagineDaoMySql implements ImmagineDao{

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public Connection getConnection() throws DatabaseException{
     
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
    public List loadAll()  throws DatabaseException {
    
         List <Image> listimage = new  ArrayList();
           
        try {
            
            PreparedStatement pstatement = this.getConnection().prepareStatement("SELECT * FROM image");
            
            ResultSet rs = pstatement.executeQuery();
            
            while(rs.next()){
                
                Image image = (Image) this.load(rs.getInt("id_image"));
                
                listimage.add(image);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ImmagineDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listimage ;
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object)  throws DatabaseException{
    
        try {
            Image image = (Image) object;
            
            
            PreparedStatement  insertimage = this.getConnection().prepareStatement("insert into image(path_image,title_image) value (?,?)",com.mysql.jdbc.Statement.RETURN_GENERATED_KEYS);
            insertimage.setString(1, image.getPath());
            insertimage.setString(2, image.getTitle());
            insertimage.execute();
            
            ResultSet rs = insertimage.getGeneratedKeys();
            if(rs.next())
            {
                return rs.getInt(1);
            }
            
            
            
            } catch (SQLException ex) {
            Logger.getLogger(ImmagineDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
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
    int id = (Integer )key ;
        Image immagine = new ImageImpl();
        try {
            PreparedStatement sImage=this.getConnection().prepareStatement("SELECT * FROM image where id_image=? ");
            sImage.setInt( 1 , id);
            ResultSet rs = sImage.executeQuery();
            
            if(rs.next()){
                immagine.setId_image(rs.getInt("id_image"));
                immagine.setPath((String)rs.getString("path_image"));
                immagine.setTitle ((String)rs.getString("title_image"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ImmagineDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return immagine ;
        
    
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object) throws DatabaseException {
    
        try {
            Image image = (Image) object ;
            PreparedStatement storeimage = this.getConnection().prepareStatement(" update image set path_image= ? , title_image = ? where id_image = ? ");
            storeimage.setString(1, image.getPath());
            storeimage.setString(2, image.getTitle());
            storeimage.setInt(3, image.getId_image());
            storeimage.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImmagineDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
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
            Image image = (Image) key ;
            
        
            PreparedStatement removeimage = this.getConnection().prepareStatement(" delete from image where id_image = ?");
        
            removeimage.setInt(1, image.getId_image());
            
            removeimage.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ImmagineDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    
    }
    
    /**
     *
     * @param titologioco
     * @return
     * @throws DatabaseException
     */
    @Override
    public Image getImageGame(String titologioco) throws DatabaseException {
        
        
        Image immagine = new ImageImpl();
        try {
            
            PreparedStatement sImageGame = this.getConnection().prepareStatement(" SELECT * FROM imagegame left join image on imagegame.id_image = image.id_image where title = ?");
            
            
            sImageGame.setString(1, titologioco);
            ResultSet rs = sImageGame.executeQuery();
            if(rs.next()){
            
                immagine.setId_image(rs.getInt("id_image"));
                immagine.setPath(rs.getString("path_image"));
                immagine.setTitle(rs.getString("title_image"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ImmagineDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return immagine;
        }
    
}
