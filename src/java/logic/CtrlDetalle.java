
package logic;

import data.DataDetalle;
import entity.Detalle;
import java.util.ArrayList;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlDetalle {
    DataDetalle dd= new DataDetalle();
    
    public ArrayList<Detalle> obtenerDetalles() throws DonaCocaException{
        return dd.obtenerDetalles();
    }
    
    public ArrayList<Detalle> obtenerDetalles(int idTorta) throws DonaCocaException{
        return dd.obtenerDetalles(idTorta);
    }
    
     public Detalle obtenerDetalle(int idDetalle) throws DonaCocaException{      
        return dd.obtenerDetalle(idDetalle);
    }   
     
     public boolean existeDetalle(String nombreDetalle) throws DonaCocaException{
         
      return dd.existeDetalle(nombreDetalle);
     }

     public void registrarDetalle(Detalle det)throws DonaCocaException{
        dd.registrarDetalle(det);
     }
     
      public void editarDetalle(Detalle det) throws DonaCocaException{
          dd.editarDetalle(det);
      }
}

