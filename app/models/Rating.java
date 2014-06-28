package models;

import java.util.Date;

import models.minimummodels.RatingMinimumModel;

/**
 * Currently unused (13.2). Intended to provide rating functionality
 * @author Markus
 * 
 */
public class Rating extends _GenericModel<RatingMinimumModel> {

	private Short stars;
	private Date timestamp;
	private String comment;
	private Boolean hasBeenEdited;
	private Date editDate;

	@Override
	public RatingMinimumModel getMinimumModel() {
		// TODO implementieren
		return new RatingMinimumModel();
	}
}
