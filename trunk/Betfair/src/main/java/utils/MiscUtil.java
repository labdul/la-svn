package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MiscUtil {
	
	/**This will read the contents of a list of Object array and returned the ones which does not have 'N' in its first column
	 * @param Values - An ArrayList containing the original data
	 * @return The rows in the list that does not have 'N' in its first column in the same ArrayList format as what was passed
	 */
/*	public static ArrayList<Object[]> StripExcludedData (ArrayList<Object[]> Values){
		
		ArrayList<Object[]> rValues = new ArrayList<Object[]>();
		for (Object[] cValues : Values) {
			if (!cValues[0].toString().equalsIgnoreCase("N")){
				Object[] sValue = new Object[cValues.length-1]; 
				for (int i=1; i < cValues.length;i++){
					sValue[i-1] = cValues[i];
					//System.out.println(sValue[i-1]);
				}
				rValues.add(sValue);
			}
		}
		return rValues;
	}*/
	@SuppressWarnings("unchecked")
	public static ArrayList<?> StripExcludedData (ArrayList<?> Values){
		
		if(Values==null)
			return new ArrayList<Object[]>();
		
		//System.out.println("Values Class: "+ Values.get(0).getClass().getName());//TODO: Remove this debug line
		
		if (Values.get(0) instanceof HashMap<?,?>){
			ArrayList<HashMap<Object,Object>> rValues = new ArrayList<HashMap<Object,Object>>();
			for (HashMap<Object,Object> cValues : (ArrayList<HashMap<Object,Object>>) Values) {
				if (!cValues.get("Include in Test").equals("N")){					
					rValues.add(cValues);
				}
			}
			return rValues;
		}
		else if (Values.get(0).getClass().isArray()){
			ArrayList<Object[]> rValues = new ArrayList<Object[]>();
			for (Object[] cValues : (ArrayList<Object[]>) Values) {
				if (!(cValues[0].toString().equalsIgnoreCase("N") || cValues[0].toString().contains("\tN"))){
					Object[] sValue = new Object[cValues.length-1];
					for (int i=1; i < cValues.length;i++){
						sValue[i-1] = cValues[i];
					//System.out.println(sValue[i-1]);
					}
					rValues.add(sValue);
				}
			}
			return rValues;
		}
		else
			return new ArrayList<Object[]>();
	}
	
	/**This will convert a String array to a String with the supplied separator as separator separating the different values.
	 * @param stringArr - The String Array that is to be converted.
	 * @param separator - The separator that will separate the values in the array
	 * @param quoteChar - A quote character that needs to be added around the value
	 * @return The output String. 
	 */
	public static String stringArrayAsString (String [] stringArr, String separator, String quoteChar) {
		if (stringArr.length==0)
			return "";
		String returnString = "";		
		for (String string: stringArr)
			returnString+=quoteChar+string+quoteChar+separator;
		return returnString.substring(0, returnString.lastIndexOf(separator)).trim();
	}
	
	/**This will convert a List<String> to a String with the supplied separator as separator separating the different values.
	 * @param stringList - The String list that is to be converted.
	 * @param separator - The separator that will separate the values in the array
	 * @param quoteChar - a quote character that needs to be added around the value
	 * @return The output String. 
	 */
	public static String stringListAsString (List<String> stringList, String separator, String quoteChar) {		
		if (stringList.isEmpty())
			return "";
		String returnString = "";		
		for (String string: stringList)
			returnString+=quoteChar+string+quoteChar+separator;
		return returnString.substring(0, returnString.lastIndexOf(separator)).trim();
	}
	
	/**Remove the prefix and suffix from a String, both prefix and suffix should be passed. <br> 
	 * If either prefix or suffix doesn't exists then the one present will be removed. <br> 
	 * If neither of them exists then the same string is returned   
	 * @param sourceString - The String from which you want to remove the prefix and suffix
	 * @param prefix - The prefix char
	 * @param suffix - The suffix char
	 * @return - String after removing the prefix and suffix
	 * @throws NullPointerException
	 * @throws IndexOutOfBoundsException
	 */
	public static String removeStringPrefixSuffix(String sourceString, String prefix, String suffix) throws NullPointerException, IndexOutOfBoundsException {
		if (sourceString.startsWith(prefix) && sourceString.endsWith(suffix))
			sourceString = sourceString.substring(1, sourceString.lastIndexOf(suffix));
		else if (sourceString.startsWith(prefix) && !sourceString.endsWith(suffix))
			sourceString = sourceString.substring(1);
		else if (!sourceString.startsWith(prefix) && sourceString.endsWith(suffix))
			sourceString = sourceString.substring(0, sourceString.lastIndexOf(suffix));
		return sourceString;
	}
	
	/**Used to remove the invalid characters from a file name.
	 * <p> <strong>NOTE:</strong> Not be be used on Folders/Directories as it will remove the path separators ("/" or "\")</p>
	 * @param fileName - Name of the file that needs to be cleaned
	 * @return - Returns the file name after removing '/',':','?','\','"','<','\','>' and '|'. 
	 */
	public static String cleanFileName(String fileName) {
		//System.out.println(fileName.replace("\\", "").replace("/", "").replace(":", "").replace("*", "").replace("?", "").replace("\"", "").replace("<", "").replace(">", "").replace("|", ""));		
		return fileName.replaceAll("[/:?\"<\\>|]", "").replace("\\", "").replace("\n", ".").replace("\t", "");		
	}
	
	/**Converts an Object array to String array with string representation of the value in each of the objects of the array 
	 * @param objArray - The String array to be converted
	 * @return The String equivalent of the object array passed.
	 */
	public static String [] objectArrayAsStringArray (Object[] objArray) {
		String[] strArray = new String[objArray.length];
		
		for (int i=0;i<objArray.length;i++)
			strArray[i] = objArray[i].toString();
		
		return strArray;
	}
	
}
	
