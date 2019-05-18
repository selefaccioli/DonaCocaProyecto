<%-- 
    Document   : envios
    Created on : 30/04/2019, 06:56:55
    Author     : selef
--%>

<%@page import="entity.Cupon"%>
<%@page import="entity.Usuario"%>
<%@page import="entity.Variante"%>
<%@page import="logic.CtrlPedido"%>
<%@page import="entity.LineaPedido"%>
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
 <jsp:include page="head.jsp"/>
  
        <body onload="scrollDiv();">
        <jsp:include page="header.jsp"/>
          <div class="row">

   
      <% if(request.getSession().getAttribute("exitoPedido") != null){ %>
            <div class="alert alert-success">
                Pedido realizado con éxito!
            </div>
<% session.setAttribute("exitoPedido", null); }
        else if(session.getAttribute("exitoMail")!= null){%> 
                    <div class="alert alert-success">
                        Un mail ha sido enviado a su casilla de correo!
                    </div>
                    <%session.setAttribute("exitoMail",null);} 
        else if(session.getAttribute("cantidadInvalida")!= null){%> 
                    <div class="alert alert-danger">
                        Por favor ingrese una cantidad válida
                    </div>
                    <%session.setAttribute("cantidadInvalida",null);} 
        else if(session.getAttribute("fechaIncorrecta")!=null){%> 
                    <div class="alert alert-danger">
                        Por favor ingrese una cantidad de días válida. 
                        No se permiten pedidos con menos de 7 dias de anticipación.
                    </div>
                    <%session.setAttribute("fechaIncorrecta", null);}  
else { if(request.getAttribute("ex") != null){%> 
                    <div class="alert alert-danger">
                        <%=request.getAttribute("ex")%>
                    </div>
                    
  <% }} %>  
  </div>
       <%!ArrayList<Pedido> pendientes;%>
        <% pendientes = (ArrayList)request.getSession().getAttribute("pendientes");
        ArrayList<LineaPedido> lineasP;
        
        CtrlPedido cp = new CtrlPedido();
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));
        Pedido pedActual = new Pedido();
        for(Pedido p : pendientes){
            if(p.getId() == idPedido){
                pedActual = p; }
            
        }
       
            lineasP = pedActual.getLineasPedido(); 
            %>
        <div class="cuenta">
            <div class="container"> 
       
        <!-- Payments Steps -->
        
       
        <br><br>
        
       
          
      </div>
          
          
          
          
        </div>
     <section class="chart-page padding-top-100 padding-bottom-100">
      <div class="container"> 
        
        <!-- Payments Steps -->
        <div class="shopping-cart"> 
          
          <!-- SHOPPING INFORMATION -->
          <div class="cart-ship-info">
               
            <div class="row"> 
              
              <!-- ESTIMATE SHIPPING & TAX -->
           
              <div class="col-sm-7">
                  
          
                            
                <h6>Usuario</h6>
                
                    
               
                 <form action="CtrlMaestro" method="post">     
                  <ul class="row">
                   
                    <!-- Name -->
                    <li class="col-md-6">
                          <h6>REGISTRO SEÑA</h6>
                      <label> *TOTAL PEDIDO
                        <input type="text"  required name="totalPed"  <%if(pedActual!= null){ %>value="<%= pedActual.getTotal()   %>" disabled <% } %> placeholder="">
                      </label>
                    </li>
                    <!-- LAST NAME -->
                    <li class="col-md-6">
                      <label> *INGRESAR SEÑA
                        <input type="text"  required name="senaPed" placeholder="" required>
                      </label>
                    </li>
                  
              
                  </ul>
                  <input type="hidden" name="form" value="RegistrarSenaComando">
                  <input type="hidden" name="idPedido" value="<%= pedActual.getId()  %>">
                 <input class="btn btn-default" style="width: 150px; margin-left: 90px;" type="submit" name="sena"  value="Registrar Seña">
 
                  </form> 
              </div>
              
           
              </div>
          
            </div>
          </div>
        </div>
      </div>
    </section>         
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
