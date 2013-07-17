package betfair.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;



//This class contains Admin Page functions
public class AdminSelectionCreation extends Page{

	public AdminSelectionCreation(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String descriptionLocator = "//form/table/tbody/tr/td[text()='Description']/../td/input[1]";
	private final String fixedPriceLocator = "//input[contains(@id,'OcLPField') and contains (@type,'text') and contains (@value,'') and contains (@size,'8') and contains (@name,'OcLP')]";
	private final String addSelectionLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Add Market Selection')]";
	private final String addFinalSelectionLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Add Selection')]";
	private final String selectionLocator = "//form/table[2]/tbody/tr[3]/td[1]/a";
	private final String modifySelectionLocator = "//input[contains(@type,'button') and contains (@onclick,'fs') and contains (@value,'Modify Selection')]";
	private final String viewAuditLocator = "//input[contains(@type,'button') and contains (@onclick,'fs') and contains (@value,'View Audit History')]";
	private final String displayOrderLocator = "//form/table[1]/tbody/tr[4]/td[2]/input[1]";
	private final String internetCheckLocator = "//form/table[1]/tbody/tr[18]/td[2]/table/tbody/tr/td[1]/input";
	private final String locationLocator = "//select[contains(@name,'OcFlag')]";
	private final String idLocator = "//form/table[1]/tbody/tr[2]/td[2]";
	private final String spPriceLocator = "//input[contains(@type,'text') and contains (@value,'') and contains (@size,'8') and contains (@name,'OcSPGuide')]";
	private final String selectionStatusLocator = "//select[contains(@name,'OcStatus')]";
	private final String forceRemovalCheckLocator = "//input[contains(@type,'checkbox') and contains (@name,'null_price') and contains (@onclick,'forceRemovalHandler')]";
	private final String backButtonLocator = "//input[contains(@class,'btn_def') and contains (@type,'button') and contains (@onclick,'return') and contains (@value,'Back')]";
	/*
	 * ELEMENTS
	 */
	@FindBy(how = How.XPATH, using = descriptionLocator)
	private WebElement descriptionElement;

	@FindBy(how = How.XPATH, using = fixedPriceLocator)
	private WebElement fixedPriceElement;

	@FindBy(how = How.XPATH, using = addSelectionLocator)
	private WebElement addSelectionElement;

	@FindBy(how = How.XPATH, using = addFinalSelectionLocator)
	private WebElement addFinalSelectionElement;

	@FindBy(how = How.XPATH, using = selectionLocator)
	private WebElement selectionElement;

	@FindBy(how = How.XPATH, using = modifySelectionLocator)
	private WebElement modifySelectionElement;

	@FindBy(how = How.XPATH, using = viewAuditLocator)
	private WebElement viewAuditElement;

	@FindBy(how = How.XPATH, using = displayOrderLocator)
	private WebElement displayOrderElement;

	@FindBy(how = How.XPATH, using = internetCheckLocator)
	private WebElement internetCheckElement;

	@FindBy(how = How.XPATH, using = locationLocator)
	private WebElement locationElement;

	@FindBy(how = How.XPATH, using = idLocator)
	private WebElement idElement;

	@FindBy(how = How.XPATH, using = spPriceLocator)
	private WebElement spPriceElement;

	@FindBy(how = How.XPATH, using = selectionStatusLocator)
	private WebElement selectionStatusElement;
	
	@FindBy(how = How.XPATH, using = forceRemovalCheckLocator)
	private WebElement forceRemovalCheckElement;
	
	@FindBy(how = How.XPATH, using = backButtonLocator)
	private WebElement backButtonElement;

	/*
	 * METHODS
	 */
	/**
	 * Inserts Selection Description
	 */
	public void enterDescription(String text) {
		descriptionElement.clear();
		descriptionElement.sendKeys(text);
	}

	/**
	 * Inserts Fixed Price
	 */
	public void enterFixedPrice(String text) {
		fixedPriceElement.clear();
		fixedPriceElement.sendKeys(text);
	}

	/**
	 * Clicks Methods
	 */
	public void clickAddSelection() {
		addSelectionElement.click();
	}

	public void clickAddFinalSelection() {
		addFinalSelectionElement.click();
	}

	public void clickSelection() {
		selectionElement.click();
	}

	public void clickModify() {
		modifySelectionElement.click();
	}

	public void clickAudit() {
		viewAuditElement.click();
	}

	public void clickInternet() {
		internetCheckElement.click();
	}
	
	public void clickForceRemoval() {
		forceRemovalCheckElement.click();
	}
	
	public void clickBack() {
		backButtonElement.click();
	}
	
	/**
	 * Clicks on Confirmation Pop-up
	 */

	public void acceptAlert() {
		Alert alertAccept = webDriver.switchTo().alert();  
		alertAccept.accept();
	}
	/**
	 * Inserts Display Order
	 */
	public void insertDisplayOrder(String text) {
		displayOrderElement.clear();
		displayOrderElement.sendKeys(text);
	}

	/**
	 * Selects Team Location
	 */
	public void selectLocation(String text) {
		Select selectLocation = new Select(webDriver.findElement(By.xpath(locationLocator)));
		selectLocation.selectByVisibleText(text);
	}

	/**
	 * Gets Selection ID
	 */
	public String getEventid() {
		return idElement.getText();
	}

	/**
	 * Enters SP Guide
	 */
	public void enterSpGuide(String text) {
		spPriceElement.clear();
		spPriceElement.sendKeys(text);
	}

	/**
	 * Chooses Selection Status
	 */
	public void selectStatus(String text) {
		Select selectStatus = new Select(webDriver.findElement(By.xpath(selectionStatusLocator)));
		selectStatus.selectByVisibleText(text);
	}

}