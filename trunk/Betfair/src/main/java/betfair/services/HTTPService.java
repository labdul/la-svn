package betfair.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

public class HTTPService {		

	public InputStream HTTPResponse (String AppURL, String rawData) throws IOException {
		URL url = new URL(AppURL);
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(rawData);
		wr.flush();
		wr.close();
		return conn.getInputStream();
	}
}