package org.gem.event.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

	public static String printDate(Calendar date){
		if(date==null) return ""; 
		DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy ");
		return dateFormat.format(date.getTime());
	}
}
