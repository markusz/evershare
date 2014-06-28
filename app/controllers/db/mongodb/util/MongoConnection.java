package controllers.db.mongodb.util;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.Mapper;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;

import util.constants.Constants;

import com.mongodb.Mongo;

public class MongoConnection {

	private static Mongo conn = null;

	/**
	 * Gets a connection to the mongodb. Currently only one connection is
	 * provided. This is just a temporary solution to overcome flaws in the
	 * connection pooling mechanism of the jongo-framework which causes problems
	 * on certain systems
	 * 
	 * @author Markus Z.
	 * @date 09.12.2012
	 * @return A connection of the internally managed connection-pool
	 * @throws UnknownHostException
	 */
	public static Mongo getConnection() throws UnknownHostException {

		if (conn == null) {
			conn = new Mongo(Constants.DB_HOST_ADRESS, Constants.DB_HOST_PORT);
		}
		return conn;
	}

	/**
	 * 
	 * @param dbName
	 *            the name of the database
	 * @return A Object of Class "Jongo" which serves as Interface between the
	 *         MongoDB and the Jongo Framework
	 * @throws UnknownHostException
	 */

	/**
	 * 
	 * @author Markus Z.
	 * @date 09.12.2012
	 * @param dbName
	 *            the name of the database
	 * @return A Object of Class "Jongo" which serves as Interface between the
	 *         MongoDB and the Jongo Framework
	 * @throws UnknownHostException
	 */
	public static Jongo getJongoHandler(String dbName)
			throws UnknownHostException {

		Mapper defaultMapper = new JacksonMapper.Builder().build();

		Jongo j = new Jongo(getConnection().getDB(dbName), defaultMapper);

		return j;
	}

	/**
	 * retrieves a collection from a running db
	 * 
	 * @author Markus Ziller
	 * @date 13.02.2013
	 * 
	 * @param dbName
	 * @param collection
	 * @return
	 * @throws UnknownHostException
	 */
	public static MongoCollection getCollection(String dbName, String collection)
			throws UnknownHostException {
		return getJongoHandler(dbName).getCollection(collection);
	}

}
