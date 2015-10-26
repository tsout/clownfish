package org.gem.utils.csv;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.gem.business.CapitalOneCreditCardTransaction;

import com.googlecode.jcsv.CSVStrategy;
import com.googlecode.jcsv.annotations.internal.ValueProcessorProvider;
import com.googlecode.jcsv.reader.CSVEntryParser;
import com.googlecode.jcsv.reader.CSVReader;
import com.googlecode.jcsv.reader.internal.AnnotationEntryParser;
import com.googlecode.jcsv.reader.internal.CSVReaderBuilder;

/**
 * Read Credit Card Transactions from a CSV file.  
 * Depends on {@link clownfish.properties} file to define path of csv file 
 * @author --
 * 
 */
public class CreditCardCsvProcessor {
	
	private static Reader csvFileReader;  
	
	public static List<CapitalOneCreditCardTransaction> getAllCreditCardRecordsFromCSV() throws Exception
	{
		try {
			CSVReader<CapitalOneCreditCardTransaction> csvCreditCardTransactionReader = CreditCardCsvProcessor.getCreditCardCSVReader();
			List<CapitalOneCreditCardTransaction> transactions = csvCreditCardTransactionReader.readAll();
			return transactions;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}

	private static CSVReader<CapitalOneCreditCardTransaction> getCreditCardCSVReader()
			throws IOException, Exception, FileNotFoundException {
		String filePath = GoogleJcsvProvider.getClownFishCsvDataPath();
		
		csvFileReader= new FileReader(filePath);
				
		ValueProcessorProvider provider = GoogleJcsvProvider.getCommonCsvStringValueProcessorProvider();
		
		CSVEntryParser<CapitalOneCreditCardTransaction> entryParser = new AnnotationEntryParser<CapitalOneCreditCardTransaction>(CapitalOneCreditCardTransaction.class, provider);
		CSVReader<CapitalOneCreditCardTransaction> csvCreditCardTransactionReader = new CSVReaderBuilder<CapitalOneCreditCardTransaction>(csvFileReader).entryParser(entryParser).strategy(CSVStrategy.UK_DEFAULT).build();
		return csvCreditCardTransactionReader;
	}

}
