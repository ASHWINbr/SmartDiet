package com.connection;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class ServerCall extends AsyncTask<String, String, String> {

	private ProgressDialog dialog;
	Activity activity;
	private boolean isCancel = false;
	Server_Interface callback;
	String method = "";
	HashMap<String, Object> params = null;
	public static String GET = "GET", POST = "POST";

	public ServerCall(Activity activity, Server_Interface callback) {
		this.activity = activity;
		this.callback = callback;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setParameter(HashMap<String, Object> param) {
		this.params = param;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPreExecute() {
		dialog = new ProgressDialog(activity, dialog.THEME_HOLO_DARK);
		dialog.setTitle("Connecting Server.......");
		dialog.setMessage("please wait.");
		dialog.show();
		dialog.setCancelable(false);
	}

	@Override
	protected String doInBackground(String... para) {
		// TODO Auto-generated method stub
		try {
			StringBuilder postData = new StringBuilder();
			for (Map.Entry<String, Object> param : params.entrySet()) {
				if (postData.length() != 0) {
					postData.append('&');
				}
				postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
				postData.append('=');
				postData.append(URLEncoder.encode(
						String.valueOf(param.getValue()), "UTF-8"));
			}

			byte[] postDataBytes = postData.toString().getBytes("UTF-8");
			URL url = null;
			if (method.equals("POST")) {
				url = new URL(para[0]); // here is your URL path for Post
			} else {
				url = new URL(para[0] + postData); // here is your URL path for
													// get
			}

			Log.e("URL", url.toString());
			/*
			 * org.json.JSONObject postDataParams = new org.json.JSONObject();
			 * postDataParams.put("name", "abc"); postDataParams.put("email",
			 * "abc@gmail.com"); Log.e("params",postDataParams.toString());
			 */

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(5000 /* milliseconds */);
			conn.setConnectTimeout(5000 /* milliseconds */);
			conn.setRequestMethod(method);
			conn.setDoInput(true);

			if (method.equals("POST")) {
				conn.setDoOutput(true);
				conn.getOutputStream().write(postDataBytes);
			}

			int responseCode = conn.getResponseCode();

			if (responseCode == HttpsURLConnection.HTTP_OK) {

				BufferedReader in = new BufferedReader(new InputStreamReader(
						conn.getInputStream()));

				StringBuffer sb = new StringBuffer("");
				String line = "";

				while ((line = in.readLine()) != null) {

					sb.append(line);
					// break;
				}

				in.close();
				Thread.sleep(2000);
				return sb.toString();

			} else {
				return new String("false : " + responseCode);
			}
		} catch (Exception e) {
			return new String("Exception: " + e.getMessage());
		}
	}

	@Override
	protected void onPostExecute(String result) {
		try {
			if (dialog.isShowing()) {
				dialog.dismiss();
			}
			if (isCancel) {
				callback.onCancel("Chceck");
			}
			callback.onFinish(result);
		} catch (Exception e) {
			// showToast("" + e);
			e.printStackTrace();
		}
	}

}
