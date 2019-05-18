<%@page import="entity.LineaPedido"%>
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
                <img class="img-responsive" src="../images/logoDonaCocam.png" alt="" >
                
              </a>    
            </form>
           
     
        </div>
       
      
          
        <nav class="navbar ownmenu">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#nav-open-btn" aria-expanded="false"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"><i class="fa fa-navicon"></i></span> </button>
          </div>
          
           <!-- NAV -->
           <% if(usu!= null && usu.isEsAdmin()){ %>
          <div class="collapse navbar-collapse" id="nav-open-btn">  
            <ul class="nav">
              <li class="dropdown active"> <a href="#." class="dropdown-toggle" data-toggle="dropdown">Administrador</a>
                <ul class="dropdown-menu">
                  <form action="CtrlMaestro" method="post" id="formABMTorta">
                <input type="hidden" name="form" value="RedireccionarComando">
                <input type="hidden" name="destino" value="/ABMTortas.jsp">
                <li> <a href="javascript:;" type="submit" onclick="document.getElementById('formABMTorta').submit()"> Tortas</a> </li>
                
            </form>   
                    <form action="CtrlMaestro" method="post" id="formABMUsuario">
                       
                       <input type="hidden" name="form" value="RedireccionarComando">
                       <input type="hidden" name="destino" value="/ABMUsuarios.jsp">
                        <li> <a href="javascript:;" type="submit" onclick="document.getElementById('formABMUsuario').submit()"> Usuarios</a> </li>
                    </form>
                    <form action="CtrlMaestro" method="post" id="formDetalles">
                        <input type="hidden" name="form" value="RedireccionarComando">
                       <input type="hidden" name="destino" value="/ABMDetalles.jsp">
                        <li> <a href="javascript:;" type="submit" onclick="document.getElementById('formDetalles').submit()"> Detalles</a> </li>
                    </form>
                    
                    <form action="CtrlMaestro" method="post" id="formVariantes">
                        <input type="hidden" name="form" value="RedireccionarComando">
                       <input type="hidden" name="destino" value="/ABMVariantes.jsp">
                        <li> <a href="javascript:;" type="submit" onclick="document.getElementById('formVariantes').submit()"> Variantes</a> </li>
                    </form>
                    
                      <form action="CtrlMaestro" method="post" id="formCupones">
                        <input type="hidden" name="form" value="RedireccionarComando">
                       <input type="hidden" name="destino" value="/ABMCupones.jsp">
                        <li> <a href="javascript:;" type="submit" onclick="document.getElementById('formCupones').submit()"> Cupones</a> </li>
                    </form>
                    <form action="CtrlMaestro" method="post" id="formPedidosPendientes">
                        <input type="hidden" name="form" value="RedireccionarComando">
                       <input type="hidden" name="destino" value="/pedidosAdmin.jsp">
                        <li> <a href="javascript:;" type="submit" onclick="document.getElementById('formPedidosPendientes').submit()"> Pedidos</a> </li>
                    </form>
                    
                  
                </ul>
              </li>
            
            </ul>
          </div>
          <% } %>
          
        
          
          <!-- Nav Right -->
          <div class="nav-right">
            <ul class="navbar-right">
              
              <!-- USER INFO -->
              <li class="dropdown user-acc"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" ><i class="icon-user"></i> </a>
              
                <ul class="dropdown-menu">
                    <% if(session.getAttribute("exitoLogin") != null){
                        if(usu.isEsAdmin()){
                           %>
                    <li><a href="#">MI CUENTA</a></li>
                    
                     <% }
                    else{%>
                    <form id="miCuentaForm" action="CtrlMaestro" method="post">
                        <input type="hidden" name="form" value="RedireccionarComando">
                        <input type="hidden" name="destino" value="/cuenta.jsp">
                        <li><a href="javascript:;" type="submit" onclick="document.getElementById('miCuentaForm').submit()">MI CUENTA</a></li>
                        
                    </form>
                    
                     <form id="misPedidosForm" action="CtrlMaestro" method="post">
                        <input type="hidden" name="form" value="RedireccionarComando">
                        <input type="hidden" name="destino" value="/pedidos.jsp">
                        <li><a href="javascript:;" type="submit" onclick="document.getElementById('misPedidosForm').submit()">MIS PEDIDOS</a></li>
                        
                    </form>
                    
                    <% } %>
                    <form id="formLogOut" action="CtrlMaestro" method="post" class="formNav">
                        <input type="hidden"  name="form" value="LogOutComando"/>
                        <li><a href="javascript:;" type="submit" onclick="document.getElementById('formLogOut').submit()">SALIR</a></li>
                    </form>
                    
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
 <!     
              
                                    
                                   
                                   
                                     
                       
              
              <!-- USER BASKET -->
             
              <li class="dropdown user-basket"> <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true"><i class="icon-basket-loaded"></i> </a>
                <ul class="dropdown-menu">
                     <% if(pedido.getLineasPedido().size() > 0){ 
                         for(LineaPedido lp : pedido.getLineasPedido()){
                      %>
                    <li>
                    <div class="media-left">
                      <div class="cart-img"> <a href="#"> <img class="media-object img-responsive" src="../images/imagenesdc/<%= lp.getTorta().getRutaImg() %>"alt="..."> </a> </div>
                    </div>
                    <div class="media-body">
                      <h6 class="media-heading"><%= lp.getTorta().getNombre() %></h6>
                      <span class="price">$ <%= lp.getTorta().getPrecio()  %></span> <span class="qty">QTY: <%= lp.getCantidad()  %></span> </div>
                  </li>
                  <% }} %>
                  <li class="margin-0">
                    <div class="row">
                        <form action="CtrlMaestro" method="post" class="formNav">
                                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                                    <input type="hidden"  name="destino" value="/carro.jsp"/>
                                    <input class="btn btn-default" type="submit" name="tipoLinea" value="Ver Carro">
                                   
                                     
                        </form>
               
                    </div>
                  </li>
                </ul>
              </li>
           
              
            </ul>
          </div>
        </nav>
      </div>
    </div>
                     
  </header>