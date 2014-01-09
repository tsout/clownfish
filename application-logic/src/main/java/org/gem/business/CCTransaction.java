package org.gem.business;

import java.util.Date;

public interface CCTransaction {

	public abstract String getDescription();

	public abstract Double getCredit();

	public abstract Double getDebit();

	public abstract Date getDate();

	public abstract String getCategory();

}