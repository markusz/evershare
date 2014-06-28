package controllers.db.dummydb.dao;

import models.wrapper.CategoryWrapper;
import controllers.abstraction.dao.ICategoryDAO;

/**
 * 
 * @author Markus
 *
 */
public class CategoryDummyDAO extends _GenericDummyDAO<CategoryWrapper> implements ICategoryDAO {

	@Override
	public CategoryWrapper getCategoryByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
