package com.example.smartlog;

import java.util.HashMap;

import com.connection.Connection;
import com.connection.ServerCall;
import com.connection.Server_Interface;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Today extends Activity implements Server_Interface {
	ServerCall connection;
	TextView morning, afternoon, evening, night, toal;;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.today);
		morning = (TextView) findViewById(R.id.morning);
		afternoon = (TextView) findViewById(R.id.afternoon);
		evening = (TextView) findViewById(R.id.evening);
		night = (TextView) findViewById(R.id.night);
		toal = (TextView) findViewById(R.id.total);

		SharedPreferences preferences = getSharedPreferences("MyPref",
				MODE_PRIVATE);
		String email = preferences.getString("emailsession", null);
		connection = new ServerCall(Today.this, Today.this);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("email", email);
		connection.setParameter(param);
		connection.setMethod(ServerCall.POST);
		connection.execute(Connection.TodayChart);
	}

	@Override
	public void onCancel(String chek) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(String result) {
		if (!result.isEmpty()) {
			
			if (result.equals("error")) {
				Toast.makeText(getApplicationContext(), "Add your Body Profile to get calorie recommendations", Toast.LENGTH_LONG)
				.show();
				Intent it = new Intent(Today.this, MainMenu.class);
				startActivity(it);
			} else {
				String temp[] = result.split("@");
				morning.setText(temp[0]+" kcal");
				afternoon.setText(temp[1]+" kcal");
				evening.setText(temp[2]+" kcal");
				night.setText(temp[3]+" kcal");
				toal.setText(temp[4]);
			}
			

		} else {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
		}

	}

}
