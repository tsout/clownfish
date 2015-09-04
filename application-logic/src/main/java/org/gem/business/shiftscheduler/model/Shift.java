package org.gem.business.shiftscheduler.model;

import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import org.gem.utils.TimePeriod;
import org.gem.utils.DateUtils;

public class Shift implements Comparable {

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

	public Date getStartDateTime() {
		return timePeriod.getStartDateTime();
	}

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

	@Override
	public String toString() {
		Formatter f = new Formatter();
		try{
			String startDate = DateUtils.printDate(timePeriod.getStartDateTime());
			String endDate=DateUtils.printDate(timePeriod.getEndDateTime());
			String covered = isCovered?"is":"is NOT";
			f.format("Shift:%s\n[%s->%s]\nrequires %s resources and %s covered", description,startDate,endDate,qtyResourcesRequired,covered);
		return f.toString();
		}
		finally{
			f.close();
		}
//		return "Shift [startDateTime="
//				+ DateUtils.printDate(timePeriod.getStartDateTime())
//				+ ", endDateTime="
//				+ DateUtils.printDate(timePeriod.getEndDateTime())
//				+ ", description=" + description + ", isCovered=" + isCovered
//				+ ", qtyResourcesRequired=" + qtyResourcesRequired
//				+ ", jobType=" + jobType + ", shiftId=" + shiftUuid + "]";
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

	public int compareTo(Object o) {
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
}
