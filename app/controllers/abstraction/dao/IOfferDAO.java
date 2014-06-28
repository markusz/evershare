package controllers.abstraction.dao;

import java.util.Date;
import java.util.List;

import models.helper.Page;
import models.helper.SearchCriteriaSet;
import models.interfaces.IGeocodeable;
import models.minimummodels.BookingMinimumModel;
import models.wrapper.CategoryWrapper;
import models.wrapper.OfferWrapper;
import models.wrapper.UserWrapper;

/**
 * An interface to describe Offer-related functionalities that are not covered by {@link _IGenericDAO}
 * 
 * @author Markus
 *
 */
public interface IOfferDAO extends _IGenericDAO<OfferWrapper>{


	
	public abstract List<OfferWrapper> getOffers(String restriction) throws Exception;
	public abstract List<OfferWrapper> getOffers(SearchCriteriaSet cs) throws Exception;
	
	public abstract List<OfferWrapper> getOffersOfUser(UserWrapper uw) throws Exception;
	public abstract List<OfferWrapper> getOffersOfUser(String userId) throws Exception;
	
	public abstract List<OfferWrapper> getOffersOfCategory(CategoryWrapper uw) throws Exception;
	public abstract List<OfferWrapper> getOffersOfCategory(String catId) throws Exception;

	/**
	 * gets a page with pagesize of ALL offers
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param i
	 * @param pagesize
	 * @param string
	 * @param string2
	 * @param string3
	 * @return
	 * @throws Exception
	 */
	public abstract Page<OfferWrapper> page(int i, Integer pagesize, String string, String string2, String string3) throws Exception;

	public abstract OfferWrapper save(OfferWrapper offerWrapper) throws Exception;
	public abstract OfferWrapper saveWithGeocoder(OfferWrapper offer) throws Exception;
	
	public abstract void delete(OfferWrapper offerWrapper) throws Exception;
	public abstract void delete(String id) throws Exception;
	
	/**
	 * ensures indexing of certain fields
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @throws Exception
	 */
	public abstract void ensureIndexes() throws Exception;
	
	public abstract void updateGeocodeInformation(IGeocodeable object) throws Exception;
	public abstract boolean isOfferBooked(OfferWrapper offerWrapper, Date date) throws Exception;
	public abstract void addBooking(String offerId, BookingMinimumModel mmm) throws Exception;

}