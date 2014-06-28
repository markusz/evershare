package models.wrapper;

import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.abstractionlayer.GenericWrapper;
import models.minimummodels.CategoryMinimumModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.CLASS,property="_class")
public class CategoryWrapper extends GenericWrapper<Category> {
	
	public List<CategoryMinimumModel> subcategories =  new ArrayList<CategoryMinimumModel>();
	
	public CategoryWrapper(Category t){
		setBaseModel(t);
	}
	
	public CategoryWrapper(){
		
	}

}
