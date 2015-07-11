package org.gem.utils;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class DateUtilsTest {

	
	Calendar c1 , c2,c3,c4,c5;
	
	@Before
	public void setUp() throws Exception {
		seedDates();
	}

	@After
	public void tearDown() throws Exception {

		c1=c2=c3=c4=c5=null;
	}

	@Test
	public void testIsBetween() {

		assertTrue(DateUtils.printDateComparison(c2,c1,c3), DateUtils.isBetween(c2, c1, c3));
		assertTrue(DateUtils.printDateComparison(c2,c1,c3), DateUtils.isBetween(c2.getTime(), c1.getTime(), c3.getTime()));
		assertFalse(DateUtils.printDateComparison(c4,c2,c3), DateUtils.isBetween(c4,c2,c3));
		assertFalse(DateUtils.printDateComparison(c5,c4,c1), DateUtils.isBetween(c5, c4, c1));
		
	}

	private void seedDates() {
		c1 = Calendar.getInstance();
		c2 = Calendar.getInstance();
		c3 = Calendar.getInstance();
		c4 = Calendar.getInstance();
		c5 = Calendar.getInstance();
		
		c1.set(2015, Calendar.MAY, 8);
		c2.set(2015, Calendar.JUNE, 1);
		c3.set(2015, Calendar.JUNE, 8);
		c4.set(2016, Calendar.MAY, 2);
		c5.set(2015, Calendar.MAY, 2);
	}

	@Test
	public void testConflictingPeriods() {

		TimePeriod p1 = new TimePeriod(c1,c2);
		TimePeriod p2 = new TimePeriod(c3,c4);
		assertTrue(!p1.conflictsWith(p2));
		
		TimePeriod p3 = new TimePeriod(c1,c3);
		assertTrue (p3.conflictsWith(p1));
	}

}
