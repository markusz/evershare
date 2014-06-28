package util.db;

import controllers.abstraction.PersistenceStrategy;
import controllers.db.BookingDAO;
import controllers.db.CategoryDAO;
import controllers.db.ItemDAO;
import controllers.db.MessageDAO;
import controllers.db.OfferDAO;
import controllers.db.RatingDAO;
import controllers.db.UserDAO;
import controllers.db.mongodb.dao.BookingMongoDAO;
import controllers.db.mongodb.dao.CategoryMongoDAO;
import controllers.db.mongodb.dao.ItemMongoDAO;
import controllers.db.mongodb.dao.MessageMongoDAO;
import controllers.db.mongodb.dao.OfferMongoDAO;
import controllers.db.mongodb.dao.RatingMongoDAO;
import controllers.db.mongodb.dao.UserMongoDAO;

/**
 * Changes the persistence Strategy
 * @author Markus
 *
 */
public class PersistenceManager {
	
	
	/**
	 * Sets the global persistence Strategy
	 * @author Markus
	 * @date 12.01.2013
	 * @param strategy An {@link Enum} of type {@link PersistenceStrategy} that defines the persistence strategy for all entities
	 */
	public static void setStrategy(PersistenceStrategy strategy){
		
		switch (strategy) {
		case SQL:
			
			//TODO
			
			break;
		case MONGODB:
			
			ItemDAO.setDAO(ItemMongoDAO.getDAO());
			UserDAO.setDAO(UserMongoDAO.getDAO());
			OfferDAO.setDAO(OfferMongoDAO.getDAO());
			RatingDAO.setDAO(RatingMongoDAO.getDAO());
			BookingDAO.setDAO(BookingMongoDAO.getDAO());
			MessageDAO.setDAO(MessageMongoDAO.getDAO());
			CategoryDAO.setDAO(CategoryMongoDAO.getDAO());
			
		case JAVA:
			
			//TODO
	
	break;

		default:
			break;
		}
		
	}
}
