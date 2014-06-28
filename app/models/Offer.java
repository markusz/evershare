package models;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.helper.Location;
import models.minimummodels.OfferMinimumModel;
import models.wrapper.BookingWrapper;
import play.Play;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

//public class Offer extends Model implements IGeocodeable {
public class Offer extends _GenericModel<OfferMinimumModel> {

	public String name;

	public String description;

	public Double latitude;

	public Double longitude;

	public Location loc = new Location();

	public String street;

	public String city;

	public Integer postalCode;

	public Integer countryId;

	public Integer categoryId;

	public String imgUrl;

	@JsonSerialize
	@JsonDeserialize
	public String titleimage;

	public byte[] img;

	public Double priceph;

	// per unit (e.q. Day, Week,..). unused at the moment
	public Double pricepu;

	public Boolean available;

	public Date avail_from;

	public Date avail_to;

	public List<Integer> availableDays;

	public Offer() {

	}

	private boolean fileExists() {
		String imgRootPath = Play.application().configuration()
				.getString("offerRootImgPath");
		String imgPath = Play.application().configuration()
				.getString("offerImgPath");
		File file = new File(Play.application().path().toString() + "/"
				+ imgRootPath + "/" + imgPath + refererId);

		return file.exists();
	}

	public String getImgUrl() {

		if (fileExists()) {
			return "img/offer/" + refererId + "/" + titleimage;
		}
		return "img/defaultimage.jpg";

	}

	public String getImgThumbnailUrl() {

		if (fileExists()) {
			return "img/offer/" + refererId + "/" + "thumbnail." + titleimage;
		}
		return "img/defaultimage.jpg";

	}

	public Double getLatitude() {
		return latitude;
	}

	/**
	 * MongoDB requires the order [long, lat]
	 * 
	 * @author Markus Ziller
	 * @date 30.01.2013
	 * 
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
		loc.y = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	/**
	 * MongoDB requires the order [long, lat] / [x,y]
	 * 
	 * @author Markus Ziller
	 * @date 30.01.2013
	 * 
	 * @param latitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
		loc.x = longitude;
	}

	public String getAddress() {
		return street + ", " + postalCode + " " + city;
	}

	// public List<Booking> getBookings() {
	// return Booking.find.where().eq("offerID", this.id).orderBy("date")
	// .findList();
	// }

	// TODO logik hieraus in die DAO ziehen
	public List<Booking> getBookings(int year, int month) {

		Calendar firstDay = Calendar.getInstance();
		firstDay.clear();
		firstDay.set(year, month, 1);

		Calendar lastDay = Calendar.getInstance();
		lastDay.set(year, month, firstDay.getActualMaximum(Calendar.DATE));

		// return Booking.find.where().eq("offerID", this.id)
		// .between("date", firstDay.getTime(), lastDay.getTime())
		// .orderBy("date").findList();
		return null;

	}

	@Override
	// TODO
	public String toString() {
		return null;
		// return "Offer.toString() -> Id: " + id + " - Name: " + name+
		// " - Owner: " + owner.name;
	}

	/**
	 * Indicates, if there exists a booking for the offer within the given time
	 * range.
	 * 
	 * @param date
	 * @param duration
	 * @return
	 */
	// TODO logik in DAO umziehen
	public boolean isBooked(Date date, int duration) {

		// Get a list of all days that are covered with input parameters
		// aka 'period of interest'

		// List<Date> dates = new LinkedList<Date>();
		// for (int i = 0; i < duration; i++) {
		// dates.add(new Date(date.getTime() + 1000 * 3600 * 24 * i));
		// }
		//
		// List<Booking> bookings = this.getBookings();
		// for (Booking i : bookings) {
		// if (i.date.after(dates.get(duration - 1))) {
		// // Bookings starting after the period of interest can be skipped
		// continue;
		// } else {
		//
		// // Check, if the booking period of Booking i collides with
		// // period of interest
		// for (int j = 0; j < i.duration; j++) {
		// if (dates.contains(new Date(i.date.getTime() + 1000 * 3600
		// * 24 * j))) {
		// return true;
		// }
		// }
		//
		// }
		// }

		return false;
	}

	/**
	 * Formats a price to a nice String with two digits after separator.
	 * 
	 * @param p
	 *            Price per time period (day)
	 * @return
	 */

	public String getPriceFormatted(double p) {
		DecimalFormat nf = new DecimalFormat("0.00");
		return nf.format(p);
	}

	/**
	 * Multiplies the booking's duration with the price per time period
	 * 
	 * @param booking
	 * @param p
	 *            Price per time period (day)
	 * @return
	 */
	public double calculateTotal(BookingWrapper booking, double p) {
		return p * booking.getBaseModel().duration;
	}

	public String noStreetNumber(String street) {

		int x = street.lastIndexOf(" ");
		if (x > 0) {
			return street.substring(0, x);
		} else {
			return street;
		}

	}

	// TODO richtig so?
	public OfferMinimumModel getMinimumModel() {
		return new OfferMinimumModel(refererId, label);
	}

}
