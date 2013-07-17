package betfair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import betfair.util.Frames;


//This class contains Admin Page functions
public class AdminEventSelectionCriteria extends Page{

	public AdminEventSelectionCriteria(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */

	private final String eventClassLocator = "//*[@id='ClassId']";
	private final String eventLocator = "//form[1]/table/tbody/tr[2]/td[2]/select[2]";
	private final String dateRangeLocator = "//*[@id='date_range']";
	private final String dateLowLocator = "//*[@id='date_lo']";
	private final String dateHighLocator = "//*[@id='date_hi']";
	private final String settleLocator = "//select[@name='settled']";
	private final String resultsLocator = "//form[1]/table/tbody/tr[4]/td[2]/select[2]";
	private final String statusLocator = "//form[1]/table/tbody/tr[5]/td[2]/select";
	private final String allowSettleLocator = "//form[1]/table/tbody/tr[6]/td[2]/select";
	private final String showEventsButtonLocator = "//form[1]/table/tbody/tr[7]/th/table/tbody/tr/th[1]/input[1]";
	private final String addEventButtonLocator = "//form[1]/table/tbody/tr[7]/th/table/tbody/tr/th[1]/input[2]";
	private final String unmatchedBFButtonLocator = "//form[1]/table/tbody/tr[7]/th/table/tbody/tr/th[1]/input[3]";
	private final String suspendedClassesButtonLocator = "//form[1]/table/tbody/tr[7]/th/table/tbody/tr/th[2]/input[1]";
	private final String quickSetupButtonLocator = "//form[1]/table/tbody/tr[8]/th/input[1]";
	private final String quickResultButtonLocator = "//form[1]/table/tbody/tr[8]/th/input[2]";
	private final String quickConfirmButtonLocator = "//form[1]/table/tbody/tr[8]/th/input[3]";


	/*
	 * ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = eventClassLocator)
	private WebElement eventTypeElement; 

	@FindBy(how = How.XPATH, using = eventLocator)
	private WebElement eventElement;

	@FindBy(how = How.XPATH, using = dateRangeLocator)
	private WebElement dateRangeElement;

	@FindBy(how = How.XPATH, using = dateLowLocator)
	private WebElement dateLowElement;

	@FindBy(how = How.XPATH, using = dateHighLocator)
	private WebElement dateHighElement;

	@FindBy(how = How.XPATH, using = settleLocator)
	private WebElement settleElement;

	@FindBy(how = How.XPATH, using = resultsLocator)
	private WebElement resultsElement;

	@FindBy(how = How.XPATH, using = statusLocator)
	private WebElement statusElement;

	@FindBy(how = How.XPATH, using = allowSettleLocator)
	private WebElement allowSettleElement;

	@FindBy(how = How.XPATH, using = showEventsButtonLocator)
	private WebElement showEventsButtonElement;

	@FindBy(how = How.XPATH, using = addEventButtonLocator)
	private WebElement addEventButtonElement;

	@FindBy(how = How.XPATH, using = unmatchedBFButtonLocator)
	private WebElement unmatchedBFButtonElement;

	@FindBy(how = How.XPATH, using = suspendedClassesButtonLocator)
	private WebElement suspendedClassesButtonElement;

	@FindBy(how = How.XPATH, using = quickSetupButtonLocator)
	private WebElement quickSetupButtonElement;

	@FindBy(how = How.XPATH, using = quickResultButtonLocator)
	private WebElement quickResultButtonElement;

	@FindBy(how = How.XPATH, using = quickConfirmButtonLocator)
	private WebElement quickConfirmButtonElement;

	/*
	 * METHODS
	 */

	/**
	 * This method selects Event Class
	 */
	public void selectClass(String text) {
		Frames.selectMainAreaFrame(webDriver);
		Select selectClass = new Select(webDriver.findElement(By.xpath(eventClassLocator)));   
		selectClass.selectByVisibleText(text);
		 
	}

	/**
	 * This method selects an Event Type
	 */
	public void selectType(String text) {
		Frames.selectMainAreaFrame(webDriver);
		Select selectType = new Select(webDriver.findElement(By.xpath(eventLocator)));   
		selectType.selectByVisibleText(text);
	}
	/**
	 * This method selects Date Range 
	 */
	public void selectDate(String text) {
		Frames.selectMainAreaFrame(webDriver);
		Select selectDate = new Select(webDriver.findElement(By.xpath(dateRangeLocator)));   
		selectDate.selectByVisibleText(text);
	}
	
	/**
	 *This method selects Settled status 
	 */
	public void selectSettled(String text) {
		Frames.selectMainAreaFrame(webDriver);
		Select selectSettled = new Select(webDriver.findElement(By.xpath(settleLocator)));
		selectSettled.selectByVisibleText(text);
	}
	
	/**
	 * Click methods for Event Search buttons
	 */
	public void clickShowEvents() {
		Frames.selectMainAreaFrame(webDriver);
		showEventsButtonElement.click();
	}
	public void clickAddEvent() {
		Frames.selectMainAreaFrame(webDriver);
		addEventButtonElement.click();
	}
	public void clickShowUnmatched() {
		Frames.selectMainAreaFrame(webDriver);
		unmatchedBFButtonElement.click();
	}
	public void clickShowSuspended() {
		Frames.selectMainAreaFrame(webDriver);
		suspendedClassesButtonElement.click();
	}
	public void clickQuickSetup() {
		Frames.selectMainAreaFrame(webDriver);
		quickSetupButtonElement.click();
	}
	public void clickQuickResult() {
		Frames.selectMainAreaFrame(webDriver);
		quickResultButtonElement.click();
	}
	public void clickQuickConfirm() {
		Frames.selectMainAreaFrame(webDriver);
		quickConfirmButtonElement.click();
	}
	/**
	 * This method inserts date range for previously automated created events
	 */
	public void insertDates() {
		dateLowElement.sendKeys("2014-03-30");
		dateHighElement.sendKeys("2014-03-31");
	}
	/**
	 * This method inserts the standard date for modifiable events
	 */
	public void insertModDates() {
		dateLowElement.sendKeys("2016-01-01");
		dateHighElement.sendKeys("2016-01-02");
		}
}

