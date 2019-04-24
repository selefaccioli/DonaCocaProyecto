
package logic;

import data.DataTorta;
import entity.Torta;
import java.util.ArrayList;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlTorta {
    
    DataTorta dt= new DataTorta();
    
    public void agregarTorta(Torta torta) throws DonaCocaException{
        dt.agregarTorta(torta);
    }
    
    public void actualizarTorta(Torta torta) throws DonaCocaException{ 
        dt.actualizarTorta(torta);
    }
    
    public byte[] buscarImagen(int id) throws DonaCocaException{
        return dt.buscarImagen(id);
    }
    
    public ArrayList<Torta> obtenerTortas() throws DonaCocaException{
         return dt.obtenerTortas();
    }
    
    public ArrayList<Torta> obtenerTortas(String nombre, int inferior, int cantidad) throws DonaCocaException{
         return dt.obtenerTortas(nombre, inferior, cantidad);
    }
    
    public ArrayList<Torta> obtenerTortas(int inferior, int cantidad) throws DonaCocaException{
        return dt.obtenerTortas(inferior, cantidad);
    }
    
    public Torta obtenerTorta(int idTorta) throws DonaCocaException{
        return dt.obtenerTorta(idTorta);
    }
    
    public boolean existeTorta(String nombreTorta) throws DonaCocaException{ 
         return dt.existeTorta(nombreTorta);
     }
    
    public ArrayList<Torta> obtenerDetalle(int idDetalle, int inferior, int cantidad) throws DonaCocaException{
        return dt.obtenerDetalle(idDetalle, inferior, cantidad);
    }
    
    public void eliminarTorta(Torta t) throws DonaCocaException{
        dt.eliminarTorta(t);
    }
}
