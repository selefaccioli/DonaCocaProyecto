/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.DataCupon;
import entity.Cupon;
import java.util.ArrayList;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlCupon {
    DataCupon dc = new DataCupon();
    
    public ArrayList<Cupon> obtenerCupones() throws DonaCocaException{
       return dc.obtenerCupones();
    }
    
    public boolean existeCupon(String codCupon) throws DonaCocaException{      
        return dc.existeCupon(codCupon);
    }
    
    
     public Cupon obtenerCupon(String codCupon) throws DonaCocaException{
       return dc.obtenerCupon(codCupon);
   
}}
