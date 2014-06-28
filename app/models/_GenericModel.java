package models;

/**
 * Generics for a Model/BaseEntity
 * @author Markus
 *
 * @param <T>
 */
public abstract class _GenericModel<T> {

	public String label;
	public String refererId;

	public abstract T getMinimumModel();

	public String getObjectId() {
		return refererId;
	}

	public void setObjectId(String id) {
		this.refererId = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
}
