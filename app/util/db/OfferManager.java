package util.db;

import java.util.List;

import models.wrapper.OfferWrapper;

import controllers.db.OfferDAO;

/**
 * Primary access point for querying offers from the db. Packs the methods of {@link OfferDAO.getDAO()} into userfriendly methods
 * @author Markus
 *
 */
public class OfferManager {
	
	public static List<OfferWrapper> getAllOffers() throws Exception{
		return OfferDAO.getDAO().getAll();
	}
	
	public static OfferWrapper getOffer(String id) throws Exception{
		return OfferDAO.getDAO().get(id);
	}

}
