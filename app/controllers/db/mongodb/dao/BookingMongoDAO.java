package controllers.db.mongodb.dao;


import java.util.Date;
import java.util.List;

import models.wrapper.BookingWrapper;
import models.wrapper.OfferWrapper;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import com.google.common.collect.Lists;

import controllers.abstraction.dao.IBookingDAO;

/**
 * 
 * @author Markus
 *
 */
public class BookingMongoDAO extends _GenericMongoDAO<BookingWrapper> implements IBookingDAO {
	
private static BookingMongoDAO instance = null;
	
	private BookingMongoDAO() {
	}
	
	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public static BookingMongoDAO getDAO(){
		if(instance == null){
			instance = new BookingMongoDAO();
		}
		
		return instance;
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IBookingDAO#getCollectionName()
	 */
	@Override
	public String getCollectionName() {
		return "booking";
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IBookingDAO#get(org.bson.types.ObjectId)
	 */
	@Override
	public BookingWrapper get(ObjectId id) throws Exception {
		return get(BookingWrapper.class, id);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IBookingDAO#getAll()
	 */
	@Override
	public List<BookingWrapper> getAll() throws Exception {
		return getAll(BookingWrapper.class);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IBookingDAO#getAll(java.lang.String)
	 */
	@Override
	public List<BookingWrapper> getAll(String selectionCriteria) throws Exception {
		return getAll(BookingWrapper.class);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IBookingDAO#countAll()
	 */
	@Override
	public Integer countAll() throws Exception {
		return countAll(BookingWrapper.class);
	}

	@Override
	public boolean isBooked(OfferWrapper o, Date date, int duration)
			throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BookingWrapper> getBookings(OfferWrapper ow) throws Exception {
		return getBookings(ow.getObjectId().toString());
	}

	@Override
	public List<BookingWrapper> getBookings(String id) throws Exception{
		
		MongoCollection conn = getCollection();
		StringBuilder sb = new StringBuilder("{bookedOffer.wrapperId: '").append(id).append("'}");
		
		return Lists.newLinkedList(conn.find(sb.toString()).as(BookingWrapper.class));
	}

	@Override
	public List<BookingWrapper> getBookings(int year, int month)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	



}
