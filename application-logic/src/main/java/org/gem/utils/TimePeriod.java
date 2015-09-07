package org.gem.utils;

import java.util.Date;
import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

public class TimePeriod {
	Date periodStartDateTime;
	Date periodEndDateTime;

	/*
	 * For Entity Generators only
	 * package-protected
	 */
	TimePeriod() {
		//noop
	}

	public TimePeriod(Date start, Date end) {
		initialize(start, end);
	}

	private void initialize(Date start, Date end) {
		this.periodStartDateTime = (Date) start.clone();
		this.periodEndDateTime = (Date) end.clone();
	}

	public TimePeriod(Calendar start, Calendar end) {
		initialize(start.getTime(), end.getTime());
	}

	public Boolean conflictsWith(TimePeriod subjectPeriod) {
		Boolean periodStartIsBetween = DateUtils.isBetween(
				this.periodStartDateTime, subjectPeriod.periodStartDateTime,
				subjectPeriod.periodEndDateTime);
		Boolean periodEndIsBetween = DateUtils.isBetween(
				this.periodEndDateTime, subjectPeriod.periodStartDateTime,
				subjectPeriod.periodEndDateTime);
		Boolean periodStartEqual = this.periodStartDateTime
				.equals(subjectPeriod.periodStartDateTime);
		Boolean periodEndEqual = this.periodEndDateTime
				.equals(subjectPeriod.periodEndDateTime);

		// System.out.println("period start conflict "+periodStartIsBetween
		// +" "+ DateUtils.printDateComparison(this.periodStart,
		// subjectPeriod.periodStart, subjectPeriod.periodEnd));
		// System.out.println("period end conflict "+periodEndIsBetween+" "+
		// DateUtils.printDateComparison(this.periodEnd,
		// subjectPeriod.periodStart, subjectPeriod.periodEnd));
		return (periodStartEqual || periodStartIsBetween || periodEndIsBetween || periodEndEqual);
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((periodEndDateTime == null) ? 0 : periodEndDateTime
						.hashCode());
		result = prime
				* result
				+ ((periodStartDateTime == null) ? 0 : periodStartDateTime
						.hashCode());
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
		TimePeriod other = (TimePeriod) obj;
		if (periodEndDateTime == null) {
			if (other.periodEndDateTime != null)
				return false;
		} else if (!periodEndDateTime.equals(other.periodEndDateTime))
			return false;
		if (periodStartDateTime == null) {
			if (other.periodStartDateTime != null)
				return false;
		} else if (!periodStartDateTime.equals(other.periodStartDateTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return PojoUtils.printPojo(this);
	}

	@JsonFormat (shape=Shape.STRING, pattern="MM-dd-yyy HH:MM")
	public Date getStartDateTime() {
		return periodStartDateTime;
	}

	public void setStartDateTime(Date periodStartDateTime) {
		this.periodStartDateTime = periodStartDateTime;
	}

	@JsonFormat (shape=Shape.STRING, pattern="MM-dd-yyy HH:MM")
	public Date getEndDateTime() {
		return periodEndDateTime;
	}

	public void setEndDateTime(Date periodEndDateTime) {
		this.periodEndDateTime = periodEndDateTime;
	}
}
