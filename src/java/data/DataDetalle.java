
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
    
    
    public ArrayList<Detalle> obtenerDetalles() throws DonaCocaException, SQLException{
        ArrayList<Detalle> listaDetalles = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        try
        {
            
            String sql = "select * from detalle;";
             ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             rs= ps.executeQuery();
                
                while(rs.next())
                {
                    Detalle d= new Detalle();
                    
                    d.setId(rs.getInt(1));
                    d.setNombre(rs.getString(2));
                    d.setEligeUsuario(rs.getBoolean(3));
                    d.setMultiple(rs.getBoolean(4));
                    
                    listaDetalles.add(d);
                }
               // conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener detalles",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
                   
        return listaDetalles;
    }        
    
    public Detalle obtenerDetalle(int idDetalle) throws DonaCocaException, SQLException{
         Detalle d= new Detalle();
         PreparedStatement ps= null;
         ResultSet rs = null;
        try
        {
            
            String sql = "select * from detalle where id_detalle = ?;";
            ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idDetalle);
            rs= ps.executeQuery();
                
                if(rs.next())
                {
                   
                    
                    d.setId(rs.getInt(1));
                    d.setNombre(rs.getString(2));
                    d.setEligeUsuario(rs.getBoolean(3));
                    d.setMultiple(rs.getBoolean(4));
                    
                   
                }
               // conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener detalles",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
                   
        return d;
    }   
    
     public ArrayList<Detalle> obtenerDetalles(int idTorta) throws DonaCocaException, SQLException{
        ArrayList<Detalle> listaDetalles = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql = "select DISTINCT d.`id_detalle` from detalle d inner join variante v on d.`id_detalle`= v.`id_detalle` \n" +
"inner join torta_variante tv on v.`id_variante` = tv.`id_variante`\n" +
"where id_torta = ?;";
        
        try
        {
            
            ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idTorta);
            rs = ps.executeQuery();
                   
            while(rs.next())
            {
               Detalle d= obtenerDetalle(rs.getInt(1));           
                if(d!=null)
                {               
                    listaDetalles.add(d);
                }
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de detalles segun id torta",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }        
        return listaDetalles;
    }
        public boolean existeDetalle(String nombreDetalle) throws DonaCocaException, SQLException{
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql= "select count(*) from detalle where nombre= ?;";
        
        int cantidad=0;
        
        try{
            
             ps =  FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1, nombreDetalle);
            
             rs= ps.executeQuery();
            
            if(rs.next()){
                cantidad= rs.getInt(1);
                
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar el detalle en existeDetalle",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
        return cantidad >0;
    }
      public void registrarDetalle(Detalle det)throws DonaCocaException, SQLException{
        
        PreparedStatement ps= null;
        ResultSet rs = null;
        String transac = "insert into detalle(nombre,elige_usuario,multiple) values (?,?,?);";
        try{
            
            ps= FactoryConexion.getInstancia().getConn().prepareStatement(transac, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, det.getNombre());
            ps.setBoolean(2, det.getEligeUsuario());
            ps.setBoolean(3, det.getMultiple());
            
            ps.executeUpdate();
             rs= ps.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                det.setId(id);
            }
           // conec.close();
            
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar detalle ",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
    }
      
    public void editarDetalle(Detalle det) throws DonaCocaException, SQLException{
        String sql="update detalle set nombre=? , elige_usuario=?, multiple =?  where id_detalle=?";
        PreparedStatement ps= null;
        ResultSet rs = null;       
        try{
           
             ps =  FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1, det.getNombre());
            ps.setBoolean(2, det.getEligeUsuario());
            ps.setBoolean(3, det.getMultiple());
            ps.setInt(4, det.getId());
            
            ps.executeUpdate();
           // conec.close();
          
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al modificar detalle",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }  
    }
    
}
