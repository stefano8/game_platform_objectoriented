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
public interface ServiceDao extends BaseDao{
 
    /**
     *
     * @param ser
     * @param group
     * @throws DatabaseException
     */
    public void addServiceToGroup(int ser, int group) throws DatabaseException;
}
