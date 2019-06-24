/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Detalle;
import entity.Variante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import logic.CtrlDetalle;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class DataVariante {
 
    
    public ArrayList<Variante> obtenerVariantes() throws DonaCocaException, SQLException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
         PreparedStatement ps= null;
        ResultSet rs = null;
        CtrlDetalle ctrlD = new CtrlDetalle();
        try
        {
           
            String sql = "select v.`id_variante`,v.`descripcion`, v.`id_detalle`,v.`precio` from variante v inner join detalle d on v.id_detalle = d.id_detalle order by d.`nombre` asc;";
             ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             rs= ps.executeQuery();
                
                while(rs.next())
                {
                    Variante v = new Variante();
                    
                    v.setId(rs.getInt(1));
                    v.setDescripcion(rs.getString(2));
                    Detalle d= ctrlD.obtenerDetalle(rs.getInt(3));
                    v.setDetalle(d);
                    v.setPrecio(rs.getFloat(4));
                    
                    listaVariantes.add(v);
                }
              //  conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener variantes",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
                   
        return listaVariantes;
    }        
    
    public Variante obtenerVariante(int idVariante) throws DonaCocaException, SQLException{
         Variante v = new Variante();
         PreparedStatement ps= null;
        ResultSet rs = null;
         CtrlDetalle ctrlD = new CtrlDetalle();
        try
        {
            
            String sql = "select * from variante where id_variante = ?;";
             ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idVariante);
             rs= ps.executeQuery();
                
                if(rs.next())
                {
                   
                    
                    v.setId(rs.getInt(1));
                    v.setDescripcion(rs.getString(2));
                    Detalle d= ctrlD.obtenerDetalle(rs.getInt(3));
                    v.setDetalle(d);
                    v.setPrecio(rs.getFloat(4));
                    
                    
                   
                }
               // conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener variante",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
                   
        return v;
    }   
    
     public ArrayList<Variante> obtenerVariantes(int idTorta) throws DonaCocaException, SQLException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
        String sql = "select tv.id_variante from torta_variante tv inner join torta t on tv.id_torta= t.id_torta where tv.id_torta=? ";
          PreparedStatement ps= null;
        ResultSet rs = null;
        try
        {
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idTorta);
             rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Variante v = obtenerVariante(rs.getInt(1));         
                if(v!=null)
                {               
                    listaVariantes.add(v);
                }
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de variantes segun id torta",e);
        } finally{
             if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }       
        return listaVariantes;
    }
    
    public ArrayList<Variante> obtenerVariantesDetalle(int idDetalle) throws DonaCocaException, SQLException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
         PreparedStatement ps= null;
        ResultSet rs = null;
        String sql = "select * from variante where id_detalle =?;";
        
        try
        {
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idDetalle);
             rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Variante v = obtenerVariante(rs.getInt(1));         
                if(v!=null)
                {               
                    listaVariantes.add(v);
                }
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de variantes segun id detalle",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }       
        return listaVariantes;
    }
    public boolean existeVariante(String nombreVariante) throws DonaCocaException, SQLException{
         PreparedStatement ps= null;
        ResultSet rs = null;
        String sql= "select count(*) from variante where descripcion= ?;";
       
        int cantidad=0;
        
        try{
           
             ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1, nombreVariante);
            
             rs= ps.executeQuery();
            
            if(rs.next()){
                cantidad= rs.getInt(1);
                
            }
            //conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar la variante en existeVariante",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
        return cantidad >0;
    }
      public void registrarVariante(Variante var)throws DonaCocaException, SQLException{
       
          PreparedStatement ps= null;
        ResultSet rs = null;
        String transac = "insert into variante(descripcion,id_detalle,precio) values (?,?,?);";
        try{
            
            ps= FactoryConexion.getInstancia().getConn().prepareStatement(transac, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, var.getDescripcion());
            ps.setInt(2, var.getDetalle().getId());
            ps.setFloat(3, var.getPrecio());
            
            ps.executeUpdate();
             rs= ps.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                var.setId(id);
            }
            
            
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar variante ",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
    }
      
    public void editarVariante(Variante var) throws DonaCocaException, SQLException{
        String sql="update variante set descripcion=? , id_detalle=?, precio=?  where id_variante=?";
          PreparedStatement ps= null;
        ResultSet rs = null;       
        try{
            
            ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setString(1,var.getDescripcion());
            ps.setInt(2, var.getDetalle().getId());
            ps.setFloat(3, var.getPrecio());
            ps.setInt(4, var.getId());
            
            ps.executeUpdate();
           // conec.close();
          
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al modificar variante",e);
        } finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        } 
    }
      public ArrayList<Variante> obtenerVariantesDetalleTorta(int idDetalle,int idTorta) throws DonaCocaException, SQLException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
        PreparedStatement ps= null;
        ResultSet rs = null;  
        String sql = "select v.`id_variante` from variante v inner join torta_variante tv on v.`id_variante`= tv.`id_variante`\n" +
"where tv.`id_torta`= ? and v.`id_detalle`=?;";
        
        try
        {
            
            ps = FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            ps.setInt(1, idTorta);
            ps.setInt(2, idDetalle);
            rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Variante v = obtenerVariante(rs.getInt(1));         
                if(v!=null)
                {               
                    listaVariantes.add(v);
                }
            }
            //conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de variantes segun id detalle y torta",e);
        } finally{
             if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }       
        return listaVariantes;
    }
}
