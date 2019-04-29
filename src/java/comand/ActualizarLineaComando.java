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
public class ActualizarLineaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        int idTorta = Integer.parseInt(request.getParameter("idTorta"));
        String cantStr=request.getParameter("cantidad");
        
        if(cantStr.matches("[1-9][0-9]*"))
        {
            int cant = Integer.parseInt(cantStr);
            Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
            for(LineaPedido lp: pedido.getLineasPedido() )
            {
                if(lp.getTorta().getId() == idTorta )
                    lp.setCantidad(cant);
            }
        }
        else
            request.getSession().setAttribute("cantidadInvalida", true);

        return("/carro.jsp");




    }
    
}
