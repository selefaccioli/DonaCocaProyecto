/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.LineaPedido;
import entity.Pedido;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlPedido;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class RegistrarEnvioComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlPedido ctrlP= new CtrlPedido();
        int idPed = Integer.parseInt(request.getParameter("idPedido"));
        ArrayList<Pedido> pendientes = (ArrayList)request.getSession().getAttribute("pendientes");
        Pedido pedAEnviar = new Pedido();
        
        
        for(Pedido p:pendientes)
        {
            if(p.getId()==idPed)
                pedAEnviar=p;      
        } 
        
       
        pedAEnviar.setEstado("Cerrado");
        
        try
        {
            ctrlP.registrarEnvio(pedAEnviar);
        }
        catch(DonaCocaException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/envios.jsp";     
        }

        try
        {
            pendientes=ctrlP.obtenerPedidosPendientes();
        }
        catch(DonaCocaException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/envios.jsp";    
        }
        
        request.getSession().setAttribute("pendientes",pendientes);
        request.setAttribute("ExitoEnvio", true);
        
        return "/envios.jsp";


    }
    
}
