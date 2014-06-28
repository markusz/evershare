package controllers;

import models.forms.FacebookLogin;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.footerContent;

public class FooterController extends Controller {

	public static Result impressum() {
		return ok(footerContent.render("Impressum", form(FacebookLogin.class)));
	}

	public static Result werSindWir() {
		return ok(footerContent.render("Wer sind wir?",form(FacebookLogin.class)));
	}

	public static Result datenschutz() {

		return ok(footerContent.render("Datenschutz", form(FacebookLogin.class)));
	}

	public static Result kontakt() {
		return ok(footerContent.render("Kontakt", form(FacebookLogin.class)));
	}

	public static Result hilfe() {
		return ok(footerContent.render("Hilfe", form(FacebookLogin.class)));
	}
}