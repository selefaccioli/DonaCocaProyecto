
<%@page import="entity.Cupon"%>
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
                                                    <input type="submit" value="+ Nuevo" class="btn btn-info btn-sm">
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
                            <button type="submit" class="btn btn-info"><%if(cupon!=null && request.getAttribute("cuponPorAgregar")==null) {%>Guardar Cambios<%}else{%>Agregar Cupon<%}%></button>
                            
                            
                            
                                
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