package org.gem.utils.csv.jackson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.gem.event.Person;
import org.gem.utils.FileUtils;
import org.gem.utils.csv.CSVSerializable;
import org.gem.utils.csv.ICsvUtil;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.io.OutputDecorator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.fasterxml.jackson.dataformat.csv.CsvFactory;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.csv.CsvSchema.Column;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class FasterXmlCSVUtil implements ICsvUtil {

	private File file = null;

	private Logger logger = Logger.getLogger(FasterXmlCSVUtil.class
			.getSimpleName());

	public FasterXmlCSVUtil(String path, String fileName, String fileExtension) {
		setTargetCSVFile(path, fileName, fileExtension);
	}

	public FasterXmlCSVUtil(File f) {
		if (f != null)
			this.file = f;
	}

	/**
	 * creates multiple lines of CSV output from a list of objects that extends
	 * the CSVSerializable interface
	 * 
	 * @param list
	 * @param clazz
	 * @return
	 */
	public static String toCsvString(Object obj, Class<?> clazz) {

		CsvMapper csvMapper = new CsvMapper();
		String csvString = "";
		registerSerializer(csvMapper);
		CsvSchema schema = csvMapper.schemaFor(clazz).withColumnSeparator('|')
				.withHeader().withLineSeparator(System.lineSeparator());
		try {
			csvString = csvMapper.writer(schema).writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.getMessage();
		}
		return csvString;
	}

	public boolean toCsvFileWithMixin(Object object, Class<?> clazz,
			Class<?> mixin) {
		if (!this.file.exists()) {
			return false;
		}

		// output writer
		ObjectWriter myObjectWriter = configureCsvMapperWithMixin(clazz, mixin);

		try {
			myObjectWriter.writeValue(file, object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return false;
		} catch (JsonMappingException e) {
			e.getMessage();
			return false;
		} catch (IOException e) {
			e.getMessage();
			return false;
		}

		return true;
	}

	public Boolean toCsvFile(Object object, Class<?> clazz) {
		if (!this.file.exists()) {
			return false;
		}

		// output writer
		ObjectWriter myObjectWriter = configureCsvMapper(clazz);

		try {
			myObjectWriter.writeValue(file, object);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
			return false;
		} catch (JsonMappingException e) {
			e.getMessage();
			return false;
		} catch (IOException e) {
			e.getMessage();
			return false;
		}

		return true;
	}

	private ObjectWriter configureCsvMapper(Class<?> clazz) {
		return configureCsvMapperWithMixin(clazz, null);
	}

	private ObjectWriter configureCsvMapperWithMixin(Class<?> clazz,
			Class<?> mixin) {
		CsvMapper m = new CsvMapper();
		if (mixin != null) {
			m.addMixInAnnotations(clazz, mixin);
		}
		CsvSchema schema = m.schemaFor(clazz);
		schema = schema.withColumnSeparator(',')
				.withLineSeparator(System.lineSeparator()).withHeader();

		return m.writer(schema);
	}

	private static void registerSerializer(CsvMapper m) {
		SimpleModule module = new SimpleModule();
		module.addSerializer(Date.class, new FasterXMLCustomDateSerializer());
		m.registerModule(module);
	}

	// public <T extends CSVSerializable> Boolean writeToFile(T record,
	// Class<T> clazz) {
	// if (!this.file.exists()) {
	// return false;
	// }
	//
	// // output writer
	// ObjectWriter myObjectWriter = configureCsvMapper(clazz);
	//
	// try {
	// System.out.println(record);
	// myObjectWriter.writeValue(file, record);
	// } catch (JsonGenerationException e) {
	// e.getMessage();
	// return false;
	// } catch (JsonMappingException e) {
	// e.getMessage();
	// return false;
	// } catch (IOException e) {
	// e.getMessage();
	// return false;
	// }
	// return true;
	// }

	/**
	 * builder method that sets the csv file that will be written to
	 * 
	 * @param filePath
	 * @param fileName
	 * @param extension
	 * @return
	 */
	// TODO: validate fileName, filePath, and extension
	private FasterXmlCSVUtil setTargetCSVFile(String filePath, String fileName,
			String extension) {
		this.file = FileUtils.createANewFile(filePath, fileName, extension);
		return this;
	}

	private ObjectReader configureReader(Class<?> clazz, Class<?> mixin) {
		CsvMapper m = new CsvMapper();
		m.addMixInAnnotations(clazz, mixin);
		CsvSchema s = m.schemaFor(clazz).withHeader().withColumnSeparator(',')
				.withLineSeparator(System.lineSeparator());
		return m.reader(s).withType(clazz);
	}

	public <T> Set<T> fromCsvFileWithMixin(Class<T> clazz, Class<?> mixin)
			throws FileNotFoundException {
		HashSet<T> set = new HashSet<T>();
		if (this.file.exists()) {
			ObjectReader oReader = configureReader(clazz, mixin);
			Person p = Person.getInstance();
			try {
				MappingIterator<T> objIt = oReader
						.<T> readValues(new FileInputStream(file));
				while (objIt.hasNext()) {
					set.add(objIt.next());
				}
				return set;
			} catch (JsonGenerationException e) {
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				e.getMessage();
				System.err.println(e.getMessage());
				return null;
			}

		} else {
			throw new FileNotFoundException("Can't find file:"
					+ this.file.getAbsolutePath());
		}
	}
}
