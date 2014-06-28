package controllers;

import java.net.UnknownHostException;

import models.forms.FacebookLogin;
import models.forms.LogIn;
import models.forms.OfferSearch;
import models.forms.Register;
import models.helper.Page;
import models.wrapper.OfferWrapper;
import models.wrapper.UserWrapper;
import play.Logger;
import play.Play;
import play.api.libs.Crypto;
import play.mvc.Controller;
import play.mvc.Http.Cookie;
import play.mvc.Result;
import play.mvc.Security;
import util.constants.Constants;
import views.html.landing2;
import views.html.register;
import views.html.start;
import controllers.db.UserDAO;
import controllers.db.mongodb.dao.OfferMongoDAO;
import controllers.db.mongodb.util.MongoDBController;



public class Application extends Controller {
	
	public static class UsernameSelect {
		public String uname;
	}

	public static Result index() {

		// Read cookie
		Cookie remeberme = request().cookies().get("rememberme");
		if (remeberme != null) {
			// Split read string
			
			String[] credentials = remeberme.value().split("-");
			
			if(credentials.length < 2){
				return landing();
			}
			
			String cryptoSign = credentials[0];
			String name = credentials[1];

			if (cryptoSign.equals(Crypto.sign(name))) {
				// Get user and log him in
				try {
					UserWrapper u = UserDAO.getDAO().getByUsername(name);
					session("userID", u.getObjectId().toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		try {
			
			if (session("userID") != null && UserDAO.getDAO().get(session("userID")) != null) {
				return start();
			} else {
				return landing();
			}
		} catch (Exception e) {
			return landing();
		}

	}

	public static Result fblogin() {
		for(String s : form().bindFromRequest().data().keySet()){
			Logger.info(s);
		}
		return start();
	}
	
	public static Result dropDB(){
		try {
			
			MongoDBController.dropDB(Constants.DB_NAME);
			return redirect(routes.Application.start());			
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return redirect(routes.Application.start());
		}
	}
	
	@Security.Authenticated(Secured.class)
	public static Result start() {
		
		try {
			Integer pagesize = Play.application().configuration().getInt("defaultPagesize");
			Page<OfferWrapper> ofrs = OfferMongoDAO.getDAO().page(0, pagesize, "", "", "");

			return ok(start.render(form(OfferSearch.class), session("userID"), ofrs));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
	}

	@Security.Authenticated(Secured.class)
	public static Result startPaginate(Integer page) {

		try {
			Integer pagesize = Play.application().configuration().getInt("defaultPagesize");
			Page<OfferWrapper> ofrs = OfferMongoDAO.getDAO().page(page, pagesize, "name", "asc", "");

			return ok(start.render(form(OfferSearch.class), session("userID"), ofrs));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
	}

	public static Result registerPage() {
		String username = form().bindFromRequest().field("uname").value();
		return ok(register.render(form(Register.class), username));
	}

	public static Result landing() {
		return ok(landing2.render(form(UsernameSelect.class),
				form(LogIn.class), form(Register.class), form(FacebookLogin.class)));
	}

	public static Result channel() {
		return ok("<script src=\"//connect.facebook.net/en_US/all.js\"></script>");
	}

}