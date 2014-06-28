package models.minimummodels;


public class CategoryMinimumModel extends _GenericMinimumModel {
	
	public CategoryMinimumModel(){
		
	}

	public CategoryMinimumModel(String refererId, String label) {
		this.wrapperId = refererId;
		this.label =  label;
	}


}
