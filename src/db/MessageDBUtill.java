package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import models.Message;

public class MessageDBUtill {
	
	private DataSource dataSource;
	
	public MessageDBUtill(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public ArrayList<Message> getAllMessages(Integer sender, Integer receiver) throws SQLException{
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		ArrayList<Message> messagesList = new ArrayList<Message>();
		try {
			
			connection = this.dataSource.getConnection();	
			String sql = "SELECT * FROM message WHERE sent_by = ? AND sent_to = ?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, sender);
			preparedStatement.setInt(2, receiver);
			resultSet = preparedStatement.executeQuery(sql);
			
			while(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String content = resultSet.getString("content");
				String created = resultSet.getString("created");
				Integer sent_by = resultSet.getInt("sent_by");
				Integer sent_to = resultSet.getInt("sent_to");
				messagesList.add(new Message(id, content, created, sent_by, sent_to));		
			}
		} finally {
			close(connection, statement, preparedStatement, resultSet);	
		}
		return messagesList;
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
