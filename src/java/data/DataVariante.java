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
    Conexion conn= new Conexion();
    Connection conec= null;
    
    public ArrayList<Variante> obtenerVariantes() throws DonaCocaException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
        CtrlDetalle ctrlD = new CtrlDetalle();
        try
        {
            conec= conn.getConn();
            String sql = "select v.`id_variante`,v.`descripcion`, v.`id_detalle`,v.`precio` from variante v inner join detalle d on v.id_detalle = d.id_detalle order by d.`nombre` asc;";
            PreparedStatement ps= conec.prepareStatement(sql);
            ResultSet rs= ps.executeQuery();
                
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
                conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener variantes",e);
        }
                   
        return listaVariantes;
    }        
    
    public Variante obtenerVariante(int idVariante) throws DonaCocaException{
         Variante v = new Variante();
         CtrlDetalle ctrlD = new CtrlDetalle();
        try
        {
            conec= conn.getConn();
            String sql = "select * from variante where id_variante = ?;";
            PreparedStatement ps= conec.prepareStatement(sql);
            ps.setInt(1, idVariante);
            ResultSet rs= ps.executeQuery();
                
                if(rs.next())
                {
                   
                    
                    v.setId(rs.getInt(1));
                    v.setDescripcion(rs.getString(2));
                    Detalle d= ctrlD.obtenerDetalle(rs.getInt(3));
                    v.setDetalle(d);
                    v.setPrecio(rs.getFloat(4));
                    
                    
                   
                }
                conec.close();
                
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener variante",e);
        }
                   
        return v;
    }   
    
     public ArrayList<Variante> obtenerVariantes(int idTorta) throws DonaCocaException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
        String sql = "select tv.id_variante from torta_variante tv inner join torta t on tv.id_torta= t.id_torta where tv.id_torta=? ";
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idTorta);
            ResultSet rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Variante v = obtenerVariante(rs.getInt(1));         
                if(v!=null)
                {               
                    listaVariantes.add(v);
                }
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de variantes segun id torta",e);
        }        
        return listaVariantes;
    }
    
    public ArrayList<Variante> obtenerVariantesDetalle(int idDetalle) throws DonaCocaException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
        String sql = "select * from variante where id_detalle =?;";
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idDetalle);
            ResultSet rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Variante v = obtenerVariante(rs.getInt(1));         
                if(v!=null)
                {               
                    listaVariantes.add(v);
                }
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de variantes segun id detalle",e);
        }        
        return listaVariantes;
    }
    public boolean existeVariante(String nombreVariante) throws DonaCocaException{
        
        String sql= "select count(*) from variante where descripcion= ?;";
        Connection conec= null;
        int cantidad=0;
        
        try{
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, nombreVariante);
            
            ResultSet rs= ps.executeQuery();
            
            if(rs.next()){
                cantidad= rs.getInt(1);
                
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al recuperar la variante en existeVariante",e);
        }
        
        return cantidad >0;
    }
      public void registrarVariante(Variante var)throws DonaCocaException{
        PreparedStatement ps;
        String transac = "insert into variante(descripcion,id_detalle,precio) values (?,?,?);";
        try{
            Connection conec= conn.getConn();
            ps= conec.prepareStatement(transac, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, var.getDescripcion());
            ps.setInt(2, var.getDetalle().getId());
            ps.setFloat(3, var.getPrecio());
            
            ps.executeUpdate();
            ResultSet rs= ps.getGeneratedKeys();
            
            if(rs.next())
            {
                int id = rs.getInt(1);
                var.setId(id);
            }
            conec.close();
            
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar variante ",e);
        }
        
    }
      
    public void editarVariante(Variante var) throws DonaCocaException{
        String sql="update variante set descripcion=? , id_detalle=?, precio=?  where id_variante=?";
               
        try{
            Connection conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1,var.getDescripcion());
            ps.setInt(2, var.getDetalle().getId());
            ps.setFloat(3, var.getPrecio());
            ps.setInt(4, var.getId());
            
            ps.executeUpdate();
            conec.close();
          
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al modificar variante",e);
        }  
    }
      public ArrayList<Variante> obtenerVariantesDetalleTorta(int idDetalle,int idTorta) throws DonaCocaException{
        ArrayList<Variante> listaVariantes = new ArrayList<>();
        String sql = "select v.`id_variante` from variante v inner join torta_variante tv on v.`id_variante`= tv.`id_variante`\n" +
"where tv.`id_torta`= ? and v.`id_detalle`=?;";
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setInt(1, idTorta);
            ps.setInt(2, idDetalle);
            ResultSet rs = ps.executeQuery();
                   
            while(rs.next())
            {
                Variante v = obtenerVariante(rs.getInt(1));         
                if(v!=null)
                {               
                    listaVariantes.add(v);
                }
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener lista de variantes segun id detalle y torta",e);
        }        
        return listaVariantes;
    }
}
