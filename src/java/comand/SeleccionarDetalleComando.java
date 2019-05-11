/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Detalle;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlDetalle;

/**
 *
 * @author selef
 */
public class SeleccionarDetalleComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {


        CtrlDetalle ctrlD = new CtrlDetalle();
        int idDetalleEdit = Integer.parseInt(request.getParameter("idDetalleEdit"));
        
        if(idDetalleEdit != 0){
            ArrayList<Detalle> detalles = (ArrayList<Detalle>) request.getSession().getAttribute("detalles");
            Detalle detalleEdit = null;
            
            for(Detalle d: detalles){
                
                if(d.getId() == idDetalleEdit){
                    
                    detalleEdit = d;
                }
            }
            
            request.getSession().setAttribute("DetalleEdit", detalleEdit);
            
        }
        else{
            request.getSession().setAttribute("DetalleEdit", null);
        }
        request.getSession().setAttribute("Scroll",true);
        
        return "/ABMDetalles.jsp";


    }




    
    
}
