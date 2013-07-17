package betfair.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;


//This class contains Admin Page functions
public class AdminEventUnsettle extends Page{

	public AdminEventUnsettle(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
		
	private final String insertDateFromLocator = "//input[contains(@type,'text') and contains (@name,'date_lo') and contains (@size,'10')]";
	private final String insertDateToLocator = "//input[contains(@type,'text') and contains (@name,'date_hi') and contains (@size,'10')]";
	private final String continueButtonLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Continue')]";
	private final String categoryHorseRacingLocator = "//a[contains(@href,'HORSE_RACING')]";
	private final String classHorseRacingLocator = "//a[contains(@href,'class_name=|Horse Racing|')]";
	private final String typeBathLocator = "//a[contains(@href,'type_name=|Bath|')]";
	private final String eventAutoUnsetLocator = "//a[contains(@href,'event_name=auto unset race')]";
	private final String showMarketsLocator = "//a[contains(@href,'Horse Racing') and contains(@href,'Bath') and contains(text(),'Show Markets')]"; 
	private final String showMarketSelectionsLocator = "//a[contains(@href,'Horse Racing') and contains(@href,'Bath') and contains(text(),'Show Market Selections')]";
	private final String hOneLocator = "//a[contains(@href,'Horse Racing') and contains(@href,'Bath') and contains(text(),'H1')]";
	private final String hTwoLocator = "//a[contains(@href,'Horse Racing') and contains(@href,'Bath') and contains(text(),'H2')]";
	private final String hThreeLocator = "//a[contains(@href,'Horse Racing') and contains(@href,'Bath') and contains(text(),'H3')]";
	private final String unsettleButtonLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Unsettle')]";
	private final String selectionUnsettledTextLocator = "//table/tbody/tr[1]/th";
	private final String backButtonLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value,'Back')]";
	private final String autounsetSearchedLocator = "//a[contains(text(),'auto unset race')]";
	
	/*
	 * ELEMENTS
	 */
		
	@FindBy(how = How.XPATH, using = insertDateFromLocator)
	private WebElement insertDateFromElement;
	
	@FindBy(how = How.XPATH, using = insertDateToLocator)
	private WebElement insertDateToElement;
	
	@FindBy(how = How.XPATH, using = continueButtonLocator)
	private WebElement continueButtonElement;
	
	@FindBy(how = How.XPATH, using = categoryHorseRacingLocator)
	private WebElement categoryHorseRacingElement;
	
	@FindBy(how = How.XPATH, using = classHorseRacingLocator)
	private WebElement classHorseRacingElement;
	
	@FindBy(how = How.XPATH, using = typeBathLocator)
	private WebElement typeBathElement;
	
	@FindBy(how = How.XPATH, using = eventAutoUnsetLocator)
	private WebElement eventAutoUnsetElement;
	
	@FindBy(how = How.XPATH, using = showMarketsLocator)
	private WebElement showMarketsElement;
	
	@FindBy(how = How.XPATH, using = showMarketSelectionsLocator)
	private WebElement showMarketSelectionsElement;
	
	@FindBy(how = How.XPATH, using = hOneLocator)
	private WebElement hOneElement;
	
	@FindBy(how = How.XPATH, using = hTwoLocator)
	private WebElement hTwoElement;
	
	@FindBy(how = How.XPATH, using = hThreeLocator)
	private WebElement hThreeElement;
	
	@FindBy(how = How.XPATH, using = unsettleButtonLocator)
	private WebElement unsettleButtonElement;
	
	@FindBy(how = How.XPATH, using = selectionUnsettledTextLocator)
	private WebElement selectionUnsettledTextElement;
	
	@FindBy(how = How.XPATH, using = backButtonLocator)
	private WebElement backButtonElement;
	
	@FindBy(how = How.XPATH, using = autounsetSearchedLocator)
	private WebElement autounsetSearchedElement;
	
	/*
	 * METHODS
	 */

	/**
	 * Click Methods
	 */
	public void clickCategoryHorseRacing() {
		Frames.selectMainAreaFrame(webDriver);
		categoryHorseRacingElement.click();
	}
	
	public void clickContinue() {
		Frames.selectMainAreaFrame(webDriver);
		continueButtonElement.click();
	}
	
	public void clickClassHorseRacing() {
		Frames.selectMainAreaFrame(webDriver);
		classHorseRacingElement.click();
	}
	
	public void clickTypeBath() {
		Frames.selectMainAreaFrame(webDriver);
		typeBathElement.click();
	}
	
	public void clickEventAutoUnset() {
		Frames.selectMainAreaFrame(webDriver);
		eventAutoUnsetElement.click();
	}
	
	public void clickShowMarkets() {
		Frames.selectMainAreaFrame(webDriver);
		showMarketsElement.click();
	}
	
	public void clickShowMarketSelections() {
		Frames.selectMainAreaFrame(webDriver);
		showMarketSelectionsElement.click();
	}
	
	public void clickSelectionOne() {
		Frames.selectMainAreaFrame(webDriver);
		hOneElement.click();
	}
	
	public void clickSelectionTwo() {
		Frames.selectMainAreaFrame(webDriver);
		hTwoElement.click();
	}
	
	public void clickSelectionThree() {
		Frames.selectMainAreaFrame(webDriver);
		hThreeElement.click();
	}
	
	public void clickUnsettle() {
		unsettleButtonElement.click();
	}
	
	public void clickBack() {
		backButtonElement.click();
	}
	
	public void clickAutoUnsetSearched() {
		Frames.selectMainAreaFrame(webDriver);
		autounsetSearchedElement.click();
	}
			
	/**
	 * Inserts Unsettlement Date Range
	 */
	public void insertUnsettlementDates(String text) {
		Frames.selectMainAreaFrame(webDriver);
		insertDateFromElement.sendKeys(text);
		insertDateToElement.sendKeys(text);
	}
	
	/**
	 * Gets Unsettled Text
	 */
	public String getUnsettledText() {
		Frames.selectMainAreaFrame(webDriver);
		return selectionUnsettledTextElement.getText();
	}
}