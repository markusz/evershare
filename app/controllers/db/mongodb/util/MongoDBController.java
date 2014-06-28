package controllers.db.mongodb.util;

import java.net.UnknownHostException;

public class MongoDBController {
	
	/**
	 * Drops the db
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param dbName Name of the db that shall be dropped
	 * @throws UnknownHostException
	 */
	public static void dropDB(String dbName) throws UnknownHostException{
		MongoConnection.getJongoHandler(dbName).getDatabase().dropDatabase();
	}

}
