/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import entity.Detalle;
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
                       if(selecc.length > 0){
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
        request.getSession().setAttribute("tortaVarActivas", tortaVarActivas);
        request.getSession().setAttribute("totalTor", total);
        return "/producto.jsp";
              


    }
    
}
