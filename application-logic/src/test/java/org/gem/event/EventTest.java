package org.gem.event;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.gem.event.Event;
import org.gem.event.EventException;
import org.gem.event.MasterSchedule;
import org.junit.Before;
import org.junit.Test;

public class EventTest {

    private static final int ONE_SECOND = 1000;


    @Before
    public void setUp() throws Exception {
    }


    @Test(expected = EventException.class)
    public void testEventWithBadDates() throws EventException, InterruptedException {
    	Calendar startDate = Calendar.getInstance();
        startDate.setTime(new Date());
        Thread.sleep(ONE_SECOND);
        final Calendar endDate = Calendar.getInstance();
        endDate.setTime(new Date());
        final Event e = new Event(endDate, startDate, "a new event");
        System.out.println(e);
    }


    @Test
    public void testEvent() throws InterruptedException, EventException {
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(new Date());
        Thread.sleep(ONE_SECOND);
        final Calendar endDate = Calendar.getInstance();
        endDate.setTime(new Date());
        final Event e = new Event(startDate, endDate, "a new event");
        System.out.println(e);
    }


    @Test
    public void testSameStartEvent() throws InterruptedException, EventException {
    	Calendar startDate = Calendar.getInstance();
        startDate.setTime(new Date());
        Thread.sleep(ONE_SECOND);
        final Calendar endDate = Calendar.getInstance();
        endDate.setTime(new Date());
        final Event e = new Event(startDate, startDate, "a new event");
        System.out.println(e);
    }
    
    @Test
    public void testOverLapptingEventsProduceScheduleConflict() throws Exception {
    	
    	MasterSchedule ms = new MasterSchedule();
		assertTrue("Expected Schedule Conflict", populateTestEvents(ms, 2));
		
	}


	private boolean populateTestEvents(MasterSchedule ms, int eventCount) throws EventException {
		boolean hadConflicts = false;

		for (int x=0;x<eventCount; x++ ){
			int startHr = x+3; 
			int durationHrs = 2;
			Calendar start= Calendar.getInstance();
    		Calendar  end= Calendar.getInstance();
    		
    		start.set(2013, 8, 1, startHr, 0);
			end.set(2013, 8, 1, startHr+durationHrs, 0);
			
			Event e = new Event(start, end, "This is event # "+(x+1));    		
    		if (!ms.scheduleEvent(e))
    			hadConflicts = true;
    	}
		return hadConflicts;
	}
}
