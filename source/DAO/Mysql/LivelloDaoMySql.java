package DAO.Mysql;


import DAO.DaoFactory;
import DAO.LivelloDao;
import DAO.Mysql.MySqlDaoFactory;
import DAO.TrofeoDao;
import EXCEPTION.DatabaseException;
import MODEL.LevelImpl;
import MODEL.Trophy;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import MODEL.LevelI;
import java.util.logging.Level;

/**
 *
 * @author tom
 */
public class LivelloDaoMySql implements LivelloDao {

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
    public List loadAll()  throws DatabaseException {
        List<LevelI> listlvl = new ArrayList();
        try {
            PreparedStatement   sListLevel = this.getConnection().prepareStatement("SELECT * FROM level");
            
            
            ResultSet list = sListLevel.executeQuery();
            while(list.next()){
                LevelI lvl= (LevelI)this.load(list.getInt("numberlevel"));
                listlvl.add(lvl);
                
            }
          
        } catch (SQLException ex) {
            Logger.getLogger(LivelloDaoMySql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      return listlvl;
    }

    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    @Override
    public int create(Object object)  throws  DatabaseException{
     
        try {
            LevelI level = (LevelI) object ;
            
            PreparedStatement insertlevel = this.getConnection().prepareStatement("INSERT INTO LEVEL (numberlevel,pointNextlevel,id_trophy) VALUE (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            
            insertlevel.setInt(1, level.getLevelnumber());
            
            insertlevel.setInt(2, level.getPointnextlevel());
            
            insertlevel.setInt(3, level.getTrophy().getId_trophy());
            
            insertlevel.execute();
            
            ResultSet rs = insertlevel.getGeneratedKeys();
            
            if(rs.next()){
                
                return rs.getInt(1);
                
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(LivelloDaoMySql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0 ;
    }

    /**
     *
     * @param idobj
     * @return
     * @throws DatabaseException
     */
    @Override
    public Object load(Object idobj) throws DatabaseException {
    int id = (int) idobj;
    
        LevelI lvl = new LevelImpl();
        try {
            PreparedStatement   sLivello = this.getConnection().prepareStatement("SELECT * FROM level where numberlevel = ? ");
            
            sLivello.setInt(1, id);
            ResultSet lvlrs = sLivello.executeQuery();
            System.out.println(sLivello +" query");
            if(lvlrs.next()){
                lvl.setId_level(lvlrs.getInt("id_level"));
                lvl.setLevelnumber(lvlrs.getInt("numberlevel"));
                lvl.setPointnextlevel(lvlrs.getInt("pointNextlevel"));
                TrofeoDao trofeodao = DaoFactory.getDaoFactory(1).getTrofeoDao();
                Trophy trofeo = (Trophy)trofeodao.load(lvlrs.getInt("id_trophy"));
                lvl.setTrophy(trofeo);
                
                
                
               
                return lvl ;
            }
            
           
        } catch (SQLException ex) {
            Logger.getLogger(LivelloDaoMySql.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } return lvl;
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

    
 

}
