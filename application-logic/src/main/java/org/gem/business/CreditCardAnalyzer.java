package org.gem.business;


import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


import org.gem.utils.CommandLineUtility;
import org.gem.utils.csv.CSVUtil;

import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;
import com.googlecode.jcsv.writer.CSVWriter;
import com.googlecode.jcsv.writer.internal.CSVWriterBuilder;

public class CreditCardAnalyzer {
	private static final String VENDOR_CATEGORIES_CSV = "%UserProfile%\\Desktop\\VendorCategories.csv";

	private static Logger logger = Logger.getLogger(CreditCardAnalyzer.class
			.getName());

	private static Console console = System.console();

	private static String description = "The credit card analyzer reviews provided credit card statements (.csv files) and then allows a user to interactively categorize each transaction in the statement. Categories allow the analyzer to create reports for specific budget categories.";
	private static String title = "Credit Card Analyzer";

	private static final String TWO_COLUMN_FORMAT = "%-50s  %-30.30s%n";
	private static List<CCTransaction> transactions = null;
	private static CreditCardStatementAnalyzer ccAnalyzer = null;

	public static void main(String[] args) throws IOException {
		welcome(title, description);
		String test = promptAndCaptureInput();
		while (!test.equals("exit")) {
			test = promptAndCaptureInput();
		}
	}

	private static String promptAndCaptureInput() {
		return promptAndCaptureInput(null);
	}

	private static void process(String input) {
		if (input.equals("1")) {
			System.out.println("printing transactions");
			CommandLineUtility.printSectionLine();
			printTransactions();
		} else if (input.equals("2")) {
			System.out.println("itemizing");
			CommandLineUtility.printSectionLine();
			itemize();

		} else if (input.equals("3")) {
			System.out.println("categorizing .. ");
			CommandLineUtility.printSectionLine();
			categorize();

		}
		else if (input.equals("4")) {
			System.out.println("Edit Existing Categories ");
			CommandLineUtility.printSectionLine();
			editExistingCategories(); 

		}
		else if (input.equals("5")) {
			System.out.println("Show Vendors by Category.. ");
			CommandLineUtility.printSectionLine();
			showVendorsByCategory();
		} 
		else if (input.equals("6")) {
			System.out.println("Exiting .. ");
			CommandLineUtility.printSectionLine();
			System.exit(0);

		}
		else if (input.equals("exit")) {
			System.out.println("Exiting .. ");
			System.exit(0);
		} else {
		}
		{

		}
		printMainMenuOptions();

	}

