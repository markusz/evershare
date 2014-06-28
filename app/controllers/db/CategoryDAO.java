package controllers.db;

import controllers.abstraction.dao.ICategoryDAO;
import controllers.db.mongodb.dao.CategoryMongoDAO;

public class CategoryDAO {
	
	private static ICategoryDAO usedDAO = CategoryMongoDAO.getDAO();

	public static ICategoryDAO getDAO() {
		return usedDAO;
	}

	public static void setDAO(ICategoryDAO dao) {
		usedDAO = dao;
	}

}
