var World = {
    loaded: false,

    init: function initFn() {
        this.createOverlays();
    },

    createOverlays: function createOverlaysFn() {

	// creamos un objeto de la clase HtmlDrawable
	htmlDrawable = new AR.HtmlDrawable({
	   html:"<div style='font-size:42px;color:#FFF;background: #068;border: 10px solid #059;border-radius: 5px;padding: 5px;height: auto;'><pre>Este libro presenta las tecnologías </pre><pre>de vanguardia para estudiantes, </pre><pre>profesores y desarrolladores de </pre><pre>software.</pre></div>"
	}, 1,{offsetX : -1,rotation:90,opacity : 0.8,scale : 2});

        // inicializamos el Tracker con el Target collection (nuestra caratula del libro Deitel)
        this.tracker = new AR.Tracker("assets/targetcollection.wtc", {
            onLoaded: this.worldLoaded
        });

	// indicamos el nombre del Target en el Tracker, basicamente al momento de que el Target sea rastreado por el Tracker el objeto htmlDrawable sera renderizado en la pantalla del dispositivo
        var javaLibro = new AR.Trackable2DObject(this.tracker, "javaLibro", {
            drawables: {
                cam: htmlDrawable
            }
        });
    }
};

// principal
World.init();
