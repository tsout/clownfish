package org.gem.business;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.gem.utils.csv.CSVUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.annotations.internal.ValueProcessorProvider;
import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.AnnotationEntryParser;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

public class CapitalOneCreditCardTransactionTest {
	List<CCTransaction> testTransactions = null;
	Statement statementUnderTest=null;

	@Before
	public void setUp() throws Exception {
		testTransactions = new ArrayList<CCTransaction>();
		testTransactions.addAll(CSVUtil.getAllRecordsFromCSV(CSVUtil.CSV_DATA_FILE));
		 statementUnderTest = new Statement (testTransactions);
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
	
		Calendar start = Calendar.getInstance(),end = Calendar.getInstance(); 
		start.set(2013, Calendar.JANUARY, 30);
		end.setTime( new Date());
		Double periodExpenses = statementUnderTest.getCumulativeExpensesFromPeriod(start,end);
		assertEquals("", 362, testTransactions.size());
		assertNotNull("Cumulative Expense is null",periodExpenses);
		assertFalse(periodExpenses<=0.0);
		System.out.println("Expenses" +periodExpenses);
	}

	@Test
	public void testcategorizeExpenses() throws Exception {
		
		Map<String,Double> expenseCategories = statementUnderTest.categorizeExpenses();
		
		assertNotNull("expense categories is null", expenseCategories);
		System.out.println(expenseCategories);
		
	}
	
}
