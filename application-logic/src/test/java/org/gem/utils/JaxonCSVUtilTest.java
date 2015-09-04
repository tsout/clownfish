package org.gem.utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.gem.business.shiftscheduler.csv.model.PersonCsvRecord;
import org.gem.business.shiftscheduler.csv.model.ResourceCsvRecord;
import org.gem.business.shiftscheduler.model.Resource;
import org.gem.event.Person;
import org.gem.utils.csv.CSVUtil;
import org.gem.utils.csv.FasterXmlCSVProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
	public void test() {

		FasterXmlCSVProvider csvUtil = new FasterXmlCSVProvider();

		System.out.println(personList.toString());
		String output = csvUtil.<PersonCsvRecord> write(personList, PersonCsvRecord.class);
		assertNotNull(output);
		System.out.println(output);

	}

	@Test
	public void testFileWriting() throws IOException {

		String fileName = "test";
		String extension = "csv";
		FasterXmlCSVProvider csvUtil = new FasterXmlCSVProvider()
				.setTargetCSVFile(path, fileName, extension);
		assertTrue(csvUtil.<PersonCsvRecord> writeToFile(personList, PersonCsvRecord.class));
		CSVUtil.printFileContents(path, fileName, extension);

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
		FasterXmlCSVProvider csvUtil = new FasterXmlCSVProvider()
				.setTargetCSVFile(path, fileName, extension);
		assertTrue(csvUtil.<ResourceCsvRecord> writeToFile(resourceList, ResourceCsvRecord.class));
		CSVUtil.printFileContents(path, fileName, extension);

	}

}

