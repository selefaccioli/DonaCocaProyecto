/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Cupon;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author selef
 */
public class SeleccionarCuponComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        int idCuponEdit = Integer.parseInt( request.getParameter("idCuponEdit"));
        
        if(idCuponEdit!=0)
        {
            ArrayList<Cupon> cupones = (ArrayList<Cupon>)request.getSession().getAttribute("listaCupones");
            Cupon cuponEdit=null;
            for(Cupon cup: cupones)
            {
                if(idCuponEdit==cup.getId())
                    cuponEdit=cup;         
            }
            request.getSession().setAttribute("CuponEdit", cuponEdit);
        }
        else
        {
            request.getSession().setAttribute("CuponEdit", null);
        }
        
        request.getSession().setAttribute("Scroll",true);
        
        return "/ABMCupones.jsp"; 


    }
    
}
