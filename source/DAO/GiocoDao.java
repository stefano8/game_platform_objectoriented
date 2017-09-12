package DAO;

import EXCEPTION.DatabaseException;
import java.sql.SQLException;

/**
 *
 * @author tom
 */
public interface GiocoDao extends BaseDao{

    /**
     *
     * @param titoloGioco
     * @throws SQLException
     */
    void setMediaVoto(String titoloGioco) throws SQLException;

    /**
     *
     * @param username
     * @param titologioco
     * @param vote
     * @throws DatabaseException
     */
    void voto(String username, String titologioco, int vote) throws SQLException;
    
}
