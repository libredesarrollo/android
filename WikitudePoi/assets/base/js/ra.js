var World = {
    loaded: false,
    init: function initFn() {
        this.createOverlays();
    },
    createOverlays: function createOverlaysFn() {

        // la data de geolocalizacion, el lugar en donde estemos
        var poiData = {
            "id": 1,
            "latitude": 10.491016,
            "longitude": -66.902061
        };
        
        // creamos un objeto GeoLocation, indicando una localizacion en especifica
        var markerLocation = new AR.GeoLocation(poiData.latitude, poiData.longitude);
        
        // creamos un overlay con una imagen, primero referenciamos la imagen
        var overlayImagenDeitel = new AR.ImageResource("img/home.png");
        // ahora creamos el objeto Drawable con la imagen fuente anterior
        var overlayDrawable = new AR.ImageDrawable(overlayImagenDeitel, 0.2, {offsetX: 0.8, offsetY: 0.8, rotation: 90, scale: 3});
        // creamos un objeto de la clase HtmlDrawable, una pagina web (cuadro informativo)
        var pageInfoDrawable = new AR.HtmlDrawable({
            uri: "home.html"
        }, 1, {offsetX: -1, rotation: 90, opacity: 0.8, scale: 2});

        // le indicamos la localizacion POI y los objetos Drawable ha ser renderizados
        var markerObject = new AR.GeoObject(markerLocation, {
            drawables: {
                cam: [pageInfoDrawable,overlayDrawable]
            }
        });

    }
};

// principal
World.init();
