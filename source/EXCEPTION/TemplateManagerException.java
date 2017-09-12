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
public class TemplateManagerException extends Exception {

    /**
     *
     * @param message
     */
    public TemplateManagerException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public TemplateManagerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause
     */
    public TemplateManagerException(Throwable cause) {
        super(cause);
    }

}
