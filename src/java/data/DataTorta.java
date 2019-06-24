package data;

import entity.Detalle;
import entity.Torta;
import entity.Usuario;
import entity.Variante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.CtrlDetalle;
import logic.CtrlVariante;
import util.DonaCocaException;



public class DataTorta {
   
    
    
    public void agregarTorta(Torta torta) throws DonaCocaException, SQLException{
       
        PreparedStatement ps= null;
        ResultSet rs = null;
        String transac = "insert into torta(nombre,precio,activo,ruta_img,eliminado) values (?,?,?,?,?);";
        try{
          
           ps= FactoryConexion.getInstancia().getConn().prepareStatement(transac, PreparedStatement.RETURN_GENERATED_KEYS);
           ps.setString(1, torta.getNombre());
           ps.setDouble(2, torta.getPrecio());
           ps.setBoolean(3, true);
           ps.setString(4, torta.getRutaImg());
           ps.setBoolean(5, false);
           
           ps.executeUpdate();
           rs= ps.getGeneratedKeys();
           
           if(rs.next())
            {
                int id = rs.getInt(1);
                torta.setId(id);
            }
            new DataTortaVariante().agregarTortaVariante(torta);
            if(torta.getRutasImg() != null){
              new DataTortaImagen().agregarTortaImagen(torta);  
            }
            
            //conec.close();
           
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al agregar torta ",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
    }
    
     public void actualizarTorta(Torta torta) throws DonaCocaException, SQLException{      
         PreparedStatement ps= null;
       
        if(torta.getRutaImg() != null){
            String sql="update torta set nombre=? , precio=?,  ruta_img=?, activo =? where id_torta=?";  
            try
            {
               
                 ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
                ps.setString(1, torta.getNombre());
                ps.setDouble(2, torta.getPrecio());
                 ps.setString(3, torta.getRutaImg());
                 ps.setBoolean(4, torta.isActivo());
                ps.setInt(5, torta.getId());
               
                
                new DataTortaVariante().actualizarTortaVariante(torta);
                ps.executeUpdate();
           
                //conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta",e);
            } finally{
            
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        }
        else{
            String sql="update torta set nombre=? , precio=?, activo= ?  where id_torta=?";  
            try
            {
                
                 ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
                ps.setString(1, torta.getNombre());
                ps.setDouble(2, torta.getPrecio());
                ps.setBoolean(3, torta.isActivo());
                ps.setInt(4, torta.getId());
               
                
                new DataTortaVariante().actualizarTortaVariante(torta);
                ps.executeUpdate();
           
                //conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta",e);
            } finally{
            
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
            
            
    }
     
     }
    
     
     public ArrayList<Torta> obtenerTortas() throws DonaCocaException, SQLException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql = "select * from torta where eliminado =0 order by torta.`id_torta` desc;;";
        try
        {
            
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Torta t = new Torta();
                
                t.setId(rs.getInt(1));
                t.setPrecio(rs.getFloat(2));
                t.setNombre(rs.getString(3));
                t.setActivo(rs.getBoolean(4));
                t.setRutaImg(rs.getString(5));
                
                ArrayList<Variante> variantes = new CtrlVariante().obtenerVariantes(t.getId());
                t.setVariantes(variantes);
                
                
                
                listaTortas.add(t);

            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener tortas all",e);
        } finally{
             if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
         
        return listaTortas;
    }
     
      public ArrayList<Torta> obtenerTortas(String nombre, int inferior, int cantidad) throws DonaCocaException, SQLException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql = "select * from torta where nombre like '%"+nombre+"%' limit ?,?;";
        try
        {
            
             ps =  FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, inferior);
            ps.setInt(2, cantidad);

             rs = ps.executeQuery();
            while(rs.next())
            {
                Torta t = new Torta();
                
                t.setId(rs.getInt(1));
                t.setPrecio(rs.getFloat(2));
                t.setNombre(rs.getString(3));
                t.setRutaImg(rs.getString(5));
                
                ArrayList<Variante> variantes= new DataTortaVariante().obtenerVariantesTorta(t);
                t.setVariantes(variantes);
                
                listaTortas.add(t);

               
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener tortas por nombre",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
        return listaTortas;
    }
      
      public ArrayList<Torta> obtenerTortas(int inferior, int cantidad) throws DonaCocaException, SQLException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql = "select * from torta limit ?,?;";
        try
        {
            
             ps =  FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, inferior);
            ps.setInt(2, cantidad);

             rs = ps.executeQuery();
            while(rs.next())
            {
                Torta t = new Torta();
                
                t.setId(rs.getInt(1));
                t.setPrecio(rs.getFloat(2));
                t.setNombre(rs.getString(3));
                t.setRutaImg(rs.getString(5));
               
                 ArrayList<Variante> variantes= new DataTortaVariante().obtenerVariantesTorta(t);
                t.setVariantes(variantes);
                
                listaTortas.add(t);

               
            }
            //conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener tortas con limite inferior superior",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
        return listaTortas;
    }
      
   public Torta obtenerTorta(int idTorta) throws DonaCocaException, SQLException{
       String sql = "select * from torta where id_torta = ?;";
       PreparedStatement ps= null;
        ResultSet rs = null;
       Torta t= new Torta();
        try
        {
            
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idTorta);

             rs = ps.executeQuery();
            while(rs.next())
            {
               
                t.setId(rs.getInt(1));
                t.setNombre(rs.getString(3));
                t.setPrecio(rs.getFloat(2));
                 ArrayList<Variante> variantes= new DataTortaVariante().obtenerVariantesTorta(t);
                t.setVariantes(variantes);
                t.setRutaImg(rs.getString(5));
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener la torta buscada",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
        return t;
    }   
   
    public boolean existeTorta(String nombreTorta) throws DonaCocaException, SQLException{      
        String sql = "select count(*) from torta where nombre=? and eliminado = 0;";        
         PreparedStatement ps= null;
        ResultSet rs = null;
        int cantidad=0;
        
        try
        {
            
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1, nombreTorta);
             rs = ps.executeQuery();
                   
            if(rs.next())              
                cantidad = rs.getInt(1);
                       
            //conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener la torta buscada ",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        if(cantidad > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
     public ArrayList<Torta> obtenerVariante(int idVariante) throws DonaCocaException, SQLException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql = "select tv.id_torta from torta_variante tv inner join torta t on tv.id_torta= t.id_torta where tv.id_variante=? ";
        
        try
        {
            
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idVariante);
             rs = ps.executeQuery();
                   
            while(rs.next())
            {
               Torta t= obtenerTorta(rs.getInt(1));
                if(t!=null)
                {               
                    listaTortas.add(t);
                }
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de torta segun variante",e);
        } finally{
             if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }       
        return listaTortas;
    }
   
       public void eliminarTorta(Torta t) throws DonaCocaException, SQLException{
         PreparedStatement ps= null;
         ResultSet rs = null;
         String sql="update torta set eliminado=? where id_torta=?;";
         try{
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            
           // conec.close();
         }
         catch(SQLException e){
             throw new DonaCocaException("Error al eliminar torta",e);
         } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
    }
       
       
      
}
