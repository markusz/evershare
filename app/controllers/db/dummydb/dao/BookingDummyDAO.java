package controllers.db.dummydb.dao;

import java.util.Date;
import java.util.List;

import models.wrapper.BookingWrapper;
import models.wrapper.OfferWrapper;
import controllers.abstraction.dao.IBookingDAO;

/**
 * 
 * @author Markus
 *
 */
public class BookingDummyDAO extends _GenericDummyDAO<BookingWrapper> implements IBookingDAO {

	@Override
	public boolean isBooked(OfferWrapper o, Date date, int duration)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BookingWrapper> getBookings(int year, int month)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookingWrapper> getBookings(OfferWrapper ow) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookingWrapper> getBookings(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
