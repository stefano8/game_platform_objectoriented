/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import EXCEPTION.DatabaseException;
import MODEL.Image;

/**
 *
 * @author tom
 */
 public interface ImmagineDao extends BaseDao {
   
    /**
     *
     * @param titologioco
     * @return
     * @throws DatabaseException
     */
    public Image getImageGame(String titologioco) throws DatabaseException;
     
}
