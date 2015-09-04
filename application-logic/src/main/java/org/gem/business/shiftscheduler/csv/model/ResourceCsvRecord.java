package org.gem.business.shiftscheduler.csv.model;

import org.gem.business.shiftscheduler.model.FrequencyUnitType;
import org.gem.utils.csv.CSVSerializable;

public class ResourceCsvRecord implements CSVSerializable {
	Integer resourceId;
	String firstName;
	String lastName;
	String email;
	String twitter;
	String facebook;
	String phone;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
}
