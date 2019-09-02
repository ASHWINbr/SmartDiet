package com.example.smartlog;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.connection.Connection;
import com.connection.ServerCall;
import com.connection.Server_Interface;

public class ViewDiet extends Activity implements Server_Interface {
	ServerCall connection;
	TextView getHeight, getWeight, getAge, getGender, getBMI, getCat,
			getWeightToBe, getPlan, getName, getType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewdiet);
		getAge = (TextView) findViewById(R.id.get_age);
		getGender = (TextView) findViewById(R.id.get_gender);
		getHeight = (TextView) findViewById(R.id.get_height);
		getWeight = (TextView) findViewById(R.id.get_weight);
		getBMI = (TextView) findViewById(R.id.get_bmi);
		getCat = (TextView) findViewById(R.id.get_cat);
		getWeightToBe = (TextView) findViewById(R.id.get_avg);
		getPlan = (TextView) findViewById(R.id.get_plan);
		getName = (TextView) findViewById(R.id.get_name);
		getType = (TextView) findViewById(R.id.get_type);

		SharedPreferences preferences = getSharedPreferences("MyPref",
				MODE_PRIVATE);
		String email = preferences.getString("emailsession", null);
		connection = new ServerCall(ViewDiet.this, ViewDiet.this);
		HashMap<String, Object> param = new HashMap<String, Object>();
		param.put("email", email);
		connection.setParameter(param);
		connection.setMethod(ServerCall.POST);
		connection.execute(Connection.DietDetails);
	}

	@Override
	public void onCancel(String chek) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(String result) {
		if (!result.isEmpty()) {
			try {
				Object obj = new JSONParser().parse(result);
				JSONObject jo = (JSONObject) obj;
				String name = (String) jo.get("name");
				String age = (String) jo.get("age");
				String gender = (String) jo.get("gender");
				String height = (String) jo.get("height");
				String weight = (String) jo.get("weight");
				String bmi = (String) jo.get("bmi");
				String category = (String) jo.get("category");
				String avgWeight = (String) jo.get("avg_weight");
				String plan = (String) jo.get("plan");
				String type=(String)jo.get("type");
				getName.setText(name);
				getAge.setText(age + " years old");
				getGender.setText(gender);
				getHeight.setText(height + " Fts");
				getWeight.setText(weight + " Kgs");
				getBMI.setText(bmi);
				getCat.setText(category);
				getWeightToBe.setText(avgWeight + " Kgs");
				getPlan.setText(plan);
				getType.setText(type);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {
			Toast.makeText(getApplicationContext(), "Empty", Toast.LENGTH_LONG)
					.show();

		}

	}

}
