package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

	private Integer id;
	private String content;
	private String image;
	private String date;
	private Integer user;
	
	
	public Post(Integer id, String content, String image, String date, Integer user) {
		this.setId(id);
		this.setContent(content);
		this.setImage(image);
		this.setDate(date);
		this.setUser(user);
	}
	
	public Post(String content, String image, Integer user) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.user = user;
		this.content = content;
		this.image = image;
		this.id = null;
		this.date = simpleDateFormat.format(new Date());
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
}
