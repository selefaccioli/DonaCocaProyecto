<%-- 
    Document   : envios
    Created on : 30/04/2019, 06:56:55
    Author     : selef
--%>

<%@page import="entity.Cupon"%>
<%@page import="entity.Usuario"%>
<%@page import="entity.Variante"%>
<%@page import="logic.CtrlPedido"%>
<%@page import="entity.LineaPedido"%>
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <jsp:include page="head.jsp"/>
  
        <body onload="scrollDiv();">
        <jsp:include page="header.jsp"/>
          <div class="row">

   
      
     <%if(session.getAttribute("exitoMail")!= null){%> 
                    <div class="alert alert-success">
                        Un mail ha sido enviado a su casilla de correo!
                    </div>
                    <%session.setAttribute("exitoMail",null);} 
        else if(session.getAttribute("cantidadInvalida")!= null){%> 
                    <div class="alert alert-danger">
                        Por favor ingrese una cantidad válida
                    </div>
                    <%session.setAttribute("cantidadInvalida",null);} 
        else if(session.getAttribute("fechaIncorrecta")!=null){%> 
                    <div class="alert alert-danger">
                        Por favor ingrese una cantidad de días válida. 
                        No se permiten pedidos con menos de 7 dias de anticipación.
                    </div>
                    <%session.setAttribute("fechaIncorrecta", null);}  
