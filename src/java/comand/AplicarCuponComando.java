/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Cupon;
import java.sql.SQLException;
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
public class AplicarCuponComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlCupon ctrlC = new CtrlCupon();
        Cupon cupon = null;
        
        
        String codigo = request.getParameter("cuponDto");
        
        try {
           cupon = ctrlC.obtenerCupon(codigo);
        } catch (DonaCocaException ex) {
            request.setAttribute("ex","Error al buscar existecupon");
            return ("/carro.jsp");
        } catch (SQLException ex) {
            Logger.getLogger(AplicarCuponComando.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(cupon != null){
            request.getSession().setAttribute("cuponActual", cupon);
            
        }
        else{
            request.getSession().setAttribute("cuponFallido", true);
        }

        return "/carro.jsp";




    }
    
}
