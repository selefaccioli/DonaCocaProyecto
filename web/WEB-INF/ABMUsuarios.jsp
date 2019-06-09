
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
      <%!ArrayList<Usuario> usuarios;%>
        <%!Parametro param;%>
        <%!Usuario usuario;%>
        <% if(session.getAttribute("parametros")!=null){ param = (Parametro) session.getAttribute("parametros"); }%>
        <% if(session.getAttribute("listaUsuarios")!=null) { usuarios = (ArrayList)session.getAttribute("listaUsuarios");}%>
        <% usuario = (Usuario)session.getAttribute("usuarioEdit"); 
          //ArrayList<Detalle> detallesTor = torta.getDetalles();
            if(request.getAttribute("usuarioPorAgregar")!=null)        
                usuario = (Usuario)request.getAttribute("usuarioPorAgregar");  
                
        %>
	<a name="top" id="top"></a>
	<div class="fw-background">
		<div></div>
	</div>
	<div class="fw-container">
          
                        
      <%if(request.getAttribute("ex")!=null && usuario ==null){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                        <% request.setAttribute("ex", null);}%>
                    </div>
                </div>
	
		
		<div class="fw-body">
			<div class="content">
                              
                            <h2 class="title text-center">Usuarios</h2> 
				
		<% if(usuarios!=null) { %>
                 
				<table id="example" class="display" style="width:100%">
					  <thead>
                                        <tr>
                                       
                                       
                                            <th>Nombre</th>
                                            <th>Apellido</th>
                                            <th>DNI</th>
                                            <th>Nombre Usuario</th>
                                            <th>Es Admin</th>
                                            <th>Activo</th>
                                            <th>Mail</th>
                                            <th>Telefono</th>
                                            <th>Direccion</th>
                                            <th></th>
                                          
                                    </tr>
                                    </thead>
                                    
                                    <tbody>
                                         <tr>
                                           
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                            <td> - </td>
                                           
                                            
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarUsuarioComando"/>
                                                    <input type="hidden" name="idUsuarioEdit" value="0">
                                                    <input type="submit" value="+ Nuevo" class="btn btn-info btn-sm">
                                                </form>
                                            </td>
                                        </tr>
                                         <%for(Usuario u : usuarios){
                                        %>
                                        <tr>
                                          
                                           
                                            <td><%= u.getNombre()%></td>
                                            <td><%= u.getApellido() %></td>
                                            <td><%= u.getDni() %></td>
                                            <td><%= u.getUsuario() %></td>
                                            <td><%if(u.isActivo()){%><img src="images\check.png"><%}%></td>
                                            <td><%if(u.isEsAdmin()){%><img src="images\check.png"><%}%></td>
                                            <td><%= u.getMail() %></td>
                                            <td><%= u.getTelefono() %></td>
                                            <td><%= u.getDireccion() %></td>
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarUsuarioComando"/>
                                                    <input type="hidden" name="idUsuarioEdit" value="<%= u.getId() %>">
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
                    <h2 class="title text-center"><%if(usuario!=null && request.getAttribute("usuarioPorAgregar")==null){%>EDITAR<%} else{%>AGREGAR<%}%> USUARIO</h2>
                    <br/>
                    <form action="CtrlMaestro" method="post" onsubmit="return validarPass();">  
                        <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">ID</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control"  type="text" placeholder="ID (Automático)" maxlength="15" name="ID" readonly="" value="<%if(usuario!=null && request.getAttribute("usuarioPorAgregar")==null )%><%=usuario.getId()%>">
                                </div>
                            </div>
                           
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Nombre</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="nomUsu" placeholder="*" pattern="^[\s\S]{0,30}$" title="Solo se permiten hasta 30 caracteres" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getNombre()%>">
                                </div>
                            </div>
                           
                            </div>
                           
                      
                        <div class="col-sm-6 ">
                            

                            <div class="row">
                                
                                <div class="col-sm-6">
                                    <h6 class="text-left">Apellido</h6>
                                 <input type="text" class="control form-control" name="apeUsu" placeholder="*" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usuario.getApellido() %>">

                                </div>
                            </div>                        
                            
                              <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">DNI</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="dniUsu" placeholder="*" pattern="^\d{0,8}" title="Solo se permiten numeros. Hasta 8 digitos" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getDni()%>">
                                </div>
                            </div>
                                
                          </div>
                                
                           <div class="col-sm-6 ">
                            
                             <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Nombre Usuario</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="usuUsu" placeholder="*" pattern="^[\s\S]{0,20}$" title="Solo se permiten hasta 20 caracteres" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getUsuario()%>">
                                </div>
                            </div>      
                                 
                             <div class="row">
                                <div class="col-sm-12">
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="admin" value="true" <%if((usuario!=null || request.getAttribute("usuarioPorAgregar")!=null) && usuario.isEsAdmin())%>checked<%;%>><h6 class="enLinea">Administrador</h6></label>
                                </div>
                            </div>
                                
                            <div class="row">
                                <div class="col-sm-12">                                                           
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="activo" value="true" <% if((usuario!=null || request.getAttribute("usuarioPorAgregar")!=null) && usuario.isActivo())%>checked<%;%>><h6 class="enLinea">Activo</h6></label>
                                </div>
                            </div>  
                                
                           </div>
                         
                                <div class="col-sm-6 ">
                                
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Mail</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="email" class="control form-control" name="mailUsu" placeholder="*" pattern="^[\s\S]{0,40}$" title="Solo se permiten hasta 40 caracteres" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getMail()%>">
                                </div>
                            </div>
                                
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Telefono</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="telUsu" placeholder="*" pattern="^\d{0,20}" title="Solo se permiten numeros. Hasta 20 digitos" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getTelefono()%>">
                                </div>
                            </div>
                                
                                </div>
                              <div class="col-sm-6 ">
                                <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Direccion</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="dicUsu" placeholder="*" pattern="^[\s\S]{0,45}$" title="Solo se permiten hasta 45 caracteres" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getDireccion()%>">
                                </div>
                            </div>
                                
                              </div>
                            <div class="col-sm-6 ">
                                <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Fecha de Nacimiento</h6>
                                </div>
                                <div class="col-sm-9">
                                     <input type="date" name="fecNac" value="" placeholder="DNI" required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usuario.getFechaNacimiento() %>">
                                   
                                </div>
                            </div>
                                
                              </div>    
                              <div class="col-sm-6 ">
                                <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Como Conoció a Doña Coca</h6>
                                </div>
                                <div class="col-sm-9">
                                       <select class="form-control"  name="conocimiento" required>
                                    <option value="instagram">Instagram</option>     
                                     <option value="facebook">Facebook</option>
                                    <option value="recomendacion">Recomendación</option>
                                    <option value="boca en boca">Boca en Boca</option>
                                    <option value="otro">Otro</option>
                                    </select> 
                                </div>
                            </div>
                                
                              </div>   
                            <br/>
                             <% if(usuario==null) {%>
                              <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-5">
                                    <h6 class="text-left">Contraseña</h6>
                                </div>
                                <div class="col-sm-7">
                                   
                                <input id="passA" type="password"  placeholder="Contraseña*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15" required>
                                <input type="hidden" id="pass1" name="Contra1">           
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-sm-5">
                                    <h6 class="text-left">Confirmar Contraseña</h6>
                                </div>
                                <div class="col-sm-7">
                                 <input id="passB" class="control form-control" type="password" placeholder="Confirmar contraseña*"  pattern="(^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[A-Za-z\d$@$!%*?&]{8,15})" title="Al menos una minuscula, una mayuscula, un digito y longitud entre 8 y 15"  required >
                                 <input type="hidden" id="pass2" name="Contra2">
                                </div>
                            </div>
                            <%}%>
                              </div>
                            
                            <div class="row"> 
                                <div class="col-sm-12">
                                    <%if(request.getAttribute("ex")!=null){ %>                           
                                        <div class="alert alert-danger">
                                            <p class="text-center"><%= request.getAttribute("ex")%></p>
                                        </div>
                                    <%}%>
                                    <%if(request.getAttribute("ExitoUsuario")!=null){
                                        if((Boolean)request.getAttribute("ExitoUsuario")){%>
                                       <div class="alert alert-success">
                                           <p class="text-center">Usuario <%if(usuario==null && request.getAttribute("usuarioPorAgregar")==null){ %>agregado<% }else{%>editado<%}%> con éxito.</p>        
                                        </div>
                                    <% }else if(!(Boolean)request.getAttribute("ExitoUsuario")){ %>
                                        <div class="alert alert-danger ">
                                            <p class="text-center">Ya existe un usuario con el mismo nombre</p>        
                                        </div>           
                                    <% }}%>
                                </div>
                            </div>
                          <input type="hidden" name="form" value="<%if(usuario!=null && request.getAttribute("usuarioPorAgregar")==null) {%>EditarUsuarioComando<%}else{%>AgregarUsuarioComando<%}%>">
                            <button type="submit" class="btn btn-info"><%if(usuario!=null && request.getAttribute("usuarioPorAgregar")==null) {%>Guardar Cambios<%}else{%>Agregar Usuario<%}%></button>
                            
                            
                            
                                
                        
                                
                                
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