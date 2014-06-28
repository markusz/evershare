package models.wrapper;

import models.Message;
import models.abstractionlayer.GenericWrapper;
import models.minimummodels.UserMinimumModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.CLASS,property="_class")
public class MessageWrapper extends GenericWrapper<Message> {
	
	public MessageWrapper(){
		
	}
	
	public MessageWrapper(Message m) {
		setBaseModel(m);
	}
	
	public UserMinimumModel sender;
	public UserMinimumModel receiver;

}
