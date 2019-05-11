/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Variante;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlDetalle;
import logic.CtrlVariante;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class AgregarVarianteComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlVariante ctrlV = new CtrlVariante();
        CtrlDetalle ctrlD = new CtrlDetalle();
            
            boolean existeVariante = true;
            
        try {
            existeVariante = ctrlV.existeVariante(request.getParameter("descVar"));
        } catch (DonaCocaException ex) {
           
            request.setAttribute("ex", ex.getMessage());
            return "/ABMVariantes.jsp";
        }
            
            
        Variante varNuevo = new Variante();
        
        varNuevo.setDescripcion(request.getParameter("descVar"));
        varNuevo.setPrecio(Float.parseFloat(request.getParameter("precVar")));
        try {
            varNuevo.setDetalle(ctrlD.obtenerDetalle(Integer.parseInt(request.getParameter("detVar"))));
        } catch (DonaCocaException ex) {
            request.setAttribute("ex", ex.getMessage());
            return "/ABMVariantes.jsp";
        }
      
        
        ArrayList<Variante> variantes;
        
        if(!existeVariante){
                try {
                    ctrlV.registrarVariante(varNuevo);
                    variantes = ctrlV.obtenerVariantes();
                } catch (DonaCocaException ex) {
                    request.setAttribute("ex", ex.getMessage());
                    return "/ABMVariantes.jsp";
                }
                
                
          
            request.getSession().setAttribute("variantes", variantes);
            request.setAttribute("ExitoVariante", true);
        }
        else{
            request.setAttribute("variantePorAgregar", varNuevo);
            request.setAttribute("ExitoVariante", false); 
    }
        request.getSession().setAttribute("Scroll",true);
        return "/ABMVariantes.jsp";


    }
    
}
