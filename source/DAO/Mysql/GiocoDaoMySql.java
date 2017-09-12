package DAO.Mysql;


import DAO.DaoFactory;
import DAO.GiocoDao;
import DAO.ImmagineDao;
import DAO.Mysql.MySqlDaoFactory;
import EXCEPTION.DatabaseException;
import MODEL.Game;
import MODEL.IMPL.GameImpl;
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
public class GiocoDaoMySql implements GiocoDao{

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
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAll() throws DatabaseException{
        
        
            
            List<Game> listgame = new ArrayList();
            
            ResultSet rs ;
            
            PreparedStatement    sListaGioco;
            try {
                sListaGioco = this.getConnection().prepareStatement("SELECT game.*,image.* FROM game left join imagegame on game.title = imagegame.title left join image on imagegame.id_image = image.id_image ");
            
           
                rs = sListaGioco.executeQuery();
            
                while (rs.next()){

                    Game gioco = new GameImpl();
                     gioco.setTitle(rs.getString("title"));
            
                gioco.setType(rs.getString("type"));
                
                gioco.setAverage_vote(rs.getFloat("Average_vote"));
                
                gioco.setDescription(rs.getString("description"));
                
                gioco.setValue(rs.getInt("value"));
                
                gioco.setTemplate(rs.getString("template"));
               
               
                Image immaginegioco = new ImageImpl();
                immaginegioco.setPath(rs.getString("path_image"));
                
                gioco.setImage(immaginegioco);
                    listgame.add(gioco);

                }
            } catch (SQLException ex) {
            
                throw new DatabaseException("errore load game");
            }
            
            return listgame;
       
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object) throws DatabaseException {
    
        int id = 0 ;
        try {
            Game game = (Game)object;
            
            
            PreparedStatement stat = this.getConnection().prepareStatement("insert into game (title,type,description,value,template) value (?,?,?,?,?)");
            stat.setString(1, game.getTitle());
            stat.setString(2, game.getType());
            stat.setString(3, game.getDescription());
            stat.setInt(4, game.getValue());
            stat.setString(5, game.getTemplate());
            
            if(stat.executeUpdate()==1){
            
                return 1 ;
                
            }
        
        
        } catch (SQLException ex) {
            Logger.getLogger(GiocoDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return 0 ;
    }

    /**
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object key) throws  DatabaseException {
        
        Game gioco = new GameImpl();
        try {
            String titolo = (String)key;
            
            
        
            ResultSet rs= null;
        
            PreparedStatement    sGioco = this.getConnection().prepareStatement("SELECT * FROM game where title = ? ");
        
            sGioco.setString(1, titolo);
        
            rs = sGioco.executeQuery();
            
            if(rs.next()){

                gioco.setTitle(titolo);
            
                gioco.setType(rs.getString("type"));
                
                gioco.setAverage_vote(rs.getFloat("Average_vote"));
                
                gioco.setDescription(rs.getString("description"));
                
                gioco.setValue(rs.getInt("value"));
                
                gioco.setTemplate(rs.getString("template"));
               
                ImmagineDao a = DaoFactory.getDaoFactory(1).getImmagineDao();
                
                Image immaginegioco = a.getImageGame(titolo);
                
                gioco.setImage(immaginegioco);
       
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(GiocoDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
     return gioco ;
    }

    /**
     *
     * @param object
     */
    @Override
    public void store(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param key
     */
    @Override
    public void remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     *
     * @param username
     * @param titologioco
     * @param vote
     * @throws SQLException
     */
    @Override
    public void voto (String username,String titologioco, int vote) throws SQLException{
    
        PreparedStatement    uVoto       = this.getConnection().prepareStatement(" update vote set vote = ? where username = ? and titlevote = ?");
        
        PreparedStatement    sVoto       = this.getConnection().prepareStatement(" SELECT * FROM vote WHERE username = ? AND titlevote = ?");
        
        PreparedStatement    iVoto       = this.getConnection().prepareStatement("INSERT INTO vote VALUE (?,?,?)");
        
        ResultSet rs = null;
        
        sVoto.setString(1, username);
        
        sVoto.setString(2, titologioco);
        
        rs = sVoto.executeQuery();
        
        if(rs.next())
        {
            uVoto.setInt(1, vote);
        
            uVoto.setString(2, username);
            
            uVoto.setString(3, titologioco);
            
            uVoto.executeUpdate();
            
        }
        else
        {
            iVoto.setString(1, username);
            
            iVoto.setString(2, titologioco);
            
            iVoto.setInt(3, vote);
            
            iVoto.executeUpdate();
            
        }
        
        this.setMediaVoto(titologioco);
    
    }

    /**
     *
     * @param titoloGioco
     * @throws SQLException
     */
    @Override
    public void setMediaVoto(String titoloGioco) throws SQLException
    {
        PreparedStatement    uMediaVoto = this.getConnection().prepareStatement("update game set Average_vote = ? where title = ? ");
       
        PreparedStatement    sVotoPerTitolo = this.getConnection().prepareStatement("select * from vote where titlevote = ?");
    
        ResultSet rs = null;
          //seleziona tutti i voti per quel gioco
    
        sVotoPerTitolo.setString(1, titoloGioco);
        
        rs = sVotoPerTitolo.executeQuery();
    
    
        float mediavoto =0;
    
        int count =0;
     // update mediavoto perquel gioco
        while(rs.next()){
     
            count++;
    
            mediavoto+= rs.getInt("vote");
     
        }
     
        mediavoto= mediavoto/count;
    
        uMediaVoto.setFloat(1, mediavoto);
     
        uMediaVoto.setString(2, titoloGioco);
     
        uMediaVoto.execute();
    
    }
    
}