package models.forms;

public class AvailabilityCalendar {

	public int year;
	public int month;

	public String validate() {
		if (month < 0 || month > 11) {
			return "Month out of range";
		} else if (year < 2010) {
			return "Invalid year";
		}

		return null;
	}
}