//	/**
//	 * @param by
//	 * @return
//	 */
//	public static boolean isElementPresent(WebDriver webDriver, By by) {
//		try {
//			//logger.log(Level.INFO, by.toString());
//			webDriver.findElement(by);
//			return true;
//		} catch (NoSuchElementException e) {
//			return false;
//		}
//	}
//	
//	public String BuildPath(String path) {
//		
//		return null;
//	}
	
//	public static boolean HoverMenu (By by) throws InterruptedException{
//	
//	try {
//		
//		//if (BaseURL.isEmpty()) {
//		
//			WebElement menu = Driver.findElement(by); //waitUntilClickable(by);
//			Actions builder = new Actions(Driver);
//			builder.moveToElement(menu).build().perform();
//			//Thread.sleep(3000);
//		/*}
//		else {
//		
//			//String ObjIdent =  by.toString().replace(": ", "=").replace("By.", "");
//			//System.out.println(ObjIdent);
//			selenium =  new WebDriverBackedSelenium(Driver, BaseURL);
//			selenium.waitForPageToLoad("5000");
//			//selenium.mouseOver(ObjIdent);
//			selenium.mouseOver("//ul[@id='top_menu_list']/li[1]/div");
//		}*/
//		//if (InitApp.config.getValue("CONFIG_BROWSER").equalsIgnoreCase("firefox")) {
//			
//			//build and perform the mouseOver with Advanced User Interactions API
//			//Actions builder = new Actions(Driver);
//			//builder.moveToElement(menu).build().perform();
//			//builder.click(menu).perform();
//		/*}
//		else {
//			//Locatable hoverItem = (Locatable) Driver.findElement(by);
//			//Mouse mouse = ((HasInputDevices) Driver).getMouse();
//			//mouse.mouseMove(hoverItem.getCoordinates());
//			//menu.click();
//			//Actions builder = new Actions(Driver);
//			//builder.(menu).build().perform();
//		}*/
//		return true;
//		
//	} catch (Error e) {
//		//InitApp.CommonVerificationErrors.append(e);
//		return false;
//	}
//}
//
//
///**
//   * Wait for an element to be visible AND clickable in the UI.
//   *
//   * @param elementXpath
//   * @return
//   */
//public static WebElement waitUntilClickable(By by) {
//
//	try {
//    	System.out.println("Object identifier: "+ by.toString());
//    	return new WebDriverWait(Driver, 10, 50).until(ExpectedConditions.elementToBeClickable(by));
//    } catch (TimeoutException e) {
//    	throw new NoSuchElementException("I failed after waiting 10 seconds for the element with ID, '" + by.toString()
//    			+ "' to appear and become clickable on the page. Check your xpaths!");
//    }
//}
	
