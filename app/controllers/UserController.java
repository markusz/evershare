package controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import models.Message;
import models.User;
import models.forms.FacebookLogin;
import models.forms.Register;
import models.minimummodels.BookingMinimumModel;
import models.minimummodels.MessageMinimumModel;
import models.wrapper.MessageWrapper;
import models.wrapper.OfferWrapper;
import models.wrapper.UserWrapper;

import org.apache.commons.codec.digest.DigestUtils;

import play.api.libs.Crypto;
import play.data.DynamicForm;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import play.mvc.Security;
import util.data.FileUtil;
import util.db.UserManager;
import views.html.mapLocation;
import views.html.message;
import views.html.messenger;
import views.html.mybookings;
import views.html.myoffers;
import views.html.profile;
import controllers.actions.VerboseAction.Verbose;
import controllers.db.MessageDAO;
import controllers.db.OfferDAO;
import controllers.db.UserDAO;

public class UserController extends Controller {

	public static Result logout() {
		session().clear();
		response().discardCookies("rememberme");
		return redirect(routes.Application.index());
	}
	
	@Verbose
	public static Result updateUser() {
		form().bindFromRequest().get("lastname");
		
		return null;
		
	}

	@Verbose
	public static Result register() {
		
		

		Form<Register> form = form(Register.class).bindFromRequest();

		if (form.hasErrors()) {
			return badRequest("bad request");
		} else {

			Register data = form.get();

			 User u = new User();
			 try {
				 
				 u.name = data.name;
				 u.password = DigestUtils.md5Hex(User.PASSWORDSALT + data.password);
				 u.email = data.email;
				 u.firstname = data.firstname;
				 u.lastname = data.lastname;
				
				 UserWrapper uw = UserDAO.getDAO().save(new UserWrapper(u));
				 
				 
				session("userID", ""+uw.getObjectId());
                return redirect(routes.Application.index());

			} catch (Exception e) {
				e.printStackTrace();
				return badRequest();
			}
		}			 
	}

