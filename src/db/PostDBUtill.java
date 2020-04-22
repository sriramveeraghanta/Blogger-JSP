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
