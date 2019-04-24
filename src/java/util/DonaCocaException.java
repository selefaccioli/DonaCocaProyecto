/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

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
            
}
