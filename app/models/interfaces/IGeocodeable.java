package models.interfaces;

import org.bson.types.ObjectId;

/**
 * 
 * @author Markus Ziller
 *
 */
public interface IGeocodeable {

	public ObjectId getObjectId();
	public String getAddress();

	public void setLongitude(Double longitude);
	public Double getLatitude();

	public void setLatitude(Double latitude);
	public Double getLongitude();
	
	/**
	 * Necessary for use with the Geocoder-thread
	 *
	 * @author Markus Ziller
	 * @date 30.01.2013
	 *
	 */
	public void updateGeocode() throws Exception;
}
