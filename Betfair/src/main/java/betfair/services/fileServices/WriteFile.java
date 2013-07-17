package betfair.services.fileServices;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WriteFile {

	public boolean writeFile (String FileName, String Contents) {
		
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(FileName));
			out.write(Contents);
			out.close();
			return true;
		}
		catch (IOException e)
		{
			return false;		
		}		
	}
	
	public boolean writeFile (String FileName, InputStream Contents) {
		
		try {
			int len;
			File file = new File (FileName);
			OutputStream out = new FileOutputStream(file);
			byte buf[]=new byte[1024];
			
			while((len = Contents.read(buf))>0)
			  out.write(buf,0,len);
			out.close();
			Contents.close();			
			return true;
		}
		catch (IOException e)
		{
			return false;		
		}		
	}
	
}
