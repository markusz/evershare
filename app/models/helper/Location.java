package models.helper;

public class Location {
	
	/**
	 * long
	 */
	public Double x;
	
	/**
	 * lat
	 */
	public Double y;
	
	public Location(){
		
	}
	
	/**
	 * 
	 * @param x longitude
	 * @param y latitude
	 */
	public Location(Double x, Double y){
		this.x = x;
		this.y = y;
	}

}
