package controllers.db.mongodb.dao;

import java.net.UnknownHostException;
import java.util.List;

import models.abstractionlayer.Mongoable;
import models.helper.Location;
import models.interfaces.IGeocodeable;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;

import util.constants.Constants;

import com.google.common.collect.Lists;

import controllers.abstraction.dao._IGenericDAO;
import controllers.db.mongodb.util.MongoConnection;

/**
 * 
 * A generic implementation of a DAO to be used with MongoDB. Provides distinct
 * generic functionalities (CRUD + x) that are required for all entities alike
 * 
 * @author Markus
 * 
 * @param <T>
 *            A subclass of {@link Mongoable}
 */
public abstract class _GenericMongoDAO<T extends Mongoable> implements _IGenericDAO<T> {

	protected String COLLECTION_NAME;

	public abstract Integer countAll() throws Exception;

	public abstract String getCollectionName();

	public abstract T get(ObjectId id) throws Exception;

	public abstract List<T> getAll() throws Exception;

	public abstract List<T> getAll(String selectionCriteria) throws Exception;

	protected MongoCollection getCollection() throws UnknownHostException {
		return MongoConnection.getCollection(Constants.DB_NAME,
				getCollectionName());
	}

	public T get(String id) throws Exception {
		return get(new ObjectId(id));
	}

	public T get(Class<T> c, ObjectId id) throws Exception {
		MongoCollection conn = getCollection();
		T t = conn.findOne(id).as(c);

		return t;
	}

	protected final List<T> getAll(Class<T> c) throws Exception {
		MongoCollection coll = getCollection();

		Iterable<T> i = coll.find().as(c);
		return Lists.newArrayList(i);

	}

	protected final List<T> getAll(Class<T> c, String selectionCriteria)
			throws Exception {
		MongoCollection coll = getCollection();

		Iterable<T> i = coll.find(selectionCriteria).as(c);
		return Lists.newArrayList(i);
	}

	public final T save(T t) throws Exception {

		MongoCollection mc = getCollection();
		mc.save(t);

		mc.update(t.getObjectId()).with("{$set: {baseEntity.refererId: #}}", t.getObjectId().toString());
		// TODO Writeresult benutzung anschauen

		return t;

	}

	public final void delete(T t) throws Exception {

		MongoCollection mc = getCollection();
		mc.remove(t.getObjectId());

	}
	
	public final void delete(String id) throws Exception {

		MongoCollection mc = getCollection();
		mc.remove(new ObjectId(id));

	}

	public final Integer countAll(Class<T> c) throws Exception {
		return getAll(c).size();
	}
	
	private T upsertDouble(String id, String key, Double value) throws Exception {
		MongoCollection mc = getCollection();
		
		StringBuilder sb = new StringBuilder("{$set: {").append(key).append(": #}}");
		
		mc.update(new ObjectId(id)).upsert().with(sb.toString(), value);
		
		return get(id);
	}
	
	private T upsertDouble(String id, List<String> keys, List<Double> values) throws Exception {
		
		if(keys.size() != values.size()){
			throw new Exception("different number of keys and values");
		}
		
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Double value = values.get(i);
			
			upsertDouble(id, key, value);
		}
		
		return get(id);
	}

	
	
private T upsertLocationDouble(String id, List<String> keys, List<Double> values) throws Exception {
		
		if(keys.size() != values.size()){
			throw new Exception("different number of keys and values");
		}
		
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Double value = values.get(i);
			
			upsertDouble(id, key, value);
		}
		
		return get(id);
	}
	

	public T upsert(String id, String key, String value) throws Exception {
		MongoCollection mc = getCollection();
		
		StringBuilder sb = new StringBuilder("{$set: {").append(key).append(": #}}");
		
		mc.update(new ObjectId(id)).upsert().with(sb.toString(), value);
		
		return get(id);
	}
	
	public T upsert(String id, String key, Integer value) throws Exception {
		MongoCollection mc = getCollection();
		
		StringBuilder sb = new StringBuilder("{$set: {").append(key).append(": #}}");
		
		mc.update(new ObjectId(id)).upsert().with(sb.toString(), value);
		
		return get(id);
	}
	
	public T upsert(String id, String key, Boolean value) throws Exception {
		MongoCollection mc = getCollection();
		
		StringBuilder sb = new StringBuilder("{$set: {").append(key).append(": #}}");
		
		mc.update(new ObjectId(id)).upsert().with(sb.toString(), value);
		
		return get(id);
	}
	
	private T upsert(String id, String key, Location value) throws Exception {
		MongoCollection mc = getCollection();
		
		StringBuilder sb = new StringBuilder("{$set: {").append(key).append(": #}}");
		
		mc.update(new ObjectId(id)).upsert().with(sb.toString(), value);
		
		return get(id);
	}
	
	public T upsert(String id, List<String> keys, List<String> values) throws Exception {
		
		if(keys.size() != values.size()){
			throw new Exception("different number of keys and values");
		}
		
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = values.get(i);
			
			upsert(id, key, value);
		}
		
		return get(id);
	}
	
	
	public void updateGeocodeInformation(IGeocodeable object) throws Exception {
		upsert(object.getObjectId().toString(), "baseEntity.loc",  new Location(object.getLongitude(), object.getLatitude()));
	}
	
	public void pushToArray(T t, String key, Object o, Class c) throws Exception {
		
		pushToArray(t.getObjectId(), key, o, c);
		
	}
	
	/**
	 * Generic pushing method to alter existing arrays. Gets an DB entry, which is identified  by its ObjectId and updates the a field identified by "key". Object o and class c are required to ensure type safety
	 *
	 * @author Markus Ziller
	 * @date 10.02.2013
	 *
	 * @param id the UId of the entitiy
	 * @param key identifier of the JSON field. Multiple hierarchies are separated by ".", i.e "baseEntity.loc"
	 * @param o the object to append
	 * @param c o's class
	 * @throws Exception
	 */
	public void pushToArray(ObjectId id, String key, Object o, Class c) throws Exception {
		//{$push: {'sentMessages': 'Neue Nachricht'}}
		StringBuilder sb = new StringBuilder("{$push: {'").append(key).append("': #}}");
		getCollection().update(id).with(sb.toString(), (c.cast(o)));
	}
	
	/**
	 * TODO
	 * NOT working yet!
	 *
	 * @author Markus Ziller
	 * @date 10.02.2013
	 *
	 * @param id
	 * @param key
	 * @param l
	 * @param c
	 * @throws Exception
	 */
	public void pushListToArray(ObjectId id, String key, List l, Class c) throws Exception {
		//{$push: {'sentMessages': 'Neue Nachricht'}}
		StringBuilder sb = new StringBuilder("{$push: {'").append(key).append("': #}}");
		getCollection().update(id).with(sb.toString(), (c.cast(l)));
	}
	
	

}
