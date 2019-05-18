/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.LineaPedido;
import entity.Pedido;
import java.util.ArrayList;
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
public class RegistrarSenaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<Pedido> pendientes;
        pendientes = (ArrayList)request.getSession().getAttribute("pendientes");
        ArrayList<LineaPedido> lineasP;
        float sena = Float.parseFloat(request.getParameter("senaPed"));
        
        CtrlPedido cp = new CtrlPedido();
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        Pedido pedActual = new Pedido();
        for(Pedido p : pendientes){
            if(p.getId() == idPedido){
                pedActual = p; }
            
        }
       
        lineasP = pedActual.getLineasPedido(); 
        
        pedActual.setSena(sena);
        try {
            cp.registrarSena(pedActual);
            pendientes = cp.obtenerPedidosPendientes();
        } catch (DonaCocaException ex) {
             request.setAttribute("ex", "Ha ocurrido un error registrando la seña");
             return "/pedidoDetalle.jsp"; 
        }
    
       request.getSession().setAttribute("pendientes", pendientes);
       request.setAttribute("ExitoSena", true);

       return "/pedidoDetalle.jsp";
    }
    
}
