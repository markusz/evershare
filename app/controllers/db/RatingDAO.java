package controllers.db;

import controllers.abstraction.dao.IRatingDAO;
import controllers.db.mongodb.dao.RatingMongoDAO;

public class RatingDAO {
	
	private static IRatingDAO usedDAO = RatingMongoDAO.getDAO();

	public static IRatingDAO getDAO() {
		return usedDAO;
	}

	public static void setDAO(IRatingDAO dao) {
		usedDAO = dao;
	}


	
	
}
