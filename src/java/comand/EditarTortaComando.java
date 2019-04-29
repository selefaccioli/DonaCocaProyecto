/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Detalle;
import entity.Torta;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import logic.CtrlDetalle;
import logic.CtrlTorta;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class EditarTortaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {

        CtrlTorta ct = new CtrlTorta();
        CtrlDetalle cd = new CtrlDetalle();
        
        Torta tortaEditada = new Torta();
        ArrayList<Detalle> detalles = new ArrayList<Detalle>();
        
        try {
            detalles = cd.obtenerDetalles();
        } catch (DonaCocaException ex) {
            request.setAttribute("ex", ex.getMessage());
            return "/ABMTortas.jsp";
        }
 
        tortaEditada.setId(Integer.parseInt(request.getParameter("ID")));
        tortaEditada.setNombre(request.getParameter("nomTor"));
        tortaEditada.setPrecio(Float.parseFloat(request.getParameter("pvtaTor")));
        
        
         Part imagen = null;
        try{
            imagen = request.getPart("imgTor");
        }
        catch  (Exception ex)
        {
            request.setAttribute("ex","Error al cargar imagen");
            return ("/ABMTortas.jsp");
        }
       
        try
        {
            if(request.getPart("imgTor")!=null)
            {
                
                InputStream inputStream = imagen.getInputStream();
                if(inputStream!=null)
                    tortaEditada.setImagen(inputStream);
            }
        }
        catch (Exception ex)
        {
            request.setAttribute("ex","Error al cargar imagen");
            return ("/ABMTortas.jsp");
        }
        
        String selecc[] = request.getParameterValues("detalles1");
        for(Detalle d: detalles)
        {
            for(int i=0; i<selecc.length;i++)  
            {
                if(d.getId()==Integer.parseInt(selecc[i]))
                {
                    tortaEditada.agregarDetalle(d);
                }
            }
        }    
        
        
         ArrayList<Torta> tortas = new ArrayList<>();
            try
            {
                ct.actualizarTorta(tortaEditada);
                tortas = ct.obtenerTortas();          
            }
            catch(DonaCocaException ex)
            {
                request.setAttribute("ex", ex.getMessage());
                request.getSession().setAttribute("Scroll",true);
                return "/ABMTortas.jsp";
            }         
            request.getSession().setAttribute("listaTortas", tortas);
            request.getSession().setAttribute("TortaEdit", tortaEditada);
            request.getSession().setAttribute("Scroll",true);
            request.setAttribute("ExitoTorta", true);
            
            return "/ABMTortas.jsp";
    }
    
}
