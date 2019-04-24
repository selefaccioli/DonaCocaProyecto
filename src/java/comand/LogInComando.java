/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Pedido;
import entity.Usuario;
import java.util.ArrayList;
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
        
        Usuario usu = null;
        
        try{
            usu = ctrlU.obtenerUsuario(nomUsu, contra);
        }
        catch (DonaCocaException ex){
            request.setAttribute("ex", ex.getMessage());
            return "/login.jsp";
        }
        
        Pedido p = (Pedido)request.getSession().getAttribute("pedido");
        
       
        
        if(usu != null)
        {      
            
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
