package util.constants;

import controllers.abstraction.PersistenceStrategy;
import play.Play;

public class Constants {
	
	public static final Integer OFFER_THUMBNAIL_MAX_HEIGHT = Play.application().configuration().getInt("offerThumbnailMaxHeight");
	public static final Integer OFFER_THUMBNAIL_MAX_WIDTH = Play.application().configuration().getInt("offerThumbnailMaxWidth");
	public static final Integer USER_THUMBNAIL_MAX_HEIGHT = Play.application().configuration().getInt("userThumbnailMaxHeight");
	public static final Integer USER_THUMBNAIL_MAX_WIDTH = Play.application().configuration().getInt("userThumbnailMaxWidth");
	
	public static final String DB_NAME = Play.application().configuration().getString("database.name");
	public static final String DB_HOST_ADRESS = Play.application().configuration().getString("database.host.adress");
	public static final Integer DB_HOST_PORT = Play.application().configuration().getInt("database.host.port");
	
	/**
	 * One Degree equals 111.12 km
	 */
	public static final Double GEOSPATIAL_DEGREE_TO_KM_CONVERSION = 111.12;
	
	public static PersistenceStrategy ps = PersistenceStrategy.SQL;

}
