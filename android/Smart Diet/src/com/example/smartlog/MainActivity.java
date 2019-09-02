package com.example.smartlog;

import com.connection.IpAddress;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {

	private Button next;
	private EditText ipaddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ipaddress = (EditText) findViewById(R.id.ips);
		next = (Button) findViewById(R.id.ipbtn);
		next.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				try {
					if (ipaddress.getText().toString().trim()
							.equalsIgnoreCase("")) {
						ipaddress.setError("Enter Ip");
						ipaddress.requestFocus();
					} else {
						String ipport = ipaddress.getText().toString().trim();
						Log.e("hello error", ipport);
						IpAddress.Ip_Address = ipport;
						Intent it = new Intent(MainActivity.this, Login.class);
						startActivity(it);

					}
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "" + e,
							Toast.LENGTH_LONG).show();
				}
			}
		});

	}
	public void onBackPressed(){
	Intent a = new Intent(Intent.ACTION_MAIN);
	a.addCategory(Intent.CATEGORY_HOME);
	a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	startActivity(a);
	}
}
