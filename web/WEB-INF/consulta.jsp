
<%@page import="entity.Usuario"%>
<%@page import="logic.CtrlPedido"%>
<%@page import="entity.LineaPedido"%>
<%@page import="entity.Pedido"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="head.jsp"/>
<body>

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
  

  
  <!-- Content -->
  <div id="content"> 
      <div class="row">
            <% if(request.getAttribute("ex")!= null){%> 
                    <div class="alert alert-danget">
                     <%=request.getAttribute("ex")%>
                    </div>
            <%request.setAttribute("ex",null);} %>   
           <% if(request.getAttribute("exitoConsulta")!= null){%> 
                    <div class="alert alert-success">
                     Consulta enviada con éxito
                    </div>
            <%request.setAttribute("exitoConsulta",null);} %>  
      </div>
     <%!ArrayList<Pedido> pendientes;%>
        <% pendientes = (ArrayList)request.getSession().getAttribute("pendientes");
        ArrayList<LineaPedido> lineasP = new ArrayList<LineaPedido>();
        
        CtrlPedido cp = new CtrlPedido();
        Pedido pedActual = (Pedido)session.getAttribute("pedidoAmpliado");
        //Pedido pedActual = (Pedido)request.getSession().getAttribute("pedConSena");
         Usuario usu = (Usuario)session.getAttribute("usuario");
        lineasP = pedActual.getLineasPedido();
              
   
        
      
           double subtotal = 0.0f;
              for(LineaPedido linea: lineasP){
          
          subtotal = subtotal + linea.getSubtotal();
              }
        
        
        %> 
    <!--======= CONATACT  =========-->
    <section class="contact padding-top-100 padding-bottom-100">
      <div class="container">
        <div class="contact-form">
          <h5>Escriba su consulta</h5>
          <div class="row">
            <div class="col-md-8"> 
              
              <!--======= Success Msg =========-->
              <div id="contact_message" class="success-msg"> <i class="fa fa-paper-plane-o"></i>Thank You. Your Message has been Submitted</div>
              
              <!--======= FORM  =========-->
              <form role="form" action="CtrlMaestro" id="contact_form" class="contact-form" method="post">
                <ul class="row">
                  <li class="col-sm-6">
                    <label>Nombre Completo del cliente *
                        <%String nombreUsu = pedActual.getUsuario().getApellido() + ", " + pedActual.getUsuario().getNombre();   %>
                      <input type="text" class="form-control" name="nombre"  placeholder=""  value="<%= nombreUsu %>">
                    </label>
                  </li>
                  <li class="col-sm-6">
                    <label>Email *
                      <input type="text" class="form-control" name="email"  placeholder="" value="<%= pedActual.getUsuario().getMail() %>" >
                    </label>
                  </li>
                 
                  <li class="col-sm-6">
                    <label>Asunto *
                      <input type="text" class="form-control" name="asunto" placeholder="">
                    </label>
                  </li>
                  <li class="col-sm-12">
                    <label>Mensaje *
                      <textarea class="form-control" name="message" rows="5" placeholder=""></textarea>
                    </label>
                  </li>
                  <li class="col-sm-12">
                      <input type="hidden" name="form" value="EnviarConsultaComando">
                    <button type="submit" value="submit" class="btn">Enviar Consulta</button>
                  </li>
                </ul>
              </form>
            </div>
            
       
          </div>
        </div>
      </div>
    </section>

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

<!-- Begin Map Script --> 
<script type='text/javascript' src='http://maps.google.com/maps/api/js?sensor=false'></script> 
<script src="js/main.js"></script> 
<script src="js/mainSele.js" type="text/javascript"></script>
</body>
</html>