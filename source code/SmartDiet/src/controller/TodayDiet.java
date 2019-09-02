package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDietPojo;
import service.UserDao;

import com.http.servlet.HttpsServlet;

public class TodayDiet extends HttpsServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		UserDao userDao = new UserDao();
		List<UserDietPojo> list = userDao.getDietPlan(email);
		String age = "";
		String gender = "";
		String plan = "";
		String height = "";
		String category="";
		if (list.isEmpty()) {
			out.print("error");
		} else {
			for (UserDietPojo userDietPojo : list) {
				age = userDietPojo.getAge();
				gender = userDietPojo.getGender();
				plan = userDietPojo.getPlan();
				height = userDietPojo.getHeight();
				category=userDietPojo.getCategory();
			}
			
			String temps[]=plan.split(" ");
			String caloriesPlan=Nutrition.nutritionValues(Integer.parseInt(age), gender, Integer.parseInt(temps[0])*30, height, category);
			System.out.println(caloriesPlan);
			String dateAndTime = DateTime.getDateAndTime();
			String temp[] = dateAndTime.split("@");
			String cals[]=caloriesPlan.split("@");
			ArrayList<Integer> cal=new ArrayList<Integer>();
			for (String string : cals) {
				cal.add(Integer.parseInt(string));
			}
			int total=0;
			for (Integer integer : cal) {
				total+=integer;
			}

			System.out.println("-------------->"+caloriesPlan+"@"+total);
			out.print(caloriesPlan+"@"+total+" kcal");
		}
		
		out.close();
	}

}
