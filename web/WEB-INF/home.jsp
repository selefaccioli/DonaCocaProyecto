
<%@page import="entity.Variante"%>
<%@page import="entity.Detalle"%>
<%@page import="entity.Torta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
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
<script
  src="https://code.jquery.com/jquery-3.4.0.min.js"
  integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg="
  crossorigin="anonymous"></script>

<!-- Online Fonts -->
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900' rel='stylesheet' type='text/css'>

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
 <!--
<script src="https://code.jquery.com/jquery-3.3.1.min.js" 
        integrity="sha384-tsQFqpEReu7ZLhBV2VZlAu7zcOV+rXbYlF2cqB8txI/8aZajjp4Bqd+V6D5IgvKT" 
        crossorigin="anonymous"></script>


<script>
   $(document).ready(function() {
       $('body').on('click', '.cd-add-to-cart', function(){
           var id = $(this).attr('id'); 
            alert(id);
       }); 
    }); 

</script>--> 

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
  
 <div class="logo">
               <% if(session.getAttribute("exitoTortaAgregada") != null){ %>
        <div class="alert alert-success ">
        <p class="text-center">Torta agregada al carro</p>        
         </div>
               <% session.setAttribute("exitoTortaAgregada", null); } %>
        </div>
        
          <!--======= SUB BANNER =========-->
  <section class="sub-bnr" data-stellar-background-ratio="">
    <div class="position-center-center">
      <div class="container">
        
        
      </div>
    </div>
  </section>
  
  <!-- Content -->
  <div id="content"> 
    
    <!-- Popular Products -->
    <section class="shop-page padding-top-100 padding-bottom-100">
      <div class="container">
        
        
        <!-- Popular Item Slide -->
        <div class="papular-block row row-eq-height"> 
            
            <% if(session.getAttribute("ex") != null) {%>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">            
                    <div class="alert alert-danger fade in"> -->
                        <%= session.getAttribute("ex")%>
                    </div>                    
                </div>
            </div>
        </div><%} else if(request.getAttribute("tortatesting")!= null){
        session.setAttribute("tortatesting", null);
        %>
                        
        <%}else{
       
        
        session.setAttribute("ex", null);
        ArrayList<Torta> listaTortas = (ArrayList)session.getAttribute("listaTortas");
        ArrayList<Detalle> detalles = (ArrayList)session.getAttribute("detalles");
        ArrayList<Variante> variantes = (ArrayList)session.getAttribute("variantes");
    
        for(Torta t: listaTortas){ 
       
       
        %> 
          
          <!-- Item -->
          <div class="col-md-3">
            <div class="item"> 
              <!-- Item img -->
              <div class="item-img"> <img class="img-1" style="width: 263px;height: 340px" src="../images/imagenesdc/<%= t.getRutaImg() %>" alt="">
               
             
              </div>
            
              
              
              <!-- Item Name -->
              <form action="CtrlMaestro" method="post" id="formProdu">
                    <input type="hidden"  name="form" value="RedireccionarTortaComando"/>
                    <input type="hidden"  name="destino" value="/producto.jsp"/>
                    <input type="hidden" name="idTortaEdit" value="<%= t.getId() %>">
                    <center>
                            <input class="btn btn-default add-to-cart linea" type="submit" name="boton"  value="Comprar">
                </center>
                   <div class="item-name"> <a href="#."  ><%= t.getNombre() %> </a>
                  <input type="hidden" name="nombreTorta" id="nombre" value="<%= t.getNombre() %>">
                 
              </div>
              </form>
          </div>
          

               </div>
                  <% }  %>
         
        </div>
      </div>
    </section>
    
   
  </div>
<% }%>
  
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
<script src="../js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
</body>
</html>