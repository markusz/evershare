package models.interfaces;


/**
 * Due to the MongoDB optimized DB Scheme different requirements lead to a
 * different approach. For performance reasons it is often necessary for to
 * provide a minimum set of values of class A inside class B (i.e. name and id
 * of a user when displaying an offer) to avoid the necessity of querying class
 * B.
 * 
 * This interface ensures that every entity can serve as a MinimumModel inside another class
 * 
 * @author Markus
 * 
 */
public interface IMinimumModel {

	public String getWrapperId();
	public String getMinimumModelLabel();

}
