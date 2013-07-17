package betfair.services.fileServices;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WriteProperties {
	
	private Properties props;
	private String FileName;
	private InputStream in;
	
	public WriteProperties (String propFile) {
		FileName = propFile;
		LoadProperties();
	}
	
	/**
	 * This is an internal function that loads the data into the filestream to be accessed and read.
	 */
	private void LoadProperties(){
		
		props = new Properties();
		try {			
			in = new FileInputStream(FileName);
			props.load(in);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setValue(String key, String value){
		try {
			props.setProperty(key, value);		
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * @return
	 */
	public boolean SavePropFile(){		
		try {
			props.store(new FileOutputStream(FileName), "");
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
	
}