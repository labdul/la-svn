package betfair.util;

import static org.testng.Assert.fail;
import static utils.MiscUtil.removeStringPrefixSuffix;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.io.IOUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import com.google.common.base.Stopwatch;
import com.openbet.webservices.utils.HttpUtils;

public class Helpers {

	private static Logger logger;
	public static final String SKIP_ENCLOSURE_TRAILING_CHAR= "]";
	public static final String SKIP_ENCLOSURE_LEADING_CHAR= "[";

	/**
	 * Switches To the next window, in the default scenario where there are two
	 * windows.
	 *
	 * @param webDriver Needed for the method to function
	 */
	public static void switchToWindow(WebDriver webDriver) {
		switchToWindow(webDriver, 2);
	}

	/**
	 * Switches to the windowIndex number window.
	 *
	 * @param webDriver Needed for the method to function.
	 * @param windowIndex The number of the window to swtich to
	 */
	public static void switchToWindow(WebDriver webDriver, int windowIndex) {

		List<String> list = new ArrayList<String>(webDriver.getWindowHandles());
		webDriver.switchTo().window(list.get(windowIndex - 1));
	}

	/**
	 * Closes all the opened windows except for the original one. If only one
	 * window is present it skips the run.
	 *
	 * @param webDriver Needed for the method to function.
	 */
	public static void closeSwitched(WebDriver webDriver) {
		List<String> list = new ArrayList<String>(webDriver.getWindowHandles());
		for (int i = 1; i < list.size(); i++) {
			webDriver.switchTo().window(list.get(i));
			webDriver.close();
		}
		webDriver.switchTo().window(list.get(0));
	}

	public static HashMap<String, String> formatTestData(Object[] testParameters) throws Throwable {		
		HashMap<String, String> testDataMap = new HashMap<String, String>();

		for(int i=0;i<testParameters.length;i++){
			String[] keyValue = testParameters[i].toString().split("\t");

			if (keyValue.length==2){
				//System.out.println(keyValue[0].toLowerCase()+ "="+ keyValue[1]);
				testDataMap.put(keyValue[0], removeStringPrefixSuffix(keyValue[1],SKIP_ENCLOSURE_LEADING_CHAR, SKIP_ENCLOSURE_TRAILING_CHAR));
			}
			else if (keyValue.length==1){
				//System.out.println(keyValue[0].toLowerCase()+ "="+ "");
				testDataMap.put(keyValue[0], "");
			}
		}
		return testDataMap;
	}

	public static String getHashMapValue(HashMap<String, String> hashMap, String key) throws Throwable {
		if (hashMap.containsKey(key))
			return 	hashMap.get(key);
		else if (hashMap.containsKey(key.toLowerCase()))
			return 	hashMap.get(key.toLowerCase());
		else if (hashMap.containsKey(key.toUpperCase()))
			return 	hashMap.get(key.toUpperCase());
		else
			return "";
	}

	/**
	 * Resizes the focused window to the width and height params
	 *
	 * @param webDriver Needed for the method to function.
	 * @param width Sets the Width of the window.
	 * @param height Sets the Height of the window.
	 */
	public static void resizeWindow(WebDriver webDriver, Integer width, Integer height) {
		((JavascriptExecutor) webDriver).executeScript(String.format("window.resizeTo(%d,%d);", width, height));
	}

	@SuppressWarnings("deprecation")
	public static void waitForAjaxToLoad(WebDriver webDriver) {
		logger = Logger.getLogger(Helpers.class);
		Stopwatch sw = new Stopwatch();
		sw.start();
		int timeout = 30000;
		while (true) {
			if (sw.elapsedMillis() > timeout)
				fail("Ajax Wait Timeout after " + timeout + " millis.");
			JavascriptExecutor js = (JavascriptExecutor) webDriver;
			Boolean ajaxIsComplete = (Boolean) js.executeScript("return (typeof jQuery == 'function')");
			ajaxIsComplete = ajaxIsComplete & (Boolean) js.executeScript("return jQuery.active == 0");

			if (ajaxIsComplete) {
				logger.info(" ... ajax loading done in " + sw.elapsedMillis() + "ms");
				break;
			}
			try {
				Thread.sleep(200);
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Returns the test case number from a description of a test method
	 *
	 * @param testCaseName the description string from the test method
	 * @return The test case number
	 */
	public static String getTCfromDescription(String testCaseDescription) {
		Pattern p = Pattern.compile("(.[0-9]{5}) (.*)");
		Matcher m = p.matcher(testCaseDescription);
		if (m.find()) {
			return m.group(1);
		}
		return null;
	}
	/**
	 * Returns the test case number from a description of a test method
	 *
	 * @param testCaseName the description string from the test method
	 * @return The test case title
	 */
	public static String getNameFromDescription(String testCaseDescription) {
		Pattern p = Pattern.compile("(.[0-9]{5}) (.*)");
		Matcher m = p.matcher(testCaseDescription);
		if (m.find()) {
			return m.group(2);
		}
		return null;
	}

	public static String getDateWithOffsetInYears(Integer offsetInYears){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, offsetInYears);
		return dateFormat.format(calendar.getTime());
	}

	public static String getDateWithOffsetInDays(Integer offsetInDays){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, offsetInDays);
		return dateFormat.format(calendar.getTime());
	}

	public static String getDateWithOffsetInMinutes(Integer offsetInMinutes){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, offsetInMinutes);
		return dateFormat.format(calendar.getTime());
	}

