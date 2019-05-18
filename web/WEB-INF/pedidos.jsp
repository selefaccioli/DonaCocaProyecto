<%-- 
    Document   : pedidos
    Created on : 29/04/2019, 10:16:53
    Author     : selef
--%>

<%@page import="entity.Usuario"%>
<%@page import="logic.CtrlPedido"%>
<%-- 
    Document   : pedidos
    Created on : 30/04/2019, 06:48:22
    Author     : selef
--%>


<%@page import="entity.LineaPedido"%>
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<html>
 <jsp:include page="head.jsp"/>
    <body onload="scrollDiv();">
        <jsp:include page="header.jsp"/>
        <%  Usuario usu = (Usuario)request.getSession().getAttribute("usuario");
            CtrlPedido ctrlP = new CtrlPedido();
         ArrayList<Pedido> pedidosUsu = ctrlP.obtenerPedidos(usu.getId());%>
        <% %>
        <div class="cuenta">
            <div class="container"> 
                <div <%if(session.getAttribute("Scroll")!=null){%> id="Edit" <%session.setAttribute("Scroll", null); };%> class="row">
                    <div class="col-lg-12">
                        <%if(request.getAttribute("ex")!=null){%>
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="alert alert-danger">
                                    <%=request.getAttribute("ex")%>
                                </div>
                            </div>
                        </div>
                        <%} else if(pedidosUsu!=null){%>
                        <h2 class="title text-center">Historial de Pedidos</h2>
                        <%if(pedidosUsu.isEmpty()){%>
                        <div class="alert alert-danger">
                            Usted nunca ha realizado pedidos.       
                        </div>
                        <%}else{%>
                        <div class="table-responsive">
                            <div class="table-striped">
                                <table class="table table-striped">
                                    <thead>
                                        <tr>
                                            <th>ID Pedido</th>
                                            <th>F Pedido</th>
                                            <th>F Entrega</th>
                                            <th>Total</th>
                                            <th>Tortas</th>
                                            <th>Estado</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%for(Pedido p: pedidosUsu){%>
                                        <tr>
                                            <td><%= p.getId()  %></td>
                                            <td><%= p.getFechaPedido() %></td>
                                            <td><%= p.getFechaEntrega() %></td>
                                             <td><%= p.getTotal() %></td>
                                            <td>
                                            <% for(LineaPedido lp: p.getLineasPedido()){
                                              lp.getTorta().getNombre();   }%> <br>                                          
                                          
                                            </td>
                                            
                                            <td><%= p.getEstado()%></td>
                                        </tr>
                                        <%}%>
                                    </tbody>
                                </table>
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
<script src="js/main.js"></script>
<script src="../js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
    </body>
   
</html>

