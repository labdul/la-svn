package betfair.util;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

/**Support functions for WebElement, all methods are static
 * @author jeapen
 *
 */
public class WEUtils {

	/**Used to find is an element is present on the page
	 * @param webDriver - An instance of WebDriver
	 * @param by - Web element represented by <tt>org.openqa.selenium.By</tt>
	 * @return <b>TRUE</b> if found, else <b>FALSE</b>
	 */
	public static boolean isElementPresent(WebDriver webDriver, By by) {
		try {
			webDriver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	/**Get the text from a list of elements and returns as a List of String.
	 * @param elements - list of WebElements 
	 * @return - the InnerText of the WebElements
	 * @throws Throwable
	 */
	public static List<String> getTextInElements(List <WebElement> elements) throws Throwable {
		List <String> texts = new ArrayList<String>(); 
		for (WebElement element: elements){
			String text = element.getText().trim();
			texts.add(text);
		}
		return texts;
	}

	/**Get the value from a list of elements and returns as a List of String.
	 * @param elements - List of WebElements
	 * @return - the values in these elements as a List of String
	 * @throws Throwable
	 */
	public static List<String> getValueInElements(List <WebElement> elements) throws Throwable {
		List <String> texts = new ArrayList<String>(); 
		for (WebElement element: elements)
			texts.add(element.getAttribute("value").trim());
		return texts;
	}
	
    /**A wrapper for <tt>org.openqa.selenium.By</tt> that allows user to select Web elements using DOM expressions 
     * @param webDriver - Instance of the WebDriver
     * @param domExpression - DOM expression that represents an element on the page 
     * <p> <b>Valid examples of DOM expressions as below</b>:
     * <br> <tt>document.div['pancakes'].button[0]
     * <br>document.getElementById("pancakes")</tt> </p>
     * @return the Web element as <tt>org.openqa.selenium.By</tt>
     */
    public static By Dom(WebDriver webDriver, String domExpression) {
        final Object o = ((JavascriptExecutor) webDriver).executeScript("return " + domExpression + ";");

        if (o instanceof WebElement) {
            return new By() {
                @Override
                public List<WebElement> findElements(SearchContext searchContext) {
                    return new ArrayList<WebElement>() {
     					private static final long serialVersionUID = 1L;

						{
                            add((WebElement) o);
                        }
                    };
                }
            };
        }
        else
        	throw new WebDriverException("Dom expression, "+ domExpression + " invalid!"); 
    }
	
	/**Finds element by xpath where the xpath is built dynamically
	 * @param webDriver - Instance of Web Driver to be used to find the element
	 * @param xPath - the dynamic xpath with string formatters like, '%s', '%f', etc
	 * @param param - the object(String, integer, etc) to be added to xpath in the right order as the formatter
	 * @return A WebElement pointing to the element on the page
	 * @throws Throwable The following exceptions are possible, <code>IllegalFormatException, NullPointerException</code> & variants of <code>SeleniumException</code>
	 */
	public static WebElement findElementbyDynamicXpath(WebDriver webDriver, String xPath, Object... param) throws Throwable {		
		return webDriver.findElement(By.xpath(String.format(xPath, param)));
	}

    
}
