package betfair.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import betfair.util.Frames;

public class CMAddOffer extends Page{

	public CMAddOffer(WebDriver webDriver) {
		super(webDriver);
	}

	/**
	 * Locators for Add New Offer
	 */

	private final String offerNameLocator="//form/table/tbody/tr/td[text()='Name']/../td/input";
	private final String offerDescriptionLocator="//form/table/tbody/tr/td[text()='Description']/../td/input";
	private final String offerURLLocator="//form/table/tbody/tr/td[text()='URL']/../td/input";
	private final String offerMaxClaimsLocator="//form/table/tbody/tr[10]/td[2]/input";
	private final String offerMaxTokensLocator="//form/table/tbody/tr[10]/td[4]/input";
	private final String addNewOfferLocator="//input[contains(@type,'button') and contains(@onclick,'return fs') and contains(@value,'Add Offer')]";
	private final String backNewOfferLocator="//a[contains(@href,'/campaign_manager')]";
	private final String selAllCurrLocator="//form/table/tbody/tr[12]/td[2]/table/tbody/tr[2]/td[3]/input[1]";
	private final String resetCurrLocator="//form/table/tbody/tr[12]/td[2]/table/tbody/tr[2]/td[3]/input[2]";
	private final String selAllLangLocator="//form/table/tbody/tr[14]/td[2]/table/tbody/tr[2]/td[3]/input[1]";
	private final String resetLangLocator="//form/table/tbody/tr[14]/td[2]/table/tbody/tr[2]/td[3]/input[2]";
	private final String selAllCountriesLocator="//form/table/tbody/tr[15]/td[2]/table/tbody/tr[2]/td[3]/input[1]";
	private final String resetCountriesLocator="//form/table/tbody/tr[15]/td[2]/table/tbody/tr[2]/td[3]/input[2]";
	private final String viewCalledLocator="//a[contains(@href, '/campaign_manager?action=FREEBETS::GoViewCalledTriggersSelect&OfferID')]";
	private final String startdateLocator="//form/table/tbody/tr/td[text()='Offer Start date']/../td/input";
	private final String entrydateLocator="//form/table/tbody/tr/td[text()='Entry expiry date']/../td/input";
	private final String enddateLocator="//form/table/tbody/tr/td[text()='Offer End date']/../td/input";
	private final String startinsLocator="//form/table/tbody/tr[7]/td[2]/input";
	private final String genTriggerLocator="//table[1]/tbody/tr/td[2]/a";
	private final String sportsTokenLocator="//body/table[2]/tbody/tr/td[2]/a";
	private final String calledLocator="//body/table/tfoot/tr/td/input";
	private final String accountAssertLocator = "//body/table/tbody/tr[1]/td[1]";	
	private final String manualLocator = "//body/table[1]/tbody/tr[3]/td[2]/a";
	
	/**
	 * Locators for New Offer Insterted buttons
	 */
	 
	private final String addTriggersLocator="//table[1]/tfoot/tr/td/a";
	private final String addRewardLocator="//table[2]/tfoot/tr/td/a";
	private final String modifyLocator="//form/table/tbody/tr[20]/td/input[1]";
	private final String deleteLocator="//form/table/tbody/tr[20]/td/input[2]";
	private final String cloneLocator="//form/table/tbody/tr[20]/td/input[3]";
	private final String backOfferLocator="//a[contains(@href,'/campaign_manager')]";
	private final String backCalledLocator="//table/tfoot/tr/td/a";	
	
	/**
	 * Locators for Add New Offer dropdowns
	 */
	
	private final String tokenOnSettleLocator="//form/table/tbody/tr/td[text()='Token On Settle']/../td/select";
	private final String unlClaimsLocator="//form/table/tbody/tr[9]/td[4]/select";
	private final String offerChannelLocator="//form/table/tbody/tr/td[text()='Channel']/../td/select";
		
	/**
	 * Locators for Add New Offer list
	 */
	
