/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import data.DataTorta;
import entity.Detalle;
import entity.Torta;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class AgregarTortaComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
    Torta torta;
    CtrlTorta ctrlT= new CtrlTorta();
    CtrlDetalle ctrlD = new CtrlDetalle();
    DataTorta dt = new DataTorta();
    
    
  
        boolean existeTorta;
        
        //valida que la torta sea única
        try
        {
            existeTorta = ctrlT.existeTorta((String)request.getParameter("nomTor"));
                    
        }
        catch(DonaCocaException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return"/ABMTortas.jsp";
        }
        
        torta = new Torta();
        torta.setNombre(request.getParameter("nomTor"));
        torta.setPrecio(Float.parseFloat(request.getParameter("pvtaTor")));
        
        
        //Comparo todos los detalles con los seleccionados y los agrego a la torta
        ArrayList<Detalle> detalles = (ArrayList)request.getSession().getAttribute("detalles");
        String selecc[] = request.getParameterValues("detalles1");
        for(Detalle d: detalles)
        {
            for(int i=0; i<selecc.length;i++)  
            {
                if(d.getId()==Integer.parseInt(selecc[i]))
                {
                    torta.agregarDetalle(d);
                }
            }
        }    
        //agrego imagen a la torta
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
                    torta.setImagen(inputStream);
            }
        }
        catch (Exception ex)
        {
            request.setAttribute("ex","Error al cargar imagen");
            return ("/ABMTortas.jsp");
        }
        
        if(!existeTorta)
        {
            //Agrego la torta y actualizo la lista
            ArrayList<Torta> tortas = new ArrayList<>();
            try
            {
                ctrlT.agregarTorta(torta);
                tortas = ctrlT.obtenerTortas();          
            }
            catch(DonaCocaException ex)
            {
                request.setAttribute("ex", ex.getMessage());
                request.getSession().setAttribute("Scroll",true);
                return "/ABMTortas.jsp";
            }         
            request.getSession().setAttribute("listaTortas", tortas);
            request.setAttribute("ExitoTorta", true);
            request.setAttribute("tortaPorAgregar", null);
        }
        else
        {
            request.setAttribute("tortaPorAgregar", torta);
            request.setAttribute("ExitoTorta", false); 
        }
        
        request.getSession().setAttribute("Scroll",true);
        return "/ABMTortas.jsp";
    }  




    }
    

