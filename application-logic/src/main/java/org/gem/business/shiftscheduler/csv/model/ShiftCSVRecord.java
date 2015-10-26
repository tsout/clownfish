package org.gem.business.shiftscheduler.csv.model;

import java.util.Date;
import java.util.Formatter;
import java.util.UUID;

import org.gem.business.shiftscheduler.model.JobType;
import org.gem.event.TimePeriod;
import org.gem.utils.DateUtils;
import org.gem.utils.csv.CSVSerializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ShiftCSVRecord extends AbstractCsvRecord{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6875869313995889364L;
	private Date startDate;
	private Date endDate;

	// private TimePeriod timePeriod;

	private String description = "default description";
	private boolean isCovered = false;
	private Integer qtyResourcesRequired = 0;
	private JobType jobType;
	private UUID shiftUuid;

	public ShiftCSVRecord() {
		super();
		shiftUuid = UUID.randomUUID();

	}

	public JobType getJobType() {
		return this.jobType;
	}

	@JsonIgnore
	public TimePeriod getTimePeriod() {
		return new TimePeriod (startDate,endDate);
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


}
