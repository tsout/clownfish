package org.gem.event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.gem.utils.AbstractPojo;

public class Person extends AbstractPojo {
	private String firstName;
	private String lastName;
	private UUID uuid;
	private String mobilePhone;
	private String workPhone;
	private String homePhone;

	private String primaryEmailAddress;
	private String primaryMailingAddressCountry;
	private String primaryMailingAddressState;
	private String primaryMailingAddressCity;
	private String primaryMailingAddressZip;
	private String primaryMailingAddressPOBox;
	private String primaryMailingAddressStreet;
	private String primaryMailingAddressStreetNumber;
	private String primaryMailingAddress;
	private Date birthday;

	Person() {

	}

	Person(UUID uuid) {
		if (this.uuid == null)
			this.uuid = UUID.fromString(uuid.toString());

		primaryEmailAddress = null;
		primaryMailingAddressCountry = null;
		primaryMailingAddressState = null;
		primaryMailingAddressCity = null;
		primaryMailingAddressZip = null;
		primaryMailingAddressPOBox = null;
		primaryMailingAddressStreet = null;
		primaryMailingAddressStreetNumber = null;
		primaryMailingAddress = null;
	}

	public String getPrimaryMailingAddress() {
		return primaryMailingAddress;
	}

	public void setPrimaryMailingAddress(String primaryMailingAddress) {
		this.primaryMailingAddress = primaryMailingAddress;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getPrimaryEmailAddress() {
		return primaryEmailAddress;
	}

	public void setPrimaryEmailAddress(String primaryEmailAddress) {
		this.primaryEmailAddress = primaryEmailAddress;
	}

	public String getPrimaryMailingAddressCountry() {
		return primaryMailingAddressCountry;
	}

	public void setPrimaryMailingAddressCountry(
			String primaryMailingAddressCountry) {
		this.primaryMailingAddressCountry = primaryMailingAddressCountry;
	}

	public String getPrimaryMailingAddressState() {
		return primaryMailingAddressState;
	}

	public void setPrimaryMailingAddressState(String primaryMailingAddressState) {
		this.primaryMailingAddressState = primaryMailingAddressState;
	}

	public String getPrimaryMailingAddressCity() {
		return primaryMailingAddressCity;
	}

	public void setPrimaryMailingAddressCity(String primaryMailingAddressCity) {
		this.primaryMailingAddressCity = primaryMailingAddressCity;
	}

	public String getPrimaryMailingAddressZip() {
		return primaryMailingAddressZip;
	}

	public void setPrimaryMailingAddressZip(String primaryMailingAddressZip) {
		this.primaryMailingAddressZip = primaryMailingAddressZip;
	}

	public String getPrimaryMailingAddressPOBox() {
		return primaryMailingAddressPOBox;
	}

	public void setPrimaryMailingAddressPOBox(String primaryMailingAddressPOBox) {
		this.primaryMailingAddressPOBox = primaryMailingAddressPOBox;
	}

	public String getPrimaryMailingAddressStreet() {
		return primaryMailingAddressStreet;
	}

	public void setPrimaryMailingAddressStreet(
			String primaryMailingAddressStreet) {
		this.primaryMailingAddressStreet = primaryMailingAddressStreet;
	}

	public String getPrimaryMailingAddressStreetNumber() {
		return primaryMailingAddressStreetNumber;
	}

	public void setPrimaryMailingAddressStreetNumber(
			String primaryMailingAddressStreetNumber) {
		this.primaryMailingAddressStreetNumber = primaryMailingAddressStreetNumber;
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

}
