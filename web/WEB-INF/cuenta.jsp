
<%@page import="entity.Usuario"%>
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
  <div id="content"> 
    
    <!--======= PAGES INNER =========-->
    <section class="chart-page padding-top-100 padding-bottom-100">
      <div class="container"> 
        
        <!-- Payments Steps -->
        <div class="shopping-cart"> 
          
          <!-- SHOPPING INFORMATION -->
          <div class="cart-ship-info register">
            <div class="row"> 
              
              <!-- ESTIMATE SHIPPING & TAX -->
              <div class="col-sm-12">
                <h6>MI CUENTA</h6>
                <div class="col-sm-3 col-sm-offset-0">
                        
                        <% if(request.getAttribute("ex")!=null){%>
                        <div class="vanish alert alert-danger mensajeError">
                            <p>                                
                                <%=request.getAttribute("ex")%>
                            </p>
                        </div>
                    <%}else if(request.getAttribute("exitoEditado")!=null){
                            if((Boolean)request.getAttribute("exitoEditado")){%>
                        <div class="vanish alert alert-success mensajeError">
                            <p>                                
                                Usuario actualizado con exito!
                            </p>
                        </div>
                        <%}else{%>
                        <div class="vanish alert alert-danger mensajeError">
                            <p>                                
                                <strong>Error! </strong>El usuario no pudo ser actualizado.
                            </p>
                        </div>
                <% }} 
                        
                                Usuario usu = (Usuario)session.getAttribute("usuario");
                                if(usu != null){
                                
                    %>   
                </div>
                
                
                <form>
                  <ul class="row">
                    
                    <!-- Name -->
                    <li class="col-md-6">
                      <label> NOMBRE
                        <input type="text" name="nombre" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" value="<%= usu.getNombre() %>">
                      </label>
                    </li>
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                      <label> APELLIDO
                        <input type="text" name="apellido" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" value="<%= usu.getApellido() %>">
                      </label>
                    </li>
                    
                    <!-- EMAIL ADDRESS -->
                    <li class="col-md-6">
                      <label> EMAIL
                        <input type="text" name="email" pattern="^[\s\S]{0,40}$" title="Solo se permiten hasta 40 caracteres" value="<%= usu.getMail()  %>">
                      </label>
                    </li>
                    <!-- PHONE -->
                    <li class="col-md-6">
                      <label> TELEFONO
                        <input type="text" name="telefono" pattern="^\d{0,20}" title="Solo se permiten numeros. Hasta 20 digitos" value="<%= usu.getTelefono() %>">
                      </label>
                    </li>
                    
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                      <label> DIRECCION
                        <input type="text" name="direccion" pattern="^[\s\S]{0,45}$" title="Solo se permiten hasta 45 caracteres" value="<%= usu.getDireccion() %>">
                      </label>
                    </li>
                    
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                        <label> DNI
                            <input type="text" name="dni" pattern="^\d{0,8}" title="Solo se permiten numeros. Hasta 8 digitos" value="<%= usu.getDni() %>">
                        </label>
                        
                    </li>
                    
                    
                
                    <li class="col-md-6">
                      <input type="hidden" name="form" value="CuentaComando"/>
                      <button type="submit" name="actDatos" value="actualizarDatos" class="btn">ACTUALIZAR DATOS</button>
                    </li>
                  </ul>
                </form>
                <% }%>        
                
                
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
  </div>

  
</div>
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