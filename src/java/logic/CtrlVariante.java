/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.DataVariante;
import entity.Variante;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlVariante {
    DataVariante dv = new DataVariante();
    
    public ArrayList<Variante> obtenerVariantes() throws DonaCocaException, SQLException{
        return dv.obtenerVariantes();
    }        
    
    public Variante obtenerVariante(int idVariante) throws DonaCocaException, SQLException{
                          
        return dv.obtenerVariante(idVariante);
    }   
    
     public ArrayList<Variante> obtenerVariantes(int idTorta) throws DonaCocaException, SQLException{
              
        return dv.obtenerVariantes(idTorta);
    }
    public boolean existeVariante(String nombreVariante) throws DonaCocaException, SQLException{
        return dv.existeVariante(nombreVariante);
    }
    
    public void registrarVariante(Variante var)throws DonaCocaException, SQLException{
        dv.registrarVariante(var);
    }
    
    public void editarVariante(Variante var) throws DonaCocaException, SQLException{
        dv.editarVariante(var);
    }
      public ArrayList<Variante> obtenerVariantesDetalleTorta(int idDetalle,int idTorta) throws DonaCocaException, SQLException{
         return  dv.obtenerVariantesDetalleTorta(idDetalle, idTorta);
      }
}
