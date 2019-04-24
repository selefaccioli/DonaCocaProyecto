
package logic;

import data.DataParametros;
import entity.Parametro;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlParametro {
    DataParametros dp = new DataParametros();
    
    public Parametro obtenerParametros() throws DonaCocaException{ 
        return dp.obtenerParametros();
    }
    
}
