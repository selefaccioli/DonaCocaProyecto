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

   
      <% if(request.getSession().getAttribute("exitoPedido") != null){ %>
            <div class="alert alert-success">
                Pedido realizado con éxito!
            </div>
<% session.setAttribute("exitoPedido", null); }
        else if(session.getAttribute("exitoMail")!= null){%> 
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
        <div class="cuenta">
            <div class="container"> 
       
        <!-- Payments Steps -->
        
       
        <br><br>
        
       
          
      </div>
          
          
          
          
        </div>
     <section class="chart-page padding-top-100 padding-bottom-100">
      <div class="container"> 
        
        <!-- Payments Steps -->
        <div class="shopping-cart"> 
          
          <!-- SHOPPING INFORMATION -->
          <div class="cart-ship-info">
               <form action="CtrlMaestro" method="post">
            <div class="row"> 
              
              <!-- ESTIMATE SHIPPING & TAX -->
              <div class="col-sm-7">
                  
          
                            
                <h6>Usuario</h6>
                <form action="CtrlMaestro" method="post">
                    
               
                         
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
                      <label>MAIL
                        <input type="text" required name="mailUsu" <%if(usu!= null){ %>value="<%= usu.getMail()   %>" disabled <% }%> placeholder="">
                      </label>
                    </li>
                    <li class="col-md-6"> 
                      <!-- DIRECCION -->
                      <label>*DIRECCIÓN
                        <input type="text" required name="direcUsu" <%if(usu!= null){ %>value="<%= usu.getDireccion()  %>" disabled <% } %> placeholder="">
                      </label>
                    </li>
                    <!-- TELEFONO -->
                    <li class="col-md-6">
                      <label>*TELÉFONO
                        <input type="text" required  name="telUsu" <%if(usu!= null){ %>value="<%= usu.getTelefono() %>" disabled <% } %>  placeholder="">
                      </label>
                    </li>
                    
                    <!-- CIUDAD -->
                    <li class="col-md-6">
                      <label> CIUDAD
                        <input type="text" disabled name="contry-state" value="Reconquista" placeholder="">
                      </label>
                    </li>
                    
                    
                
                    
                    
                  
                    <!-- CREATE AN ACCOUNT -->
                    <li class="col-md-6">
                      <div class="checkbox margin-0 margin-top-20">
                        <input id="checkbox1" class="styled" type="checkbox">
                        <label for="checkbox1"> Ship to a different address </label>
                      </div>
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
                    <li class="col-md-6">
                      <button type="submit" class="btn" <% if(usu != null && usu.isEsAdmin()){ %> disabled="" <% } %> >FINALIZAR PEDIDO</button>
                    </li>
                    
                   

                      
                    
                   
             
                
               
              
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
                  <p class="all-total">PORCENTAJE DE DESCUENTO<span><%= request.getSession().getAttribute("porcDescuento") %></span></p>
                
                  <p class="all-total">DESCUENTO EN $<span><%= request.getSession().getAttribute("descuentoPesos") %></span></p>
                 
                  <p class="all-total">TOTAL<span><%= request.getSession().getAttribute("total") %></span></p>
                 
                 <%  session.setAttribute("cuponSi", null); 
                    %>
                  
                  <% } %> 
                </div>
            
            </div>
                <br><br><br><br>
                <h6>Método de envío</h6>
                <div class="order-place">
                    <div class="radio">
                          <input type="radio" name="radio1" id="domicilio" value="domicilio" required >
                          <label for="radio2">Envío a domicilio</label>
                        </div>
                    
                        <div class="radio">
                          <input type="radio" name="radio1" id="local" value="local">
                          <label for="radio3">Retiro en local</label>
                        </div>
                   
                </div>
                
                
                
                </div>
              </div>
               </form>
            </div>
          </div>
        </div>
      </div>
    </section>         
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/own-menu.js"></script> 
<script src="js/jquery.lighter.js"></script> 
<script src="js/owl.carousel.min.js"></script> 

<!-- SLIDER REVOLUTION 4.x SCRIPTS  --> 

<script type="text/javascript" src="rs-plugin/js/jquery.tp.t.min.js"></script> 
<script type="text/javascript" src="rs-plugin/js/jquery.tp.min.js"></script> 
<script src="js/main.js"></script> 
<script src="../js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
    </body>
 
</html>
