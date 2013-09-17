package com.example.menu_opciones;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onMenuItemSelected(int featureId, MenuItem item) {

		TextView tv = (TextView) findViewById(R.id.tv);

		switch (item.getItemId()) {
		case R.id.opcion1:
			tv.setText("opcion 1");
			return true;
		case R.id.opcion2:
			tv.setText("opcion 2");
			return true;

		case R.id.opcion3:
			tv.setText("opcion 3");
			return true;

		}
		return super.onMenuItemSelected(featureId, item);
	}

}
