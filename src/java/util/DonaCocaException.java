/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;

/**
 *
 * @author selef
 */
public class DonaCocaException extends Exception {
    
    public DonaCocaException(){
        super();
    }
    
    public DonaCocaException(String msj, Throwable c){
        super(msj, c);
    }
    
    public DonaCocaException(Throwable c){
        super(c);
    }

    public DonaCocaException(SQLException e, String error_al_conectar_a_la_base_de_datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
}
