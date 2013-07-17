package betfair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import betfair.util.Frames;



//This class contains Admin Page functions
public class AdminMarketCreation extends Page{

	public AdminMarketCreation(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String addMarketButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Add Market')]";
	private final String addSelectionButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Add Market Selection')]";
	private final String backButtonLocator = "//form/table[1]/tbody/tr[38]/th/table/tbody/tr/th[1]/input[15]";
	private final String marketDropdownLocator = "//form/table/tbody/tr[3]/th/select";
	private final String addEventMarketButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Add Event Market')]";
	private final String raceWinnerMarketLocator = "//a[contains(text(),'Race Winner')]";
	private final String displayedLocator = "//form/table[1]/tbody/tr[7]/td[2]/select";
	private final String livePriceLocator = "//form/table/tbody/tr[8]/td[2]/select[1]";
	private final String spPriceLocator = "//form/table/tbody/tr[8]/td[2]/select[2]";
	private final String marketStatusLocator = "//select[contains(@name,'MktStatus')]";
	private final String modifyButtonLocator = "//input[contains(@type,'button') and contains(@onclick,'return') and contains(@name,'mod_mkt')]";
	private final String higherlowerLocator = "//input[contains(@type,'text') and contains (@size,'10') and contains (@value,'') and contains (@name,'MktHcapValue')]";
	private final String firstMarketLocator = "//a[contains(@href,'admin?action=ADMIN::MARKET') and contains (text(),'%s')]";
	
	/*
	 * FIXED ELEMENTS
	 */
	@FindBy(how = How.XPATH, using = addMarketButtonLocator)
	private WebElement addMarketButtonElement;

	@FindBy(how = How.XPATH, using = addSelectionButtonLocator)
	private WebElement addSelectionButtonElement;

	@FindBy(how = How.XPATH, using = backButtonLocator)
	private WebElement backButtonElement;

	@FindBy(how = How.XPATH, using = marketDropdownLocator)
	private WebElement marketDropdownElement;

	@FindBy(how = How.XPATH, using = addEventMarketButtonLocator)
	private WebElement addEventMarketButtonElement;

	@FindBy(how = How.XPATH, using = raceWinnerMarketLocator)
	private WebElement raceWinnerMarketElement;

	@FindBy(how = How.XPATH, using = displayedLocator)
	private WebElement displayedElement;

	@FindBy(how = How.XPATH, using = livePriceLocator)
	private WebElement livePriceElement;

	@FindBy(how = How.XPATH, using = spPriceLocator)
	private WebElement spPriceElement;
	
	@FindBy(how = How.XPATH, using = marketStatusLocator)
	private WebElement marketStatusElement;
	
	@FindBy(how = How.XPATH, using = modifyButtonLocator)
	private WebElement modifyButtonElement;
	
	@FindBy(how = How.XPATH, using = higherlowerLocator)
	private WebElement higherlowerElement;
	
	
	/*
	 * DYNAMIC ELEMENTS
	 */
	
	private WebElement firstMarketElement (String text) {
		return webDriver.findElement(By.xpath(String.format(firstMarketLocator, text)));
	}
	
	
	/*
	 * METHODS
	 */

	/**
	 * Selects Displayed Status
	 */
	public void selectDisplayed(String text) {
		Select selectDisplayed = new Select(webDriver.findElement(By.xpath(displayedLocator)));   
		selectDisplayed.selectByVisibleText(text);
	}

	/**
	 * Selects Market type
	 */
	public void selectMarket(String text) {
		Select selectMarket = new Select(webDriver.findElement(By.xpath(marketDropdownLocator)));   
		selectMarket.selectByVisibleText(text);
	}

	/**
	 * Click Methods
	 */
	public void clickBack() {
		Frames.selectMainAreaFrame(webDriver);
		backButtonElement.click();

	}
	public void clickAddMarket() {
		Frames.selectMainAreaFrame(webDriver);
		addMarketButtonElement.click();

	}
	public void clickAddSelection() {
		Frames.selectMainAreaFrame(webDriver);
		addSelectionButtonElement.click();
	}
	public void clickAddEventMarket() {
		Frames.selectMainAreaFrame(webDriver);
		addEventMarketButtonElement.click();

	}
	public void clickRaceWinnerMarket() {
		raceWinnerMarketElement.click();
	}
	
	public void clickModifyMarket() {
		modifyButtonElement.click();
	}
	
	public void clickFirstMarket(String text) { 
		firstMarketElement(text).click();
	}
	
	/**
	 * Selects Options for Live Price
	 */
	public void selectLive(String text) {
		Select selectLive = new Select(webDriver.findElement(By.xpath(livePriceLocator)));   
		selectLive.selectByVisibleText(text);
	}

	/**
	 * Selects Options for SP
	 */

	public void selectSp(String text) {
		Select selectSp = new Select(webDriver.findElement(By.xpath(spPriceLocator)));   
		selectSp.selectByVisibleText(text);
	}
	
	/**
	 * Selects Market Status
	 */

	public void selectMarketStatus(String text) {
		Select selectMarketStatus = new Select(webDriver.findElement(By.xpath(marketStatusLocator)));   
		selectMarketStatus.selectByVisibleText(text);
	}
	
	/**
	 * Sets Higher/Lower Value for Handicap Markets
	 */
	public void insertHigherLower(String text) {
		higherlowerElement.sendKeys(text);
	}
}
