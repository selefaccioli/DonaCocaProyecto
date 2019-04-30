/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import data.DataPedido;
import entity.Pedido;
import entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlPedido;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class FinalizarPedidoComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        
        Pedido p = (Pedido)request.getSession().getAttribute("pedido");
        
       
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try{
            fecha = formato.parse(request.getParameter("fechaEntrega"));              
            p.setFechaEntrega(new java.sql.Date(fecha.getTime()));
           }catch(ParseException e){
                 request.setAttribute("ex", "Ha ocurrido un error con la fecha");
                 return "/carro.jsp";} 
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(p.getFechaPedido()); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, 7);  
        Date fechaEntrega = calendar.getTime();
        
        boolean fechasok = false;
        
       
            if (fecha.compareTo(fechaEntrega) > 0) {
            fechasok = true; }
            else{
                request.getSession().setAttribute("fechaIncorrecta", true);
                return "/carro.jsp";
            }
            
        if(p.getLineasPedido().size() > 0){
            if(request.getSession().getAttribute("usuario") == null ){
                request.getSession().setAttribute("usuarioNoLogueado", true);
                return "/login.jsp";
            }
            else{
                    
                    
                Usuario u = (Usuario)request.getSession().getAttribute("usuario");
                float total = (float) request.getSession().getAttribute("total");
                
                CtrlPedido ctrlP = new CtrlPedido();
                p.setUsuario(u);
                p.setEstado("Pendiente");
                p.setTotal(total);
                
                try{
                    ctrlP.registrarPedido(p);
                    
                }
                catch (DonaCocaException ex){
                    request.setAttribute("ex", ex.getMessage());
                    return "/carro.jsp";
                    
                }
                request.getSession().setAttribute("exitoPedido", true);
                Pedido ped = new Pedido();
                request.getSession().setAttribute("pedido", ped);
            } 
            
        }
        else{
            request.getSession().setAttribute("pedidoVacio", true);
        }
        return "/carro.jsp";
    }
    
}
