package models;

import java.util.ArrayList;

public class GroupChat {
	ArrayList<User> userList;
	ArrayList<Message> messages;
		
	public GroupChat(ArrayList<User> userList, ArrayList<Message> messages) {
		this.userList = userList;
		this.messages = messages;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public ArrayList<Message> getMessages() {
		return messages;
	}	
}
