
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    
 <jsp:include page="head.jsp"/>
<body class="wide comments example">
     <jsp:include page="header.jsp"/>
   <%!ArrayList<Pedido> pendientes;%>
        <% pendientes = (ArrayList)request.getSession().getAttribute("pendientes");
        ArrayList<Pedido> pedidosFiltrados = new ArrayList<Pedido>();
    pedidosFiltrados = (ArrayList)session.getAttribute("pedidosFiltrados");
        if(session.getAttribute("pedidoAmpliado") != null){
            session.setAttribute("pedidoAmpliado", null);
        }
if(session.getAttribute("lineasP")!= null){
    session.setAttribute("lineasP", null);
    
}%> 
	<a name="top" id="top"></a>
	<div class="fw-background">
		<div></div>
	</div>
	<div class="fw-container">
            <% if(pedidosFiltrados != null){
                    if(pedidosFiltrados.isEmpty() && session.getAttribute("provieneDeFiltro")!= null){ %>
                        
                <div class="row">
                    <div class="col-lg-12">
                        <div class="alert alert-danger">
                           <%= "No se encontraron registros para su búsqueda"%>
                           <% session.setAttribute("provieneDeFiltro", null);  %>
                        </div>
                    </div>
                </div> 
                        <%} }%>
                        
      <%if(request.getAttribute("ex")!=null){ %>
                <div class="row">
                    <div class="alert alert-success fade in">
                        <%= request.getAttribute("ex")%>
                        <% request.setAttribute("ex", null);}%>
                    </div>
                </div>
	
		
		<div class="fw-body">
			<div class="content">
                            <h2 class="title text-center">Pedidos a realizar</h2> 
				
		 <form id="search" role="form" style="border:0">
      <div class="col-md-2">
        <ul class="nav nav-stacked">
          <li><strong>Fecha Desde (Entrega)</strong></li>
          <li>
            <input type="date" class="form-control" name="dateFrom" />
          </li>
        </ul>
      </div>

      <div class="col-md-2">
        <ul class="nav nav-stacked">
          <li><strong>Fecha Hasta (Entrega)</strong></li>
          <li>
            <input type="date" class="form-control"  name="dateTo" />
          </li>
        </ul>
      </div>

         <div class="col-md-2">
        <ul class="nav nav-stacked">
          <li><strong>Estado</strong></li>
          <li>
             <select class="form-control" name="estadoSelect">
            
                
                <option value="Aprobado">Aprobado</option>
                
                
               
                </select>
          </li>
        </ul>
      </div>
        
         <div class="col-md-3">
        <ul class="nav nav-stacked">
          <li><strong>Usuario</strong></li>
          <li>
            <input type="text" class="form-control"  name="usuInput" />
          </li>
        </ul>
      </div>
        
      <div class="col-md-3">
        <ul class="nav nav-stacked">
          <li>&nbsp;</li>
          <li>
               <input type="hidden" name="form" value="FiltrosComando">
               <input class="btn btn-primary" type="submit" id="getJsonSrc" name="SearchArealizar" value="Buscar">
          </li>
        </ul>
      </div> <br><br>
                 </form> 	
                 
				<table id="example" class="display" style="width:100%">
					  <thead>
                                        <tr>
                                        <th>ID</th>
                                        <th>Fecha Pedido</th>
                                        <th>Fecha Entrega</th>
                                        <th>Total</th>
                                        <th>Estado</th>
                                        <th>Total seña</th>
                                        <th>Restan pagar</th>
                                        <th>Usuario</th>
                                        <th>Metodo de envío</th>
                                        <th>Dirección usuario</th>
                                     
                                        <th></th>
                                    </tr>
                                    </thead>
                                    
                                    <tbody>
                                      <% if(pedidosFiltrados != null){%>
                                        <%for(Pedido p: pedidosFiltrados){
                                       %>
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
                                            <td><%= p.getTotal() - p.getSena() %></td>
                                            <td><%= p.getUsuario().getApellido()%>, <%= p.getUsuario().getNombre()%></td>
                                            <% if(p.getEnvioDomicilio()){ %>
                                            <td>Envío a domicilio</td>
                                            <% } else{   %>
                                            <td>-</td> <% } %>
                                            <% if( p.getUsuario().getDireccion() != null){ %>
                                           <td><%= p.getUsuario().getDireccion()%></td>
                                           <% } else{   %>
                                           <td></td><% } %>
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="RedireccionarPedidoComando"/>
                                                    <input type="hidden"  name="destino" value="/pedidoDetalle.jsp"/>
                                                    <input type="hidden" name="idPedidoEdit" value="<%= p.getId()%>">
                                                    <input type="submit" class="btn btn-default" value="Ver pedido">
                                                </form>
                                            </td>
                                        </tr>
                                        <%}}
                                    else{%>
                                      <%for(Pedido p: pendientes){
                                          if(p.getEstado().equals("Aprobado")){
                                       %>
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
                                            <td><%= p.getTotal() - p.getSena() %></td>
                                            <td><%= p.getUsuario().getApellido()%>, <%= p.getUsuario().getNombre()%></td>
                                            <% if(p.getEnvioDomicilio()){ %>
                                            <td>Envío a domicilio</td>
                                            <% } else{   %>
                                            <td>Retira en local</td> <% } %>
                                            <td><%= p.getUsuario().getDireccion()%></td>
                                            <td>
                                                <form action="CtrlMaestro" method="post">
                                                    <input type="hidden"  name="form" value="RedireccionarPedidoComando"/>
                                                    <input type="hidden"  name="destino" value="/pedidoDetalle.jsp"/>
                                                    <input type="hidden" name="idPedidoEdit" value="<%= p.getId()%>">
                                                    <input type="submit" class="btn btn-default" value="Ver pedido">
                                                </form>
                                            </td>
                                        </tr>
                                        <%}} }%>
                                    </tbody>
					
					
				</table>
				
				
			<% if(pedidosFiltrados != null){ %>
                         <% session.setAttribute("pedidosFiltrados", null); }%>	
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
</body>
</html>