package controllers.db.mongodb.dao;

import java.util.List;

import models.minimummodels.BookingMinimumModel;
import models.minimummodels.MessageMinimumModel;
import models.wrapper.UserWrapper;

import org.bson.types.ObjectId;

import util.api.google.GeocoderWorker;
import controllers.abstraction.dao.IUserDAO;

/**
 * 
 * @author Markus Z.
 * 
 */
public class UserMongoDAO extends _GenericMongoDAO<UserWrapper> implements IUserDAO {
	
	
private static UserMongoDAO instance = null;
	
	private UserMongoDAO() {
	}
	
	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public static UserMongoDAO getDAO(){
		if(instance == null){
			instance = new UserMongoDAO();
		}
		
		return instance;
	}
	

	private static String COLLECTION_NAME = "user";

	@Override
	public String getCollectionName() {
		return COLLECTION_NAME;
	}

	public UserWrapper saveWithGeocoder(UserWrapper user) throws Exception {
		UserWrapper u = save(user);
		new GeocoderWorker(u).run();

		return u;
	}


	@Override
	public UserWrapper get(ObjectId id) throws Exception {
		return get(UserWrapper.class, id);
	}


	@Override
	public List<UserWrapper> getAll() throws Exception {
		return getAll(UserWrapper.class);
	}


	@Override
	public List<UserWrapper> getAll(String selectionCriteria) throws Exception {
		return getAll(UserWrapper.class, selectionCriteria);
	}

	public Integer countAll() throws Exception {
		return countAll(UserWrapper.class);
	}
	
	@Override
	public UserWrapper getByUsername(String name) throws Exception {
		StringBuilder sb =  new StringBuilder("{baseEntity.name: '").append(name).append("'}");
		return getCollection().findOne(sb.toString()).as(UserWrapper.class);
	}
	
	@Override
	public void ensureIndexes() throws Exception {
		getCollection().ensureIndex("{baseEntity.loc: '2d'}");
		
	}

	@Override
	public UserWrapper getByFacebookId(String id) throws Exception {
		StringBuilder sb =  new StringBuilder("{baseEntity.facebookId: '").append(id).append("'}");
		return getCollection().findOne(sb.toString()).as(UserWrapper.class);
	}


	@Override
	public void removeFacebookCredentials(String internalUserId) throws Exception {
		upsert(internalUserId, "baseEntity.facebookId", "");
		upsert(internalUserId, "baseEntity.facebookAccessToken", "");
		
	}

	@Override
	public void addSentMessage(String userId, MessageMinimumModel mmm) throws Exception {
		pushToArray(new ObjectId(userId), "sentMessages", mmm, mmm.getClass());	
	}

	
	@Override
	public void addReceivedMessage(String userId, MessageMinimumModel mmm) throws Exception {
		pushToArray(new ObjectId(userId), "receivedMessages", mmm, mmm.getClass());		
	}

	@Override
	public void addBooking(String userId, BookingMinimumModel bmm) throws Exception {
		pushToArray(new ObjectId(userId), "bookings", bmm, bmm.getClass());		
	}

	@Override
	/**
	 * TODO not working
	 */
	public void changeMessageHasBeenReadStatus(String id, boolean b) throws Exception {
		//TODO
	}

	@Override
	public void updateFacebookCredentials(String internalId, String facebookId, String accessToken) throws Exception {
		upsert(internalId, "baseEntity.facebookId", facebookId);
		upsert(internalId, "baseEntity.facebookAccessToken", accessToken);
		
		
	}


}
