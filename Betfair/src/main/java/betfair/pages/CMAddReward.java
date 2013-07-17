package betfair.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import betfair.util.Frames;

public class CMAddReward extends Page{

	public CMAddReward(WebDriver webDriver) {
		super(webDriver);
	}

	/**
	 * Locators for Add Reward Page
	 */
	
	private final String insertLocator="//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Insert')]";
	private final String backRewardLocator="//a[contains(@href,'/campaign_manager')] ";
	private final String selAllTypesLocator="//form/table/tbody/tr[15]/td[2]/table/tbody/tr[2]/td[3]/input[1]";
	private final String resetTypeLocator="//form/table/tbody/tr[15]/td[2]/table/tbody/tr[2]/td[3]/input[2]";
	private final String currencyRewardLocator="//td[contains(@class,'currency')]/input[contains(@name,'%s')]";
	private final String minOddsLocator="//form/table/tbody/tr/td[text()='Minimum Odds']/../td/input";
	private final String absExpLocator="//form/table/tbody/tr/td[text()='Absolute Expiry']/../td/input";
	private final String relExpLocator="//form/table/tbody/tr/td[text()='Relative Expiry']/../td/input";
	private final String addRedemptionLocator="//*[@id='redemptionValues']/tbody[2]/tr/td/input";
	private final String anyLocator="//form/table/tbody[1]/tr[1]/td[5]/a";
	private final String removeLocator="//*[@id='redemptionValues']/tbody[1]/tr/td[4]/input";
	private final String deleteLocator="//form[1]/table/tbody/tr[7]/td/input[2]";
	private final String removeRedempLocator="//*[@id='redemptionValues']/tbody[1]/tr/td[4]/input";
	
	/**
	 * Locators for Bet Types List
	 */
	
	private final String betTypesLocator="//table/tbody/tr/td[text()='Bet Types']/../td[2]/table/tbody/tr[2]/td[1]/select";
	
	/*
	 * Dynamic Elements
	 */
	
	private WebElement currencyElement(String currency){
		return webDriver.findElement(By.xpath(String.format(currencyRewardLocator, currency)));
	}
	
	/**
	 * Elements for Add Reward Page
	 */
	
	@FindBy(how = How.XPATH, using = removeRedempLocator)
	private WebElement removeRedempElement;
	
	@FindBy(how = How.XPATH, using = insertLocator)
	private WebElement insertElement;
	
	@FindBy(how = How.XPATH, using = backRewardLocator)
	private WebElement backRewardElement;
	
	@FindBy(how = How.XPATH, using = removeLocator)
	private WebElement removeElement;
	
	@FindBy(how = How.XPATH, using = deleteLocator)
	private WebElement deleteElement;
	
	@FindBy(how = How.XPATH, using = anyLocator)
	private WebElement anyElement;
	
	@FindBy(how = How.XPATH, using = selAllTypesLocator)
	private WebElement selAllTypesElement;
	
	@FindBy(how = How.XPATH, using = resetTypeLocator)
	private WebElement resetTypeElement;
	
	@FindBy(how = How.XPATH, using = minOddsLocator)
	private WebElement minOddsElement;
	
	@FindBy(how = How.XPATH, using = absExpLocator)
	private WebElement absExpElement;
	
	@FindBy(how = How.XPATH, using = relExpLocator)
	private WebElement relExpElement;
	
	@FindBy(how = How.XPATH, using = addRedemptionLocator)
	private WebElement addRedemptionElement;
	
	/**
	 *  method for Relative Expiry
	 */
	
	public void relativeExpiry(String text) {
		Frames.selectMainFrame(webDriver);
		relExpElement.sendKeys(text);
	}

	/**
	 * Dropdown
	 * @param Bet Types
	 * @throws Throwable
	 */
	
	public void betTypes(String text) {
		Frames.selectMainFrame(webDriver);
		Select betTypes = new Select(webDriver.findElement(By.xpath(betTypesLocator)));   
		betTypes.selectByVisibleText(text);		 
	}
	
	/**
	 * method for Add Trigger currency
	 */
	
	public void insertCurrency(String currency, String text) {
		Frames.selectMainFrame(webDriver);
		currencyElement(currency).sendKeys(text);
	}
	
	/**
	 * Click methods for Insert Reward
	 */
	
	public void insertReward() {
		Frames.selectMainFrame(webDriver);
		insertElement.click();
	}
	
	public void removeRedemp() {
		Frames.selectMainFrame(webDriver);
		removeRedempElement.click();
	}
	
	public void backReward() {
		Frames.selectMainFrame(webDriver);
		backRewardElement.click();
	}
	
	public void removeLevel() {
		Frames.selectMainFrame(webDriver);
		removeElement.click();
	}
	
	public void deleteReward() {
		Frames.selectMainFrame(webDriver);
		deleteElement.click();
	}
	
	public void selAllReward() {
		Frames.selectMainFrame(webDriver);
		selAllTypesElement.click();
	}
	
	public void resetTypeReward() {
		Frames.selectMainFrame(webDriver);
		resetTypeElement.click();
	}
	
	public void addRedemption() {
		Frames.selectMainFrame(webDriver);
		addRedemptionElement.click();
	}
	
	public void any() {
		Frames.selectMainFrame(webDriver);
		anyElement.click();
	}
	
	public void acceptAlert() {
		Alert alertAccept = webDriver.switchTo().alert();  
		alertAccept.accept();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
		
}

