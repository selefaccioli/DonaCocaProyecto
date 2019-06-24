
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
  <section class="sub-bnr2" data-stellar-background-ratio="0.5">
    <div class="position-center-center">
    
    </div>
  </section>
  
  <!-- Content -->
  <div id="content"> 
    
    <!-- History -->
    <section class="history-block padding-top-100 padding-bottom-100">
      <div class="container">
        <div class="row">
          <div class="col-xs-10 center-block">
            <div class="col-sm-9 center-block">
              <h4>Pasos para comprar en Doña Coca</h4>
              
                  <ul> 
                    <li>+ Escoja la torta que desee y haga click en el botón de "Comprar"</li> 
                    <li>+ Para poder agregar la torta al carro, primero debe calcular el precio de la misma</li> 
                    <li>+ Elija las características que desee en su torta, de las que se encuentren disponibles para la torta elegida</li> 
                    <li>+ Algunas opciones pueden ser múltiples, con lo cual puede clickear más de una opción. Por ejemplo, el relleno.</li> 
                    <li>+ Haga click en el botón "Calcular Precio"</li>
                    <li>+ Luego, haga click en el botón "Comprar" para añadir su torta al carro</li>
                    <li>+ Diríjase al carrito haciendo click en el mismo y luego en "Ver carro". Allí podrá editar la cantidad que tiene en su carro o eliminar un producto</li>
                    <li>+ Haga click en "Continuar al checkout". Complete los datos necesarios y finalice su pedido</li>
                    <li>+ Un mail llegará a su casilla confirmando su compra</li>
                  </ul>
                  
              
                <br>
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
<script type="text/javascript">
/*==========  Map  ==========*/
var map;
function initialize_map() {
if ($('#map').length) {
	var myLatLng = new google.maps.LatLng(-37.814199, 144.961560);
	var mapOptions = {
		zoom: 17,
		center: myLatLng,
		scrollwheel: false,
		panControl: false,
		zoomControl: true,
		scaleControl: false,
		mapTypeControl: false,
		streetViewControl: false
	};
	map = new google.maps.Map(document.getElementById('map'), mapOptions);
	var marker = new google.maps.Marker({
		position: myLatLng,
		map: map,
		tittle: 'Envato',
		icon: './images/map-locator.png'
	});
} else {
	return false;
}}
google.maps.event.addDomListener(window, 'load', initialize_map);
</script>
</body>
</html>