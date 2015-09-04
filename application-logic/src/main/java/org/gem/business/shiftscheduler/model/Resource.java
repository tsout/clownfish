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

import org.gem.utils.csv.CSVSerializable;
import org.jboss.logging.Logger;

public class Resource {

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
	Set<JobType> qualifiedForJobs;
	Set<BlackoutDate> blackouts;
	private Set<Shift> assignments;
	private Logger logger = Logger.getLogger(Resource.class);

	public Resource() {
		initialize();
	}

	private void initialize() {
		if (this.blackouts == null) {
			this.blackouts = new HashSet<BlackoutDate>();
		}

		if (this.qualifiedForJobs == null) {
			this.qualifiedForJobs = new HashSet<JobType>();
		}
		if (this.assignments == null) {
			this.assignments = new HashSet<Shift>();
		}
	}

	public Resource(String firstName, String lastName, String email,
			String facebook, String twitter, String phone,
			Set<BlackoutDate> blackouts, Set<JobType> jobTypes, FrequencyUnitType unit , Integer frequency) {

		initialize();

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.facebook = facebook;
		this.twitter = twitter;
		this.phone = phone;
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

	@Override
	public String toString() {
//		return "Resource [firstName=" + firstName + ", lastName=" + lastName
//				+ ", email=" + email + ", twitter=" + twitter + ", facebook="
//				+ facebook + ", phone=" + phone + ", qualifiedForJobs="
//				+ qualifiedForJobs + ", blackouts=\n\t" + blackouts
//				+ ", assignments=\n\t" + assignments + "]";
		return printResource();
	}
	private String printResource(){
		Formatter f = new Formatter();
		try{
			f.format("%10s %10s\n %s\n%s\n%s\n%s\nqualified for:\n\t%s\nassignments:\n\t%s\n\n\n", firstName,lastName,"-",email,twitter,facebook,phone,qualifiedForJobs,assignments);
			return f.toString();
		}finally{
			f.close();
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
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
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
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
}
