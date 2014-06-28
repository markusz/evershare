package models.helper;

import java.text.ParseException;
import java.util.Date;

import models.forms.OfferSearch;
import util.data.DateUtil;

/**
 * Contains a set of criteria describing a users search
 * @author Markus
 *
 */
public class SearchCriteriaSet {
	
	private String label;
	private Location loc;

	private Double kmRange;
	private Date time;
	private String ownerId;
	
	private String categoryId;
	
	public SearchCriteriaSet(OfferSearch os) {
		if (os.range == null || os.range <= 0) {
			os.range = 3000.;
		}

		this.label = os.term;
		this.loc = new Location(os.lng, os.lat);
		this.kmRange = os.range;
		try {
			this.time = DateUtil.parseString(os.date);
		} catch (ParseException e) {
			// fail silently
		}
	}
	
	public Location getLoc() {
		return loc;
	}
	public void setLoc(Location loc) {
		this.loc = loc;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	
	public Double getRange() {
		return kmRange;
	}
	public void setRange(Double range) {
		this.kmRange = range;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	
	public String toQueryString(){
		
		Boolean elementInsterted = false;
		StringBuilder sb = new StringBuilder("{");
		
		if(label != null){
			
			if(elementInsterted){
				sb.append(",");
			}
			
			sb.append("'baseEntity.name' : '").append(label).append("'");
			elementInsterted = true;
			
		}
		if(loc.x != null && loc.y != null){
			
			if(elementInsterted){
				sb.append(",");
			}
			
			if(kmRange == null){
				kmRange = 10.;
			}
			Double degreeRange = kmRange / 6371;
			sb.append("'baseEntity.loc' : {'$within' : {'$centerSphere' : [[").append(loc.y).append(",").append(loc.x).append("], ").append(degreeRange).append("]}}");
			
			elementInsterted = true;
		}
		
		
		if(categoryId != null){
			
			if(elementInsterted){
				sb.append(",");
			}
			
			sb.append("'baseEntity.name' : '").append(label).append("'");
			elementInsterted = true;
			
		}
		
		sb.append("}");
		return sb.toString();
		
	}

}
