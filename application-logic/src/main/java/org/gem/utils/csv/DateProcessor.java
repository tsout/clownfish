package org.gem.utils.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.googlecode.jcsv.annotations.ValueProcessor;

public class DateProcessor implements ValueProcessor<Date> {

	public Date processValue(String value) {
		Date date =null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		try {
			 date = formatter.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