	private static void showVendorsByCategory() {
		Set<String> categories = new HashSet<String>();
		
		try {
			HashMap<String, String> existingCategories = readExistingCategoriesFromFile();
			
			for(Map.Entry<String, String> m : existingCategories.entrySet()){
				categories.add(m.getValue());
			}
			
			for(String c: categories){
				if(!c.equals("")){
					System.out.println("Category: "+c);
					for (Map.Entry<String, String> m : existingCategories.entrySet())
					{
						if (m.getValue().equals(c)){
							System.out.println("\t"+m.getKey());
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void editExistingCategories() {
		try {
			HashMap<String, String> existingCategories = readExistingCategoriesFromFile();
			Integer x = 0; 
			for(Map.Entry<String, String> m: existingCategories.entrySet()){
				System.out.println("[#"+(x++)+"]\t"+ m);
			}
			String response = promptAndCaptureInput("Choose a category mapping to edit (by #): ");
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private static void welcome(String title, String description) {
		CommandLineUtility.printHeader();
		System.out.println("Application Title:");
		System.out.println("\t" + title);
		CommandLineUtility.printSectionLine();
		System.out.println("Description:");
		System.out.println("\t" + description);
		printMainMenuOptions();
	}

	private static void printMainMenuOptions() {

		CommandLineUtility.printHeader();
		System.out.println("[1] Print Transasctions");
		System.out.println("[2] itemizing");
		System.out.println("[3] categorize");
		System.out.println("[4] Exit existing categories");
		System.out.println("[5] Exit");
		CommandLineUtility.printFooter();

	}

	private static void printTransactions() {
		setUp();
		print2013Expenses();
		print2014Expenses();
	}

	private static void setUp() {
		try {
			transactions = new ArrayList<CCTransaction>();
			transactions.addAll(CSVUtil
					.getAllRecordsFromCSV(CSVUtil.CSV_DATA_FILE));
			ccAnalyzer = new CreditCardStatementAnalyzer(transactions);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	private static void print2013Expenses() {
		Calendar start = Calendar.getInstance(), end = Calendar.getInstance();
		start.set(2013, Calendar.JANUARY, 1);
		end.set(2013, Calendar.DECEMBER, 31);
		Double periodExpenses = ccAnalyzer.getCumulativeExpensesFromPeriod(
				start, end);
		System.out.printf(TWO_COLUMN_FORMAT, "2013 Expenses",
				currencyFormat(periodExpenses));

	}

	private static void print2014Expenses() {
		Calendar start = Calendar.getInstance(), end = Calendar.getInstance();
		start.set(2014, Calendar.JANUARY, 1);
		end.set(2014, Calendar.DECEMBER, 31);
		Double periodExpenses = ccAnalyzer.getCumulativeExpensesFromPeriod(
				start, end);
		System.out.printf(TWO_COLUMN_FORMAT, "2014 Expenses",
				currencyFormat(periodExpenses));

	}

	public static String currencyFormat(Double moneyValue) {
		NumberFormat curFormatter = NumberFormat.getCurrencyInstance();
		return curFormatter.format(moneyValue);
	}

	public static void itemize() {
		setUp();
		Map<String, Double> expenseCategories = ccAnalyzer.categorizeExpenses();

		for (Map.Entry<String, Double> m : expenseCategories.entrySet()) {
			System.out.printf(TWO_COLUMN_FORMAT, m.getKey(),
					currencyFormat(m.getValue()));

		}
	}

	public static void categorize() {
		setUp();
		HashMap<String, String> categoryVendorPair = new HashMap<String, String>();
		if (!promptUseExistingCategories()) {
			Map<String, Double> expenseCategories = ccAnalyzer
					.categorizeExpenses();
			for (Map.Entry<String, Double> m : expenseCategories.entrySet()) {
				requestCategoryName(categoryVendorPair, m);
			}

			System.out.println(categoryVendorPair);
			saveCategoriesToCSV(categoryVendorPair);
		} else {
			try {
				HashMap<String, String> existingCategories = readExistingCategoriesFromFile();
				Map<String, Double> expenseCategories = ccAnalyzer
						.categorizeExpenses();
				for (Map.Entry<String, Double> m : expenseCategories.entrySet()) {
					if (!existingCategories.containsKey(m.getKey())) {
						requestCategoryName(categoryVendorPair, m);
					}
					else if (existingCategories.get(m.getKey()).equals("")){
//						String response = promptAndCaptureInput("Vendor: '"+m.getKey()+"' does not have a category assigned to it. Would you like to give it one? [Y/N]");
//						if(isYes(response)){
							requestCategoryName(categoryVendorPair,m);
//						};
					}
				}

				System.out.println(categoryVendorPair);
				saveCategoriesToCSV(categoryVendorPair);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static HashMap<String, String> readExistingCategoriesFromFile()
			throws FileNotFoundException, IOException {
		HashMap<String, String> existingCategories = new HashMap<String, String>();
		CSVReader<String[]> reader = CSVReaderBuilder
				.newDefaultReader(new FileReader(VENDOR_CATEGORIES_CSV));
		for (String[] s : reader) {
			existingCategories.put(s[0], s[1]);

		}

		reader.close();
		return existingCategories;
	}

	private static void requestCategoryName(
			HashMap<String, String> categoryVendorPair,
			Map.Entry<String, Double> m) {
		String categoryName = promptAndCaptureInput("What category does'"
				+ m.getKey()
				+ "' belong to? Type any category name (e.g. 'food') ");
		String vendorName = m.getKey();
		categoryVendorPair.put( vendorName, categoryName);
	}

	private static boolean promptUseExistingCategories() {
		String response = promptAndCaptureInput("Use existing budget categories? [Y/N].  Using existing categories will report expenses defined previously");
		return isYes(response);
	}

	private static boolean isYes(String response) {
		if (response.equals("Y") || response.equals("y")
				|| response.equals("yes")) {
			return true;
		}
		return false;
	}

	private static void saveCategoriesToCSV(
			HashMap<String, String> categoryVendorPair) {

		try {
			FileWriter fw;
			fw = new FileWriter(VENDOR_CATEGORIES_CSV, true);
			CSVWriter<String[]> csvWriter = CSVWriterBuilder
					.newDefaultWriter(fw);
			for (Map.Entry<String, String> m : categoryVendorPair.entrySet()) {
				csvWriter.write(new String[] { m.getKey(), m.getValue() });
			}
			csvWriter.flush();
			csvWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static String promptAndCaptureInput(String interogative) {

		if (interogative != null) {
			System.out.println("");
			System.out.println(interogative);
			System.out.println("");
		}
		System.out.print("analyzer>");
		String input = null;
		input = console.readLine();
		process(input);
		return input;
	}

}
