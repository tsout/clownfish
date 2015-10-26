package org.gem.business.shiftscheduler.model;

import java.util.Date;

import org.gem.event.TimePeriod;
import org.gem.utils.AbstractPojo;
import org.gem.utils.PojoUtils;
import org.gem.utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class BlackoutDate extends AbstractPojo {

	private TimePeriod timePeriod;
	private String reason;

	/**
	 * package protected for entity generators only
	 * 
	 */
	BlackoutDate() {
		// noop
	}

	public BlackoutDate(Date start, Date end, String reason) {
		this.timePeriod = new TimePeriod(start, end);
		this.reason = reason;
	}

	@JsonIgnore
	public Date getStartDateTime() {
		return timePeriod.getStartDateTime();
	}

	@JsonIgnore
	public Date getEndDateTime() {
		return timePeriod.getEndDateTime();
	}

	public String getReason() {
		return reason;
	}

	 public void setReason(String reason) {
	 this.reason = reason;
	 }


	public TimePeriod getTimePeriod() {
		return this.timePeriod;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
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
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		if (timePeriod == null) {
			if (other.timePeriod != null)
				return false;
		} else if (!timePeriod.equals(other.timePeriod))
			return false;
		return true;
	}
	
	

}
