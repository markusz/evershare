package controllers.db.mongodb.dao;

import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import models.helper.Page;
import models.helper.SearchCriteriaSet;
import models.minimummodels.BookingMinimumModel;
import models.wrapper.CategoryWrapper;
import models.wrapper.OfferWrapper;
import models.wrapper.UserWrapper;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import util.api.google.GeocoderWorker;
import util.data.DateUtil;

import com.google.common.collect.Lists;

import controllers.abstraction.dao.IOfferDAO;

/**
 * 
 * @author Markus
 *
 */
public class OfferMongoDAO extends _GenericMongoDAO<OfferWrapper> implements IOfferDAO{
	
	String COLLECTION_NAME = "offer";
	
	private static OfferMongoDAO  instance = null;
	
	private OfferMongoDAO() {
	}
	
	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public static OfferMongoDAO getDAO(){
		if(instance == null){
			instance = new OfferMongoDAO();
		}
		return instance;
	}
	
	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	@Override
	public OfferWrapper get(ObjectId id) throws Exception {
		return get(OfferWrapper.class, id);
	}

	@Override
	public List<OfferWrapper> getAll() throws Exception {
		return getAll(OfferWrapper.class);
	}

	@Override
	public List<OfferWrapper> getAll(String selectionCriteria) throws Exception {
		return getAll(OfferWrapper.class, selectionCriteria);
	}

	@Override
	public Integer countAll() throws Exception {
		return countAll(OfferWrapper.class);
	}

	public OfferWrapper saveWithGeocoder(OfferWrapper offer) throws Exception {
		OfferWrapper u = save(offer);
		new GeocoderWorker(offer).run();

		return u;
	}

	public List<OfferWrapper> getOffers(String restriction) throws UnknownHostException {
		MongoCollection conn = getCollection();

		Iterable<OfferWrapper> i = conn.find(restriction).as(OfferWrapper.class);
		return Lists.newArrayList(i);
	}
	
	public List<OfferWrapper> getOffers(SearchCriteriaSet cs) throws UnknownHostException{
		List<OfferWrapper> all = getOffers(cs.toQueryString());
		if(cs.getTime() != null){
			List<OfferWrapper> cleaned = removeUnavailable(all, cs.getTime());
			return cleaned;
		}
		return all;
	}

	public Page<OfferWrapper> page(int i, Integer pagesize, String string, String string2, String string3) throws Exception {
		List<OfferWrapper> ow = getAll();
		return new Page<OfferWrapper>(ow, i, pagesize);
	}

	@Override
	public List<OfferWrapper> getOffersOfUser(UserWrapper uw) throws Exception {
		return getOffersOfUser(uw.getObjectId().toString());
	}

	@Override
	public List<OfferWrapper> getOffersOfUser(String userId) throws Exception {
		MongoCollection conn = getCollection();
		StringBuilder sb = new StringBuilder("{owner.wrapperId: '").append(userId).append("'}");
		return Lists.newArrayList(conn.find(sb.toString()).as(OfferWrapper.class));
	}

	@Override
	public void ensureIndexes() throws Exception {
		getCollection().ensureIndex("{baseEntity.loc: '2d'}");
		
	}

	@Override
	public boolean isOfferBooked(OfferWrapper offerWrapper, Date date) {
		return false;
	}
	
	@Override
	public void addBooking(String offerId, BookingMinimumModel mmm) throws Exception {
		pushToArray(new ObjectId(offerId), "bookings", mmm, mmm.getClass());	
	}

	@Override
	public List<OfferWrapper> getOffersOfCategory(CategoryWrapper uw) throws Exception{
		return getOffersOfCategory(uw.getObjectId().toString());
	}

	@Override
	public List<OfferWrapper> getOffersOfCategory(String catId) throws Exception {
		MongoCollection conn = getCollection();
		StringBuilder sb = new StringBuilder("{category.wrapperId: '").append(catId).append("'}");
		return Lists.newArrayList(conn.find(sb.toString()).as(OfferWrapper.class));
	}

	/**
	 * This is a dirty workaround that removes all offers that can, which is necessary because a bug in the jongo framework seems to prevent querying only unbooked offers.
	 * 
	 *
	 * @author Markus Ziller
	 * @date 12.02.2013
	 *
	 * @param all
	 * @return
	 * @throws ParseException 
	 */
	private List<OfferWrapper> removeUnavailable(List<OfferWrapper> all, Date date){
		Calendar searchDateCal = Calendar.getInstance();
		searchDateCal.setTime(date);
		Calendar bookingDates = Calendar.getInstance();
		List<OfferWrapper> list = new ArrayList<OfferWrapper>();
		
		for (OfferWrapper offerWrapper : all) {
			Boolean keepOffer = true;
			for(BookingMinimumModel bmm : offerWrapper.getBookings()){
				bookingDates.setTime(bmm.start);
				try {
					if(DateUtil.parseDate(bookingDates.getTime()).equalsIgnoreCase(DateUtil.parseDate(date))){
						keepOffer = false;
					}
				} catch (ParseException e) {
					
					e.printStackTrace();
				}
				for (int i = 1; i < bmm.duration; i++) {
					bookingDates.add(Calendar.DATE, 1);
					try {
						if(DateUtil.parseDate(bookingDates.getTime()).equalsIgnoreCase(DateUtil.parseDate(date))){
							keepOffer = false;
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if(keepOffer){
				list.add(offerWrapper);
			}
		}
		return list;
	}

}
