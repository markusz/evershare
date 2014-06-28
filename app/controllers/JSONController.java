package controllers;

import java.util.List;

import models.Offer;
import models.User;
import models.android.OfferAndroid;
import models.android.UserAndroid;
import models.helper.OfferListWrapper;
import models.wrapper.OfferWrapper;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import util.db.OfferManager;
import util.db.UserManager;

import com.google.common.collect.Lists;


public class JSONController extends Controller{

	//Returns JSON for Offers
	public static Result getAllOffers() {
		OfferListWrapper wrapper = new OfferListWrapper();
		
		//wrapper.offers = Offer.findAll();
		
		
		try {
			//TODO 
			List<OfferAndroid> offers = Lists.newArrayList();
			for(OfferWrapper ow : OfferManager.getAllOffers())
			{
				OfferAndroid oa = new OfferAndroid();
				Offer o = ow.getBaseModel();
				oa.avail_from = o.avail_from;
				oa.avail_to = o.avail_to;
				oa.available = o.available;
				oa.availableDays = o.availableDays;
				oa.categoryId = o.categoryId;
				oa.city = o.city;
				oa.countryId = o.countryId;
				oa.description = o.description;
				oa.id = o.refererId;
				oa.imgUrl = o.imgUrl;
				oa.latitude = o.loc.y;
				oa.longitude = o.loc.x;
				oa.name = o.name;
				oa.postalCode = o.postalCode;
				oa.priceph = o.priceph;
				oa.pricepu = o.pricepu;
				oa.street = o.street;
				oa.titleimage = o.titleimage;
				
				
				User u = UserManager.getUser(ow.getUserMinimumModel().wrapperId).getBaseModel();
				UserAndroid ua = new UserAndroid();
				ua.name = u.name;
				ua.id = u.refererId;
				ua.profilePicture = u.profileimage;
				
				oa.owner= ua;
				
				offers.add(oa);
				
			}
			
			wrapper.offers = offers;
			
			return ok(Json.toJson(wrapper));
		} catch (Exception e) {
			return badRequest();
		}
	}
	
	//Returns JSON for Offers
		public static Result getOffer(String offerID) {
			try {
				models.wrapper.OfferWrapper ow = OfferManager.getOffer(offerID);
				return ok(Json.toJson(ow));
			} catch (Exception e) {
				return badRequest();
			}
		}

}
