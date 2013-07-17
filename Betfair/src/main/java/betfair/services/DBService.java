package betfair.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import betfair.services.fileServices.ReadCSV;


public class DBService {
	
	public final static String TABLE_EXISTS_IN_SQLITEDB_QRY = "SELECT count(*) FROM sqlite_master WHERE name='%s'";
	
	/** Used to create a connection to an SQLite DB loaded in the memory (no physical copy will exists)
	 * @return The connection object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection ConnectSQLiteDB() throws ClassNotFoundException, SQLException {
		
		Connection conn;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:");		
		return conn;
	}	
		
	/** This will create a connection to the SQLite DB passed as parameter.
	 * @param DBName : The full path to the SQLite DB
	 * @return The connection object
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection ConnectSQLiteDB(String dbname) throws ClassNotFoundException, SQLException {
		
		Connection conn;
		Class.forName("org.sqlite.JDBC");
		conn = DriverManager.getConnection("jdbc:sqlite:" + dbname);		
		return conn;
	}
	
	/** This method will create a connection to the CVS file with supplied folder and connection property, only one property at the moment.
	 * @param PreReqFolder : Folder in which the CSV files are placed
	 * @param ConPropType : Property type to be passed onto the connection string
	 * @param ConProperties : Property value to be passed onto the connection string
	 * @return Connection object
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	
	public Connection ConnectCSV(String PreReqFolder, String ConPropType, String ConProperties) throws SQLException, ClassNotFoundException {		
		
		// load the driver into memory
		Class.forName("org.relique.jdbc.csv.CsvDriver");
		Connection conn;
		
	    // create a connection. PreReqFolder be the directory in which the .csv files are held		
		if (ConPropType==null || ConPropType=="" || ConProperties==null || ConProperties == "") {
			conn = DriverManager.getConnection("jdbc:relique:csv:" + PreReqFolder);
		} 
		else {
			Properties props = new Properties();
			// Define column data types here.
			props.put(ConPropType, ConProperties);
			conn = DriverManager.getConnection("jdbc:relique:csv:" + PreReqFolder,props);
		}
	    return conn;
	}	
	
	/** This is a wrapper that will run an SQL query and return the Result set.
	 * @param conn : The connection object to be used for running the SQL query
	 * @param QueryString : The SQL query that you intend to run.
	 * @return Result set object with the results of SQL query passed as Query string.
	 * @throws SQLException
	 */
	public ResultSet QueryData(Connection conn, String querystring) throws SQLException {

		//Create a Statement object to execute the query with and return the query results
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(querystring);
	}
	
	/** 
	 * Determines the number of rows in a <code>ResultSet</code>. Upon exit, if the cursor was not 
	 * currently on a row, it is just before the first row in the result set (a call to 
	 * {@link ResultSet#next()} will go to the first row). 
	 * @param set The <code>ResultSet</code> to check (must be scrollable). 
	 * @return The number of rows. 
	 * @throws SQLException If the <code>ResultSet</code> is not scrollable.
	 */  
	public int getRowCount(ResultSet set) throws SQLException  
	{  
		int rowCount;  
	   	int currentRow = set.getRow();            // Get current row  
	   	rowCount = set.last() ? set.getRow() : 0; // Determine number of rows  
	   	if (currentRow == 0)                      // If there was no current row  
		   set.beforeFirst();                     // We want next() to go to first row  
	   	else                                      // If there WAS a current row  
		   set.absolute(currentRow);              // Restore it  
	   	return rowCount;  
	}  
	
	/**This method will return records in a ResultSet as a two-dimensional array
	 * @param conn : The connection object to the database
	 * @param querystring : The query string used to query the data
	 * @return The values returned in ResultSet as a two dimensional array
	 * @throws SQLException
	 */
	public String [][] ReturnDataAsArray(Connection conn, String querystring) throws SQLException {
		
		String [] [] Values;
		int Rows = 0;
		ResultSet rsData = QueryData(conn, querystring);
		ResultSetMetaData rsmd = rsData.getMetaData();
		int Columns = rsmd.getColumnCount();
		Values = new String [getRowCount(rsData)] [Columns];    
		
	    while (rsData.next()) {
	    	for (int i=1;i<=Columns;i++)
	    		Values[Rows][i] = rsData.getString(i);
	    	Rows++;
	    }
	    return Values;
	}
	
