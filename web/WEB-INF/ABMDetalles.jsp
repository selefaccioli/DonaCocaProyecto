
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
  
        <%!ArrayList<Detalle> detalles;%>
        <%!ArrayList<Variante> variantes;%>
        <%!Parametro param;%>
        <%!Detalle detalle;%>
        <% if(session.getAttribute("parametros")!=null){ param = (Parametro) session.getAttribute("parametros"); }%>
        <% if(session.getAttribute("variantes")!=null) { variantes = (ArrayList)session.getAttribute("variantes");}%>
        <% if(session.getAttribute("detalles")!=null) { detalles = (ArrayList)session.getAttribute("detalles");}%>
        <% detalle = (Detalle)session.getAttribute("DetalleEdit"); 
          //ArrayList<Detalle> detallesTor = detalle.getDetalles();
            if(request.getAttribute("detallePorAgregar")!=null)        
                detalle = (Detalle)request.getAttribute("detallePorAgregar");  
                
        %>
        <div class="cuenta">
            <div class="container"> 
                <%if(request.getAttribute("ex")!=null && detalle ==null ){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                    </div>
                </div>
                <%}%>
                <% if(detalles!=null) { %>
                <div class="row">
                    <h2 class="title text-center">Lista de detalles</h2> 
                    <div class="col-sm-12">
                        <div class="table-responsive" style="height:400px; overflow:auto">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Nombre</th>
                                            <th>Elige Usuario</th>
                                            <th>Agregar / Editar</th>
                                           
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
                                                    <input type="hidden"  name="form" value="SeleccionarDetalleComando"/>
                                                    <input type="hidden" name="idDetalleEdit" value="0">
                                                    <input type="submit" value="+ Nuevo" class="btn btn-info btn-sm">
                                                </form>
                                            </td>
                                        </tr>
                                        <%for(Detalle d: detalles){
                                        %>
                                       
                                        <tr> 
                                            <td><%= d.getId()%></td>
                                            <td><%= d.getNombre()%></td>
                                            <td><%if(d.getEligeUsuario()){%><img src="./images/check.png"><%}%></td>
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="SeleccionarDetalleComando"/>
                                                    <input type="hidden" name="idDetalleEdit" value="<%= d.getId() %>">
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
                    <h2 class="title text-center"><%if(detalle!=null && request.getAttribute("detallePorAgregar")==null){%>EDITAR<%} else{%>AGREGAR<%}%> detalle</h2>
                    <br/>
                    <form action="CtrlMaestro" method="post" enctype="multipart/form-data">  
                        <div class="col-sm-6 ">
                            <div class="row">
                                <div class="col-sm-3">
                                    <h4 class="text-left">ID</h4>
                                </div>
                                <div class="col-sm-9">
                                    <input class="control form-control"  type="text" placeholder="ID (Automático)" maxlength="15" name="ID" readonly="" value="<%if(detalle!=null && request.getAttribute("detallePorAgregar")==null )%><%=detalle.getId()%>">
                                </div>
                            </div>
                           
                            <div class="row">
                                <div class="col-sm-3">
                                    <h6 class="text-left">Nombre</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="nomDet" placeholder="*"  required value="<%if(detalle!=null || request.getAttribute("detallePorAgregar")!=null)%><%=detalle.getNombre()%>">
                                </div>
                            </div>
                          <div class="row">
                                <div class="col-sm-12">                                                           
                                    <label class="puntero"><input class="enLinea" type="checkbox" name="eligeUsu" value="true" <% if((detalle!=null || request.getAttribute("detallePorAgregar")!=null) && detalle.getEligeUsuario()== true)%>checked<%;%>><h6 class="enLinea">Elige Usuario</h6></label>
                                </div>
                           </div>  <br> 
                        
                             
                           
                        </div>
                        <div class="col-sm-6 ">
                            
                         
                            <br/>
                           
                            
                            <div class="row"> 
                                <div class="col-sm-12">
                                    <%if(request.getAttribute("ex")!=null){ %>                           
                                        <div class="alert alert-danger">
                                            <p class="text-center"><%= request.getAttribute("ex")%></p>
                                        </div>
                                    <%}%>
                                    <%if(request.getAttribute("ExitoDetalle")!=null){
                                        if((Boolean)request.getAttribute("ExitoDetalle")){%>
                                       <div class="alert alert-success">
                                           <p class="text-center">detalle <%if(detalle==null && request.getAttribute("detallePorAgregar")==null){ %>agregado<% }else{%>editado<%}%> con éxito.</p>        
                                        </div>
                                    <% }else if(!(Boolean)request.getAttribute("ExitoDetalle")){ %>
                                        <div class="alert alert-danger ">
                                            <p class="text-center">Ya existe una detalle con el mismo nombre</p>        
                                        </div>           
                                    <% }}%>
                                </div>
                            </div>
                          <input type="hidden" name="form" value="<%if(detalle!=null && request.getAttribute("detallePorAgregar")==null) {%>EditarDetalleComando<%}else{%>AgregarDetalleComando<%}%>">
                            <button type="submit" class="btn btn-info"><%if(detalle!=null && request.getAttribute("detallePorAgregar")==null) {%>Guardar Cambios<%}else{%>Agregar detalle<%}%></button>
                            
                            
                            
                                
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