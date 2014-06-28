package models.abstractionlayer;

import org.bson.types.ObjectId;

/**
 * Assures that every Wrapper has an ObjectId as well as related getters and setters
 * @author Markus
 *
 */
public class Mongoable{
	
	protected ObjectId _id;
	
	public ObjectId getObjectId() {
		return _id;
	}

	public void setObjectId(ObjectId _id) {
		this._id = _id;
	}

}
