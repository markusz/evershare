package controllers.abstraction.dao;

import models.wrapper.CategoryWrapper;

/**
 * An interface to describe Category-related functionalities that are not covered by {@link _IGenericDAO}
 * 
 * @author Markus
 *
 */
public interface ICategoryDAO extends _IGenericDAO<CategoryWrapper>{
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @date 13.02.2013
	 *
	 * @param name
	 * @return
	 * @throws Exception
	 */
	public abstract CategoryWrapper getCategoryByName(String name) throws Exception;


}