package util.db;

import java.util.List;

import models.User;
import models.minimummodels.UserMinimumModel;
import models.wrapper.UserWrapper;
import play.mvc.Controller;
import util.api.google.GeocoderWorker;
import controllers.db.UserDAO;

/**
 * Primary access point for querying users from the db. Packs the methods of {@link UserDAO.getDAO()} into userfriendly methods
 * @author Markus
 *
 */
public class UserManager {

	public static UserWrapper getActiveUserAsWrapper() throws Exception {
		return UserDAO.getDAO().get(Controller.session("userID"));
	}

	public static User getActiveUserAsBase() throws Exception {
		return UserDAO.getDAO().get(Controller.session("userID")).getBaseModel();
	}

	public static UserMinimumModel getActiveUserAsMinimumModel() throws Exception {
		return UserDAO.getDAO().get(Controller.session("userID")).getBaseModel().getMinimumModel();
	}

	public static List<UserWrapper> getAllUsers() throws Exception {
		return UserDAO.getDAO().getAll();
	}

	public static UserWrapper getUser(String userID) throws Exception {
		return UserDAO.getDAO().get(userID);
	}

	public static String getActiveUserUserId() throws Exception {
		return getActiveUserAsWrapper().getObjectId().toString();
	}
	
	public static void updateGeospatialInformation(UserWrapper uw){
		new GeocoderWorker(uw).run();
	}
}
