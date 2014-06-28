import java.util.List;

import models.wrapper.BookingWrapper;
import models.wrapper.CategoryWrapper;
import models.wrapper.ItemWrapper;
import models.wrapper.MessageWrapper;
import models.wrapper.OfferWrapper;
import models.wrapper.RatingWrapper;
import models.wrapper.UserWrapper;

/**
 * @author Markus
 * @date 12.01.2013
 *
 */

/**
 * Represents a dummy DB and enables to test functionalities without the necessity of a real DB running. 
 * 
 * @author Markus
 *
 */
public final class DummyDatabase {
	
	private DummyDatabase db;
	
	List<OfferWrapper> offers  = null;
	List<UserWrapper> users = null;
	List<BookingWrapper> bookings = null;
	List<ItemWrapper> items = null;
	List<RatingWrapper> ratings = null;
	List<CategoryWrapper> categories = null;
	List<MessageWrapper> messages = null;

	
	
	private DummyDatabase(){
	}
	
	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public DummyDatabase getDB(){
		if(db == null){
			db = new DummyDatabase();
		}
		
		return db;
	}
	
	public void initDB(){
		getDB().initOffers();
		getDB().initUsers();
		getDB().initBookings();
		getDB().initItems();
		getDB().initRatings();
		getDB().initCategories();
		getDB().initMessages();
	}


	private void initMessages() {
		// TODO Auto-generated method stub
		
	}

	private void initCategories() {
		// TODO Auto-generated method stub
		
	}

	private void initRatings() {
		// TODO Auto-generated method stub
		
	}

	private void initItems() {
		// TODO Auto-generated method stub
		
	}

	private void initBookings() {
		// TODO Auto-generated method stub
		
	}

	private void initUsers() {
		// TODO Auto-generated method stub
		
	}

	private void initOffers() {
		// TODO Auto-generated method stub
		
	}
	

}
