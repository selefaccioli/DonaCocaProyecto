/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Detalle;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlDetalle;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class AgregarDetalleComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
            CtrlDetalle ctrlD = new CtrlDetalle();
            
            boolean existeDetalle = true;
            
        try {
            existeDetalle = ctrlD.existeDetalle(request.getParameter("nomDet"));
        } catch (DonaCocaException ex) {
           
            request.setAttribute("ex", ex.getMessage());
            return "/ABMDetalles.jsp";
        }
            
            
        Detalle detNuevo = new Detalle();
        
        detNuevo.setNombre(request.getParameter("nomDet"));
        Boolean multiple = (request.getParameter("multipleUsu")!=null);
        detNuevo.setMultiple(multiple);
        detNuevo.setEligeUsuario(true);
        
        ArrayList<Detalle> detalles;
        
        if(!existeDetalle){
                try {
                    ctrlD.registrarDetalle(detNuevo);
                    detalles = ctrlD.obtenerDetalles();
                } catch (DonaCocaException ex) {
                    request.setAttribute("ex", ex.getMessage());
                    return "/ABMDetalles.jsp";
                }
                
                
          
            request.getSession().setAttribute("detalles", detalles);
            request.setAttribute("ExitoDetalle", true);
        }
        else{
            request.setAttribute("detallePorAgregar", detNuevo);
            request.setAttribute("ExitoDetalle", false); 
    }
        request.getSession().setAttribute("Scroll",true);
        return "/ABMDetalles.jsp";

}
}
