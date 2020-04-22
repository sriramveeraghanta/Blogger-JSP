package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

import models.User;

public class UserDBUtill {

	private DataSource dataSource;
	
	public UserDBUtill(DataSource datasource) {
		this.dataSource = datasource;
	}
	
	public User findUser(String email) throws Exception{
		
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		User foundUser = null;	
		
		try {
			
			connection = this.dataSource.getConnection();
			String sql = "SELECT * FROM user WHERE email = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				Integer idInteger = resultSet.getInt("id");
				String firstNameString  = resultSet.getString("first_name").toString();
				String lastNameString = resultSet.getString("last_name").toString();
				String emailString = resultSet.getString("email").toString();
				String passwordString = resultSet.getString("password").toString();
				foundUser = new User(idInteger, firstNameString, lastNameString, emailString, passwordString);
			}
		} finally {
			close(connection,statement, preparedStatement, resultSet);
		}
			
		return foundUser;	
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