else { if(request.getAttribute("ex") != null){%> 
                    <div class="alert alert-danger">
                        <%=request.getAttribute("ex")%>
                    </div>
                    
  <% }} %>  
  </div>
        <%!ArrayList<Pedido> pendientes;%>
        <% Pedido ped = (Pedido)session.getAttribute("pedido");
       Usuario usu = (Usuario)session.getAttribute("usuario");
       Cupon cuponActual = (Cupon)session.getAttribute("cuponActual");
       int contLineas=0;
       ArrayList<LineaPedido> lp = ped.getLineasPedido();
       
        
        %>  
     
     <section class="chart-page padding-top-0 padding-bottom-100">
      <div class="container"> 
        
        <!-- Payments Steps -->
        <div class="shopping-cart"> 
          
          <!-- SHOPPING INFORMATION -->
          <div class="cart-ship-info">
               
            <div class="row"> 
              
              <!-- ESTIMATE SHIPPING & TAX -->
              <div class="col-sm-7">
              <h6>Método de envío</h6>
              <ul class="row">
                  <li>
                  <select class="form-control" id="envioSelect" name="envioSelect" required>
              <option value="ninguno">Elija una opción</option>     
              <option value="retiraLocal">Retira en el local</option>
              <option value="aDomicilio">Envío a domiclio</option>
              </select>  
                  </li>
                  
              </ul>
                
              </div>
             
              <br><br><br>
              
              
              
               
            
              <div class="col-sm-7" id="conDomicilio">
                
            <form action="CtrlMaestro" method="post"> 
                            
                <h6>Usuario</h6>
             
                    
               
                         
                  <ul class="row">
                    
                    <!-- Name -->
                   
                    <%if(usu!= null){ %>
                    <li class="col-md-6">
                      <label> *NOMBRE
                          <input type="text"  pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres"  required name="nomUsu" <%if(usu!= null){ %>value="<%= usu.getNombre()   %>" disabled  <% } %> placeholder="">
                      </label>
                    </li>
                    <% } else{ %>
                     <li class="col-md-6">
                      <label> *NOMBRE
                        <input type="text"  pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres"  required name="nomUsu"  placeholder="">
                      </label>
                    </li>
                    <% } %>
                    
                    <%if(usu!= null){ %>
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                      <label> *APELLIDO
                        <input type="text"  required name="apeUsu"  pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" <%if(usu!= null){ %>value="<%= usu.getApellido()   %>" disabled <% } %> placeholder="">
                      </label>
                    </li>
                    <% } else{ %>
                    <li class="col-md-6">
                      <label> *APELLIDO
                        <input type="text"  required name="apeUsu"  pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres"  placeholder="">
                      </label>
                    </li>
                    <% } %>
                    
                    <%if(usu!= null){ %>
                    
                    <li class="col-md-6"> 
                      <!-- MAIL -->
                      <label>*MAIL
                        <input type="mail" required name="mailUsu"  pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" <%if(usu!= null){ %>value="<%= usu.getMail()   %>"  <% }%> placeholder="">
                      </label>
                    </li>
                    <% } else{ %>
                    <li class="col-md-6"> 
                      <!-- MAIL -->
                      <label>*MAIL
                        <input type="mail" required name="mailUsu"  pattern="^[\s\S]{0,40}$" title="Solo se permiten hasta 40 caracteres" placeholder="">
                      </label>
                    </li>
                    <% } %>
                    
                    <%if(usu!= null){ %>
                    <li class="col-md-6"> 
                      <!-- DIRECCION -->
                      <label>*DIRECCIÓN
                        <input type="text" required name="direcUsu"  pattern="^[\s\S]{0,45}$" title="Solo se permiten hasta 45 caracteres"  <%if(usu!= null){ %>value="<%= usu.getDireccion()  %>"  <% } %> placeholder="">
                      </label>
                    </li>
                    <% } else{ %>
                    <li class="col-md-6"> 
                      <!-- DIRECCION -->
                      <label>*DIRECCIÓN
                        <input type="text" required name="direcUsu"  pattern="^[\s\S]{0,45}$" title="Solo se permiten hasta 45 caracteres" placeholder="">
                      </label>
                    </li>
                    <% } %>
                    
                     <%if(usu!= null){ %>
                    <!-- TELEFONO -->
                    <li class="col-md-6">
                      <label>*TELÉFONO
                        <input type="text" required  name="telUsu"  pattern="^\d{0,20}" title="Solo se permiten numeros. Hasta 20 digitos"  <%if(usu!= null){ %>value="<%= usu.getTelefono() %>"  <% } %>  placeholder="">
                      </label>
                    </li>
                    <% } else{ %>
                    <li class="col-md-6">
                      <label>*TELÉFONO
                        <input type="text" required  name="telUsu"  pattern="^\d{0,20}" title="Solo se permiten numeros. Hasta 20 digitos"  placeholder="">
                      </label>
                    </li>
                    <% } %>
                    
                    <!-- CIUDAD -->
                    <li class="col-md-6">
                      <label> CIUDAD
                        <input type="text" disabled name="contry-state" value="Reconquista" placeholder="">
                      </label>
                    </li>
                    
                    
                        <!-- fecha nacimiento -->
                    <li class="col-md-6">
                      <label> *FECHA NACIMIENTO
                        <input type="date" name="fecNac" <%if(usu!= null){ %>value="<%= usu.getFechaNacimiento() %>"  <% } %> placeholder="DNI">
                      </label>
                    </li>
                    
                <!-- Como conocio a doña coca -->
                <li class="col-md-6">
                     <label>CÓMO CONOCIÓ A DOÑA COCA</label>
                    
                       <select class="form-control"  name="conocimiento" required <%if(usu!= null){ %> disabled <% } %>>
                           <option value="instagram" <%if(usu!= null && usu.getConocimiento().equals("instagram")){ %> selected <% } %>>Instagram</option>     
                       <option value="facebook" <%if(usu!= null && usu.getConocimiento().equals("facebook")){ %> selected <% } %>>Facebook</option>
                        <option value="recomendacion" <%if(usu!= null && usu.getConocimiento().equals("recomendacion")){ %> selected <% } %>>Recomendación</option>
                        <option value="boca en boca" <%if(usu!= null && usu.getConocimiento().equals("boca en boca")){ %> selected <% } %>>Boca en Boca</option>
                        <option value="otro" <%if(usu!= null && usu.getConocimiento().equals("otro")){ %> selected <% } %>>Otro</option>
                       </select> 
                    </li>
                    
                    
                  
                  </ul>
                           <h6>Fecha de Entrega</h6>
                     <h7>(Recuerde que los pedidos se deben realizar con una semana de anticipación como mínimo)</h7><br>
                  <div class="row">
                         <div class="col-sm-12 col-sm-offset-0">
                             <input class="control form-control" type="date" name="fechaEntrega" style="width: 200px"  required>
                             <br></div>                                   
                  </div> <br>
                      
                      <h6>Aclaraciones adicionales o preferencias</h6>
                     <h7>(Nombre a poner en la torta, cambio de colores, cuadro de futbol, edad, etc )</h7><br>
                     <textarea name="aclaraciones" placeholder="*" pattern="^[\s\S]{0,300}$" title="Solo se permiten hasta 300 caracteres" rows="10" cols="80"></textarea><br><br>
                     
                  <!-- PHONE -->
                   <input type="hidden"  name="form" value="FinalizarPedidoComando"/>
                   <input type="hidden" name="envio" value="aDomicilio">
                    <li class="col-md-6">
                      <button type="submit" class="btn" <% if(usu != null && usu.isEsAdmin()){ %> disabled="" <% } %> >FINALIZAR PEDIDO</button>
                    </li>
                    
                   

                      
                    
                   
             
                 </form>   
               
             
              </div>
                  
                    
              <div class="col-sm-7" id="sinDomicilio">
                  
          <form action="CtrlMaestro" method="post"> 
                            
                <h6>Usuario</h6>
                
                    
               
                         
                  <ul class="row">
                    
                    <!-- Name -->
                    <li class="col-md-6">
                      <label> *NOMBRE
                        <input type="text"  required name="nomUsu"  <%if(usu!= null){ %>value="<%= usu.getNombre()   %>" disabled <% } %> placeholder="">
                      </label>
                    </li>
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                      <label> *APELLIDO
                        <input type="text"  required name="apeUsu" <%if(usu!= null){ %>value="<%= usu.getApellido()   %>" disabled <% } %> placeholder="">
                      </label>
                    </li>
                    <li class="col-md-6"> 
                      <!-- MAIL -->
                      <label>*MAIL
                        <input type="text" required name="mailUsu" <%if(usu!= null){ %>value="<%= usu.getMail()   %>" disabled <% }%> placeholder="">
                      </label>
                    </li>
                    
                   
                    <!-- TELEFONO -->
                    <li class="col-md-6">
                      <label>*TELÉFONO
                        <input type="text" required  name="telUsu" <%if(usu!= null){ %>value="<%= usu.getTelefono() %>" disabled <% } %>  placeholder="">
                      </label>
                    </li>
                    
                         <!-- fecha nacimiento -->
                    <li class="col-md-6">
                      <label> *FECHA NACIMIENTO
                        <input type="date" name="fecNac" <%if(usu!= null){ %>value="<%= usu.getFechaNacimiento() %>" disabled <% } %> placeholder="">
                      </label>
                    </li>
                    
                <!-- Como conocio a doña coca -->
                <li class="col-md-6">
                     <label>CÓMO CONOCIÓ A DOÑA COCA</label>
                    
                       <select class="form-control"  name="conocimiento" required <%if(usu!= null){ %> disabled <% } %>>
                           <option value="instagram" <%if(usu!= null && usu.getConocimiento().equals("instagram")){ %> selected <% } %>>Instagram</option>     
                       <option value="facebook" <%if(usu!= null && usu.getConocimiento().equals("facebook")){ %> selected <% } %>>Facebook</option>
                        <option value="recomendacion" <%if(usu!= null && usu.getConocimiento().equals("recomendacion")){ %> selected <% } %>>Recomendación</option>
                        <option value="boca en boca" <%if(usu!= null && usu.getConocimiento().equals("boca en boca")){ %> selected <% } %>>Boca en Boca</option>
                        <option value="otro" <%if(usu!= null && usu.getConocimiento().equals("otro")){ %> selected <% } %>>Otro</option>
                       </select> 
                    </li>
                    
                    
                
                    
                    
                  
                  </ul>
                           <h6>Fecha de Entrega</h6>
                     <h7>(Recuerde que los pedidos se deben realizar con una semana de anticipación como mínimo)</h7><br>
                  <div class="row">
                         <div class="col-sm-12 col-sm-offset-0">
                             <input class="control form-control" type="date" name="fechaEntrega" style="width: 200px"  required>
                             <br></div>                                   
                  </div> <br>
                      
                      <h6>Aclaraciones adicionales o preferencias</h6>
                     <h7>(Nombre a poner en la torta, cambio de colores, cuadro de futbol, edad, etc )</h7><br>
                     <textarea name="aclaraciones"  rows="10" cols="80"></textarea><br><br>
                     
                  <!-- PHONE -->
                   <input type="hidden"  name="form" value="FinalizarPedidoComando"/>
                   <input type="hidden" name="envio" value="retiraLocal">
                    <li class="col-md-6">
                      <button type="submit" class="btn" <% if(usu != null && usu.isEsAdmin()){ %> disabled="" <% } %> >FINALIZAR PEDIDO</button>
                    </li>
                    
                   

                      
                    
                   
          </form>
                
               
              
              </div>      
              
              <!-- SUB TOTAL -->
              <div class="col-sm-5">
               <h6>Su orden</h6>
                <div class="order-place">
              
              
             
                <div class="order-detail">
                   <% double subtotal = 0.0f;
                       for(LineaPedido linea: lp){
                   subtotal = subtotal + linea.getSubtotal();
           %>
                  <p><%= linea.getTorta().getNombre()  %><span><%= linea.getSubtotal() %></span></p>
                  
                  <% } %>
                  <!-- SUB TOTAL -->
                  <p class="all-total"><%if(request.getSession().getAttribute("cuponSi") != null){%>SUBTOTAL<%} else{%>TOTAL<% } %><span><%= subtotal %></span></p>
                  
                  
                  <% if(request.getSession().getAttribute("cuponSi")  != null){ %>
                  <p class="all-total">PORCENTAJE DE DESCUENTO<span><%= ped.getPorcentajeDescuento() %></span></p>
                
                  <p class="all-total">DESCUENTO EN $<span><%= ped.getDescuento() %></span></p>
                 
                  <p class="all-total">TOTAL<span><%= ped.getTotal() %></span></p>
                 
                 <%  session.setAttribute("cuponSi", null); 
                    %>
                  
                  <% } %> 
                </div>
            
            </div>
                <br><br><br><br>
             
                
                
                
                </div>
              </div>
               </form>
            </div>
          </div>
        </div>
      </div>
    </section>
                
                
                
                <script>
                    $(function () {
  $("#envioSelect").change(function() {
    var val = $(this).val();
    if(val === "retiraLocal") {
        $("#sinDomicilio").show();
        $("#conDomicilio").hide();
    }
    else if(val === "aDomicilio") {
        $("#conDomicilio").show();
        $("#sinDomicilio").hide();
    }
    else if(val === "ninguno"){
        alert("Elija un método de envío");
    }
  });
});

$( document ).ready(function() {
    $("#sinDomicilio").hide();
    $("#conDomicilio").hide();
});
                </script>               
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/own-menu.js"></script> 
<script src="js/jquery.lighter.js"></script> 
<script src="js/owl.carousel.min.js"></script> 

<!-- SLIDER REVOLUTION 4.x SCRIPTS  --> 

<script type="text/javascript" src="rs-plugin/js/jquery.tp.t.min.js"></script> 
<script type="text/javascript" src="rs-plugin/js/jquery.tp.min.js"></script> 
<script src="js/main.js"></script> 
<script src="js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
    </body>
 
</html>
