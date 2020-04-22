package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import db.PostDBUtill;
import models.Post;

@WebServlet("/home")
public class Home extends HttpServlet{

	@Resource(name="jdbc/blogger")
	private DataSource dataSource;
	private PostDBUtill postDBUtill;
	
	public Home() {
		super();
	}
	
	@Override
	public void init() throws ServletException {
		super.init();
		postDBUtill = new PostDBUtill(dataSource);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Post> allPosts = new ArrayList<>();
		try {
			allPosts =	postDBUtill.getAllPosts();
			request.setAttribute("allPosts", allPosts);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			RequestDispatcher dispatcher =  request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	
}
