
<%@page import="logic.CtrlVariante"%>
<%@page import="logic.CtrlDetalle"%>
<%@page import="entity.Variante"%>
<%@page import="entity.Detalle"%>
<%@page import="entity.Torta"%>
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
  <div class="logo">
               <% if(session.getAttribute("exitoTortaAgregada") != null){ %>
        <div class="alert alert-success ">
        <p class="text-center">Torta agregada al carro</p>        
         </div>
               <% session.setAttribute("exitoTortaAgregada", null); } %>
        </div>
  
  <% if(session.getAttribute("tortaAmpliada") != null) {
      
        Torta t = (Torta)session.getAttribute("tortaAmpliada");
        ArrayList<Torta> listaTortas = (ArrayList)session.getAttribute("listaTortas");
        ArrayList<Detalle> detalles = (ArrayList)session.getAttribute("detalles");
        ArrayList<Variante> variantes = (ArrayList)session.getAttribute("variantes");
        CtrlDetalle ctrlD = new CtrlDetalle();
        CtrlVariante ctrlV = new CtrlVariante();
        ArrayList<Detalle> detallesTorta = (ArrayList)session.getAttribute("detallesTorta");
        ArrayList<Variante> varianteDetalle =new ArrayList<Variante>(); 
        Torta tortaVarActivas = (Torta)session.getAttribute("tortaVarActivas");
        
       
       
        %> 
  
  <!-- Content -->
  <div id="content"> 
    
    <!-- Popular Products -->
    <section class="padding-top-25 padding-bottom-100">
      <div class="container"> 
        
        <!-- SHOP DETAIL -->
        <div class="shop-detail">
          <div class="row"> 
            
            <!-- Popular Images Slider -->
            <div class="col-md-7"> 
              
              <!-- Images Slider -->
              <div class="images-slider">
                <ul class="slides">
                  <li> <img class="img-responsive" style="width: 649px;height: 662px" src="images\imagenesdc\<%=  t.getRutaImg() %>"  alt=""> </li>
                </ul>
              </div>
           
            </div>
            
            <!-- COntent -->
             <form action="CtrlMaestro" name="datosVariantes" method="post" enctype="multipart/form-data" onsubmit="return validarChecks2()"> 
            <div class="col-md-4">
                <% if(session.getAttribute("ex")!= null){   %>
                <div class="container">
            <div class="row">
                <div class="col-sm-4">            
                    <div class="alert alert-danger fade in"> -->
                        <%= session.getAttribute("ex")%>
                    </div>                    
                </div>
            </div>
        </div> <%} %>
              <h4><%= t.getNombre()  %></h4>
              <h6>Cantidad</h6>
              
               <div class="cart_quantity_button">
                  
                   
                   <input  min="1"  required  class="tamanio cart_quantity_input" type="number"  name="cantidad" <%if(session.getAttribute("cantTor") != null){ %> value="<%= session.getAttribute("cantTor")  %>"  <% } %> />
                   
                   
              </div>
             
              <% if(session.getAttribute("totalTor") != null){ 
              %>
              <span class="price"><small>$</small><%= session.getAttribute("totalTor") %></span> 
             
              <%} 


              if(detallesTorta.size() > 0){ %>
               
               <%  for(Detalle d: detallesTorta){%>
                <h6><%= d.getNombre() %></h6> 
                <% varianteDetalle = ctrlV.obtenerVariantesDetalleTorta(d.getId(), t.getId());
                 if(d.getMultiple()){ %>
                     <div class="table-responsive" style="height:80px; overflow:auto;">
                                        <table class="table-striped col-lg-12">
                                            <tbody>
                                            <% for(Variante vd: varianteDetalle){%>
                                                <tr>
                                                    <td>
                                                        
                                                        <label class="puntero"><input class="check" type="checkbox" name="variantesD" value="<%= vd.getId() %>" <% if(tortaVarActivas != null && tortaVarActivas.contieneVariante(vd)){%> checked <% }; %>><%= vd.getDescripcion()  %></label>

                                                    </td>
                                                </tr>
                                                <%}%>
                                           </tbody>
                                        </table>
                                           <br>
                                    </div> 
                <% }else{ %>

                <select class="form-control" name="<%= d.getNombre()  %>">
                <% if(tortaVarActivas != null){
                    for(Variante vd: varianteDetalle){%>
                <% if(tortaVarActivas.contieneVariante(vd)){%>
                <option value="<%= vd.getId() %>"><%=  vd.getDescripcion()  %></option>
                <% for(Variante vd1: varianteDetalle){  
                    if(vd1 != vd){%>
                <option value="<%= vd1.getId() %>"><%=  vd1.getDescripcion()  %></option>
                    <% }}}}}
                else{
                    for(Variante vd : varianteDetalle){%>
                    <option value="<%= vd.getId() %>"><%=  vd.getDescripcion()  %></option> <% }} %>
                </select>
                <br>
             <% }}  }%>
              
              <!-- Short By -->
              <div class="some-info">
                  <input type="hidden" name="form" value="CalcularPrecioComprarComando">
                  <input type="hidden" name="idTorta" value="<%= t.getId() %>">
                  <input class="btn btn-default add-to-cart linea" type="submit" name="calcPrecio"  value="Calcular Precio"><br><br>
                  <% if(session.getAttribute("totalTor") != null){   %>
                  <input class="btn btn-default add-to-cart linea" type="submit" name="comprar"  value="Comprar">
                  <% } else{ %>
                  <input class="btn btn-default add-to-cart linea" type="submit" name="comprar" disabled value="Comprar">
                  <% } %>
                  
                   <% session.setAttribute("totalTor", null);  %> 
             </div>
            </div>
             
             </form>
           
          </div>
        </div>
        
        
      </div>
    </section>
   
  </div>
  <% } %>
 

  
  <!--======= RIGHTS =========--> 
  
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