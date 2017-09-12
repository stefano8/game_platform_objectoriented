/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import MODEL.Review;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author tom
 */
public interface  RecensioneDao extends BaseDao {

    /**
     *
     * @param id_recensione
     * @return
     * @throws Exception
     */
    Review getRecensione(int id_recensione)  throws Exception;
    
    /**
     *
     * @param titologioco
     * @return
     * @throws Exception
     */
    List<Review> listaRecensioni(String titologioco)  throws Exception;

   

    
   
}
