package org.gem.business.shiftscheduler.csv.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PersonCsvRecord extends AbstractCsvRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6131580189485350314L;
	private String personId;
	private String firstName;
	private String lastName;
	private String mobilePhone;
	private String officePhone;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy")
	private Date birthday;
	private String mailingAddress;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="[a-zA-Z0-9\\.\\_\\-]+@[a-zA-Z0-9\\-]+\\.[a-zA-Z0-9\\-]")
	private String emailAddress;
	String twitter;
	String facebook;

	public PersonCsvRecord() {
	}

	/**
	 * Person Copy Constructor
	 * 
	 * @LessonLearned PODAM does not handle copy constructors without special
	 *                treatment
	 * @return new Person Instance
	 */
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMailingAddress() {
		return mailingAddress;
	}

	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result
				+ ((mobilePhone == null) ? 0 : mobilePhone.hashCode());
		result = prime * result
				+ ((officePhone == null) ? 0 : officePhone.hashCode());
		result = prime * result
				+ ((personId == null) ? 0 : personId.hashCode());
		return result;
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

}
