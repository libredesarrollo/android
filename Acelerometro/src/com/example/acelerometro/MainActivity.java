package com.example.acelerometro;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements
		SensorEventListener {

	TextView textView;
	String sensor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		textView = (TextView) this.findViewById(R.id.textView);

		// buscamos los sensores del sistema
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		// buscamos si hay un sensor de tipo acelerometro
		if (sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).size() == 0) {
			textView.setText("No hay un acelerómetros en el dispositivo");
		} else {

			// obtenemos el acelerometro
			Sensor accelerometer = sensorManager.getSensorList(
					Sensor.TYPE_ACCELEROMETER).get(0);

			if (!sensorManager.registerListener(this, accelerometer,
					SensorManager.SENSOR_DELAY_NORMAL)) {
				textView.setText("ocurrio un error al registrar el listener");
			}

		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		sensor = "";
		sensor = "x: " + event.values[0] + " y: " + event.values[1] + " z: "
				+ event.values[2];

		textView.setText(sensor);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}
}
