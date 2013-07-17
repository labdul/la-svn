package betfair.services.fileServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

/*
 * This function does the low level task of opening the CSV file and reading in data.
 */

public class ReadCSV {
	
	public String FILENAME;
	public char SEPARATOR= ',';
	public char QUOTECHAR = '\u0000';
	public int SKIPLINES = 0;
	public CSVReader Reader;
	
	/** This constructor will read the contents of a CSV file with the default separator of ',' 
	 * @param Filename : The file name with the full path to the file.
	 * @throws FileNotFoundException
	 */
	public ReadCSV(String Filename) {
		this.FILENAME = Filename;
	}
	
	/** This constructor will read contents of a CSV file with the passes separator
	 * @param Filename : The file name with the full path to the file.
	 * @param Separator : The separator character.
	 * @throws FileNotFoundException
	 */
	public ReadCSV(String Filename, char Separator){
		this.FILENAME = Filename;
		this.SEPARATOR = Separator;
	}
	
	/** This constructor will read contents of a CSV file with the passes separator (','/';')&amp; quote character ('"'/''').
	 * @param Filename : The file name with the full path to the file.
	 * @param Separator : The character that separates the columns on a line. 
	 * @param Quotechar : The character that is used to quote a value in column.
	 * @throws FileNotFoundException
	 */
	public ReadCSV(String Filename, char Separator, char Quotechar) {
		this.FILENAME = Filename;
		this.SEPARATOR = Separator;
		this.QUOTECHAR = Quotechar;
	}
	
	/** This constructor will read contents of a CSV file with the passes separator, quote character &amp; number of lines from top to skip.
	 * @param Filename : The file name with the full path to the file.
	 * @param Separator : The character that separates the columns on a line.
	 * @param Quotechar : The character that is used to quote a value in column.
	 * @param skipLines : Number of lines to skip from the top
	 * @throws FileNotFoundException
	 */
	public ReadCSV(String Filename, char Separator, char Quotechar, int skipLines) {
		this.FILENAME = Filename;
		this.SEPARATOR = Separator;
		this.QUOTECHAR = Quotechar;
		this.SKIPLINES = skipLines;	
	}	
	
	/** This is to read a CSV file to be passed onto DataProvider function of TestNG
	 * @return An instance of the same class with a predefined separator,quote character &amp; lines to skip.  
	 * @throws FileNotFoundException
	 */
	public ReadCSV skipHeader() {		
		SEPARATOR = ',';
		QUOTECHAR = '\u0000'; //NULL for char data type
		SKIPLINES = 1; //Skip the first line as its expected to be the header for the data in the CSV file.
		return this;
	}

	
	public List<String[]> ReadValues() throws IOException {
		List<String[]> lValues = null;
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FILENAME);

		InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		Reader = new CSVReader(inputStreamReader,SEPARATOR,QUOTECHAR,SKIPLINES);
		
		lValues = Reader.readAll();
		Reader.close();
		return lValues;
	}
	
	/** This will return values in a test data CVS file as an 2-dimensional array.
	 * @return returns the contents of the CSVReader object as a two-dimensional array.
	 * @throws IOException
	 */
	public String [][] ReadIncludedTestData() throws IOException {
		
		int count = 0, width=0;
		List<String[]> lValues = ReadValues();//Reader.readAll();

		//Counts the rows which is set to be included in the test
		for (String[] cValues : lValues) {
			if (cValues[0].equalsIgnoreCase("Y")){
				count++;
			}
			if (width == 0) 
				width = cValues.length-1;
		}

		//Initialise the Array with the count taken above
		String[][] Values = new String[count][width];

		//Read the data into the Array from the list
		count = 0;

		//New way to read the data - START
		for (String[] cValues : lValues) {
			if (cValues[0].equalsIgnoreCase("Y")){
				for (int wCount=0; wCount < width; wCount++){
					//Values[count] = cValues;
					Values[count][wCount] = cValues[wCount+1];
				}
				count++;
			}
		}
		//New way to read the data - END
		return Values;
	}
	
	/**
	 * This will return values in a test data CVS file as an list of object array.
	 * @return returns the contents of the CSVReader object as a two-dimensional array.
	 * @throws IOException
	 */
	public ArrayList<Object[]> ReadData() throws IOException {
	
		List<String[]> lValues = ReadValues();
		List<Object[]> rValues = new ArrayList<Object[]>();
		
		//Add the String values to object so that it can be returned as ArrayList<Object[]>
		for(String [] cValues:lValues) {
			rValues.add(cValues);
		}		
		return (ArrayList<Object[]>) rValues;
	}
	
	public String [] ReadHeader() throws IOException {
		
		//Reader = new CSVReader(new FileReader(FILENAME),SEPARATOR,QUOTECHAR,SKIPLINES);
		Reader = new CSVReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream(FILENAME)),SEPARATOR,QUOTECHAR,SKIPLINES);
		String Header[] = Reader.readNext();
		Reader.close();
		return Header;
	}
	
}