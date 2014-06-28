package models.wrapper;

import java.util.ArrayList;
import java.util.List;

import models.Item;
import models.abstractionlayer.GenericWrapper;
import models.minimummodels.RatingMinimumModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.CLASS,property="_class")
public class ItemWrapper extends GenericWrapper<Item> {
	
	public List<RatingMinimumModel> ratings = new ArrayList<RatingMinimumModel>();
	
	public ItemWrapper(){
		
	}

}
