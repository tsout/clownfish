package org.gem.utils;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.gem.business.shiftscheduler.csv.model.PersonCsvRecord;
import org.gem.business.shiftscheduler.csv.model.ResourceCsvRecord;
import org.gem.business.shiftscheduler.model.BlackoutDate;
import org.gem.business.shiftscheduler.model.JobType;
import org.gem.business.shiftscheduler.model.Resource;
import org.gem.business.shiftscheduler.model.Shift;
import org.gem.event.Person;
import org.gem.utils.csv.jackson.FasterXmlCSVUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class JaxonCSVUtilTest {
	private static final String TEST_PATH = "c:"+File.separator+"test";
	private static List<PersonCsvRecord> personList;
	private static List<ResourceCsvRecord> resourceList;
	private static String path =null;
	/**
	 * Uses PoDAM to populate a person w/ random values. NOTE: Pojo can not have
	 * a copy constructor when using PODAM
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		path = TEST_PATH;
		personList = PojoTestUtils.<PersonCsvRecord> generatePojos(50, PersonCsvRecord.class);
		resourceList = PojoTestUtils.<ResourceCsvRecord> generatePojos(50, ResourceCsvRecord.class);
	}

	@After
	public void tearDown() throws Exception {
		personList = null;
	}

	@Test
	public void testCsvStringOutput() {
		String output = FasterXmlCSVUtil.toCsvString(personList, PersonCsvRecord.class);
		assertNotNull(output);
//		System.out.println(output);
	}
	
	@Test
	public void testSingleObjectCsvOutput() {
		String output;
		output=FasterXmlCSVUtil.toCsvString(personList.get(0), PersonCsvRecord.class);
		assertNotNull(output);
	}

	@Test
	public void testFileWriting() throws IOException {

		String fileName = "test";
		String extension = "csv";
		FasterXmlCSVUtil csvUtil = new FasterXmlCSVUtil(path, fileName, extension);
		assertTrue(csvUtil.toCsvFile(personList, PersonCsvRecord.class));
		FileUtils.printFileContents(path, fileName, extension);

	}
	
	@Test
	public void testFileWriting2() throws IOException {

		String fileName = "test";
		String extension = "csv";
		FasterXmlCSVUtil csvUtil = new FasterXmlCSVUtil(path, fileName, extension);
		List<PersonCsvRecord> personCsvRecords = new ArrayList<PersonCsvRecord>();
		Person e= new Person(UUID.randomUUID());
		
//		e.setBirthday(new Date());
		e.setFirstName("t");
		e.setLastName("test");
//		personCsvRecords.add(e);
		
		Boolean output = csvUtil.toCsvFile(e, Person.class);
		assertTrue(output);
//		System.out.println("t"+output);
		FileUtils.printFileContents(path, fileName, extension);

	}
	  
	@Test
	public void testMapper() throws Exception {
		PersonCsvRecord inputObject = personList.get(0);
//		System.out.println(inputObject);
		Person p = BeanMapper.<Person>map(inputObject, org.gem.event.Person.class);
		assertNotNull(p);
//		System.out.println(p);
		
	}
	
	@Test
	public void testResourceFileWriting() throws IOException {
 
		String fileName = "resource";
		String extension = "csv";
		FasterXmlCSVUtil csvUtil = new FasterXmlCSVUtil(path, fileName, extension);
		assertTrue(csvUtil.toCsvFile(resourceList, ResourceCsvRecord.class));
		FileUtils.printFileContents(path, fileName, extension);

	}
	
	

}

