package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.UserDietPojo;
import service.UserDao;

public class DietReader extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		System.out.println("called email------------" + email);
		UserDao userDao = new UserDao();
		List<UserDietPojo> list = userDao.getDietPlan(email);
		String gender = "";
		String type = "";
		for (UserDietPojo dietPojo : list) {
			gender = dietPojo.getGender().toLowerCase();
			type = dietPojo.getType().toLowerCase();
		}
		System.out.println(gender+"=========================="+type);
		if (type==null || type.isEmpty()) {
			out.print("Add your Body Profile to get workout recommendations");
		} else {
			if (type.equalsIgnoreCase("sedentary")) {
				out.print("No suggestions for Sedentary");
			} else {
				if (gender.equalsIgnoreCase("male")) {
					String path = "webapps/SmartDiet/workouts/men.txt";
					String workouts = FileReadByWord.getWorkoutDetails(path, type);
					System.out.println(workouts);
					out.print(workouts);

				} else {
					String path = "webapps/SmartDiet/workouts/women.txt";
					String workouts = FileReadByWord.getWorkoutDetails(path, type);
					System.out.println(workouts);
					out.print(workouts);
				}
			}
		}
		
		

		out.close();
	}

}
