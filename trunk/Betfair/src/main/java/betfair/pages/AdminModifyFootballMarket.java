package betfair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;



//This class contains Admin Page functions
public class AdminModifyFootballMarket extends Page{

	public AdminModifyFootballMarket(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String displayOrderLocator = "//form/table/tbody/tr[7]/td/input[1]";
	private final String displayedLocator = "//form/table/tbody/tr[7]/td/select[1]";
	private final String blurbLocator = "//form/table/tbody/tr[15]/td/textarea";
	private final String deadHeatLocator = "//form/table/tbody/tr[21]/td/select[1]";
	private final String addMarketLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Add Market')]";
	private final String modifyMarketLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'Modify Market')]";
	private final String marketLocator = "//form/table/tbody/tr[3]/td[1]/a";
	private final String viewAuditLocator = "//input[contains(@type,'button') and contains(@onclick,'return fs')and contains(@value,'View Audit History')]";
	private final String auditDisplayedLocator = "//table/tbody/tr[4]/td[11]";
	private final String auditDisplayOrderLocator = "//table/tbody/tr[4]/td[12]";
	private final String auditBlurbLocator = "//table/tbody/tr[4]/td[48]";
	private final String auditDeadHeatLocator = "//table/tbody/tr[4]/td[74]";
	
	/*
	 * ELEMENTS
	 */
	@FindBy(how = How.XPATH, using = displayOrderLocator)
	private WebElement displayOrderElement;

	@FindBy(how = How.XPATH, using = displayedLocator)
	private WebElement displayedElement;

	@FindBy(how = How.XPATH, using = blurbLocator)
	private WebElement blurbElement;

	@FindBy(how = How.XPATH, using = deadHeatLocator)
	private WebElement deadHeatElement;

	@FindBy(how = How.XPATH, using = addMarketLocator)
	private WebElement addMarketElement;

	@FindBy(how = How.XPATH, using = modifyMarketLocator)
	private WebElement modifyMarketElement;

	@FindBy(how = How.XPATH, using = marketLocator)
	private WebElement marketElement;

	@FindBy(how = How.XPATH, using = viewAuditLocator)
	private WebElement viewAuditElement;
	
	@FindBy(how = How.XPATH, using = auditDisplayedLocator)
	private WebElement auditDisplayElement;
	
	@FindBy(how = How.XPATH, using = auditDisplayOrderLocator)
	private WebElement auditDisplayOrderElemet;
	
	@FindBy(how = How.XPATH, using = auditBlurbLocator)
	private WebElement auditBlurbElemet;
	
	@FindBy(how = How.XPATH, using = auditDeadHeatLocator)
	private WebElement auditDeadHeatElement;

	/*
	 * METHODS
	 */
	/**
	 * Inserts Display Order 
	 */

	public void insertDisplayOrder(String text) {
		displayOrderElement.clear();
		displayOrderElement.sendKeys(text);
	}

	/**
	 * Selects Displayed Value 
	 */

	public void selectDisplayed(String text) {
		Select selectDisplayed = new Select(webDriver.findElement(By.xpath(displayedLocator)));
		selectDisplayed.selectByVisibleText(text);
	}

	/**
	 * Inserts Blurb Text 
	 */

	public void insertBlurb(String text) {
		blurbElement.sendKeys(text);
	}

	/**
	 * Selects Dead Heat Value 
	 */

	public void selectDeadHeat(String text) {
		Select selectDeadHeat = new Select(webDriver.findElement(By.xpath(deadHeatLocator)));
		selectDeadHeat.selectByVisibleText(text);
	}

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
	 * Gets Audit info  
	 */
	
	public String getAuditDisplay() {
		return auditDisplayElement.getText();
	}
	public String getAuditDisplayOrder() {
		return auditDisplayOrderElemet.getText();
	}
	public String getAuditBlurb() {
		return auditBlurbElemet.getText();
	}
	public String getAuditDeadHeat() {
		return auditDeadHeatElement.getText();
	}
}