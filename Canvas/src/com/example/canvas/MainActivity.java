package com.example.canvas;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new MyView(this));
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

	public class MyView extends View {
		public MyView(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

		@Override
		protected void onDraw(Canvas canvas) {
			// TODO Auto-generated method stub
			super.onDraw(canvas);
			int x = getWidth();
			int y = getHeight();
			int radius;
			radius = 100;
			Paint paint = new Paint(); // pintura o pincel
			paint.setStyle(Paint.Style.FILL); // el tipo de trazado

			// convertimos un color Hexa en un android.graphics.Color
			paint.setColor(Color.parseColor("#000000"));
			// pintamos un rectangulo negro
			canvas.drawRect(50, 50, 200, 200, paint);

			// convertimos un color Hexa en un android.graphics.Color
			paint.setColor(Color.parseColor("#FF0000"));

			// pintamos un circulo rojo
			canvas.drawCircle(x / 2, y / 2, radius, paint);

		}
	}
}
