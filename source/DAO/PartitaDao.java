/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;

/**
 *
 * @author tom
 */
public interface PartitaDao extends BaseDao{
    
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public int currentLevel(String username) throws Exception;
    
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public int sumTotalPoint(String username) throws Exception;

    /**
     *
     * @param username
     * @param numberlvl
     * @return
     * @throws Exception
     */
    public int sumTotalPointForLevel(String username,int numberlvl)  throws Exception;
    
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public List getlistMatch(String username) throws Exception;
}
