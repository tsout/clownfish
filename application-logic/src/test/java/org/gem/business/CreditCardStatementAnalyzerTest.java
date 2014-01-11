package org.gem.business;

import static org.junit.Assert.*;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gem.utils.csv.CSVUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class CreditCardStatementAnalyzerTest {
	private static final String TWO_COLUMN_FORMAT = "%-72s  %-30.30s%n";
	List<CCTransaction> testTransactions = null;
	CreditCardStatementAnalyzer statementUnderTest=null;
	

	@Before
	public void setUp() throws Exception {
		testTransactions = new ArrayList<CCTransaction>();
		testTransactions.addAll(CSVUtil.getAllRecordsFromCSV(CSVUtil.CSV_DATA_FILE));
		 statementUnderTest = new CreditCardStatementAnalyzer (testTransactions);
			 
		 printTestBreak();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCumlativeExpensesOverPeriod() {
	
		Calendar start = Calendar.getInstance(),end = Calendar.getInstance(); 
		start.set(2013, Calendar.JANUARY, 30);
		end.setTime( new Date());
		Double periodExpenses = statementUnderTest.getCumulativeExpensesFromPeriod(start,end);
		assertEquals("", 362, testTransactions.size());
		assertNotNull("Cumulative Expense is null",periodExpenses);
		assertFalse(periodExpenses<=0.0);
		
		System.out.printf(TWO_COLUMN_FORMAT, "Expenses",currencyFormat(periodExpenses));
	}

	public static String currencyFormat(Double moneyValue) {
		NumberFormat curFormatter = NumberFormat.getCurrencyInstance();
		return curFormatter.format(moneyValue);
	}

	@Test
	public void testcategorizeExpenses() throws Exception {
		
		Map<String,Double> expenseCategories = statementUnderTest.categorizeExpenses();
		
		assertNotNull("expense categories is null", expenseCategories);
		for(Map.Entry<String,Double> m: expenseCategories.entrySet() )
		{
			System.out.printf(TWO_COLUMN_FORMAT, m.getKey(), currencyFormat(m.getValue()));
			
		}
		
	}
	
	void printTestBreak()
	{
		System.out
				.println("\n--------------------------------------------------\n");
	}
	
}
