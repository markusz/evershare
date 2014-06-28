package controllers.db;

import controllers.abstraction.dao.IItemDAO;
import controllers.db.mongodb.dao.ItemMongoDAO;

public class ItemDAO {
	
	private static IItemDAO usedDAO = ItemMongoDAO.getDAO();

	public static IItemDAO getDAO() {
		return usedDAO;
	}

	public static void setDAO(IItemDAO dao) {
		usedDAO = dao;
	}


}
