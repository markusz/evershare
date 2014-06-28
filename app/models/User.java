package models;

import java.io.File;
import java.util.Date;

import models.helper.Location;
import models.minimummodels.UserMinimumModel;
import play.Play;

public class User extends _GenericModel<UserMinimumModel> {
	public static final String PASSWORDSALT = "sdfsdklfds797987dasdsalkjjoijn";

	public String name;
	public String password;
	public String email;

	public UserMinimumModel getMinimumModel() {
		return new UserMinimumModel(refererId, name);
	}

	/** Personal details */
	public String firstname;
	public String middlename;
	public String lastname;
	public String title;
	public Date birthday;
	public Integer points;

	/** Adress */
	//public Country countryCode;
	public String country;
	public Integer postalcode;
	public String postalcodeString;
	public String city;
	public String street;
	public String streetnumber;

	/** Geographical data */
	public Double latitude;
	public Double longitude;

	public Location loc = new Location();

	/** */
	public Date registrationDate;

	public String profileimage;

	/** API settings */
	public String facebookId;
	public String facebookAccessToken;
	public String googleId;
	public String googleOAuthToken;
	public String openId;

	/** Activity data */
	public Date lastLogin;
	public Date lastAcitivity;

	/** Settings */
	// public Language language;

	public Double getLatitude() {

		return latitude;
	}

	public User() {

	}

	/**
	 * MongoDB requires the order [long, lat]
	 * 
	 * @author Markus Ziller
	 * @date 30.01.2013
	 * 
	 * @param latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
		loc.y = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public String getName() {
		return name;
	}
	
	
	private boolean fileExists() {
		String imgRootPath = Play.application().configuration().getString("offerRootImgPath");
		String imgPath = Play.application().configuration().getString("userImgPath");
		File file = new File(Play.application().path().toString() + "/" + imgRootPath + "/" + imgPath + refererId);

		return file.exists();
	}

	public String getImgUrl() {

		if (fileExists()) {
			return "img/user/" + refererId + "/" + profileimage;
		}
		return "img/user/default.jpg";

	}

	public String getImgThumbnailUrl() {

		if (fileExists()) {
			return "img/user/" + refererId + "/" + "thumbnail." + profileimage;
		}
		return "img/user/default.jpg";

	}

	/**
	 * MongoDB requires the order [long, lat]
	 * 
	 * @author Markus Ziller
	 * @date 30.01.2013
	 * 
	 * @param latitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
		loc.x = longitude;
	}


	public String getAddress() {
		return street + ", " + postalcode + " " + city;
	}

	@Override
	public String toString() {
		return "User [id=" + refererId + ", name=" + name + "]";
	}

}
