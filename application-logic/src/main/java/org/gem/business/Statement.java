package org.gem.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Statement implements BalanceStatement {

	private List<CCTransaction> transactions=null;
	
	Statement (List<CCTransaction> transactions)
	{
		List<CCTransaction> records = new ArrayList<CCTransaction>(transactions);
		this.transactions = records;
		
	}
	private static Double addExpense(Double newExpense, Double lastBalance){
		
		return new Double(newExpense+lastBalance);
		
	}
	public Double getCumulativeExpensesFromPeriodByCategory( Calendar beginningDate, Calendar endingDate, String category)
	{
		return null;
	}
	
	public Double getCumulativeExpensesFromPeriod( Calendar beginningDate, Calendar endingDate)
	{
		if(transactions==null){
			throw new NullPointerException("statement transaction set has not been initialized")	;	
		}
		
		Double subtotal = new Double(0.0);
		for (CCTransaction t: transactions)
		{
			Calendar c = Calendar.getInstance(); 
			Date transactionDate = t.getDate();
//			System.out.println("transaction date"+transactionDate);
			c.setTime(transactionDate);
			
			if (c.after(beginningDate) && c.before(endingDate))
			{
				subtotal = addExpense(t.getDebit(), subtotal);
			}
		}
		
		System.out.println("subtotal"+subtotal);
		return subtotal;
	}

	public Double getPercentageOfExpensesByCategory(String categoryName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Map<String, Double> categorizeExpenses(List<CCTransaction>transactions) {
		// TODO Auto-generated method stub
		
		// find all expenses w/ same description, assign a name / code to each, subtotal Expenses, return map
		HashMap<String, Double> expenseCategories = new HashMap<String,Double>();
		
		for(CCTransaction t: transactions)
		{
			if (!expenseCategories.containsKey(t.getDescription()))
			{
				expenseCategories.put(t.getDescription(), t.getDebit());
			}
			else
			{
				Double lastBalance = expenseCategories.get(t.getDescription());
				expenseCategories.put(t.getDescription(), lastBalance+t.getCredit());
			}
		}
		return expenseCategories;
	}

	public Map<String, Double> categorizeExpenses() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
