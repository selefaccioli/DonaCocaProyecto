
package data;

import entity.Detalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                    d.setEligeUsuario(rs.getBoolean(3));
                    d.setMultiple(rs.getBoolean(4));
                    
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
                    d.setEligeUsuario(rs.getBoolean(3));
                    d.setMultiple(rs.getBoolean(4));
                    
                   
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
        String sql = "select DISTINCT d.`id_detalle` from detalle d inner join variante v on d.`id_detalle`= v.`id_detalle` \n" +
"inner join torta_variante tv on v.`id_variante` = tv.`id_variante`\n" +
"where id_torta = ?;";
        
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
        public boolean existeDetalle(String nombreDetalle) throws DonaCocaException{
        
        String sql= "select count(*) from detalle where nombre= ?;";
        Connection conec= null;
        int cantidad=0;
        
        try{
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, nombreDetalle);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                cantidad= rs.getInt(1);
                
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar el detalle en existeDetalle",e);
        }
        
        return cantidad >0;
    }
      public void registrarDetalle(Detalle det)throws DonaCocaException{
        PreparedStatement ps;
        String transac = "insert into detalle(nombre,elige_usuario) values (?,?);";
        try{
            Connection conec= conn.getConn();
            ps= conec.prepareStatement(transac, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, det.getNombre());
            ps.setBoolean(2, det.getEligeUsuario());
            
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                det.setId(id);
            }
            conec.close();
            
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar detalle ",e);
        }
        
    }
      
    public void editarDetalle(Detalle det) throws DonaCocaException{
        String sql="update detalle set nombre=? , elige_usuario=?  where id_detalle=?";
               
        try{
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, det.getNombre());
            ps.setBoolean(2, det.getEligeUsuario());
            ps.setInt(3, det.getId());
            
            ps.executeUpdate();
            conec.close();
          
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al modificar detalle",e);
        }  
    }
    
}
