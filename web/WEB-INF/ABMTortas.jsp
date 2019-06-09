
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
     <%!ArrayList<Torta> tortas;%>
        <%!ArrayList<Variante> variantes;%>
        <%!Parametro param;%>
        <%!Torta torta;%>
        <% if(session.getAttribute("parametros")!=null){ param = (Parametro) session.getAttribute("parametros"); }%>
        <% if(session.getAttribute("listaTortas")!=null) { tortas = (ArrayList)session.getAttribute("listaTortas");}%>
        <% if(session.getAttribute("variantes")!=null) { variantes = (ArrayList)session.getAttribute("variantes");}%>
        <% torta = (Torta)session.getAttribute("TortaEdit"); 
          //ArrayList<Detalle> detallesTor = torta.getDetalles();
            if(request.getAttribute("tortaPorAgregar")!=null)        
                torta = (Torta)request.getAttribute("tortaPorAgregar");  
                
        %>
	<a name="top" id="top"></a>
	<div class="fw-background">
		<div></div>
	</div>
	<div class="fw-container">
          
                        
      <%if(request.getAttribute("ex")!=null){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                        <% request.setAttribute("ex", null);}%>
                    </div>
                </div>
	
		
		<div class="fw-body">
			<div class="content">
                              
                            <h2 class="title text-center">Tortas</h2> 
				
		<% if(tortas!=null) { %>
                 
				<table id="example" class="display" style="width:100%">
					  <thead>
                                        <tr>
                                       
                                        <th>Nombre</th>
                                        <th>Activo</th>
                                        <th>Editar / Eliminar</th>
                                           
                                        <th></th>
                                    </tr>
                                    </thead>
                                    
                                    <tbody>
                                         <tr>
                                           
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                           
                                            
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarTortaComando"/>
                                                    <input type="hidden" name="idTortaEdit" value="0">
                                                    <input type="submit" value="+ Nuevo" class="btn btn-info btn-sm">
                                                </form>
                                            </td>
                                        </tr>
                                         <%for(Torta t: tortas){
                                        %>
                                        <tr>
                                          
                                            <td><%= t.getNombre() %></td>
                                            <td><%if(t.isActivo()){%><img src="images\check.png"><%}%></td>
                                           
                                             <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarTortaComando"/>
                                                    <input type="hidden" name="idTortaEdit" value="<%= t.getId() %>">
                                                    <input type="submit" value="Editar" class="btn btn-default btn-sm">
                                                </form>
                                            </td>
                                            <td>
                                                <form action="CtrlMaestro" method="post" name="eliminarTorta" onsubmit="return validarEliminacion()">
                                                    <input type="hidden"  name="form" value="EliminarTortaComando"/>
                                                    <input type="hidden" name="idTortaElim" id="idTortaElim" value="<%= t.getId() %>">
                                                    <input type="hidden" name="nomTorta" id="nomTorta" value="<%= t.getNombre() %>">
                                                    <input type="submit" value="Eliminar" class="btn btn-danger btn-sm">
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
                    <h2 class="title text-center"><%if(torta!=null && request.getAttribute("tortaPorAgregar")==null){%>EDITAR<%} else{%>AGREGAR<%}%> TORTA</h2>
                    <br/>
                    <form action="CtrlMaestro" method="post" enctype="multipart/form-data" name="datosTorta"  onsubmit="return validarChecks()">  
                        <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">ID</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control"  type="text" placeholder="ID (Automático)" maxlength="15" name="ID" readonly="" value="<%if(torta!=null && request.getAttribute("tortaPorAgregar")==null )%><%=torta.getId()%>">
                                </div>
                            </div>
                           
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Nombre</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="nomTor" placeholder="*" pattern="^[\s\S]{0,45}$" title="Solo se permiten hasta 45 caracteres" required value="<%if(torta!=null || request.getAttribute("tortaPorAgregar")!=null)%><%=torta.getNombre()%>">
                                </div>
                            </div>
                           
                            
                           
                        </div>
                        <div class="col-sm-6 ">
                            

                                                
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">Foto</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input type="file" class="control form-control" name="imgTor" id="imgTor">
                                </div>
                            </div>
                            
                           <div class="row">
                                <div class="col-sm-12">                                                           
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="activo" value="true" <% if((torta!=null || request.getAttribute("tortaPorAgregar")!=null) && torta.isActivo()== true)%>checked<%;%>><h6 class="enLinea">Activo</h6></label>
                                </div>
                            </div>  
                            
                            <div class="row">
                                <div class="col-sm-6">
                                <h4 class="text-left">Variantes</h4>

                                  <div class="table-responsive" style="height:150px; width: 350px; overflow:auto;">
                                        <table class="table-striped col-lg-12">
                                            <tbody>
                                            <% for(int i=0;i<variantes.size();i++){%>
                                                <tr>
                                                    <td>
                                                        
                                                        <label class="puntero"><input class="check" type="checkbox" name="variantes1" value="<%=variantes.get(i).getId()%>" <%if((torta!=null || request.getAttribute("tortaPorAgregar")!=null) && torta.contieneVariante(variantes.get(i)))%>checked<%;%>><%= variantes.get(i).getDetalle().getNombre()  %>: &nbsp; <%= variantes.get(i).getDescripcion()  %> &nbsp; <%= variantes.get(i).getPrecio()  %></label>

                                                    </td>
                                                </tr>
                                                <%}%>
                                           </tbody>
                                        </table>
                                    </div> 
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
                                    <%if(request.getAttribute("ExitoTorta")!=null){
                                        if((Boolean)request.getAttribute("ExitoTorta")){%>
                                       <div class="alert alert-success">
                                           <p class="text-center">Torta <%if(torta==null && request.getAttribute("tortaPorAgregar")==null){ %>agregada<% }else{%>editada<%}%> con éxito.</p>        
                                        </div>
                                    <% }else if(!(Boolean)request.getAttribute("ExitoTorta")){ %>
                                        <div class="alert alert-danger ">
                                            <p class="text-center">Ya existe una torta con el mismo nombre</p>        
                                        </div>           
                                    <% }}%>
                                </div>
                            </div>
                                <input type="hidden" name="imgf" id="imgf">
                          <input type="hidden" name="form" value="<%if(torta!=null && request.getAttribute("tortaPorAgregar")==null) {%>EditarTortaComando<%}else{%>AgregarTortaComando<%}%>">
                            <button type="submit" id="btnAbmTorta" class="btn btn-info"><%if(torta!=null && request.getAttribute("tortaPorAgregar")==null) {%>Guardar Cambios<%}else{%>Agregar Torta<%}%></button>
                            
                            
                            
                                
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