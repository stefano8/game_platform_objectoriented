/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import EXCEPTION.DatabaseException;
import MODEL.LevelI;
import MODEL.Match;
import MODEL.Trophy;
import java.sql.Date;
import java.util.Map;


/**
 *
 * @author tom
 */
public interface TrofeoDao extends BaseDao {

    /**
     *
     * @param match
     * @param level
     * @throws Exception
     */
    void assignTrofeo(Match match,LevelI level)  throws Exception;

    /**
     *
     * @param username
     * @return
     * @throws DatabaseException
     */
    Map<Integer,Map<Date,Trophy>> trofeiVinti(String username) throws DatabaseException;
    
}
