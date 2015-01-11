var World = {
	initiallyLoadedData: false,

	markerDrawable: null,

	loadPoisFromJsonData: function loadPoisFromJsonDataFn(poiData) {

		// referenciamos la imagen del POI
		World.markerDrawable_idle = new AR.ImageResource("img/marker.png");
		

		var markerLocation = new AR.GeoLocation(poiData.latitude, poiData.longitude, poiData.altitude);
		var markerImageDrawable = new AR.ImageDrawable(World.markerDrawable_idle, 2.5, {
			zOrder: 0,
			opacity: 1.0
		});

		// creamos nuestro POI con la imagen marker.png referenciando dicho POI
		var markerObject = new AR.GeoObject(markerLocation, {
			drawables: {
				cam: [markerImageDrawable]
			}
		});
	},
	locationChanged: function locationChangedFn(lat, lon, alt, acc) {
alert()
		// con esta comprobacion evitamos cargar nuevos POI 
		// cuando cambie la posicion del usuario
		if (!World.initiallyLoadedData) {
 			// creamos un POI muy cercano a la posicion del usuario
			var poiData = {
				"id": 1,
				"longitude": (lon + (Math.random() / 5 - 0.1)),
				"latitude": (lat + (Math.random() / 5 - 0.1)),
				"altitude": 100.0
			};

			World.loadPoisFromJsonData(poiData);
			World.initiallyLoadedData = true;
		}
	},
};
AR.context.onLocationChanged = World.locationChanged;
