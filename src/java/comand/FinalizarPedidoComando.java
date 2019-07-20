/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import data.DataPedido;
import entity.LineaPedido;
import entity.Pedido;
import entity.Usuario;
import entity.Variante;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logic.CtrlPedido;
import logic.CtrlUsuario;
import logic.SendMail;
import util.DonaCocaException;

/**
 *
 * @author selef
 */
public class FinalizarPedidoComando extends Comando{

    @Override
    public String ejecutar(HttpServletRequest request, HttpServletResponse response) {
        CtrlUsuario ctrlU = new CtrlUsuario();
        Pedido p = (Pedido)request.getSession().getAttribute("pedido");
        String aclaraciones = request.getParameter("aclaraciones");
        String envio = request.getParameter("envio");
        float total = (float) request.getSession().getAttribute("total");
        Date fechaNacimiento = null;
        String mailUsu;
        String fechn = request.getParameter("fecNac");
        
        p.setAclaraciones(aclaraciones);
        
        double subtotal = 0.0f;
              for(LineaPedido linea: p.getLineasPedido()){
          
          subtotal = subtotal + linea.getSubtotal()*linea.getCantidad();
              } 
        SimpleDateFormat formato =  new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        if(fechn != null){
        try {
            fechaNacimiento = formato.parse(fechn);
        } catch (ParseException ex) {
           request.setAttribute("ex", "Ha ocurrido un error con la fecha de nacimiento");
           return "/Checkout.jsp";
        } }
        
        
        try{
            fecha = formato.parse(request.getParameter("fechaEntrega"));              
            p.setFechaEntrega(new java.sql.Date(fecha.getTime()));
           }catch(ParseException e){
                 request.setAttribute("ex", "Ha ocurrido un error con la fecha");
                 return "/Checkout.jsp";} 
        
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(p.getFechaPedido()); // Configuramos la fecha que se recibe
        calendar.add(Calendar.DAY_OF_YEAR, 7);  
        Date fechaEntrega = calendar.getTime();
        
        boolean fechasok = false;
        
       
            if (fecha.compareTo(fechaEntrega) > 0) {
            fechasok = true; }
            else{
                request.getSession().setAttribute("fechaIncorrecta", true);
                return "/Checkout.jsp";
            }
            
        if(p.getLineasPedido().size() > 0){
            if(request.getSession().getAttribute("usuario") == null ){
                String nombre = request.getParameter("nomUsu");
                String apellido= request.getParameter("apeUsu");
                String mail = request.getParameter("mailUsu");
                String direcc = request.getParameter("direcUsu");
                String tel = request.getParameter("telUsu");
                
                Usuario usuNuevo = new Usuario();
                
                usuNuevo.setNombre(nombre);
                usuNuevo.setActivo(true);
                usuNuevo.setApellido(apellido);
                usuNuevo.setDireccion(direcc);
                usuNuevo.setTelefono(tel);
                usuNuevo.setMail(mail);
                mailUsu = usuNuevo.getMail();
                usuNuevo.setConocimiento(request.getParameter("conocimiento"));
                usuNuevo.setFechaNacimiento(fechaNacimiento);
                
                try {
                    ctrlU.registrarUsuario(usuNuevo);
                    p.setUsuario(usuNuevo);
                    
                } catch (DonaCocaException ex) {
                    request.setAttribute("ex", ex.getMessage());
                    return "/Checkout.jsp";
                } catch (SQLException ex) {
                    Logger.getLogger(FinalizarPedidoComando.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            else{
                    
                    
                Usuario u = (Usuario)request.getSession().getAttribute("usuario");
                mailUsu = u.getMail();
                p.setUsuario(u); 
            }
                
                
                CtrlPedido ctrlP = new CtrlPedido();
                
                if(envio.equals("aDomicilio")){
                    p.setEnvioDomicilio(true);
                }
                else{
                    p.setEnvioDomicilio(false);
                }
                p.setEstado("Pendiente");
                p.setTotal(total);
               
                
                
                try{
                    ctrlP.registrarPedido(p);
                }
                catch (DonaCocaException ex){
                    request.setAttribute("ex", ex.getMessage());
                    return "/Checkout.jsp";
                    
                } catch (SQLException ex) {
                Logger.getLogger(FinalizarPedidoComando.class.getName()).log(Level.SEVERE, null, ex);
            }
                String mje;
           
 
 
 mje = "<style type=\"text/css\">\n" +
"  body,\n" +
"  html, \n" +
"  .body {\n" +
"    background: #f3f3f3 !important;\n" +
"  }\n" +
"</style>\n" +
"<!-- move the above styles into your custom stylesheet -->\n" +
"\n" +
"<spacer size=\"16\"></spacer>\n" +
"\n" +
"<container>\n" +
"\n" +
"  <spacer size=\"16\"></spacer>\n" +
"\n" +
"  <row>\n" +
"    <columns>\n" +
"      <h1>Muchas gracias por su compra!</h1>\n" +
"      <p>A continuación le dejamos el detalle de su pedido.</p>\n" +
"\n" +
"      <spacer size=\"16\"></spacer>\n" +
"\n" +
"      <callout class=\"secondary\">\n" +
"        <row>\n" +
"          <columns large=\"6\">\n" +
"            <p>\n" +
"              <strong>Datos del usuario</strong><br/>\n";

     mje = mje + "Nombre y Apellido: " + p.getUsuario().getNombre() + " " + p.getUsuario().getApellido() + "<br/>"+
                 "Mail: " + p.getUsuario().getMail() + "<br/>"+ 
                 "Teléfono: " + p.getUsuario().getTelefono() + "<br/>"+ "</p>";
 

 mje = mje + 
"        </row>\n" +
"      </callout>\n" +
         
"      <callout class=\"secondary\">\n" +
"        <row>\n" +
"          <columns large=\"6\">\n" +
"            <p>\n" +
"              <strong>Metodo de envío</strong><br/>\n";
 if(p.getEnvioDomicilio()){
     mje = mje + "Envio a domicilio. Dirección: <br/>" + p.getUsuario().getDireccion() + ", Reconquista.</p>";
 }
 else{
      mje = mje + "Retira en local</p> ";
 }
 mje = mje + 
"        </row>\n" +
"      </callout>\n" +
"\n" +
"      <h4>Detalles de la orden</h4>\n" +
"\n" +
 "      <callout class=\"secondary\">\n" +
"        <row>\n" +
"          <columns large=\"6\">\n" +
"            <p>\n" +
"              <strong>Aclaraciones adicionales</strong><br/>\n";

     mje = mje + p.getAclaraciones() + "</p><br/>";
 

 mje = mje + 
"        </row>\n" +
"      </callout>\n" +        
"      <table>\n" +
"        <tr>"
         + "<th>Nombre Producto</th>"
         + "<th>Detalles</th>"
         + "<th>Precio unitario</th>" 
         + "<th>Cantidad</th>" 
         + "<th>Precio total</th>" 
         + "</tr>\n";
 for(LineaPedido lp : p.getLineasPedido()){
     mje = mje + "<tr><td>" + lp.getTorta().getNombre() + "</td>";
     mje= mje + "<td>"; 
     if(lp.getVariantes() != null){ 
         for(Variante v: lp.getVariantes()){ 
             mje = mje +  v.getDetalle().getNombre() + ": " +  v.getDescripcion() + "<br/>";
         }
     }
     mje = mje + "</td>";
     mje = mje + "<td>$" + lp.getSubtotal() +"</td>\n";
     mje = mje + "<td>" + lp.getCantidad() +"</td>\n";
     mje = mje + "<td>$" + lp.getSubtotal()*lp.getCantidad() +"</td></tr>\n";

 }

if(p.getPorcentajeDescuento() != 0){
    
mje = mje + 
"          <br/><tr><td colspan=\"2\"><b>Subtotal:</br></td>\n" +
"          <td>$" + subtotal + "</td>\n" +
"        </tr>\n" +
"          <tr><td colspan=\"2\"><b>Porcentaje de descuento:</br></td>\n" +
"          <td>%" + p.getPorcentajeDescuento() + "</td>\n" +
"        </tr>\n" +
"          <tr><td colspan=\"2\"><b>Descuento en pesos:</br></td>\n" +
"          <td>$" + p.getDescuento() + "</td>\n" +
"        </tr>\n" +   
"          <tr><td colspan=\"2\"><b>Total:</br></td>\n" +
"          <td>$" + (subtotal - p.getDescuento()) + "</td>\n" +
"        </tr>\n" +        
"      </table>\n" ;
}
else{
    mje = mje + 
"          <br/><tr><td colspan=\"2\"><b>Total:</br></td>\n" +
"          <td>$" + p.getTotal() + "</td>\n" +
"        </tr>\n" +      
"      </table>\n" ;
}
mje = mje + 
"      <hr/>\n" +
       "<h4>Que sigue ahora?</h4>"+

      "<p>Usted deberá abonar una seña en el local de Doña Coca para que su pedido sea aprobado. "
        + "Tiene tiempo hasta tres días antes de la fecha de entrega.</p>" +
   
"    </columns>\n" +
"  </row>\n" +
"</container>";
 
           String mensaje;
               
        try{
            String destinatario = request.getParameter("mailUsu"); ;
            String asunto = "CompraRealizada con éxito!";
            String asunto2 = "CompraRealizada con éxito!";
            String cuerpo = mje;
            final String pass = "f37453481";
            String user = "msolnieto@donacocafinaljava.com";

            

            if(SendMail.send(destinatario,asunto,cuerpo,user,pass)){
                 mensaje = "Se ha enviado el mail correctamente";
                 SendMail.send(user,asunto2,cuerpo,user,pass);
            }else{
                 mensaje = "Ha ocurrido un error y no se ha podido enviar el mail.";
            }
            }catch(DonaCocaException ex){
            request.setAttribute("ex", ex.getMessage());
                    return "/Checkout.jsp";
            }
                
                
                request.getSession().setAttribute("exitoPedido", true);
                request.getSession().setAttribute("mailUsu", mailUsu);
               
               
                Pedido ped = new Pedido();
                request.getSession().setAttribute("pedido", ped);
                 return "/pedidoExito.jsp";
            } 
            
        
        else{
            request.getSession().setAttribute("pedidoVacio", true);
            return "/Checkout.jsp";
        }
        
    }
    
}
