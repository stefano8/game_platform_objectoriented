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
public interface UserGroupDao extends BaseDao{
    
    /**
     *
     * @return
     * @throws DatabaseException
     */
    public List loadAllOnlyUser() throws DatabaseException;
}
