package controllers.db.mongodb.dao;

import java.util.List;

import models.wrapper.ItemWrapper;

import org.bson.types.ObjectId;

import controllers.abstraction.dao.IItemDAO;

/**
 * 
 * @author Markus
 * 
 */
public class ItemMongoDAO extends _GenericMongoDAO<ItemWrapper> implements IItemDAO {

	private static ItemMongoDAO instance = null;

	private ItemMongoDAO() {
	}

	/**
	 * Singleton
	 * 
	 * @author Markus
	 * @date 12.01.2013
	 * @return
	 */
	public static ItemMongoDAO getDAO() {
		if (instance == null) {
			instance = new ItemMongoDAO();
		}

		return instance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.mongodb.dao.IItemDAO#getCollectionName()
	 */
	@Override
	public String getCollectionName() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.mongodb.dao.IItemDAO#get(org.bson.types.ObjectId)
	 */
	@Override
	public ItemWrapper get(ObjectId id) throws Exception {
		return get(ItemWrapper.class, id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.mongodb.dao.IItemDAO#getAll()
	 */
	@Override
	public List<ItemWrapper> getAll() throws Exception {
		return getAll(ItemWrapper.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.mongodb.dao.IItemDAO#getAll(java.lang.String)
	 */
	@Override
	public List<ItemWrapper> getAll(String selectionCriteria) throws Exception {
		return getAll(ItemWrapper.class, selectionCriteria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see controllers.mongodb.dao.IItemDAO#countAll()
	 */
	@Override
	public Integer countAll() throws Exception {
		return countAll(ItemWrapper.class);
	}

}
