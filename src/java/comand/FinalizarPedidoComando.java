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
           
 /*     mje= 
"<html>\n";
 
      mje = mje + 
"\n" +
"  \n" +
"        \n" +
" <body>    \n" +
"        <div class=\"cuenta\">\n" +
"            <div class=\"container\"> \n" +
"              \n" +
"                         \n" +
"                \n" +
"                \n" +
"       \n" +
"        <!-- Payments Steps -->\n" +
"  <div class=\"row\">\n" +
"  <div class=\"table-responsive\">\n" +
"                            <div class=\"table-striped\">\n" +
"                                <table class=\"table table-striped\">\n" +
"                                    <thead>\n" +
"                                        <tr>\n" +
                           
"                                        <th>Estado</th>\n" +
"                                        <th></th>\n" +
"                                    </tr>\n" +
"                                    </thead>\n" +
"                                    <tbody>\n" +
"                                        \n" +
"                                        \n" +
"                                        <tr>\n";
                                          
      
                                            if(p.getEstado().equals("Pendiente")){ 
                                 mje=  mje +  "<td><button type=\"button\" class=\"btn btn-primary btn-sm\">" + p.getEstado() + "</button></td>\n";
                                            } else if(p.getEstado().equals("Aprobado")){ 
                                 mje=  mje +    "<td><button type=\"button\" class=\"btn btn-success btn-sm\">" + p.getEstado() + "</button></td>\n";
                                            } else if(p.getEstado().equals("Cancelado")){  
                                 mje=  mje +     "<td><button type=\"button\" class=\"btn btn-danger btn-sm\">" + p.getEstado() + "</button></td>\n";
                                            } else{  
                                 mje=  mje +    "<td><button type=\"button\" class=\"btn btn-info btn-sm\">" + p.getEstado() + "</button></td>\n"; }
                                             
 mje = mje +"\n" +
"                                        </tr>\n" +
"                                      \n" +
"                                    </tbody>\n" +
"                                </table>\n" +
"                            </div>\n" +
"                        </div>\n" +
"       \n" +
"  </div>\n" +
"       \n" +
"        <br><br>\n" +
"        \n" ;

              for(LineaPedido linea: p.getLineasPedido()){ 
 mje = mje + "\n" +
"           \n" +
"          <!-- Cart Details -->\n" +
"          <ul class=\"row cart-details\">\n" +
"            <li class=\"col-sm-6\">\n" +
"              <div class=\"media\"> \n" +
"                <!-- Media Image -->\n" +
 
"                <!-- Item Name -->\n" +
"                <div class=\"media-body\">\n" +
"                  <div class=\"position-center-center\">\n" +
"                    <h5> Nombre torta: " +  linea.getTorta().getNombre();
                      if(linea.getVariantes() != null){ 
                          mje = mje + "</h5>";
                    for(Variante v: linea.getVariantes()){ 
                         mje = mje + "<p>\n" +
                        v.getDetalle().getNombre() + ": " +
                        v.getDescripcion()  +
                     "</p>\n"; }}
                 mje = mje +
"                  </div>\n" +
"                </div>\n" +
"              </div>\n" +
"            </li>\n" +
"            \n" +
"            <!-- PRICE -->\n" +
"            <li class=\"col-sm-2\">\n" +
"                     <div class=\"position-center-center\"> \n" +
"                  <p class=\"all-total\"><span>" + "Precio unitario: " + linea.getSubtotal()  + "</span></p>\n" +
"                     </div>\n" +
"            </li>\n" +
"            \n" +
"            <!-- QTY -->\n" +
"            <li class=\"col-sm-1\">\n" +
"              <div class=\"position-center-center\">\n" +
"              \n" +
"                  <!-- QTY -->\n" +
"                <div class=\"cart_quantity_button\">\n" +
"                   <form action=\"CtrlMaestro\" method=\"post\">\n" +
"                   <input type=\"hidden\"  name=\"form\" value=\"ActualizarLineaComando\"/>\n" +
"                   <input type=\"hidden\" name=\"idTorta\" value=\"<%= linea.getTorta().getId() %>\"/>\n" +
"                   <input disabled type=\"text\"  name=\"cantidad\" value=\" Cantidad: " + linea.getCantidad() + "\"/>\n" +
"                   \n" +
"                   </form>\n" +
"              </div>\n" +
"               \n" +
"              </div>\n" +
"            </li>\n" +
"            \n" +
"            <!-- TOTAL PRICE -->\n" +
"            <li class=\"col-sm-2\">\n" +
"              <div class=\"position-center-center\"> \n" +
"                 <p class=\"all-total\"><span>"+ "Total torta: " + linea.getSubtotal()* linea.getCantidad() + "</span></p>\n" +
"                  \n" +
"               \n" +
"                \n" +
"              \n" +
"              </div>\n" +
"            </li>\n" +
"            \n" +
"         \n" +
"            \n" +
"          </ul>\n" +
"        \n";
 }
        mje = mje + "\n" +
"          \n" +
"       \n" +
"          \n" +
"          \n" +
"        </div>\n" +
"          \n" +
"      </div>\n" +
"          \n" +
"          \n" +
"          \n" +
"          \n" +
"       \n" +
"     <section class=\"chart-page padding-top-100 padding-bottom-100\">\n" +
"      <div class=\"container\"> \n" +
"        \n" +
"        <!-- Payments Steps -->\n" +
"        <div class=\"shopping-cart\"> \n" +
"          \n" +
"          <!-- SHOPPING INFORMATION -->\n" +
"          <div class=\"cart-ship-info\">\n" +
"            <div class=\"row\"> \n" +
"              \n" +
"              <!-- ESTIMATE SHIPPING & TAX -->\n" +
"              <div class=\"col-sm-7\">\n" +
"                <h6>Usuario</h6>\n" +
"                <form action=\"CtrlMaestro\" method=\"post\">\n" +
"                  <ul class=\"row\">\n" +
"                    \n" +
"                    <!-- Name -->\n" +
"                    <li class=\"col-md-6\">\n" +
"                      <label> *NOMBRE\n" +
"                        <input type=\"text\" disabled name=\"first-name\" value=\"" + p.getUsuario().getNombre()   +"\" placeholder=\"\">\n" +
"                      </label>\n" +
"                    </li>\n" +
"                    <!-- LAST NAME -->\n" +
"                    <li class=\"col-md-6\">\n" +
"                      <label> *APELLIDO\n" +
"                        <input type=\"text\" disabled name=\"last-name\" value=\"" + p.getUsuario().getApellido()   + "\" placeholder=\"\">\n" +
"                      </label>\n" +
"                    </li>\n" +
"                    <li class=\"col-md-6\"> \n" +
"                      <!-- MAIL -->\n" +
"                      <label>MAIL\n" +
"                        <input type=\"text\" disabled name=\"company\" value=\"" + p.getUsuario().getMail()  + "\" placeholder=\"\">\n" +
"                      </label>\n" +
"                    </li>\n" +
"                    <li class=\"col-md-6\"> \n" +
"                      <!-- DIRECCION -->\n" +
"                      <label>*DIRECCIÓN\n" +
"                        <input type=\"text\" disabled name=\"address\" value=\"" + p.getUsuario().getDireccion()  + "\" placeholder=\"\">\n" +
"                      </label>\n" +
"                    </li>\n" +
"                    <!-- TELEFONO -->\n" +
"                    <li class=\"col-md-6\">\n" +
"                      <label>*TELÉFONO\n" +
"                        <input type=\"text\"  disabled name=\"town\" value=\""+ p.getUsuario().getTelefono()  +"\" placeholder=\"\">\n" +
"                      </label>\n" +
"                    </li>\n" +
"                    \n" +
"                    <!-- CIUDAD -->\n" +
"                    <li class=\"col-md-6\">\n" +
"                      <label> CIUDAD\n" +
"                        <input type=\"text\" disabled name=\"contry-state\" value=\"Reconquista\" placeholder=\"\">\n" +
"                      </label>\n" +
"                    </li>\n" +
"                    \n" +
"                    \n" +
"                    \n" +
"                \n" +
"                    \n" +
"                    <!-- CREATE AN ACCOUNT -->\n" +
"                   \n" +
"                  </ul>\n" +
"                      \n" +
"                      <h6>Aclaraciones adicionales o preferencias</h6>\n" +
"                     <h7>(Nombre a poner en la torta, cambio de colores, cuadro de futbol, edad, etc )</h7><br>\n" +
"                  <textarea name=\"aclaraciones\" disabled rows=\"10\" cols=\"80\">";
                 if(p.getAclaraciones()!= null){ 
                     mje = mje + p.getAclaraciones();
                         } 
                  mje = mje + "</textarea><br>\n" +
                   

                
"             \n" +
"              </div>\n" +
"              \n" +
"              <!-- SUB TOTAL -->\n" +
"              <div class=\"col-sm-5\">\n" +
"                <h6>Su orden</h6>\n" +
"                <div class=\"order-place\">\n" +
"                  <div class=\"order-detail\">\n" +
"                 \n" +
"                    \n" +
"                    <!-- SUB TOTAL -->\n" +
"                     <p class=\"all-total\">";
                  if(p.getPorcentajeDescuento() != 0){
                      mje = mje + "SUBTOTAL";} else{
                      mje = mje + "TOTAL"; } 
                     mje = mje + "<span>" +  subtotal +"</span></p>\n";
                    if(p.getPorcentajeDescuento() != 0){ 
                 mje = mje + "<p class=\"all-total\">PORCENTAJE DE DESCUENTO<span>" +  p.getPorcentajeDescuento() + "</span></p>\n" +
"                \n" +
"                  <p class=\"all-total\">DESCUENTO EN $<span>" + p.getDescuento() + "</span></p>\n" +
"                 \n" +
"                  <p class=\"all-total\">TOTAL<span>" + p.getTotal() + "</span></p>\n" +
"                 \n" +
"                  \n";}
                  

                    mje = mje + 
"                    <p class=\"all-total\">TOTAL SEÑA<span> $" +  p.getSena()   +"</span></p>\n" +
"                    <p class=\"all-total\">RESTAN PAGAR <span> $" + (p.getTotal() -  p.getSena())   +"</span></p>\n" +
"                  </div>\n" +
"                 \n" +
"                \n" +
"                   </div> \n" +
"                    <br><br><br><br>\n" +
"                <h6>Método de envío</h6>\n" +
"                <div class=\"order-place\">\n" +
"                    <div class=\"radio\">\n" +
"                          <input type=\"radio\" name=\"radio1\" id=\"domicilio\" value=\"domicilio\" disabled\n";
                    
                    if(p.getEnvioDomicilio()){
                        mje = mje + "checked"; } 
                    mje = mje + ">\n" +
"                          <label for=\"radio2\">Envío a domicilio</label>\n" +
"                        </div>\n" +
"                    \n" +
"                        <div class=\"radio\">\n" +
"                          <input type=\"radio\" name=\"radio1\" id=\"local\" value=\"local\" disabled\n";
                    if(!p.getEnvioDomicilio()){
                        mje = mje + "checked"; } 
                     mje = mje + ">\n" +
"                          <label for=\"radio3\">Retiro en local</label>\n" +
"                        </div>\n" +
"                   \n" +
"                </div>\n" +
"                    \n" +
"                   \n" +
"                </div>\n" +
"              </div>\n" +
"            </div>\n" +
"          </div>\n" +
"        </div>\n" +
"     \n" +
"    </section>         \n" +
" </div>\n" +
"    </body>\n" +
" \n" +
"</html>\n" +
"";
              
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
            
            */
                
                
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
