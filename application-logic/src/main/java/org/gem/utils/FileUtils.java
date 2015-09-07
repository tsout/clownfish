package org.gem.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class FileUtils {

	private static Logger logger = Logger.getLogger(FileUtils.class.getSimpleName());
	/**
	 * Give a folder path, file name and extension, this method safely creates a
	 * file. If the path or file do not already exist, the method will create
	 * them.
	 * 
	 * @param filePath
	 * @param fileName
	 * @param extension
	 * @return File newFile
	 */
	public static File createANewFile(String filePath, String fileName,
			String extension) {
		// ensure that input values are non null
		if (!StringUtils.isEmpty(filePath) || !StringUtils.isEmpty(fileName)
				|| !StringUtils.isEmpty(extension)) {
	
			// remove the first period from an extension
			if (extension.matches("^\\..*")) {
				extension.replaceFirst("\\.", "");
			}
			// create the file
			File tmp = new File(filePath + File.separator + fileName + "."
					+ extension);
			try {
				// create a new file if path does not already exist
				if (!tmp.exists()) {
					tmp.getParentFile().mkdirs();
					tmp.createNewFile();
				}
				// test access to the file
				if (!(tmp.isFile() && tmp.canWrite())) {
					logger.severe("Can not write to file path:"
							+ System.lineSeparator() + tmp.getAbsolutePath());
					return null;
				}
			} catch (IOException e) {
				e.getMessage();
				return null;
			}
			return tmp;
		} else {
			throw new InvalidParameterException(
					"file path, name, or extension can not be empty");
		}
	}
	public static void printFileContents(String path, String fileName, String extension)
			throws FileNotFoundException, IOException {
		String output = FileUtils.readFile(path, fileName, extension);
		System.out.println(output);
	}
	public static String readFile(String path, String fileName,
			String extension) throws FileNotFoundException, IOException {
		File f = new File(path + File.separator + fileName + "." + extension);
		StringBuffer fileBuffer = new StringBuffer(); 
	
		BufferedReader br= new BufferedReader( new FileReader(f));
		String line="";
		while ((line =br.readLine())!=null) {
			fileBuffer.append(line);
		}
		br.close();
		return fileBuffer.toString();
	}
	

}
