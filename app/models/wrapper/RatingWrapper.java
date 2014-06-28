package models.wrapper;

import models.Rating;
import models.abstractionlayer.GenericWrapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
@JsonTypeInfo(use=Id.CLASS,property="_class")
public class RatingWrapper extends GenericWrapper<Rating> {
	
	public RatingWrapper(){
		
	}

}
