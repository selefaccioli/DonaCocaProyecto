
package data;

import entity.Detalle;
import entity.Torta;
import entity.Variante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import logic.CtrlDetalle;
import util.DonaCocaException;


public class DataTortaVariante {
   
    
    
    public void agregarTortaVariante(Torta torta) throws DonaCocaException, SQLException{
        PreparedStatement ps= null;
        ResultSet rs = null;
        String transac = "insert into torta_variante values ";
        
         for(int i=0; i<torta.getVariantes().size(); i++)
        {
            transac = transac +"("+torta.getId()+","+torta.getVariantes().get(i).getId()+")";
            
            if(i==torta.getVariantes().size()-1)            
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
            throw new DonaCocaException("Error al agregar torta_variante",e);
        }  finally{
           if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }   
    }
    
    public void actualizarTortaVariante(Torta torta)throws DonaCocaException, SQLException{
        String sql = "delete from torta_variante where id_torta=?;";
        PreparedStatement ps= null;
        ResultSet rs = null;
         try{
             
              ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             ps.setInt(1, torta.getId());
             ps.executeUpdate();
            // conec.close();
             
             this.agregarTortaVariante(torta);
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta_variante",e);
        }  finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }   
    }
    
    public ArrayList<Variante> obtenerVariantesTorta(Torta torta) throws DonaCocaException, SQLException{
        ArrayList<Variante> variantes = new ArrayList<Variante>();
        PreparedStatement ps= null;
        ResultSet rs = null;
        String sql="select variante.`id_variante`, variante.`descripcion`, variante.`id_detalle` from variante inner join torta_variante on variante.`id_variante`= torta_variante.`id_variante`where id_torta=?;";
        CtrlDetalle ctrlD = new CtrlDetalle();
        try{
            
              ps= FactoryConexion.getInstancia().getConn().prepareStatement(sql);
             ps.setInt(1, torta.getId());
              rs= ps.executeQuery();
             
             while(rs.next()){
                 Variante v = new Variante();
                 v.setId(rs.getInt(1));
                 v.setDescripcion(rs.getString(2));
                 Detalle d = ctrlD.obtenerDetalle(rs.getInt(3));
                 v.setDetalle(d);
                 
                 variantes.add(v);
             }
             //conec.close();
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener variantes Torta",e);
        } finally{
            if(rs!=null)rs.close();
            if(ps!=null)ps.close();
            FactoryConexion.getInstancia().releaseConn();
        }   
        
        return variantes;
    }
    
   
}