	public static String getSsoidForClient(String clientId) throws Exception {
		String proxyHost = "custproxy.openbet.openbet.com";
		HttpHost proxy = new HttpHost(proxyHost, 8080, "http");
		String url ="http://sbg.cd2-integration.dev.betfair/admin/?action=ADMIN::SSOID::DoGetBFAccountInfo" + "&BfAcctId=" + clientId;

		DefaultHttpClient HTTP_CLIENT = new DefaultHttpClient();
		HTTP_CLIENT.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, 8080),new UsernamePasswordCredentials(PropertyLoader.loadProperty("proxy.username"), PropertyLoader.loadProperty("proxy.password")));
		HTTP_CLIENT.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		HttpGet httpGet = new HttpGet(url);
		HttpResponse resp = HTTP_CLIENT.execute(httpGet);
		String responseBody = IOUtils.toString(resp.getEntity().getContent(), "UTF-8");

		try {
			HttpUtils httpUtils2 = new HttpUtils(url, proxyHost,"8080",PropertyLoader.loadProperty("proxy.username"), PropertyLoader.loadProperty("proxy.password"));
			return httpUtils2.createResponse(resp.getStatusLine().getStatusCode(), responseBody).withSingleReturn("ssoidDataResponse.ssoid");

		}
		catch (Exception e) {
			System.out.println("There was an error querying Oxi.");
			return null;
		}

	}

	public static String getBalanceForClient(String clientId) throws Exception {
		String proxyHost = "custproxy.openbet.openbet.com";
		HttpHost proxy = new HttpHost(proxyHost, 8080, "http");
		String url ="http://sbg.cd2-integration.dev.betfair/admin/?action=ADMIN::SSOID::DoGetBFAccountInfo" + "&BfAcctId=" + clientId;

		DefaultHttpClient HTTP_CLIENT = new DefaultHttpClient();
		HTTP_CLIENT.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, 8080),new UsernamePasswordCredentials(PropertyLoader.loadProperty("proxy.username"), PropertyLoader.loadProperty("proxy.password")));
		HTTP_CLIENT.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		HttpGet httpGet = new HttpGet(url);
		HttpResponse resp = HTTP_CLIENT.execute(httpGet);
		String responseBody = IOUtils.toString(resp.getEntity().getContent(), "UTF-8");

		try {
			HttpUtils httpUtils2 = new HttpUtils(url, proxyHost,"8080",PropertyLoader.loadProperty("proxy.username"), PropertyLoader.loadProperty("proxy.password"));
			return httpUtils2.createResponse(resp.getStatusLine().getStatusCode(), responseBody).withSingleReturn("ssoidDataResponse.balance");

		}
		catch (Exception e) {
			System.out.println("There was an error querying Oxi.");
			return null;
		}

	}

	public static String dbPublish (String request, String response) throws Exception {


		String proxyHost = "custproxy.openbet.openbet.com";
		HttpHost proxy = new HttpHost(proxyHost, 8080, "http");
		String url ="http://sbg.cd2-integration.dev.betfair/dbPublish" + request;

		DefaultHttpClient HTTP_CLIENT = new DefaultHttpClient();
		HTTP_CLIENT.getCredentialsProvider().setCredentials(new AuthScope(proxyHost, 8080),new UsernamePasswordCredentials(PropertyLoader.loadProperty("proxy.username"), PropertyLoader.loadProperty("proxy.password")));
		HTTP_CLIENT.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);

		HttpGet httpGet = new HttpGet(url);
		HttpResponse resp = HTTP_CLIENT.execute(httpGet);
		String responseBody = IOUtils.toString(resp.getEntity().getContent(), "UTF-8");

		try {
			HttpUtils httpUtils2 = new HttpUtils(url, proxyHost,"8080",PropertyLoader.loadProperty("proxy.username"), PropertyLoader.loadProperty("proxy.password"));
			return httpUtils2.createResponse(resp.getStatusLine().getStatusCode(), responseBody).withSingleReturn(response);

		}
		catch (Exception e) {
			System.out.println("There was an error querying Oxi.");
			return null;
		}

	}
}