/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import entity.LineaPedido;
import entity.Pedido;
import entity.Torta;
import entity.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import logic.CtrlUsuario;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class DataPedido {
    
    Conexion conn= new Conexion();
    Connection conec= null;
    
    
    public void cerrarPedido(Pedido p) throws DonaCocaException{
        
        
        String sql = "update pedido set estado=?, cerrado=? where id_pedido=?;";
        try{
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            ps.setString(1, p.getEstado());
            ps.setBoolean(2, p.isCerrado());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al cerrar pedido",e);
        }
    }
    
    public void registrarEnvio(Pedido p) throws DonaCocaException{
        String sql= "update pedido set estado=?, cerrado=? where id_pedido=?;";;
        try
        {
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            
            ps.setString(1, p.getEstado());
            ps.setBoolean(2, true);
            ps.setInt(3, p.getId());
            ps.executeUpdate();
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar envio",e);
        }
    }
    
      public void registrarSena(Pedido p) throws DonaCocaException{
        String sql= "update pedido set sena=?, estado=? where id_pedido=?;";;
        try
        {
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql);
            
            ps.setFloat(1, p.getSena());
            ps.setString(2, p.getEstado());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
            conec.close();
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar seña",e);
        }
    }
    
    public void registrarPedido(Pedido p) throws DonaCocaException{        
        String sql = "insert into pedido(fecha_pedido, fecha_entrega,total, estado, id_usuario, cerrado, sena, aclaraciones, envio_domicilio,porc_descuento,descuento) values (?,?,?,?,?,?,?,?,?,?,?);";
        
        try
        {   
            conec= conn.getConn();
            PreparedStatement ps = conec.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setDate(1, new java.sql.Date(p.getFechaPedido().getTime()));
            ps.setDate(2,new java.sql.Date(p.getFechaEntrega().getTime()));
            ps.setFloat(3, p.getTotal());
            ps.setString(4, p.getEstado());
            ps.setInt(5, p.getUsuario().getId());
            ps.setBoolean(6, p.isCerrado());
            ps.setFloat(7, p.getSena());
            ps.setString(8, p.getAclaraciones());
            ps.setBoolean(9, p.getEnvioDomicilio());
            ps.setFloat(10, p.getPorcentajeDescuento());
            ps.setFloat(11, p.getDescuento());
            ps.executeUpdate();
             
            ResultSet rs = ps.getGeneratedKeys(); //obtengo las ultimas llaves generadas
            
            if(rs.next())
            { 
                int clave=rs.getInt(1);
                DataLineaPedido dlp =new DataLineaPedido();
                DataLineaPedidoVariante dlpv = new DataLineaPedidoVariante();
                dlp.registrarLineas(p.getLineasPedido(), clave);
                dlpv.registrarLpVariantes(p.getLineasPedido(), clave);
            }
                       
             conec.close();      
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al registrar pedido",e);
        }
    }
    
    public ArrayList<Pedido> obtenerPedidos(int idUsuario) throws DonaCocaException{         
        ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
        String sql = "select * from pedido where id_usuario = ? order by pedido.`fecha_entrega` asc, pedido.`estado`= \"Aprobado\";";
        
        try 
        {
           conec= conn.getConn();
           PreparedStatement ps= conec.prepareStatement(sql);
           ps.setInt(1, idUsuario);
            ResultSet rs =ps.executeQuery();
             
            while(rs.next())
            { 
                Pedido p = new Pedido();
                
                p.setId(rs.getInt(1));
                p.setFechaPedido(new java.sql.Date(rs.getDate(2).getTime()));
                p.setFechaEntrega(new java.sql.Date(rs.getDate(3).getTime()));
                p.setTotal(rs.getFloat(4));
                p.setEstado(rs.getString(5));
                Usuario usu = new CtrlUsuario().obtenerUsuario(idUsuario);
                p.setUsuario(usu);
                p.setCerrado(rs.getBoolean(7));
                p.setSena(rs.getFloat(8));
                p.setAclaraciones(rs.getString(9));
                p.setEnvioDomicilio(rs.getBoolean(10));
                p.setPorcentajeDescuento(rs.getFloat(11));
                p.setDescuento(rs.getFloat(12));
                p.setLineasPedido(new DataLineaPedido().obtenerLineasPedido(p.getId()));
                pedidosEncontrados.add(p);                     
            }                   
            conec.close();      
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener pedidos",e);
        }
        return pedidosEncontrados;
    } 
    
    public ArrayList<Pedido> obtenerPedidosEnviados () throws DonaCocaException{         
        ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM pedido where estado like 'Enviado';";
        
        try
        {
           conec= conn.getConn();
           PreparedStatement ps= conec.prepareStatement(sql);
           ResultSet rs =ps.executeQuery();
             
            while(rs.next())
            { 
                Pedido p = new Pedido();
                
                p.setId(rs.getInt(1));
                p.setFechaPedido(new java.sql.Date(rs.getDate(2).getTime()));
                p.setFechaEntrega(new java.sql.Date(rs.getDate(3).getTime()));
                p.setTotal(rs.getFloat(4));
                p.setEstado(rs.getString(5));
                p.setCerrado(rs.getBoolean(7));
                p.setLineasPedido(new DataLineaPedido().obtenerLineasPedido(p.getId()));
                pedidosEncontrados.add(p);                         
            }                   
            conec.close();      
        }
         catch(SQLException e){
            throw new DonaCocaException("Error al obtener pedidos enviados",e);
        }
        return pedidosEncontrados;
    }
    
     public ArrayList<Pedido> obtenerPedidosPendientes() throws DonaCocaException{         
        ArrayList<Pedido> pedidosEncontrados = new ArrayList<>();
        CtrlUsuario ctrlU = new CtrlUsuario();
        Usuario usu = new Usuario();
        String sql = "select p.*, case when DATEDIFF(p.`fecha_entrega`,now())>3 and p.`estado`='Pendiente' \n" +
"                 then \"Aun tiene tiempo para pagar seña\"\n" +
"                 \n" +
"                 when DATEDIFF(p.`fecha_entrega`,now()) and p.`estado`='Pendiente'\n" +
"                 then \"Tiempo para pagar seña expirado, contactar\"\n" +
"                 \n" +
"                 when p.`estado`=\"Aprobado\" \n" +
"                 then \"Seña pagada\" \n" +
"                 \n" +
"                 when p.`estado`=\"Cerrado\" \n" +
"                 then \"Cerrado\"\n" +
"                 \n" +
"                 when p.`estado`=\"Cancelado\"\n" +
"                 then \"Cancelado\"\n" +
"                 \n" +
"                 \n" +
"                    \n" +
"            end as \"Condicion seña\"\n" +
"            \n" +
"from pedido as p\n" +
"order by p.`fecha_entrega` asc, p.`estado`= \"Seña pagada\";";   
        
        try
        {
           conec= conn.getConn();
           PreparedStatement ps= conec.prepareStatement(sql);
           ResultSet rs =ps.executeQuery();
             
            while(rs.next())
            { 
                Pedido p = new Pedido();
                
                
                p.setId(rs.getInt(1));
                p.setFechaPedido(new java.sql.Date(rs.getDate(2).getTime()));
                p.setFechaEntrega(new java.sql.Date(rs.getDate(3).getTime()));
                p.setTotal(rs.getFloat(4));
                p.setEstado(rs.getString(5));
                usu = ctrlU.obtenerUsuario(rs.getInt(6));
                p.setUsuario(usu);
                p.setCerrado(rs.getBoolean(7));
                p.setSena(rs.getFloat(8));
                p.setAclaraciones(rs.getString(9));
                p.setEnvioDomicilio(rs.getBoolean(10));
                p.setPorcentajeDescuento(rs.getFloat(11));
                p.setDescuento(rs.getFloat(12));
                p.setCondicionSena(rs.getString(13));
                DataLineaPedido dlp = new DataLineaPedido();
                ArrayList<LineaPedido> lineas = dlp.obtenerLineasPedido(p.getId());
                p.setLineasPedido(new DataLineaPedido().obtenerLineasPedido(p.getId()));
                pedidosEncontrados.add(p);                  
            }                   
            conec.close();      
        }
        catch(SQLException e){
            throw new DonaCocaException("Error al obtener pedidos pendientes",e);
        }
        return pedidosEncontrados;
    }       
     
     public ArrayList<Pedido> filtrosPedidos(Date fDesde, Date fHasta, String estado, String usuario) throws DonaCocaException, SQLException{
         ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
         int cont=0;
         //if(fDesde != null && fHasta != null && estado != null && usuario != null){
             String sql = "select p.*, case when DATEDIFF(p.`fecha_entrega`,now())>3 and p.`estado`='Pendiente' \n" +
"                 then \"Aun tiene tiempo para pagar seña\"\n" +
"                 \n" +
"                 when DATEDIFF(p.`fecha_entrega`,now()) and p.`estado`='Pendiente'\n" +
"                 then \"Tiempo para pagar seña expirado, contactar\"\n" +
"                 \n" +
"                 when p.`estado`=\"Seña pagada\" \n" +
"                 then \"Seña pagada\" \n" +
"                 \n" +
"                 when p.`estado`=\"Cerrado\" \n" +
"                 then \"Cerrado\"\n" +
"                 \n" +
"                 when p.`estado`=\"Cancelado\"\n" +
"                 then \"Cancelado\"\n" +
"                 \n" +
"                 \n" +
"                    \n" +
"            end as \"Condicion seña\"\n" +
"            \n" +
"           from pedido as p inner join usuario u on p.`id_usuario`= u.`id_usuario` where ";
           
         
        try {
            conec= conn.getConn();
            Usuario usu = new Usuario();
            CtrlUsuario ctrlU = new CtrlUsuario();
            
                if(fDesde != null){
                    sql= sql + "p.fecha_entrega >= '" + fDesde + "'";                    
                    cont = cont + 1;
                    
                   
                }
                if(fHasta != null){
                    if(cont != 0){
                     sql= sql + " and ";   
                    }
                    sql= sql + " p.fecha_entrega <= '" + fHasta + "'";
                    cont = cont + 1;
                    
                }
                if(estado != null){
                    if(cont != 0){
                     sql= sql + " and ";   
                    }
                    sql= sql + " p.estado =  '"+ estado + "'";
                    cont = cont + 1;
                }
                if(usuario != null){
                    if(cont != 0){
                     sql= sql + " and ";   
                    }
                    sql= sql + " (u.nombre like" + "'%"+usuario+"%'" + "or u.apellido like " + "'%"+usuario+"%')";
                    cont = cont + 1;
                }
                
                sql= sql + "order by p.`fecha_entrega` asc, p.`estado`= \"Seña pagada\" ;";
               PreparedStatement ps= conec.prepareStatement(sql);
           ResultSet rs =ps.executeQuery();
             
            while(rs.next())
            { 
                Pedido p = new Pedido();
                
                
                p.setId(rs.getInt(1));
                p.setFechaPedido(new java.sql.Date(rs.getDate(2).getTime()));
                p.setFechaEntrega(new java.sql.Date(rs.getDate(3).getTime()));
                p.setTotal(rs.getFloat(4));
                p.setEstado(rs.getString(5));
                usu = ctrlU.obtenerUsuario(rs.getInt(6));
                p.setUsuario(usu);
                p.setCerrado(rs.getBoolean(7));
                p.setSena(rs.getFloat(8));
                p.setAclaraciones(rs.getString(9));
                p.setEnvioDomicilio(rs.getBoolean(10));
                p.setPorcentajeDescuento(rs.getFloat(11));
                p.setDescuento(rs.getFloat(12));
                p.setCondicionSena(rs.getString(13));
                DataLineaPedido dlp = new DataLineaPedido();
                ArrayList<LineaPedido> lineas = dlp.obtenerLineasPedido(p.getId());
                p.setLineasPedido(new DataLineaPedido().obtenerLineasPedido(p.getId()));
                pedidos.add(p);                  
            }                   
            conec.close();      
            
            
        } catch (DonaCocaException ex) {
            throw new DonaCocaException("Error al filtrar pedidos",ex);
        }
           
         
     return pedidos;
    
}}