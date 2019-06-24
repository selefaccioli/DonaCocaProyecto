/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Cupon;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlCupon;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class EditarCuponComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlCupon ctrlC = new CtrlCupon();
        Cupon cupEditado = (Cupon) request.getSession().getAttribute("CuponEdit");
        
     
        Boolean esActivo = (request.getParameter("activo")!=null);
        cupEditado.setCodigo(request.getParameter("codCup"));
        cupEditado.setId(Integer.parseInt(request.getParameter("ID")));
        cupEditado.setActivo(esActivo);
        cupEditado.setPorcDescuento(Float.parseFloat(request.getParameter("porcCup")));
        
        
        
        ArrayList<Cupon> cupones=null;
        
        try {
            ctrlC.actualizarCupon(cupEditado);
            cupones = ctrlC.obtenerCupones();
        } catch (DonaCocaException ex) {
           request.setAttribute("ex", ex.getMessage());
           return "/ABMCupones.jsp";
        } catch (SQLException ex) {
            Logger.getLogger(EditarCuponComando.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.getSession().setAttribute("listaCupones", cupones);
        request.getSession().setAttribute("cuponEdit", cupEditado);
        request.getSession().setAttribute("Scroll",true);
        request.setAttribute("ExitoCupon", true);
        
        return"/ABMCupones.jsp";

    }


    
    
}
