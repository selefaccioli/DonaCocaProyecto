
<%@page import="entity.Variante"%>
<%@page import="entity.Detalle"%>
<%@page import="entity.Torta"%>
<%@page import="java.util.ArrayList"%>
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
  
  <!--======= SUB BANNER =========-->
  <% if(session.getAttribute("exitoRegistro") != null){   %>
   <div class="row">
                <div class="col-sm-4">            
                    <div class="alert alert-success fade in"> 
                        Usuario registrado con exito!
                        <% session.setAttribute("exitoRegistro", null);  %>
                    </div>                    
                </div>
            </div> 
        <% } %>
  <section class="sub-bnr" data-stellar-background-ratio="0.5">
    <div class="position-center-center">
     
    </div>
  </section>
  
  <!-- Content -->
 <div id="content"> 
    
    <!-- Popular Products -->
    <section class="shop-page padding-top-100 padding-bottom-100">
      <div class="container">
          
      
        <!-- Popular Item Slide -->
        <div class="papular-block row row-eq-height"> 
            
            <% if(session.getAttribute("ex") != null) {%>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">            
                    <div class="alert alert-danger fade in"> -->
                        <%= session.getAttribute("ex")%>
                    </div>                    
                </div>
            </div>
                    
        </div><%} else if(request.getAttribute("tortatesting")!= null){
        session.setAttribute("tortatesting", null);
        %>
                        
        <%}else{
       
        
        session.setAttribute("ex", null);
        ArrayList<Torta> listaTortas = (ArrayList)session.getAttribute("listaTortas");
        ArrayList<Detalle> detalles = (ArrayList)session.getAttribute("detalles");
        ArrayList<Variante> variantes = (ArrayList)session.getAttribute("variantes");

        if(session.getAttribute("tortaAmpliada") != null || session.getAttribute("detallesTorta") != null ||
            session.getAttribute("tortaVarActivas")!= null){
        session.setAttribute("tortaAmpliada", null);
        session.setAttribute("detallesTorta", null);
        session.setAttribute("tortaVarActivas", null);
        session.setAttribute("cantTor", null);
        }
    
        for(Torta t: listaTortas){ 
        if(t.isActivo()){

       
        %> 
          
          <!-- Item -->
          <div class="col-md-3">
            <div class="item"> 
              <!-- Item img -->
              <div> <img  style="width: 263px;height: 340px" src="images\imagenesdc\<%= t.getRutaImg() %>" alt="">
               
             
              </div>
            
              
              
              <!-- Item Name -->
              <form action="CtrlMaestro" method="post" id="formProdu">
                    <input type="hidden"  name="form" value="RedireccionarTortaComando"/>
                    <input type="hidden"  name="destino" value="/producto.jsp"/>
                    <input type="hidden" name="idTortaEdit" value="<%= t.getId() %>">
                    <center>
                            <input class="btn btn-default add-to-cart linea" type="submit" name="boton"  value="Comprar">
                </center>
                   <div class="item-name"> <a href="#."  ><%= t.getNombre() %> </a>
                  <input type="hidden" name="nombreTorta" id="nombre" value="<%= t.getNombre() %>">
                 
              </div>
              </form>
          </div>
          

               </div>
                  <% } } }  %>
         
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

<script src="js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>

</body>
</html>