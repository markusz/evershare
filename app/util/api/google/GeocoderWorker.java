package util.api.google;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import models.interfaces.IGeocodeable;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

import play.Logger;


/**
 * @author Markus
 * Transforms a string representation of an adress to the lat-long format. necessary for range checks
 */
public class GeocoderWorker extends Thread {
	IGeocodeable geocObject;
	
	public GeocoderWorker(IGeocodeable geo) {
		this.geocObject = geo;
	}

	@Override
	public void run() {
		String adress = geocObject.getAddress();

		try {

			URL url = new URI("http", "maps.google.com", "/maps/api/geocode/json", "address=" + adress + "&sensor=false", null).toURL();
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

			ObjectMapper obj = new ObjectMapper();
			JsonNode node = obj.readTree(reader);
			
			JsonNode locinfo = node.get("results").get(0).get("geometry").get("location");
			
			Double latitude = locinfo.get("lat").asDouble();
			Double longitude = locinfo.get("lng").asDouble();
			
			
			geocObject.setLatitude(latitude);
			geocObject.setLongitude(longitude);

			geocObject.updateGeocode();
		}
		catch (Exception e) {
			Logger.info("error");
		}

	}

}
