/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import entity.Detalle;
import entity.LineaPedido;
import entity.Parametro;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import entity.Pedido;
import entity.Torta;
import entity.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlDetalle;
import logic.CtrlParametro;
import logic.CtrlTorta;
import logic.CtrlUsuario;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class InicioComando extends Comando{
   public String ejecutar(HttpServletRequest request, HttpServletResponse response)
    {    
    

//crea pedido inicial
        Pedido pedido= new Pedido(); 
        request.getSession().setAttribute("pedido", pedido);
                
        // carga de tortas del home, carrusel y tablita de abajo
        CtrlTorta ctrlT= new CtrlTorta();
        ArrayList<Torta> listaTortas=new ArrayList<Torta>();
        ArrayList<Torta>tortasCarrusel= new ArrayList<Torta>();

        try
        {   
            listaTortas = ctrlT.obtenerTortas();
           // listaTortas.add(ctrlT.obtenerDetalle(1, 0, 2));
           /* listaTortas.add(ctrlT.obtenerDetalle(2, 0, 4));
            listaTortas.add(ctrlT.obtenerDetalle(3, 0, 4));*/
            
            tortasCarrusel = ctrlT.obtenerTortas(1, 3); 
        }
        catch(DonaCocaException ex)
        {
            request.getSession().setAttribute("ex", ex.getMessage());
            return "/home.jsp";
        }
        request.getSession().setAttribute("listaTortas", listaTortas);
        request.getSession().setAttribute("tortasCarrusel", tortasCarrusel);
        
        //carga de detalles
        CtrlDetalle ctrlD = new CtrlDetalle();
        try
        {
            ArrayList<Detalle> detalles = ctrlD.obtenerDetalles();
            request.getSession().setAttribute("detalles", detalles);
        }
        catch(DonaCocaException ex)
        {
            request.setAttribute("ex", ex.getMessage());
            return "/home.jsp";
        }
               
        //mantenerme conectado
        String nomUsu = null;
        String contra = null;
        if( request.getCookies()!=null && request.getSession().getAttribute("usuario")==null )
        {
            Cookie[] cookies = request.getCookies();
            for(Cookie c:cookies)
            {
                if(c.getName().equals("nomUsuarioDonaCoca"))
                    nomUsu=c.getValue();
                if(c.getName().equals("contraDonaCoca"))
                    contra=c.getValue();
            }
            if(nomUsu!=null && contra!=null)
            {
                CtrlUsuario ctrlU = new CtrlUsuario();
                Usuario usu;
                try
                {
                    usu= ctrlU.obtenerUsuario(nomUsu, contra);
                }
                catch(DonaCocaException ex)
                {
                    request.getSession().setAttribute("ex", ex.getMessage());
                    return "/home.jsp";
                }
                
                if(usu!=null)
                {
                    request.getSession().setAttribute("usuario", usu);
                    request.getSession().setAttribute("exitoLogin", true);
                }
            }
        }
        
        //carga de parámetros desde la BD
        CtrlParametro ctrlP = new CtrlParametro();
        Parametro parametros = new Parametro();
        try
        {
            parametros = ctrlP.obtenerParametros();
        } 
        catch (DonaCocaException ex) 
        {
            request.getSession().setAttribute("ex", ex.getMessage());
            return "/home.jsp";
        }
        request.getSession().setAttribute("parametros", parametros);
        
        return "/home.jsp";
        
    }
}

 