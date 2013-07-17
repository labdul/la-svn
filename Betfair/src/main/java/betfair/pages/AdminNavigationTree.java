package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;

//This class contains Admin Navigation Tree functions
public class AdminNavigationTree extends Page{

	public AdminNavigationTree(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */

	private final String miscellaneousLocator = "//*[@id='first']/span";
	private final String betfairLocator = "//*[@id='vclickMenu']/li[2]/span";
	private final String bettingSetupLocator = "//*[contains(text(),'Betting Setup')]";
	private final String unsettlementLocator = "//*[contains(text(),'Unsettlement')]";
	private final String displayConfigLocator = "//*[@id='vclickMenu']/li[4]/span";
	private final String queriesLocator = "//*[@id='vclickMenu']/li[5]/span";
	private final String birLocator = "//*[@id='vclickMenu']/li[6]/span";
	private final String freebetsLocator = "//*[@id='vclickMenu']/li[7]/span";
	private final String monitorLocator = "//*[@id='vclickMenu']/li[8]/span";
	private final String customerLettersLocator = "//*[@id='vclickMenu']/li[9]/span";
	private final String searchLocator = "//*[@id='vclickMenu']/li[10]/span";
	private final String navContainerLocator = "//*[@id='adminMenuDiv']/ul";
	private final String eventsLocator = "//a[contains(text(),'Events')]";
	private final String CustomersLocator = "//*[@id='vclickMenu']/li[5]/ul/li[6]/a";
	private final String fixedOddsBetLocator = "//*[@id='vclickMenu']/li[5]/ul/li[1]/a";
	private final String eventsClassesLocator = "//a[contains(text(),'Event Classes')]";
		
	/*
	 * ELEMENTS
	 */

	//Admin Navigation Tree Elements
	@FindBy(how = How.XPATH, using = miscellaneousLocator)
	private WebElement miscellaneousElement;

	@FindBy(how = How.XPATH, using = betfairLocator)
	private WebElement betfairElement;

	@FindBy(how = How.XPATH, using = bettingSetupLocator)
	private WebElement bettingSetupElement;

	@FindBy(how = How.XPATH, using = displayConfigLocator)
	private WebElement displayConfigElement;

	@FindBy(how = How.XPATH, using = queriesLocator)
	private WebElement queriesElement;

	@FindBy(how = How.XPATH, using = birLocator)
	private WebElement birElement;

	@FindBy(how = How.XPATH, using = freebetsLocator)
	private WebElement freebetsElement;

	@FindBy(how = How.XPATH, using = monitorLocator)
	private WebElement monitorElement;

	@FindBy(how = How.XPATH, using = customerLettersLocator)
	private WebElement customerLettersElement;

	@FindBy(how = How.XPATH, using = searchLocator)
	private WebElement searchElement;

	@FindBy(how = How.XPATH, using = navContainerLocator)
	private WebElement navContainerElement;

	@FindBy(how = How.XPATH, using = eventsLocator)
	private WebElement eventsElement;
	
	@FindBy(how = How.XPATH, using = CustomersLocator)
	private WebElement customersElement;

	@FindBy(how = How.XPATH, using = fixedOddsBetLocator)
	private WebElement fixedOddsBetElement;
	
	@FindBy(how = How.XPATH, using = unsettlementLocator)
	private WebElement unsettlementElement;
	
	@FindBy(how = How.XPATH, using = eventsClassesLocator)
	private WebElement eventsClassesElement;
	
	/*
	 * METHODS
	 */

	/**
	 * Click methods for the navigation tree
	 */
	
	public void clickBettingSetup() {
		Frames.selectMenuFrame(webDriver);
		bettingSetupElement.click();
	}
	
	public void clickEvents() {
		Frames.selectMenuFrame(webDriver);
		eventsElement.click();
	}
	
	public void clickQueries() {
		Frames.selectMenuFrame(webDriver);
		queriesElement.click();
	}
	public void clickCustomers() {
		Frames.selectMenuFrame(webDriver);
		customersElement.click();
	}
	public void clickFixedOddsBet() {
		Frames.selectMenuFrame(webDriver);
		fixedOddsBetElement.click();
	}
	public void clickUnsettlement() {
		Frames.selectMenuFrame(webDriver);
		unsettlementElement.click();
	}
	
	public void clickEventsClasses() {
		Frames.selectMenuFrame(webDriver);
		eventsClassesElement.click();
	}
}