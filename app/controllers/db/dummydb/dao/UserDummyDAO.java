package controllers.db.dummydb.dao;

import java.util.List;

import models.interfaces.IGeocodeable;
import models.minimummodels.BookingMinimumModel;
import models.minimummodels.MessageMinimumModel;
import models.wrapper.UserWrapper;
import controllers.abstraction.dao.IUserDAO;

/**
 * 
 * @author Markus
 *
 */
public class UserDummyDAO extends _GenericDummyDAO<UserWrapper> implements IUserDAO {

	@Override
	public String getCollectionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWrapper saveWithGeocoder(UserWrapper user) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserWrapper> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserWrapper> getAll(String selectionCriteria) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer countAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserWrapper getByUsername(String name) throws Exception {
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
	public UserWrapper getByFacebookId(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addSentMessage(String userId, MessageMinimumModel mmm)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addReceivedMessage(String userId, MessageMinimumModel mmm)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBooking(String wrapperId, BookingMinimumModel bmm)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeMessageHasBeenReadStatus(String id, boolean b) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFacebookCredentials(String internalUserId)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFacebookCredentials(String internalId, String facebookId,
			String accessToken) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserWrapper upsert(String uid, String key, Integer postalCode)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
		
		
	

}
