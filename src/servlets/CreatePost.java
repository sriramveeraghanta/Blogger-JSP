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

import db.PostDBUtill;
import models.User;

@WebServlet("/post/create")
public class CreatePost extends HttpServlet {

	public CreatePost() {
		super();
	}
	
	@Resource(name="jdbc/blogger")
    private DataSource dataSource;
    private PostDBUtill postDBUtill;
    
    
    public void init() throws ServletException {
    	super.init();
    	postDBUtill = new PostDBUtill(dataSource);   	
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	User user = (User) session.getAttribute("user");
    	
    	String content = request.getParameter("content");
    	String image = request.getParameter("image");
    	Boolean isCreated =  user.createPost(content, image, postDBUtill);
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Home");
		request.setAttribute("isCreated", isCreated);
		request.removeAttribute(image);
		request.removeAttribute(content);		
		dispatcher.forward(request, response);
	}
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
    
    
}
