
package data;

import entity.Parametro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DonaCocaException;

public class DataParametros {
    Conexion conn= new Conexion();
    Connection conec= null;
    
     public Parametro obtenerParametros() throws DonaCocaException{ 
        Parametro par= null;
        String sql = "select * from parametros where fecha_actualizacion= "+
                     "(select max(fecha_actualizacion) from parametros where fecha_actualizacion < CURRENT_DATE);"; 
                        
        try
        {
            conec= conn.getConn();
            PreparedStatement ps= conec.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            
            if(rs.next())
            {  
                par = new Parametro();
                
                par.setRazonSocial(rs.getString(1));
                par.setCuit(rs.getString(2));
                par.setDireccion(rs.getString(3));
                par.setTelefono(rs.getString(4));
                par.setFechaActualizacion(rs.getDate(5));
            }
            
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener parametros",e);
        }
        
        return par;
    } 
}
