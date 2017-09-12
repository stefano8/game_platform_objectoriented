/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import EXCEPTION.DatabaseException;
import java.sql.*;
import java.util.List;

/**
 *
 * @author tom
 */
public interface BaseDao {

    /**
     *
     * @return
     * @throws DatabaseException
     */
    public List loadAll() throws DatabaseException;
    
    /**
     *
     * @param object
     * @return
     * @throws DatabaseException
     */
    public int create(Object object)throws DatabaseException ;
    
    /**
     *
     * @param key
     * @return
     * @throws DatabaseException
     */
    public Object load(Object key) throws DatabaseException;
    
    /**
     *
     * @param object
     * @throws DatabaseException
     */
    public void store(Object object)throws DatabaseException ;
    
    /**
     *
     * @param key
     * @throws DatabaseException
     */
    public void remove(Object key) throws DatabaseException ;
    
    /**
     *
     * @return
     * @throws DatabaseException
     */
    public abstract Connection getConnection() throws DatabaseException ;
    
    /**
     *
     * @param rs
     * @param stmt
     * @param conn
     * @throws DatabaseException
     */
    public abstract void closeDbConnection(ResultSet rs,  Statement stmt,  Connection conn) throws DatabaseException ;
    
}
