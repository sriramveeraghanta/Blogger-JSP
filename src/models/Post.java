package models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {

	private Integer id;
	private String content;
	private String image;
	private String createdAt;
	private Integer user_id;
	private Integer likesCout;
	private User user;
	
	// Do something about this for the bonus marks.....
//	private ArrayList<Comment> comments;
	
	public Post(Integer id, String content, String image, String createdAt, Integer user_id) {
		this.setId(id);
		this.setContent(content);
		this.setImage(image);
		this.setCreatedAt(createdAt);
		this.setUserId(user_id);
	}
	
	public Post(String content, String image, Integer user_id) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		this.user_id = user_id;
		this.content = content;
		this.image = image;
		this.id = null;
		this.createdAt = simpleDateFormat.format(new Date());
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return this.content;
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
	public int getUserId() {
		return this.user_id;
	}
	public void setUserId(Integer user_id) {
		this.user_id = user_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Integer getLikesCout() {
		return likesCout;
	}

	public void setLikesCout(Integer likesCout) {
		this.likesCout = likesCout;
	}
}
