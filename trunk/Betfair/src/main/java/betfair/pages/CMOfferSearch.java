package betfair.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import betfair.util.Frames;

public class CMOfferSearch extends Page{

	public CMOfferSearch(WebDriver webDriver) {
		super(webDriver);
	}

	/**
	 * Locators for Offer create/search
	 */
	
	
	private final String offerSearchNameLocator="//form/table/tbody/tr[1]/td[2]/input";
	private final String searchOfferLocator="//form/table/tbody/tr[10]/td/input";
	private final String addOfferLocator="//form/table/tbody/tr[10]/td/a";
	private final String startLocator="//form/table/tbody/tr[2]/td[2]/input[1]";
	private final String startAndLocator="//form/table/tbody/tr[2]/td[2]/input[2]";
	private final String endsLocator="//form/table/tbody/tr[3]/td[2]/input[1]";
	private final String endsAndLocator="//form/table/tbody/tr[3]/td[2]/input[2]";
	
	/**
	 * Locators for dropdowns Offers page
	 */
	
	private final String channelLocator="//form/table/tbody/tr[4]/td[2]/select";
	private final String languageLocator="//form/table/tbody/tr[5]/td[2]/select";
	private final String wellFormedLocator="//form/table/tbody/tr[6]/td[2]/select";
	private final String currentlyRunningLocator="//form/table/tbody/tr[7]/td[2]/select";
	private final String offerCategoriesLocator="//form/table/tbody/tr[8]/td[2]/select";
	private final String triggerTypesLocator="//form/table/tbody/tr[9]/td[2]/select";
		
	/**
	 * Elements for Offer create/search
	 */
		
	@FindBy(how = How.XPATH, using = offerSearchNameLocator)
	private WebElement offerSearchNameElement;
	
	@FindBy(how = How.XPATH, using = startLocator)
	private WebElement startElement;
	
	@FindBy(how = How.XPATH, using = startAndLocator)
	private WebElement startAndElement;
	
	@FindBy(how = How.XPATH, using = endsLocator)
	private WebElement endsElement;
	
	@FindBy(how = How.XPATH, using = endsAndLocator)
	private WebElement endsAndElement;
	
	/**
	 * Elements for Offer create/search buttons
	 */
	
	@FindBy(how = How.XPATH, using = searchOfferLocator)
	private WebElement searchOfferElement;
	
	@FindBy(how = How.XPATH, using = addOfferLocator)
	private WebElement addOfferElement;
	
	/**
	 * Dropdown
	 * @param Channel
	 * @throws Throwable
	 */

	public void selectChannel(String text) {
		Frames.selectMainFrame(webDriver);
		Select channel = new Select(webDriver.findElement(By.xpath(channelLocator)));   
		channel.selectByVisibleText(text);		 
	}
	
	/**
	 * Dropdown
	 * @param Language
	 * @throws Throwable
	 */
	
	public void selectLanguage(String text) {
		Frames.selectMainFrame(webDriver);
		Select language = new Select(webDriver.findElement(By.xpath(languageLocator)));   
		language.selectByVisibleText(text);		 
	}
	
	/**
	 * Dropdown
	 * @param WellFormed
	 * @throws Throwable
	 */

	public void selectWellFormed(String text) {
		Frames.selectMainFrame(webDriver);
		Select wellFormed = new Select(webDriver.findElement(By.xpath(wellFormedLocator)));   
		wellFormed.selectByVisibleText(text);		 
	}
	
	/**
	 * Dropdown
	 * @param CurrentlyRunning
	 */

	
	public void selectCurrentlyRunning(String text) {
		Frames.selectMainFrame(webDriver);
		Select currentlyRunning = new Select(webDriver.findElement(By.xpath(currentlyRunningLocator)));   
		currentlyRunning.selectByVisibleText(text);		 
	}
	
	/**
	 * Dropdown
	 * @param OfferCategories
	 * @throws Throwable
	 */
	
	public void selectOfferCat(String text) {
		Frames.selectMainFrame(webDriver);
		Select offerCategories = new Select(webDriver.findElement(By.xpath(offerCategoriesLocator)));   
		offerCategories.selectByVisibleText(text);		 
	}
	
	/**
	 * Dropdown
	 * @param TriggerTypes
	 * @throws Throwable
	 */


	
	public void selectTriggerTypes(String text) {
		Frames.selectMainFrame(webDriver);
		Select triggerTypes = new Select(webDriver.findElement(By.xpath(triggerTypesLocator)));   
		triggerTypes.selectByVisibleText(text);		 
	}
	
	/**
	 * method for Offer create/search
	 */
	
	public void offerSearchName(String text) {
		Frames.selectMainFrame(webDriver);
		offerSearchNameElement.sendKeys(text);
	}
	
	public void start(String text) {
		Frames.selectMainFrame(webDriver);
		startElement.sendKeys(text.toString());
	}
	
	public void startAnd(String text) {
		Frames.selectMainFrame(webDriver);
		startAndElement.sendKeys(text.toString());
	}
	
	public void ends(String text) {
		Frames.selectMainFrame(webDriver);
		endsElement.sendKeys(text.toString());
	}
	
	public void endsAnd(String text) {
		Frames.selectMainFrame(webDriver);
		endsAndElement.sendKeys(text.toString());
	}
	
	/**
	 * Click methods for Offer create/search buttons
	 */
		
	public void clickSearchOffer() {
		Frames.selectMainFrame(webDriver);
		searchOfferElement.click();
	}
	
	public void clickAddOffer() {
		Frames.selectMainFrame(webDriver);
		addOfferElement.click();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
