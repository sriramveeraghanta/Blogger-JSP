package models;

import db.PostDBUtill;
import db.UserDBUtill;

public class User {

	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
		
	public User(int id, String firstName, String lastName, String email, String password) {
		this.setId(id);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setEmail(email);
		this.setPassword(password);
	}
	
	public User(String email, String password) {
		this.setEmail(email);
		this.setPassword(password);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean login(UserDBUtill userDBUtill) {
		
		try {
			User tempUser = (User) userDBUtill.findUser(this.email);
				
			if(tempUser != null) {
				
				String tempPass = tempUser.getPassword();
				
				if(this.password.equals(tempPass)) {
					this.id = tempUser.getId();
					this.firstName = tempUser.getFirstName();
					this.lastName = tempUser.getLastName();
					this.email = tempUser.getEmail();
					this.password = null;
					return true;
				}			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean createPost(String content, String image, PostDBUtill postDBUtill) {

		return null;
	}
	
}
