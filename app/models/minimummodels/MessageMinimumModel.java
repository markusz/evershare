package models.minimummodels;

import java.util.Date;

import scala.Math;

//TODO könnte platzsparender als TreeMap implementiert werden, da ein Nutzer meist mehrere Nachrichten schickt
public class MessageMinimumModel extends _GenericMinimumModel {
	
	public String senderName;
	public String senderId;
	public String receiverName;
	public String receiverId;
	public String messageTeaser;
	public Boolean read;
	public Date timestamp;
	
	public MessageMinimumModel(){
		
	}
	
	
	public MessageMinimumModel(String senderName, String senderId,
			String receiverName, String receiverId, String messageTeaser,
			Boolean read, Date timestamp) {
		super();
		this.senderName = senderName;
		this.senderId = senderId;
		this.receiverName = receiverName;
		this.receiverId = receiverId;
		this.messageTeaser = messageTeaser;
		this.read = read;
		this.timestamp = timestamp;
	}


	public MessageMinimumModel(String id, String title, String message, Boolean read,
			Date timestamp) {
		
		setMinimumModelWrapperId(id);
		this.label = title;
		this.messageTeaser = message.substring(0, Math.min(15, message.length()))+"..";
		this.read = read;
		this.timestamp = timestamp;
	}
	
	
	

}
