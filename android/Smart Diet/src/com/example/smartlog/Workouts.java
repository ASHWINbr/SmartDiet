package com.example.smartlog;

import java.util.HashMap;

import com.connection.Connection;
import com.connection.ServerCall;
import com.connection.Server_Interface;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Workouts extends Activity implements Server_Interface {

	ServerCall connection;
	String workots = "";
	TextView works;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.workouts);
		works = (TextView) findViewById(R.id.get_form);

		SharedPreferences preferences = getSharedPreferences("MyPref",
				MODE_PRIVATE);
		String email = preferences.getString("emailsession", null);
		connection = new ServerCall(Workouts.this, Workouts.this);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("email", email);
		connection.setParameter(param);
		connection.setMethod(ServerCall.POST);
		connection.execute(Connection.WorkoutsHit);
	}

	@Override
	public void onCancel(String chek) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(String result) {
		Toast.makeText(getApplicationContext(), "Retriving workout details",
				Toast.LENGTH_LONG).show();
		works.setText(result);

		// works.setText(result);
	}

}
