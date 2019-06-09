
<%@page import="entity.Detalle"%>
<%@page import="entity.Usuario"%>
<%@page import="entity.Parametro"%>
<%@page import="entity.Parametro"%>
<%@page import="entity.Variante"%>
<%@page import="entity.Torta"%>
<%@page import="entity.Torta"%>
<%@page import="entity.Cupon"%>
<%@page import="entity.Cupon"%>
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
  <head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="M_Adnan">
<title>PAVSHOP - Multipurpose eCommerce HTML5 Template</title>

<!-- SLIDER REVOLUTION 4.x CSS SETTINGS -->
<link rel="stylesheet" type="text/css" href="rs-plugin/css/settings.css" media="screen" />



<!-- Custom CSS -->
<link href="css/font-awesome.min.css" rel="stylesheet" type="text/css">
<link href="css/ionicons.min.css" rel="stylesheet">
<link href="css/main.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<link href="css/responsive.css" rel="stylesheet">


<!-- Bootstrap Core CSS -->

<link href="css/bootstrap.min.css" rel="stylesheet">


<!-- JavaScripts -->
<script src="js/modernizr.js"></script>
<script
  src="https://code.jquery.com/jquery-3.4.0.min.js"
  integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg="
  crossorigin="anonymous"></script>
  


<!-- Online Fonts -->
<link href='https://fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Playfair+Display:400,700,900' rel='stylesheet' type='text/css'>




	
	
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	
	<style type="text/css" class="init">
	
	</style>
	
	<script type="text/javascript" language="javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
	<script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
	
	<script type="text/javascript" class="init">
	
$(document).ready(function() {
	$('#example').DataTable( {
		"pagingType": "full_numbers"
	} );
} );

	</script>


