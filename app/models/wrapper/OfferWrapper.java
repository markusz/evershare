package models.wrapper;

import java.util.ArrayList;
import java.util.List;

import models.Offer;
import models.abstractionlayer.GenericWrapper;
import models.interfaces.IGeocodeable;
import models.minimummodels.BookingMinimumModel;
import models.minimummodels.CategoryMinimumModel;
import models.minimummodels.UserMinimumModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import controllers.db.OfferDAO;

/**
 * Due to the design of the underlying NoSQL MongoDB the design of the objects
 * has to be adjusted as well. <b>For an Offer, a Wrapper is necessary since we
 * store a minimum set of additional information like the id and name of the
 * owning user.</b> Doing so we avoid having to query the user-table each time
 * the very common usecase "Show list of offers" is performed.
 * 
 * The required information to satisfy this usecase are stored inside this
 * wrapper and the user-table has only to be queried if the full user details
 * are required
 * 
 * @author Markus
 * 
 */

@JsonTypeInfo(use=Id.CLASS,property="_class")
public class OfferWrapper extends GenericWrapper<Offer> implements IGeocodeable {

	/**
	 * the owner
	 * TODO rename
	 */
	
	@JsonSerialize
	@JsonDeserialize
    private UserMinimumModel owner = new UserMinimumModel();
	
	@JsonSerialize
	@JsonDeserialize
    public CategoryMinimumModel category = new CategoryMinimumModel();
	
	@JsonSerialize
	@JsonDeserialize
    private List<BookingMinimumModel> bookings = new ArrayList<BookingMinimumModel>();
	
    public List<BookingMinimumModel>  getBookingMinimumModels(int year, int month) {
		// TODO Richtige funktionalität
    	return bookings;
	}
    
    public List<BookingMinimumModel> getBookings() {
		return bookings;
	}

	public void setBookingMinimumModels(
			List<BookingMinimumModel> bookingMinimumModels) {
		this.bookings = bookingMinimumModels;
	}

	public void addBooking(BookingMinimumModel booking){
    	bookings.add(booking);
    }
    
    public void removeBooking(BookingMinimumModel booking){
    	for (BookingMinimumModel bmm : bookings) {
			if(bmm.getWrapperId() == booking.getWrapperId()){
				bookings.remove(bmm);
			}
		}
    }
    
    public OfferWrapper(){
    	
    }

    public OfferWrapper(Offer o){
		setBaseModel(o);
	}

	@Override
	public String getAddress() {
		return getBaseModel().getAddress();
	}

    public UserMinimumModel getUserMinimumModel() {
        return owner;
    }

    public void setUserMinimumModel(UserMinimumModel userMinimumModel) {
        this.owner = userMinimumModel;
    }

    public CategoryMinimumModel getCategoryMinimumModel() {
        return category;
    }

    public void setCategoryMinimumModel(CategoryMinimumModel categoryMinimumModel) {
        this.category = categoryMinimumModel;
    }

	@Override
	public void setLongitude(Double longitude) {
		getBaseModel().setLongitude(longitude);
	}

	@Override
	public Double getLatitude() {
		return getBaseModel().getLatitude();
	}

	@Override
	public void setLatitude(Double latitude) {
		getBaseModel().setLatitude(latitude);
	}

	@Override
	public Double getLongitude() {
		return getBaseModel().getLongitude();
	}

	@Override
	public void updateGeocode() throws Exception {
		OfferDAO.getDAO().updateGeocodeInformation(this);
	}

	

}
