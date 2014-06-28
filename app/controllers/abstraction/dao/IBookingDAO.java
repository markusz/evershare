package controllers.abstraction.dao;

import java.util.Date;
import java.util.List;

import models.wrapper.BookingWrapper;
import models.wrapper.OfferWrapper;

/**
 * An interface to describe Booking-related functionalities that are not covered by {@link _IGenericDAO}
 * 
 * @author Markus
 * 
 * 
 *
 */
public interface IBookingDAO extends _IGenericDAO<BookingWrapper> {

	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param o
	 * @param date
	 * @param duration
	 * @return
	 * @throws Exception
	 */
	public boolean isBooked(OfferWrapper o, Date date, int duration) throws Exception;
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param year
	 * @param month
	 * @return
	 * @throws Exception
	 */
	public abstract List<BookingWrapper> getBookings(int year, int month) throws Exception;
	
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param ow
	 * @return
	 * @throws Exception
	 */
	public abstract List<BookingWrapper> getBookings(OfferWrapper ow) throws Exception;
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public abstract List<BookingWrapper> getBookings(String id) throws Exception;


	
	
}