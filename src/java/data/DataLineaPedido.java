
package data;

import entity.LineaPedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DonaCocaException;

public class DataLineaPedido {
    
   
    
    public ArrayList<LineaPedido> obtenerLineasPedido (int idPedido) throws DonaCocaException, SQLException{
        String sql = "select * from linea_pedido1 where id_pedido =? ;";
        Connection con = null;      
        ArrayList<LineaPedido> lineas = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        try
        {
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idPedido);
             rs = ps.executeQuery();
              
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
            //conec.close();
        }  
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lineas de un pedido",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
        return lineas;         
    }
    
      public void registrarLineas(ArrayList<LineaPedido> lineas, int idPedido) throws DonaCocaException, SQLException{ 
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        for(LineaPedido lp: lineas ) 
        {   
            String sql = "insert into linea_pedido1(id_pedido,cantidad,id_torta,subtotal) values (?,?,?,?);";
           
            try
            {
              
               ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
               ps.setInt(1, idPedido);
               ps.setInt(2, lp.getCantidad());
               ps.setInt(3, lp.getTorta().getId());
               ps.setDouble(4, lp.getSubtotal());
               ps.executeUpdate();
               
               //conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al registrar linea de pedido",e);
            } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }             
        }      
    }      
    
       
      
      
}
