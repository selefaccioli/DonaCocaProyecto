/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.Detalle;
import entity.LineaPedido;
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
public class DataLineaPedidoVariante {
  
    
    public void registrarLpVariantes(ArrayList<LineaPedido> lineas, int idPedido) throws DonaCocaException, SQLException{ 
     PreparedStatement ps= null;
     ResultSet rs = null; 
         for(int i=0; i< lineas.size(); i++)
        {
            String transac = "insert into lineapedido_variante values ";
            for(int j= 0 ; j< lineas.get(i).getVariantes().size() ; j++){
                transac = transac +"("+ idPedido +","+ lineas.get(i).getTorta().getId()+","+ lineas.get(i).getVariantes().get(j).getId() +")";        
                    
             if(j== ((lineas.get(i).getVariantes().size())-1))            
                transac = transac+";";
            else            
                transac = transac+","; 
            
            }
             try
            {
               
                ps = FactoryConexion.getInstancia().getConn().prepareStatement(transac);
               ps.executeUpdate();
               
               //conec.close();
            }
            catch(SQLException e){
            throw new DonaCocaException("Error al registrar lineapedido_variante",e);
            } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }
            
                      
        }   
            
           
                       
              
    }    
    
    public ArrayList<Variante> obtenerVariantesLp(LineaPedido lp) throws DonaCocaException, SQLException{
        ArrayList<Variante> variantes = new ArrayList<Variante>();
        PreparedStatement ps= null;
        ResultSet rs = null; 
        String sql="select variante.`id_variante`, variante.`descripcion`, variante.`id_detalle` from variante inner join lineapedido_variante on variante.`id_variante`= lineapedido_variante.`id_variante` where id_torta=? and id_pedido=?;";
        CtrlDetalle ctrlD = new CtrlDetalle();
        try{
            
             ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             ps.setInt(1, lp.getTorta().getId());
             ps.setInt(2, lp.getIdPedido());
             rs= ps.executeQuery();
             
             while(rs.next()){
                 Variante v = new Variante();
                 v.setId(rs.getInt(1));
                 v.setDescripcion(rs.getString(2));
                 Detalle d = ctrlD.obtenerDetalle(rs.getInt(3));
                 v.setDetalle(d);
                 
                 variantes.add(v);
             }
            // conec.close();
             
            
         } 
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener variantes lineapedido",e);
            
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }  
         
        return variantes;
    }
    
}
