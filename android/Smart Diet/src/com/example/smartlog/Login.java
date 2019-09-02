package com.example.smartlog;

import java.util.HashMap;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.connection.Connection;
import com.connection.ServerCall;
import com.connection.Server_Interface;

public class Login extends Activity implements Server_Interface {
	private NotificationCompat.Builder notificationBuilder;
	private NotificationManager notificationManager;
	ServerCall connection;
	TextView callreg;
	public Button BtnLog;
	EditText Email1, Password1;
	String email = "", password = "";
	public static String temp[]=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Email1 = (EditText) findViewById(R.id.emails);
		Password1 = (EditText) findViewById(R.id.passwords);
		BtnLog = (Button) findViewById(R.id.btn_login);
		BtnLog.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				email = Email1.getText().toString().trim();
				password = Password1.getText().toString().trim();
				
				if (Email1.getText().toString().equalsIgnoreCase("")) {
					Email1.setError("Email should not be empty");
					Email1.requestFocus();
				} else if (Password1.getText().toString().equalsIgnoreCase("")) {
					Password1.setError("Password should not be empty");
					Password1.requestFocus();
				
				
				} else {
					SharedPreferences pref = getApplicationContext()
							.getSharedPreferences("MyPref", MODE_PRIVATE);
					SharedPreferences.Editor editor = pref.edit();
					editor.putString("emailsession", email);
					editor.commit();
					connection = new ServerCall(Login.this, Login.this);
					HashMap<String, Object> param = new HashMap<String, Object>();
					param.put("email", email);
					param.put("password", password);
					
					connection.setParameter(param);
					connection.setMethod(ServerCall.POST);
					connection.execute(Connection.Login);

					// Toast.makeText(getApplicationContext(),
					// "Check your credentials",Toast.LENGTH_LONG).show();
				}

			}
		});
		callreg = (TextView) findViewById(R.id.callreg);

		callreg.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent it = new Intent(Login.this, Register.class);
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
		if (result.equals("ok")) {
			Toast.makeText(getApplicationContext(), "Login Successfull",
					Toast.LENGTH_LONG).show();
			Intent it = new Intent(Login.this, MainMenu.class);
			 startActivity(it);	
			
		} else {
			Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG)
					.show();
		}
	}
	

}
