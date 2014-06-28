package controllers.abstraction.dao;

import java.util.List;

import models.interfaces.IGeocodeable;
import models.minimummodels.BookingMinimumModel;
import models.minimummodels.MessageMinimumModel;
import models.wrapper.UserWrapper;

/**
 * An interface to describe User-related functionalities that are not covered by {@link _IGenericDAO}
 * 
 * @author Markus
 *
 */
public interface IUserDAO extends _IGenericDAO<UserWrapper>{

	public abstract String getCollectionName();
	public abstract UserWrapper saveWithGeocoder(UserWrapper user) throws Exception;
	public abstract UserWrapper getByUsername(String name) throws Exception;
	public abstract List<UserWrapper> getAll() throws Exception;
	public abstract List<UserWrapper> getAll(String selectionCriteria) throws Exception;
	public abstract Integer countAll() throws Exception;
	public abstract void updateGeocodeInformation(IGeocodeable object) throws Exception;
	
	/**
	 * Ensures user related indexes
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @throws Exception
	 */
	public abstract void ensureIndexes() throws Exception;
	public abstract void addSentMessage(String userId, MessageMinimumModel mmm) throws Exception;
	public abstract void addReceivedMessage(String userId, MessageMinimumModel mmm) throws Exception;
	
	public abstract UserWrapper getByFacebookId(String id) throws Exception;
	public abstract void removeFacebookCredentials(String internalUserId) throws Exception;
	
	/**
	 * Updates a userwrapper after he did a booking
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param userId
	 * @param bmm
	 * @throws Exception
	 */
	public abstract void addBooking(String userId, BookingMinimumModel bmm)  throws Exception;
	public abstract void changeMessageHasBeenReadStatus(String id, boolean b)  throws Exception;
	public abstract void updateFacebookCredentials(String internalId,
			String facebookId, String accessToken) throws Exception;
	public abstract UserWrapper upsert(String uid, String key, Integer postalCode) throws Exception;
}