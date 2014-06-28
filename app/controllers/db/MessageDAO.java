package controllers.db;

import controllers.abstraction.dao.IMessageDAO;
import controllers.db.mongodb.dao.MessageMongoDAO;

public class MessageDAO {
	
	private static IMessageDAO usedDAO = MessageMongoDAO.getDAO();

	public static IMessageDAO getDAO() {
		return usedDAO;
	}

	public static void setDAO(IMessageDAO dao) {
		usedDAO = dao;
	}


}
