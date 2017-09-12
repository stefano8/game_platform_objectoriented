/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import MODEL.Match;
import java.util.Map;

/**
 *
 * @author tom
 */
public interface RankDao extends BaseDao {
 
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public int recuperaNumeroLivello(String username)  throws Exception;
    
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public int recuperaPunteggioTotale(String username) throws Exception;
    
    /**
     *
     * @param match
     * @throws Exception
     */
    public void updatePoint(Match match) throws Exception;
    
    /**
     *
     * @param match
     * @throws Exception
     */
    public void updateLevel(Match match) throws Exception;
    
    /**
     *
     * @return
     * @throws Exception
     */
    public Map<String,Integer> getRanking() throws Exception;
}
