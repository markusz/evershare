package models.wrapper;

import java.util.ArrayList;
import java.util.List;

import models.User;
import models.abstractionlayer.GenericWrapper;
import models.interfaces.IGeocodeable;
import models.minimummodels.BookingMinimumModel;
import models.minimummodels.ItemMinimumModel;
import models.minimummodels.MessageMinimumModel;
import models.minimummodels.OfferMinimumModel;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import controllers.db.UserDAO;

@JsonTypeInfo(use = Id.CLASS, property = "_class")
public class UserWrapper extends GenericWrapper<User> implements IGeocodeable {

	public List<ItemMinimumModel> itemsOwned = new ArrayList<ItemMinimumModel>();
	public List<OfferMinimumModel> offersRunning = new ArrayList<OfferMinimumModel>();
	public List<MessageMinimumModel> sentMessages = new ArrayList<MessageMinimumModel>();
	public List<MessageMinimumModel> receivedMessages = new ArrayList<MessageMinimumModel>();
	
	//TODO updatelogik
	public List<BookingMinimumModel> bookings = new ArrayList<BookingMinimumModel>();

	public UserWrapper() {

	}

	public UserWrapper(User u) {
		setBaseModel(u);
	}

	@Override
	public String getAddress() {
		return getBaseModel().getAddress();
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
		UserDAO.getDAO().updateGeocodeInformation(this);
	}

}