	/** This will check if a table exists in an SQLite database
	 * @param conn : the connection to the SQLite DB.	
	 * @param tablename : Name of the table whose existence is to be checked
	 * @return <b>true</b> if found, else <b>false</b>.
	 * @throws SQLException
	 */
	public boolean TableExistsinSQLiteDB(Connection conn, String tablename) throws SQLException {
		
        String ps = String.format(TABLE_EXISTS_IN_SQLITEDB_QRY, tablename);
        
        //Checking if the table exists, if not then return false, else true
        ResultSet rsTableExists = QueryData(conn, ps);
        rsTableExists.next();
        
        if (!(rsTableExists.getInt(1)==0)) {
        	rsTableExists.close();
        	return true;
        }
        else {
        	rsTableExists.close();
        	return false;
        }        
	}
	
	/** This will return all the columns in the passed table.
	 * @param conn : The connection to the database
	 * @param tablename : Name of the table
	 * @return The columns in the table
	 * @throws SQLException
	 */
	public String [] GetTableColumns (Connection conn, String tablename) throws SQLException {
		
		int NoOfCols=0;
		List <String> ColumnName = new ArrayList<String>();
		DatabaseMetaData meta = conn.getMetaData();
        ResultSet rsColumns = meta.getColumns(null, null, tablename.toLowerCase(), null);

        while(rsColumns.next()) { 
        	ColumnName.add(rsColumns.getString("COLUMN_NAME"));
        	NoOfCols++;
        }
        rsColumns.close();
        
        String [] ColName = new String [NoOfCols];
        //rsColumns.first();
        
        for (int i=0;i<NoOfCols;i++) { 
        	ColName [i] = ColumnName.get(i);
        	//System.out.println("ColName ["+ i + "] " + ColName [i]);
        	//rsColumns.next();
        }
        
        //rsColumns.close();
        return ColName;
	}
	
	/**Return the column names fetched in a RecordSet
	 * @param conn : The Connection object to the database
	 * @param querystring : The query string that is used to retrieve the data
	 * @return The column names as an one dimensional array
	 * @throws SQLException
	 */
	public String [] GetColfromResultSet (Connection conn, String querystring) throws SQLException {
		
		List <String> ColumnName = new ArrayList<String>();
		ResultSet rsColumns = QueryData(conn, querystring);
		ResultSetMetaData rsmd = rsColumns.getMetaData();
		int NoOfCols = rsmd.getColumnCount();
		
		//Add the columns names returned by the ResultSet to a ArrayList
		for (int i=1;i<=NoOfCols;i++) {
			ColumnName.add(rsmd.getColumnName(i));
		}		
        rsColumns.close();
        
        String [] ColName = new String [NoOfCols];
        for (int i=0;i<NoOfCols;i++) { 
        	ColName [i] = ColumnName.get(i);
        	//System.out.println("ColName ["+ i + "] " + ColName [i]);
        }
        return ColName;
	}	
	
	/**
	 * @param conn
	 * @param tablename
	 * @param colname
	 * @return
	 * @throws SQLException
	 */
	public boolean IsColAvailable (Connection conn, String tablename, String colname) throws SQLException {
		
		DatabaseMetaData meta = conn.getMetaData();
        ResultSet rsColumns = meta.getColumns(null, null, tablename.toLowerCase(), null);

        while(rsColumns.next()) { 
        	if (rsColumns.getString("COLUMN_NAME").equalsIgnoreCase(colname))
        		return true;
        }
        rsColumns.close();
        return false;
	}
	
