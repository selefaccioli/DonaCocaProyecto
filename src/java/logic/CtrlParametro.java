
package logic;

import data.DataParametros;
import entity.Parametro;
import java.sql.SQLException;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlParametro {
    DataParametros dp = new DataParametros();
    
    public Parametro obtenerParametros() throws DonaCocaException, SQLException{ 
        return dp.obtenerParametros();
    }
    
}
