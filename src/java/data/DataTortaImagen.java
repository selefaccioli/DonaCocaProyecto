/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Detalle;
import entity.Imagen;
import entity.Torta;
import entity.Variante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.CtrlDetalle;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class DataTortaImagen {
  
    
    
    public void agregarTortaImagen(Torta torta) throws DonaCocaException, SQLException{
        PreparedStatement ps= null;
        ResultSet rs = null;
        String transac = "insert into torta_imagen values ";
        
         for(int i=0; i<torta.getRutasImg().size(); i++)
        {
            transac = transac +"("+torta.getId()+","+torta.getRutasImg().get(i).getId()+")";
            
            if(i==torta.getRutasImg().size()-1)            
                transac = transac+";";
            else            
                transac = transac+",";            
        }
         
         try{
             
              ps= FactoryConexion.getInstancia().getConn().prepareStatement(transac);
             ps.executeUpdate();
            // conec.close();
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al agregar torta_imagen",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }  
    }
    
    public void actualizarTortaImagen(Torta torta)throws DonaCocaException, SQLException{
        String sql = "delete from torta_imagen where id_torta=?;";
         PreparedStatement ps= null;
        ResultSet rs = null;
         try{
            
              ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             ps.setInt(1, torta.getId());
             ps.executeUpdate();
             //conec.close();
             
             this.agregarTortaImagen(torta);
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta_imagen",e);
        } finally{
             if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }   
    }
    
     public ArrayList<Imagen> obtenerImagenesTorta(Torta torta) throws DonaCocaException, SQLException{
        ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql="select torta_imagen.id_imagen, torta_imagen.ruta from torta_imagen `where id_torta=?;";
        CtrlDetalle ctrlD = new CtrlDetalle();
        try{
             
              ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             ps.setInt(1, torta.getId());
              rs= ps.executeQuery();
             
             while(rs.next()){
                 Imagen img = new Imagen();
                 img.setId(rs.getInt(1));
                 img.setRuta(rs.getString(2));
                 

                 
                 imagenes.add(img);
             }
             //conec.close();
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener variantes Torta",e);
        }  finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }  
        
        return imagenes;
    }
}
