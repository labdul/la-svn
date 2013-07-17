package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;


public class CampaignManagerTree extends Page{

	public CampaignManagerTree(WebDriver webDriver) {
		super(webDriver);
	}
	
	/**
	 * LOCATORS
	 */

	private final String identifyCustomersLocator="//*[@id='first']/span";
	private final String campaignsLocator="//*[@id='vclickMenu']/li[2]/span";
	private final String offersLocator="//ul[@id='vclickMenu']/li[3]/span";
	private final String offerLocator="//*[@id='vclickMenu']/li[3]/ul/li[2]/a";
	private final String reportsLocator="//*[@id='vclickMenu']/li[4]/span";
	private final String miscLocator="//*[@id='vclickMenu']/li[5]/span";
	private final String logoutLocator="//*[@id='logoutMenuItem']";	
	
	/**
	 * Identify Customers Locators
	 */

	private final String expenditureLocator="/*[@id='first']/ul/li[1]/a";
	private final String specificProductLocator="//*[@id='first']/ul/li[2]/a";
	private final String anniversariesLocator="//*[@id='first']/ul/li[3]/a";
	private final String decliningLocator="//*[@id='first']/ul/li[4]/a";
	private final String lapsedLocator="//*[@id='first']/ul/li[5]/a";
	private final String depositsLocator="//*[@id='first']/ul/li[6]/a";
	private final String inactiveLocator="//*[@id='first']/ul/li[7]/a";
	private final String communicationLocator="//*[@id='first']/ul/li[8]/a";
	private final String bettingLocator="//*[@id='first']/ul/li[9]/a";
	private final String registrationLocator="//*[@id='first']/ul/li[10]/a";
	private final String transactionsLocator="//*[@id='first']/ul/li[11]/a";
	private final String channelsLocator="//*[@id='first']/ul/li[12]/a";
	private final String tagLocator="//*[@id='first']/ul/li[13]/a";
	private final String segmentationLocator="//*[@id='first']/ul/li[14]/a";
	private final String combineResultsLocator="//*[@id='first']/ul/li[15]/a";
	private final String divideResultsLocator="//*[@id='first']/ul/li[16]/a";
	private final String viewResultsLocator="//*[@id='first']/ul/li[17]/a";
	private final String viewTagsLocator="//*[@id='first']/ul/li[18]/a";
	private final String tagCustomersLocator="//*[@id='first']/ul/li[19]/a";
	private final String uploadLocator="//*[@id='first']/ul/li[20]/a";	

	/**
	 * Offers Locators
	 */
	
	private final String triggerTypesLocator = "//*[@id='vclickMenu']/li[3]/ul/li[1]/a";
	private final String offerCatLocator = "//*[@id='vclickMenu']/li[3]/ul/li[3]/a";
	private final String redempLocator = "//*[@id='vclickMenu']/li[3]/ul/li[4]/a";
	private final String adHocLocator = "//*[@id='vclickMenu']/li[3]/ul/li[5]/a";
	private final String freeBetLocator = "//*[@id='vclickMenu']/li[3]/ul/li[6]/a";
	
	/**
	 * Elements for Offers
	 */
	
	@FindBy(how = How.XPATH, using = triggerTypesLocator)
	private WebElement triggerTypesElement;
	
	@FindBy(how = How.XPATH, using = offerCatLocator)
	private WebElement offerCatElement;
	
	@FindBy(how = How.XPATH, using = redempLocator)
	private WebElement redempElement;
	
	@FindBy(how = How.XPATH, using = adHocLocator)
	private WebElement adHocElement;
	
	@FindBy(how = How.XPATH, using = freeBetLocator)
	private WebElement freeBetElement;
	
	/**
	 * Elements for selection Tree
	 */
	
	@FindBy(how = How.XPATH, using = identifyCustomersLocator)
	private WebElement identifyCustomersElement;
	
