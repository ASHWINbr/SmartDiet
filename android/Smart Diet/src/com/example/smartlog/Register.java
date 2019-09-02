package com.example.smartlog;

import java.util.HashMap;

import com.connection.Connection;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import com.connection.ServerCall;
import com.connection.Server_Interface;

public class Register extends Activity implements Server_Interface {

	EditText Name, Email, Password, Mobile;
	String name = "", email = "", password = "", mobile = "";
	public Button BtnReg;
	ServerCall connection;
	TextView calllog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		try {

			if (android.os.Build.VERSION.SDK_INT > 9) {
				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);
			}

		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "Error====" + e,
					Toast.LENGTH_LONG).show();
			Log.d("Myservice", "" + e);
		}
		Name = (EditText) findViewById(R.id.name);
		Email = (EditText) findViewById(R.id.email);
		Password = (EditText) findViewById(R.id.password);
		Mobile = (EditText) findViewById(R.id.mobile);
//		Age = (EditText) findViewById(R.id.age);
		BtnReg = (Button) findViewById(R.id.btn_register);
		BtnReg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				name = Name.getText().toString().trim();
				email = Email.getText().toString().trim();
				password = Password.getText().toString().trim();
				mobile = Mobile.getText().toString().trim();
				
				if (Name.getText().toString().equalsIgnoreCase("")) {
					Name.setError("Name should not be empty");
					Name.requestFocus();
				} else if (Email.getText().toString().equalsIgnoreCase("")) {
					Email.setError("Email should not be empty");
					Email.requestFocus();
				} else if (Password.getText().toString().equalsIgnoreCase("")) {
					Password.setError("Password should not be empty");
					Password.requestFocus();
				} else if (Mobile.getText().toString().length() < 10) {
					Mobile.setError("Mobile number should be 10 digits");
					Mobile.requestFocus();
//				} else if (Age.getText().toString().equalsIgnoreCase("")) {
//					Age.setError("Address should not be empty");
//					Age.requestFocus();
				} else {
					connection = new ServerCall(Register.this, Register.this);
					HashMap<String, Object> param = new HashMap<String, Object>();
					param.put("name", name);
					param.put("email", email);
					param.put("password", password);
					param.put("mobile", mobile);
//					param.put("gender", Gender);
//					param.put("address", age);
					connection.setParameter(param);
					connection.setMethod(ServerCall.POST);
					connection.execute(Connection.Register);

					// Toast.makeText(getApplicationContext(),
					// "Check your credentials",Toast.LENGTH_LONG).show();
				}

			}
		});

		calllog = (TextView) findViewById(R.id.calllog);

		calllog.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent it = new Intent(Register.this, Login.class);
				startActivity(it);

			}
		});
	}



	@Override
	public void onCancel(String chek) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(String result) {
		if (result.equalsIgnoreCase("ok")) {

			Toast.makeText(getApplicationContext(), "Registration Successful",
					Toast.LENGTH_LONG).show();
			Intent it = new Intent(Register.this, Login.class);
			startActivity(it);

		} else {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
		}

	}

}
