package betfair.services.fileServices;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class will read contents of a properties file, it can read the project config properties file, 
 * the object repository properties or any properties file passed as a parameter . At least one of the
 * builder constructors should be called, else the object initialisation will fail.<br><br>
 * <b>Available builder constructors</b>: <br>
 * <li> <i><b>ReadConfig()</b>
 * <li> <b>ReadObjectRepository()</b>
 * <li> <b>ReadPropertiesFile (String testfile)</b></i><br>
 * @author Johnson Eapen
 *
 */
public class ReadProperties {
	private Properties props;
	private String FileName;
	private InputStream in;
	

	/**
	 * The constructor that reads the contents of the properties file and loads it for processing.
	 * @param propFile - The name of the properties file with its path relative to the project.
	 */
	public ReadProperties (String propFile) {
		FileName = propFile;
		LoadProperties();
	}

	
	/**
	 * This is an internal function that loads the data into the filestream to be accessed and read.
	 */
	private void LoadProperties(){
		
		props = new Properties();
		try {			
			in = this.getClass().getClassLoader().getResourceAsStream(FileName);//new FileInputStream(FileName);
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	/** This will read and return the value of the key passed as a parameter from the properties file. 
	 * @param key : The key of which you want the value to be read.
	 * @return The value stored against the key passed as parameter.
	 */
	public String getValue(String key){
		return (String)props.getProperty(key);
	}
	

}