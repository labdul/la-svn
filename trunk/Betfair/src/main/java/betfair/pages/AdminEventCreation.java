package betfair.pages;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;



//This class contains Admin Page functions
public class AdminEventCreation extends Page{

	public AdminEventCreation(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String nameLocator = "//*[@id='EvDesc']";
	private final String startTimeLocator = "//table/tbody/tr/td[text()='Start Time']/../td[2]/input[1]";
	private final String addEventButtonLocator = "//tbody/tr[28]/th/table/tbody/tr/th/input[1]";
	private final String addMarketButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Add Market')]";
	private final String identifierLocator = "//table/tbody/tr[2]/td[2]";
	private final String updateEventButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Update Event')]";
	private final String viewAuditButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'View Audit History')]";
	private final String modStartTimeLocator = "//table/tbody/tr[3]/td[11]";
	private final String domainLocator = "//select[contains(@name,'EvFBDomain')]";
	
	/*
	 * ELEMENTS
	 */
	@FindBy(how = How.XPATH, using = nameLocator)
	private WebElement nameElement;

	@FindBy(how = How.XPATH, using = startTimeLocator)
	private WebElement startTimeElement;

	@FindBy(how = How.XPATH, using = addEventButtonLocator)
	private WebElement addEventButtonElement;

	@FindBy(how = How.XPATH, using = addMarketButtonLocator)
	private WebElement addMarketButtonElement;

	@FindBy(how = How.XPATH, using = identifierLocator)
	private WebElement identifierElement;

	@FindBy(how = How.XPATH, using = updateEventButtonLocator)
	private WebElement updateEventButtonElement;

	@FindBy(how = How.XPATH, using = viewAuditButtonLocator)
	private WebElement viewAuditButtonElement;

	@FindBy(how = How.XPATH, using = modStartTimeLocator)
	private WebElement modStartTimeFootballElement;
	
	@FindBy(how = How.XPATH, using = domainLocator)
	private WebElement domainElement;
	
	/*
	 * METHODS
	 */

	/**
	 * Enters Event Name
	 */
	public void insertEventName(String text) {
		nameElement.clear();
		nameElement.sendKeys(text);
	}
	/**
	 * Enters Start Time
	 */
	public void insertStartTime(String text) {
		startTimeElement.clear();
		startTimeElement.sendKeys(text);
	}

	/**
	 * Clicks Add Event
	 */
	public void clickAddEvent() {
		addEventButtonElement.click();
	}
	/**
	 * Clicks Add Market
	 */
	public void clickAddMarket() {
		addMarketButtonElement.click();
	}
	/**
	 * Prints out Event ID
	 */
	public String printID() {
		return identifierElement.getText();
	}
	/**
	 * Clicks Update Event
	 */
	public void clickUpdate() {
		updateEventButtonElement.click();
	}
	/**
	 * Clicks View Audit History
	 */
	public void clickViewAudit() {
		viewAuditButtonElement.click();
	}
	/**
	 * Gets modified start time from Audit
	 * @return 
	 */
	public String getAuditChange() {
		return modStartTimeFootballElement.getText();	
	}
	
	/**
	 * Creates Random Event Name
	 */
	public String randomName() {
	    String s = "";
		String randomString = "abcdefghijklmno";
		Random randomGenerator = new Random();
		for (int i = 0; i < 5; i++) {
			s += randomGenerator.nextInt(9);
			s += randomString.charAt(randomGenerator.nextInt(randomString.length()));
		}
		System.out.println(s);
		return s;
	}
	
	/**
	 * Selects Domain Type
	 */
	
	public void selectDomain(String text) {
		Select selectDomain = new Select(webDriver.findElement(By.xpath(domainLocator)));
		selectDomain.selectByVisibleText(text);
	
	}
}
