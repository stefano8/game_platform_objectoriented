/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import EXCEPTION.DatabaseException;
import java.util.List;

/**
 *
 * @author tom
 */
public interface PermissionHandlerDao extends BaseDao{
    
    /**
     *
     * @param id_user
     * @throws Exception
     */
    void insertIntoGroup(int id_user) throws Exception;
    
    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    public List listapermessi(String username)throws Exception;
    
   
}
