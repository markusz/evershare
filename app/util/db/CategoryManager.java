package util.db;

import java.util.List;

import controllers.db.CategoryDAO;
import models.wrapper.CategoryWrapper;

/**
 * Primary access point for querying categories from the db. Packs the methods of {@link CategoryDAO.getDAO()} into userfriendly methods
 * @author Markus
 *
 */
public class CategoryManager {
	
	public static CategoryWrapper getCategoryByName(String categoryName) throws Exception{
		return CategoryDAO.getDAO().getCategoryByName(categoryName);
	}
	
	public static CategoryWrapper getCategoryById(String categoryId) throws Exception{
		return CategoryDAO.getDAO().get(categoryId);
	}
	
	public static List<CategoryWrapper> getAll() throws Exception{
		return CategoryDAO.getDAO().getAll();
	}

}
