package controllers.db.dummydb.dao;

import java.util.Date;
import java.util.List;

import models.helper.Page;
import models.helper.SearchCriteriaSet;
import models.interfaces.IGeocodeable;
import models.minimummodels.BookingMinimumModel;
import models.wrapper.CategoryWrapper;
import models.wrapper.OfferWrapper;
import models.wrapper.UserWrapper;
import controllers.abstraction.dao.IOfferDAO;
import controllers.db.OfferDAO;

/**
 * 
 * @author Markus
 *
 */
public class OfferDummyDAO extends _GenericDummyDAO<OfferWrapper> implements IOfferDAO {

	@Override
	public List<OfferWrapper> getOffers(String restriction) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OfferWrapper> getOffers(SearchCriteriaSet cs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OfferWrapper> page(int i, Integer pagesize, String string, String string2, String string3) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferWrapper saveWithGeocoder(OfferWrapper offer) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OfferWrapper> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OfferWrapper> getOffersOfUser(UserWrapper uw) throws Exception {
		StringBuilder sb =  new StringBuilder("{userMinimumModel.label: '").append(uw.getBaseModel().name).append("'}");
		return OfferDAO.getDAO().getOffers(sb.toString());
	}

	@Override
	public List<OfferWrapper> getOffersOfUser(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OfferWrapper upsert(String id, String key, String value)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateGeocodeInformation(IGeocodeable object) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ensureIndexes() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isOfferBooked(OfferWrapper offerWrapper, Date date) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addBooking(String offerId, BookingMinimumModel mmm)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OfferWrapper> getOffersOfCategory(CategoryWrapper uw)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<OfferWrapper> getOffersOfCategory(String catId)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
