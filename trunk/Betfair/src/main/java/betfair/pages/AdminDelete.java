package betfair.pages;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



//This class contains Admin Page functions
public class AdminDelete extends Page{

	public AdminDelete(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */

	private final String deleteSelectionLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Delete Selection')]";
	private final String deleteMarketLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Delete Market')]";
	private final String deleteEventLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Delete Event')]";

	/*
	 * ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = deleteSelectionLocator)
	private WebElement deleteSelectionElement;


	@FindBy(how = How.XPATH, using = deleteMarketLocator)
	private WebElement deleteMarketElement;

	@FindBy(how = How.XPATH, using = deleteEventLocator)
	private WebElement deleteEventElement;

	/*
	 * METHODS
	 */

	/**
	 * Click Methods	
	 */

	public void clickDeleteSelection() {
		deleteSelectionElement.click();
	}

	public void clickDeleteMarket() {
		deleteMarketElement.click();
	}

	public void clickDeleteEvent() {
		deleteEventElement.click();
	}

	public enum Stuff {
		EVENT(1),
		MARKET(2),
		SELECTION(3);

		public int value;

		private Stuff(int value){
			this.value = value;
		}
	}

	/**
	 * Deletes Selection, Market and Event by level index
	 */

	public void deleteAllStuff(Stuff level) {

		try {
			webDriver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			for(int i = level.value; i > 0 ; i--){
				switch (i) {
				case 3:
					deleteSelectionElement.click();
					break;
				case 2:
					deleteMarketElement.click();
					break;
				case 1:
					deleteEventElement.click();
					break;
				}
			}
		} catch (Exception e) {
			logger.debug("Delete Failiure@(level)"); 
		} finally {
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}
	}
}