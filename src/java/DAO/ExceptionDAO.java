/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author mortein
 */
public class ExceptionDAO extends Exception {

    public ExceptionDAO(Throwable cause) {
        super(cause);
    }

    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDAO(String message) {
        super(message);
    }

    public ExceptionDAO() {
        super();
    }

}
