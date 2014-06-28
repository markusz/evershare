package controllers.db.dummydb.dao;

import java.util.List;

import models.wrapper.MessageWrapper;

import org.bson.types.ObjectId;

import controllers.abstraction.dao.IMessageDAO;

public class MessageDummyDAO  extends _GenericDummyDAO<MessageWrapper> implements IMessageDAO {

	@Override
	public Integer getNumberOfUnreadMessages(ObjectId userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageWrapper> getUnreadMessages(ObjectId userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageWrapper> getSentMessages(ObjectId userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageWrapper> getReceivedMessages(ObjectId userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageWrapper> getUnreadReceivedMessages(ObjectId userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MessageWrapper> getUnreadMessages(String userId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void changeHasBeenRead(String string, boolean b) throws Exception{
		// TODO Auto-generated method stub
		
	}

}
