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
    Conexion conn= new Conexion();
    Connection conec= null;
    
    
    public void agregarTorta(Torta torta) throws DonaCocaException{
        PreparedStatement ps;
        String transac = "insert into torta(nombre,precio,activo,ruta_img,eliminado) values (?,?,?,?,?);";
        try{
           conec= conn.getConn();
           ps= conec.prepareStatement(transac, Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, torta.getNombre());
           ps.setDouble(2, torta.getPrecio());
           ps.setBoolean(3, true);
           ps.setString(4, torta.getRutaImg());
           ps.setBoolean(5, false);
           
           ps.executeUpdate();
           ResultSet rs= ps.getGeneratedKeys();
           
           if(rs.next())
            {
                int id = rs.getInt(1);
                torta.setId(id);
            }
            new DataTortaVariante().agregarTortaVariante(torta);
            if(torta.getRutasImg() != null){
              new DataTortaImagen().agregarTortaImagen(torta);  
            }
            
            conec.close();
           
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al agregar torta ",e);
        }
        
    }
    
     public void actualizarTorta(Torta torta) throws DonaCocaException{      
        if(torta.getRutaImg() != null){
            String sql="update torta set nombre=? , precio=?,  ruta_img=?, activo =? where id_torta=?";  
            try
            {
                conec= conn.getConn();
                PreparedStatement ps = conec.prepareStatement(sql);
                ps.setString(1, torta.getNombre());
                ps.setDouble(2, torta.getPrecio());
                 ps.setString(3, torta.getRutaImg());
                 ps.setBoolean(4, torta.isActivo());
                ps.setInt(5, torta.getId());
               
                
                new DataTortaVariante().actualizarTortaVariante(torta);
                ps.executeUpdate();
           
                conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta",e);
            }
        }
        else{
            String sql="update torta set nombre=? , precio=?, activo= ?  where id_torta=?";  
            try
            {
                conec= conn.getConn();
                PreparedStatement ps = conec.prepareStatement(sql);
                ps.setString(1, torta.getNombre());
                ps.setDouble(2, torta.getPrecio());
                ps.setBoolean(3, torta.isActivo());
                ps.setInt(4, torta.getId());
               
                
                new DataTortaVariante().actualizarTortaVariante(torta);
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
        String sql = "select * from torta where eliminado =0 order by torta.`id_torta` desc;;";
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
                t.setActivo(rs.getBoolean(4));
                t.setRutaImg(rs.getString(5));
                
                ArrayList<Variante> variantes = new CtrlVariante().obtenerVariantes(t.getId());
                t.setVariantes(variantes);
                
                
                
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
                t.setRutaImg(rs.getString(5));
                
                ArrayList<Variante> variantes= new DataTortaVariante().obtenerVariantesTorta(t);
                t.setVariantes(variantes);
                
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
                t.setRutaImg(rs.getString(5));
               
                 ArrayList<Variante> variantes= new DataTortaVariante().obtenerVariantesTorta(t);
                t.setVariantes(variantes);
                
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
                 ArrayList<Variante> variantes= new DataTortaVariante().obtenerVariantesTorta(t);
                t.setVariantes(variantes);
                t.setRutaImg(rs.getString(5));
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener la torta buscada",e);
        }
        
        return t;
    }   
   
    public boolean existeTorta(String nombreTorta) throws DonaCocaException{      
        String sql = "select count(*) from torta where nombre=? and eliminado = 0;";        
        
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
    
     public ArrayList<Torta> obtenerVariante(int idVariante) throws DonaCocaException{
        ArrayList<Torta> listaTortas = new ArrayList<>();
        String sql = "select tv.id_torta from torta_variante tv inner join torta t on tv.id_torta= t.id_torta where tv.id_variante=? ";
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idVariante);
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
            throw new DonaCocaException("Error al obtener lista de torta segun variante",e);
        }        
        return listaTortas;
    }
   
       public void eliminarTorta(Torta t) throws DonaCocaException{
         
         String sql="update torta set eliminado=? where id_torta=?;";
         try{
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setBoolean(1, true);
            ps.setInt(2, t.getId());
            ps.executeUpdate();
            
            conec.close();
         }
         catch(SQLException e){
             throw new DonaCocaException("Error al eliminar torta",e);
         }
    }
       
       
      
}
