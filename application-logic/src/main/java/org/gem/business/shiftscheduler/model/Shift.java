package org.gem.business.shiftscheduler.model;

import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import org.gem.utils.AbstractPojo;
import org.gem.utils.PojoUtils;
import org.gem.utils.TimePeriod;
import org.gem.utils.DateUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Shift extends AbstractPojo implements Comparable<Shift>  {

	private TimePeriod timePeriod;
	private String description = "default description";
	private boolean isCovered = false;
	private Integer qtyResourcesRequired = 0;
	private JobType jobType;
	private UUID shiftUuid;

	public Shift() {
		super();
		shiftUuid = UUID.randomUUID();

	}

	public JobType getJobType() {
		return this.jobType;
	}

	@JsonIgnore
	public Date getStartDateTime() {
		return timePeriod.getStartDateTime();
	}

	@JsonIgnore
	public Date getEndDateTime() {
		return timePeriod.getEndDateTime();
	}

	public void setTimePeriod(Date start, Date end) {
		this.timePeriod = new TimePeriod(start, end);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isCovered() {
		return isCovered;
	}

	public void setCovered(boolean isCovered) {
		this.isCovered = isCovered;
	}

	public Integer getQtyResourcesRequired() {
		return qtyResourcesRequired;
	}

	public void setQtyResourcesRequired(Integer qtyResourcesRequired) {
		this.qtyResourcesRequired = qtyResourcesRequired;
	}

	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}

	public UUID getShiftId() {
		return shiftUuid;
	}

	public void setShiftId(UUID shiftId) {
		this.shiftUuid = shiftId;
	}

	public TimePeriod getTimePeriod() {
		return timePeriod;
	}

	public void setTimePeriod(TimePeriod timePeriod) {
		this.timePeriod = timePeriod;
	}

	public int compareTo(Shift o) {
		Shift shift = (Shift) o;
		// order shifts by start times
		if (this.timePeriod.getStartDateTime().before(
				shift.timePeriod.getStartDateTime())) {
			return -1;
		} else if (this.timePeriod.getStartDateTime().after(
				shift.timePeriod.getStartDateTime())) {
			return 1;
		} else {
			return 0;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + (isCovered ? 1231 : 1237);
		result = prime * result + ((jobType == null) ? 0 : jobType.hashCode());
		result = prime
				* result
				+ ((qtyResourcesRequired == null) ? 0 : qtyResourcesRequired
						.hashCode());
		result = prime * result
				+ ((shiftUuid == null) ? 0 : shiftUuid.hashCode());
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
		Shift other = (Shift) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (isCovered != other.isCovered)
			return false;
		if (jobType != other.jobType)
			return false;
		if (qtyResourcesRequired == null) {
			if (other.qtyResourcesRequired != null)
				return false;
		} else if (!qtyResourcesRequired.equals(other.qtyResourcesRequired))
			return false;
		if (shiftUuid == null) {
			if (other.shiftUuid != null)
				return false;
		} else if (!shiftUuid.equals(other.shiftUuid))
			return false;
		if (timePeriod == null) {
			if (other.timePeriod != null)
				return false;
		} else if (!timePeriod.equals(other.timePeriod))
			return false;
		return true;
	}
	
	
}
