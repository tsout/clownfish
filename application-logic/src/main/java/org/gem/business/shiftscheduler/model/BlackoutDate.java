package org.gem.business.shiftscheduler.model;

import java.util.Date;

import org.gem.utils.TimePeriod;
import org.gem.utils.DateUtils;

public class BlackoutDate {

	private final TimePeriod timePeriod;
	private final String reason;
	

	public BlackoutDate(Date start, Date end,
			String reason) {
		this.timePeriod = new TimePeriod(start,end);
		this.reason = reason; 
	}

	public Date getStartDateTime() {
		return timePeriod.getStartDateTime();
	}

	public Date getEndDateTime() {
		return timePeriod.getEndDateTime();
	}


	public String getReason() {
		return reason;
	}

//	public void setReason(String reason) {
//		this.reason = reason;
//	}

	@Override
	public String toString() {
		return "BlackoutDate [startDateTime=" + DateUtils.printDate(timePeriod.getStartDateTime())
				+ ", endDateTime=" + DateUtils.printDate(timePeriod.getEndDateTime()) + ", reason=" + reason + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((timePeriod == null) ? 0 : timePeriod.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlackoutDate other = (BlackoutDate) obj;
		if (timePeriod == null) {
			if (other.timePeriod != null)
				return false;
		} else if (!timePeriod.equals(other.timePeriod))
			return false;
		return true;
	}

	public TimePeriod getTimePeriod() {
		return this.timePeriod;
	}
	
}