	@FindBy(how = How.XPATH, using = campaignsLocator)
	private WebElement campaignsElement;
	
	@FindBy(how = How.XPATH, using = offersLocator)
	private WebElement offersElement;
	
	@FindBy(how = How.XPATH, using = offerLocator)
	private WebElement offerElement;
	
	@FindBy(how = How.XPATH, using = reportsLocator)
	private WebElement reportsElement;
	
	@FindBy(how = How.XPATH, using = miscLocator)
	private WebElement miscElement;
	
	@FindBy(how = How.XPATH, using = logoutLocator)
	private WebElement logoutElement;
	
	/**
	 * Identify Customers Elements
	 */

	@FindBy(how = How.XPATH, using = expenditureLocator)
	private WebElement expenditureElement;
	
	@FindBy(how = How.XPATH, using = specificProductLocator)
	private WebElement specificProductElement;
	
	@FindBy(how = How.XPATH, using = anniversariesLocator)
	private WebElement anniversariesElement;
	
	@FindBy(how = How.XPATH, using = decliningLocator)
	private WebElement decliningElement;
	
	@FindBy(how = How.XPATH, using = lapsedLocator)
	private WebElement lapsedElement;
	
	@FindBy(how = How.XPATH, using = depositsLocator)
	private WebElement depositsElement;
	
	@FindBy(how = How.XPATH, using = inactiveLocator)
	private WebElement inactiveElement;
	
	@FindBy(how = How.XPATH, using = communicationLocator)
	private WebElement communicationElement;
	
	@FindBy(how = How.XPATH, using = bettingLocator)
	private WebElement bettingElement;
	
	@FindBy(how = How.XPATH, using = registrationLocator)
	private WebElement registrationElement;
	
	@FindBy(how = How.XPATH, using = transactionsLocator)
	private WebElement transactionsElement;
	
	@FindBy(how = How.XPATH, using = channelsLocator)
	private WebElement channelElement;
	
	@FindBy(how = How.XPATH, using = tagLocator)
	private WebElement tagElement;
	
	@FindBy(how = How.XPATH, using = segmentationLocator)
	private WebElement segmentationElement;
	
	@FindBy(how = How.XPATH, using = combineResultsLocator)
	private WebElement combineResultsElement;
	
	@FindBy(how = How.XPATH, using = divideResultsLocator)
	private WebElement divideResultsElement;
	
	@FindBy(how = How.XPATH, using = viewResultsLocator)
	private WebElement viewResultsElement;
	
	@FindBy(how = How.XPATH, using = viewTagsLocator)
	private WebElement viewTagsElement;
	
	@FindBy(how = How.XPATH, using = tagCustomersLocator)
	private WebElement tagCustomersElement;
	
	@FindBy(how = How.XPATH, using = uploadLocator)
	private WebElement uploadElement;
	
	/**
	 * Click methods for Tree selection
	 */

	public void identifyCustormers() {
		Frames.selectMenuFrame(webDriver);
		identifyCustomersElement.click();
	}
	public void campaigns() {
		Frames.selectMenuFrame(webDriver);
		campaignsElement.click();
	}
	public void clickOffers() {
		Frames.selectCMenuFrame(webDriver);
		offersElement.click();
	}
	public void clickOffer() {
		Frames.selectCMenuFrame(webDriver);
		offerElement.click();
	}
	
	public void clickFreebet() {
		Frames.selectCMenuFrame(webDriver);
		freeBetElement.click();
	}
	public void clickAdHoc() {
		Frames.selectCMenuFrame(webDriver);
		adHocElement.click();
	}
	
	public void reports() {
		Frames.selectMenuFrame(webDriver);
		reportsElement.click();
	}
	public void misc() {
		Frames.selectMenuFrame(webDriver);
		miscElement.click();
	}
	public void logout() {
		Frames.selectCMenuFrame(webDriver);
		logoutElement.click();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}
