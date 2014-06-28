package models.minimummodels;

import models.interfaces.IMinimumModel;

public abstract class _GenericMinimumModel implements IMinimumModel {
	
	public String label;
	public String wrapperId;

	@Override
	public String getMinimumModelLabel() {
		return label;
	}

	@Override
	public String getWrapperId() {
		return wrapperId;
	}
	

	public void setMinimumModelLabel(String label) {
		this.label = label;
	}

	public void setMinimumModelWrapperId(String id) {
		this.wrapperId = id;
	}

}