	private final String currenciesLocator="//form/table/tbody/tr/td[text()='Currencies']/../td[2]/table/tbody/tr[2]/td[1]/select";
	private final String languagesLocator="//form/table/tbody/tr/td[text()='Languages']/../td[2]/table/tbody/tr[2]/td[1]/select";
	private final String countriesLocator="//form/table/tbody/tr/td[text()='Countries']/../td[2]/table/tbody/tr[2]/td[1]/select";
	private final String currinofferLocator="//form/table/tbody/tr/td[text()='Currencies']/../td[2]/table/tbody/tr[2]/td[2]/select";
	private final String langinoofferLocator="//form/table/tbody/tr/td[text()='Languages']/../td[2]/table/tbody/tr[2]/td[2]/select";
	private final String countriesinofferLocator="//form/table/tbody/tr/td[text()='Countries']/../td[2]/table/tbody/tr[2]/td[2]/select";
		
	/**
	 * Elements for New Offer Inserted buttons
	 */
	
	@FindBy(how = How.XPATH, using = manualLocator)
	private WebElement manualElement;
	
	@FindBy(how = How.XPATH, using = accountAssertLocator)
	private WebElement accountAssertElement;
	
	@FindBy(how = How.XPATH, using = sportsTokenLocator)
	private WebElement sportsTokenElement;
	
	@FindBy(how = How.XPATH, using = genTriggerLocator)
	private WebElement genTriggerElement;
	
	@FindBy(how = How.XPATH, using = addTriggersLocator)
	private WebElement addTriggersElement;
	
	@FindBy(how = How.XPATH, using = addRewardLocator)
	private WebElement addRewardElement;
	
	@FindBy(how = How.XPATH, using = modifyLocator)
	private WebElement modifyElement;
	
	@FindBy(how = How.XPATH, using = deleteLocator)
	private WebElement deleteElement;
	
	@FindBy(how = How.XPATH, using = cloneLocator)
	private WebElement cloneElement;
	
	@FindBy(how = How.XPATH, using = backOfferLocator)
	private WebElement backElement;
	
	@FindBy(how = How.XPATH, using = viewCalledLocator)
	private WebElement viewCalledElement;
	
	@FindBy(how = How.XPATH, using = calledLocator)
	private WebElement calledElement;
	
	@FindBy(how = How.XPATH, using = backCalledLocator)
	private WebElement backCalledElement;
	
	/**
	 * Elements for Add New Offer
	 */
	
	@FindBy(how = How.XPATH, using = offerNameLocator)
	private WebElement offerNameElement;
	
	@FindBy(how = How.XPATH, using = startdateLocator)
	private WebElement startdateElement;
	
	@FindBy(how = How.XPATH, using = startinsLocator)
	private WebElement startinsElement;
	
	@FindBy(how = How.XPATH, using = entrydateLocator)
	private WebElement entrydateElement;
	
	@FindBy(how = How.XPATH, using = enddateLocator)
	private WebElement enddateElement;
	
	@FindBy(how = How.XPATH, using = offerDescriptionLocator)
	private WebElement offerDescriptionElement;
	
	@FindBy(how = How.XPATH, using = offerURLLocator)
	private WebElement offerURLElement;
	
	@FindBy(how = How.XPATH, using = offerMaxClaimsLocator)
	private WebElement offerMaxClaimsElement;
	
	@FindBy(how = How.XPATH, using = offerMaxTokensLocator)
	private WebElement offerMaxTokensElement;
	
	@FindBy(how = How.XPATH, using = addNewOfferLocator)
	private WebElement addNewOfferElement;
	
	@FindBy(how = How.XPATH, using = backNewOfferLocator)
	private WebElement backNewOfferElement;
	
	@FindBy(how = How.XPATH, using = selAllCurrLocator)
	private WebElement selAllCurrElement;
	
	@FindBy(how = How.XPATH, using = resetCurrLocator)
	private WebElement resetCurrElement;
	
	@FindBy(how = How.XPATH, using = selAllLangLocator)
	private WebElement selAllLangElement;
	
	@FindBy(how = How.XPATH, using = resetLangLocator)
	private WebElement resetLangElement;
	
	@FindBy(how = How.XPATH, using = selAllCountriesLocator)
	private WebElement selAllCountriesElement;
	
	@FindBy(how = How.XPATH, using = resetCountriesLocator)
	private WebElement resetCountriesElement;
	
	/**
	 * List
	 * @param Currencies
	 */
	
	public void selectCurrencies(String text) {
		Frames.selectMainFrame(webDriver);
		Select selectCurrencies = new Select(webDriver.findElement(By.xpath(currenciesLocator)));   
		selectCurrencies.selectByVisibleText(text);		 
	}
	
