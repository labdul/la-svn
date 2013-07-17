package betfair.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import betfair.services.MiscServices;


/*
 * Abstract class representation of a Page in the UI. Page object pattern
 * 
 * @author Victor Vrinceanu
 */
public abstract class Page {

	protected WebDriver webDriver;
	protected Logger logger;
	protected MiscServices miscServices;

	/*
	 * Constructor injecting the WebDriver interface
	 * 
	 * @param webDriver
	 */
	public Page(WebDriver webDriver) {
		this.webDriver = webDriver;
		this.logger =  Logger.getLogger(this.getClass().getCanonicalName());
		miscServices = new MiscServices(webDriver);
	}

	public WebDriver getWebDriver() {
		return webDriver;
	}

	public String getTitle() {
		return webDriver.getTitle();
	}
	
	public abstract boolean isVisible(); 

}
