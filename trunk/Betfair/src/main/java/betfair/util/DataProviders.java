package betfair.util;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;

import betfair.services.DBService;
import betfair.services.MiscServices;
import betfair.services.fileServices.*;

/*
 * This class will pass the data read from the CSV files to the Test function requesting data.
 * It will dynamically read the data from the csv file which has same name as that of the 
 * calling method's class.
 */

public class DataProviders {
	
	DBService dbservice = new DBService();
	ReadProperties DBProp = new ReadProperties("db.properties"); 
	
	@DataProvider(name = "CSVDriver")
	public static Iterator<Object[]> DataStorage(Method m ) throws FileNotFoundException {
		String testDataFolder = "test-data";
		String fileExtension = ".csv";
		String className = m.getDeclaringClass().getSimpleName();
		String methodName = m.getName();
		String fileSeparator = System.getProperty("file.separator");
		
		String fileFullPath = testDataFolder + fileSeparator + className + fileSeparator + methodName + fileExtension;
		
		List<Object[]> Values = new ArrayList<Object[]>();
		ReadCSV obj = new ReadCSV(fileFullPath).skipHeader();
		try {
			Values = MiscServices.StripExcludedData(obj.ReadData());
		} catch (IOException e) {
			System.out.println("File " + fileFullPath + "Not Found!!! " + e);
		}
		return Values.iterator();
	}

	@DataProvider(name = "XLDriver")
    public static Iterator<Object[]> XLDataProvider (Method m) throws IllegalArgumentException, InvalidFormatException, IOException {
        //Get the input file path from the ITestContext
    	String ClassName = "test-data"+ System.getProperty("file.separator")+m.getDeclaringClass().getSimpleName()+".xlsx";
		String MethodName = m.getName();		  
		
        ReadXL readXL = new ReadXL();
        
        //Get a list of String file content (line items) from the test file.
        ArrayList<Object[]> testData = MiscServices.StripExcludedData(readXL.ConvertWorkbookToArray(ClassName, MethodName, true));

        //return the iterator - testng will initialise the test class and calls the 
        //test method with each of the content of this iterator.
        return testData.iterator(); 
    }
	
	/**Data provider method that will read an SQLite table of the same name as the class that calls this method. 
	 * @param m : The name of the method that calls this method
	 * @return The values read from the table in a two dimensional array.
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	@DataProvider(name = "SQLiteDriver")
	public Object[][] SQLiteDataStorage(Method m) throws FileNotFoundException, ClassNotFoundException, SQLException {
		String [][] Values = null;
		String SQLiteDB = System.getProperty("user.dir") + DBProp.getValue("SQLLITE_DB_PATH")+ DBProp.getValue("SQLLITE_TEST_DATA_DB");
		//Get the name of the Test class
		String ClassName = m.getDeclaringClass().getSimpleName();
		//Read all values from a table that has the name of the Test class 
		String TestData = "select * from " + ClassName;
		Connection Conn = dbservice.ConnectSQLiteDB(SQLiteDB);
	    Values = dbservice.ReturnDataAsArray(Conn, TestData);
		return (Object[][]) Values;
	}
}