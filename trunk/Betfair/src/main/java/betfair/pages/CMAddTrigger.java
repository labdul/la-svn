package betfair.pages;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import au.com.bytecode.opencsv.CSVWriter;
import betfair.util.Frames;

public class CMAddTrigger extends Page{

	public CMAddTrigger(WebDriver webDriver) {
		super(webDriver);
	}
	
	/**
	 * Locators for Trigger Details Page
	 */
	
	private final String addTriggerLocator="//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Add Trigger')]";
	private final String backTriggerLocator="//a[contains(@href,'/campaign_manager')]";
	private final String rankLocator="//form/table/tbody/tr/td[text()='Rank']/../td/input";
	private final String currencyTriggerLocator="//form/table/tbody/tr/td[text()='%s']/../td/input";
	private final String addLevelLocator="//table/tfoot/tr/td/a";
	private final String minPriceLocator="//*[@id='trigger_select']/table/tbody/tr[3]/td[2]/input";
	private final String deleteTriggerLocator="//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Delete Trigger')]";
	private final String deleteLevelLocator="//table/tbody/tr/td[3]/a";
	private final String fileUploadLocator="//*[@id='trigger_select']/table/tbody/tr[4]/td[2]/input";
	private final String modifyTriggerLocator="//*[@id='trigger_select']/table/tbody/tr[5]/td/input[1]";
	private final String selAllTypesLocator="//*[@id='trigger_select']/table/tbody/tr[14]/td[2]/table/tbody/tr[2]/td[3]/input[1]";
	private final String fireLocator="//*[@id='trigger_select']/table/tbody/tr[3]/td/input[1]";
	
	/**
	 * Locators for Trigger Details Page Dropdowns
	 */
	
	private final String typeTriggerLocator="//form/table/tbody/tr[1]/td[2]/select";
	private final String tokenValueTriggerLocator="//form/table/tbody/tr[4]/td[2]/select";
		
	/**
	 * Locators for Trigger Details Page Dropdowns
	 */
	
	private final String betTypesLocator="//*[@id='trigger_select']/table/tbody/tr/td[text()='Bet Types']/../td[2]/table/tbody/tr[2]/td[1]/select";
	
	/**
	 * Elements for Trigger Details Page
	 */
	
	@FindBy(how = How.XPATH, using = fireLocator)
	private WebElement fireElement;
	
	@FindBy(how = How.XPATH, using = addTriggerLocator)
	private WebElement addTriggerElement;
	
	@FindBy(how = How.XPATH, using = backTriggerLocator)
	private WebElement backTriggerElement;
	
	@FindBy(how = How.XPATH, using = deleteTriggerLocator)
	private WebElement deleteTriggerElement;
	
	@FindBy(how = How.XPATH, using = deleteLevelLocator)
	private WebElement deleteLevelElement;
	
	@FindBy(how = How.XPATH, using = rankLocator)
	private WebElement rankElement;
	
	@FindBy(how = How.XPATH, using = minPriceLocator)
	private WebElement minPriceElement;
	
	@FindBy(how = How.XPATH, using = addLevelLocator)
	private WebElement addLevelElement;
	
	@FindBy(how = How.XPATH, using = fileUploadLocator)
	private WebElement fileUploadElement;
	
	@FindBy(how = How.XPATH, using = modifyTriggerLocator)
	private WebElement modifyTriggerElement;

	@FindBy(how = How.XPATH, using = selAllTypesLocator)
	private WebElement selAllTypesElement;
	
	/**
	 * Upload Method
	 */
	
	public void send(String text){
		Frames.selectMainFrame(webDriver);
		fileUploadElement.sendKeys(text);
	}
	
	/**
	 * Dynamic Elements
	 */
	
	private WebElement currencyElement(String currency){
		return webDriver.findElement(By.xpath(String.format(currencyTriggerLocator, currency)));
	}
	
	/**
	 * Dropdown
	 * @param Type Trigger
	 * @throws Throwable
	 */

	public void typeTrigger(String text) {
		Frames.selectMainFrame(webDriver);
		Select typeTrigger = new Select(webDriver.findElement(By.xpath(typeTriggerLocator)));
		typeTrigger.selectByVisibleText(text);	
		
		
	}
	
	/**
	 * Dropdown
	 * @param Token Value Trigger
	 * @throws Throwable
	 */
	
	public void tokenValueTrigger(String text) {
		Frames.selectMainFrame(webDriver);
		Select tokenValueTrigger = new Select(webDriver.findElement(By.xpath(tokenValueTriggerLocator)));   
		tokenValueTrigger.selectByVisibleText(text);		 
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
	 * method for Add Trigger Rank
	 */
	
	public void rank(String text) {
		Frames.selectMainFrame(webDriver);
		rankElement.sendKeys(text);
	}
	
	/**
	 * method for Min Price Rank
	 */
	
	public void minPrice(String text) {
		Frames.selectMainFrame(webDriver);
		minPriceElement.sendKeys(text);
	}
	
	/**
	 * method for Add Trigger currency
	 */
	
	public void insertCurrency(String currency, String text) {
		Frames.selectMainFrame(webDriver);
		currencyElement(currency).sendKeys(text);
	}
	
	/**
	 * Click methods for Add Trigger Page
	 */
	
	public void addTrigger() {
		Frames.selectMainFrame(webDriver);
		addTriggerElement.click();
	}
	
	public void backTrigger() {
		Frames.selectMainFrame(webDriver);
		backTriggerElement.click();
	}
	
	public void addLevel() {
		Frames.selectMainFrame(webDriver);
		addLevelElement.click();
	}
	
	public void deleteTrigger() {
		Frames.selectMainFrame(webDriver);
		deleteTriggerElement.click();
	}
	
	public void deleteLevel() {
		Frames.selectMainFrame(webDriver);
		deleteLevelElement.click();
	}
	
	public void modifyTrigger() {
		Frames.selectMainFrame(webDriver);
		modifyTriggerElement.click();
	}
	
	public void selAllTypes() {
		Frames.selectMainFrame(webDriver);
		selAllTypesElement.click();
	}
	
	public void fire() {
		Frames.selectMainFrame(webDriver);
		fireElement.click();
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
	
	/**
	 * CSV creator for Add Trigger Page
	 */
	
	public void writecsv(String path, String username, String ccyCode, String usernameData, String ccyCodeData)  {
		String csv = path;
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(csv));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {username,ccyCode});
		data.add(new String[] {usernameData,ccyCodeData});

		writer.writeAll(data);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
