package data;
import entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import util.DonaCocaException;


public class DataUsuarios {
    
    Conexion conn = new Conexion();
    public Usuario obtenerUsuario(int idUsuario)throws DonaCocaException{
        Usuario usu= null;
        String sql= "select * from usuario where id_usuario=?;";
        Connection conec= null;
        try{
            conec= conn.getConn();
            
            PreparedStatement ps= conec.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            ResultSet rs= ps.executeQuery();
            
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
            }
            
            conec.close();
        }
        catch(SQLException e){
             throw new DonaCocaException("Error al recuperar el usuario por id",e);
        }
        return usu;
    }
    
    public Usuario obtenerUsuario(String usuario,String password)throws DonaCocaException{
        Usuario usu= null;
        String sql= "select * from usuario where usuario=? and contrasenia=?;";
        Connection conec= null;
        try{
            conec= conn.getConn();
            
            PreparedStatement ps= conec.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, password);
            ResultSet rs= ps.executeQuery();
            
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
            }
            
            conec.close();
        }
        catch(SQLException e){
             throw new DonaCocaException("Error al recuperar el usuario en obtenerUsuario",e);
        }
        return usu;
    }
    
    public boolean existeUsuario(String nombreUsuario) throws DonaCocaException{
        
        String sql= "select count(*) from usuario where usuario= ?;";
        Connection conec= null;
        int cantidad=0;
        
        try{
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                cantidad= rs.getInt(1);
                
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar el usuario en existeUsuario",e);
        }
        
        return cantidad >0;
    }
    
    public ArrayList<Usuario> buscarUsuarios(Usuario usu) throws DonaCocaException{
        
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
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            
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
                resultado.add(usu);
                
            }
            conec.close();
            
            
        }catch(SQLException e){
            throw new DonaCocaException("Error al recuperar uuarios en buscarUsuarios",e);
        }
        return resultado;
        
    }
    
    public ArrayList<Usuario> obtenerUsuarios() throws DonaCocaException{
        ArrayList<Usuario> resultado = new ArrayList<Usuario>();
        
        String sql= "select * from usuario;";
        try{
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
            
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
                resultado.add(usu);
            }
            
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar usuarios", e);
        }
        return resultado;
        
    }
    
    public void editarUsuario(Usuario usu) throws DonaCocaException{
        String sql="update usuario set nombre=? , apellido=? , direccion=? ,telefono=? , mail=?, dni=?,activo=?,es_admin=?,usuario=?, contrasenia=? where id_usuario=?";
               
        try{
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
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
            ps.executeUpdate();
            conec.close();
          
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al modificar usuario",e);
        }  
    }
    
    public void registrarUsuario(Usuario usu)throws DonaCocaException{
        PreparedStatement ps;
        String transac = "insert into usuario(nombre,apellido,dni,usuario,contrasenia,es_admin,activo,mail,telefono,direccion) values (?,?,?,?,?,?,?,?,?,?);";
        try{
            Connection conec= conn.getConn();
            ps= conec.prepareStatement(transac, Statement.RETURN_GENERATED_KEYS);
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
            
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                usu.setId(id);
            }
            conec.close();
            
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar usuario ",e);
        }
        
    }
    
    public void eliminarUsuario(Usuario usu) throws DonaCocaException{
         
         String sql="update usuario set activo=? where id_usuario=?";
         try{
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setBoolean(1, false);
            ps.setInt(2, usu.getId());
            ps.executeUpdate();
         }
         catch(SQLException e){
             throw new DonaCocaException("Error al eliminar usuario",e);
         }
    }
    
}
