package models.wrapper;

import models.Booking;
import models.abstractionlayer.GenericWrapper;
import models.minimummodels.OfferMinimumModel;
import models.minimummodels.UserMinimumModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@JsonTypeInfo(use=Id.CLASS,property="_class")
public class BookingWrapper extends GenericWrapper<Booking> {
	
	public UserMinimumModel bookingUser;
	public OfferMinimumModel bookedOffer;
	
	public BookingWrapper(){
		
	}

	public BookingWrapper(Booking booking) {
		setBaseModel(booking);
	}

}
