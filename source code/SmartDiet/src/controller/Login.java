package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.UserDao;
import com.http.servlet.HttpsServlet;

public class Login extends HttpsServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		UserDao userDao = new UserDao();
		String result = userDao.loginUser(email, password);

		if (!result.equals("no")) {
			out.print("ok");
		} else {
			out.print("Invalid email or password");
		}
		out.close();
	}

}
