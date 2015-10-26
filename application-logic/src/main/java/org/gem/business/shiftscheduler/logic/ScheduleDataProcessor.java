package org.gem.business.shiftscheduler.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.gem.business.shiftscheduler.model.BlackoutDate;
import org.gem.business.shiftscheduler.model.Resource;
import org.gem.event.Person;
import org.gem.utils.FileUtils;
import org.gem.utils.JsonUtil;
import org.gem.utils.PropertyHelper;
import org.gem.utils.csv.jackson.FasterXmlCSVUtil;
import org.gem.utils.csv.jackson.PersonCSVMixin;

import com.fasterxml.jackson.core.type.TypeReference;

public class ScheduleDataProcessor {

	public static final String CSV_EXTENSION = "csv";
	private static final String JSON_EXTENSION = "json";
	private static final String PROPERTY_NAME_DATA_FILE_PATH = "data.file.path";
	private static final String CLOWNFISH_PROPERTY_FILE = "clownfish.properties";
	private static final String DEFAULT_DATA_FILE_PATH = "c:\\test\\";
	private Schedule s;

	public ScheduleDataProcessor(Schedule s) throws InstantiationException {
		if (s != null) {
			this.s = s;
		} else {
			throw new InstantiationException("Schedule must not be null");
		}
	}

	private File getFileHandle(String fileName) {
		String filePath = getFilePathFromPropertyFile();

		return FileUtils.createANewFile(filePath, fileName, JSON_EXTENSION);
	}

	public static String getFilePathFromPropertyFile() {
		String filePath = DEFAULT_DATA_FILE_PATH;
		try {
			PropertyHelper ph = new PropertyHelper(CLOWNFISH_PROPERTY_FILE);
			String pathFromPropertyFile = ph
					.getProperty(PROPERTY_NAME_DATA_FILE_PATH);
			filePath = !StringUtils.isEmpty(pathFromPropertyFile) ? pathFromPropertyFile
					: filePath;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}

	public Schedule fromJsonFile() {
		Schedule s = JsonUtil.fromJsonFile(getFileHandle("Schedule"),
				new TypeReference<Schedule>() {
				});
		// TODO: change to support multiple return objects
		return s;
	};

	public boolean toJsonFile() {
		boolean scheduleExportSucceeded = JsonUtil.toJsonFile(s,
				getFileHandle("Schedule"));
		boolean resourceExportSucceeded = JsonUtil.toJsonFile(
				s.getImportedResources(), getFileHandle("Resources"));
		boolean shiftExportSucceeded = JsonUtil.toJsonFile(
				s.getImportedShifts(), getFileHandle("Shifts"));

		Set<Person> people = new HashSet<Person>();
		for (Resource r : s.getImportedResources()) {
			people.add(r.getPerson());
		}
		boolean personExportedSuceeded = JsonUtil.toJsonFile(people,
				getFileHandle("People"));

		return (scheduleExportSucceeded && resourceExportSucceeded
				&& shiftExportSucceeded && personExportedSuceeded);

	}

	public void importPersonCsv(String fileName) {
		if (!StringUtils.isBlank(fileName)) {
			FasterXmlCSVUtil csvUtil = new FasterXmlCSVUtil(
					getFilePathFromPropertyFile(), fileName, CSV_EXTENSION);
			Set<Person> people = new HashSet<Person>();

			try {
				people = csvUtil.fromCsvFileWithMixin(Person.class,
						PersonCSVMixin.class);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			Set<Resource> rSet = new HashSet<Resource>();
			if (people != null) {
				for (Person p : people) {
					if (p != null) {
						rSet.add(new Resource(p));
						System.out.println(p);
					}
				}
			}
			s.importResources(rSet);
		} else {
			throw new InvalidParameterException("Filename must not be empty: "
					+ fileName);
		}
	}

	public void exportResourcesToCSV(String fileName) {

		if (!StringUtils.isBlank(fileName)) {
			FasterXmlCSVUtil csvUtil = new FasterXmlCSVUtil(
					getFilePathFromPropertyFile(), fileName, CSV_EXTENSION);
			for (BlackoutDate b : s.getImportedBlackoutDates()) {
				csvUtil.toCsvFileWithMixin(b, BlackoutDate.class,
						BlackoutDateMixin.class);
			}

		} else {
			throw new InvalidParameterException("Filename must not be empty: "
					+ fileName);
		}
	}
}
