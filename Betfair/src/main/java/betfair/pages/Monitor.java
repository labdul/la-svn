package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//This class contains Monitor functions
public class Monitor extends Page{

	public Monitor(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */

	private final String arbitrageLocator = "//form/table/tbody/tr[2]/td/select/option[1]";
	private final String betTickerLocator = "//form/table/tbody/tr[2]/td/select/option[2]";
	private final String startButtonLocator = "//form/table/tbody/tr[3]/th/input[1]";
	private final String customizeButtonLocator = "//form/table/tbody/tr[3]/th/input[2]";
	
	/*
	 * ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = arbitrageLocator)
	private WebElement arbitrageElement;

	@FindBy(how = How.XPATH, using = betTickerLocator)
	private WebElement betTickerElement;

	@FindBy(how = How.XPATH, using = startButtonLocator)
	private WebElement startButtonElement;

	@FindBy(how = How.XPATH, using = customizeButtonLocator)
	private WebElement customizeButtonElement;

	/*
	 * METHODS
	 */
	/**
	 * Clicks Arbitrage
	 */
	public void clickArbitrage() {
		arbitrageElement.click();
	}
	/**
	 * Clicks Bet Ticker
	 */
	public void clickBetTicker() {
		betTickerElement.click();
	}
	/**
	 * Clicks Start Button
	 */
	public void clickStart() {
		startButtonElement.click();
	}
	/**
	 * Clicks Customize Button
	 */
	public void clickCustomize() {
		customizeButtonElement.click();
	}

}