package controllers.abstraction;

import util.db.PersistenceManager;

/**
 * Enumeration of different persistence strategies. Used in {@link PersistenceManager}
 * @author Markus
 *
 */
public enum PersistenceStrategy {

	SQL, JAVA, MONGODB
}
