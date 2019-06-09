
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
    <section class="chart-page padding-top-0 padding-bottom-100">
      <div class="container"> 
        
        <!-- Payments Steps -->
        <div class="shopping-cart"> 
          
          <!-- SHOPPING INFORMATION -->
          <div class="cart-ship-info register">
            <div class="row"> 
              
              <!-- ESTIMATE SHIPPING & TAX -->
              <div class="col-sm-12">
                <h6>REGISTRO NUEVO USUARIO</h6>
                <form action="CtrlMaestro" method="Post" onsubmit="return validarPass();">
                  <ul class="row">
                    
                    <!-- Name -->
                    <li class="col-md-6">
                      <label> *NOMBRE
                        <input type="text" name="nomUsu" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" value="" placeholder="Nombre">
                      </label>
                    </li>
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                      <label> *APELLIDO
                        <input type="text" name="apeUsu" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" value="" placeholder="Apellido">
                      </label>
                    </li>
                    
                     <!-- DNI -->
                    <li class="col-md-6">
                      <label> *DNI
                        <input type="text" name="dniUsu" value="" pattern="^\d{0,8}" title="Solo se permiten numeros. Hasta 8 digitos" placeholder="DNI">
                      </label>
                    </li>
                    
                     <!-- fecha nacimiento -->
                    <li class="col-md-6">
                      <label> *FECHA NACIMIENTO
                        <input type="date" name="fecNac" value="" placeholder="DNI">
                      </label>
                    </li>
                    
                     
                     <!-- MAIL -->
                    <li class="col-md-6">
                      <label> *EMAIL
                        <input type="email" name="mailUsu" pattern="^[\s\S]{0,40}$" title="Solo se permiten hasta 40 caracteres" value="" placeholder="Email">
                      </label>
                    </li>
                    
                    <!-- Name -->
                    <li class="col-md-6">
                      <label> *USUARIO
                        <input type="text" name="usuUsu" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" value="" placeholder="Nombre de usuario">
                      </label>
                    </li>
                    
                    <!-- PASSWORD -->
                    <li class="col-md-6">
                      <label> *CONTRASEÑA
                        <input id="passA" type="password"  placeholder="Contraseña*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15" required>
                      </label>
                    </li>
                    
                     <!-- PASSWORD -->
                    <li class="col-md-6">
                      <label> *CONFIRMAR CONTRASEÑA
                        <input id="passB" class="control form-control" type="password" placeholder="Confirmar contraseña*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15"  required >
                      </label>
                    </li>
                    
                     <input type="hidden" id="pass1" name="Contra1">
                     <input type="hidden" id="pass2" name="Contra2">
                    
                    <!-- EMAIL ADDRESS -->
                    <li class="col-md-6">
                      <label> *DIRECCION
                        <input type="text" name="direcUsu" pattern="^[\s\S]{0,45}$" title="Solo se permiten hasta 45 caracteres" value="" placeholder="">
                      </label>
                    </li>
                    <!-- PHONE -->
                    <li class="col-md-6">
                      <label> *TELEFONO
                        <input type="text" name="telUsu" pattern="^\d{0,20}" title="Solo se permiten numeros. Hasta 20 digitos" value="" placeholder="">
                      </label>
                    </li>
                    
                <!-- Como conocio a doña coca -->
                    
                    <li class="col-md-6">
                         <label>CÓMO CONOCIÓ A DOÑA COCA</label>
                       <select class="form-control"  name="conocimiento" required>
                       <option value="instagram">Instagram</option>     
                       <option value="facebook">Facebook</option>
                        <option value="recomendacion">Recomendación</option>
                        <option value="boca en boca">Boca en Boca</option>
                        <option value="otro">Otro</option>
                       </select> 
                    </li>
                    
                    
                    <li class="col-md-6">
                        <input type="hidden" name="form" value="RegistroComando"> 
                      <button type="submit" class="btn">REGISTRARSE</button>
                    </li>
                  </ul>
                    <%if(request.getAttribute("ex") != null){%>                                           
                                <div class="alert alert-danger">
                                    <%=request.getAttribute("ex")%>
                                </div>
                                <%}if(request.getAttribute("exitoRegistro") != null){%> 
                                <div class="alert alert-danger">
                                    <%=request.getAttribute("exitoRegistro")%>
                                </div>
                  <%}%>
                  
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
   
    
  </div>
  

  
  <!--======= RIGHTS =========--> 
  
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
<script src="js/mainSele.js" type="text/javascript"></script>
<script src="js/sha.js" type="text/javascript"></script>
</body>
</html>