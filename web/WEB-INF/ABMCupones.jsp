
<%@page import="entity.Cupon"%>
<%@page import="entity.Parametro"%>
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
 <body onload="scrollDiv()">
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
  
        <%!ArrayList<Cupon> cupones;
        Cupon cupon;%>
        <% if(session.getAttribute("listaCupones")!=null) { cupones = (ArrayList)session.getAttribute("listaCupones");}%>
        <% cupon = (Cupon)session.getAttribute("CuponEdit"); 
            if(request.getAttribute("cuponPorAgregar")!=null)        
                cupon = (Cupon)request.getAttribute("cuponPorAgregar");  
                
        %>
        <div class="cuenta">
            <div class="container"> 
                <%if(request.getAttribute("ex")!=null && cupon ==null ){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                    </div>
                </div>
                <%}%>
                <% if(cupones!=null) { %>
                <div class="row">
                    <h2 class="title text-center">Lista de cupones</h2> 
                    <div class="col-sm-12">
                        <div class="table-responsive" style="height:400px; overflow:auto">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Codigo</th>
                                            <th>Porcentaje de descuento</th>
                                            <th>Activo</th>
                                           
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarCuponComando"/>
                                                    <input type="hidden" name="idCuponEdit" value="0">
                                                    <input type="submit" value="+ Nuevo">
                                                </form>
                                            </td>
                                        </tr>
                                        <%for(Cupon c: cupones){
                                        %>
                                        <tr>
                                            <td><%= c.getId()%></td>
                                            <td><%= c.getCodigo() %></td>
                                            <td><%= c.getPorcDescuento()%></td>
                                            <td><%if(c.isActivo()){%><img src="./images/check.png"><%}%></td>
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarCuponComando"/>
                                                    <input type="hidden" name="idCuponEdit" value="<%= c.getId() %>">
                                                    <input type="submit" value="Editar">
                                                </form>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div> 
                </div>
                <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); }%> class="row">
                    <br/>         
                    <h2 class="title text-center"><%if(cupon!=null && request.getAttribute("cuponPorAgregar")==null){%>EDITAR<%} else{%>AGREGAR<%}%> CUPON</h2>
                    <br/>
                    <form action="CtrlMaestro" method="post" enctype="multipart/form-data">  
                        <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">ID</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control"  type="text" placeholder="ID (Automático)" maxlength="15" name="ID" readonly="" value="<%if(cupon!=null && request.getAttribute("cuponPorAgregar")==null )%><%= cupon.getId() %>">
                                </div>
                            </div>
                           
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Codigo</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="codCup" placeholder="*"  required value="<%if(cupon!=null || request.getAttribute("cuponPorAgregar")!=null)%><%=cupon.getCodigo()%>">
                                </div>
                            </div>
                           
                            
                           
                        </div>
                        <div class="col-sm-6 ">
                            

                            <div class="row">
                                
                                <div class="col-sm-6">
                                    <h4 class="text-left">Porcentaje de descuento</h4>
                                    <input type="text" class="control form-control" name="porcCup" placeholder="* (En $)" pattern="^[0-9]+(\.[0-9]+)?$" title="Numero" required value="<%if(cupon!=null || request.getAttribute("cuponPorAgregar")!=null)%><%= cupon.getPorcDescuento() %>">
                                </div>
                            </div>                        
                            
                            
                           <div class="row">
                                <div class="col-sm-12">                                                           
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="activo" value="true" <% if((cupon!=null || request.getAttribute("cuponPorAgregar")!=null) && cupon.isActivo()== true)%>checked<%;%>><h6 class="enLinea">Activo</h6></label>
                                </div>
                            </div>  
                            
                            
                            <br/>
                           
                            
                            <div class="row"> 
                                <div class="col-sm-12">
                                    <%if(request.getAttribute("ex")!=null){ %>                           
                                        <div class="alert alert-danger">
                                            <p class="text-center"><%= request.getAttribute("ex")%></p>
                                        </div>
                                    <%}%>
                                    <%if(request.getAttribute("ExitoCupon")!=null){
                                        if((Boolean)request.getAttribute("ExitoCupon")){%>
                                       <div class="alert alert-success">
                                           <p class="text-center">Cupon <%if(cupon==null && request.getAttribute("cuponPorAgregar")==null){ %>agregado<% }else{%>editado<%}%> con éxito.</p>        
                                        </div>
                                    <% }else if(!(Boolean)request.getAttribute("ExitoCupon")){ %>
                                        <div class="alert alert-danger ">
                                            <p class="text-center">Ya existe un cupon con el mismo codigo</p>        
                                        </div>           
                                    <% }}%>
                                </div>
                            </div>
                          <input type="hidden" name="form" value="<%if(cupon!=null && request.getAttribute("cuponPorAgregar")==null) {%>EditarCuponComando<%}else{%>AgregarCuponComando<%}%>">
                            <button type="submit" class="btn btn-default"><%if(cupon!=null && request.getAttribute("cuponPorAgregar")==null) {%>Guardar Cambios<%}else{%>Agregar Cupon<%}%></button>
                            
                            
                            
                                
                        </div>
                                
                                
                     </form>   
                            
                      
                    
                </div>
                <% }%>
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
<script src="../js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
    </body>
</html>