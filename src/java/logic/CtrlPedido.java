
package logic;

import data.DataPedido;
import entity.Pedido;
import java.util.ArrayList;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlPedido {
    DataPedido dp = new DataPedido();
    
    public void cerrarPedido(Pedido p) throws DonaCocaException{
        dp.cerrarPedido(p);
    }
    
    public void registrarEnvio(Pedido p) throws DonaCocaException{
         dp.registrarEnvio(p);
    }
     
    public void registrarPedido(Pedido p) throws DonaCocaException{  
        dp.registrarPedido(p);
    }
    
    public ArrayList<Pedido> obtenerPedidos(int idUsuario) throws DonaCocaException{   
        return dp.obtenerPedidos(idUsuario);
    }
   
    public ArrayList<Pedido> obtenerPedidosEnviados () throws DonaCocaException{    
        return dp.obtenerPedidosEnviados();
    }
    
    public ArrayList<Pedido> obtenerPedidosPendientes() throws DonaCocaException{  
        return dp.obtenerPedidosPendientes();
    }
    
    
}
