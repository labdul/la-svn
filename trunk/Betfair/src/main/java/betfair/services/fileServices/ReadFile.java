
package betfair.services.fileServices;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile {
	
	  /**
	 * @param file : Name of the file to be read
	 * @return The contents of the file in a string.
	 * @throws IOException
	 */
	public String readFile(String file) throws IOException {
		    BufferedReader reader = new BufferedReader( new FileReader (file));
		    String line  = null;
		    StringBuilder stringBuilder = new StringBuilder();
		    String ls = System.getProperty("line.separator");
		    while( ( line = reader.readLine() ) != null ) {
		        stringBuilder.append( line );
		        stringBuilder.append( ls );
		    }
		    reader.close();
		    return stringBuilder.toString();
	}

}