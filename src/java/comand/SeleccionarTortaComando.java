/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Torta;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author selef
 */
public class SeleccionarTortaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        int idTortaEdit = Integer.parseInt( request.getParameter("idTortaEdit"));
        
        if(idTortaEdit!=0)
        {
            ArrayList<Torta> tortas = (ArrayList<Torta>)request.getSession().getAttribute("listaTortas");
            Torta tortaEdit=null;
            for(Torta tor: tortas)
            {
                if(idTortaEdit==tor.getId())
                    tortaEdit=tor;         
            }
            request.getSession().setAttribute("TortaEdit", tortaEdit);
        }
        else
        {
            request.getSession().setAttribute("TortaEdit", null);
        }
        
        request.getSession().setAttribute("Scroll",true);
        
        return "/ABMTortas.jsp"; 
    }
    
}
