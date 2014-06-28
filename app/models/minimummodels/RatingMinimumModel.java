package models.minimummodels;

public class RatingMinimumModel extends _GenericMinimumModel{
	
	private short rating;
	private String description;
	
	public RatingMinimumModel(){
		
	}
	
	public short getRating() {
		return rating;
	}
	public void setRating(short rating) {
		this.rating = rating;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
