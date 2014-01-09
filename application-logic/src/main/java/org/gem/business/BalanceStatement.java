package org.gem.business;

import java.util.Calendar;
import java.util.Map;

public interface BalanceStatement extends Analyzable{
	public Double getCumulativeExpensesFromPeriod( Calendar beginningDate, Calendar endingDate);
	public Double getPercentageOfExpensesByCategory(String categoryName);
	public Map<String,Double> categorizeExpenses();
	public Double getCumulativeExpensesFromPeriodByCategory( Calendar beginningDate, Calendar endingDate, String category);
}

