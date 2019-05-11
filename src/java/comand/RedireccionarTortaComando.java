/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Detalle;
import entity.Torta;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlDetalle;
import logic.CtrlTorta;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class RedireccionarTortaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

     int id = Integer.parseInt(request.getParameter("idTortaEdit"));
      CtrlTorta ctrlT = new CtrlTorta();
      CtrlDetalle ctrlD = new CtrlDetalle();
      ArrayList<Detalle> detallesTorta = new ArrayList<Detalle>(); 
      Torta t= null;
        try {
            t = ctrlT.obtenerTorta(id);
        } catch (DonaCocaException ex) {
            Logger.getLogger(RedireccionarTortaComando.class.getName()).log(Level.SEVERE, null, ex);
        }
         if(t != null){
         try {
            detallesTorta = ctrlD.obtenerDetalles(t.getId());
         } catch (DonaCocaException ex) {
             Logger.getLogger(RedireccionarTortaComando.class.getName()).log(Level.SEVERE, null, ex);
         }
             request.getSession().setAttribute("tortaAmpliada", t);
             request.getSession().setAttribute("detallesTorta", detallesTorta);
         }
        
         
        return request.getParameter("destino");
    }
    
}
