package data;
import entity.Pedido;
import entity.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.DonaCocaException;


public class DataUsuarios {
    
   
    
    public Usuario obtenerUsuario(int idUsuario)throws DonaCocaException, SQLException{
        Usuario usu= null;
        PreparedStatement ps= null;
        ResultSet rs = null; 
        String sql= "select * from usuario where id_usuario=?;";
       
        try{
           
            
             ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idUsuario);
             rs= ps.executeQuery();
            
            if(rs.next()){
                usu = new Usuario();
                usu.setId(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setDni(rs.getInt(4));
                usu.setUsuario(rs.getString(5));
                usu.setContrasenia(rs.getString(6));
                usu.setEsAdmin(rs.getBoolean(7)); 
                usu.setActivo(rs.getBoolean(8));
                usu.setMail(rs.getString(9));
                usu.setTelefono(rs.getString(10));
                usu.setDireccion(rs.getString(11));
                usu.setFechaNacimiento(new java.sql.Date(rs.getDate(12).getTime()));
                usu.setConocimiento(rs.getString(13));
            }
            
           // conec.close();
        }
        catch(SQLException e){
             throw new DonaCocaException("Error al recuperar el usuario por id",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        } 
        return usu;
    }
    
    public Usuario obtenerUsuario(String usuario,String password)throws DonaCocaException, SQLException{
        Usuario usu= null;
        PreparedStatement ps= null;
        ResultSet rs = null; 
        String sql= "select * from usuario where usuario=? and contrasenia=?;";
        
        try{
            
            
             ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
             rs= ps.executeQuery();
            
            if(rs.next()){
                usu = new Usuario();
                usu.setId(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setDni(rs.getInt(4));
                usu.setUsuario(rs.getString(5));
                usu.setContrasenia(rs.getString(6));
                usu.setEsAdmin(rs.getBoolean(7)); 
                usu.setActivo(rs.getBoolean(8));
                usu.setMail(rs.getString(9));
                usu.setTelefono(rs.getString(10));
                usu.setDireccion(rs.getString(11));
                usu.setFechaNacimiento(new java.sql.Date(rs.getDate(12).getTime()));
                usu.setConocimiento(rs.getString(13));
            }
            
            //conec.close();
        }
        catch(SQLException e){
             throw new DonaCocaException("Error al recuperar el usuario en obtenerUsuario",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        } 
        return usu;
    }
    
    public boolean existeUsuario(String nombreUsuario) throws DonaCocaException, SQLException{
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql= "select count(*) from usuario where usuario= ?;";
        
        int cantidad=0;
        
        try{
            
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            
             rs= ps.executeQuery();
            
            if(rs.next()){
                cantidad= rs.getInt(1);
                
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar el usuario en existeUsuario",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        } 
        
        return cantidad >0;
    }
    
    public ArrayList<Usuario> buscarUsuarios(Usuario usu) throws DonaCocaException, SQLException{
         PreparedStatement ps= null;
        ResultSet rs = null;
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
        
        String apellido = usu.getApellido();
        String sql= "select * from usuario where activo=1 and apellido like '"+apellido+"%'";
        int id = usu.getId();
        String nombre = usu.getNombre();
        int dni = usu.getDni();
        if(id!=0)
            sql = sql + " and id_usuario="+id;
        if(!nombre.equals(""))
            sql = sql + " and nombre like '"+nombre+"%'";
        if(dni != 0)
            sql = sql + " and dni like '%"+dni+"%'";
        sql = sql+";";
        
        try{
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             rs= ps.executeQuery();
            
            while(rs.next()){
                usu= new Usuario();
                usu.setId(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setDni(rs.getInt(4));
                usu.setUsuario(rs.getString(5));
                usu.setContrasenia(rs.getString(6));
                usu.setEsAdmin(rs.getBoolean(7)); 
                usu.setActivo(rs.getBoolean(8));
                usu.setMail(rs.getString(9));
                usu.setTelefono(rs.getString(10));
                usu.setDireccion(rs.getString(11));
                usu.setFechaNacimiento(new java.sql.Date(rs.getDate(12).getTime()));
                usu.setConocimiento(rs.getString(13));
                resultado.add(usu);
                
            }
           // conec.close();
            
            
        }catch(SQLException e){
            throw new DonaCocaException("Error al recuperar uuarios en buscarUsuarios",e);
        }  finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        } 
        return resultado;
        
    }
    
    public ArrayList<Usuario> obtenerUsuarios() throws DonaCocaException, SQLException{
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
         PreparedStatement ps= null;
        ResultSet rs = null;
        String sql= "select * from usuario;";
        try{
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             rs= ps.executeQuery();
            
            while(rs.next()){
                Usuario usu= new Usuario();
                usu.setId(rs.getInt(1));
                usu.setNombre(rs.getString(2));
                usu.setApellido(rs.getString(3));
                usu.setDni(rs.getInt(4));
                usu.setUsuario(rs.getString(5));
                usu.setContrasenia(rs.getString(6));
                usu.setEsAdmin(rs.getBoolean(7)); 
                usu.setActivo(rs.getBoolean(8));
                usu.setMail(rs.getString(9));
                usu.setTelefono(rs.getString(10));
                usu.setDireccion(rs.getString(11));
                usu.setFechaNacimiento(new java.sql.Date(rs.getDate(12).getTime()));
                usu.setConocimiento(rs.getString(13));
                resultado.add(usu);
            }
            
            //conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar usuarios", e);
        }  finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        return resultado;
        
    }
    
    public void editarUsuario(Usuario usu) throws DonaCocaException, SQLException{
        String sql="update usuario set nombre=? , apellido=? , direccion=? ,telefono=? , mail=?, dni=?,activo=?,es_admin=?,usuario=?, contrasenia=?, fechanacimiento=?, conocimiento= ? where id_usuario=?";
        PreparedStatement ps= null;
             
        try{
            
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getApellido());
            ps.setString(3, usu.getDireccion());
            ps.setString(4, usu.getTelefono());
            ps.setString(5, usu.getMail());
            ps.setInt(6, usu.getDni());
            ps.setBoolean(7, usu.isActivo());
            ps.setBoolean(8, usu.isEsAdmin());
            ps.setString(9,usu.getUsuario());
            ps.setString(10, usu.getContrasenia());
            ps.setInt(11, usu.getId());
            ps.setDate(12, new java.sql.Date(usu.getFechaNacimiento().getTime()));
            ps.setString(13, usu.getConocimiento());
           
            ps.executeUpdate();
           // conec.close();
          
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al modificar usuario",e);
        } finally{
            
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        } 
    }
    
    public void registrarUsuario(Usuario usu)throws DonaCocaException, SQLException{
      
        PreparedStatement ps= null;
        ResultSet rs = null;  
        String transac = "insert into usuario(nombre,apellido,dni,usuario,contrasenia,es_admin,activo,mail,telefono,direccion,fechanacimiento,conocimiento) values (?,?,?,?,?,?,?,?,?,?,?,?);";
        try{
           
            ps= FactoryConexion.getInstancia().getConn().prepareStatement(transac, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, usu.getNombre());
            ps.setString(2, usu.getApellido());
            ps.setInt(3, usu.getDni());
            ps.setString(4, usu.getUsuario());
            ps.setString(5, usu.getContrasenia());
            ps.setBoolean(6, usu.isEsAdmin());
            ps.setBoolean(7, usu.isActivo());
            ps.setString(8, usu.getMail());
            ps.setString(9, usu.getTelefono());
            ps.setString(10, usu.getDireccion());
            ps.setDate(11, new java.sql.Date(usu.getFechaNacimiento().getTime()));
            ps.setString(12, usu.getConocimiento());
            
            ps.executeUpdate();
             rs= ps.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                usu.setId(id);
            }
           // conec.close();
            
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar usuario ",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
    }
    
    public void eliminarUsuario(Usuario usu) throws DonaCocaException, SQLException{
         PreparedStatement ps= null;
        ResultSet rs = null;
         String sql="update usuario set activo=? where id_usuario=?";
         try{
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, usu.getId());
            ps.executeUpdate();
         }
         catch(SQLException e){
             throw new DonaCocaException("Error al eliminar usuario",e);
         } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
    }
    
   
    
}
