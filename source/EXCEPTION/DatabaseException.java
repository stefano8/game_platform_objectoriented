/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXCEPTION;

import java.sql.SQLException;



/**
 *
 * @author tom
 */
public class DatabaseException extends SQLException {
    
    /**
     *
     */
    public DatabaseException()  {
        super();
    }
    /** Handles a Database Exception calling super class
     * @param msg String allowing you to send additional information about cause of Exception
     */
    public DatabaseException(String msg)  {
        super(msg);
}
    
    
}
