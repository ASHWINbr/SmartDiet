package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserPojo;
import service.UserDao;

import com.http.servlet.HttpsServlet;

public class Register extends HttpsServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String mobile = request.getParameter("mobile");

//		String gender=request.getParameter("gender");
//		String age=request.getParameter("age");
		UserPojo userPojo = new UserPojo(name, email, password, mobile);
		UserDao userDao = new UserDao();
		boolean result = userDao.saveUser(userPojo);
		if (result) {
			out.print("ok");
		} else {
			out.print("Email already exists!");
		}

		out.close();
	}

}
