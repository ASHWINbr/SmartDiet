package com.example.smartlog;

import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.connection.Connection;
import com.connection.ServerCall;
import com.connection.Server_Interface;

public class Diet extends Activity implements Server_Interface,
		OnItemSelectedListener {
	String gender;
	String height = "", weight = "", age = "";
	EditText Height, Weight, Age;
	ServerCall connection;
	private Button AddDiet;
	private Spinner spinner, spinnertype;
	private static final String[] plan = { "3 Months", "6 Months", "12 Months" };
	private static final String[] type = { "Sedentary", "Light", "Moderate",
			"Active", "Very Brisk" };
	String choosePlan, chooseType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adddiet);
		spinner = (Spinner) findViewById(R.id.spinner);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(Diet.this,
				android.R.layout.simple_spinner_item, plan);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

		spinnertype = (Spinner) findViewById(R.id.spinnertype);
		ArrayAdapter<String> adaptertype = new ArrayAdapter<String>(Diet.this,
				android.R.layout.simple_spinner_item, type);
		adaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnertype.setAdapter(adaptertype);
		spinnertype.setOnItemSelectedListener(this);

		Height = (EditText) findViewById(R.id.height);
		Weight = (EditText) findViewById(R.id.weight);
		Age = (EditText) findViewById(R.id.age);
		AddDiet = (Button) findViewById(R.id.btn_diet);
		AddDiet.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				height = Height.getText().toString().trim();
				weight = Weight.getText().toString().trim();
				age = Age.getText().toString().trim();

				if (Height.getText().toString().equalsIgnoreCase("")) {
					Height.setError("Name should not be empty");
					Height.requestFocus();
				} else if (Weight.getText().toString().equalsIgnoreCase("")) {
					Weight.setError("Email should not be empty");
					Weight.requestFocus();
				} else if (Age.getText().toString().equalsIgnoreCase("")) {
					Age.setError("Password should not be empty");
					Age.requestFocus();

				} else {
					SharedPreferences preferences = getSharedPreferences(
							"MyPref", MODE_PRIVATE);
					String email = preferences.getString("emailsession", null);
					connection = new ServerCall(Diet.this, Diet.this);
					HashMap<String, Object> param = new HashMap<String, Object>();
					param.put("email", email);
					param.put("height", height);
					param.put("weight", weight);
					param.put("age", age);
					param.put("gender", gender);
					param.put("plan", choosePlan);
					param.put("type", chooseType);
					connection.setParameter(param);
					connection.setMethod(ServerCall.POST);
					connection.execute(Connection.AddDiet);

					// Toast.makeText(getApplicationContext(),
					// "Check your credentials",Toast.LENGTH_LONG).show();
				}

			}
		});
	}

	public void onRadioButtonClicked(View view) {
		boolean checked = ((RadioButton) view).isChecked();
		switch (view.getId()) {
		case R.id.male:
			if (checked)
				gender = "male";
			break;
		case R.id.female:
			if (checked)
				gender = "female";
			break;

		}
		Toast.makeText(getApplicationContext(), gender, Toast.LENGTH_SHORT)
				.show();

	}

	@Override
	public void onCancel(String chek) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(String result) {
		if (result.equals("ok")) {
			Toast.makeText(getApplicationContext(),
					"Diet plan added successfully", Toast.LENGTH_LONG).show();
			Intent it = new Intent(Diet.this, ViewDiet.class);
			startActivity(it);
			finish();
		} else {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View v, int position,
			long id) {
		if (parent.getId() == R.id.spinner) {
//			Toast.makeText(getApplicationContext(), plan[position],
//					Toast.LENGTH_LONG).show();
			choosePlan = plan[position];

		} else {
			chooseType = type[position];

		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
