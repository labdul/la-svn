package betfair.services;

//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;

import static org.testng.Assert.fail;

import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.openqa.selenium.internal.Locatable;

import com.google.common.base.Stopwatch;

//import com.thoughtworks.selenium.Selenium;

//import common.InitApp;

public class MiscServices {

	//Variable declarations
	private WebDriver Driver;
	//private Selenium selenium;
	String BaseURL;

	public MiscServices () {
	}
	
	/**
	 * @param driver
	 */
	public MiscServices (WebDriver driver) {
		Driver = driver;
	}

	/**
	 * @param baseurl
	 * @return
	 */
	public MiscServices usingBaseURL(String baseurl) {
		this.BaseURL = baseurl;
		return this;
	}
		
	/**
	 * @param by
	 * @return
	 */
	public boolean isElementPresent(By by) {
		try {
			//logger.log(Level.INFO, by.toString());
			Driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String BuildPath(String path) {
		
		return null;
	}
	
	public boolean HoverMenu (By by) throws InterruptedException{
		
		try {
			
			//if (BaseURL.isEmpty()) {
			
				WebElement menu = Driver.findElement(by); //waitUntilClickable(by);
				Actions builder = new Actions(Driver);
				builder.moveToElement(menu).build().perform();
				//Thread.sleep(3000);
			/*}
			else {
			
				//String ObjIdent =  by.toString().replace(": ", "=").replace("By.", "");
				//System.out.println(ObjIdent);
				selenium =  new WebDriverBackedSelenium(Driver, BaseURL);
				selenium.waitForPageToLoad("5000");
				//selenium.mouseOver(ObjIdent);
				selenium.mouseOver("//ul[@id='top_menu_list']/li[1]/div");
			}*/
			//if (InitApp.config.getValue("CONFIG_BROWSER").equalsIgnoreCase("firefox")) {
				
				//build and perform the mouseOver with Advanced User Interactions API
				//Actions builder = new Actions(Driver);
				//builder.moveToElement(menu).build().perform();
				//builder.click(menu).perform();
			/*}
			else {
				//Locatable hoverItem = (Locatable) Driver.findElement(by);
				//Mouse mouse = ((HasInputDevices) Driver).getMouse();
				//mouse.mouseMove(hoverItem.getCoordinates());
				//menu.click();
				//Actions builder = new Actions(Driver);
				//builder.(menu).build().perform();
			}*/
			return true;
			
		} catch (Error e) {
			//InitApp.CommonVerificationErrors.append(e);
			return false;
		}
	}
	

	/**
	   * Wait for an element to be visible AND clickable in the UI.
	   *
	   * @param elementXpath
	   * @return
	   */
	public WebElement waitUntilClickable(By by) {

		try {
	    	System.out.println("Object identifier: "+ by.toString());
	    	return new WebDriverWait(Driver, 10, 50).until(ExpectedConditions.elementToBeClickable(by));
	    } catch (TimeoutException e) {
	    	throw new NoSuchElementException("I failed after waiting 10 seconds for the element with ID, '" + by.toString()
	    			+ "' to appear and become clickable on the page. Check your xpaths!");
	    }
	}
	
	/**
	 * This will read the contents of a list of Object array and returned the ones which have 'Y' in its first column
	 * @param lValues - The object Array containing the original data
	 * @return The rows in the list that has 'Y' in its first column
	 */
	public static ArrayList<Object[]> StripExcludedData (ArrayList<Object[]> Values){
		
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
	}	
	
	@SuppressWarnings("deprecation")
	public void waitForAjaxToLoad()
    {
        Stopwatch sw = new Stopwatch();
        sw.start();
		//Added as a slowdown of all tests.
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e1) {	}
		int timeout=30000;
        while (true)
        {
            if (sw.elapsedMillis() > timeout) fail("Ajax Wait Timeout after "+timeout+" millis.");
            JavascriptExecutor js = (JavascriptExecutor)Driver;
            Boolean ajaxIsComplete = (Boolean)js.executeScript("return (typeof jQuery == 'function')");
            ajaxIsComplete = ajaxIsComplete  & (Boolean)js.executeScript("return jQuery.active == 0");
            
            if (ajaxIsComplete) {
            }
            try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }            
    }
}