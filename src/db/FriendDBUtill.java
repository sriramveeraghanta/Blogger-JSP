package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

public class FriendDBUtill {

	private DataSource dataSource;
	
	public FriendDBUtill(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void addFriend(Integer user_id, Integer friend_id) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			connection = this.dataSource.getConnection();
			String sql = String.format("INSERT INTO firend (user_id, friend_id) VALUES ('%s','%s')", user_id, friend_id);
			statement = connection.createStatement();
			statement.executeUpdate(sql);
		}finally {
			close(connection,statement,preparedStatement,resultSet);
		}
	}
	
	
	public void close(Connection connection, Statement statement, PreparedStatement prepStatement, ResultSet resultSet) {
		try {
			if(connection !=null) {
				connection.close();
			}
			if(statement != null) {
				statement.close();
			}
			if (prepStatement != null) {
				prepStatement.close();
			}
			if(resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
