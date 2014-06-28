package controllers;

import java.util.Date;

import models.Message;
import models.minimummodels.MessageMinimumModel;
import models.minimummodels.UserMinimumModel;
import models.wrapper.MessageWrapper;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import util.db.UserManager;
import controllers.db.MessageDAO;
import controllers.db.UserDAO;

public class MessageController extends Controller {


	public static Result sendMessage() {

		try {
			Form<Message> form = form(Message.class).bindFromRequest();
			Message m = form.get();

			m.setTimestamp(new Date());
			m.setRead(false);

			String recepientName = form().bindFromRequest().field("recName")
					.value();
			MessageController.sendAndPersistMessage(m,
					UserManager.getActiveUserAsMinimumModel(), UserDAO.getDAO()
							.getByUsername(recepientName).getBaseModel()
							.getMinimumModel());

			return redirect(routes.UserController.showMessenger());
		} catch (Exception e) {
			e.printStackTrace();
			return badRequest(e.getMessage());
		}
	}

	public static void sendAndPersistMessage(Message m,
			UserMinimumModel sender, UserMinimumModel recepient)
			throws Exception {

		MessageWrapper mWrapper = new MessageWrapper(m);
		mWrapper.sender = sender;
		mWrapper.receiver = recepient;

		MessageDAO.getDAO().save(mWrapper);
		MessageMinimumModel created = mWrapper.getBaseModel().getMinimumModel();
		created.setMinimumModelWrapperId(mWrapper.getObjectId().toString());

		if (sender != null && sender.wrapperId != "1") {
			UserDAO.getDAO().addSentMessage(UserManager.getActiveUserUserId(),
					created);
		}
		UserDAO.getDAO().addReceivedMessage(mWrapper.receiver.wrapperId,
				created);
	}

}
