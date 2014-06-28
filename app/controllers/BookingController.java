package controllers;

import java.util.Date;

import models.Booking;
import models.Message;
import models.User;
import models.minimummodels.BookingMinimumModel;
import models.minimummodels.UserMinimumModel;
import models.wrapper.BookingWrapper;
import models.wrapper.OfferWrapper;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import util.data.DateUtil;
import util.db.UserManager;
import views.html.confirmation;
import controllers.db.BookingDAO;
import controllers.db.OfferDAO;
import controllers.db.UserDAO;

@Security.Authenticated(Secured.class)
public class BookingController extends Controller {

	public static Result createBooking(String offerID) {

		try {
			Date date = DateUtil.parseString(form().bindFromRequest()
					.field("date").value());

			int duration = Integer.valueOf(form().bindFromRequest()
					.field("duration").value());

			OfferWrapper o = OfferDAO.getDAO().get(offerID);

			// TODO
			if (BookingDAO.getDAO().isBooked(o, date, duration)) {
				// This booking collides with an existing one
				flash("Error: already booked in that time");
				return badRequest("already booked in that time");
			}

			Booking booking = new Booking();
			booking.bookTime = System.currentTimeMillis();
			booking.bookingStart = date;
			booking.duration = duration;

			BookingWrapper bw = new BookingWrapper(booking);

			bw.bookedOffer = OfferDAO.getDAO().get(offerID).getBaseModel()
					.getMinimumModel();
			bw.bookingUser = UserManager.getActiveUserAsMinimumModel();

			BookingDAO.getDAO().save(bw);

			BookingMinimumModel bmm = bw.getBaseModel().getMinimumModel();
			bmm.update(bw.bookingUser.getWrapperId(), o.getObjectId()
					.toString(), bw.bookingUser.label, o.getBaseModel().name);

			OfferDAO.getDAO().addBooking(o.getObjectId().toString(), bmm);
			UserDAO.getDAO().addBooking(bw.bookingUser.getWrapperId(), bmm);

			UserMinimumModel offerOwner = o.getUserMinimumModel();
			UserMinimumModel offerBooker = UserManager
					.getActiveUserAsMinimumModel();
			User owner = UserManager.getUser(o.getUserMinimumModel().wrapperId)
					.getBaseModel();
			User booker = UserManager.getUser(offerBooker.getWrapperId())
					.getBaseModel();

			Message msgToOfferOwner = new Message();
			msgToOfferOwner.setRead(false);
			msgToOfferOwner.setTitle("Dein Angebot (" + o.getBaseModel().name
					+ ") wurde gebucht");
			msgToOfferOwner.setMessage("Hallo "
					+ owner.firstname
					+ ",\n"
					+ "Dein Angebot "
					+ o.getBaseModel().name
					+ " wurde ab "
					+ DateUtil.parseDate(booking.getDate())
					+ " für "
					+ booking.duration
					+ " Tag(e) gebucht. Der Preis für das Angebot beträgt "
					+ o.getBaseModel().getPriceFormatted(
							o.getBaseModel().priceph)
					+ "€ pro Tag. Die Kontaktdaten des Buchenden sind:\n"
					+ "Name: " + booker.firstname + " " + booker.lastname + " "
					+ "\nEmail: " + booker.email);
			MessageController.sendAndPersistMessage(msgToOfferOwner,
					new UserMinimumModel("1", "Evershare"), offerOwner);

			Message msgToBooker = new Message();
			msgToBooker.setRead(false);
			msgToBooker.setTitle("Du hast ein Angebot gebucht: "
					+ o.getBaseModel().name);
			msgToBooker.setMessage("Hallo "
					+ booker.name
					+ ",\nDu hast das Angebot "
					+ o.getBaseModel().name
					+ " ab "
					+ DateUtil.parseDate(booking.getDate())
					+ " für "
					+ booking.duration
					+ " Tag(e) gebucht. Der Preis für das Angebot beträgt "
					+ o.getBaseModel().getPriceFormatted(
							o.getBaseModel().priceph)
					+ "€ pro Tag. Die Kontaktdaten des Anbieters sind:"
					+ "\nName: " + owner.firstname + " " + owner.lastname
					+ "\nEmail: " + owner.email + "\nAbholadresse ist "
					+ o.getBaseModel().getAddress());
			MessageController.sendAndPersistMessage(msgToBooker,
					new UserMinimumModel("1", "Evershare"), offerBooker);

			if (form().bindFromRequest().field("API").value() != null) {
				return ok(bw.getObjectId().toString() + "");
			}

			return ok(confirmation.render(o, bw, owner));

		} catch (Exception e) {
			e.printStackTrace();
			flash("Error: ParseError date");
			return badRequest("ParseError date");
		}

	}
}
