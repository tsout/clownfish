package org.gem.business.shiftscheduler.csv.model;

import java.util.Date;

import org.gem.event.TimePeriod;
import org.gem.utils.DateUtils;

public class BlackoutDateCsvRecord extends AbstractCsvRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4504530736416166213L;
	private final Date startTime; 
	private final Date endTime; 
	private final String reason;
	private final String notes; 
	private final String requestedBy; 
	

	public BlackoutDateCsvRecord(Date start, Date end,
			String reason, String notes, String requestedBy) {
		if(start==null || end==null){
			throw new IllegalArgumentException("dates must not be null");
		}
		this.startTime = start;
		this.endTime = end; 
		this.reason = reason; 
		this.requestedBy = requestedBy; 
		this.notes=notes;
		
	}

	public Date getStartDateTime() {
		return this.startTime;
	}

	public Date getEndDateTime() {
		return this.endTime;
	}


	public TimePeriod getTimePeriod() {
		return new TimePeriod (startTime, endTime);
	}

	public String getRequestedBy() {
		return requestedBy;
	}

	public String getNotes() {
		return notes;
	}

	public String getReason() {
		return reason;
	}
	
}
