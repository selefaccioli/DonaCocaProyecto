/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Torta;
import entity.Variante;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import logic.CtrlTorta;
import logic.CtrlVariante;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class EliminarTortaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

       int idTortaElim = Integer.parseInt( request.getParameter("idTortaElim"));
       CtrlTorta ctrlT = new CtrlTorta();
       Torta tortaElim = new Torta();
       ArrayList<Torta> tortas = new ArrayList<>();
        
        if(idTortaElim!=0)
        {
           try {
               tortaElim = ctrlT.obtenerTorta(idTortaElim);
               ctrlT.eliminarTorta(tortaElim);
               tortas = ctrlT.obtenerTortas(); 
               
           } catch (DonaCocaException ex) {
               request.setAttribute("ex", ex.getMessage());
               return"/ABMTortas.jsp";
           }
          
           
            request.getSession().setAttribute("listaTortas", tortas);
            request.setAttribute("ExitoTortaElim", true);
        }
        else
        {
            request.getSession().setAttribute("TortaElim", null);
            request.setAttribute("ExitoTortaElim", true);
        }
        return "/ABMTortas.jsp";
    }



    }
    

