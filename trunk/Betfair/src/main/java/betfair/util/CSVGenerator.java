package betfair.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import au.com.bytecode.opencsv.CSVWriter;

public class CSVGenerator {
	
	
	 	public static String writecsv(int linesCount, String filename) {  
			String csvPath = (new File(".").getAbsolutePath().replace(".", "")) + filename;
			CSVWriter csvWriter = null;
			try {
				csvWriter = new CSVWriter(new FileWriter(csvPath), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER); 
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			List<String[]> data = new ArrayList<String[]>();
			data.add(new String[] {"BETFAIR ACCOUNT ID", "LIABILITY GROUP"});
			String randomStringId = "123456789";
			String randomStringGroups = "ABCDEFGHIJLMNO";
			Random randomGenerator = new Random();
			for (int i = 1; i < linesCount; i++) {
				String customerId = "";
				String groups = "";
				customerId += randomStringId.charAt(randomGenerator.nextInt(randomStringId.length()));
				customerId += randomStringId.charAt(randomGenerator.nextInt(randomStringId.length()));
				customerId += randomStringId.charAt(randomGenerator.nextInt(randomStringId.length()));
				customerId += randomStringId.charAt(randomGenerator.nextInt(randomStringId.length()));
				groups += randomStringGroups.charAt(randomGenerator.nextInt(randomStringGroups.length()));
				data.add(new String[] {customerId, groups});
			}
			csvWriter.writeAll(data);
			try {
				csvWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

			return csvPath;
		}

	}