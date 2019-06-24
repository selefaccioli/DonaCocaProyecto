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
    
    
   
    public ArrayList<Cupon> obtenerCupones() throws DonaCocaException, SQLException{
        ArrayList<Cupon> cupones = new ArrayList<Cupon>();
        String sql = "select * from cupon;";
        PreparedStatement stmt= null;
	ResultSet rs= null;
        
        try {
            stmt=FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            //conec = conn.getConn();
            /*PreparedStatement ps;
            ps = conec.prepareStatement(sql);*/
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cupon c = new Cupon();
                
                c.setId(rs.getInt(1));
                c.setCodigo(rs.getString(2));
                c.setActivo(rs.getBoolean(3));
                c.setPorcDescuento(rs.getFloat(4));
                
                cupones.add(c);
            }
            //conec.close();
        } catch (SQLException ex) {
            throw new DonaCocaException("Error al obtener cupones all",ex);
        } finally{
           
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			
        }
        
        return cupones;
       
    }
    
    public boolean existeCupon(String codCupon) throws DonaCocaException, SQLException{      
        String sql = "select count(*) from cupon where codigo=?";        
        PreparedStatement stmt= null;
	ResultSet rs= null;
        int cantidad=0;
        
        try
        {
            stmt=FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            /*conec = conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);*/
            stmt.setString(1, codCupon);
            rs = stmt.executeQuery();
                   
            if(rs.next())              
                cantidad = rs.getInt(1);
                       
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener el cupon buscado ",e);
        } finally{
           
				if(rs!=null)rs.close();
				if(stmt!=null)stmt.close();
				FactoryConexion.getInstancia().releaseConn();
			
        }
        
        if(cantidad > 0){
            return true;
        }
        else{
            return false;
        }
    }
    
    
     public Cupon obtenerCupon(String codCupon) throws DonaCocaException, SQLException{
       String sql = "select * from cupon where codigo = ? and activo = 1;";
       Cupon c = null;
       PreparedStatement ps = null;
       ResultSet rs = null;
       
        try
        {
            ps=FactoryConexion.getInstancia().getConn().prepareStatement(sql);
            /*conec = conn.getConn();
            ps = conec.prepareStatement(sql);*/
            ps.setString(1, codCupon);

            rs = ps.executeQuery();
            while(rs.next())
            {
                c= new Cupon();
                c.setId(rs.getInt(1));
                c.setCodigo(rs.getString(2));
                c.setActivo(rs.getBoolean(3));
                c.setPorcDescuento(rs.getFloat(4));
            }
           // conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener el cupon buscado",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
        return c;
    }   
   
      public void agregarCupon(Cupon cupon) throws DonaCocaException, SQLException{
        PreparedStatement ps= null;
        ResultSet rs = null;
        String transac = "insert into cupon(codigo,activo,porc_descuento) values (?,?,?);";
        try{
           //conec= conn.getConn();
           ps= FactoryConexion.getInstancia().getConn().prepareStatement(transac, PreparedStatement.RETURN_GENERATED_KEYS);
					
           ps.setString(1, cupon.getCodigo());
           ps.setBoolean(2, true);
           ps.setFloat(3, cupon.getPorcDescuento());
           
           ps.executeUpdate();
           rs= ps.getGeneratedKeys();
           
           if(rs.next())
            {
                int id = rs.getInt(1);
                cupon.setId(id);
            }
           
           // conec.close();
           
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al agregar cupon ",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
    }
     public void actualizarCupon(Cupon cupon) throws DonaCocaException, SQLException{      
            PreparedStatement ps= null;
            ResultSet rs = null;
            String sql="update cupon set  activo=?, porc_descuento=?, codigo=? where id_cupon=?";  
            try
            {
                //conec= conn.getConn();
                ps =  FactoryConexion.getInstancia().getConn().prepareStatement(sql);
                ps.setBoolean(1, cupon.isActivo());
                ps.setFloat(2, cupon.getPorcDescuento());
                ps.setString(3, cupon.getCodigo());
                ps.setInt(4, cupon.getId());
                
              
                ps.executeUpdate();
           
                //conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al actualizar cupon",e);
            } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
        
       
            
            
    }
      
}