	/**
	 * List
	 * @param Languages
	 */
	
	public void selectLanguages(String text) {
		Frames.selectMainFrame(webDriver);
		Select languages = new Select(webDriver.findElement(By.xpath(languagesLocator)));   
		languages.selectByVisibleText(text);		 
	}
	
	/**
	 * List
	 * @param Countries
	 */
	
	public void selectCountries(String text) {
		Frames.selectMainFrame(webDriver);
		Select selectCountries = new Select(webDriver.findElement(By.xpath(countriesLocator)));
		String[] countries = text.split(";");
		for (String country : countries) {
			selectCountries.selectByVisibleText(country);	
		}
	}
	
	/**
	 * List
	 * @param Currencies in Offer
	 */
	
	public void selCurrinooffer(String text) {
		Frames.selectMainFrame(webDriver);
		Select currinoffer = new Select(webDriver.findElement(By.xpath(currinofferLocator)));   
		currinoffer.selectByVisibleText(text);
	}
	
	public boolean isCurrVisible(){
		if(webDriver.findElement(By.xpath(currinofferLocator)) != null)
			return false;
		else
			return true;
	}
		
	/**
	 * List
	 * @param Languages in offer
	 */
	
	public void selLanginoffer(String text) {
		Frames.selectMainFrame(webDriver);
		Select langinooffer = new Select(webDriver.findElement(By.xpath(langinoofferLocator)));   
		langinooffer.selectByVisibleText(text);		 
	}

	public boolean isLangVisible(){
		if(webDriver.findElement(By.xpath(langinoofferLocator)) != null)
			return false;
		else
			return true;
	}
	
	/**
	 * List 
	 * @param Countries in offer
	 */
	
	public void selCountriesinoffer(String text) {
		Frames.selectMainFrame(webDriver);
		Select countriesinoffer = new Select(webDriver.findElement(By.xpath(countriesinofferLocator)));   
		countriesinoffer.selectByVisibleText(text);		 
	}
	
	public boolean isCountriesVisible(){
		if(webDriver.findElement(By.xpath(countriesinofferLocator)) != null)
			return false;
		else
			return true;
	}
	
	/**
	 * Dropdown
	 * @param Token On Settle
	 */
	
	public void selectTokenOnSettle(String text) {
		Frames.selectMainFrame(webDriver);
		Select tokenOnSettle = new Select(webDriver.findElement(By.xpath(tokenOnSettleLocator)));   
		tokenOnSettle.selectByVisibleText(text);		 
	}
	
	/**
	 * Dropdown
	 * @param Unlimited Claims
	 */

	public void selectunlClaims(String text) {
		Frames.selectMainFrame(webDriver);
		Select unlClaims = new Select(webDriver.findElement(By.xpath(unlClaimsLocator)));   
		unlClaims.selectByVisibleText(text);		 
	}
	
	/**
	 * Dropdown
	 * @param Token On Settle
	 */

	public void selectofferChannel(String text) {
		Frames.selectMainFrame(webDriver);
		Select offerChannel = new Select(webDriver.findElement(By.xpath(offerChannelLocator)));   
		offerChannel.selectByVisibleText(text);		 
	}

	/**
	 * Click methods for Add New Offer buttons page
	 * Add new Offer Max Tokens field
	 */	
	
	public void addTriggers() {
		Frames.selectMainFrame(webDriver);
		addTriggersElement.click();
	}
	
	public void sportsToken() {
		Frames.selectMainFrame(webDriver);
		sportsTokenElement.click();
	}
	
	public void addReward() {
		Frames.selectMainFrame(webDriver);
		addRewardElement.click();
	}
	
	public void modifyOffer() {
		Frames.selectMainFrame(webDriver);
		modifyElement.click();
	}
	
	public void deleteOffer() {
		Frames.selectMainFrame(webDriver);
		deleteElement.click();
	}
	
	public void cloneOffer() {
		Frames.selectMainFrame(webDriver);
		cloneElement.click();
	}
	
	public void backOffer() {
		Frames.selectMainFrame(webDriver);
		backElement.click();
	}
	
	public void manual() {
		Frames.selectMainFrame(webDriver);
		manualElement.click();
	}
	
