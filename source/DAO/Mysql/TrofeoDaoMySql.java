package DAO.Mysql;

import DAO.DaoFactory;
import DAO.ImmagineDao;
import DAO.Mysql.MySqlDaoFactory;
import DAO.TrofeoDao;
import EXCEPTION.DatabaseException;
import MODEL.Image;
import MODEL.Match;
import MODEL.Trophy;
import MODEL.TrophyImpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import MODEL.LevelI;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author tom
 */
public class TrofeoDaoMySql implements TrofeoDao{

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
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object) throws DatabaseException {
    
        try {
            Trophy trofeo = (Trophy) object ;
            
            PreparedStatement createTrofeo = this.getConnection().prepareStatement("INSERT INTO TROPHY(name,id_imagetrophy) VALUE (?,?)",Statement.RETURN_GENERATED_KEYS);
            
            createTrofeo.setString(1, trofeo.getName());
            createTrofeo.setInt(2, trofeo.getImage().getId_image());
            
            createTrofeo.execute();
            
            ResultSet rs = createTrofeo.getGeneratedKeys();
            if(rs.next()){
                
                return rs.getInt(1);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TrofeoDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }

    /**
     *
     * @return
     * @throws DatabaseException
     */
    @Override
    public List loadAll() throws DatabaseException {
        List <Trophy> listatrofei = new ArrayList();
        try {
            PreparedStatement       sListTrofei = this.getConnection().prepareStatement("SELECT * FROM trophy ");
            
            
            ResultSet listT = sListTrofei.executeQuery();
            while(listT.next()){
                Trophy trofeo = (Trophy)this.load(listT.getInt("id_trophy"));
                listatrofei.add(trofeo);
            }
            
        } catch (SQLException ex) {
        
        } catch (Exception ex) {
            Logger.getLogger(TrofeoDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listatrofei ;
    }

    /**
     *
     * @param idObj
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object idObj) throws DatabaseException {
        int id =(int)idObj;
     Trophy trofeo = new TrophyImpl();
        try {
            PreparedStatement sTrofeo = this.getConnection()
                    .prepareStatement("SELECT * FROM trophy where id_trophy = ? ");
         
           
            
            sTrofeo.setInt(1, id);
            ResultSet srs = sTrofeo.executeQuery();
            
            if(srs.next()){
                
                ImmagineDao im = DaoFactory.getDaoFactory(1).getImmagineDao();
                Image immagine = (Image) im.load(srs.getInt("id_imagetrophy"));
                trofeo.setImage(immagine);
                trofeo.setId_trophy(srs.getInt("id_trophy"));
                trofeo.setName((String)srs.getString("name"));
                
            }
            
        } catch (SQLException ex) {
           
        }
        return trofeo;  
    }

    /**
     *
     * @param object
     * @throws DatabaseException
     */
    @Override
    public void store(Object object) throws DatabaseException {
    
        try {
            Trophy trofeo = (Trophy) object ;
            
            PreparedStatement storeTrofeo = this.getConnection().prepareStatement("UPDATE TROPHY SET name = ?, id_imagetrophy = ? WHERE id_trophy = ?");
            
            storeTrofeo.setString(1, trofeo.getName());
            storeTrofeo.setInt(2, trofeo.getImage().getId_image());
            storeTrofeo.setInt(3, trofeo.getId_trophy());
            
            storeTrofeo.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TrofeoDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param idobj
     * @throws DatabaseException
     */
    @Override
    public void remove(Object idobj) throws DatabaseException {
        Trophy trofeo = (Trophy)  idobj ;
        try {
            PreparedStatement dTrofeo = this.getConnection()
                    .prepareStatement("DELETE FROM trophy WHERE id_trophy = ?");
         
            dTrofeo.setInt(1, trofeo.getId_trophy());
            dTrofeo.execute();
        } catch (SQLException ex) {
            
        }
         }
    
    /**
     *
     * @param match
     * @param level
     * @throws DatabaseException
     */
    @Override
    public void assignTrofeo(Match match,LevelI level)  throws DatabaseException{
        try {
            PreparedStatement assegnatrofeo = this.getConnection()
                    
                    .prepareStatement("insert into usertrophy (username,id_trophy,id_gameplay,numberlevel) value (?,?,?,?)");
            
            assegnatrofeo.setString(1, match.getUsername());
            
            assegnatrofeo.setInt(2, level.getTrophy().getId_trophy());
            
            assegnatrofeo.setInt(3,match.getId_match());
            
            assegnatrofeo.setInt(4, level.getLevelnumber());
            
            assegnatrofeo.execute();
        } catch (SQLException ex) {
            Logger.getLogger(TrofeoDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
   // @Override

    /**
     *
     * @param username
     * @return
     * @throws DatabaseException
     */
        public Map<Integer,Map<Date,Trophy>>  trofeiVinti(String username )  throws DatabaseException{
     
            Map<Integer,Map<Date,Trophy>> mappa= new HashMap();
        try {
            
            PreparedStatement       sTrofeiVintiPerUtente = this.getConnection()
        
                    .prepareStatement("select usertrophy.* from usertrophy left join trophy on usertrophy.id_trophy = trophy.id_trophy where usertrophy.username = ?");
            
            
            
            sTrofeiVintiPerUtente.setString(1, username);

            ResultSet rsList;
        
            rsList = sTrofeiVintiPerUtente.executeQuery();
            
            
            while(rsList.next()){
                
                Map<Date,Trophy> mappainterna = new HashMap();
            
                int numerolvl = rsList.getInt("numberlevel");
                
                Trophy trofeo = (Trophy) this.load(rsList.getInt("id_trophy"));

                Date data = rsList.getDate("dataconquistatrofeo");
                
                mappainterna.put(data, trofeo);
               
                
            
                mappa.put(numerolvl, mappainterna);
        
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TrofeoDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println(mappa.size()+"dimensione mappa");
        return mappa;
        }

         
        
        
        
        
    
 
}
