package models.minimummodels;

import models.interfaces.IMinimumModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.CLASS,property="_class")
public class UserMinimumModel implements IMinimumModel {
	
	public String label;
	public String wrapperId;
	
	public UserMinimumModel(){
		
	}
	
	public UserMinimumModel(String id, String label){
		setMinimumModelWrapperId(id);
		setMinimumModelLabel(label);
		
	}
	
	public String getMinimumModelLabel() {
		return label;
	}

	public String getWrapperId() {
		return wrapperId;
	}
	

	public void setMinimumModelLabel(String label) {
		this.label = label;
	}

	public void setMinimumModelWrapperId(String id) {
		this.wrapperId = id;
	}

}
