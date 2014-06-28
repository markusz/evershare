
import play.Application;
import play.GlobalSettings;
import play.Logger;
import util.data.DataInitUtil;
import util.db.PersistenceManager;
import controllers.abstraction.PersistenceStrategy;
import controllers.db.OfferDAO;
import controllers.db.UserDAO;


public class Global extends GlobalSettings {
	
	@Override
	public void onStart(Application app) {
		Logger.info("Application has started");

		// order is important
		try {
			PersistenceManager.setStrategy(PersistenceStrategy.MONGODB);
			DataInitUtil.createDefaultUser();
			DataInitUtil.createDefaultCategories();
			DataInitUtil.createDefaultOffers();
			OfferDAO.getDAO().ensureIndexes();
			UserDAO.getDAO().ensureIndexes();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
