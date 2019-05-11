
<%@page import="entity.Variante"%>
<%@page import="entity.Cupon"%>
<%@page import="entity.Usuario"%>
<%@page import="entity.Detalle"%>
<%@page import="entity.LineaPedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Pedido"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="M_Adnan">
<title>PAVSHOP - Multipurpose eCommerce HTML5 Template</title>

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css" media="screen" />

<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

<!-- Custom CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="css/ionicons.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">

<!-- JavaScripts -->
<script src="js/modernizr.js"></script>

<!-- Online Fonts -->
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900' rel='stylesheet' type='text/css'>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->

</head>
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
                <% session.setAttribute("cuponActual", null); }%>
                
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
                  
                  <% if(cuponActual != null){ %>
                  <p class="all-total">PORCENTAJE DE DESCUENTO<span>%<%= cuponActual.getPorcDescuento() %></span></p>
                  <% float descuento = (cuponActual.getPorcDescuento()*subtotal)/100; %>
                  <p class="all-total">DESCUENTO EN $<span><%= descuento %></span></p>
                  <% float total = subtotal - descuento; %>
                  <p class="all-total">TOTAL<span><%= total %></span></p>
                  <% session.setAttribute("total", total); %>
                  
                  <% } %> 
                </div>
              </div>
            </div>
          </div>
        </div>
        
            <div class="row">
                
                <br>
                
                
                <form action="CtrlMaestro" method="post">
                     <input type="hidden"  name="form" value="FinalizarPedidoComando"/>
                     <h5>Fecha de Entrega</h5>
                     <h6>(Recuerde que los pedidos se deben realizar con una semana de anticipación como mínimo)</h6><br>
                  <div class="row">
                         <div class="col-sm-12 col-sm-offset-0">
                             <input class="control form-control" type="date" name="fechaEntrega" style="width: 200px"  required>
                        </div>                                   
                  </div> <br>
                     <input class="btn btn-default add-to-cart linea" type="submit" value="Finalizar Pedido" <% if(usu != null && usu.isEsAdmin()){ %> disabled="" <% } %>>
                     
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