	/**
	 * @param conn
	 * @param tablename
	 * @param colname
	 * @return
	 * @throws SQLException
	 */
	public String GetColDataType (Connection conn, String tablename, String colname) throws SQLException {
		
		DatabaseMetaData meta = conn.getMetaData();
        ResultSet rsColumns = meta.getColumns(null, null, tablename.toLowerCase(), null);
        
        while(rsColumns.next()) { 
        	if (rsColumns.getString("COLUMN_NAME").equalsIgnoreCase(colname))
        		return rsColumns.getString("TYPE_NAME");
        }
        rsColumns.close();
        return null;
	}
	
	/** This will create a table with the CSV file name and import data in the file into the newly created table.
	 * @param conn : The connection to the database.
	 * @param csvfile : The CSV file which contains data
	 * @return <b>true</b> if successful, else <b>false</b>
	 * @throws Exception
	 */
	public boolean ImportTableFromCSV(Connection conn, String csvfile) throws Exception {
		
		String TableName = GetTableName(csvfile);
		ReadCSV readHeader = new ReadCSV(csvfile);
		String[] Header = readHeader.ReadHeader();
		
		ReadCSV readData = new ReadCSV(csvfile).skipHeader();
		List<String[]> Values = readData.ReadValues();
		
		if (CreateTable (conn, TableName, Header)){
			InsertValues(conn, TableName, Values);
			return true;
		}
		else
			return false;		
		
	}
	
	/** A wrapper function to strip off the file name from the whole CSV file path
	 * @param csvfile : The CSV file with its absolute path in the file system.
	 * @return The name of the CSV file.
	 */
	public String GetTableName(String csvfile){

		return csvfile.substring(csvfile.lastIndexOf(System.getProperty("file.separator"))+1).replace(".csv", "");
	}
	
	/**Creates a table using the passed connection object.
	 * @param connection : The connection to the database in which the table is to be created.
	 * @param tablename : Name of the table to be created.
	 * @param header : Columns for the table
	 * @return <b>True</b> if the table was created successfully, else <b>false</b>.
	 * @throws IOException
	 * @throws SQLException
	 */
	public boolean CreateTable(Connection connection, String tablename, String[] header) throws IOException, SQLException {
		
		int NoOfCol = header.length;		
		//Exit if the CSV file does not have any header
		if (NoOfCol==0)
			return false;

		//Build the table name by stripping off the path and the file extension (The below code is for windows only) 
		//String TableName = csvfile.substring(csvfile.lastIndexOf('\\')+1).replace(".csv", "");
		Statement statement = connection.createStatement();
		
		//Drop the table if it exists so that a new table can be created with the updated schema if any
		String ps = "drop table if exists "+ tablename;		
		statement.executeUpdate(ps);

		//Build the new create table statement and insert the table. 
		ps = "create table " + tablename + " (";
		
		for( int i=0 ; i< NoOfCol ; i++ ) {
			if( i > 0 )  ps += ", ";	
			ps += String.format("%s %s", header[i],GetDataType(header[i]));
		}
		ps += ")";
		
		statement.executeUpdate(ps);
		return true;
	}
	
	/**This can be used to add columns to a table using the passed connection object.
	 * @param connection : The connection object
	 * @param tablename : Name of the table
	 * @param columns : New columns as a String Array
	 * @return <b>True</b> if columns were added to the table successfully, else <b>false</b>.
	 * @throws IOException
	 * @throws SQLException
	 */
	public boolean AddColumnstoTable(Connection connection, String tablename, String [] columns) throws IOException, SQLException {
		
		int NoOfCol = columns.length;
		
		//Exit if the CSV file does not have any header
		if (NoOfCol==0)
			return false;

		Statement statement = connection.createStatement();
		
		//Build the new create table statement and insert the table. 
		String ps;
		
		for( int i=0 ; i< NoOfCol ; i++ ) {
			ps = String.format("alter table %s add %s %s", tablename, columns[i], GetDataType(columns[i]));
			statement.executeUpdate(ps);
		}
		return true;
	}
	
