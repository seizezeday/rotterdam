package com.rotterdam.tools;

import com.rotterdam.tools.json.JsonCommands;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

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

    public static Date getDateAfterFourWeeks(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, 4);
        return calendar.getTime();
    }

    public static Date getDateNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 1);
        return calendar.getTime();
    }

    public static Date getDatePrevDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -1);
        return calendar.getTime();
    }

    public static Date getDateOfFirstMonday(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.DAY_OF_WEEK_IN_MONTH, 1);
        return cal.getTime();
    }

    public static Date getDateOfLastDay(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));

        return cal.getTime();
    }

    public static boolean isSunday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
    }

    public static boolean isMonday(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    public static Date getDateOfNextSunday(Date date){
        while(!isSunday(date))
            date = getDateNextDay(date);
        return date;
    }

    public static Date getDateOfPrevMonday(Date date){
        while(!isMonday(date))
            date = getDatePrevDay(date);
        return date;
    }

    public static List<Date> getDatesOfWeekWithDate(Date date){
        Date dateToAdd = getDateOfPrevMonday(date);
        List<Date> dates = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            dates.add(dateToAdd);
            dateToAdd = getDateNextDay(dateToAdd);
        }
        return dates;
    }

    public static List<String> formatDates(List<Date> dates){
        DateFormat simpleDateFormat = new SimpleDateFormat(JsonCommands.PARAM_DATE_PATTERN);
        List<String> dateStrings = new ArrayList<>();
        for (Date date : dates) {
            dateStrings.add(simpleDateFormat.format(date));
        }
        return dateStrings;
    }

    public static String getWeekDayTitle(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG, Locale.US);
    }

    public static Date getDateOf7DayAfter(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 6);
        return calendar.getTime();
    }

    public static double getDoubleFormatHours(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        double hours = calendar.get(Calendar.HOUR_OF_DAY);
        double minutes = calendar.get(Calendar.MINUTE);

        hours += minutes / 60;

        return hours;
    }

    public static Date convertFromLocalDate(LocalDate ld){
        Instant instant = ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
