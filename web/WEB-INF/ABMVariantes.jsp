
<%@page import="entity.Detalle"%>
<%@page import="entity.Variante"%>
<%@page import="entity.Parametro"%>
<%@page import="entity.Torta"%>
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
        <div class="cuenta">
            <div class="container"> 
                <%if(request.getAttribute("ex")!=null && variante ==null ){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                    </div>
                </div>
                <%}%>
                <% if(variantes!=null) { %>
                <div class="row">
                    <h2 class="title text-center">Lista de variantes</h2> 
                    <div class="col-sm-12">
                        <div class="table-responsive" style="height:400px; overflow:auto">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Descripcion</th>
                                            <th>Detalle asociado</th>
                                            <th>Precio</th>
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
                            </div>
                        </div>
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
                                    <input type="text" class="control form-control" name="descVar" placeholder="*"  required value="<%if(variante!=null || request.getAttribute("variantePorAgregar")!=null)%><%= variante.getDescripcion() %>">
                                </div>
                            </div>
                               <br>
                           <div class="row">
                                <div class="col-sm-6">
                                    <h6 class="text-left">Precio</h6>
                                </div>
                                <div class="col-sm-9">
                                    <input type="text" class="control form-control" name="precVar" placeholder="*"  required value="<%if(variante!=null || request.getAttribute("variantePorAgregar")!=null)%><%= variante.getPrecio() %>">
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