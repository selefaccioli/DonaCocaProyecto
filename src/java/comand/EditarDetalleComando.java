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
public class EditarDetalleComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        CtrlDetalle ctrlD = new CtrlDetalle();
        Detalle detEditado = (Detalle) request.getSession().getAttribute("DetalleEdit");
        
        detEditado.setNombre(request.getParameter("nomDet"));
        Boolean eligeUsuario = (request.getParameter("eligeUsu")!=null);
        detEditado.setEligeUsuario(eligeUsuario);
        
        ArrayList<Detalle> detalles = null;
        
        try {
            ctrlD.editarDetalle(detEditado);
            detalles = ctrlD.obtenerDetalles();
        } catch (DonaCocaException ex) {
           request.setAttribute("ex", ex);
            return "/ABMDetalles.jsp";
        }
        
        
        request.getSession().setAttribute("detalles", detalles);
        request.getSession().setAttribute("DetalleEdit", detEditado);
        request.getSession().setAttribute("Scroll",true);
        request.setAttribute("ExitoDetalle", true);
        
        return"/ABMDetalles.jsp";



    }
    
}