	/** It will return the Data type for a column in a CSV file based on the first character of the column name <br>
	 * <li><b>i/I</b>: Will be returned INT data type <br> </li>
	 * <li><b>s/S/d/D/</b>: Will be returned as TEXT data type <br> </li>
	 * <li><b>r/R/f/F/</b>: Will be returned as REAL data type <br> <br> </li>
	 * If the columnName starts with a character other than the above, it will be returned as TEXT </p>
	 * @param columnname : Name of the column
	 * @return The Data type for the column
	 */
	public String GetDataType(String columnname) {
		
		String dataType;
		char dType = columnname.charAt(0);
		
		if (dType == 'i' || dType == 'I')
			dataType = "INT";
		else if (dType == 's' || dType == 'd' || dType == 'S' || dType == 'D')
			dataType = "TEXT";
		else if (dType == 'r' || dType == 'f' || dType == 'R' || dType == 'F')
			dataType = "REAL";
		else
			dataType = "TEXT";
		return dataType;
	}
	
	
    /**This Method will insert the values passed into the table passed as a parameter, it can take conditional statements while taking inserting data. 
     * @param connection : The connection to the database in which the table exists.
     * @param tablename : Name of the table to which the data is to be loaded from the CSV file.
     * @param values : The values to be inserted as a String Array list.
     * @param ConditionalCol : If any conditional columns are needed then that's passed as a String Array. This is <b>null</b> if you dont want to pass any conditional columns.
     * @param ConditionalVal : Values for the columns in the conditional statement, has to be same size as them ConditionalCol variable.
     * @return <b>True</b> if values are inserted to he table successfully, else <b>false</b>.
     * @throws Exception
     */
    public boolean InsertValues(Connection connection, String tablename, List<String[]> values) throws Exception {
        
    	int NoOfCols;
        NoOfCols = GetTableColumns(connection, tablename.toLowerCase()).length;
        
        if (NoOfCols==0)
        	return false;
        
        String ps= "insert into " + tablename.toLowerCase() + "  values (";

        //Build the prepared statement
        for( int i=0 ; i< NoOfCols ; i++ ) {
                if( i > 0 )  ps += ",";
                ps += "?";
        }
        ps += ")";
        
        //System.out.println("Prepared String: " + ps);
        
        PreparedStatement prep;
        prep = connection.prepareStatement(ps);

        //Populate the insert statements with the values from the CSV file 
		for (String[] cValues : values) {
			if (cValues.length!=NoOfCols)
				throw new SQLException("Number of values (" + cValues.length + ") does not match the number of columns (" +  NoOfCols + ")");
			for( int i=0 ; i< NoOfCols; i++ ) {
				if( "null".equals(cValues[i]) )
                	prep.setObject(i+1, null);
            	else
                	prep.setObject(i+1, cValues[i]);				
			}
			prep.addBatch();
		}

		//Load data
        prep.executeBatch();
        prep.close();
        return true;
    } 

    /**This Method will insert the values passed into the table passed as a parameter, it can take conditional statements while taking inserting data. 
     * @param connection : The connection to the database in which the table exists.
     * @param tablename : Name of the table to which the data is to be loaded from the CSV file.
     * @param values : The values to be inserted as a two-dimensional String array.
     * @param ConditionalCol : If any conditional columns are needed then that's passed as a String Array. This is <b>null</b> if you dont want to pass any conditional columns.
     * @param ConditionalVal : Values for the columns in the conditional statement, has to be same size as them ConditionalCol variable.
     * @return <b>True</b> if values are inserted to he table successfully, else <b>false</b>.
     * @throws Exception
     */
    public boolean InsertValues(Connection connection, String tablename, String[][] values) throws Exception {
        
        List<String[]> lValues = new ArrayList<String[]>();        
        int Rows = values.length;
        
        for (int i=0;i<Rows;i++)
        	lValues.add(values[i]);
        
        InsertValues(connection,tablename,lValues);
        
        /*
        if (NoOfCols==0)
        	return false;
        
        String ps= "insert into " + tablename.toLowerCase() + "  values (";

        //Build the prepared statement
        for( int i=0 ; i< NoOfCols ; i++ ) {
                if( i > 0 )  ps += ",";
                ps += "?";
        }
        ps += ")";
        
        System.out.println("Prepared String: " + ps);
        
        PreparedStatement prep;
        prep = connection.prepareStatement(ps);

        //Populate the insert statements with the values from the CSV file 
		for (String[] cValues : values) {
			if (cValues.length!=NoOfCols)
				throw new SQLException("Number of values (" + cValues.length + ") does not match the number of columns (" +  NoOfCols + ")");
			for( int i=0 ; i< NoOfCols; i++ ) {
				if( "null".equals(cValues[i]) )
                	prep.setObject(i+1, null);
            	else
                	prep.setObject(i+1, cValues[i]);				
			}
			prep.addBatch();
		}

		//Load data
        prep.executeBatch();
        prep.close();*/
        return true;
    } 
    
