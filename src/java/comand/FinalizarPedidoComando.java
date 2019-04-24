/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import data.DataPedido;
import entity.Pedido;
import entity.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class FinalizarPedidoComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        
        Pedido p = (Pedido)request.getSession().getAttribute("pedido");
        
        if(p.getLineasPedido().size() > 0){
            if(request.getSession().getAttribute("usuario") == null ){
                request.getSession().setAttribute("usuarioNoLogueado", true);
                return "/login.jsp";
            }
            else{
                Usuario u = (Usuario)request.getSession().getAttribute("usuario");
                DataPedido dp = new DataPedido();
                p.setUsuario(u);
                p.setEstado("Pendiente");
                
                try{
                    dp.registrarPedido(p);
                    
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
