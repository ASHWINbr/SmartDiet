package com.connection;

import android.R.string;

public class Connection {
	public static final String Domain_Name = "SmartDiet";

	public static String Port_No = "8888";

	/*-------------------Url Call-------------------*/
	public static String MAIN_URL = "http://" + IpAddress.Ip_Address + ":"
			+ Port_No + "/" + Domain_Name + "/";

	public static String Register = MAIN_URL + "Register";
	public static String Login = MAIN_URL + "Login";
	public static String AddDiet = MAIN_URL + "DietPlan";
	public static String WorkoutsHit = MAIN_URL + "DietReader";
	public static String DietDetails = MAIN_URL + "ViewDiet";
	public static String BarCoder = MAIN_URL + "BarCoder";
	public static String AllDates = MAIN_URL + "DietDetails";
	public static String DietByDate = MAIN_URL + "DateView";
	public static String DietByDateTime = MAIN_URL + "DateViewTime";
	public static String fooddate = MAIN_URL + "FoodData?";
	public static String TodayChart = MAIN_URL + "TodayDiet";
	public static String SaveDietInformation = MAIN_URL + "SaveDietInformation";

}