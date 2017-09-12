/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import DAO.DaoFactory;
import MODEL.Match;
import java.util.Map;

/**
 *
 * @author tom
 */
public class RankMgr {
    
    
        private DaoFactory dbFactory = DaoFactory.getDaoFactory(1);  
    
    
    
    private RankMgr(){
    
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public static RankMgr getInstance()throws Exception{
    
    return new RankMgr();
    
    }
    
    /**
     * 
     * @param username
     * @return
     * @throws Exception 
     */
    public int initializeRank(String username) throws Exception{
    
        int key = dbFactory.getRankDao().create(username);
        
        return key;
    }
    
    
    /**
     * 
     * @param match
     * @throws Exception 
     */
    public void updatePoint(Match match)throws Exception{
    
        dbFactory.getRankDao().updatePoint(match);
    }
    
    /**
     * 
     * @return
     * @throws Exception 
     */
    public Map<String,Integer> getRank()throws Exception{
    
        Map<String,Integer> mappa = dbFactory.getRankDao().getRanking();
    
        return mappa;
    
    }
}
