package models;

import models.minimummodels.CategoryMinimumModel;

//public class Category extends Model {
public class Category extends _GenericModel<CategoryMinimumModel> {


	public String name = "";
	public Boolean firstLvlCat = false;
	public String description = "";

	public Category(String name) {
		this.name = name;
	}
	
	public Category(){
		
	}

	//TODO
	@Override
	public CategoryMinimumModel getMinimumModel() {
		return new CategoryMinimumModel(refererId, name);
	}
}
