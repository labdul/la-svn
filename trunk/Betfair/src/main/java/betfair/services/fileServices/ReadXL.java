package betfair.services.fileServices;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This will read .XLS/.XLSX files and return them as a string array.
 * @author jeapen
 *
 */
public class ReadXL {

    private Workbook workbook = null;
    private ArrayList<ArrayList<String>> ArrData = null;
    private int maxRowWidth = 0;
    private DataFormatter formatter = null;
    private FormulaEvaluator evaluator = null;
    /**
     * Identifies that the CSV file should obey Excel's formatting conventions
     * with regard to escaping certain embedded characters - the field separator,
     * speech mark and end of line (EOL) character
     */
    public static final int EXCEL_STYLE_ESCAPING = 0;

    /**
     * Identifies that the CSV file should obey UNIX formatting conventions
     * with regard to escaping certain embedded characters - the field separator
     * and end of line (EOL) character
     */
    public static final int UNIX_STYLE_ESCAPING = 1;
	//Record record;
	
    /**
     * @param strXLSName
     * @param strWorkbook
     * @param bolSkipHeader
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     * @throws IllegalArgumentException
     * @throws InvalidFormatException
     */
    /*
    public String [][] ConvertWorkbookToArray1(String strXLSName, String strWorkbook, boolean bolSkipHeader)
            throws FileNotFoundException, IOException, IllegalArgumentException, InvalidFormatException {

    	int length = 0, width=0;
    	// Open the workbook
    	this.openWorkbook(strXLSName);

    	// Convert it's contents into a 2 dimensional array
    	this.convertToArray(strWorkbook, bolSkipHeader);
    	
    	//ArrData.toArray();

    	//Find the row and columns for the Array
    	for (ArrayList<String> cValues : ArrData) {
    		length++;
    		if (width == 0) 
    			width = cValues.size()-1;
    	}

    	//Initialise the Array with the count taken above
    	String[][] Values = new String[length][width];

    	//Read the data into the Array from the list
    	length = 0;

    	//New way to read the data - START
    	for (ArrayList<String> cValues : ArrData) {
    		for (int wCount=0; wCount < width; wCount++){
    			Values[length][wCount] = cValues.get(wCount);
    		}
    		length++;
    	}
    	return Values;
    }*/
        
    public ArrayList<Object[]>  ConvertWorkbookToArray(String strXLSName, String strWorkbook, boolean bolSkipHeader)
            throws IOException, IllegalArgumentException, InvalidFormatException {

    	// Open the workbook
    	this.openWorkbook(strXLSName);
    	// Convert it's contents into a 2 dimensional array
    	this.convertToArray(strWorkbook, bolSkipHeader);
    	//Initialise a list of object array
    	List<Object[]> Values = new ArrayList<Object[]>();    	
    	
    	for (ArrayList<String> cValues : ArrData) {
    		Object[] obj = new Object[cValues.size()];
    		for (int i=0;i<cValues.size();i++){
    			obj[i] = cValues.get(i);
    		}
    		Values.add(obj);
    	}    	
    	return (ArrayList<Object[]>) Values;
    }
    

    /** Open an Excel workbook ready for conversion.
     * @param file
     * @throws FileNotFoundException
     * @throws IOException
     * @throws InvalidFormatException
     */
    private void openWorkbook(String file) throws FileNotFoundException,
                                           IOException, InvalidFormatException {
        InputStream fis = null;
        try {
        	//Load the file if its found in the project classpath
        	fis = this.getClass().getClassLoader().getResourceAsStream(file);

            // Open the workbook and then create the FormulaEvaluator and
            // DataFormatter instances that will be needed to, respectively,
            // force evaluation of forumlae found in cells and create a
            // formatted String encapsulating the cells contents.
            this.workbook = WorkbookFactory.create(fis);
            this.evaluator = this.workbook.getCreationHelper().createFormulaEvaluator();
            this.formatter = new DataFormatter(true);
        }
        finally {
            if(fis != null) {
                fis.close();
            }
        }
    }
    	
    /**
     * Called to convert the contents of the workbook passed as parameter into
     * a 2 dimensional array.
     * @param strWorkbook - Name of the workbook
     * @param bolSkipHeader
     */
    private void convertToArray(String strWorkbook, boolean bolSkipHeader) {
        Sheet sheet = null;
        Row row = null;
        int lastRowNum = 0;
        this.ArrData = new ArrayList<ArrayList<String>>();

        //System.out.println("Converting files contents to a Two-dimensional String Array.");
  
        sheet = this.workbook.getSheet(strWorkbook);
        if(sheet.getPhysicalNumberOfRows() > 0) {

        	// Note down the index number of the bottom-most row and
        	// then iterate through all of the rows on the sheet starting
        	// from the very first row - number 1 - even if it is missing.
        	// Recover a reference to the row and then call another method
        	// which will strip the data from the cells and build lines
        	// for inclusion in the resylting CSV file.
        	lastRowNum = sheet.getLastRowNum();
        	//If skipHeader is set to true, then set the starting row to 1, else 0
        	for(int j = (bolSkipHeader==true)? 1:0; j <= lastRowNum; j++) {
        		row = sheet.getRow(j);
        		this.rowToArr(row);
            }
        }
        
    }
    /**
     * Called to convert a row of cells into a line of data that will become the row of the ArrayList.
     *
     * @param row An instance of either the HSSFRow or XSSFRow classes that
     *            encapsulates information about a row of cells recovered from
     *            an Excel workbook.
     */
    private void rowToArr(Row row) {
        Cell cell = null;
        int lastCellNum = 0;
        ArrayList<String> ArrRow = new ArrayList<String>();

        // Check to ensure that a row was recovered from the sheet as it is
        // possible that one or more rows between other populated rows could be
        // missing - blank. If the row does contain cells then...
        if(row != null) {

            // Get the index for the right most cell on the row and then
            // step along the row from left to right recovering the contents
            // of each cell, converting that into a formatted String and
            // then storing the String into the csvLine ArrayList.
            lastCellNum = row.getLastCellNum();
            for(int i = 0; i < lastCellNum; i++) {
                cell = row.getCell(i);
                if(cell == null) {
                	ArrRow.add("");
                }
                else {
                    if(cell.getCellType() != Cell.CELL_TYPE_FORMULA) {
                    	ArrRow.add(this.formatter.formatCellValue(cell));
                    }
                    else {
                    	ArrRow.add(this.formatter.formatCellValue(cell, this.evaluator));
                    }
                }
            }
            // Make a note of the index number of the right most cell. This value
            // will later be used to ensure that the matrix of data in the CSV file
            // is square.
            if(lastCellNum > this.maxRowWidth) {
                this.maxRowWidth = lastCellNum;
            }
        }
        this.ArrData.add(ArrRow);
    }
}