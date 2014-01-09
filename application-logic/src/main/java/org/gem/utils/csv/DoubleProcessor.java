package org.gem.utils.csv;

import com.googlecode.jcsv.annotations.ValueProcessor;

public class DoubleProcessor implements ValueProcessor<Double> {

	public Double processValue(String value) {
		if (value.isEmpty()) return 0.0; 
		return Double.parseDouble(value);
	}

}

