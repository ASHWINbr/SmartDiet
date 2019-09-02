package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDietPojo;
import service.UserDao;

import com.http.servlet.HttpsServlet;

public class DietPlan extends HttpsServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String height = request.getParameter("height");
		String weight = request.getParameter("weight");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String plan = request.getParameter("plan");
		String type = request.getParameter("type");

		int feet, inches, totalInches;
		int femaleWeight, maleWeight;

		String temp[] = height.split("\\.");
		feet = Integer.parseInt(temp[0]);
		inches = Integer.parseInt(temp[1]);
		totalInches = feet * 12 + inches;
		femaleWeight = 100 + (totalInches - 60) * 5;
		maleWeight = 106 + (totalInches - 60) * 6;
		double malekilos = maleWeight * 0.45;
		double femalekilos = femaleWeight * 0.45;

		float we = Float.valueOf(weight) * 2;
		double weightInKilos = we * 0.453592;
		double heightInMeters = (((feet * 12) + inches) * .0254);
		double bmi = weightInKilos / Math.pow(heightInMeters, 2.0);

		// float bmi = (Float.valueOf(weight))
		// / (Float.valueOf(height) * Float.valueOf(height));
		String category = "";
		if (bmi < 18.5) {
			category = "Under Weight";
		} else if (bmi >= 18.5 && bmi < 25) {
			category = "Normal";
		} else if (bmi >= 25 && bmi < 30) {
			category = "Over Weight";
		} else {
			category = "Obese";
		}
		DecimalFormat dff = new DecimalFormat("#.##");      
		bmi = Double.valueOf(dff.format(bmi));
		System.out
				.println("Your BMI is " + bmi + " and that means " + category);

		// System.out.println("The ideal weight for a female in pounds " +
		// femaleWeight + "& Kgs------------->"+femalekilos);
		// System.out.println("The ideal weight for a male in pounds " +
		// maleWeight + "& Kgs------------>"+malekilos);
		String weightToBe = "";
		if (gender.equalsIgnoreCase("male")) {
			DecimalFormat df = new DecimalFormat("#.##");      
			malekilos = Double.valueOf(df.format(malekilos));
			weightToBe = String.valueOf(malekilos);
		} else {
			DecimalFormat df = new DecimalFormat("#.##");      
			femalekilos = Double.valueOf(df.format(femalekilos));
			weightToBe = String.valueOf(femalekilos);
		}
		System.out.println(weight + "<--------------------------->"
				+ weightToBe);

		UserDietPojo userDietPojo = new UserDietPojo(email, height, weight,
				age, gender, String.valueOf(bmi), category, weightToBe, plan,
				type);
		UserDao userDao = new UserDao();

		boolean result = userDao.saveDietPlan(userDietPojo);
		if (result) {

			out.print("ok");
		} else {
			out.print("Failed to find BMI");
		}

		out.close();
	}

}
