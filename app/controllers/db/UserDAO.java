package controllers.db;

import controllers.abstraction.dao.IUserDAO;
import controllers.db.mongodb.dao.UserMongoDAO;

public class UserDAO {
	

	private static IUserDAO usedDAO = UserMongoDAO.getDAO();
	
	public static IUserDAO getDAO(){
		return usedDAO;
	}
	
	public static void setDAO(IUserDAO dao){
		usedDAO = dao;
	}

}
