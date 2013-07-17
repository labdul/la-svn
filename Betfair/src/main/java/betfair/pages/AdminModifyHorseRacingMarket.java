package betfair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;




//This class contains Admin Page functions
public class AdminModifyHorseRacingMarket extends Page{

	public AdminModifyHorseRacingMarket(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String ewLocator = "//form/table[1]/tbody/tr[11]/td[2]/select[1]";
	private final String placesLocator = "//form/table[1]/tbody/tr[11]/td[2]/input[1]";
	private final String atoneLocator = "//form/table[1]/tbody/tr[11]/td[2]/input[2]";
	private final String attwoLocator = "//form/table[1]/tbody/tr[11]/td[2]/input[3]";
	private final String addMarketLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Add Market')]";
	private final String modifyMarketLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Modify Market')]";
	private final String marketLocator = "//form/table/tbody/tr[3]/td[1]/a";
	private final String viewAuditLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'View Audit History')]";
	private final String auditEwLocator = "//table/tbody/tr[4]/td[14]";
	private final String auditPlacesLocator = "//table/tbody/tr[4]/td[19]";
	private final String auditAtOneLocator = "//table/tbody/tr[4]/td[17]";
	private final String auditAtTwoLocator = "//table/tbody/tr[4]/td[18]";
	private final String birDelayLocator = "//input[contains(@type,'text') and contains(@size,'4') and contains(@name,'MktBirDelay')]";
	private final String birDropLocator = "//select[contains(@name,'MktBetInRun')]";

	/*
	 * ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = ewLocator)
	private WebElement ewElement;

	@FindBy(how = How.XPATH, using = placesLocator)
	private WebElement placesElement;

	@FindBy(how = How.XPATH, using = atoneLocator)
	private WebElement atoneElement;

	@FindBy(how = How.XPATH, using = attwoLocator)
	private WebElement attwoElement;

	@FindBy(how = How.XPATH, using = addMarketLocator)
	private WebElement addMarketElement;

	@FindBy(how = How.XPATH, using = modifyMarketLocator)
	private WebElement modifyMarketElement;

	@FindBy(how = How.XPATH, using = marketLocator)
	private WebElement marketElement;

	@FindBy(how = How.XPATH, using = viewAuditLocator)
	private WebElement viewAuditElement;

	@FindBy(how = How.XPATH, using = auditEwLocator)
	private WebElement auditEwElement;

	@FindBy(how = How.XPATH, using = auditPlacesLocator)
	private WebElement auditPlacesElement;

	@FindBy(how = How.XPATH, using = auditAtOneLocator)
	private WebElement auditAtOneElement;

	@FindBy(how = How.XPATH, using = auditAtTwoLocator)
	private WebElement auditAtTwoElement;

	@FindBy(how = How.XPATH, using = birDelayLocator)
	private WebElement birDelayElement;

	@FindBy(how = How.XPATH, using = birDropLocator)
	private WebElement birDropElement;

	/*
	 * METHODS
	 */

	/**
	 * Click Methods
	 */

	public void clickAddMarket() {
		addMarketElement.click();
	}

	public void clickModifyMarket() {
		modifyMarketElement.click();
	}

	public void clickMarket() {
		marketElement.click();
	}

	public void clickViewAudit() {
		viewAuditElement.click();
	}

	/**
	 * Selects Each-Way Value
	 */

	public void selectEw(String text) {
		Select selectEachway = new Select(webDriver.findElement(By.xpath(ewLocator)));
		selectEachway.selectByVisibleText(text);
	}

	/**
	 * Inserts Places 
	 */

	public void insertPlaces(String text) {
		placesElement.sendKeys(text);
	}

	/**
	 * Inserts at One
	 */
	public void insertAtOne(String text) {
		atoneElement.sendKeys(text);
	}

	/**
	 * Inserts at Two
	 * 
	 */

	public void insertAtTwo(String text) {
		attwoElement.sendKeys(text);
	}

	/**
	 * Gets Audit Info
	 */

	public String getAuditEw() {
		return auditEwElement.getText();
	}

	public String getAuditPlaces() {
		return auditPlacesElement.getText();
	}

	public String getAuditAtOne() {
		return auditAtOneElement.getText();
	}

	public String getAuditAtTwo() {
		return auditAtTwoElement.getText();
	}

	/**
	 * Inserts BIR delay
	 */
	public void insertBirDelay(String text) {
		birDelayElement.clear();
		birDelayElement.sendKeys(text);
	}

	/**
	 * Selects BIR status
	 */
	public void selectBir(String text) {
		Select selectBir = new Select(webDriver.findElement(By.xpath(birDropLocator)));   
		selectBir.selectByVisibleText(text);
	}
}