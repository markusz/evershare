package util.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Offer;
import models.User;
import models.wrapper.CategoryWrapper;
import models.wrapper.OfferWrapper;
import models.wrapper.UserWrapper;

import org.apache.commons.codec.digest.DigestUtils;

import play.Logger;
import play.Play;
import util.db.CategoryManager;
import controllers.db.CategoryDAO;
import controllers.db.OfferDAO;
import controllers.db.UserDAO;

//import com.google.common.io.Files;

public class DataInitUtil {

	public static List<OfferWrapper> createDefaultOffers() throws Exception {

		List<OfferWrapper> offers = new ArrayList<OfferWrapper>();

		if (OfferDAO.getDAO().countAll() < 1) {

			// clearImageFiles();

			Offer o1 = new Offer();
			OfferWrapper ow1 = new OfferWrapper(o1);
			Offer o2 = new Offer();
			OfferWrapper ow2 = new OfferWrapper(o2);
			Offer o3 = new Offer();
			OfferWrapper ow3 = new OfferWrapper(o3);
			Offer o4 = new Offer();
			OfferWrapper ow4 = new OfferWrapper(o4);
			Offer o5 = new Offer();
			OfferWrapper ow5 = new OfferWrapper(o5);
			Offer o6 = new Offer();
			OfferWrapper ow6 = new OfferWrapper(o6);
			Offer o7 = new Offer();
			OfferWrapper ow7 = new OfferWrapper(o7);
			Offer o8 = new Offer();
			OfferWrapper ow8 = new OfferWrapper(o8);
			Offer o9 = new Offer();
			OfferWrapper ow9 = new OfferWrapper(o9);

			String imgUploadPath = Play.application().path().toString()
					+ "/"
					+ Play.application().configuration()
							.getString("defaultOffersImagesPath") + "/";
			
			o1.name = "Akkubohrer";
			o1.description = "Handlicher Akku-Bohrschrauber mit sehr kurzer Bauform "
					+ "Mechanisches 2-Gang-Planetengetriebe mit Metallzahnrädern "
					+ "zur optimalen Kraftübertragung "
					+ "Elektronikschalter für stufenlose Drehzahlsteuerung "
					+ "Drehmoment in 16 Stufen plus Bohrstufe einstellbar "
					+ "Handgriff mit eingelegtem Gummipolster für sicheren "
					+ "Halt und bequemes Arbeiten Mit Spindelarretierung.";
			o1.available = true;
			o1.city = "Muenchen";
			ow1.setUserMinimumModel(UserDAO.getDAO().getAll().get(0)
					.getBaseModel().getMinimumModel());
			o1.postalCode = 80797;
			o1.street = "Schleissheimerstr. 12";
			// o1.latitude = 48.1489;
			// o1.longitude = 11.5592;
			o1.priceph = 3.00;
			o1.imgUrl = "akkuschrauber_makita.jpg";
			ow1.category = CategoryManager.getCategoryByName("Heimwerk").getBaseModel().getMinimumModel();

			OfferDAO.getDAO().saveWithGeocoder(ow1);
			FileUtil.uploadOfferImageOnInit(ow1, imgUploadPath);

			o2.name = "Pavillion";
			o2.description = "Auch bei Regen trocken bleiben";
			o2.available = true;
			o2.city = "Muenchen";
			ow2.setUserMinimumModel(UserDAO.getDAO().getAll().get(0)
					.getBaseModel().getMinimumModel());
			o2.postalCode = 80798;
			o2.street = "Schellingstr. 84";
			o2.priceph = 2.50;
			// o2.latitude = 48.1529;
			// o2.longitude = 11.5685;
			o2.imgUrl = "pavillion.jpg";
			ow2.category = CategoryManager.getCategoryByName("Party").getBaseModel().getMinimumModel();

			OfferDAO.getDAO().saveWithGeocoder(ow2);
			FileUtil.uploadOfferImageOnInit(ow2, imgUploadPath);

			o3.name = "Stihl-Kettensaege";
			o3.description = "Gute Kettensaege fuer alle Art Holz";
			o3.available = true;
			o3.city = "Muenchen";
			ow3.setUserMinimumModel(UserDAO.getDAO().getAll().get(0)
					.getBaseModel().getMinimumModel());
			o3.postalCode = 80339;
			o3.street = "Kazmairstrasse 43";
			o3.priceph = 6.00;
			// o3.latitude = 48.1350;
			// o3.longitude = 11.5406;
			o3.imgUrl = "kettensaege_stihl.jpg";
			ow3.category = CategoryManager.getCategoryByName("Großgerät").getBaseModel().getMinimumModel();

			OfferDAO.getDAO().saveWithGeocoder(ow3);
			FileUtil.uploadOfferImageOnInit(ow3, imgUploadPath);

			o4.name = "Gebrauchter Schlitten";
			o4.description = "Holzschlitten fuer 2 Personen";
			o4.available = true;
			o4.city = "Muenchen";
			ow4.setUserMinimumModel(UserDAO.getDAO().getAll().get(1)
					.getBaseModel().getMinimumModel());
			o4.postalCode = 80331;
			o4.street = "Zweibrueckenstrasse 15";
			o4.priceph = 3.20;
			// o4.latitude = 48.1333;
			// o4.longitude = 11.5841;
			o4.imgUrl = "schlitten.jpg";
			ow4.category = CategoryManager.getCategoryByName("Freizeit").getBaseModel().getMinimumModel();;

			OfferDAO.getDAO().saveWithGeocoder(ow4);
			FileUtil.uploadOfferImageOnInit(ow4, imgUploadPath);

			o5.name = "Philips Boxen";
			o5.description = "Ideal fuer Garagenparties oder fuers Grillen!";
			o5.available = true;
			o5.city = "Muenchen";
			ow5.setUserMinimumModel(UserDAO.getDAO().getAll().get(1)
					.getBaseModel().getMinimumModel());
			o5.postalCode = 80803;

			o5.street = "Clemensstr. 32";
			o5.priceph = 4.50;
			// o5.latitude = 48.1643;
			// o5.longitude = 11.5804;
			o5.imgUrl = "philips-boxen.jpg";
			ow5.category = CategoryManager.getCategoryByName("Party").getBaseModel().getMinimumModel();;

			OfferDAO.getDAO().saveWithGeocoder(ow5);
			FileUtil.uploadOfferImageOnInit(ow5, imgUploadPath);

			o6.name = "Bierbank";
			o6.description = "Platz fuer 10 Personen";
			o6.available = true;
			o6.city = "Muenchen";
			ow6.setUserMinimumModel(UserDAO.getDAO().getAll().get(1)
					.getBaseModel().getMinimumModel());
			o6.postalCode = 81667;
			o6.street = "Pariser Platz 5";
			o6.priceph = 2.70;
			// o6.latitude = 48.1269;
			// o6.longitude = 11.5961;
			o6.imgUrl = "bierbank.jpg";

			ow6.category = CategoryManager.getCategoryByName("Party").getBaseModel().getMinimumModel();;

			OfferDAO.getDAO().saveWithGeocoder(ow6);
			FileUtil.uploadOfferImageOnInit(ow6, imgUploadPath);

			o7.name = "Makita-Presslufthammer";
			o7.description = "Der Hammer fuer jeden Untergrund";
			o7.available = true;
			o7.city = "Muenchen";
			ow7.setUserMinimumModel(UserDAO.getDAO().getAll().get(1)
					.getBaseModel().getMinimumModel());
			o7.postalCode = 80799;
			o7.street = "Barer Strasse 56";
			o7.priceph = 12.00;
			// o7.latitude = 48.1505;
			// o7.longitude = 11.5792;
			o7.imgUrl = "presslufthammer_makita.jpg";
			ow7.category = CategoryManager.getCategoryByName("Großgerät").getBaseModel().getMinimumModel();;

			OfferDAO.getDAO().saveWithGeocoder(ow7);
			FileUtil.uploadOfferImageOnInit(ow7, imgUploadPath);

			o8.name = "Temperaturmessgerät";
			o8.description = "Infrarot Messgerät für Temperaturen auf Oberflächen";
			o8.available = true;
			o8.city = "München";
			ow8.setUserMinimumModel(UserDAO.getDAO().getAll().get(1)
					.getBaseModel().getMinimumModel());
			o8.postalCode = 80805;
			o8.street = "Biedersteiner Strasse 87";
			o8.priceph = 10.00;
			// o8.latitude = 48.1693;
			// o8.longitude = 11.5971;
			o8.imgUrl = "temperaturmesser.jpg";
			ow8.category = CategoryManager.getCategoryByName("Werkzeug").getBaseModel().getMinimumModel();;

			OfferDAO.getDAO().saveWithGeocoder(ow8);
			FileUtil.uploadOfferImageOnInit(ow8, imgUploadPath);

			o9.name = "Anhänger";
			o9.description = "Unser groesster meistverkaufter Anhaenger ueberhaupt. Er ist besonders fuer den privaten Gebrauch sehr nuetzlich und in den meisten Faellen ausreichend";
			o9.available = true;
			ow9.setUserMinimumModel(UserDAO.getDAO().getAll().get(1)
					.getBaseModel().getMinimumModel());
			o9.postalCode = 85664;
			o9.city = "Hohenlinden";
			o9.street = "Kanzleiweg 7a";
			o9.priceph = 13.00;
			// o9.latitude = 48.1693;
			// o9.longitude = 11.5971;
			o9.imgUrl = "anheanger.jpg";
			ow9.category = CategoryManager.getCategoryByName("Auto").getBaseModel().getMinimumModel();;

			OfferDAO.getDAO().saveWithGeocoder(ow9);
			FileUtil.uploadOfferImageOnInit(ow9, imgUploadPath);

			offers.add(ow1);
			offers.add(ow2);
			offers.add(ow3);
			offers.add(ow4);
			offers.add(ow5);
			offers.add(ow6);
			offers.add(ow7);
			offers.add(ow8);
			offers.add(ow9);

		}
		return offers;
	}

