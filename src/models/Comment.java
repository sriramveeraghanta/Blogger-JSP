package models;

public class Comment {
	private int userID;
	private String message;
	
	public Comment(int userID, String message) {
		this.userID = userID;
		this.message = message;
	}
	
	public int getUserID() {
		return this.userID;
	}
	
	public String getMessage() {
		return this.message;
	}
}
