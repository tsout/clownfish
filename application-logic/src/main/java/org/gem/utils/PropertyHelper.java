package org.gem.utils;

import java.io.BufferedInputStream;
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
						BufferedInputStream bis = new BufferedInputStream(inputStream);
						bis.mark(255);
						if(bis.read()==-1)
						{
							throw new IOException("Property File:'"+propFileName+"' is empty");
						}
						else
							bis.reset();
							
						propertyFile.load(bis);

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