	static void delete(File f) throws IOException {
		if (f.isDirectory()) {
			for (File c : f.listFiles())
				delete(c);
		}
		if (!f.delete())

		throw new FileNotFoundException("Failed to delete file: " + f);
	}

	public static List<UserWrapper> createDefaultUser() throws Exception {

		List<UserWrapper> users = new ArrayList<UserWrapper>();

		if (UserDAO.getDAO().countAll() < 1) {

			User u1 = new User();

			u1.name = "Joachim";
			u1.firstname = "Joachim";
			u1.lastname = "Löw";
			u1.email = "jogi@dfb.de";
			u1.street = "Otto-Fleck-Schneise 6";
			u1.city = "Frankfurt";
			u1.postalcode = 60528;
			u1.password = DigestUtils.md5Hex(User.PASSWORDSALT + "12345678");

			UserWrapper uw = UserDAO.getDAO().saveWithGeocoder(
					new UserWrapper(u1));
			users.add(uw);

			User u2 = new User();

			u2.name = "Nitromouse";
			u2.firstname = "Michael";
			u2.lastname = "Ludwig";
			u2.email = "mail.michael.ludwig@gmail.com";
			u2.street = "Primelweg 6";
			u2.city = "Poing";
			u2.postalcode = 85586;
			u2.password = DigestUtils.md5Hex(User.PASSWORDSALT + "12345678");

			UserWrapper uw2 = UserDAO.getDAO().saveWithGeocoder(
					new UserWrapper(u2));
			users.add(uw2);

			User u3 = new User();

			u3.name = "Markus";
			u3.firstname = "Markus";
			u3.lastname = "Ziller";
			u3.email = "mail.markus.ziller@gmail.com";
			u3.city = "Hohenlinden";
			u3.street = "Kanzleiweg 7a";
			u3.postalcode = 85664;
			u3.password = DigestUtils.md5Hex(User.PASSWORDSALT + "12345678");

			UserWrapper uw3 = UserDAO.getDAO().saveWithGeocoder(
					new UserWrapper(u3));
			users.add(uw3);

			User u4 = new User();
			u4.name = "Moe";
			u4.firstname = "Maurice";
			u4.lastname = "Laboureur";
			u4.email = "Moe.La@web.de";
			u4.city = "Muenchen";
			u4.street = "Hans-Leipelt-Str. 8";
			u4.postalcode = 80805;
			u4.password = DigestUtils.md5Hex(User.PASSWORDSALT + "");

			UserWrapper uw4 = UserDAO.getDAO().saveWithGeocoder(
					new UserWrapper(u4));
			users.add(uw4);

		}

		return users;

	}

