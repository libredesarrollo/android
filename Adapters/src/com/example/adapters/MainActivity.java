<p>Podemos decir que el objetivo principal de la mayoría de las aplicaciones desarrolladas para dispositivos móviles incluyendo Android, es la de consumir contenido: noticias, notas u otros listados de información son algunos ejemplos; los llamados Adaptadores o  Adapters son una potente herramienta provista en la API de android.widget que podemos usar para esta labor; consiste en mostrar un conjunto de información a través de listas o grids (personalizables a nivel de definición, estilo y eventos); en esta entrega veremos como crear un Adaptador o Adapter personalizado para Android.</p>

<h2>¿Qué es un Adapter según la API de Android?</h2>

<p>En la API de Android definen los adapters como:</p>

<p class="red italic">An Adapter object acts as a bridge between an AdapterView and the underlying data for that view. The Adapter provides access to the data items. The Adapter is also responsible for making a View for each item in the data set.</p>

<p>Una posible traducción de lo anterior sería como: Un objeto Adapter actúa como un puente entre el AdapterView y los datos de una vista. El Adapter proporciona acceso a cada item. El adaptador también es responsable de hacer una vista para cada producto en el conjunto de datos.<p>

<p>Tenemos vários elementos aquí:</p>

<ul class="number">
    <li><span class="black">Colección de datos</span>: Datos que se desean mostrar; generalmente están almacenados en un List, ArrayList o array.</li>
    <li><span class="black">Vista</span>: Definición de cómo lucirá un elemento de la lista mediante un layout XML.</li>
    <li><span class="black">Adapter</span>: Finalmente definimos el puente entre los datos y la vista; el Adapter se encarga de tomar la vista y repetirla para cada colección de datos además de interactuar con los elementos que componen la vista.</li>
</ul>

<p>Básicamente podemos usar una interfaz de un Adapter para crear y mostrar listados de información de una lista de objetos u otro tipo de dato de una forma eficiente e interactuar con ella mediante eventos.</p>

<h2>Definiendo un Adapter en cuatro pasos...</h2>

<p>Para poder trabajar con los Adapter que veremos en esta entrega, tenemos que realizar los siguientes pasos:</p>

<ul class="number">
    <li><a href="#uno">Definir el estilo de nuestra lista a través de un layout (XML).</a></li>
    <li><a href="#dos">Crear una clase (Adapter) que extienda de ArrayAdapter&#60;?&#62;, en donde ? puede ser un objeto o un tipo de dato.</a></li>
    <li><a href="#tres">Agregar un Tag o elemento ListView u GridView según sea el caso al layout de la actividad en la cual deseamos incluir el listado; si deseamos que nuestro listado tenga forma de lista:</a>

        <img src = "/public/images/example/android/adapter/listview.png"/>

        Debemos utilizar ListView; si deseamos que nuestro listado sean cuadrículas:

        <img src = "/public/images/example/android/adapter/gridview.png"/>

        Debemos utilizar GridView.

    </li>
    <li><a href="#cuatro">Invocar al Adapter desde una Activity.</a></li>
</ul>

<h2><a id="uno">1. Creando el aspecto de nuestra lista</a></h2>

<p>Definimos el layout del Adapter dentro de la carpeta layout que luzca de la siguiente manera:</p>

<img src="/public/images/example/android/adapter/componente-vista-listview.png" />

<pre class = "highlight">
        &#60;?xml version = "1.0" encoding = "utf-8"
        ?&#62;
        &#60;LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
        android:layout_width = "match_parent"
        android:layout_height = "wrap_content"
        android:layout_margin = "10dp"
        android:background = "#0088BB"
        android:orientation = "vertical"
        android:padding = "5dp" &#62;

        &#60;TextView
        android:id = "@+id/nameTextView"
        android:layout_width = "fill_parent"
        android:layout_height = "wrap_content"
        android:textColor = "#FFFFFF" /&#62;

        &#60;/LinearLayout/&#62;
</pre>

