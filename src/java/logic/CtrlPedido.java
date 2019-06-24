
package logic;

import data.DataPedido;
import entity.Pedido;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CtrlPedido {
    DataPedido dp = new DataPedido();
    
    public void cerrarPedido(Pedido p) throws DonaCocaException, SQLException{
        dp.cerrarPedido(p);
    }
    
    public void registrarEnvio(Pedido p) throws DonaCocaException, SQLException{
         dp.registrarEnvio(p);
    }
     
    public void registrarPedido(Pedido p) throws DonaCocaException, SQLException{  
        dp.registrarPedido(p);
    }
    
    public ArrayList<Pedido> obtenerPedidos(int idUsuario) throws DonaCocaException, SQLException{   
        return dp.obtenerPedidos(idUsuario);
    }
   
    public ArrayList<Pedido> obtenerPedidosEnviados () throws DonaCocaException, SQLException{    
        return dp.obtenerPedidosEnviados();
    }
    
    public ArrayList<Pedido> obtenerPedidosPendientes() throws DonaCocaException, SQLException{  
        return dp.obtenerPedidosPendientes();
    }
    
    public void registrarSena(Pedido p) throws DonaCocaException, SQLException{
        dp.registrarSena(p);
    }
    
    public ArrayList<Pedido> filtrosPedidos(Date fDesde, Date fHasta, String estado, String usuario) throws DonaCocaException, SQLException{
       return dp.filtrosPedidos(fDesde, fHasta, estado, usuario);
    }
    
}
