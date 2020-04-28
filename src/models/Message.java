package models;

public class Message {
	private int userID;
	private String message;
	
	
	public Message(int userID, String message) {
		this.userID = userID;
		this.message = message;
	}

	public int getUserID() {
		return userID;
	}

	public String getMessage() {
		return message;
	}
	
}
