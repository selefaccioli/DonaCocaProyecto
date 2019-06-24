/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Pedido;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlPedido;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class FiltrosComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        
        ArrayList<Pedido> pedidosFiltrados = new  ArrayList<Pedido>();
        CtrlPedido ctrlP = new CtrlPedido();
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesde = null;
        String fechaHasta = null;
        Date fDesde = null;
        Date fHasta = null;
        String estado = null;
        String usuario= null;
        Pedido p= new Pedido();
        
        try{
            fechaDesde = request.getParameter("dateFrom");
            if(fechaDesde != null && fechaDesde != ""){
               fDesde= new java.sql.Date((formato.parse(fechaDesde).getTime()));
               
            }
            fechaHasta = request.getParameter("dateTo");
            if(fechaHasta != null && fechaHasta != ""){
               fHasta= new java.sql.Date((formato.parse(fechaHasta).getTime()));
                
            }
         
            
           }catch(ParseException e){
                 request.setAttribute("ex", "Ha ocurrido un error con la fecha");
                 if(request.getParameter("SearchTodos") != null){
            return "/pedidosTodos.jsp";
        }
        else{
            return "/pedidosARealizar.jsp";
        }
        } 
        
       estado = request.getParameter("estadoSelect");
       if(estado.equals("ninguno")) estado= null;
       
       usuario = request.getParameter("usuInput");
       if(fDesde == null && fHasta == null && estado == null && usuario == null){
            try {
                pedidosFiltrados = ctrlP.obtenerPedidosPendientes();
            } catch (DonaCocaException ex) {
                request.setAttribute("ex", "Ha ocurrido un error filtrando");
                if(request.getParameter("SearchTodos") != null){
            return "/pedidosTodos.jsp";
        }
        else{
            return "/pedidosARealizar.jsp";
        }
        
            } catch (SQLException ex) {
                Logger.getLogger(FiltrosComando.class.getName()).log(Level.SEVERE, null, ex);
            }
       }else{
          try {
            pedidosFiltrados = ctrlP.filtrosPedidos(fDesde, fHasta, estado, usuario);
        } catch (DonaCocaException ex) {
            request.setAttribute("ex", "Ha ocurrido un error filtrando");
             if(request.getParameter("SearchTodos") != null){
            return "/pedidosTodos.jsp";
        }
        else{
            return "/pedidosARealizar.jsp";
        }
        
        } catch (SQLException ex) {
            request.setAttribute("ex", "Ha ocurrido un error filtrando");
              if(request.getParameter("SearchTodos") != null){
            return "/pedidosTodos.jsp";
        }
        else{
            return "/pedidosARealizar.jsp";
        }
        
        } 
       }
        
        
        request.getSession().setAttribute("pedidosFiltrados", pedidosFiltrados);
       
        request.getSession().setAttribute("provieneDeFiltro", true);
        
        if(request.getParameter("SearchTodos") != null){
            return "/pedidosTodos.jsp";
        }
        else{
            return "/pedidosARealizar.jsp";
        }
        
       
    }
    
}