<p>Este layout representa el aspecto que tendrá cada item en el listado, como podrá darse cuenta, solo representa el aspecto de un item de la colección de datos, queda por parte del Adapter repetir el proceso para cada uno de los elementos que forman la colección de datos.</p>

<p>Este layout puede ser tan complejo como desee; puede contar de vários campos de texto (TextView), botones (Button) o cualquier otro elementos ofrecido por la API de Android.</p>

<h2><a id="dos">2. Creando el ArrayAdapter</a></h2>

<p>Ya definido y creado el layout, el siguiente paso consiste en crear una clase que extienda de ArrayAdapter&#60;String&#62; con el siguiente contenido:</p>

<pre class = "highlight">

        public class ListAdapter extends ArrayAdapter&#60;String&#62; {

        private Activity activity;
        ArrayList&#60;String&#62; mensajes;

        public ListAdapter(Activity activity, ArrayList&#60;String&#62; mensajes) {
          super(activity, R.layout.list_view);
          this.activity = activity;
          this.mensajes = mensajes;
        }

        static class ViewHolder {
        }

        public int getCount() {
          return mensajes.size();
        }

        public long getItemId(int position) {
          return position;
        }

        public View getView(final int position, View convertView,
          final ViewGroup parent) {
          View view = null;
          return view;
        }

        }


</pre>

<p><span class="black">ViewHolder</span>: Permite referenciar los elementos que componen al listado; en nuestro ejemplo el listado solo esta compuesto de un TextView:</p>

<pre class="highlight">
	static class ViewHolder {
		protected TextView nameTextView;

	}
</pre>

<p>Y el <span class="black">getView</span>; en donde son referenciados y establecidos los valores de los elementos de la vista:</p> 

<pre class="highlight">
		// inflamos nuestra vista con el layout
		View view = null;
		LayoutInflater inflator = activity.getLayoutInflater();
		view = inflator.inflate(R.layout.list_view, null);
		final ViewHolder viewHolder = new ViewHolder();

		// *** instanciamos a los recursos
		viewHolder.nameTextView = (TextView) view
				.findViewById(R.id.nameTextView);

		// importante!!! establecemos el mensaje
		viewHolder.nameTextView.setText(mensajes.get(position));

		return view;
</pre>

<ul class="number">
    <li>Inflamos el menu con nuestro layout ya definido en el <a href="uno">paso uno</a></li>
    <li>Referenciamos los elementos del layout (el TextView).</li>
    <li>Y hacemos algo con los elementos ya referenciados, en este ejemplo le establecemos un valor de la lista mensajes.</li>
</ul>

<p>Un punto interesante, es que es posible definir los eventos dentro del Adapter como veremos en el siguiente artículo.</p>

<h2><a id="tres">3. Agregando el Adapter al layout de la Actividad (Activity)</a></h2>

<p>Definimos el siguiente elemento dentro del main_layout.xml o el layout de la actividad:</p>

<pre class="highlight">
    &#60;ListView
        android:id="@+id/listview"
        android:layout_width="fill_parent"
        android:layout_height="fill_parent" /&#62;
</pre>

<h2><a id="cuatro">4. Invocando al Adapter desde la Actividad (Activity)</a></h2>

<p>Ahora solo falta invocar al Adapter desde la Actividad:</p>

<pre class="highlight">
		// creamos nuestra coleccion de datos
		mensajes = new ArrayList<String>();
		mensajes.add("uno");
		mensajes.add("dos");
		mensajes.add("tres");
		mensajes.add("cuatro");
		mensajes.add("cinco");

		// creamos el listado
		listAdapter = new ListAdapter(this, mensajes);

		// establecemos el adaptador en la lista
		listView.setAdapter(listAdapter);
</pre>

<p>Al ejecutar la aplicación, veremos una pantalla como esta:</p>

<img src="/public/images/example/android/adapter/adapter-android-listview.png" />

<p>Puedes ver el código completo del ejemplo en nuestro repositorio de github.</p>
