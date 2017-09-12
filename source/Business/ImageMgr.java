package Business;

import DAO.DaoFactory;
import MODEL.Image;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tom
 */
public class ImageMgr {
 
    
    private DaoFactory dbfactory ;
    /**
     * 
     * @throws Exception 
     */
    private ImageMgr()throws Exception{
    
        dbfactory = DaoFactory.getDaoFactory(1);
    
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public static ImageMgr getIstance()throws Exception{
    
        return new ImageMgr();
    
    }
    
    /**
     * 
     * @return
     * @throws SQLException 
     */
    public List<Image> listImage() throws SQLException{
    
        List<Image> listimage = dbfactory.getImmagineDao().loadAll();
        
        
        return listimage;
    }
    
    /**
     * 
     * @param image
     * @return
     * @throws SQLException 
     */
    public int insertimage(Image image) throws SQLException{
    
        return dbfactory.getImmagineDao().create(image);
    
    }
    /**
     * 
     * @param image
     * @throws SQLException 
     */
    public void removeimage(Image image) throws SQLException{
    
        dbfactory.getImmagineDao().remove(image);
    
    }
    /**
     * 
     * @param image
     * @throws SQLException 
     */
    public void updateimage(Image image) throws SQLException{
    
        dbfactory.getImmagineDao().store(image);
        
    }
    
}
