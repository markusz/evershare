package controllers.db;

import controllers.abstraction.dao.IOfferDAO;
import controllers.db.mongodb.dao.OfferMongoDAO;

public class OfferDAO {
	
	private static IOfferDAO usedDAO = OfferMongoDAO.getDAO();
	
	public static IOfferDAO getDAO(){
		return usedDAO;
	}
	
	public static void setDAO(IOfferDAO dao){
		usedDAO = dao;
	}

}
