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

@WebServlet("/login")
public class Login extends HttpServlet {

	public Login() {
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
	
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");
		
		User tempUser = new User(email,pass);
		Boolean canLogin = tempUser.login(userDBUtill);

		if(canLogin) {
			session.setAttribute("user", tempUser);
			System.out.println(tempUser.getEmail());
			response.sendRedirect("profile.jsp");
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			request.setAttribute("loginError", true);
			dispatcher.forward(request, response);		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	
	
}
