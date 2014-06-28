package models;

import models.minimummodels.ItemMinimumModel;



/**
 * Currently (13.2) unused. Intended to enable user to create different offers for one item
 * TODO
 * @author Markus
 *
 */
public class Item extends _GenericModel<ItemMinimumModel>{	

	
	@Override
	public ItemMinimumModel getMinimumModel() {
		return null;
	}
	
	public Item(){
		
	}

}
