package controllers.abstraction.dao;

import java.util.List;

import models.wrapper.MessageWrapper;

import org.bson.types.ObjectId;

/**
 * An interface to describe Message-related functionalities that are not covered by {@link _IGenericDAO}
 * 
 * @author Markus
 *
 */
public interface IMessageDAO extends _IGenericDAO<MessageWrapper>  {

	/**
	 * <b> currently fetches all unread messages</b>  and returns .size() of the
	 * received list. You might as well use getUnreadMessages(ObjectId userId)
	 * to avoid double-querying the database
	 * 
	 * 
	 * 
	 * @author Markus Z.
	 * @date 10.12.2012
	 * @return
	 * @throws Exception
	 */
	public abstract Integer getNumberOfUnreadMessages(ObjectId userId) throws Exception;
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public abstract List<MessageWrapper> getUnreadMessages(ObjectId userId) throws Exception;
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public abstract List<MessageWrapper> getUnreadMessages(String userId) throws Exception;
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public abstract List<MessageWrapper> getSentMessages(ObjectId userId) throws Exception;
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public abstract List<MessageWrapper> getReceivedMessages(ObjectId userId) throws Exception;
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	public abstract List<MessageWrapper> getUnreadReceivedMessages(ObjectId userId) throws Exception;

	/**
	 * Changes if a message has been read
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param messageId
	 * @param hasBeenRead
	 * @throws Exception
	 */
	public abstract void changeHasBeenRead(String messageId, boolean hasBeenRead) throws Exception;


}