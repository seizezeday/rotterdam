package com.rotterdam.tools;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * @author Roman
 *
 */
public class DateTools {

	/**
	 * @param date
	 *            - for converting to String not null
	 * @param format
	 *            - format like 'yyyy-MM-dd'
	 * @return String value of Date
	 */
	public static String convertDateToString(Date date, String format) {
		DateTimeFormatter formatter = DateTimeFormat.forPattern(format);
		DateTime jodatime = new DateTime(date);
		return jodatime.toString(formatter);
	}

	/**
	 * @param week - week of month (1..4)
	 * @param month - month of year (1..12)
	 * @param year - year (example 2014)
	 * @return List of date from start week
	 */
	public static List<Date> getDateForWeekMonthYear(int week, int month,
			int year) {
		List<Date> result = new ArrayList();
		DateTime dt = new DateTime(year, month, 1, 0, 0);
		dt = dt.withWeekOfWeekyear(dt.getWeekOfWeekyear() + week - 2).withDayOfWeek(1);
		result.add(dt.toDate());
		System.out.println("convert : "
				+ convertDateToString(dt.toDate(), "dd-MM-yyyy"));
		for (int i = 1; i <= 6; i++) {
			result.add(dt.plusDays(i).toDate());
			System.out
					.println("convert : "
							+ convertDateToString(dt.plusDays(i).toDate(),
									"dd-MM-yyyy"));
		}
		return result;
	}

    /**
     * @return List of date from start week
     */
    public static List<Date> getDateForWeekMonthYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;        // months in class Calendar beginning from 0 to 11
        int currentYear = calendar.get(Calendar.YEAR);
        return getDateForWeekMonthYear(currentWeek, currentMonth, currentYear);

    }

    /**
     * @return int value of week number
     */
    public static int getCurrentWeekNumber(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH);


    }

}
