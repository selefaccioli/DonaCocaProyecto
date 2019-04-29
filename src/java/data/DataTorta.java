package data;

import entity.Detalle;
import entity.Torta;
import entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.CtrlDetalle;
import util.DonaCocaException;



public class DataTorta {
    Conexion conn= new Conexion();
    Connection conec= null;
    
    
    public void agregarTorta(Torta torta) throws DonaCocaException{
        PreparedStatement ps;
        String transac = "insert into torta(nombre,precio,imagen) values (?,?,?);";
        try{
           conec= conn.getConn();
           ps= conec.prepareStatement(transac, Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, torta.getNombre());
           ps.setDouble(2, torta.getPrecio());
           ps.setBlob(3, torta.getImagen());
           
           ps.executeUpdate();
           ResultSet rs= ps.getGeneratedKeys();
           
           if(rs.next())
            {
                int id = rs.getInt(1);
                torta.setId(id);
            }
            new DataTortaDetalle().agregarTortaDetalle(torta);
            conec.close();
           
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al agregar torta ",e);
        }
        
    }
    
     public void actualizarTorta(Torta torta) throws DonaCocaException{      
        if(torta.getImagen() != null){
            String sql="update torta set nombre=? , precio=?, imagen=?  where id_torta=?";  
            try
            {
                conec= conn.getConn();
                PreparedStatement ps = conec.prepareStatement(sql);
                ps.setString(1, torta.getNombre());
                ps.setDouble(2, torta.getPrecio());
                 ps.setBlob(3, torta.getImagen());
                ps.setInt(4, torta.getId());
               
                
                new DataTortaDetalle().actualizarTortaDetalle(torta);
                ps.executeUpdate();
           
                conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta",e);
            }
        }
        else{
            String sql="update torta set nombre=? , precio=?  where id_torta=?";  
            try
            {
                conec= conn.getConn();
                PreparedStatement ps = conec.prepareStatement(sql);
                ps.setString(1, torta.getNombre());
                ps.setDouble(2, torta.getPrecio());
                ps.setInt(3, torta.getId());
               
                
                new DataTortaDetalle().actualizarTortaDetalle(torta);
                ps.executeUpdate();
           
                conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta",e);
            }
        }
            
            
    }
     
     public byte[] buscarImagen(int id) throws DonaCocaException{
        String sql = "select imagen from torta where id_torta=?;";
        byte[] imgData=null;
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();      
            if(rs.next())
            {
                imgData = rs.getBytes("imagen");
            }
            conec.close();
        } 
        catch(SQLException e){
            throw new DonaCocaException("Error al buscar imagen de torta",e);
        }
        return imgData;
    }
    
     
     public ArrayList<Torta> obtenerTortas() throws DonaCocaException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        String sql = "select * from torta";
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Torta t = new Torta();
                
                t.setId(rs.getInt(1));
                t.setPrecio(rs.getFloat(2));
                t.setNombre(rs.getString(3));
                
                ArrayList<Detalle> detalles = new CtrlDetalle().obtenerDetalles(t.getId());
                t.setDetalles(detalles);
                
                
                
                listaTortas.add(t);

            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener tortas all",e);
        }
         
        return listaTortas;
    }
     
      public ArrayList<Torta> obtenerTortas(String nombre, int inferior, int cantidad) throws DonaCocaException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        String sql = "select * from torta where nombre like '%"+nombre+"%' limit ?,?;";
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, inferior);
            ps.setInt(2, cantidad);

            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Torta t = new Torta();
                
                t.setId(rs.getInt(1));
                t.setPrecio(rs.getFloat(2));
                t.setNombre(rs.getString(3));
                
                ArrayList<Detalle> detalles= new DataTortaDetalle().obtenerDetallesTorta(t);
                t.setDetalles(detalles);
                
                listaTortas.add(t);

               
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener tortas por nombre",e);
        }
        
        return listaTortas;
    }
      
      public ArrayList<Torta> obtenerTortas(int inferior, int cantidad) throws DonaCocaException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        String sql = "select * from torta limit ?,?;";
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, inferior);
            ps.setInt(2, cantidad);

            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Torta t = new Torta();
                
                t.setId(rs.getInt(1));
                t.setPrecio(rs.getFloat(2));
                t.setNombre(rs.getString(3));
               
                ArrayList<Detalle> detalles= new DataTortaDetalle().obtenerDetallesTorta(t);
                t.setDetalles(detalles);
                
                listaTortas.add(t);

               
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener tortas con limite inferior superior",e);
        }
        
        return listaTortas;
    }
      
   public Torta obtenerTorta(int idTorta) throws DonaCocaException{
       String sql = "select * from torta where id_torta = ?;";
       Torta t= new Torta();
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idTorta);

            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
               
                t.setId(rs.getInt(1));
                t.setNombre(rs.getString(3));
                t.setPrecio(rs.getFloat(2));
                ArrayList<Detalle> detalles= new DataTortaDetalle().obtenerDetallesTorta(t);
                t.setDetalles(detalles);
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener la torta buscada",e);
        }
        
        return t;
    }   
   
    public boolean existeTorta(String nombreTorta) throws DonaCocaException{      
        String sql = "select count(*) from torta where nombre=?";        
        
        int cantidad=0;
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, nombreTorta);
            ResultSet rs = ps.executeQuery();
                   
            if(rs.next())              
                cantidad = rs.getInt(1);
                       
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener la torta buscada ",e);
        }
        if(cantidad > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
     public ArrayList<Torta> obtenerDetalle(int idDetalle) throws DonaCocaException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        String sql = "select td.id_torta from torta_detalle td inner join torta t on td.id_torta= t.id_torta where td.id_detalle=? ";
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            ResultSet rs = ps.executeQuery();
                   
            while(rs.next())
            {
               Torta t= obtenerTorta(rs.getInt(1));
                if(t!=null)
                {               
                    listaTortas.add(t);
                }
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de torta segun detalle",e);
        }        
        return listaTortas;
    }
   
       public void eliminarTorta(Torta t) throws DonaCocaException{
         
         String sql="delete from torta where id_torta=?;";
         try{
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, t.getId());
            ps.executeUpdate();
         }
         catch(SQLException e){
             throw new DonaCocaException("Error al eliminar torta",e);
         }
    }
       
       
      
}
