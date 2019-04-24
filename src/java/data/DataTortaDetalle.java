
package data;

import entity.Detalle;
import entity.Torta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import util.DonaCocaException;


public class DataTortaDetalle {
    Conexion conn= new Conexion();
    
    
    public void agregarTortaDetalle(Torta torta) throws DonaCocaException{
        
        String transac = "insert into finaljava.torta_detalle values ";
        
         for(int i=0; i<torta.getDetalles().size(); i++)
        {
            transac = transac +"("+torta.getId()+","+torta.getDetalles().get(i).getId()+")";
            
            if(i==torta.getDetalles().size()-1)            
                transac = transac+";";
            else            
                transac = transac+",";            
        }
         
         try{
             Connection conec= conn.getConn();
             PreparedStatement ps= conec.prepareStatement(transac);
             ps.executeUpdate();
             conec.close();
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al agregar torta_detalle",e);
        }   
    }
    
    public void actualizarTortaDetalle(Torta torta)throws DonaCocaException{
        String sql = "delete from finaljava.torta_detalle where id_torta=?;";
         try{
             Connection conec= conn.getConn();
             PreparedStatement ps= conec.prepareStatement(sql);
             ps.setInt(1, torta.getId());
             ps.executeUpdate();
             conec.close();
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al actualizar torta_detalle",e);
        }   
    }
    
    public ArrayList<Detalle> obtenerDetallesTorta(Torta torta) throws DonaCocaException{
        ArrayList<Detalle> detalles = new ArrayList<Detalle>();
        String sql="select detalle.`nombre`, detalle.`detalle` from detalle inner join torta_detalle on " +
                    "detalle.`id_detalle`= torta_detalle.`id_detalle`where id_torta=?;";
        
        try{
             Connection conec= conn.getConn();
             PreparedStatement ps= conec.prepareStatement(sql);
             ps.setInt(1, torta.getId());
             ResultSet rs= ps.executeQuery();
             
             while(rs.next()){
                 Detalle d = new Detalle();
                 //d.setId(rs.getInt(1));
                 d.setNombre(rs.getString(1));
                 d.setDetalle(rs.getString(2));
                 detalles.add(d);
             }
             conec.close();
         }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener detallesTorta",e);
        }   
        
        return detalles;
    }
}
