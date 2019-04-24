if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', ready);
} else {
    ready();
}

function ready() {
    
    var botonesAgregarCarro = document.getElementsByClassName('cd-add-to-cart');
    for(var i=0; i < botonesAgregarCarro ; i++){
        var botonActual = botonesAgregarCarro[i];
        botonActual.addEventListener('click', agregarAlCarroClick);
    }
        
    
}

function agregarAlCarroClick(event){
    var boton = event.target;
    var shopItem = boton.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement;
    var nombre = shopItem.getElementsByClassName('item-name')[0].innerText;
    var precio = shopItem.getElementsByClassName('price')[0].innerText;
    var imagenSrc = shopItem.getElementsByClassName('item-img')[0].src;
    agregarItemCarro(nombre, precio, imagenSrc);
}

function agregarItemCarro(nombre, precio, imagenSrc){
    var cartRow = document.createElement('li');
    cartRow.innerText = nombre;
    var itemsCarro = document.getElementsByClassName('cart-items')[0];
    itemsCarro.append(cartRow);
}
