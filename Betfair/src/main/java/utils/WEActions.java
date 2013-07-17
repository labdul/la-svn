package utils;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;


/**A class of Advanced WebElement actions which normally is difficult to achieve while running Selenium tests. All methods are static
 * @author jeapen
 *
 */
public class WEActions {
		
    /**Support static class for waitForOption method
     * @author jeapen
     *
     */
    private static class OptionAvailable implements ExpectedCondition<WebElement> {
    	private WebElement select;
    	private By byOption;

    	private OptionAvailable(WebElement select, By byOption) {
    		this.select = select;
    		this.byOption = byOption;
    	}

    	@Override
    	public WebElement apply(WebDriver input) {
    		return select.findElement(byOption);
    	}
    }

    /**Waits until the option targeted by <tt>byOption</tt> is available.
     *@param webDriver - Instance of the WebDriver
     *@param select - parent element of the option to wait for
     *@param byOption - selector (relative to the <tt>select</tt>). It will be searched calling <tt>select.findElement(byOption)</tt>
     *@param seconds - time in seconds to wait before giving up
     *@return option element
     */
    public static WebElement waitForOption(WebDriver webDriver, WebElement select, By byOption, int seconds) {
        Wait<WebDriver> wait = new WebDriverWait(webDriver, seconds);
        return wait.until(new OptionAvailable(select, byOption));
    }

	/**Wait for an element to be visible and available for action.
	 * @param webDriver - Instance of the WebDriver
	 * @param by - Element represented by <tt>org.openqa.selenium.By</tt> 
	 * @param waitTimeInSeconds - Time to wait before giving up
	 * @return - WebElement if found after waiting
	 */
	public static WebElement waitUntilAvailble(WebDriver webDriver, By by, long waitTimeInSeconds) {

		try {
	    	//System.out.println("Object identifier: "+ by.toString());
	    	return new WebDriverWait(webDriver, waitTimeInSeconds, 50).until(ExpectedConditions.elementToBeClickable(by));
	    } catch (TimeoutException e) {
	    	throw new NoSuchElementException("I failed after waiting 10 seconds for the element with ID, '" + by.toString()
	    			+ "' to appear and become clickable on the page. Check your xpaths!");
	    }
	}
    
    /**Clicking by injecting Javascipt in case WebDriver is unable to click on the element 
     * @param driver - Instance of the WebDriver 
     * @param element - WebElement that is to be clicked
     */
    public static void Click(WebDriver webDriver, WebElement element) throws Throwable{
    	try{
    		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    		element.click();
    	}
    	catch(WebDriverException e) {
    		//e.printStackTrace();
    		((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", element);
    	}
    	finally{
    		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	}
    }
    
    /**Sending keys to input elements by injecting Javascript in case WebDriver is unable to do so on the element
     * @param driver - Instance of the WebDriver
     * @param elementID - ID attribute of the element as String
     * @param value - The value that is to be sent to the input element
     */
    public static void SendKeys(WebDriver webDriver, String elementID, String value) throws Throwable {
    	try{
    		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    		webDriver.findElement(By.id(elementID)).clear();
    		webDriver.findElement(By.id(elementID)).sendKeys(value);
    	}
    	catch (InvalidElementStateException e) {
    		((JavascriptExecutor)webDriver).executeScript(String.format("document.getElementById('%s').value='%s'", elementID, value));
    	}
    	finally{
    		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	}
    }
    
    /**Sending keys to input elements by injecting Javascript in case WebDriver is unable to do so on the element
     * @param driver - Instance of the WebDriver
     * @param element - WebElement on which the value is to be set
     * @param value - The value that is to be sent to the input element
     */
    public static void SendKeys(WebDriver webDriver, WebElement element, String value) throws Throwable{
    	try{
    		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    		element.clear();
    		element.sendKeys(value);
    	}
    	catch (InvalidElementStateException e) {
    		((JavascriptExecutor)webDriver).executeScript(String.format("arguments[0].value='%s'",value),element);
    	}
    	finally{
    		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	}
    }
    
    /**Selecting option from a dropdown by injecting Javascript in case WebDriver is unable to do so on the element
     * @param driver - Instance of the WebDriver
     * @param element - WebElement of type Select on which the value is to be set
     * @param visibleValue - The value that is to selected, this is the Visible value in the dropdown and not the value attribute 
     */
    public static void SelectOption(WebDriver webDriver, Select element, String visibleValue) {
    	try{
    		webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    		element.selectByVisibleText(visibleValue);
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		try {
    			((JavascriptExecutor)webDriver).executeScript(String.format("arguments[0].value('%s').selected=true", visibleValue),element);    		
    		}
    		catch (Exception e1) {
    			e1.printStackTrace();
    		}
    		
    	}
    	finally{
    		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    	}
    }
    
    /**Build action to hover mouse on an element
     * @param webDriver - Instance of the WebDriver
     * @param by - Element represented by 'org.openqa.selenium.By' 
     * @return - <b>TRUE</b> if its able to perform the action, <b>FALSE</b> if an exception is raised.
     */
    public static boolean HoverMenu (WebDriver webDriver, By by) {		
		try {
			WebElement menu = webDriver.findElement(by);
			Actions builder = new Actions(webDriver);
			builder.moveToElement(menu).build().perform();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
    
}
