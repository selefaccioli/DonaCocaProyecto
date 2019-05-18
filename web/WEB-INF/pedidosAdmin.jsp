<%-- 
    Document   : envios
    Created on : 30/04/2019, 06:56:55
    Author     : selef
--%>

<%@page import="entity.LineaPedido"%>
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <jsp:include page="head.jsp"/>
  
        <body onload="scrollDiv();">
        <jsp:include page="header.jsp"/>
        <%!ArrayList<Pedido> pendientes;%>
        <% pendientes = (ArrayList)request.getSession().getAttribute("pendientes");%>  
        <div class="cuenta">
            <div class="container">
            <%if(request.getAttribute("ExitoEnvio")!=null){%>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-success fade in">
                            Envío registrado.
                        </div>
                    </div>
                </div>
                <%}if(request.getAttribute("ex")!=null){%>
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-danger">
                           <%=request.getAttribute("ex")%>
                        </div>
                    </div>
                </div>
                <%}%>                      
                <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); };%> class="row">
                    <div class="col-lg-12">
                        <% if(pendientes!=null){%>
                        <h2 class="title text-center">Pedidos</h2>
                        <%if(pendientes.isEmpty()){%>
                        <div class="alert alert-success fade<%if(pendientes.isEmpty()){ %> in <%session.setAttribute("pendientes", null);} %>">
                            No existen pedidos pendientes de envío.       
                        </div>
                        <%}else{%>
                       <!-- Tab links -->
                            <div class="tab">
                            <button class="tablinks" id="defaultOpen" onclick="openCity(event, 'realizar')">Pedidos a realizar</button>
                            <button class="tablinks" onclick="openCity(event, 'todos')">Todos los pedidos</button>
                            </div>

                      <!-- Tab content -->
                      <div id="realizar" class="tabcontent">
                            <h3>Pedidos a realizar</h3>
                             <div class="table-responsive">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                        <th>ID</th>
                                        <th>F Pedido</th>
                                        <th>F Entrega</th>
                                        <th>Total</th>
                                        <th>Estado</th>
                                        <th>Total seña</th>
                                        <th>Restan pagar</th>
                                        <th>Usuario</th>
                                        <th>Destino</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Pedido p:pendientes){
                                        if(p.getEstado().equals("Aprobado")){ %>
                                        <tr>
                                            <td><%= p.getId() %></td>
                                            <td><%= p.getFechaPedido() %></td>
                                            <td><%= p.getFechaEntrega() %></td>
                                            <td><%= p.getTotal() %></td>
                                            <td><button type="button" class="btn btn-success btn-sm"><%= p.getEstado() %></button></td>
                                            <td><%= p.getSena() %></td>
                                            <td><%= p.getTotal() - p.getSena() %></td>
                                            <td><%= p.getUsuario().getApellido()%>, <%= p.getUsuario().getNombre()%></td>
                                          
                                            <td><%= p.getUsuario().getDireccion()%></td>
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                                                    <input type="hidden"  name="destino" value="/pedidoDetalle.jsp"/>
                                                    <input type="hidden" name="idPedido" value="<%= p.getId()%>">
                                                    <input type="submit" class="btn btn-default" value="Ver pedido">
                                                </form>
                                            </td>
                                        </tr>
                                        <%}}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                            
                    </div>               
                      
                      
                      
                      
                            <div id="todos" class="tabcontent">
                            <h3>Todos los pedidos</h3>
                             <div class="table-responsive">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                        <th>ID</th>
                                        <th>F Pedido</th>
                                        <th>F Entrega</th>
                                        <th>Total</th>
                                        <th>Estado</th>
                                        <th>Total seña</th>
                                        <th>Condición Seña</th>
                                        <th>Usuario</th>
                                        <th>Destino</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                        <%for(Pedido p:pendientes){%>
                                        <tr>
                                            <td><%= p.getId() %></td>
                                            <td><%= p.getFechaPedido() %></td>
                                            <td><%= p.getFechaEntrega() %></td>
                                            <td><%= p.getTotal() %></td>
                                            <% if(p.getEstado().equals("Pendiente")){   %>
                                            <td><button type="button" class="btn btn-primary btn-sm"><%= p.getEstado() %></button></td>
                                            <% } else if(p.getEstado().equals("Aprobado")){   %>
                                            <td><button type="button" class="btn btn-success btn-sm"><%= p.getEstado() %></button></td>
                                            <% } else if(p.getEstado().equals("Cancelado")){  %>
                                            <td><button type="button" class="btn btn-danger btn-sm"><%= p.getEstado() %></button></td>
                                            <% } else{  %>
                                            <td><button type="button" class="btn btn-info btn-sm"><%= p.getEstado() %></button></td>
                                            <% } %>
                                            <td><%= p.getSena() %></td>
                                            <td><%= p.getCondicionSena() %></td>
                                            <td><%= p.getUsuario().getApellido()%>, <%= p.getUsuario().getNombre()%></td>
                                          
                                            <td><%= p.getUsuario().getDireccion()%></td>
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="RedireccionarComando"/>
                                                    <input type="hidden"  name="destino" value="/pedidoDetalle.jsp"/>
                                                    <input type="hidden" name="idPedido" value="<%= p.getId()%>">
                                                    <input type="submit" class="btn btn-default" value="Ver pedido">
                                                </form>
                                            </td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                            
                            
                            
                            
                            </div>
                                    
                       




                        <%}}%>
                    </div>
                </div>
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
