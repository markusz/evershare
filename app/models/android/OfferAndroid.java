package models.android;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class OfferAndroid implements Serializable {

	public String id;

    public String name;

    public String description;

    public Double latitude;

    public Double longitude;

    public String street;

    public String city;

    public Integer postalCode;

    public Integer countryId;

    // TODO dirty
    public Integer categoryId;

   public UserAndroid owner;

    // TODO dirty
    public Long ownerDirtyID;

    public String imgUrl;

    public String titleimage;
    
    public Double priceph;

    // per unit (e.q. Day, Week,..). unused at the moment
    public Double pricepu;

    public Boolean available;

    public Date avail_from;

    public Date avail_to;

    public List<Integer> availableDays;
    
    @Override
    public String toString() {
    	return name;
    	
    }
	
}
