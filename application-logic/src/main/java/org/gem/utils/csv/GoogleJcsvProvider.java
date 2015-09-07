package org.gem.utils.csv;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.gem.utils.PropertyHelper;

import com.googlecode.jcsv.annotations.internal.ValueProcessorProvider;

public class GoogleJcsvProvider {
	public static final String CSV_DATA_FILE = "%UserProfile%\\Documents\\Expenses2013.csv";
	
	
	public static String getClownFishCsvDataPath() throws IOException, Exception {
		PropertyHelper pHelper = new PropertyHelper("clownfish.properties");
		String path = pHelper.getProperty("csvInputFile");
		String updatedpath = path.replace('\\', File.separatorChar);
		return updatedpath;
	}

	public static ValueProcessorProvider getCommonCsvStringValueProcessorProvider() {
		
		ValueProcessorProvider provider = new ValueProcessorProvider();
		provider.removeValueProcessor(Date.class);
		provider.registerValueProcessor(Date.class, new DateProcessor());
		//TODO: determine if the date is being parsed and captured correctly
		provider.removeValueProcessor(Double.class);
		provider.registerValueProcessor(Double.class, new DoubleProcessor());
		return provider;
	}
	
	
}
