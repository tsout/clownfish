package org.gem.business.shiftscheduler.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.gem.event.Person;
import org.gem.utils.AbstractPojo;
import org.gem.utils.PojoUtils;
import org.gem.utils.csv.CSVSerializable;
import org.jboss.logging.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Resource extends AbstractPojo {

	private Integer resourceId;
	private Person person;	
	private Integer frequencyLimit;
	
	/**
	 * unit of measure by which a frequency value is evaluated
	 */
	private FrequencyUnitType frequencyLimitUnitOfMeasure;
	private Set<JobType> qualifiedForJobs;
	private Set<BlackoutDate> blackouts;
	private Set<Shift> assignments;
	private Logger logger = Logger.getLogger(Resource.class);

	public Resource() {
		initialize();
	}

	private void initialize() {
			this.blackouts = new HashSet<BlackoutDate>();
			this.qualifiedForJobs = new HashSet<JobType>();
			this.assignments = new HashSet<Shift>();
			person = Person.getInstance();
	}

	public Resource(String firstName, String lastName, String email,
			String facebook, String twitter, String mobilePhone,
			Set<BlackoutDate> blackouts, Set<JobType> jobTypes, FrequencyUnitType unit , Integer frequency) {

		initialize();
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setPrimaryEmailAddress(email);

		person.setFacebook(facebook);
		person.setTwitter(twitter);
		person.setMobilePhone(mobilePhone);
		if (blackouts != null)
			this.blackouts = blackouts;
		if (jobTypes != null)
			this.qualifiedForJobs = jobTypes;
		this.frequencyLimitUnitOfMeasure =unit;
		this.frequencyLimit = frequency;
	}

	public Set<BlackoutDate> getBlackOutDates() {
		return blackouts;
	}

	public Set<JobType> getQualifiedForJobTypes() {
		return qualifiedForJobs;
	}

	public Set<JobType> getQualifiedForJobs() {
		return qualifiedForJobs;
	}

	public void setQualifiedForJobs(Set<JobType> qualifiedForJobs) {
		this.qualifiedForJobs = qualifiedForJobs;
	}

	public Set<BlackoutDate> getBlackouts() {
		return blackouts;
	}

	public void setBlackouts(Set<BlackoutDate> blackouts) {
		this.blackouts = blackouts;
	}

	public Set<Shift> getAssignments() {
		return assignments;
	}

	/**
	 * Determines if the resource has been assigned too many times within a prescribed period of time. 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean resourceAssignmentFrequencyExceeded() {
		List<Shift> shiftList = new ArrayList(assignments);

		Collections.sort(shiftList);
		switch (this.frequencyLimitUnitOfMeasure) {
		case WEEKLY:
			for (int x = 0; x < shiftList.size(); x++) {
				if((x+1)<shiftList.size()){
					int currentStart = shiftList.get(x).getStartDateTime().getDate();
					int nextStart = shiftList.get(x+1).getStartDateTime().getDate();
					if(Math.abs(currentStart-nextStart )>7){
						continue;
					}
					logger .info("Weekly frequency '"+ this.frequencyLimit +"x Week' exceeded");
					return false;
				}
				return true;
			}
			// for(Shift shift : assignments){
			// Calendar cal = Calendar.getInstance();
			// cal.setTime(shift.getStartDateTime());
			//
			// cal.getFirstDayOfWeek();
			// cal.roll(Calendar.DATE, up);
			// cal.get(Calendar.DAY_OF_WEEK);
			// cal.get(Calendar.DATE);
			// // if ((shift.getStartDateTime()-nextShift.getStartDateTime)<7)
			// || ((previous.getStartDateTime() -shift.getStartDateTime())>7){
			// return true;
			// }
			// }
			return false;
		case DAILY:
		case MONTHLY:
		case YEARLY:
		default:
			return false;
		}
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignments == null) ? 0 : assignments.hashCode());
		result = prime * result
				+ ((blackouts == null) ? 0 : blackouts.hashCode());
		result = prime * result
				+ ((frequencyLimit == null) ? 0 : frequencyLimit.hashCode());
		result = prime
				* result
				+ ((frequencyLimitUnitOfMeasure == null) ? 0
						: frequencyLimitUnitOfMeasure.hashCode());
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime
				* result
				+ ((qualifiedForJobs == null) ? 0 : qualifiedForJobs.hashCode());
		result = prime * result
				+ ((resourceId == null) ? 0 : resourceId.hashCode());
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
		Resource other = (Resource) obj;
		if (assignments == null) {
			if (other.assignments != null)
				return false;
		} else if (!assignments.equals(other.assignments))
			return false;
		if (blackouts == null) {
			if (other.blackouts != null)
				return false;
		} else if (!blackouts.equals(other.blackouts))
			return false;
		if (frequencyLimit == null) {
			if (other.frequencyLimit != null)
				return false;
		} else if (!frequencyLimit.equals(other.frequencyLimit))
			return false;
		if (frequencyLimitUnitOfMeasure != other.frequencyLimitUnitOfMeasure)
			return false;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (qualifiedForJobs == null) {
			if (other.qualifiedForJobs != null)
				return false;
		} else if (!qualifiedForJobs.equals(other.qualifiedForJobs))
			return false;
		if (resourceId == null) {
			if (other.resourceId != null)
				return false;
		} else if (!resourceId.equals(other.resourceId))
			return false;
		return true;
	}

	public Person getPerson() {
		return person;
	}
	
	
}
