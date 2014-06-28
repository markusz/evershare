package models;

import java.util.Date;

import models.minimummodels.BookingMinimumModel;

public class Booking extends _GenericModel<BookingMinimumModel> {

	private Long offerID;
	private Long userID;
	public Long bookTime;
	private Long bookedTill;
	private Long bookedFrom;
	public Date bookingStart;
	public Integer duration;

	@Deprecated
	public Long getOfferID() {
		return offerID;
	}

	public void setOfferID(Long offerID) {
		this.offerID = offerID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public Long getBookTime() {
		return bookTime;
	}

	public void setBookTime(Long bookTime) {
		this.bookTime = bookTime;
	}

	public Long getBookedTill() {
		return bookedTill;
	}

	public void setBookedTill(Long bookedTill) {
		this.bookedTill = bookedTill;
	}

	public Long getBookedFrom() {
		return bookedFrom;
	}

	public void setBookedFrom(Long bookedFrom) {
		this.bookedFrom = bookedFrom;
	}

	public Date getDate() {
		return bookingStart;
	}

	public void setDate(Date date) {
		this.bookingStart = date;
	}
	
	@Override
	public String toString() {
		return "Booking.toString() -> Id: " + offerID + "\tdate: " + bookingStart
				+ "\tduration: " + duration + "\tofferID: " + offerID;
	}

	@Override
	public BookingMinimumModel getMinimumModel() {
		return new BookingMinimumModel(refererId, bookingStart, duration);
	}
		
}
