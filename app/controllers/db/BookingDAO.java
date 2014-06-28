package controllers.db;

import controllers.abstraction.dao.IBookingDAO;
import controllers.db.mongodb.dao.BookingMongoDAO;

public class BookingDAO {

	private static IBookingDAO usedDAO = BookingMongoDAO.getDAO();
	
	public static IBookingDAO getDAO(){
		return usedDAO;
	}
	
	public static void setDAO(IBookingDAO dao){
		usedDAO = dao;
	}
	
}
