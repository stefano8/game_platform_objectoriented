/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EXCEPTION;

/**
 *
 * @author tom
 */
public class BusinessException extends Exception {
    
    /**
     *
     */
    public BusinessException()  {
        super();
    }
    /** Handles a Database Exception calling super class
     * @param msg String allowing you to send additional information about cause of Exception
     */
    public BusinessException(String msg)  {
        super(msg);
}
}
