package org.gem.business.shiftscheduler.logic;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.gem.business.shiftscheduler.model.FrequencyUnitType;
import org.gem.business.shiftscheduler.model.JobType;
import org.gem.business.shiftscheduler.model.Resource;
import org.gem.business.shiftscheduler.model.BlackoutDate;
import org.gem.business.shiftscheduler.model.Shift;
import org.gem.business.shiftscheduler.model.ShiftAssignment;
import org.gem.utils.DateUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ShiftSchedulerTest {

	private static Schedule uut;
	Set<Resource> resources = seedResources();
	Set<Shift> shifts = seedShifts();
	Set<BlackoutDate> blackouts = seedBlackouts();

	@Before
	public void setUp() throws Exception {
		uut = new Schedule();
		resources = seedResources();
		shifts = seedShifts();
		blackouts = seedBlackouts();
		uut.importResources(resources);
		uut.importShiftData(shifts);
		uut.importShiftResourceBlackouts(blackouts);

	}

	@After
	public void tearDown() throws Exception {
		uut =null;
		resources = null;
		shifts = null;
		blackouts = null;
	}

	@Test
	public void testImportShiftResourceBlackouts() {
		assertTrue(uut.getImportedBlackoutDates().size() > 0);
	}

	@Test
	public void testGenerateSchedules() {
		Set<ShiftAssignment> generatedSchedule = generateAssignments();
		assertTrue("expect assignment size to exceed 0",generatedSchedule.size()>0);
	}

	private Set<ShiftAssignment> generateAssignments() {
		Set<ShiftAssignment> generatedSchedule = uut.generateSchedules(resources, shifts);		
		return generatedSchedule;
	}
	
	@Test
	public void testExportSchedule() throws Exception {
		generateAssignments();
		ScheduleDataProcessor dataProcessor = new ScheduleDataProcessor(uut);
		dataProcessor.toJsonFile(); 
		
	}
	
	@Test
	public void testImportSchedule() throws Exception {
		
		Schedule importedSchedule = new Schedule();		
		ScheduleDataProcessor dataProcessor = new ScheduleDataProcessor(uut);
		importedSchedule = dataProcessor.fromJsonFile();
		Set<ShiftAssignment> importedShiftAssignments = importedSchedule.getImportedShiftAssignments();
		assertTrue(importedShiftAssignments.size()>0);
		System.out.println(importedShiftAssignments);
		
		
	}

	private static Set<BlackoutDate> seedBlackouts() {
		Set<BlackoutDate> blackouts = new HashSet<BlackoutDate>();
		String reason = "may blackout";
		Date start = DateUtils.buildDate(5, 1, 2015);
		Date end = DateUtils.buildDate(5, 28, 2015);
		blackouts.add(new BlackoutDate(start, end, reason));

		String reason2 = "june 1 blackout";
		Date start2 = DateUtils.buildDate(6, 1, 2015);
		Date end2 = DateUtils.buildDate(6, 10, 2015);
		blackouts.add(new BlackoutDate(start2, end2, reason2));

		String reason3 = "june 2 blackout";
		Date start3 = DateUtils.buildDate(6, 26, 2015);
		Date end3 = DateUtils.buildDate(6, 26, 2015);
		blackouts.add(new BlackoutDate(start3, end3, reason3));

		return blackouts;
	}

	private static Set<Shift> seedShifts() {
		Set<Shift> shifts = new HashSet<Shift>();

		Shift s = new Shift();
		s.setTimePeriod(DateUtils.buildDate(6, 27, 2015),
				DateUtils.buildDate(6, 27, 2015));
		s.setJobType(JobType.COMMUNION);
		s.setQtyResourcesRequired(3);
		shifts.add(s);

		Shift s2 = new Shift();
		s2.setTimePeriod(DateUtils.buildDate(5, 15, 2014),
				(DateUtils.buildDate(5, 15, 2014)));
		s2.setJobType(JobType.USHER);
		shifts.add(s2);
		s2.setQtyResourcesRequired(2);

		Shift s3 = new Shift();
		s3.setTimePeriod(DateUtils.buildDate(5, 28, 2015),
				DateUtils.buildDate(5, 28, 2015));
		s3.setJobType(JobType.USHER);
		s3.setQtyResourcesRequired(1);
		shifts.add(s3);

		Shift s4 = new Shift();
		s4.setTimePeriod(DateUtils.buildDate(6, 26, 2015),
				DateUtils.buildDate(6, 26, 2015));
		s4.setJobType(JobType.USHER);
		s4.setQtyResourcesRequired(1);

		shifts.add(s4);

		return shifts;
	}

	private static Set<Resource> seedResources() {
		Set<JobType> jobTypes = new HashSet<JobType>();
		jobTypes.add(JobType.USHER);
		jobTypes.add(JobType.COMMUNION);

		Resource r1 = new Resource("Bill", "other",
				"tim.southerland@gmail.com", null, null, null, seedBlackouts(),
				jobTypes,FrequencyUnitType.WEEKLY,1);
		Resource r2 = new Resource("Joe", "Last", "email@email.com", null,
				null, null, seedBlackouts(), jobTypes,FrequencyUnitType.WEEKLY,2);
		Resource r3 = new Resource("dave", "Last", "email@email.com", null,
				null, null, seedBlackouts(), jobTypes,FrequencyUnitType.WEEKLY,2);
		Resource r4 = new Resource("john", "Last", "email@email.com", null,
				null, null, seedBlackouts(), jobTypes,FrequencyUnitType.WEEKLY,2);
		Resource r5 = new Resource("roger", "Last", "email@email.com", null,
				null, null, seedBlackouts(), jobTypes,FrequencyUnitType.WEEKLY,2);
		Resource r6 = new Resource("kenny", "Last", "email@email.com", null,
				null, null, seedBlackouts(), jobTypes, FrequencyUnitType.WEEKLY,4);

		HashSet<Resource> resources = new HashSet<Resource>();
		resources.add(r1);
		resources.add(r2);
		resources.add(r3);
		resources.add(r4);
		resources.add(r5);
		resources.add(r6);

		return resources;
	}


}
