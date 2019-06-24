/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Pedido;
import entity.Usuario;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlPedido;
import logic.CtrlUsuario;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class LogInComando extends Comando{
    CtrlUsuario ctrlU = new CtrlUsuario();

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        String nomUsu = request.getParameter("nomUsu");
        String contra = request.getParameter("contra");
        Boolean recordar = (request.getParameter("recordarUsu")!=null);
        CtrlPedido ctrlP = new CtrlPedido();
        
        Usuario usu = null;
        
        try{
            usu = ctrlU.obtenerUsuario(nomUsu, contra);
        }
        catch (DonaCocaException ex){
            request.setAttribute("ex", ex.getMessage());
            return "/login.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(LogInComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Pedido p = (Pedido)request.getSession().getAttribute("pedido");
        
       
        
        if(usu != null)
        {      
           
        if(recordar)
            {
                Cookie recordarNombre = new Cookie("nomUsuarioDonaCoca", nomUsu);
                Cookie recordarContra = new Cookie("contraDonaCoca", contra);
                recordarNombre.setMaxAge(356*24*60*60);
                recordarContra.setMaxAge(365*24*60*60);
                recordarContra.setPath("/");
                recordarNombre.setPath("/");
                response.addCookie(recordarNombre);
                response.addCookie(recordarContra);
            }     
            
            
        if(usu.isEsAdmin())
            {
                ArrayList<Pedido> pendientes = null;
                try
                {
                    pendientes = new CtrlPedido().obtenerPedidosPendientes();
                }
                catch(DonaCocaException ex)
                {
                    request.setAttribute("ex",ex.getMessage());
                    return "/login.jsp";
                } catch (SQLException ex) {
                Logger.getLogger(LogInComando.class.getName()).log(Level.SEVERE, null, ex);
            }
                
                request.getSession().setAttribute("pendientes", pendientes);
            }
                
        request.getSession().setAttribute("usuario", usu);
        request.getSession().setAttribute("exitoLogin", true);   
        
        if( request.getSession().getAttribute("usuarioNoLogueado") != null ){
            request.getSession().setAttribute("usuarioNoLogueado", null);
            return("/carro.jsp");
            
        }
        
        return "/home.jsp";
        
        }
        
        else{
            return "/login.jsp";
        }
    }
    
}
