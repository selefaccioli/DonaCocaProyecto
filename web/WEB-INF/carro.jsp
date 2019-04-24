
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
<% 
session.setAttribute("exitoPedido", null); }
else{
%>  
  </div>
  
  <div id="content"> 
 
    <!--======= PAGES INNER =========-->
    <section class="padding-top-100 padding-bottom-100 pages-in chart-page">
      <div class="container"> 
         
       <% Pedido ped = (Pedido)session.getAttribute("pedido");
       Usuario usu = (Usuario)session.getAttribute("usuario");
       int contLineas=0;
       ArrayList<LineaPedido> lp = ped.getLineasPedido();
       
       
       %> 
        <!-- Payments Steps -->
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
                <div class="media-left media-middle"> <a href="#." class="item-img"> <img class="media-object" src="images/cart-img-1.jpg" alt=""> </a> </div>
                
                <!-- Item Name -->
                <div class="media-body">
                  <div class="position-center-center">
                    <h5><%= linea.getTorta().getNombre() %></h5>
                    <% for(Detalle d: linea.getTorta().getDetalles()){ %> 
                    <p>
                        <%= d.getNombre() %>: &nbsp 
                       <%= d.getDetalle() %>
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
                <div class="quinty"> 
                  <!-- QTY -->
                  <input type="text" name="cantidad" value="<%= linea.getCantidad()  %>">
                </div>
              </div>
            </li>
            
            <!-- TOTAL PRICE -->
            <li class="col-sm-2">
              <div class="position-center-center"> <span class="price"><small>$</small>299</span> </div>
            </li>
            
            <!-- REMOVE -->
            <li class="col-sm-1">
              <div class="position-center-center"> <a href="#."><i class="icon-close"></i></a> </div>
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
              <div class="coupn-btn">
               <form action="CtrlMaestro" method="post">
                     
                        <input type="hidden"  name="form" value="RedireccionarComando"/>
                        <input type="hidden" name="destino"  value="/home.jsp"/>
                        <input class="btn btn-default add-to-cart linea" type="submit" value="Seguir Comprando">
                        
                        
                </form> 
                  <form action="CtrlMaestro" method="post">
                     
                        <input type="hidden"  name="form" value="FinalizarPedidoComando"/>
                        <input class="btn btn-default add-to-cart linea" type="submit" value="Finalizar Pedido" <% if(usu != null && usu.isEsAdmin()){ %> disabled="" <% } %>>
                        
                        
                </form> 
                  </div>
               
            </div>
            
            <!-- SUB TOTAL -->
            <div class="col-sm-5">
              <h6>grand total</h6>
              <div class="grand-total">
                <div class="order-detail">
                  <p>WOOD CHAIR <span>$598 </span></p>
                  <p>STOOL <span>$199 </span></p>
                  <p>WOOD SPOON <span> $139</span></p>
                  
                  <!-- SUB TOTAL -->
                  <p class="all-total">TOTAL COST <span> $998</span></p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  <div class="row">
      
      
      
      
      
  </div>
    <div class="signup-form">
                            
  
    
  
  </div>
  
  
  <!--======= RIGHTS =========--> 
 
</div>
  <% } %>                       
                        
                        
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/own-menu.js"></script> 
<script src="js/jquery.lighter.js"></script> 
<script src="js/owl.carousel.min.js"></script> 

<!-- SLIDER REVOLUTION 4.x SCRIPTS  --> 
<script type="text/javascript" src="rs-plugin/js/jquery.tp.t.min.js"></script> 
<script type="text/javascript" src="rs-plugin/js/jquery.tp.min.js"></script> 
<script src="js/main.js"></script> 
<script src="js/main.js"></script>
</body>
</html>