	public void viewcalledOffer() {
		Frames.selectMainFrame(webDriver);
		viewCalledElement.click();
	}
	
	public void genTrigger() {
		Frames.selectMainFrame(webDriver);
		genTriggerElement.click();
	}
	
	public void called() {
		Frames.selectMainFrame(webDriver);
		calledElement.click();
	}
	
	public void backCalled() {
		Frames.selectMainFrame(webDriver);
		backCalledElement.click();
	}
	
	/**
	 * method for Add New Offer page
	 * Add new Offer Name field
	 */
	
	public void offerName(String text) {
		Frames.selectMainFrame(webDriver);
		offerNameElement.sendKeys(text);
	}
	
	/**
	 * method for Start Date Inserted
	 * Add new Offer Name field
	 */
	
	public void startDateIns(String text) {
		Frames.selectMainFrame(webDriver);
		startinsElement.clear();
		startinsElement.sendKeys(text.toString());
	}
	
	/**
	 * method for Add New Offer page
	 * Add new Start Date field
	 */
	
	public void startDate(String tomorrow) {
		Frames.selectMainFrame(webDriver);
		startdateElement.clear();
		startdateElement.sendKeys(tomorrow.toString());
	}
	
	public boolean isStartDateVisible(){
		if(startdateElement.isDisplayed())
			return false;
		else
			return true;
	}
	
	/**
	 * method for Add New Offer page
	 * Add new Entry Date field
	 */
	
	public void entryDate(String text) {
		Frames.selectMainFrame(webDriver);
		entrydateElement.clear();
		entrydateElement.sendKeys(text.toString());
	}
	
	/**
	 * method for Add New Offer page
	 * Add new End Date field
	 */
	
	public void endDate(String text) {
		Frames.selectMainFrame(webDriver);
		enddateElement.clear();
		enddateElement.sendKeys(text.toString());
	}
		
	/**
	 * method for Add New Offer page
	 * Add new Offer Description field
	 */
	
	public void offerDescription(String text) {
		Frames.selectMainFrame(webDriver);
		offerDescriptionElement.sendKeys(text);
	}
	
	/**
	 * method for Add New Offer page
	 * Add new Offer URL field
	 */
	
	public void offerURL(String text) {
		Frames.selectMainFrame(webDriver);
		offerURLElement.sendKeys(text);
	}
	
	/**
	 * method for Add New Offer page
	 * Add new Offer Max Claims field
	 */
	
	public void offerMaxClaims(String text) {
		Frames.selectMainFrame(webDriver);
		offerMaxClaimsElement.sendKeys(text);
	}
	
	/**
	 * method for Add New Offer page
	 * Add new Offer Max Tokens field
	 */
	
	public void offerMaxTokens(String text) {
		offerMaxTokensElement.sendKeys(text);
	}
	
	/**
	 * Click methods for Add New Offer buttons page
	 * Add new Offer Max Tokens field
	 */	
	
	public void addNewOffer() {
		Frames.selectMainFrame(webDriver);
		addNewOfferElement.click();
	}
	
	public void backNewOffer() {
		Frames.selectMainFrame(webDriver);
		backNewOfferElement.click();
	}
	
	public void selAllCurr() {
		Frames.selectMainFrame(webDriver);
		selAllCurrElement.click();
	}
	
	public void resetCurr() {
		Frames.selectMainFrame(webDriver);
		resetCurrElement.click();
	}
	
	public void selAllLang() {
		Frames.selectMainFrame(webDriver);
		selAllLangElement.click();
	}
	
	public void resetLang() {
		Frames.selectMainFrame(webDriver);
		resetLangElement.click();
	}
	
	public void selAllCountries() {
		Frames.selectMainFrame(webDriver);
		selAllCountriesElement.click();
	}
	
	public void resetCountries() {
		Frames.selectMainFrame(webDriver);
		resetCountriesElement.click();
	}

	public void acceptAlert() {
		Alert alertAccept = webDriver.switchTo().alert();  
		alertAccept.accept();
	}
	
	public void acceptCancel() {
		Alert alertCancel = webDriver.switchTo().alert();  
		alertCancel.dismiss();
	}
	
	public String getAccountNumber() {
		return accountAssertElement.getText();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
