package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;


//This class contains Admin Page functions
public class AdminBetSearch extends Page{

	public AdminBetSearch(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	
	private final String usernameFieldLocator = "//input[@name='Customer']";
	private final String receiptFieldLocator = "//table[1]/tbody/tr[6]/td[2]/input";
	private final String findBetsButtonLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Find Bets')]";
	private final String resultsQueryLocator = "//table/tbody/tr[1]/th";
	private final String receiptLocator = "//center/table[2]/tbody/tr[16]/td[2]";
	private final String noBetsFoundLocator = "//table/tbody/tr[3]/td";
	
	/*
	 * ELEMENTS
	 */

	
	@FindBy(how = How.XPATH, using = usernameFieldLocator)
	private WebElement usernameFieldElement;

	@FindBy(how = How.XPATH, using = receiptFieldLocator)
	private WebElement receiptFieldElement;

	@FindBy(how = How.XPATH, using = findBetsButtonLocator)
	private WebElement findBetsButtonElement;
	
	@FindBy(how = How.XPATH, using = resultsQueryLocator)
	private WebElement resultsQueryElement;
	
	@FindBy(how = How.XPATH, using = receiptLocator)
	private WebElement receiptElement;
	
	@FindBy(how = How.XPATH, using = noBetsFoundLocator)
	private WebElement noBetsFoundElement;
	
	/*
	 * METHODS
	 */

	/**
	 * Inserts Username
	 */
	public void insertUsername(String text) {
		Frames.selectMainAreaFrame(webDriver);
		usernameFieldElement.sendKeys(text);
	}
	
	/**
	 *Inserts Receipt 
	 */
	public void insertReceipt(String text) {
		Frames.selectMainAreaFrame(webDriver);
		receiptFieldElement.sendKeys(text);
	}
	
	/**
	 * Clicks Find Bets
	 */
	
	public void clickFindBets() {
		Frames.selectMainAreaFrame(webDriver);
		findBetsButtonElement.click();
	}
	/**
	 * Gets Query Results Page Text
	 */
	public String getQueryTitle() {
	return resultsQueryElement.getText();
	}
	
	/**
	 * Gets bet receipt 
	 */
	public String getReceipt() {
		return receiptElement.getText();
	}
	
	/**
	 * Boolean conditions for Asserts 
	 */
	
	public boolean isNoBetsFoundElementDisplayed() {
		return noBetsFoundElement.isDisplayed();
	}
	
	public boolean  isReceiptElementDisplayed() {
		return receiptElement.isDisplayed();
	}
	
}
