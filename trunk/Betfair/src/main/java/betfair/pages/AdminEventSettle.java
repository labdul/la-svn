package betfair.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import betfair.util.Frames;



//This class contains Admin Page functions
public class AdminEventSettle extends Page{

	public AdminEventSettle(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String eventNameLocator = "//table/tbody/tr[3]/td[4]/a";
	private final String marketLocator = "//form/table/tbody/tr[3]/td[1]/a";
	private final String firstSelectionLocator = "//form/table[2]/tbody/tr[3]/td[1]/a";
	private final String secondSelectionLocator = "//form/table[2]/tbody/tr[5]/td[1]/a";
	private final String thirdSelectionLocator = "//form/table[2]/tbody/tr[7]/td[1]/a";
	private final String resultDropboxLocator = "//form/table[2]/tbody/tr[3]/td[1]/select";
	private final String placeBoxLocator = "//form/table[2]/tbody/tr[3]/td[2]/input";
	private final String setResultsButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Set Selection Result')]";
	private final String backbuttonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Back')]";
	private final String settleMarketButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Settle Market')]";
	private final String confirmResultsLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Confirm Results')]";
	private final String settlementResultsTextLocator = "//table/tbody/tr[1]/th";
	/*
	 * ELEMENTS
	 */
	@FindBy(how = How.XPATH, using = eventNameLocator)
	private WebElement eventNameElement;

	@FindBy(how = How.XPATH, using = marketLocator)
	private WebElement marketElement;

	@FindBy(how = How.XPATH, using = firstSelectionLocator)
	private WebElement firstSelectionElement;

	@FindBy(how = How.XPATH, using = secondSelectionLocator)
	private WebElement secondSelectionElement;

	@FindBy(how = How.XPATH, using = thirdSelectionLocator)
	private WebElement thirdSelectionElement;
	
	@FindBy(how = How.XPATH, using = resultDropboxLocator)
	private WebElement resultDropboxElement;
	
	@FindBy(how = How.XPATH, using = placeBoxLocator)
	private WebElement placeBoxElement;
	
	@FindBy(how = How.XPATH, using = setResultsButtonLocator)
	private WebElement setResultsButtonElement;
	
	@FindBy(how = How.XPATH, using = backbuttonLocator)
	private WebElement backButtonElement;
	
	@FindBy(how = How.XPATH, using = settleMarketButtonLocator)
	private WebElement settleMarketButtonElement;
	
	@FindBy(how = How.XPATH, using = confirmResultsLocator)
	private WebElement confirmResultsElement;
	
	@FindBy(how = How.XPATH, using = settlementResultsTextLocator)
	private WebElement settlementResultsTextElement;
	
	/*
	 * METHODS
	 */

	/**
	 * Click Methods
	 */
	public void clickEvent() {
		Frames.selectMainAreaFrame(webDriver);
		eventNameElement.click();
	}
	public void clickMarket() {
		Frames.selectMainAreaFrame(webDriver);
		marketElement.click();
	}
	public void clickFirstSelection() {
		Frames.selectMainAreaFrame(webDriver);
		firstSelectionElement.click();
	}
	public void clickSecondSelection() {
		Frames.selectMainAreaFrame(webDriver);
		secondSelectionElement.click();
	}
	public void clickThirdSelection() {
		Frames.selectMainAreaFrame(webDriver);
		thirdSelectionElement.click();
	}
	public void clickSetResults() {
		Frames.selectMainAreaFrame(webDriver);
		setResultsButtonElement.click();
	}
	public void clickBackButton() {
		Frames.selectMainAreaFrame(webDriver);
		backButtonElement.click();
	}
	public void clickSettleMarket() {
		Frames.selectMainAreaFrame(webDriver);
		settleMarketButtonElement.click();
	}
	public void clickConfirmResults() {
		Frames.selectMainAreaFrame(webDriver);
		confirmResultsElement.click();
	}
	/**
	 * Selects Result Type
	 */
	public void selectResult(String text) {
		Select selectResult = new Select(webDriver.findElement(By.xpath(resultDropboxLocator)));   
		selectResult.selectByVisibleText(text);
	}
	/**
	 * Inserts place number
	 */
	public void insertPlace(String text) {
		placeBoxElement.sendKeys(text);
	}
	/**
	 * Gets Settlement results text
	 */
	public String settlementResults() {
		return settlementResultsTextElement.getText();
	}
}