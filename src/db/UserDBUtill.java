package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	public User addUser(String email, String password, String firstName, String lastName) throws SQLException {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		User createdUser = null;	
		
		try {
			connection = this.dataSource.getConnection();
			String sql = String.format("INSERT INTO user (first_name,last_name,email,password) VALUES ('%s','%s','%s','%s')", firstName, lastName, email, password);
			statement = connection.createStatement();
			int dbStatus = statement.executeUpdate(sql);
			
			if(dbStatus > 0) {
				createdUser = new User(firstName, lastName, email, "");
			}
			
		} finally {
			close(connection,statement, preparedStatement, resultSet);
		}
		
		return createdUser;
	}
	
	
public ArrayList<String> getAllUser(String email) throws Exception{
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		ArrayList<String> allUsers = new ArrayList<>();
		try {
			connection = this.dataSource.getConnection();
			String sql = "SELECT * FROM user WHERE email != ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				String tempFname = resultSet.getString("first_name").toString();
				String tempLname = resultSet.getString("last_name").toString();				
				allUsers.add(tempFname+" "+tempLname);	
			}
			
		}finally {
			close(connection,statement,preparedStatement,resultSet);
		}
		return allUsers;	
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
