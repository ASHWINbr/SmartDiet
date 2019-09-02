package com.example.smartlog;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.connection.Connection;
import com.connection.ServerCall;
import com.connection.Server_Interface;

public class MainMenu extends Activity implements Server_Interface {
	ImageView NutritionScan, NutritionWeight;
	ServerCall connection;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);
		NutritionScan = (ImageView) findViewById(R.id.qr_ntn);
		NutritionWeight = (ImageView) findViewById(R.id.ses_ntn);
		NutritionScan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				 Intent intent = new Intent(MainMenu.this, Workouts.class);
				 startActivity(intent);
			

			}
		});
		NutritionWeight.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainMenu.this, Today.class);
				startActivity(intent);

			}
		});
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		try {

			if (requestCode == 0) {
				if (resultCode == RESULT_OK) {
					String contents = intent.getStringExtra("SCAN_RESULT");
					String format = intent.getStringExtra("SCAN_RESULT_FORMAT");
					Toast.makeText(getApplicationContext(), contents,
							Toast.LENGTH_LONG).show();
					SharedPreferences preferences = getSharedPreferences(
							"MyPref", MODE_PRIVATE);
					String email = preferences.getString("emailsession", null);
					connection = new ServerCall(MainMenu.this, MainMenu.this);
					HashMap<String, Object> param = new HashMap<String, Object>();
					param.put("email", email);
					param.put("barcode", contents);
					connection.setParameter(param);
					connection.setMethod(ServerCall.POST);
					connection.execute(Connection.BarCoder);

				} else if (resultCode == RESULT_CANCELED) {
					// Handle cancel
					Toast.makeText(getApplicationContext(),
							"Sorry Scan cancelled", Toast.LENGTH_LONG).show();

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onCancel(String chek) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(String result) {
		if (!result.isEmpty()) {
			// Toast.makeText(getApplicationContext(), result,
			// Toast.LENGTH_LONG).show();
			// Intent intent = new Intent(MainMenu.this,
			// CalorieInformation.class);
			// intent.putExtra("result", result);
			// startActivity(intent);

		} else {
			Toast.makeText(getApplicationContext(), "Invalid barcode",
					Toast.LENGTH_LONG).show();

		}

	}

	public void actionLogout() {
		Log.e("in logout", "logout process");

		Intent it = new Intent(MainMenu.this, MainActivity.class);
		startActivity(it);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.adddiet:
			Intent it = new Intent(MainMenu.this, Diet.class);
			startActivity(it);
			return true;
		case R.id.viewdiet:
			Intent it1 = new Intent(MainMenu.this, ViewDiet.class);
			startActivity(it1);
			return true;
		case R.id.today_chart:
			Intent intent = new Intent(MainMenu.this, Today.class);
			startActivity(intent);
			return true;

		case R.id.workouts:
			Intent foo = new Intent(MainMenu.this, Workouts.class);
			startActivity(foo);
			return true;

		case R.id.logout:
			actionLogout();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}

	}

	public boolean onPrepareOptionsMenu(MainMenu menu) {
		// if nav drawer is opened, hide the action items
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		// menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu((android.view.Menu) menu);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent it = new Intent(MainMenu.this, MainMenu.class);
		startActivity(it);
	}

}
