// Selecciona el contenedor y el carrusel
const wrapper = document.querySelector(".wrapper");
const carousel = document.querySelector(".carousel");

// Obtiene el ancho de la primera tarjeta en el carrusel
const firstCardWidth = carousel.querySelector(".card").offsetWidth;

// Selecciona los botones de flecha
const arrowBtns = document.querySelectorAll(".wrapper i");

// Crea una matriz de los elementos secundarios del carrusel
const carouselChildrens = [...carousel.children];

// Variables de estado para el arrastre y la reproducción automática
let isDragging = false, isAutoPlay = true, startX, startScrollLeft, timeoutId;

// Calcula el número de tarjetas que caben en el carrusel a la vez
let cardPerView = Math.round(carousel.offsetWidth / firstCardWidth);

// Inserta copias de las últimas tarjetas al principio del carrusel para el desplazamiento infinito
carouselChildrens.slice(-cardPerView).reverse().forEach(card => {
    carousel.insertAdjacentHTML("afterbegin", card.outerHTML);
});

// Inserta copias de las primeras tarjetas al final del carrusel para el desplazamiento infinito
carouselChildrens.slice(0, cardPerView).forEach(card => {
    carousel.insertAdjacentHTML("beforeend", card.outerHTML);
});

// Desplaza el carrusel a la posición adecuada para ocultar las primeras tarjetas duplicadas en Firefox
carousel.classList.add("no-transition");
carousel.scrollLeft = carousel.offsetWidth;
carousel.classList.remove("no-transition");

// Agrega escuchadores de eventos para los botones de flecha para desplazar el carrusel a la izquierda y a la derecha
arrowBtns.forEach(btn => {
    btn.addEventListener("click", () => {
        carousel.scrollLeft += btn.id === "left" ? -firstCardWidth : firstCardWidth;
    });
});

// Función para iniciar el arrastre
const dragStart = (e) => {
    isDragging = true;
    carousel.classList.add("dragging");
    // Registra la posición inicial del cursor y del desplazamiento del carrusel
    startX = e.pageX;
    startScrollLeft = carousel.scrollLeft;
};

// Función para arrastrar
const dragging = (e) => {
    if(!isDragging) return; // Si isDragging es false, retorna desde aquí
    // Actualiza la posición de desplazamiento del carrusel en función del movimiento del cursor
    carousel.scrollLeft = startScrollLeft - (e.pageX - startX);
};

// Función para detener el arrastre
const dragStop = () => {
    isDragging = false;
    carousel.classList.remove("dragging");
};

// Función para el desplazamiento infinito
const infiniteScroll = () => {
    // Si el carrusel está al principio, desplázate al final
    if(carousel.scrollLeft === 0) {
        carousel.classList.add("no-transition");
        carousel.scrollLeft = carousel.scrollWidth - (2 * carousel.offsetWidth);
        carousel.classList.remove("no-transition");
    }
    // Si el carrusel está al final, desplázate al principio
    else if(Math.ceil(carousel.scrollLeft) === carousel.scrollWidth - carousel.offsetWidth) {
        carousel.classList.add("no-transition");
        carousel.scrollLeft = carousel.offsetWidth;
        carousel.classList.remove("no-transition");
    }

    // Borra el tiempo de espera existente y comienza la reproducción automática si el mouse no está sobre el carrusel
    clearTimeout(timeoutId);
    if(!wrapper.matches(":hover")) autoPlay();
};

// Función para la reproducción automática
const autoPlay = () => {
    if(window.innerWidth < 800 || !isAutoPlay) return; // Retorna si la ventana es menor que 800 o isAutoPlay es false
    // Reproduce automáticamente el carrusel cada 2500 ms
    timeoutId = setTimeout(() => carousel.scrollLeft += firstCardWidth, 2500);
};
autoPlay();

// Event listeners para el arrastre, el desplazamiento infinito y la reproducción automática
carousel.addEventListener("mousedown", dragStart);
carousel.addEventListener("mousemove", dragging);
document.addEventListener("mouseup", dragStop);
carousel.addEventListener("scroll", infiniteScroll);
wrapper.addEventListener("mouseenter", () => clearTimeout(timeoutId));
wrapper.addEventListener("mouseleave", autoPlay);

function Toggle2(button){
    // Obtener el elemento i dentro del botón
    var icon = button.querySelector('i');

    if (icon.style.color === "red") {
        icon.style.color = "black";
    } else {
        icon.style.color = "red";
    }
}

function addCard(formulario) {
    var valor = formulario.elements[0].value;
    var url = '/carrito/agregar';
    url = url + '/' + valor;
    $("#resultsBlock").load(url);
}

function toggleAndAddCard(button) {
    var isAuthenticated;

    if (!isAuthenticated) {
        window.alert("Debe iniciar sesión para agregar elementos al carrito.");
        return;
    }  
    Toggle2(button);
    addCard(button.form);
}

