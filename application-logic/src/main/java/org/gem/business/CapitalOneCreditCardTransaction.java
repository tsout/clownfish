package org.gem.business;

import java.util.Date;

import com.googlecode.jcsv.annotations.MapToColumn;

public class CapitalOneCreditCardTransaction implements CCTransaction {
	@MapToColumn(column=0)
	Date date;
	@MapToColumn(column=1)
	String creditCardID;
	@MapToColumn(column=2)
	String Description; 
	@MapToColumn(column=4)
	Double credit;
	@MapToColumn(column=3)
	Double debit;
	@MapToColumn(column=6)
	String category;
	
	/* (non-Javadoc)
	 * @see org.gem.business.CCTransaction#getDescription()
	 */
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	/* (non-Javadoc)
	 * @see org.gem.business.CCTransaction#getCredit()
	 */
	public Double getCredit() {
		return credit;
	}
	public void setCredit(Double value) {
		credit = value;
	}
	/* (non-Javadoc)
	 * @see org.gem.business.CCTransaction#getDebit()
	 */
	public Double getDebit() {
		return debit;
	}
	public void setDebit(Double value) {
		debit = value;
	}
	/* (non-Javadoc)
	 * @see org.gem.business.CCTransaction#getDate()
	 */
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	/* (non-Javadoc)
	 * @see org.gem.business.CCTransaction#getCategory()
	 */
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCreditCardID() {
		return creditCardID;
	}
	public void setCreditCardID(String creditCardID) {
		this.creditCardID = creditCardID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((credit == null) ? 0 : credit.hashCode());
		result = prime * result + ((debit == null) ? 0 : debit.hashCode());
		result = prime * result
				+ ((Description == null) ? 0 : Description.hashCode());
		result = prime * result
				+ ((category == null) ? 0 : category.hashCode());
		result = prime * result
				+ ((creditCardID == null) ? 0 : creditCardID.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		CapitalOneCreditCardTransaction other = (CapitalOneCreditCardTransaction) obj;
		if (credit == null) {
			if (other.credit != null)
				return false;
		} else if (!credit.equals(other.credit))
			return false;
		if (debit == null) {
			if (other.debit != null)
				return false;
		} else if (!debit.equals(other.debit))
			return false;
		if (Description == null) {
			if (other.Description != null)
				return false;
		} else if (!Description.equals(other.Description))
			return false;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (creditCardID == null) {
			if (other.creditCardID != null)
				return false;
		} else if (!creditCardID.equals(other.creditCardID))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CapitalOneCreditCardTransaction [Description=" + Description
				+ ", Credit=" + credit + ", Debit=" + debit + ", date=" + date
				+ ", category=" + category + ", creditCardID=" + creditCardID
				+ "]";
	} 

}
