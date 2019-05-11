/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import static com.sun.corba.se.spi.presentation.rmi.StubAdapter.request;
import entity.Cupon;
import entity.Detalle;
import entity.LineaPedido;
import entity.Parametro;
import java.util.ArrayList;
import javax.servlet.http.Cookie;
import entity.Pedido;
import entity.Torta;
import entity.Usuario;
import entity.Variante;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlCupon;
import logic.CtrlDetalle;
import logic.CtrlParametro;
import logic.CtrlPedido;
import logic.CtrlTorta;
import logic.CtrlUsuario;
import logic.CtrlVariante;
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
        CtrlUsuario ctrlU = new CtrlUsuario();
        CtrlPedido ctrlP = new CtrlPedido();
        CtrlCupon ctrlC = new CtrlCupon();
        CtrlDetalle ctrlD = new CtrlDetalle();
        CtrlVariante ctrlV = new CtrlVariante();
        CtrlParametro ctrlPar = new CtrlParametro();
        ArrayList<Torta> listaTortas=new ArrayList<Torta>();
        ArrayList<Usuario> listaUsuarios = new ArrayList<Usuario>();
        ArrayList<Torta> tortasCarrusel= new ArrayList<Torta>();
        ArrayList<Pedido> listaPedPendientes = new ArrayList<Pedido>();
        ArrayList<Cupon> listaCupones = new ArrayList<Cupon>();
        ArrayList<Variante> variantes = new  ArrayList<Variante>();
        ArrayList<Detalle> detalles = new  ArrayList<Detalle>();
        Parametro parametros = new Parametro();
       
          try {
           listaCupones = ctrlC.obtenerCupones();
           listaUsuarios = ctrlU.obtenerUsuarios();
           listaPedPendientes = ctrlP.obtenerPedidosPendientes();
           listaTortas = ctrlT.obtenerTortas();
           variantes = ctrlV.obtenerVariantes();
           detalles = ctrlD.obtenerDetalles();
           parametros = ctrlPar.obtenerParametros();
       } catch (DonaCocaException ex) {
           request.getSession().setAttribute("ex", ex.getMessage());
           return "/home.jsp";
       }
        
        
        
       
       request.getSession().setAttribute("listaUsuarios", listaUsuarios);
       request.getSession().setAttribute("pendientes", listaPedPendientes);
        request.getSession().setAttribute("listaTortas", listaTortas); 
        request.getSession().setAttribute("variantes", variantes);
        request.getSession().setAttribute("detalles", detalles);
        request.getSession().setAttribute("parametros", parametros);
        request.getSession().setAttribute("listaCupones", listaCupones);
               
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
        
        
        
        
        return "/home.jsp";
        
    }
}

 