    /** This will update values in the columns with the values, it supports conditional update based on columns/values passed as parameters.
     * @param connection : The connection to the database. 
     * @param tablename : Name of the table in which the data is to be updated.
     * @param columntoupdate : The columns that you want updated in the table.
     * @param values : Values for the columns, has to match the count of columns, else will raise an exception
     * @param conditionalcol : This is the name of the columns to be used for conditionally selecting records, you can specify multiple columns, but the you should have as many values.
     * @param conditionalval : This is the values for the conditional columns, it has either to match the number of conditional columns or should be more, if more then only one conditional column is supported as it will be evaluated with an <b>IN</b> clause
     * @return <b>True</b> if values are updated in the table successfully, else <b>false</b>.
     * @throws Exception
     */
    public boolean UpdateValues(Connection connection, String tablename, String [] columntoupdate, String[] values, 
    		String [] conditionalcol, String [] conditionalval) throws Exception {
        
        if (columntoupdate.length==0)
        	throw new SQLException("Unable to process when nnumber of columns to be updated is " +  columntoupdate.length + ".");
        
        if (columntoupdate.length != values.length)
        	throw new SQLException("Number of values (" + values.length + ") does not match the number of columns (" +  columntoupdate.length + ")");
        
        String ps= "update " + tablename.toLowerCase() + " set ";

        //Build the prepared statement
        for(int i=0;i< columntoupdate.length;i++) {
        	if( i > 0 )  ps += ", ";
        	ps += columntoupdate[i] +"= ?";
        }
        
        if (conditionalcol!=null && conditionalval!=null) {
        	if (conditionalcol.length==conditionalval.length){
        		//When a column has to be evaluated against a single value
        		ps+= " where ";
        		for (int i = 0;i<conditionalcol.length;i++){
        			ps+= conditionalcol[i] + " = ?";
        		}
        	}
        	else if (conditionalcol.length==1 && conditionalcol.length<conditionalval.length){
        		//When there is only one conditional column passed and has to be evaluated against multiple values
        		ps+= " where " + conditionalcol[0] + " in (";        		
        		for (int i = 0;i<conditionalval.length;i++){
        			if( i > 0 )  ps += ", ";
        			ps+="?";        			
        		}
        		ps+= ")";
        		
        	}
        	else if (conditionalcol.length!=conditionalval.length){
        		//Handles the condition where the conditional column are more than the values or when the conditional columns are lesser than values but columns count is more than one
        		throw new SQLException("Number of values for conditional value (" + conditionalval.length + ") does not match the number of conditional columns (" 
        				+  conditionalcol.length + ")");
        	}
        }
        
        //System.out.println("Prepared String: " + ps);
        
        PreparedStatement prep;
        prep = connection.prepareStatement(ps);

        //Populate the insert statements with the parameter values
        int i=0;
        for(int j=0 ; j< values.length; j++) {
        	if("null".equals(values[j]) )
        		prep.setObject(i+1, null);
        	else
        		prep.setObject(i+1, values[j]);
        	i++;
        }
        for(int k=0 ; k< conditionalval.length; k++ ) {
        	if("null".equals(conditionalval[k]) )
        		prep.setObject(i+1, null);
        	else
        		prep.setObject(i+1, conditionalval[k]);
        	i++;
        }

		//Load data
        prep.execute();// executeBatch();
        prep.close();
        return true;
    } 

}