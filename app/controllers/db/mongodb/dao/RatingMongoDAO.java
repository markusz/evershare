package controllers.db.mongodb.dao;

import java.util.List;

import models.wrapper.RatingWrapper;

import org.bson.types.ObjectId;

import controllers.abstraction.dao.IRatingDAO;

/**
 * 
 * @author Markus
 *
 */
public class RatingMongoDAO extends _GenericMongoDAO<RatingWrapper> implements IRatingDAO{
	
	
private static RatingMongoDAO instance = null;
	
	private RatingMongoDAO() {
	}
	
	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public static RatingMongoDAO getDAO(){
		if(instance == null){
			instance = new RatingMongoDAO();
		}
		
		return instance;
	}
	
	
	public static IRatingDAO getHandler(){
		return new RatingMongoDAO();
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IRatingDAO#getCollectionName()
	 */
	@Override
	public String getCollectionName() {
		return "rating";
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IRatingDAO#get(org.bson.types.ObjectId)
	 */
	@Override
	public RatingWrapper get(ObjectId id) throws Exception {
		return get(RatingWrapper.class, id);
	}

	@Override
	public RatingWrapper get(String id) throws Exception {
		return get(new ObjectId(id));
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IRatingDAO#getAll()
	 */
	@Override
	public List<RatingWrapper> getAll() throws Exception {
		return getAll(RatingWrapper.class);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IRatingDAO#getAll(java.lang.String)
	 */
	@Override
	public List<RatingWrapper> getAll(String selectionCriteria) throws Exception {
		return getAll(RatingWrapper.class, selectionCriteria);
	}

	/* (non-Javadoc)
	 * @see controllers.mongodb.dao.IRatingDAO#countAll()
	 */
	@Override
	public Integer countAll() throws Exception {
		return countAll(RatingWrapper.class);
	}


}
