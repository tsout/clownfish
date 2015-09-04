package org.gem.utils.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.logging.Logger;



import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

public class FasterXmlCSVProvider implements ICsvUtil {
	
	
	private File file = null;
	
	private Logger logger = Logger.getLogger(FasterXmlCSVProvider.class
			.getSimpleName());

	/**
	 * creates a single line of csv ouptut string from pojos that implement the CSVSerializable interface
	 * @param serializable
	 * @param clazz
	 * @param useHeader
	 * @return
	 */
	public <T> String write(CSVSerializable serializable, Class<T> clazz, boolean useHeader) {
//		logger.info("writing:\n"+serializable);
		CsvMapper csvMapper = new CsvMapper();
		String csvString="";
		
		CsvSchema schema = csvMapper.schemaFor(clazz).withColumnSeparator('|');		
		if(useHeader){
			schema =schema.withHeader();			
		}
		
		try {
			csvString= csvMapper.writer(schema).writeValueAsString(serializable);			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		logger.info("wrote:\t" + csvString);
		return csvString;
	}
	
	/**
	 * creates multiple lines of CSV output from a list of objects that extends the CSVSerializable interface
	 * @param list
	 * @param clazz
	 * @return
	 */
	public <T> String write (List<? extends CSVSerializable> list, Class<T> clazz){
		int x=0;
		StringBuffer sb = new StringBuffer(); 
		while(x<list.size()-1){
			boolean useHeader = x==0?true:false;
			sb.append(this.write(list.get(x),clazz, useHeader));
			x++;
		}
		return sb.toString(); 
	}
	
	public <T> Boolean writeToFile(List<? extends CSVSerializable> list, Class<T> clazz){
		if(!this.file.exists()){
			return false;
		}
		String output =write(list, clazz);
		FileWriter fw = null;
		BufferedWriter bw = null; 
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			bw.write(output);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();			
		}
		return true;
	}

	/**
	 * builder method that sets the csv file that will be written to
	 * @param filePath
	 * @param fileName
	 * @param extension
	 * @return
	 */
	//TODO: validate fileName, filePath, and extension
	public FasterXmlCSVProvider setTargetCSVFile(String filePath, String fileName, String extension){
	
		
		if(!StringUtils.isEmpty(filePath)||!StringUtils.isEmpty(fileName)||!StringUtils.isEmpty(extension)){
			
			File tmp =  new File(filePath+File.separator+fileName+"."+extension);			
			try {
				if(!tmp.exists()){
					tmp.getParentFile().mkdirs();
					tmp.createNewFile();
				}
					
				if (tmp.isFile() && tmp.canWrite()) {
					this.file = tmp;
				} else {

					logger.severe("Can not write to file path:"
							+ System.lineSeparator() + tmp.getAbsolutePath());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			throw new InvalidParameterException("file path, name, or extension can not be empty");
		}
		
		return this;
	}
}
