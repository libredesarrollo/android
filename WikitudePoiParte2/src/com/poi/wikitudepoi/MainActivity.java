package com.poi.wikitudepoi;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.ArchitectView.ArchitectConfig;

public class MainActivity extends Activity {

	ArchitectView architectView;

	LocationManager locationManager;
	double latitude = 0;
	double longitude = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// la ruta del architectView en nuestro XML
		this.architectView = (ArchitectView) this
				.findViewById(R.id.architectView);
		final ArchitectConfig config = new ArchitectConfig("" /* license key */);
		this.architectView.onCreate(config);

		// actualziamos las variables latitude y longitude de la aplicacion
		getLocalization();
		
	}

	/*
	 * Ciclo de vida en nuestra activida
	 */

	@Override
	protected void onResume() {
		super.onResume();
		if (this.architectView != null) {
			this.architectView.setLocation(latitude, longitude, 1f);
			this.architectView.onResume();
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		if (this.architectView != null) {
			this.architectView.onPause();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (this.architectView != null) {
			this.architectView.onDestroy();
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if (this.architectView != null) {
			this.architectView.onLowMemory();
		}
	}

	@Override
	protected void onPostCreate(final Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		// IMPORTANTE cargamos el ARchitect worlds (codigo web: HTML CSS
		// javaScript)
		this.architectView.onPostCreate();
		try {
			this.architectView.load("base/index.html");
			this.architectView.onResume();
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void getLocalization() {

		final Activity activity = this;

		// Obtenemos una referencia al LocationManager
		locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

		// Nos registramos para recibir actualizaciones de la posición
		LocationListener locationListener = new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {

				if (location != null) {
					latitude = location.getLatitude();
					longitude = location.getLongitude();
				}

				Random rand = new Random();

				int randomNum = rand.nextInt(50);

				Toast.makeText(
						activity,
						"nueva posicion obtenida " + latitude + " " + longitude + " "
								+ randomNum, Toast.LENGTH_SHORT).show();

				architectView.setLocation(latitude, longitude, 1f);
			}

			@Override
			public void onProviderDisabled(String provider) {
				Toast.makeText(activity, "Debe de habilitar el \"Acceso a su ubicación\" ",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onProviderEnabled(String provider) {
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {

				// muestro un mensaje segun el estatus erroneo de la senal
				switch (status) {
				case LocationProvider.OUT_OF_SERVICE:
					Toast.makeText(
							activity,
							"Se a perdido la comunicación con el GPS y/o redes teléfonicas.",
							Toast.LENGTH_LONG).show();
					break;
				case LocationProvider.TEMPORARILY_UNAVAILABLE:
					Toast.makeText(
							activity,
							"Se a perdido la comunicación con el GPS y/o redes teléfonicas.",
							Toast.LENGTH_LONG).show();
					break;
				case LocationProvider.AVAILABLE:
					Toast.makeText(
							activity,
							"Se a establecido la conexión con el GPS y/o redes teléfonicas.",
							Toast.LENGTH_LONG).show();
					break;
				}

			}
		};

		// Si esta habilitada el GPS en el dispositivo
		if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

			// obtengo la ultima localizacion del usuario, si no la hay, es null
			Location location = locationManager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (location != null) {
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}

			// actualizo la posicion del usuario cada 5 min = 80000 ms
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 80000, 0, locationListener);

		} else if (locationManager // Puntos Wifi o senal telefonica
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {

			// obtengo la ultima localizacion del usuario, si no la hay, es null
			Location location = locationManager
					.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
			if (location != null) {
				latitude = location.getLatitude();
				longitude = location.getLongitude();
			}

			// actualizo la posicion del usuario cada 5 min = 80000 ms
			locationManager.requestLocationUpdates(
					LocationManager.NETWORK_PROVIDER, 80000, 0,
					locationListener);

		} else {
			// servicio desactivado
			Toast.makeText(
					this,
					"Por favor active el \"Acceso a su ubicación\" desde las configuraciones.",
					Toast.LENGTH_LONG).show();

		}

	}

}