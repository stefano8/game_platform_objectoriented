/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.DaoFactory;
import MODEL.Review;
import java.util.List;

/**
 *
 * @author tom
 */
public class ReviewMgr {
    
    
    private DaoFactory dbfactory = DaoFactory.getDaoFactory(1);
    
    
    private ReviewMgr(){
    
        
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public static ReviewMgr getIstance()throws Exception{
    
        return new ReviewMgr();
    
    }

    /**
     * 
     * @param game
     * @return
     * @throws Exception 
     */
    public List getListReview(String game)throws Exception{
    
        List<Review> listrev = dbfactory.getRecensioneDao().listaRecensioni(game);
        
        return listrev;
    }
    
    /**
     *
     * @param review
     * @return
     * @throws Exception
     */
    public int insertReview(Review review)throws Exception{
    
        int id = dbfactory.getRecensioneDao().create(review);
    
        return id;
    
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public List getListReviewBO() throws Exception{
    
     
        List<Review> listrev = dbfactory.getRecensioneDao().loadAll();
     
        return listrev;
    
    }
    /**
     * 
     * @param id
     * @throws Exception 
     */
    public void removeReview(int id) throws Exception{
    
        dbfactory.getRecensioneDao().remove(id);
    
    }
    
    /**
     * 
     * @param id
     * @throws Exception 
     */
    public void acceptReview(int id) throws Exception{
    
        dbfactory.getRecensioneDao().store(id);
    
    }
    
}
