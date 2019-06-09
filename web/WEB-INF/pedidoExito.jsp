
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
    
    <!-- History -->
    <section class="history-block padding-top-100 padding-bottom-100">
      <div class="container">
        <div class="row">
          <div class="col-xs-10 center-block">
           
                <center>
                          <h4>¡Gracias por su compra!</h4>
                          <p> Un email fue enviado a la casilla <b><%= session.getAttribute("mailUsu")  %></b> con los detalles de la compra.<br>
                           </p>  
                </center>
      
            
            
          
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
<script src="js/mainSele.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script>
	if( !window.jQuery ) document.write('<script src="js/jquery-3.0.0.min.js"><\/script>');
</script>
</body>
</html>