/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.LineaPedido;
import entity.Pedido;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author selef
 */
public class EliminarLineaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        Pedido ped= (Pedido)request.getSession().getAttribute("pedido");
        Integer idTorta= Integer.parseInt(request.getParameter("idTortaEliminar"));
        LineaPedido l = null;
        
        for(LineaPedido lp: ped.getLineasPedido())
        {
            if(lp.getTorta().getId() == idTorta )
            {  
                l=lp;
                break;
            }
        }
        
        ped.getLineasPedido().remove(l);
      
        request.getSession().setAttribute("pedido", ped);
        return("/carro.jsp");



    }
    
}
