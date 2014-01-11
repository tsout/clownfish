package org.gem.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

public class PropertyHelper {

	Properties propertyFile = null;
	private String propertyFilePath = null;
	private Reader fileReader = null;

	public PropertyHelper(String propFileName) throws IOException {
		if (propFileName != null && !propFileName.isEmpty()) {
			try {
						String path = propFileName.trim();
						propertyFile = new Properties();
						InputStream inputStream = getClass().getClassLoader().getResourceAsStream(path);
						inputStream.mark(255);
						if(inputStream.read()==-1)
						{
							throw new IOException("Property File:'"+propFileName+"' is empty");
						}
						else
							inputStream.reset();
							
						propertyFile.load(inputStream);

			} catch (FileNotFoundException e) {
				System.err.println(e.getMessage());
			} 

		}

	}

	public String getProperty(String propName) throws Exception {
		String propertyName = propName.trim();
		if (!propertyName.isEmpty() && propertyName != null) {
			return propertyFile.getProperty(propertyName);
		} else
			throw new Exception("Property: '" + propertyName + "' Not found");
	}

}
