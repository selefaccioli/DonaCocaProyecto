
package data;

import entity.Detalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DonaCocaException;


public class DataDetalle {
    Conexion conn= new Conexion();
    Connection conec= null;
    
    public ArrayList<Detalle> obtenerDetalles() throws DonaCocaException{
        ArrayList<Detalle> listaDetalles = new ArrayList<>();
        try
        {
            conec= conn.getConn();
            String sql = "select * from detalle;";
            PreparedStatement ps= conec.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
                
                while(rs.next())
                {
                    Detalle d= new Detalle();
                    
                    d.setId(rs.getInt(1));
                    d.setNombre(rs.getString(2));
                    d.setDetalle(rs.getString(3));
                    
                    listaDetalles.add(d);
                }
                conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener detalles",e);
        }
                   
        return listaDetalles;
    }        
    
    public Detalle obtenerDetalle(int idDetalle) throws DonaCocaException{
         Detalle d= new Detalle();
        try
        {
            conec= conn.getConn();
            String sql = "select * from detalle where id_detalle = ?;";
            PreparedStatement ps= conec.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            ResultSet rs= ps.executeQuery();
                
                if(rs.next())
                {
                   
                    
                    d.setId(rs.getInt(1));
                    d.setNombre(rs.getString(2));
                    d.setDetalle(rs.getString(3));
                    
                   
                }
                conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener detalles",e);
        }
                   
        return d;
    }   
    
     public ArrayList<Detalle> obtenerDetalles(int idTorta) throws DonaCocaException{
        ArrayList<Detalle> listaDetalles = new ArrayList<>();
        String sql = "select td.id_detalle from torta_detalle td inner join torta t on td.id_torta= t.id_torta where td.id_torta=? ";
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idTorta);
            ResultSet rs = ps.executeQuery();
                   
            while(rs.next())
            {
               Detalle d= obtenerDetalle(rs.getInt(1));           
                if(d!=null)
                {               
                    listaDetalles.add(d);
                }
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de detalles segun id torta",e);
        }        
        return listaDetalles;
    }
    
    
}
