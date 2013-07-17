package betfair.services.fileServices;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//import com.informix.jdbc.*;
import au.com.bytecode.opencsv.CSVWriter;

public class CreateCSV {
	
	Connection Conn;
	Statement Query;
	ResultSet RS;
	
	public boolean CreateCSVFromDB(String ConnString, String SQLString, String filename) {

		try{
			RS = GenResultSet(ConnString, SQLString);
			GenerateCSV(filename, RS);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	public boolean CreateCSVFromStringArr(String filename, List<String[]> StringArr) {

		try{
			String Newfilename = filename; 
			File file = new File(filename);
						
			while (file.exists()) {
				while (!file.renameTo(new File(filename.concat(".old"))))
					filename = filename.concat(".old");
			}
			
			GenerateCSV(Newfilename, StringArr);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}

	private ResultSet GenResultSet (String ConnString, String SQLString) throws  Exception, SQLException{
		
		Class.forName("com.informix.jdbc.IfxDriver");
		Conn = DriverManager.getConnection(ConnString);
		Query = Conn.createStatement();
		return Query.executeQuery(SQLString); 
	}

	private void GenerateCSV(String filename, ResultSet rs) throws IOException, SQLException{
		CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');
		writer.writeAll(rs, true);
		writer.close();
	}
	
	private void GenerateCSV(String filename, List<String[]> CSVdata) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(filename), ',');
		writer.writeAll(CSVdata);
		writer.close();
	}
	
}