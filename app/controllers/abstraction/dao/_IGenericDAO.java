package controllers.abstraction.dao;

import java.util.List;

/**
 * A generic interface to describe basic CRUD operations that every DAO, regardless of entity and persistence strategy, has to offer
 * 
 * @author Markus
 *
 * @param <T> Generic
 */
public interface _IGenericDAO<T> {
	
	
	abstract T save(T t) throws Exception;
	abstract T get(String id) throws Exception;
	abstract List<T> getAll() throws Exception;
	abstract void delete(T t) throws Exception;
	abstract Integer countAll() throws Exception;
	public abstract T upsert(String id, String key, String value) throws Exception;
	

}
