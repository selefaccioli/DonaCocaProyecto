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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlPedido;
import logic.CtrlUsuario;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class FinalizarPedidoComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        CtrlUsuario ctrlU = new CtrlUsuario();
        Pedido p = (Pedido)request.getSession().getAttribute("pedido");
        String aclaraciones = request.getParameter("aclaraciones");
        String envio = request.getParameter("radio1");
        float total = (float) request.getSession().getAttribute("total");
        String mailUsu;
        
        p.setAclaraciones(aclaraciones);
       
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
                return "/Checkout.jsp";
            }
            
        if(p.getLineasPedido().size() > 0){
            if(request.getSession().getAttribute("usuario") == null ){
                String nombre = request.getParameter("nomUsu");
                String apellido= request.getParameter("apeUsu");
                String mail = request.getParameter("mailUsu");
                String direcc = request.getParameter("direcUsu");
                String tel = request.getParameter("telUsu");
                
                Usuario usuNuevo = new Usuario();
                
                usuNuevo.setNombre(nombre);
                usuNuevo.setActivo(true);
                usuNuevo.setApellido(apellido);
                usuNuevo.setDireccion(direcc);
                usuNuevo.setTelefono(tel);
                usuNuevo.setMail(mail);
                mailUsu = usuNuevo.getMail();
                
                try {
                    ctrlU.registrarUsuario(usuNuevo);
                    p.setUsuario(usuNuevo);
                    
                } catch (DonaCocaException ex) {
                    request.setAttribute("ex", ex.getMessage());
                    return "/Checkout.jsp";
                }
                
            }
            else{
                    
                    
                Usuario u = (Usuario)request.getSession().getAttribute("usuario");
                mailUsu = u.getMail();
                p.setUsuario(u); 
            }
                
                
                CtrlPedido ctrlP = new CtrlPedido();
                
                if(envio.equals("domicilio")){
                    p.setEnvioDomicilio(true);
                }
                else{
                    p.setEnvioDomicilio(false);
                }
                p.setEstado("Pendiente");
                p.setTotal(total);
               
                
                
                try{
                    ctrlP.registrarPedido(p);
                }
                catch (DonaCocaException ex){
                    request.setAttribute("ex", ex.getMessage());
                    return "/Checkout.jsp";
                    
                }
                request.getSession().setAttribute("exitoPedido", true);
                request.getSession().setAttribute("mailUsu", mailUsu);
               
               
                Pedido ped = new Pedido();
                request.getSession().setAttribute("pedido", ped);
                 return "/pedidoExito.jsp";
            } 
            
        
        else{
            request.getSession().setAttribute("pedidoVacio", true);
            return "/Checkout.jsp";
        }
        
    }
    
}
