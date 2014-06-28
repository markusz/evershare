package models.abstractionlayer;


/**
 * Ensures that each Wrapper contains a base entity
 * @author Markus
 *
 * @param <T> type of the base entity
 */
public class GenericWrapper<T> extends Mongoable{
	
	private T baseEntity;

	public T getBaseModel() {
		return baseEntity;
	}

	public void setBaseModel(T entity) {
		this.baseEntity = entity;
	}


	

}
