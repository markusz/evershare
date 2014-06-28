package models.minimummodels;

import java.util.Date;

public class BookingMinimumModel extends _GenericMinimumModel {
	
	public BookingMinimumModel(String id, Date start, Integer duration) {
		this.wrapperId = id;
		this.start = start;
		this.duration = duration;
	}
	
	/**
	 * 
	 *
	 * @author Markus Ziller
	 * @return 
	 * @date 10.02.2013
	 *
	 */
	public void update(String userId, String offerId, String userName, String offerName){
		this.userId = userId;
		this.offerId = offerId;
		this.userName = userName;
		this.offerName = offerName;
	}

	public Date start;
	public Integer duration;
	public String userId;
	public String offerId;
	public String userName;
	public String offerName;
	
	public BookingMinimumModel(){
		
	}
	
	

}