	public static Result fblogin() {

		
		try {
			Form<FacebookLogin> form = form(FacebookLogin.class).bindFromRequest();

			UserWrapper fbUser = null;
			try {

				String uID = session("userID");
				try {
					fbUser = UserDAO.getDAO().getByFacebookId(form.get().userID);
				} catch (Exception e) {
					//fail silently
				}
				UserWrapper uw = UserDAO.getDAO().get(uID);
				
				
				if (fbUser == null) {
					// This fb account isn't connected to any evershare acc

					// Connect/update profile to/with fb account
					uw = UserDAO.getDAO().get(uID);
					UserDAO.getDAO().updateFacebookCredentials(uw.getObjectId().toString(), form.get().userID, form.get().accessToken);

					return redirect(routes.UserController.myProfile());
				} else {
					return badRequest("The facebook account is already connected to another evershare account. Release that connection first!");
				}
			} catch (Exception e) {
				if (fbUser == null) {
					// Register a new user
					// TODO Provide a proper form to register
					User u = new User();
					u.name = "FBUser-" + form.get().userID;
					u.password = form.get().accessToken;
					u.facebookId = form.get().userID;
					u.facebookAccessToken = form.get().accessToken;
					fbUser =  new UserWrapper(u);
					UserDAO.getDAO().save(fbUser);
				}

				// Mark user as logged in
				session("userID", fbUser.getObjectId().toString());
				return redirect(routes.Application.index());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
	}

	public static Result fbunlink() {
		try {
			String uID = session("userID");

			if (uID != null) {
				UserWrapper uw = UserManager.getActiveUserAsWrapper();
				UserDAO.getDAO().removeFacebookCredentials(uw.getObjectId().toString());

				return redirect(routes.UserController.myProfile());
			} else {
				return badRequest("No one is logged in. You shouldn't have been able to visit this page or you have hacked it into your browser!");
			}
		} catch (Exception e) {
			return badRequest();
		}
	}
	
	
	public static Result login() {

		String username = form().bindFromRequest().field("name").value();
		String pw = form().bindFromRequest().field("pw").value();

		UserWrapper uw = null;
		try {
			uw = UserDAO.getDAO().getByUsername(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Logger.info(new Gson().toJson(u));
		if (uw != null) {
			if (uw.getBaseModel().password.equals(DigestUtils.md5Hex(User.PASSWORDSALT + pw))) {
				session("userID", uw.getBaseModel().getObjectId().toString());

				if (form().bindFromRequest().field("rememberme").value() != null) {
					// Set cookie
					response().setCookie("rememberme", Crypto.sign(uw.getBaseModel().name) + "-" + uw.getBaseModel().name);
				}

				if (form().bindFromRequest().field("API-Logon").value() != null) {
					return ok(uw.getBaseModel().getObjectId().toString());
				}

				return redirect(routes.Application.index());
			} else
				return badRequest("Wrong password");

		} else
			return badRequest("No such user");

	}


	
	@Security.Authenticated(Secured.class)
	public static Result myProfile() {
		try {
			UserWrapper uw = UserManager.getActiveUserAsWrapper();

			if(uw!=null)		
				return ok(profile.render(uw, form(FacebookLogin.class)));
			else
				return badRequest("No such user");
		} catch (Exception e) {
			return badRequest();
		}
	}

	public static Result settingsPage() {
		return ok("Settings Page");
	}

	public static Result showUserLocation() {
		return ok(mapLocation.render());
	}

	// Returns JSON for all User
	public static Result getAllUser() {
		try {
			List<UserWrapper> uw = UserManager.getAllUsers();
			return ok(Json.toJson(uw));
		} catch (Exception e) {
			return badRequest();
		}
	}

	// Returns JSON for a User
	public static Result getUser(String userID) {
		try {
			return ok(Json.toJson(UserManager.getUser(userID)));
		} catch (Exception e) {
			return badRequest();
		}
	}

	public static Result showMessenger() {

		try {
			List<Message> sentMessages = new ArrayList<Message>();
			List<Message> receivedMessages = new ArrayList<Message>();

			for (long i = 0; i < 5; i++) {
				Message m1 = new Message();
				m1.setTitle("Testnachricht " + i);
				m1.setMessage("Das ist Testnachricht #" + i);
				m1.setObjectId(""+i);

				sentMessages.add(m1);
			}

			for (long i = 5; i < 10; i++) {
				Message m1 = new Message();
				m1.setTitle("Testnachricht " + i);
				m1.setMessage("Das ist Testnachricht #" + i);
				m1.setObjectId(""+i);

				receivedMessages.add(m1);
			}

			UserWrapper uw = UserManager.getActiveUserAsWrapper();

			List<MessageMinimumModel> sM = uw.sentMessages;
			List<MessageMinimumModel> rM = uw.receivedMessages;
			MessageWrapper m = null;

			
			if (rM.size() > 0) {
				m = MessageDAO.getDAO().get(rM.get(0).getWrapperId());
			}else if (sM.size() > 0) {
				m = MessageDAO.getDAO().get(sM.get(0).getWrapperId());
			}
			
			if(m != null){
				MessageDAO.getDAO().changeHasBeenRead(m.getObjectId().toString(), true);
			}

			return ok(messenger.render(m, sM, rM));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
	}
	
	public static Result updateProfileImage(){
		
		try {
			
			
			
			String uid = UserManager.getActiveUserUserId();
			
			DynamicForm df = form().bindFromRequest();
			
			MultipartFormData body = request().body().asMultipartFormData();
			MultipartFormData.FilePart picture = body.getFile("avatar");

			if (picture != null) {
				String fileName = picture.getFilename();
				File file = picture.getFile();

				FileUtil.uploadUserProfileAvatar(uid, fileName, file);
			} else {
				flash("error", "Missing file");
			}

			return redirect(routes.UserController.myProfile());
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
		
	}
	
public static Result updateProfile(){
		
		try {
			
			
			
			String uid = UserManager.getActiveUserUserId();
			
			DynamicForm df = form().bindFromRequest();
			Integer postalCode = Integer.valueOf(df.get("postalCode"));
			String email = df.get("email");
			String firstname = df.get("firstname");
			String lastname = df.get("lastname");
			String street = df.get("street");
			String city = df.get("city");
			
			UserDAO.getDAO().upsert(uid, "baseEntity.postalcode", postalCode);
			UserDAO.getDAO().upsert(uid, "baseEntity.email", email);
			UserDAO.getDAO().upsert(uid, "baseEntity.firstname", firstname);
			UserDAO.getDAO().upsert(uid, "baseEntity.lastname", lastname);
			UserDAO.getDAO().upsert(uid, "baseEntity.street", street);
			UserWrapper uw = UserDAO.getDAO().upsert(uid, "baseEntity.city", city);
			UserManager.updateGeospatialInformation(uw);

			return redirect(routes.UserController.myProfile());
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
		
	}


	public static Result displayMessage(String messageId) {
		try {
			UserWrapper uw = UserManager.getActiveUserAsWrapper();

			List<MessageMinimumModel> sM = uw.sentMessages;
			List<MessageMinimumModel> rM = uw.receivedMessages;
			MessageWrapper m = MessageDAO.getDAO().get(messageId);
			MessageDAO.getDAO().changeHasBeenRead(m.getObjectId().toString(), true);
			

			return ok(messenger.render(m, sM, rM));
		} catch (Exception e) {
			return badRequest();
		}
	}

	public static Result newMessage() {

		String username = form().bindFromRequest().field("user").value();

		String subjectText = form().bindFromRequest().field("subject").value();

		
		return ok(message.render(form(Message.class), username, subjectText));
	}

	public static Result listMyOffers() {

		try {
			
			List<OfferWrapper> omm = OfferDAO.getDAO().getOffersOfUser(UserManager.getActiveUserUserId());
				
			return ok(myoffers.render(omm));
		} catch (Exception e) {
			return badRequest();
		}
	}

	public static Result listMyBookings() {

		try {
			UserWrapper uw = UserManager.getActiveUserAsWrapper();

			List<BookingMinimumModel> bmm = uw.bookings;

			return ok(mybookings.render(bmm));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
	}
}
