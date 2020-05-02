package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import models.Post;

public class PostDBUtill {

	private DataSource dataSource;
	
	
	public PostDBUtill(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	public ArrayList<Post> getAllPosts() throws SQLException{
		ArrayList<Post> allPosts  = new ArrayList<>();	
		
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = this.dataSource.getConnection();
			String queryString = "SELECT * from post ORDER BY data DESC;";
			statement = connection.createStatement();
			resultSet = statement.executeQuery(queryString);
			// iterating until the result set has values.
			while(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer user = resultSet.getInt("user");
				String content = resultSet.getString("content");
				String image = resultSet.getString("image");
				String date = resultSet.getString("date");
				// adding post to the model
				allPosts.add(new Post(id, content, image, date, user));
			}
		} finally {
			close(connection, statement, preparedStatement, resultSet);
		}
		
		return allPosts;
	}
	
	
	public ArrayList<Post> getAllUserPosts(Integer userId) throws Exception{
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ArrayList<Post> userPosts  = new ArrayList<>();	
		
		try {
			connection = this.dataSource.getConnection();
			String sql = "SELECT * FROM post where user_id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userId);
			statement = connection.createStatement();
			resultSet = preparedStatement.executeQuery();
						
			while(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer user_id = resultSet.getInt("user_id");
				String content = resultSet.getString("content");
				String image = resultSet.getString("image");
				String created = resultSet.getString("created");
				userPosts.add(new Post(id, content, image, created, user_id));
			}
		}finally {		
			close(connection,statement,preparedStatement,resultSet);
		}
		return userPosts;
	}
	
	
	public Post getPostDetails(Integer post_id) throws Exception{
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		Post tempPost = null;
		
		try {			
			connection = this.dataSource.getConnection();
			String sql = "SELECT * FROM post WHERE id = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, post_id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer user_id = resultSet.getInt("user_id");
				String content = resultSet.getString("content");
				String image = resultSet.getString("image");
				String createdAt = resultSet.getString("created");
				tempPost = new Post(id, content, image, createdAt, user_id);
			}
		}finally {
			close(connection,statement,preparedStatement,resultSet);
		}
		return tempPost;
	}
	
	public void createPost(Post tempPost) throws Exception {
	
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	
		String content = tempPost.getContent();	
		Integer user_id = tempPost.getUserId();	
		String image = tempPost.getImage();	
		String created = tempPost.getCreatedAt();
	
		try {
			connection = this.dataSource.getConnection();
			String sql = String.format("INSERT INTO post (user_id,content,image,created) VALUES ('%s','%s','%s','%s')", user_id,content,image,created);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		}finally {
			close(connection,statement,preparedStatement,resultSet);
		}
	}
	
	public void updatePost(Integer post_id ,String newContent) throws Exception {
		
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {			
			connection = this.dataSource.getConnection();
			String sql = "update post set content=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newContent);
			preparedStatement.setInt(2, post_id);
			preparedStatement.executeUpdate();
		}
		finally {
			close(connection, statement, preparedStatement,resultSet);
		}
	}	
	
	public void deletePost(String post_id) throws Exception {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {	
			connection = this.dataSource.getConnection();
			String sql = "delete FROM post WHERE id = ?";	
			preparedStatement = connection.prepareStatement(sql);	
			preparedStatement.setString(1, post_id);
			preparedStatement.executeUpdate();
		} finally {
			close(connection, statement, preparedStatement, resultSet);
		} 
	}
	
	public void updateLikesInPost(Integer post_id, Integer likesCount) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
				
		try {	
			connection = this.dataSource.getConnection();
			String sql = "update post set likes=? where id=?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Integer.toString(likesCount));
			preparedStatement.setString(2, Integer.toString(post_id));
			preparedStatement.executeUpdate();
		}
		finally {
			close(connection, statement, preparedStatement, resultSet);
		}
	}
	
	public void	likePost(Integer post_id)throws Exception {
		Post tempPost = getPostDetails(post_id);
		int likesCount = tempPost.getLikesCout();
		updateLikesInPost(tempPost.getId(), likesCount +1);
	}
	
	public void	unlikePost(Integer post_id) throws Exception {
		Post tempPost = getPostDetails(post_id);
		int likesCount = tempPost.getLikesCout();
		updateLikesInPost(tempPost.getId(), likesCount -1);
	}
	
	private void close(Connection conn, Statement smt, PreparedStatement pstmt, ResultSet res) {
		try {
			if(conn != null) {
				conn.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(smt != null) {
				smt.close();
			}
			if(res != null) {
				res.close();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
