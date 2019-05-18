/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import data.DataTorta;
import entity.Detalle;
import entity.LineaPedido;
import entity.Pedido;
import entity.Torta;
import entity.Variante;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlVariante;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class CalcularPrecioComprarComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        
        ArrayList<Detalle> detallesTorta = (ArrayList)request.getSession().getAttribute("detallesTorta");
        Torta t = (Torta)request.getSession().getAttribute("tortaAmpliada");
        CtrlVariante ctrlV = new CtrlVariante();
        ArrayList<Variante> varianteDetalle = new ArrayList<Variante>();
        ArrayList<Variante> variantesActivas = new ArrayList<Variante>();
        Torta tortaVarActivas = new Torta();
      if(request.getParameter("calcPrecio") != null){
           float total =0;
        
        if(detallesTorta.size() > 0){
             for(Detalle d: detallesTorta){
                 try {
                     varianteDetalle = ctrlV.obtenerVariantesDetalleTorta(d.getId(), t.getId());
                 } catch (DonaCocaException ex) {
                     request.setAttribute("ex", ex.getMessage());
                     return "/productos.jsp";
                 }
                 if(d.getMultiple()){
                       String selecc[] = request.getParameterValues("variantesD");
                       if(selecc !=  null){
                          for(Variante vd: varianteDetalle){
                                            
                            for(int i=0; i<selecc.length;i++){  
                                       
                            if(vd.getId()==Integer.parseInt(selecc[i])){
                      
                                    total = total + vd.getPrecio();
                                    variantesActivas.add(vd);
                            }        
                            }
                            }  
                       }
                       
               } else{
                     try {
                         Variante selecVar = ctrlV.obtenerVariante(Integer.parseInt(request.getParameter(d.getNombre())));
                         total = total + selecVar.getPrecio();
                         variantesActivas.add(selecVar);
                     } catch (DonaCocaException ex) {
                        request.setAttribute("ex", ex.getMessage());
                        return "/productos.jsp";
                     }
                     
                 }
        }
      
           
           
       }
        tortaVarActivas.setVariantes(variantesActivas);
        tortaVarActivas.setId(t.getId());
        tortaVarActivas.setActivo(t.isActivo());
        tortaVarActivas.setRutaImg(t.getRutaImg());
        tortaVarActivas.setNombre(t.getNombre());
        tortaVarActivas.setPrecio(total);
        request.getSession().setAttribute("tortaVarActivas", tortaVarActivas);
        request.getSession().setAttribute("totalTor", total);
        return "/producto.jsp";
      } else{
        Torta tortaAgregar = (Torta)request.getSession().getAttribute("tortaVarActivas");  
        int idTorta = tortaAgregar.getId();
        Pedido pedido = (Pedido)request.getSession().getAttribute("pedido");
        int lineaExiste = 0;
   
        for(LineaPedido lp : pedido.getLineasPedido()){
        
        if(lp.getTorta().getId() == idTorta){  //ya estaba en el carro 
            lp.setCantidad(lp.getCantidad() + 1);
            lp.setSubtotal(tortaAgregar.getPrecio() * lp.getCantidad());
            request.getSession().setAttribute("exitoTortaAgregada", true);
            
            lineaExiste = 1;
        }
        
        
    }
    
    
    
    if(lineaExiste == 0){
        DataTorta dt = new DataTorta();
        LineaPedido lp = new LineaPedido();
        
        try {
            Torta tnueva = dt.obtenerTorta(idTorta);
            lp.setCantidad(1); //porque no estaba antes en el carro va a ser 1
            lp.setTorta(tortaAgregar);
            lp.setSubtotal(tortaAgregar.getPrecio());
            pedido.setLinea(lp);
            request.getSession().setAttribute("exitoTortaAgregada", true);
            
        } catch (DonaCocaException ex) {
           request.getSession().setAttribute("exitoTortaAgregada", false);
        }
    }
    
    request.getSession().setAttribute("pedido", pedido);
    
   

    return "/producto.jsp";
      }
       
           


    }
    
}
