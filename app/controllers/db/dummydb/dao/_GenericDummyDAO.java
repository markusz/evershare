package controllers.db.dummydb.dao;

import java.util.List;

import controllers.abstraction.dao._IGenericDAO;

public abstract class _GenericDummyDAO<T> implements _IGenericDAO<T> {

	@Override
	public T save(T t) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T get(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(T t) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Integer countAll() throws Exception {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	public List<T> getAll() throws Exception{
		return null;
	}
	
	public T upsert(String id, String key, String value) throws Exception {
		
		//TODO
		return get(id);
	}

}
