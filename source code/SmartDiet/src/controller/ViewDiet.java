package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDietPojo;
import model.UserPojo;

import org.json.simple.JSONObject;

import service.UserDao;

import com.http.servlet.HttpsServlet;

public class ViewDiet extends HttpsServlet {

	// @Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		UserDao userDao = new UserDao();
		List<UserPojo> list = userDao.getUserDetails(email);
		List<UserDietPojo> list1 = userDao.getDietPlan(email);
		JSONObject jsonObject = new JSONObject();

		for (UserPojo userPojo : list) {
			jsonObject.put("name", userPojo.getName());

		}
		for (UserDietPojo userDietPojo : list1) {
			jsonObject.put("age", userDietPojo.getAge());
			jsonObject.put("gender", userDietPojo.getGender());
			jsonObject.put("height", userDietPojo.getHeight());
			jsonObject.put("weight", userDietPojo.getWeight());
			jsonObject.put("bmi", userDietPojo.getBmi());
			jsonObject.put("category", userDietPojo.getCategory());
			jsonObject.put("avg_weight", userDietPojo.getWeightToBe());
			jsonObject.put("plan", userDietPojo.getPlan());
			jsonObject.put("type", userDietPojo.getType());
		}
		System.out.println(jsonObject);
		out.print(jsonObject);
		out.close();
	}

}
