package org.gem.business.shiftscheduler.logic;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.gem.business.shiftscheduler.csv.model.PersonCsvRecord;
import org.gem.business.shiftscheduler.csv.model.ResourceCsvRecord;
import org.gem.business.shiftscheduler.csv.model.ShiftCSVRecord;
import org.gem.business.shiftscheduler.model.Resource;
import org.gem.business.shiftscheduler.model.Shift;
import org.gem.business.shiftscheduler.model.ShiftAssignment;
import org.gem.utils.BeanMapper;
import org.gem.utils.FileUtils;
import org.gem.utils.JsonUtil;
import org.gem.utils.PropertyHelper;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ScheduleDataProcessor {

	private Schedule s;

	public ScheduleDataProcessor(Schedule s) throws InstantiationException {
		if (s != null) {
			this.s = s;
		} else {
			throw new InstantiationException("Schedule must not be null");
		}
	}

	private File getFileHandle(String fileName) {
		String filePath = "c:\\test\\";
		try {
			PropertyHelper ph = new PropertyHelper("clownfish.properties");
			String pathFromPropertyFile = ph.getProperty("data.file.path");
			filePath = !StringUtils.isEmpty(pathFromPropertyFile) ? pathFromPropertyFile
					: filePath;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return FileUtils.createANewFile(filePath, fileName, "json");
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

		return (scheduleExportSucceeded && resourceExportSucceeded && shiftExportSucceeded);

	}

}
