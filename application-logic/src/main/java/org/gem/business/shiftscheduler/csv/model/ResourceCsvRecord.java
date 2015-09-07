package org.gem.business.shiftscheduler.csv.model;

import org.gem.business.shiftscheduler.model.FrequencyUnitType;
import org.gem.utils.csv.CSVSerializable;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ResourceCsvRecord implements CSVSerializable {
	Integer resourceId;
	String personId; 
	String firstName;
	String lastName;
	Integer frequencyLimit;
	
	/**
	 * unit of measure by which a frequency value is evaluated
	 */
	FrequencyUnitType frequencyLimitUnitOfMeasure;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getFrequencyLimit() {
		return frequencyLimit;
	}

	public void setFrequencyLimit(Integer frequencyLimit) {
		this.frequencyLimit = frequencyLimit;
	}

	public FrequencyUnitType getFrequencyLimitUnitOfMeasure() {
		return frequencyLimitUnitOfMeasure;
	}

	public void setFrequencyLimitUnitOfMeasure(
			FrequencyUnitType frequencyLimitUnitOfMeasure) {
		this.frequencyLimitUnitOfMeasure = frequencyLimitUnitOfMeasure;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}


	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
}
