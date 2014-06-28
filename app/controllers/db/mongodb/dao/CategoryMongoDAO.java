package controllers.db.mongodb.dao;

import java.util.List;

import models.wrapper.CategoryWrapper;

import org.bson.types.ObjectId;

import controllers.abstraction.dao.ICategoryDAO;

/**
 * 
 * @author Markus
 *
 */
public class CategoryMongoDAO extends _GenericMongoDAO<CategoryWrapper> implements ICategoryDAO {

	private static CategoryMongoDAO instance = null;
	
	private CategoryMongoDAO() {
	}
	
	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public static CategoryMongoDAO getDAO(){
		if(instance == null){
			instance = new CategoryMongoDAO();
		}
		
		return instance;
	}
	
	
	
	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.ICategoryDAO#getCollectionName()
	 */
	@Override
	public String getCollectionName() {
		return "category";
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.ICategoryDAO#get(org.bson.types.ObjectId)
	 */
	@Override
	public CategoryWrapper get(ObjectId id) throws Exception {
		return get(CategoryWrapper.class, id);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.ICategoryDAO#getAll()
	 */
	@Override
	public List<CategoryWrapper> getAll() throws Exception {
		return getAll(CategoryWrapper.class);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.ICategoryDAO#getAll(java.lang.String)
	 */
	@Override
	public List<CategoryWrapper> getAll(String selectionCriteria) throws Exception {
		return getAll(CategoryWrapper.class, selectionCriteria);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.ICategoryDAO#countAll()
	 */
	@Override
	public Integer countAll() throws Exception {
		return countAll(CategoryWrapper.class);
	}

	@Override
	public CategoryWrapper getCategoryByName(String name) throws Exception {
		StringBuilder sb =  new StringBuilder("{baseEntity.name: '").append(name).append("'}");
		return getCollection().findOne(sb.toString()).as(CategoryWrapper.class);
	}


	
	
	
	
	
	
	
	

}
