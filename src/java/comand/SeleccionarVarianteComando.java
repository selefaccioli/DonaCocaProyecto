/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Variante;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlVariante;

/**
 *
 * @author selef
 */
public class SeleccionarVarianteComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        
        CtrlVariante ctrlV = new CtrlVariante();
        int idVarianteEdit = Integer.parseInt(request.getParameter("idVarianteEdit"));
        
        if(idVarianteEdit != 0){
            ArrayList<Variante> variantes = (ArrayList<Variante>) request.getSession().getAttribute("variantes");
            Variante varianteEdit = null;
            
            for(Variante v: variantes){
                
                if(v.getId() == idVarianteEdit){
                    
                    varianteEdit = v;
                }
            }
            
            request.getSession().setAttribute("VarianteEdit", varianteEdit);
            
        }
        else{
            request.getSession().setAttribute("VarianteEdit", null);
        }
        request.getSession().setAttribute("Scroll",true);
        
        return "/ABMVariantes.jsp";





    }
    
}