</head>
<body class="wide comments example" onload="scrollDiv()">
     <jsp:include page="header.jsp"/>
     <%!ArrayList<Variante> variantes;%>
        <%!ArrayList<Detalle> detalles;%>
        <%!Parametro param;%>
        <%!Variante variante;%>
        <% if(session.getAttribute("parametros")!=null){ param = (Parametro) session.getAttribute("parametros"); }%>
        <% if(session.getAttribute("variantes")!=null) { variantes = (ArrayList)session.getAttribute("variantes");}%>
        <% if(session.getAttribute("detalles")!=null) { detalles = (ArrayList)session.getAttribute("detalles");}%>
        <% variante = (Variante)session.getAttribute("VarianteEdit"); 
          //ArrayList<Variante> variantesTor = variante.getVariantes();
            if(request.getAttribute("variantePorAgregar")!=null)        
                variante = (Variante)request.getAttribute("variantePorAgregar");  
                
        %>
	<a name="top" id="top"></a>
	<div class="fw-background">
		<div></div>
	</div>
	<div class="fw-container">
          
                        
      <%if(request.getAttribute("ex")!=null && variante ==null){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                        <% request.setAttribute("ex", null);}%>
                    </div>
                </div>
	
		
		<div class="fw-body">
			<div class="content">
                              
                            <h2 class="title text-center">Variantes</h2> 
				
		<% if(variantes!=null) { %>
                 
				<table id="example" class="display" style="width:100%">
					  <thead>
                                        <tr>
                                       
                                            <th>ID</th>
                                            <th>Descripcion</th>
                                            <th>Detalle asociado</th>
                                            <th>Precio</th>
                                            <th>Agregar / Editar</th>
                                           
                                           
                                          
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
                                                    <input type="hidden"  name="form" value="SeleccionarVarianteComando"/>
                                                    <input type="hidden" name="idVarianteEdit" value="0">
                                                    <input type="submit" value="+ Nuevo" class="btn btn-info btn-sm">
                                                </form>
                                            </td>
                                        </tr>
                                         <%for(Variante v: variantes){
                                        %>
                                        <tr>
                                          
                                           <td><%= v.getId() %></td>
                                            <td><%= v.getDescripcion() %></td>
                                            <td><%= v.getDetalle().getNombre() %></td>
                                            <td><%= v.getPrecio() %></td>
                                            
                                            
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarVarianteComando"/>
                                                    <input type="hidden" name="idVarianteEdit" value="<%= v.getId() %>">
                                                    <input type="submit" value="Editar" class="btn btn-default btn-sm">
                                                </form>
                                            </td>
                                        </tr>
                                        <%}%>
                                   
                                    </tbody>
					
					
				</table>
				
				<%} %>
			
			</div>
                                
                                
		</div>
                  
                <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); }%> class="row">
                    <br/>         
                    <h2 class="title text-center"><%if(variante!=null && request.getAttribute("variantePorAgregar")==null){%>EDITAR<%} else{%>AGREGAR<%}%> variante</h2>
                    <br/>
                    <form action="CtrlMaestro" method="post" enctype="multipart/form-data">  
                        <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">ID</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control"  type="text" placeholder="ID (Automático)" maxlength="15" name="ID" readonly="" value="<%if(variante!=null && request.getAttribute("variantePorAgregar")==null )%><%=variante.getId()%>">
                                </div>
                            </div>
                                <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-left">Descripción</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="descVar" placeholder="*" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" required value="<%if(variante!=null || request.getAttribute("variantePorAgregar")!=null)%><%= variante.getDescripcion() %>">
                                </div>
                            </div>
                               <br>
                           <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-left">Precio</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="precVar" placeholder="*" pattern="^^\d{0,4}(\.\d{0,2})?$" title="Numero de maximo 4 digitos. Opcionalmente con 2 decimales (ejemplo: 1000.50)"  required value="<%if(variante!=null || request.getAttribute("variantePorAgregar")!=null)%><%= variante.getPrecio() %>">
                                </div>
                            </div>      
                        <br>
                        <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-left">Detalle asociado</h6>
                                </div>
                            <select class="form-control" name="detVar">
                                <%if(variante!=null || request.getAttribute("variantePorAgregar")!=null){%>
                                <option value="<%= variante.getDetalle().getId() %>"><%= variante.getDetalle().getNombre() %></option><%  
                                for(Detalle d: detalles){  
                                if(d.getId() != variante.getDetalle().getId())%><option  value="<%= d.getId() %>"><%= d.getNombre()  %> </option>
                                 <% }%>
                                
                                
                              <% } else{ %>
                                
                                    <% for(Detalle d: detalles){   %>
                                <option  value="<%= d.getId() %>"><%= d.getNombre()  %> </option>    
                                    
                                     
                                    <% } }%>
                                </select>
                            </div>  
                           <br><br><br><br>  
                           
                        </div>
                       <div class="row">      
                        <div class="col-sm-6 ">
                            
                         
                            <br/>
                           
                            
                            <div class="row"> 
                                <div class="col-sm-12">
                                    <%if(request.getAttribute("ex")!=null){ %>                           
                                        <div class="alert alert-danger">
                                            <p class="text-center"><%= request.getAttribute("ex")%></p>
                                        </div>
                                    <%}%>
                                    <%if(request.getAttribute("ExitoVariante")!=null){
                                        if((Boolean)request.getAttribute("ExitoVariante")){%>
                                       <div class="alert alert-success">
                                           <p class="text-center">variante <%if(variante==null && request.getAttribute("variantePorAgregar")==null){ %>agregada<% }else{%>editada<%}%> con éxito.</p>        
                                        </div>
                                    <% }else if(!(Boolean)request.getAttribute("ExitoVariante")){ %>
                                        <div class="alert alert-danger ">
                                            <p class="text-center">Ya existe una variante con el mismo nombre</p>        
                                        </div>           
                                    <% }}%>
                                </div>
                            </div>
                          <input type="hidden" name="form" value="<%if(variante!=null && request.getAttribute("variantePorAgregar")==null) {%>EditarVarianteComando<%}else{%>AgregarVarianteComando<%}%>">
                            <button type="submit" class="btn btn-info"><%if(variante!=null && request.getAttribute("variantePorAgregar")==null) {%>Guardar Cambios<%}else{%>Agregar variante<%}%></button>
                            
                            
                            
                         </div>        
                        </div>
                                
                                
                     </form>   
                            
                      
                    
                </div>
                                
                                
                </div>
	</div>
	<script type="text/javascript">
$(".dataTables_filter").hide();
</script>
	<script type="text/javascript">
				  var _gaq = _gaq || [];
				  _gaq.push(['_setAccount', 'UA-365466-5']);
				  _gaq.push(['_trackPageview']);

				  (function() {
					var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
					ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
					var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
				  })();
	</script>
   
<script src="js/bootstrap.min.js"></script> 

<script src="js/jquery.lighter.js"></script> 


<!-- SLIDER REVOLUTION 4.x SCRIPTS  --> 





<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>

<script src="js/mainSele.js" type="text/javascript"></script>
</body>
</html>