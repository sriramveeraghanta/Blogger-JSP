package servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import db.UserDBUtill;
import models.User;
import sun.security.util.Password;

@WebServlet("/register")
public class Register extends HttpServlet {

	public Register() {
		super();
	}
	
	@Resource(name="jdbc/blogger")
	private DataSource dataSource;
	private UserDBUtill userDBUtill;
	
	
	@Override
	public void init() throws ServletException{
		super.init();
		userDBUtill = new UserDBUtill(dataSource);
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
	
		String first_name = request.getParameter("first_name");
		String last_name = request.getParameter("last_name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		Boolean registerStatus = false;
		
		User tempUser = new User(first_name, last_name, email, pass);
		if(first_name != null && last_name!=null && email!=null && pass != null ) {
			registerStatus = tempUser.registerUser(userDBUtill);
			if(registerStatus) {
				response.sendRedirect("login.jsp");
			}
		}
		request.setAttribute("registerError", registerStatus);
		RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
