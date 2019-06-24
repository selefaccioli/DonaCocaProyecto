
package logic;

import data.DataTorta;
import entity.Torta;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlTorta {
    
    DataTorta dt= new DataTorta();
    
    public void agregarTorta(Torta torta) throws DonaCocaException, SQLException{
        dt.agregarTorta(torta);
    }
    
    public void actualizarTorta(Torta torta) throws DonaCocaException, SQLException{ 
        dt.actualizarTorta(torta);
    }
    
   
    
    public ArrayList<Torta> obtenerTortas() throws DonaCocaException, SQLException{
         return dt.obtenerTortas();
    }
    
    public ArrayList<Torta> obtenerTortas(String nombre, int inferior, int cantidad) throws DonaCocaException, SQLException{
         return dt.obtenerTortas(nombre, inferior, cantidad);
    }
    
    public ArrayList<Torta> obtenerTortas(int inferior, int cantidad) throws DonaCocaException, SQLException{
        return dt.obtenerTortas(inferior, cantidad);
    }
    
    public Torta obtenerTorta(int idTorta) throws DonaCocaException, SQLException{
        return dt.obtenerTorta(idTorta);
    }
    
    public boolean existeTorta(String nombreTorta) throws DonaCocaException, SQLException{ 
         return dt.existeTorta(nombreTorta);
     }
    
    public ArrayList<Torta> obtenerVariante(int idVariante, int inferior, int cantidad) throws DonaCocaException, SQLException{
        return dt.obtenerVariante(idVariante);
    }
    
    public void eliminarTorta(Torta t) throws DonaCocaException, SQLException{
        dt.eliminarTorta(t);
    }
}
