package models;

import java.util.Date;

import models.minimummodels.MessageMinimumModel;

public class Message extends _GenericModel<MessageMinimumModel> {
	
	private String title;
	private String message;
	private Boolean read;
	private Date timestamp;
	
	public Message(){
		
	}
	
	//TODO
	public MessageMinimumModel getMinimumModel(){
		return new MessageMinimumModel(refererId, title, message, read, timestamp);
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Boolean getRead() {
		return read;
	}
	public void setRead(Boolean read) {
		this.read = read;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
}
