package util.db;

import java.util.List;

import controllers.db.MessageDAO;

import models.wrapper.MessageWrapper;

/**
 * Primary access point for querying messages from the db. Packs the methods of {@link MessageDAO.getDAO()} into userfriendly methods
 * @author Markus
 *
 */
public class MessageManager {
	
	public static Integer getNumberOfUnreadReceivedMessages() throws Exception{
		return getUnreadReceivedMessages().size();
	}
	
	public static List<MessageWrapper> getUnreadReceivedMessages() throws Exception{
		return MessageDAO.getDAO().getUnreadMessages(UserManager.getActiveUserUserId());
	}

}
