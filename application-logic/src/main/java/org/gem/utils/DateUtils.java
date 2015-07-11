package org.gem.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static String printDate(Calendar date) {
		if (date == null)
			return "";
		return printDate(date);
	}
	
	public static String printDate(Date date){
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
		return dateFormat.format(date);
	}

	public static Date buildDate(int month, int date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date);
		return new Date(cal.getTimeInMillis());
	}

	public static Boolean isBetween(Date subjectDate, Date windowStartDate,
			Date windowEndDate) {
		return (subjectDate.before(windowEndDate) && subjectDate
				.after(windowStartDate));
	}

	public static Boolean isBetween(Calendar subjectDate,
			Calendar windowStartDate, Calendar windowEndDate) {
//		String msg = printDateComparison(subjectDate, windowStartDate, windowEndDate);
//		System.out.println(msg);
		return isBetween(subjectDate.getTime(), windowStartDate.getTime(),
				windowEndDate.getTime());
	}

	public static String printDateComparison(Calendar subjectDate,
			Calendar windowStartDate, Calendar windowEndDate) {
		return printDateComparison(subjectDate.getTime(), windowStartDate.getTime(), windowEndDate.getTime());
	}
	
	public static String printDateComparison (Date subjectDate, Date windowStartDate, Date windowEndDate)
	{
		return DateUtils.printDate(windowStartDate) + " < "
				+ DateUtils.printDate(subjectDate) + " < "
				+ DateUtils.printDate(windowEndDate);
		
	}
}
