/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.DataCupon;
import entity.Cupon;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlCupon {
    DataCupon dc = new DataCupon();
    
    public ArrayList<Cupon> obtenerCupones() throws DonaCocaException, SQLException{
       return dc.obtenerCupones();
    }
    
    public boolean existeCupon(String codCupon) throws DonaCocaException, SQLException{      
        return dc.existeCupon(codCupon);
    }
    
    
     public Cupon obtenerCupon(String codCupon) throws DonaCocaException, SQLException{
       return dc.obtenerCupon(codCupon);}
   
    public void agregarCupon(Cupon cupon) throws DonaCocaException, SQLException{
        dc.agregarCupon(cupon);
        }
        
      public void actualizarCupon(Cupon cupon) throws DonaCocaException, SQLException{      
       
         dc.actualizarCupon(cupon);
    }
}
