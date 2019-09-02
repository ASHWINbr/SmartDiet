package controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
	public static String getDateAndTime() {
		Date d = new Date();
		Calendar calendar = Calendar.getInstance();
		String date = DateFormat.getDateInstance().format(d);
		System.out.println(date);
		// -----------------------------
		Date date1 = calendar.getTime();
		DateFormat format1 = new SimpleDateFormat("HH:mm");
		String time = format1.format(date1);
		System.out.println(time);
		String temp[] = time.split(":");
		int hour = Integer.parseInt(temp[0]);
		System.out.println(hour);

		String day = "";
		if (hour >=0 && hour < 12) {
			day = "Morning";
		} else if (hour >=12 && hour < 17) {
			day = "Afternoon";
		} else if (hour >= 17 && hour < 19) {
			day = "Evening";
		} else if (hour >=19 && hour < 23) {
			day = "Night";
		}else{
			System.out.println("Error while getting time");
		}
		System.out.println(day);
		return date+"@"+day;
	}

}
