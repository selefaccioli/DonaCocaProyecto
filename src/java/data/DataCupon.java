/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Cupon;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class DataCupon {
    
    Conexion conn= new Conexion();
    Connection conec= null;
   
    public ArrayList<Cupon> obtenerCupones() throws DonaCocaException{
        ArrayList<Cupon> cupones = new ArrayList<Cupon>();
        String sql = "select * from cupon;";
        
        
        try {
            conec = conn.getConn();
            PreparedStatement ps;
            ps = conec.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Cupon c = new Cupon();
                
                c.setId(rs.getInt(1));
                c.setCodigo(rs.getString(2));
                c.setActivo(rs.getBoolean(3));
                c.setPorcDescuento(rs.getFloat(4));
                
                cupones.add(c);
            }
            
        } catch (SQLException ex) {
            throw new DonaCocaException("Error al obtener cupones all",ex);
        }
        
        return cupones;
       
    }
    
    public boolean existeCupon(String codCupon) throws DonaCocaException{      
        String sql = "select count(*) from cupon where codigo=?";        
        
        int cantidad=0;
        
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, codCupon);
            ResultSet rs = ps.executeQuery();
                   
            if(rs.next())              
                cantidad = rs.getInt(1);
                       
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener el cupon buscado ",e);
        }
        if(cantidad > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    
     public Cupon obtenerCupon(String codCupon) throws DonaCocaException{
       String sql = "select * from cupon where codigo = ? and activo = 1;";
       Cupon c = null;
        try
        {
            conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, codCupon);

            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                c= new Cupon();
                c.setId(rs.getInt(1));
                c.setCodigo(rs.getString(2));
                c.setActivo(rs.getBoolean(3));
                c.setPorcDescuento(rs.getFloat(4));
            }
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener el cupon buscado",e);
        }
        
        return c;
    }   
   
      public void agregarCupon(Cupon cupon) throws DonaCocaException{
        PreparedStatement ps;
        String transac = "insert into cupon(codigo,activo,porc_descuento) values (?,?,?);";
        try{
           conec= conn.getConn();
           ps= conec.prepareStatement(transac, Statement.RETURN_GENERATED_KEYS);
           ps.setString(1, cupon.getCodigo());
           ps.setBoolean(2, true);
           ps.setFloat(3, cupon.getPorcDescuento());
           
           ps.executeUpdate();
           ResultSet rs= ps.getGeneratedKeys();
           
           if(rs.next())
            {
                int id = rs.getInt(1);
                cupon.setId(id);
            }
           
            conec.close();
           
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al agregar cupon ",e);
        }
        
    }
     public void actualizarCupon(Cupon cupon) throws DonaCocaException{      
       
            String sql="update cupon set  activo=?, porc_descuento=? where codigo=?";  
            try
            {
                conec= conn.getConn();
                PreparedStatement ps = conec.prepareStatement(sql);
                ps.setBoolean(1, cupon.isActivo());
                ps.setFloat(2, cupon.getPorcDescuento());
                ps.setString(3, cupon.getCodigo());
                
              
                ps.executeUpdate();
           
                conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al actualizar cupon",e);
            }
        
       
            
            
    }
      
}
