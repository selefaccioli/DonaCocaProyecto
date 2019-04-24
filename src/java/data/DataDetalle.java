
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
    
}