	public static List<CategoryWrapper> createDefaultCategories()
			throws Exception {

		List<CategoryWrapper> categories = new ArrayList<CategoryWrapper>();

		if (CategoryDAO.getDAO().countAll() < 1) {

			Category c1 = new Category("Heimwerk");
			CategoryWrapper cw = CategoryDAO.getDAO().save(
					new CategoryWrapper(c1));
			categories.add(cw);

			Category c2 = new Category("Party");
			CategoryWrapper cw2 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c2));
			categories.add(cw2);

			Category c3 = new Category("Freizeit");
			CategoryWrapper cw3 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c3));
			categories.add(cw3);
			
			
			Category c4 = new Category("Garten");
			CategoryWrapper cw4 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c4));
			categories.add(cw4);
			
			Category c5 = new Category("Haushalt");
			CategoryWrapper cw5 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c5));
			categories.add(cw5);
			
			Category c6 = new Category("Werkzeug");
			CategoryWrapper cw6 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c6));
			categories.add(cw6);
			
			Category c7 = new Category("Großgerät");
			CategoryWrapper cw7 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c7));
			categories.add(cw7);
			
			Category c8 = new Category("Sport");
			CategoryWrapper cw8 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c8));
			categories.add(cw8);
			
			Category c9 = new Category("Auto");
			CategoryWrapper cw9 = CategoryDAO.getDAO().save(
					new CategoryWrapper(c9));
			categories.add(cw9);
		}

		return categories;
	}

}
