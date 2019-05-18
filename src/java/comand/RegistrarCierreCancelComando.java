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
public class RegistrarCierreCancelComando extends Comando{

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
        
        if(request.getParameter("cerrar") != null){
            pedAEnviar.setEstado("Cerrado");
           
        }
        else{
            pedAEnviar.setEstado("Cancelado");
        }
        
        
        try
        {
            ctrlP.registrarEnvio(pedAEnviar);
        }
        catch(DonaCocaException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/pedidoDetalle.jsp";     
        }

        try
        {
            pendientes=ctrlP.obtenerPedidosPendientes();
        }
        catch(DonaCocaException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/pedidoDetalle.jsp";    
        }
        
        request.getSession().setAttribute("pendientes",pendientes);
        if(pedAEnviar.getEstado().equals("Cancelado")){
            request.setAttribute("ExitoCancel", true);
        }
        else{
            request.setAttribute("ExitoCierre", true);
        }
       
        
        return "/pedidoDetalle.jsp";


    }
    
}
