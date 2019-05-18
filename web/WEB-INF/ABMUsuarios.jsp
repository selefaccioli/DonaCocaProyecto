
<%@page import="entity.Usuario"%>
<%@page import="entity.Parametro"%>
<%@page import="entity.Detalle"%>
<%@page import="entity.Torta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
 <jsp:include page="head.jsp"/>
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
        <div class="cuenta">
            <div class="container"> 
                <%if(request.getAttribute("ex")!=null && usuario ==null ){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                    </div>
                </div>
                <%}%>
                <% if(usuarios!=null) { %>
                <div class="row">
                    <h2 class="title text-center">Lista de Usuarios</h2> 
                    <div class="col-sm-12">
                        <div class="table-responsive" style="height:400px; overflow:auto">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
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
                                            <td> - </td>
                                            
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarUsuarioComando"/>
                                                    <input type="hidden" name="idUsuarioEdit" value="0">
                                                    <input type="submit" value="+ Nuevo" class="btn btn-info btn-sm">
                                                </form>
                                            </td>
                                        </tr>
                                        <%for(Usuario u: usuarios){
                                        %>
                                        <tr>
                                            <td><%= u.getId() %></td>
                                            <td><%= u.getNombre()%></td>
                                            <td><%= u.getApellido() %></td>
                                            <td><%= u.getDni() %></td>
                                            <td><%= u.getUsuario() %></td>
                                            <td><%if(u.isActivo()){%><img src="./images/check.png"><%}%></td>
                                            <td><%if(u.isEsAdmin()){%><img src="./images/check.png"><%}%></td>
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
                            </div>
                        </div>
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
                                    <input type="text" class="control form-control" name="nomUsu" placeholder="*"  required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getNombre()%>">
                                </div>
                            </div>
                           
                            </div>
                           
                      
                        <div class="col-sm-6 ">
                            

                            <div class="row">
                                
                                <div class="col-sm-6">
                                    <h6 class="text-left">Apellido</h6>
                                 <input type="text" class="control form-control" name="apeUsu" placeholder="*"  required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%= usuario.getApellido() %>">

                                </div>
                            </div>                        
                            
                              <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">DNI</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="dniUsu" placeholder="*"  required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getDni()%>">
                                </div>
                            </div>
                                
                          </div>
                                
                           <div class="col-sm-6 ">
                            
                             <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Nombre Usuario</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="usuUsu" placeholder="*"  required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getUsuario()%>">
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
                                    <input type="text" class="control form-control" name="mailUsu" placeholder="*"  required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getMail()%>">
                                </div>
                            </div>
                                
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Telefono</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="telUsu" placeholder="*"  required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getTelefono()%>">
                                </div>
                            </div>
                                
                                </div>
                              <div class="col-sm-6 ">
                                <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Direccion</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="dicUsu" placeholder="*"  required value="<%if(usuario!=null || request.getAttribute("usuarioPorAgregar")!=null)%><%=usuario.getDireccion()%>">
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
                <% }%>
            </div>
        </div>
<script src="js/jquery-1.11.3.min.js"></script> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/own-menu.js"></script> 
<script src="js/jquery.lighter.js"></script> 
<script src="js/owl.carousel.min.js"></script> 

<!-- SLIDER REVOLUTION 4.x SCRIPTS  --> 
<!-- SLIDER REVOLUTION 4.x SCRIPTS  --> 
<script type="text/javascript" src="rs-plugin/js/jquery.tp.t.min.js"></script> 
<script type="text/javascript" src="rs-plugin/js/jquery.tp.min.js"></script> 
<script src="js/main.js"></script> 
<script src="../js/mainSele.js" type="text/javascript"></script>
<script src="../js/sha.js" type="text/javascript"></script>
</body>
</html>