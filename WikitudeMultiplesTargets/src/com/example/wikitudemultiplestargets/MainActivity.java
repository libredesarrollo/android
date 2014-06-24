package com.example.wikitudemultiplestargets;

import java.io.IOException;

import com.wikitude.architect.ArchitectView;
import com.wikitude.architect.ArchitectView.ArchitectConfig;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	ArchitectView architectView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// la ruta del architectView en nuestro XML
		this.architectView = (ArchitectView) this
				.findViewById(R.id.architectView);
		final ArchitectConfig config = new ArchitectConfig("" /* license key */);
		this.architectView.onCreate(config);

	}
	
	/*
	 * Ciclo de vida en nuestra activida 
	 */
	
	@Override
	protected void onResume() {
		super.onResume();
		if ( this.architectView != null ) {
			this.architectView.onResume();
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		if ( this.architectView != null ) {
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
		if ( this.architectView != null ) {
			this.architectView.onDestroy();
		}
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		if ( this.architectView != null ) {
			this.architectView.onLowMemory();
		}
	}


	@Override
	protected void onPostCreate( final Bundle savedInstanceState ) {
		super.onPostCreate( savedInstanceState );
		
		// IMPORTANTE cargamos el ARchitect worlds (codigo web: HTML CSS javaScript)
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

}
