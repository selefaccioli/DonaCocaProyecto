<%@page import="entity.Usuario"%>
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%!int cantidadAEnviar = 0;%>
<%    
    if(request.getSession().getAttribute("pendientes")!=null)
    {
        cantidadAEnviar = ((ArrayList)request.getSession().getAttribute("pendientes")).size();
    }
    Pedido pedido = (Pedido)session.getAttribute("pedido");
    Usuario usu = (Usuario)session.getAttribute("usuario");
%>
<header>
    <div class="sticky">
      <div class="container"> 
        
        <!-- Logo -->
        <div class="logo"> 
            <form action="CtrlMaestro" method="post" id="formLogo">
                <input type="hidden" name="form" value="RedireccionarComando">
                <input type="hidden" name="destino" value="/home.jsp">
                <a href="javascript:;" type="submit" onclick="document.getElementById('formLogo').submit()">
                <img class="img-responsive" src="images/logo.png" alt="" >
              </a>    
            </form>
           
        
        </div>
        <nav class="navbar ownmenu">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-open-btn" aria-expanded="false"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"><i class="fa fa-navicon"></i></span> </button>
          </div>
          
           <!-- NAV -->
          <div class="collapse navbar-collapse" id="nav-open-btn">
            <ul class="nav">
              <li class="dropdown active"> <a href="#." class="dropdown-toggle" data-toggle="dropdown">Home</a>
                <ul class="dropdown-menu">
                  <li> <a href="index.html">Index Default</a> </li>
                  <li> <a href="index-1.html">Index 2</a> </li>
                  <li> <a href="index-2.html">Index 3</a></li>
                  <li> <a href="index-header-1.html">Index Header 1</a></li>
                  <li> <a href="index-header-2.html">Index Header 2</a></li>
                  <li> <a href="index-header-3.html">Index Header 3</a></li>
                  <li> <a href="index-header-4.html">Index Header 4</a></li>
                </ul>
              </li>
              <li class="dropdown"> <a href="#." class="dropdown-toggle" data-toggle="dropdown">Pages</a>
                <ul class="dropdown-menu">
                  <li> <a href="shop_01.html">Shop 01 </a> </li>
                  <li> <a href="shop_02.html">Shop 02</a> </li>
                  <li> <a href="shop_03.html">Shop 03 </a> </li>
                  <li> <a href="shop_04.html">Shop 04 </a> </li>
                  <li> <a href="product-detail_01.html">Product Detail 01</a> </li>
                  <li> <a href="product-detail_02.html">Product Detail 02</a> </li>
                  <li> <a href="shopping-cart.html">Shopping Cart</a> </li>
                  <li> <a href="checkout.html">Checkout</a> </li>
                  <li> <a href="about-us_01.html">About 01</a> </li>
                  <li> <a href="about-us_02.html">About 02</a> </li>
                  <li> <a href="contact.html">Contact</a> </li>
                  <li> <a href="blog-list_01.html">Blog List 01</a> </li>
                  <li> <a href="blog-list_02.html">Blog List 02</a> </li>
                  <li> <a href="blog-list_03.html">Blog List 03 </a> </li>
                  <li> <a href="blog-detail_01.html">Blog Detail 01 </a> </li>
                </ul>
              </li>
              <li> <a href="about-us_01.html">About </a> </li>
              
              <!-- Two Link Option -->
              <li class="dropdown"> <a href="#." class="dropdown-toggle" data-toggle="dropdown">Designer</a>
                <div class="dropdown-menu two-option">
                  <div class="row">
                    <ul class="col-sm-6">
                      <li> <a href="shop_01.html">summer store</a></li>
                      <li> <a href="shop_01.html"> sarees</a></li>
                      <li> <a href="shop_01.html"> kurtas</a></li>
                      <li> <a href="shop_01.html"> shorts & tshirts</a></li>
                      <li> <a href="shop_01.html"> winter wear</a></li>
                      <li> <a href="shop_01.html"> jeans</a></li>
                      <li> <a href="shop_01.html"> bra</a></li>
                      <li> <a href="shop_01.html"> babydools</a> </li>
                    </ul>
                    <ul class="col-sm-6">
                      <li> <a href="shop_01.html">deodornts</a></li>
                      <li> <a href="shop_01.html"> skin care</a></li>
                      <li> <a href="shop_01.html"> make up</a></li>
                      <li> <a href="shop_01.html"> watch</a></li>
                      <li> <a href="shop_01.html"> siting bags</a></li>
                      <li> <a href="shop_01.html"> totes</a></li>
                      <li> <a href="shop_01.html"> gold rings</a></li>
                      <li> <a href="shop_01.html"> jewellery</a> </li>
                    </ul>
                  </div>
                </div>
              </li>
              
              <!-- MEGA MENU -->
              <li class="dropdown megamenu"> <a href="#." class="dropdown-toggle" data-toggle="dropdown">store</a>
                <div class="dropdown-menu">
                  <div class="row"> 
                    
                    <!-- Shop Pages -->
                    <div class="col-md-3">
                      <h6>Shop Pages</h6>
                      <ul>
                        <li> <a href="shop_01.html">Shop 01 </a> </li>
                        <li> <a href="shop_02.html">Shop 02</a> </li>
                        <li> <a href="shop_03.html">Shop 03 </a> </li>
                        <li> <a href="shop_04.html">Shop 04 </a> </li>
                        <li> <a href="product-detail_01.html">Product Detail 01</a> </li>
                        <li> <a href="product-detail_02.html">Product Detail 02</a> </li>
                        <li> <a href="shopping-cart.html">Shopping Cart</a> </li>
                        <li> <a href="checkout.html">Checkout</a> </li>
                      </ul>
                    </div>
                    
                    <!-- TOp Rate Products -->
                    <div class="col-md-4">
                      <h6>TOp Rate Products</h6>
                      <div class="top-rated">
                        <ul>
                          <li>
                            <div class="media-left">
                              <div class="cart-img"> <a href="#"> <img class="media-object img-responsive" src="images/cart-img-1.jpg" alt="..."> </a> </div>
                            </div>
                            <div class="media-body">
                              <h6 class="media-heading">WOOD CHAIR</h6>
                              <div class="stars"> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> </div>
                              <span class="price">129.00 USD</span> </div>
                          </li>
                          <li>
                            <div class="media-left">
                              <div class="cart-img"> <a href="#"> <img class="media-object img-responsive" src="images/cart-img-2.jpg" alt="..."> </a> </div>
                            </div>
                            <div class="media-body">
                              <h6 class="media-heading">STOOL</h6>
                              <div class="stars"> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> </div>
                              <span class="price">129.00 USD</span> </div>
                          </li>
                          <li>
                            <div class="media-left">
                              <div class="cart-img"> <a href="#"> <img class="media-object img-responsive" src="images/cart-img-3.jpg" alt="..."> </a> </div>
                            </div>
                            <div class="media-body">
                              <h6 class="media-heading">WOOD SPOON</h6>
                              <div class="stars"> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> </div>
                              <span class="price">129.00 USD</span> </div>
                          </li>
                        </ul>
                      </div>
                    </div>
                    
                    <!-- New Arrival -->
                    <div class="col-md-5">
                      <h5>NEW ARRIVAL 2016 <span>(Best Collection)</span></h5>
                      <img class="nav-img" src="images/nav-img.png" alt="" >
                      <p>Lorem ipsum dolor sit amet,<br>
                        consectetur adipiscing elit. <br>
                        Donec faucibus maximus<br>
                        vehicula.</p>
                      <a href="#." class="btn btn-small btn-round">SHOP NOW</a> </div>
                  </div>
                </div>
              </li>
              <li> <a href="contact.html"> contact</a> </li>
            </ul>
          </div>
          
          <!-- Nav Right -->
          <div class="nav-right">
            <ul class="navbar-right">
              
              <!-- USER INFO -->
              <li class="dropdown user-acc"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" ><i class="icon-user"></i> </a>
              
                <ul class="dropdown-menu">
                    <% if(session.getAttribute("exitoLogin") != null){ %>
                    <li><a href="#">MY CART</a></li>
                    <li><a href="#">ACCOUNT INFO</a></li>
                    <li><a href="#">LOG OUT</a></li>
                     <% }
                        else{%>
                     <form id="formLogIn" action="CtrlMaestro" method="post" class="formNav">
                         <input type="hidden"  name="form" value="RedireccionarComando"/>
                         <input type="hidden"  name="destino" value="/login.jsp"/>   
                         <li><a href="javascript:;" type="submit" onclick="document.getElementById('formLogIn').submit();" >LOG IN</a></li>
                     </form>
                    <form id="formSignUp" action="CtrlMaestro" method="post" class="formNav">
                         <input type="hidden"  name="form" value="RedireccionarComando"/>
                         <input type="hidden"  name="destino" value="/signup.jsp"/>   
                         <li><a href="javascript:;" type="submit" onclick="document.getElementById('formSignUp').submit();" >REGISTRARSE</a></li>
                     </form>
                     <% }    %>
                  
                </ul>
              
              </li>
              
              
                                    
                                   
                                   
                                     
                       
              
              <!-- USER BASKET -->
             
              <li class="dropdown user-basket"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"><i class="icon-basket-loaded"></i> </a>
                <ul class="dropdown-menu">
                    <div class='cart-items'>
                       <!-- los productos van aqui -->
                    </div>
                  <li>
                    <h5 class="text-center">SUBTOTAL: $0</h5>
                  </li>
                  <li class="margin-0">
                    <div class="row">
                        <form action="CtrlMaestro" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                                    <input type="hidden"  name="destino" value="/carro.jsp"/>
                                    <input class="btn btn-default" type="submit" name="tipoLinea" value="Ver Carro">
                                   
                                     
                        </form>
                      
                      <div class="col-xs-6 "> <a href="checkout.html" class="btn">CHECK OUT</a></div>
                    </div>
                  </li>
                </ul>
              </li>
           
              
              <!-- SEARCH BAR -->
              <li class="dropdown"> <a href="javascript:void(0);" class="search-open"><i class=" icon-magnifier"></i></a>
                <div class="search-inside animated bounceInUp"> <i class="icon-close search-close"></i>
                  <div class="search-overlay"></div>
                  <div class="position-center-center">
                    <div class="search">
                      <form>
                        <input type="search" placeholder="Search Shop">
                        <button type="submit"><i class="icon-check"></i></button>
                      </form>
                    </div>
                  </div>
                </div>
              </li>
            </ul>
          </div>
        </nav>
      </div>
    </div>
  </header>