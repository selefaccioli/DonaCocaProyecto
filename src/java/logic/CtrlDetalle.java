
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
    
}
