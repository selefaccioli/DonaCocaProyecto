/*Encriptar Contraseñas*/
function calcHash()
{
    try 
    {
        var hashObj = new jsSHA("SHA-256","TEXT",1);
        
        var hashInput1 = document.getElementById("passA");
        hashObj.update(hashInput1.value);
        document.getElementById("pass1").value = hashObj.getHash("HEX");
               
    } 
    catch(e) 
    {
        
    }
}





/*Validación para el cambio de contraseña*/
function validarCambioPass()
{
    var hashInput1 = document.getElementById("passAnterior");
    var hashObj = new jsSHA("SHA-256","TEXT",1);
    hashObj.update(hashInput1.value);
    document.getElementById("passAnt").value = hashObj.getHash("HEX");
    return validarPass();
}
/*Valida que el usuario quiera eliminar la torta */
function validarEliminacion(){
    var nombre = document.getElementById('nomTorta');
    var id = document.getElementById('idTortaElim');

    if (confirm("Est\u00e1  seguro que desea eliminar la torta?")){ 
       document.eliminarTorta.submit(); 
    }
    else{
        return false;
    }
   
}

/*Validacion checkBoxs*/
function validarChecks()
{
    cant = 0;
   
        for(i=0; i<document.datosTorta.variantes1.length; i++)
    {
        if(document.datosTorta.variantes1[i].checked)
        {
            cant++;
        }
    }
    
    
    
    
    if(cant!==0)
    {
        return true;
    }
    else
    {
        alert("Seleccione alguna variante para la torta");
        return false;
    }
}

function validarChecks2()
{
    var cant = 0;
    
       
    
    
        /*for(i=0; i < $('[name=variantesD]').length; i++){
    
        if($('[name=variantesD]').prop('checked'))
        {
            cant++;
        }
    }*/
    
    if($('.check').length > 0){
        if($('.check:checked').length > 0)
    {
        return true;
    }
    else
    {
        alert("Seleccione alg\u00fan checkbox");
        return false;
    } 
    }
    
   
}
/*Validación de que coinciden las password*/
function validarPass()
{
    var contra1 = document.getElementById('passA');
    var contra2 = document.getElementById('passB');
    if(contra1.value === contra2.value)
    {
        calcHash();
        return true;
    }
    else
    {
        alert("Las contrase\u00f1as deben coincidir");
        return false;
    }
}

/*Socrolls ABMs*/
function scrollDiv()
{
    var div = document.getElementById('Edit');
    div.scrollIntoView(true);
}

/*Desaparecer alertas y avisos*/
$(document).ready(
        
        function()
        {
            
            setTimeout(function(){$(".popover").fadeOut(1000)},4000);
            setTimeout(function(){$(".vanish").fadeOut(1000)},4000);
        });
/*price range*/

 $('#sl2').slider();

	var RGBChange = function() {
	  $('#RGB').css('background', 'rgb('+r.getValue()+','+g.getValue()+','+b.getValue()+')')
	};	
		
/*scroll to top*/
$(document).ready(function(){
        
	$(function () {
		$.scrollUp
                ({
	        scrollName: 'scrollUp', // Element ID
	        scrollDistance: 300, // Distance from top/bottom before showing element (px)
	        scrollFrom: 'top', // 'top' or 'bottom'
	        scrollSpeed: 300, // Speed back to top (ms)
	        easingType: 'linear', // Scroll to top easing (see http://easings.net/)
	        animation: 'fade', // Fade, slide, none
	        animationSpeed: 200, // Animation in speed (ms)
	        scrollTrigger: false, // Set a custom triggering element. Can be an HTML string or jQuery object
					//scrollTarget: false, // Set a custom target element for scrolling to the top
	        scrollText: '<i class="fa fa-angle-up"></i>', // Text for element, can contain HTML
	        scrollTitle: false, // Set a custom <a> title if required.
	        scrollImg: false, // Set true to use image
	        activeOverlay: false, // Set CSS color to display scrollUp active point, e.g '#00FFFF'
	        zIndex: 2147483647 // Z-Index for the overlay
		});
	});
        
        $(function(){
            $(".formNav").hover(function(){$(this).children("i").css("color","#3A5C83");
                                             $(this).children("input").css("color","#3A5C83");
                                             $(this).css("cursor","pointer");},
                                  function(){$(this).children("i").css("color","#696763");
                                             $(this).children("input").css("color","#696763");
                                            });
        });
});


$("#btnAbmTorta").on("click", function () {

   if ($('#imgTor').get(0).files.length === 0) {
      
         
         document.getElementById("imgf").value = "vacio";

    }
    
} ) ;

function openCity(evt, cityName) {
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the button that opened the tab
  document.getElementById(cityName).style.display = "block";
  evt.currentTarget.className += " active";
  
 
}





