/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import data.DataTorta;
import entity.LineaPedido;
import entity.Pedido;
import entity.Torta;
import entity.Usuario;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlUsuario;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class AgregarLineaPedidoComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        
    int idTorta = Integer.parseInt(request.getParameter("idTorta"));
    Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
    int lineaExiste = 0;
   
        for(LineaPedido lp : pedido.getLineasPedido()){
        
        if(lp.getTorta().getId() == idTorta){  //ya estaba en el carro 
            lp.setCantidad(lp.getCantidad() + 1);
            request.getSession().setAttribute("exitoTortaAgregada", true);
            
            lineaExiste = 1;
        }
        
        
    }
    
    
    
    if(lineaExiste == 0){
        DataTorta dt = new DataTorta();
        LineaPedido lp = new LineaPedido();
        
        try {
            Torta t = dt.obtenerTorta(idTorta);
            lp.setCantidad(1); //porque no estaba antes en el carro va a ser 1
            lp.setTorta(t);
            pedido.setLinea(lp);
            request.getSession().setAttribute("exitoTortaAgregada", true);
            
        } catch (DonaCocaException ex) {
           request.getSession().setAttribute("exitoTortaAgregada", false);
        }
    }
    
    request.getSession().setAttribute("pedido", pedido);
    
    if(request.getParameter("provieneDeTorta") != null){
        return "/torta.jsp";
    }

    return "/home.jsp";
    }
    
}
