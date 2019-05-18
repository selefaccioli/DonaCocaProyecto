
<%@page import="entity.Variante"%>
<%@page import="entity.Cupon"%>
<%@page import="entity.Usuario"%>
<%@page import="entity.Detalle"%>
<%@page import="entity.LineaPedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Pedido"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body>

<!-- LOADER -->
<div id="loader">
  <div class="position-center-center">
    <div class="ldr"></div>
  </div>
</div>

<!-- Wrap -->
<div id="wrap"> 
  
  <!-- header -->
<jsp:include page="header.jsp"/>
  
 
  <!-- Content -->
  <div class="row">

   
      <% if(session.getAttribute("exitoMail")!= null){%> 
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
        else if(request.getAttribute("ex") != null){%> 
                    <div class="alert alert-danger">
                        <%=request.getAttribute("ex")%>
                    </div>
                    
  <% }else{ %>  
  </div>
        
  <div id="content"> 
    
    <!--======= PAGES INNER =========-->
    <section class="padding-top-100 padding-bottom-100 pages-in chart-page">
      <div class="container"> 
         
       <% Pedido ped = (Pedido)session.getAttribute("pedido");
       Usuario usu = (Usuario)session.getAttribute("usuario");
       Cupon cuponActual = (Cupon)session.getAttribute("cuponActual");
       int contLineas=0;
       ArrayList<LineaPedido> lp = ped.getLineasPedido();
       
       
       %> 
        <!-- Payments Steps -->
        
       
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
          <% for(LineaPedido linea: lp){
           
           %>
          <!-- Cart Details -->
          <ul class="row cart-details">
            <li class="col-sm-6">
              <div class="media"> 
                <!-- Media Image -->
                <div class="media-left media-middle"> <a href="#." class="item-img"> <img class="media-object" src="../images/imagenesdc/<%= linea.getTorta().getRutaImg() %>" alt=""> </a> </div>
                
                <!-- Item Name -->
                <div class="media-body">
                  <div class="position-center-center">
                    <h5><%= linea.getTorta().getNombre() %></h5>
                    <% for(Variante v: linea.getTorta().getVariantes()){ %> 
                    <p>
                        <%= v.getDetalle().getNombre() %>: &nbsp 
                       <%= v.getDescripcion() %>
                    </p>
                    <% }%>
                  </div>
                </div>
              </div>
            </li>
            
            <!-- PRICE -->
            <li class="col-sm-2">
              <div class="position-center-center"> <span class="price"><small>$</small><%= linea.getTorta().getPrecio() %></span> </div>
            </li>
            
            <!-- QTY -->
            <li class="col-sm-1">
              <div class="position-center-center">
              
                  <!-- QTY -->
                <div class="cart_quantity_button">
                   <form action="CtrlMaestro" method="post">
                   <input type="hidden"  name="form" value="ActualizarLineaComando"/>
                   <input type="hidden" name="idTorta" value="<%= linea.getTorta().getId() %>"/>
                   <input onchange="submit()" min="1" class="tamanio cart_quantity_input"type="number" name="cantidad" value="<%=linea.getCantidad()%>"/>
                   
                   </form>
              </div>
               
              </div>
            </li>
            
            <!-- TOTAL PRICE -->
            <li class="col-sm-2">
              <div class="position-center-center"> <span class="price"><small>$</small><%= (linea.getCantidad()*linea.getTorta().getPrecio() )  %></span> </div>
            </li>
            
            <!-- REMOVE -->
            <li class="col-sm-1">
              <div class="position-center-center"> 
                  <form action="CtrlMaestro" method="post" id="eliminarLinea">
                      <input type="hidden" name="form" value="EliminarLineaComando">
                      <input type="hidden" name="idTortaEliminar" value="<%= linea.getTorta().getId() %>">
                      <a href="javascript:;" type="submit" onclick="document.getElementById('eliminarLinea').submit()"><i class="icon-close"></i></a> 
                  </form>
                  
              </div>
            </li>
          </ul>
        
          <% } %>
          
       
          
          
        </div>
          
      </div>
    </section>
    
  <!--======= PAGES INNER =========-->
    <section class="padding-top-100 padding-bottom-100 light-gray-bg shopping-cart small-cart">
      <div class="container"> 
        
        <!-- SHOPPING INFORMATION -->
        <div class="cart-ship-info margin-top-0">
          <div class="row"> 
              <!-- DISCOUNT CODE -->
            <div class="col-sm-7">
              <h6>CUPON DE DESCUENTO</h6>
              <form action="CtrlMaestro" method="post">
                <input type="hidden" name="form" value="AplicarCuponComando">
                <input type="text" name="cuponDto" <%if(cuponActual != null){ %>value="<%= cuponActual.getCodigo() %>" <% } %> placeholder="INGRESE EL CÓDIGO DE SU CUPÓN AQUÍ">
                <button type="submit" class="btn btn-small btn-dark">APLICAR</button>
              </form>
                  <% if(request.getSession().getAttribute("cuponActual") != null){ %>
            <div class="alert alert-success">
                Cupón aplicado con exito!
            </div>
                <% 
                 
                  session.setAttribute("cuponActual", null); }%>
                
              <% if(request.getSession().getAttribute("cuponFallido") != null){ %>
            <div class="alert alert-danger">
                El cupón ingresado no existe 
            </div>
                <% session.setAttribute("cuponFallido", null); }%>   
                
            </div>
            
              
            
            
          
               
              
         
            
            <!-- SUB TOTAL -->
            <div class="col-sm-5">
              <h6>Total</h6>
              <div class="grand-total">
                <div class="order-detail">
                   <% Float subtotal = 0.0f;
                       for(LineaPedido linea: lp){
                   subtotal = subtotal + linea.getTorta().getPrecio()*linea.getCantidad();
           %>
                  <p><%= linea.getTorta().getNombre()  %><span><%= linea.getTorta().getPrecio()*linea.getCantidad() %></span></p>
                  
                  <% } %>
                  <!-- SUB TOTAL -->
                  <p class="all-total"><%if(cuponActual != null){%>SUBTOTAL<%} else{%>TOTAL<% } %><span><%= subtotal %></span></p>
                  <% session.setAttribute("total", subtotal); %>
                  
                  <% if(cuponActual != null){ 
                    session.setAttribute("cuponSi", true);   
                  float porcDescuento = cuponActual.getPorcDescuento(); 
                  session.setAttribute("porcDescuento", porcDescuento);%>
                  <p class="all-total">PORCENTAJE DE DESCUENTO<span>%<%= cuponActual.getPorcDescuento() %></span></p>
                  <% float descuento = (cuponActual.getPorcDescuento()*subtotal)/100; 
                   session.setAttribute("descuentoPesos", descuento); %>
                  <p class="all-total">DESCUENTO EN $<span><%= descuento %></span></p>
                  <% float total = subtotal - descuento; 
                  session.setAttribute("total", total); %>
                  <p class="all-total">TOTAL<span><%= total %></span></p>
                  
                  
                  <% } %> 
                </div>
              </div>
            </div>
          </div>
        </div>
        
            <div class="row">
                
                <br>
                <h5>
                    Si posee una cuenta puede <h5> 
                    <form action="CtrlMaestro" method="post" id="formLogin">
                        <input type="hidden" name="form" value="RedireccionarComando">
                       <input type="hidden" name="destino" value="/login.jsp">
                       <u><a href="javascript:;" type="submit" syle="" onclick="document.getElementById('formLogin').submit()"> LOGUEARSE</a></u>
                    </form>
                        <h5>De lo contrario puede </h5> 
                     <form action="CtrlMaestro" method="post" id="formRegistro">
                        <input type="hidden" name="form" value="RedireccionarComando">
                       <input type="hidden" name="destino" value="/signup.jsp">
                       <u> <a href="javascript:;" type="submit" onclick="document.getElementById('formRegistro').submit()"> REGISTRARSE</a> </u>
                    </form>
                    <h5> para recibir novedades. 
                </h5>
              
                <br>
                <form action="CtrlMaestro" method="post">
                     <input type="hidden"  name="form" value="RedireccionarComando"/>
                     <input type="hidden"  name="destino" value="/Checkout.jsp"/>
                
                 
                     <input class="btn btn-default add-to-cart linea" type="submit" value="No, gracias. continuar al checkout" <% if(usu != null && usu.isEsAdmin()){ %> disabled="" <% } %>>
                     
                </form><br><br>
                        
                   <form action="CtrlMaestro" method="post">
                     
                        <input type="hidden"  name="form" value="RedireccionarComando"/>
                        <input type="hidden" name="destino"  value="/home.jsp"/>
                        <input class="btn btn-default add-to-cart linea" type="submit" value="Seguir Comprando">
                        
                        
                  </form>         
                     
                        
                        
               
              </div>
      </div>
    </section>
 
  
  
  <!--======= RIGHTS =========--> 
 
</div>
  <% } %>                    
 </form>                         
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