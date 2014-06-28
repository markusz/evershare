package controllers;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import models.Booking;
import models.Offer;
import models.forms.OfferSearch;
import models.helper.EasyDate;
import models.helper.Page;
import models.helper.SearchCriteriaSet;
import models.wrapper.BookingWrapper;
import models.wrapper.OfferWrapper;
import models.wrapper.UserWrapper;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Result;
import play.mvc.Security;
import util.data.DateUtil;
import util.data.FileUtil;
import util.db.CategoryManager;
import util.db.OfferManager;
import util.db.UserManager;
import views.html.createoffer;
import views.html.mapoverview;
import views.html.offer;
import views.html.start;
import controllers.actions.VerboseAction.Verbose;
import controllers.db.BookingDAO;
import controllers.db.OfferDAO;

//import com.avaje.ebean.Page;

@Security.Authenticated(Secured.class)
public class OfferController extends Controller {

	@Verbose
	public static Result createOfferPage() {

		if (form().hasErrors()) {
			return badRequest("Form has errors");
		}

		return ok(createoffer.render(form(Offer.class)));
	}

	@Verbose
	public static Result createOffer() {

		try {
			Form<Offer> form = form(Offer.class).bindFromRequest();

			if (form.hasErrors()) {
				return badRequest("wrong form input ");
			}

			String categoryId = form().bindFromRequest().field("catSelect").value();
		
			
			Offer of = form.get();
			OfferWrapper oWrapper = new OfferWrapper(of);
			oWrapper.category = CategoryManager.getCategoryById(categoryId).getBaseModel().getMinimumModel();

			oWrapper.setUserMinimumModel(UserManager.getActiveUserAsMinimumModel());
			of.available = true;
			
			OfferDAO.getDAO().saveWithGeocoder(oWrapper);

			MultipartFormData body = request().body().asMultipartFormData();
			MultipartFormData.FilePart picture = body.getFile("img");

			if (picture != null) {
				String fileName = picture.getFilename();
				File file = picture.getFile();

				FileUtil.uploadOfferImage(oWrapper, fileName, file);
			} else {
				flash("error", "Missing file");
			}

			return redirect(routes.OfferController.showOffer(oWrapper.getObjectId().toString()));

		} catch (NumberFormatException e) {
			e.printStackTrace();
			return badRequest();
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}

	}

	@Verbose
	public static Result deleteOffer(String id) {

		try {
			OfferDAO.getDAO().delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return redirect(routes.Application.start());
	}

	public static Result offersOnMap() {
		try {
			List<OfferWrapper> ofrs = OfferManager.getAllOffers();

			return ok(mapoverview.render(form(OfferSearch.class),
					session("userID"), ofrs));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}
	}

	// Returns JSON for Offers
	public static Result getAllOffers() {

		try {
			// TODO
			List<models.wrapper.OfferWrapper> ow = OfferManager.getAllOffers();
			return ok(Json.toJson(ow));
		} catch (Exception e) {
			// TODO
			return ok();
		}
	}


	public static Result getOffer(String offerID) {
		try {
			models.wrapper.OfferWrapper ow = OfferManager.getOffer(offerID);
			return ok(Json.toJson(ow));
		} catch (Exception e) {
			return ok();
		}
	}
	
	
	public static Result showOfferForCategory(String catId) {

		try {
			List<OfferWrapper> ow = OfferDAO.getDAO().getOffersOfCategory(catId);
			Page<OfferWrapper> ofrs = new Page<OfferWrapper>(ow, 1, 8);

			Form<OfferSearch> form2 = form(OfferSearch.class);
			String userId = session("userID");

			return ok(start.render(form2, userId, ofrs));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest("Date coulnt be parsed");
		}

	}

	public static Result showOffer(String offerId) {

		try {
			OfferWrapper ow = OfferDAO.getDAO().get(offerId);
			
			List<OfferWrapper> ows = OfferDAO.getDAO().getOffersOfUser(
					ow.getUserMinimumModel().getWrapperId());
			
			List<OfferWrapper> relatedOffers = OfferDAO.getDAO().getOffersOfCategory(ow.category.wrapperId);
			ows.remove(ow);

			Booking b = new Booking();
			BookingWrapper bw = new BookingWrapper(b);
			bw.bookedOffer = OfferManager.getOffer(ow.getObjectId().toString()).getBaseModel().getMinimumModel();

			List<BookingWrapper> bookings = BookingDAO.getDAO().getBookings(ow);
			List<EasyDate> bookedDays = DateUtil.getBookedDays(bookings);

			UserWrapper uw = UserManager.getActiveUserAsWrapper();

			return ok(offer.render(ow, ows, relatedOffers, form(Booking.class).fill(b),
					bookedDays, uw));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest();
		}

	}

	public static Result search() {

		try {
			Form<OfferSearch> form = form(OfferSearch.class).bindFromRequest();
			SearchCriteriaSet scs = new SearchCriteriaSet(form.get());

			List<OfferWrapper> ow = OfferDAO.getDAO().getOffers(scs);
			Page<OfferWrapper> ofrs = new Page<OfferWrapper>(ow, 1, 8);
			Form<OfferSearch> form2 = form(OfferSearch.class);
			String userId = session("userID");

			return ok(start.render(form2, userId, ofrs));
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest("Your search resulted in an error. We apologize. Please try again");
		}

	}

}
