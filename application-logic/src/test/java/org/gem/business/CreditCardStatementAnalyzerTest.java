package org.gem.business;

import static org.junit.Assert.*;

import java.io.Console;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gem.utils.csv.CreditCardCsvProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class CreditCardStatementAnalyzerTest {
	private static final String TWO_COLUMN_FORMAT = "%-72s  %-30.30s%n";
	List<CCTransaction> testTransactions = null;
	CreditCardStatementAnalyzer statementUnderTest=null;
	private static Console console = System.console(); 
	

	@Before
	public void setUp() throws Exception {
		testTransactions = new ArrayList<CCTransaction>();
		testTransactions.addAll(CreditCardCsvProcessor.getAllCreditCardRecordsFromCSV());
		 statementUnderTest = new CreditCardStatementAnalyzer (testTransactions);
			 
		 printTestBreak();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test 
	public void inputOutput(){
		String test  = promptAndCaptureInput(); 
		while (test.equals("exit")){
			System.out.println("You typed:/t "+test);
			test = promptAndCaptureInput(); 
		}
	}

	private String promptAndCaptureInput() {
		System.out.println("Type something");		
		console.writer().write("here is something");
		console.writer().flush();
		return console.readLine();
	}
	@Test
	public void testCumlativeExpensesOverPeriod() {
	
		print2013Expenses();
		print2014Expenses();
	}
	
	private void print2013Expenses(){
		Calendar start = Calendar.getInstance(),end = Calendar.getInstance(); 
		start.set(2013, Calendar.JANUARY, 1);
		end.set(2013, Calendar.DECEMBER, 31);
		Double periodExpenses = statementUnderTest.getCumulativeExpensesFromPeriod(start,end);
		System.out.printf(TWO_COLUMN_FORMAT, "2013 Expenses",currencyFormat(periodExpenses));
		
	}
	
	private void print2014Expenses(){
		Calendar start = Calendar.getInstance(),end = Calendar.getInstance(); 
		start.set(2014, Calendar.JANUARY, 1);
		end.set(2014, Calendar.DECEMBER, 31);
		Double periodExpenses = statementUnderTest.getCumulativeExpensesFromPeriod(start,end);
		System.out.printf(TWO_COLUMN_FORMAT, "2014 Expenses",currencyFormat(periodExpenses));
		
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
