package util.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import models.helper.EasyDate;
import models.wrapper.BookingWrapper;

public class DateUtil {

	static DateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy",
			Locale.GERMAN);
	static DateFormat sqlformat = new SimpleDateFormat("yyyy-MM-dd",
			Locale.ENGLISH);

	public static Date parseString(String date) throws ParseException {
		return dateformat.parse(date);
	}

	public static String parseDate(Date date) throws ParseException {
		return dateformat.format(date);
	}

	// TODO call hierarchy anschauen und ggf. anpassen
	public static String formatForSQL(Date date) throws ParseException {
		return (sqlformat.format(date) + " 00:00:00");
	}

	public static List<EasyDate> getBookedDays(List<BookingWrapper> bookings)
			throws Exception {

		List<EasyDate> bookedDays = new ArrayList<EasyDate>();

		Calendar cal = Calendar.getInstance();

		for (BookingWrapper bwr : bookings) {

			cal.setTime(bwr.getBaseModel().bookingStart);

			int day = cal.get(Calendar.DAY_OF_MONTH);
			int month = cal.get(Calendar.MONTH);
			int year = cal.get(Calendar.YEAR);

			bookedDays.add(new EasyDate(day, month, year));

			for (int i = 1; i < bwr.getBaseModel().duration; i++) {

				cal.add(Calendar.DATE, 1);

				day = cal.get(Calendar.DAY_OF_MONTH);
				month = cal.get(Calendar.MONTH);
				year = cal.get(Calendar.YEAR);

				bookedDays.add(new EasyDate(day, month, year));
			}
		}
		return bookedDays;
	}

}
