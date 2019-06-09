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
import logic.CtrlDetalle;
import logic.CtrlTorta;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class RedireccionarPedidoComando extends Comando{
    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

     int idPedido = Integer.parseInt(request.getParameter("idPedidoEdit"));
     ArrayList<Pedido> pedidos = (ArrayList)request.getSession().getAttribute("pendientes"); 
     ArrayList<LineaPedido> lineasP = new ArrayList<LineaPedido>();
     Pedido pedActual = null;
     for(Pedido p : pedidos){
            if(p.getId() == idPedido){
                pedActual = p; } 
       }
    if(pedActual != null){
       lineasP = pedActual.getLineasPedido();
    } 
        
    request.getSession().setAttribute("pedidoAmpliado", pedActual);
    request.getSession().setAttribute("lineasP", lineasP);
         
    return request.getParameter("destino");
    }
    
}
