
<%@page import="entity.Variante"%>
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
        <div class="cuenta">
            <div class="container"> 
                <%if(request.getAttribute("ex")!=null && torta ==null ){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                    </div>
                </div>
                <%}%>
                <% if(tortas!=null) { %>
                <div class="row">
                    <h2 class="title text-center">Lista de Tortas</h2> 
                    <div class="col-sm-12">
                        <div class="table-responsive" style="height:400px; overflow:auto">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Precio</th>
                                            <th>Activo</th>
                                            <th>Agregar / Editar</th>
                                            <th>Eliminar</th>
                                           
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
                                            <td><%= t.getId()%></td>
                                            <td><%= t.getNombre()%></td>
                                            <td><%if(t.isActivo()){%><img src="./images/check.png"><%}%></td>
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
                            </div>
                        </div>
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
                                    <input type="text" class="control form-control" name="nomTor" placeholder="*"  required value="<%if(torta!=null || request.getAttribute("tortaPorAgregar")!=null)%><%=torta.getNombre()%>">
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
<script src="../js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
</body>
</html>