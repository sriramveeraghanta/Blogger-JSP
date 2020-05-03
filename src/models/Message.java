package models;

public class Message {
	private Integer id;
	private String content;
	private String created;
	private Integer sentBy;
	private Integer sentTo;
	
	
	public Message(Integer id, String content, String created, Integer sentBy, Integer sentTo) {
		setId(id);
		setContent(content);
		setCreated(created);
		setSentBy(sentBy);
		setSentTo(sentTo);
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCreated() {
		return created;
	}


	public void setCreated(String created) {
		this.created = created;
	}


	public Integer getSentBy() {
		return sentBy;
	}


	public void setSentBy(Integer sentBy) {
		this.sentBy = sentBy;
	}


	public Integer getSentTo() {
		return sentTo;
	}


	public void setSentTo(Integer sentTo) {
		this.sentTo = sentTo;
	}
	
}
