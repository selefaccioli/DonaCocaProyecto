<%-- 
    Document   : envios
    Created on : 30/04/2019, 06:56:55
    Author     : selef
--%>

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

   
      <% if(request.getAttribute("ExitoCierre")!= null){%> 
                    <div class="alert alert-success">
                       Pedido cerrado con éxito! Un mail ha sido enviado a la casilla del cliente.
                    </div>
                    <%request.setAttribute("ExitoCierre",null);} %>
     <% if(request.getAttribute("ExitoCancel")!= null){%> 
                    <div class="alert alert-success">
                       Pedido cancelado con éxito! Un mail ha sido enviado a la casilla del cliente.
                    </div>
                    <%request.setAttribute("ExitoCancel",null);} %>
    <% if(request.getAttribute("ExitoSena")!= null){%> 
                    <div class="alert alert-success">
                       Seña registrada con éxito! Un mail ha sido enviado a la casilla del cliente.
                    </div>
                    <%request.setAttribute("ExitoSena",null);} %>
    <% if(request.getAttribute("ex")!= null){%> 
                    <div class="alert alert-danget">
                     <%=request.getAttribute("ex")%>
                    </div>
                    <%request.setAttribute("ex",null);} %>                 
       <% if(request.getAttribute("exitoConsulta")!= null){%> 
                    <div class="alert alert-succsess">
                     Consulta enviada con éxito, puede revisar su casilla para ver mas detalle.
                    </div>
                    <%request.setAttribute("exitoConsulta",null);} %> 
  </div>
        <%!ArrayList<Pedido> pendientes;%>
        <% pendientes = (ArrayList)request.getSession().getAttribute("pendientes");
        ArrayList<LineaPedido> lineasP = new ArrayList<LineaPedido>();
        
        CtrlPedido cp = new CtrlPedido();
        Pedido pedActual = (Pedido)session.getAttribute("pedidoAmpliado");
        //Pedido pedActual = (Pedido)request.getSession().getAttribute("pedConSena");
         Usuario usu = (Usuario)session.getAttribute("usuario");
        lineasP = pedActual.getLineasPedido();
              
   
        
      
           double subtotal = 0.0f;
              for(LineaPedido linea: lineasP){
          
          subtotal = subtotal + linea.getSubtotal();
              }
        
        
        %>  
        <div class="cuenta">
            <div class="container"> 
                <form action="CtrlMaestro" method="post"> 
                     <div class="row">
                         <input  type="hidden" name="form" value="RedireccionarComando">
                          <input type="hidden"  name="destino" value="/consulta.jsp"/>
                    <input type="submit" class="btn-info" value="Realizar consulta a cliente">
                    
                </div>  
                </form>
                         
                
                
       
        <!-- Payments Steps -->
  <div class="row">
  <div class="table-responsive">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                        <th>ID</th>
                                        <th>F Pedido</th>
                                        <th>F Entrega</th>
                                        <th>Estado</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        
                                        
                                        <tr>
                                            <td><%= pedActual.getId() %></td>
                                            <td><%= pedActual.getFechaPedido() %></td>
                                            <td><%= pedActual.getFechaEntrega() %></td>
                                            <% if(pedActual.getEstado().equals("Pendiente")){   %>
                                            <td><button type="button" class="btn btn-primary btn-sm"><%= pedActual.getEstado() %></button></td>
                                            <% } else if(pedActual.getEstado().equals("Aprobado")){   %>
                                            <td><button type="button" class="btn btn-success btn-sm"><%= pedActual.getEstado() %></button></td>
                                            <% } else if(pedActual.getEstado().equals("Cancelado")){  %>
                                            <td><button type="button" class="btn btn-danger btn-sm"><%= pedActual.getEstado() %></button></td>
                                            <% } else{  %>
                                            <td><button type="button" class="btn btn-info btn-sm"><%= pedActual.getEstado() %></button></td>
                                            <% } %>
                                          
                                        </tr>
                                      
                                    </tbody>
                                </table>
                            </div>
                        </div>
       
  </div>
       
        <br><br>
        
        <div class="shopping-cart text-center">
          <div class="cart-head">
            <ul class="row">
              <!-- PRODUCTS -->
              <li class="col-sm-2 text-left">
                <h6>PRODUCTOS</h6>
              </li>
              <!-- NAME -->
              <li class="col-sm-4 text-left">
                <h6>NOMBRE</h6>
              </li>
              <!-- PRICE -->
              <li class="col-sm-2">
                <h6>PRECIO</h6>
              </li>
              <!-- QTY -->
              <li class="col-sm-1">
                <h6>CANTIDAD</h6>
              </li>
              
              <!-- TOTAL PRICE -->
              <li class="col-sm-2">
                <h6>TOTAL</h6>
              </li>
              <li class="col-sm-1"> </li>
            </ul>
          </div>
          <% 
              for(LineaPedido linea: lineasP){
          
           %>
          <!-- Cart Details -->
          <ul class="row cart-details">
            <li class="col-sm-6">
              <div class="media"> 
                <!-- Media Image -->
                <div class="media-left media-middle"> <a href="#." class="item-img"> <img class="media-object" src="images\imagenesdc\<%= linea.getTorta().getRutaImg() %>" alt=""> </a> </div>
                
                <!-- Item Name -->
                <div class="media-body">
                  <div class="position-center-center">
                    <h5><%= linea.getTorta().getNombre()%> 
                       <% if(linea.getVariantes() != null){ %></h5>
                    <% for(Variante v: linea.getVariantes()){ %> 
                    <p>
                        <%= v.getDetalle().getNombre() %>: &nbsp 
                       <%= v.getDescripcion() %>
                    </p>
                    <%}  }%>
                  </div>
                </div>
              </div>
            </li>
            
            <!-- PRICE -->
            <li class="col-sm-2">
                     <div class="position-center-center"> 
                  <p class="all-total"><span><%= linea.getSubtotal() %></span></p>
                     </div>
            </li>
            
            <!-- QTY -->
            <li class="col-sm-1">
              <div class="position-center-center">
              
                  <!-- QTY -->
                <div class="cart_quantity_button">
                   <form action="CtrlMaestro" method="post">
                   <input type="hidden"  name="form" value="ActualizarLineaComando"/>
                   <input type="hidden" name="idTorta" value="<%= linea.getTorta().getId() %>"/>
                   <input onchange="submit()" disabled min="1" class="tamanio cart_quantity_input"type="number" name="cantidad" value="<%=linea.getCantidad()%>"/>
                   
                   </form>
              </div>
               
              </div>
            </li>
            
            <!-- TOTAL PRICE -->
            <li class="col-sm-2">
              <div class="position-center-center"> 
                 <p class="all-total"><span><%= linea.getSubtotal()* linea.getCantidad() %></span></p>
                  
               

              
              
              
              
              </div>
            </li>
            
         
            
          </ul>
        
          <% } %>
          
       
          
          
        </div>
          
      </div>
          
          
          
          
        </div>
     <section class="chart-page padding-top-100 padding-bottom-100">
      <div class="container"> 
        
        <!-- Payments Steps -->
        <div class="shopping-cart"> 
          
          <!-- SHOPPING INFORMATION -->
          <div class="cart-ship-info">
            <div class="row"> 
              
              <!-- ESTIMATE SHIPPING & TAX -->
              <div class="col-sm-7">
                <h6>Usuario</h6>
                <form action="CtrlMaestro" method="post">
                  <ul class="row">
                    
                    <!-- Name -->
                    <li class="col-md-6">
                      <label> *NOMBRE
                        <input type="text" disabled name="first-name" value="<%= pedActual.getUsuario().getNombre()   %>" placeholder="">
                      </label>
                    </li>
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                      <label> *APELLIDO
                        <input type="text" disabled name="last-name" value="<%= pedActual.getUsuario().getApellido()   %>" placeholder="">
                      </label>
                    </li>
                    <li class="col-md-6"> 
                      <!-- MAIL -->
                      <label>MAIL
                        <input type="text" disabled name="company" value="<%= pedActual.getUsuario().getMail()   %>" placeholder="">
                      </label>
                    </li>
                    <li class="col-md-6"> 
                      <!-- DIRECCION -->
                      <label>*DIRECCIÓN
                        <input type="text" disabled name="address" value="<%= pedActual.getUsuario().getDireccion()  %>" placeholder="">
                      </label>
                    </li>
                    <!-- TELEFONO -->
                    <li class="col-md-6">
                      <label>*TELÉFONO
                        <input type="text"  disabled name="town" value="<%= pedActual.getUsuario().getTelefono()  %>" placeholder="">
                      </label>
                    </li>
                    
                    <!-- CIUDAD -->
                    <li class="col-md-6">
                      <label> CIUDAD
                        <input type="text" disabled name="contry-state" value="Reconquista" placeholder="">
                      </label>
                    </li>
                    
                    
                    
                
                    
                    <!-- CREATE AN ACCOUNT -->
                   
                  </ul>
                      
                      <h6>Aclaraciones adicionales o preferencias</h6>
                     <h7>(Nombre a poner en la torta, cambio de colores, cuadro de futbol, edad, etc )</h7><br>
                  <textarea name="aclaraciones" disabled rows="10" cols="80"><%if(pedActual.getAclaraciones()!= null){ %><%= pedActual.getAclaraciones()   %><% } %></textarea><br>
                    <% if(usu != null && usu.isEsAdmin()){  %>   
                    <% if(pedActual.getEstado().equals("Aprobado") || pedActual.getEstado().equals("Pendiente")){ %>
                    <input type="hidden" name="form" value="RegistrarCierreCancelComando">
                    <input type="hidden" name="idPedido" value="<%=  pedActual.getId()  %>">
                    <input class="btn btn-default" style="width: 150px;" type="submit" name="cerrar"  value="Cerrar Pedido"> 
                  
                    <input class="btn btn-default" style="width: 150px; margin-left: 50px;" type="submit" name="cancelar"  value="Cancelar">
                    
                   
                    <% } %>
                </form>
                 <% if(pedActual.getEstado().equals("Pendiente")){ %>
                <form action="CtrlMaestro" method="post">
                    <input type="hidden" name="form" value="RedireccionarComando">
                    <input type="hidden" name="destino" value="/registrarSena.jsp">
                    <input type="hidden" name="idPedido" value="<%=  pedActual.getId()  %>">
                    <input class="btn btn-default" style="width: 150px; margin-left: 90px;" type="submit" name="sena"  value="Registrar Seña">
                </form>
                <% } }%>
             
              </div>
              
              <!-- SUB TOTAL -->
              <div class="col-sm-5">
                <h6>Su orden</h6>
                <div class="order-place">
                  <div class="order-detail">
                 
                    
                    <!-- SUB TOTAL -->
                     <p class="all-total"><%if(pedActual.getPorcentajeDescuento() != 0){%>SUBTOTAL<%} else{%>TOTAL<% } %><span><%= subtotal %></span></p>
                  
                  
                  <% if(pedActual.getPorcentajeDescuento() != 0){ %>
                  <p class="all-total">PORCENTAJE DE DESCUENTO<span><%= pedActual.getPorcentajeDescuento() %></span></p>
                
                  <p class="all-total">DESCUENTO EN $<span><%= pedActual.getDescuento() %></span></p>
                 
                  <p class="all-total">TOTAL<span><%= pedActual.getTotal() %></span></p>
                 
                  
                  <% } %> 
                    
                    <p class="all-total">TOTAL SEÑA<span> $<%=  pedActual.getSena()   %></span></p>
                    <p class="all-total">RESTAN PAGAR <span> $<%= pedActual.getTotal() -  pedActual.getSena()   %></span></p>
                  </div>
                 
                
                   </div> 
                    <br><br><br><br>
                <h6>Método de envío</h6>
                <div class="order-place">
                    <div class="radio">
                          <input type="radio" name="radio1" id="domicilio" value="domicilio" disabled
                                 <%if(pedActual.getEnvioDomicilio()){%> checked  <% } %>>
                          <label for="radio2">Envío a domicilio</label>
                        </div>
                    
                        <div class="radio">
                          <input type="radio" name="radio1" id="local" value="local" disabled
                                  <%if(!pedActual.getEnvioDomicilio()){%>  checked  <% } %>>
                          <label for="radio3">Retiro en local</label>
                        </div>
                   
                </div>
                    
                   
                </div>
              </div>
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
<script src="js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
    </body>
 
</html>
