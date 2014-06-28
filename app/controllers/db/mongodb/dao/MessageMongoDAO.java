package controllers.db.mongodb.dao;

import java.util.List;

import models.wrapper.MessageWrapper;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import com.google.common.collect.Lists;

import controllers.abstraction.dao.IMessageDAO;

/**
 * 
 * @author Markus
 *
 */
public class MessageMongoDAO extends _GenericMongoDAO<MessageWrapper> implements IMessageDAO{
	
	
private static MessageMongoDAO instance = null;
	
	private MessageMongoDAO() {
	}
	
	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public static MessageMongoDAO getDAO(){
		if(instance == null){
			instance = new MessageMongoDAO();
		}
		
		return instance;
	}
	
	

//	private static String COLLECTION_NAME = "message";

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#getCollectionName()
	 */
	@Override
	public String getCollectionName() {
		return "message";
		
		
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#getNumberOfUnreadMessages(org.bson.types.ObjectId)
	 */
	public Integer getNumberOfUnreadMessages(ObjectId userId) throws Exception {

		//TODO Geht sicher auch performanter -> todo
		return getUnreadMessages(userId).size();

	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#getUnreadMessages(org.bson.types.ObjectId)
	 */
	public List<MessageWrapper> getUnreadMessages(ObjectId userId) throws Exception {

return getUnreadMessages(userId.toString());
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#getSentMessages(org.bson.types.ObjectId)
	 */
	public List<MessageWrapper> getSentMessages(ObjectId userId) throws Exception {

		MongoCollection mc = getCollection();

		Iterable<MessageWrapper> i = mc.find().as(MessageWrapper.class);
		return Lists.newArrayList(i);

	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#getReceivedMessages(org.bson.types.ObjectId)
	 */
	public List<MessageWrapper> getReceivedMessages(ObjectId userId) throws Exception {

		MongoCollection mc = getCollection();

		Iterable<MessageWrapper> i = mc.find().as(MessageWrapper.class);
		return Lists.newArrayList(i);

	}

//	public List<Message> getSentMessages(User user) throws Exception {
//		return getSentMessages(user.get_id());
//	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#get(org.bson.types.ObjectId)
	 */
	@Override
	public MessageWrapper get(ObjectId id) throws Exception {
		return get(MessageWrapper.class, id);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#getAll()
	 */
	@Override
	public List<MessageWrapper> getAll() throws Exception {
		return getAll(MessageWrapper.class);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#getAll(java.lang.String)
	 */
	@Override
	public List<MessageWrapper> getAll(String selectionCriteria) throws Exception {
		return getAll(MessageWrapper.class, selectionCriteria);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IMessageDAO#countAll()
	 */
	@Override
	public Integer countAll() throws Exception {
		return countAll(MessageWrapper.class);
	}

	@Override
	public List<MessageWrapper> getUnreadReceivedMessages(ObjectId userId) throws java.lang.Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageWrapper> getUnreadMessages(String userId) throws Exception {
		MongoCollection mc = getCollection();

		Iterable<MessageWrapper> i = mc.find("{baseEntity.read:#, receiver.wrapperId: #}",false, userId).as(MessageWrapper.class);
		return Lists.newArrayList(i);
	}

	@Override
	public void changeHasBeenRead(String id, boolean b) throws Exception{
		upsert(id, "baseEntity.read", b);
//		UserDAO.getDAO().changeMessageHasBeenReadStatus(id, b);
		
	}
	
	



	

}
