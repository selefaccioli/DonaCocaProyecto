
package data;

import entity.LineaPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DonaCocaException;

public class DataLineaPedido {
    
    Conexion conn= new Conexion();
    Connection conec= null;
    
    public ArrayList<LineaPedido> obtenerLineasPedido (int idPedido) throws DonaCocaException{
        String sql = "select * from linea_pedido1 where id_pedido =? ;";
        Connection con = null;      
        ArrayList<LineaPedido> lineas = new ArrayList<>();
        
        try
        {
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idPedido);
            ResultSet rs = ps.executeQuery();
              
            while(rs.next())
            {
                LineaPedido lp = new LineaPedido();
                
                lp.setIdPedido(rs.getInt(1));
                lp.setCantidad(rs.getInt(3));
                lp.setTorta(new DataTorta().obtenerTorta(rs.getInt(2)));
                lp.setSubtotal(rs.getDouble(4));
                DataLineaPedidoVariante dlpv = new DataLineaPedidoVariante();
                lp.setVariantes(dlpv.obtenerVariantesLp(lp));
                lineas.add(lp);
                
            }    
            conec.close();
        }  
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lineas de un pedido",e);
        }
        
        return lineas;         
    }
    
      public void registrarLineas(ArrayList<LineaPedido> lineas, int idPedido) throws DonaCocaException{ 
    
        
        for(LineaPedido lp: lineas ) 
        {   
            String sql = "insert into linea_pedido1(id_pedido,cantidad,id_torta,subtotal) values (?,?,?,?);";
           
            try
            {
               conec= conn.getConn();
               PreparedStatement ps = conec.prepareStatement(sql);
               ps.setInt(1, idPedido);
               ps.setInt(2, lp.getCantidad());
               ps.setInt(3, lp.getTorta().getId());
               ps.setDouble(4, lp.getSubtotal());
               ps.executeUpdate();
               
               conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al registrar linea de pedido",e);
            }             
        }      
    }      
    
       
      
      
}
