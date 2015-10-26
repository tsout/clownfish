package org.gem.utils.csv.jackson;

import org.gem.event.Person;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "firstName", "lastName", "primaryEmailAddress",
		"mobilePhone", "homePhone", "twitter", "facebook" })
public abstract class PersonCSVMixin {

	@JsonProperty(required = true, value = "FirstName")
	abstract public String getFirstName();

	@JsonProperty(required = true, value = "LastName")
	abstract public String getLastName();

	@JsonProperty(required = true, value = "Email")
	abstract public String getPrimaryEmailAddress();

	@JsonProperty(required = true, value = "Twitter")
	abstract public String getTwitter();

	@JsonProperty(required = true, value = "Facebook")
	abstract public String getFacebook();

	@JsonProperty(required = true, value = "Mobile#")
	abstract public String getMobilePhone();

	@JsonProperty(required = true, value = "Home#")
	abstract public String getHomePhone();

}
