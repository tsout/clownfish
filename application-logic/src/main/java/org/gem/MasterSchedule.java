package org.gem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.gem.Event.EventStatus;
import org.gem.utils.DateUtils;

public class MasterSchedule {

    enum RequestStatus {
        GRANTED, DENIED
    };

    List<Event> scheduledEvents;
    List<Event> availabilities;

	MasterSchedule(){
		scheduledEvents = new ArrayList<Event>();
		availabilities= new ArrayList<Event>();
		 
	}
    public void autoRespondToEventDelay(final Event event) {
    };


    // public void respondToEventDelay(Event event, Action action){};

    public void showAvailability(final Date startDate, final Date endDate) {
    };


    // public void showAvailability(Date startDate, Time duration){};

    public void showConflicts(final Date startDate, final Date endDate) {
    };


    // public void showConflicts(Date startDate, Time duration){};

    public void showScheduledEvents(final Date startDate, final Date endDate) {
    };


    // public void showScheduledEvents(Date startDate, Time duration){};

    public void showMasterSchedule(final Date startDate, final Date endDate) {
    };


    // public void showMasterSchedule(Date startDate, Time duration){};

    public void showDestinationSequence() {
    };


    public RequestStatus commitRequest(final Event event) {
        final RequestStatus requestStatus = RequestStatus.DENIED;
        if (eventConflictExists(event)) {
        }
        return requestStatus;
    };


    // public RequestStatus delayRequest(){return requestStatus; };
    // public RequestStatus tentativeRequest(){return requestStatus; };

    public boolean eventConflictExists(final Event event) {

        // check for conflicts w/ master schedule
        Calendar proposedStartDate= event.getStartDate();
        Calendar proposedEndDate=event.getEndDate();

        for (final Event e : this.scheduledEvents) {
        	boolean eventPrecedes = proposedStartDate.before(e.getStartDate()) && proposedEndDate.before(e.getStartDate());
        	boolean eventIsAfter =proposedStartDate.after(e.getEndDate()) && proposedEndDate.after(e.getEndDate());
            if (eventPrecedes || eventIsAfter) 
            {
            	continue; // no conflict
            } else {
            	String errorMsg = "Conflict between proposed ["+event.getEventName()+"] and scheduled ["+e.getEventName()+"] exists";
            	System.err.println(errorMsg);
            	System.err.println(printConflictDetails(event, e));
            	System.err.println();
            	return true;
            }
        }
        return false; // no events, no conflicts
    }


    private String printConflictDetails(Event proposedEvent, Event existingEvent) {
		String proposedWindow="\t"+DateUtils.printDate(proposedEvent.getStartDate())+" - "+DateUtils.printDate(proposedEvent.getEndDate());
		String existingWindow="\t"+DateUtils.printDate(existingEvent.getStartDate())+" - "+DateUtils.printDate(existingEvent.getEndDate());
		String overlap=""; //TODO: calculate the amount of time that the events intersect
		return overlap+proposedWindow+" conflicts with "+existingWindow;
	}
	public void commit(final Event event) {
        event.updateStatus(EventStatus.SCHEDULED);
        // notify participants
        // reserve place
        // reserve things
    }


    public void setDelay(final Event e, final Date newStartTime) {
        // determine response: slip, reschedule,acceptConflict
        // notify participants of delay action
        // extend place reservations
        // extend thing reservations
    }


	public boolean scheduleEvent(Event e) {
		if(!eventConflictExists(e)){
			e.setStatus(EventStatus.SCHEDULED);
			scheduledEvents.add(e);
			return true;
		}
		return false;  //conflict found, event not scheduled
	}
}
