/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import EXCEPTION.DatabaseException;

/**
 *
 * @author tom
 */
public interface UserDao extends BaseDao{
    
    /**
     *
     * @param username
     * @param password
     * @return
     * @throws DatabaseException
     */
    public int checkLogin(String username, String password)  throws DatabaseException;
}
