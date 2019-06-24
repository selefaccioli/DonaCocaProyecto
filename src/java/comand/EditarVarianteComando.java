/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Variante;
import java.sql.SQLException;
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
public class EditarVarianteComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlVariante ctrlV = new CtrlVariante();
        CtrlDetalle ctrlD = new CtrlDetalle();
        Variante varEditado = (Variante) request.getSession().getAttribute("VarianteEdit");
        
        
        varEditado.setDescripcion(request.getParameter("descVar"));
        varEditado.setPrecio(Float.parseFloat(request.getParameter("precVar")));
       
        
         try {
            varEditado.setDetalle(ctrlD.obtenerDetalle(Integer.parseInt(request.getParameter("detVar"))));
        } catch (DonaCocaException ex) {
            request.setAttribute("ex", ex.getMessage());
            return "/ABMVariantes.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(EditarVarianteComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ArrayList<Variante> variantes = null;
        
        try {
            ctrlV.editarVariante(varEditado);
            variantes = ctrlV.obtenerVariantes();
        } catch (DonaCocaException ex) {
           request.setAttribute("ex", ex);
            return "/ABMVariantes.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(EditarVarianteComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        request.getSession().setAttribute("variantes", variantes);
        request.getSession().setAttribute("VarianteEdit", varEditado);
        request.getSession().setAttribute("Scroll",true);
        request.setAttribute("ExitoVariante", true);
        
        return"/ABMVariantes.jsp";




    }
    
}
