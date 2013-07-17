package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.DataProviders;
import betfair.util.Helpers;

public class AmendTriggersTest extends TestBase{
	
	
	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManagerTree;
	CMOfferSearch cmOfferSearch;
	CMOfferList cmOfferList;
	CMAddOffer cmAddOffer;
	CMAddTrigger cmAddTrigger;
	CMAddReward cmAddReward;

	@BeforeClass
	
	public void classInit() {
		webDriver.get(websiteUrl);
		login = PageFactory.initElements(webDriver, Login.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		campaignManagerTree = PageFactory.initElements(webDriver, CampaignManagerTree.class);
		cmOfferSearch = PageFactory.initElements(webDriver, CMOfferSearch.class);
		cmOfferList = PageFactory.initElements(webDriver, CMOfferList.class);
		cmAddOffer = PageFactory.initElements(webDriver, CMAddOffer.class);
		cmAddTrigger = PageFactory.initElements(webDriver, CMAddTrigger.class);
		cmAddReward = PageFactory.initElements(webDriver, CMAddReward.class);
	}
	
	@BeforeMethod
	public void loginAdministrator(){
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
		tabs.clickCM();
	}
	
	@AfterMethod
	public void logoutAdministrator(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
	
	@Test(description = "T413813 Amend Trigger - Setup Offer and Set-up Trigger", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void triggerSetup(String offerName, String longCurrency,String shortCurrency, String language, String country, String typeTrigger, String betType) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(longCurrency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click on Add Triggers");
		cmAddOffer.addTriggers();
		logger.info("Selecting the trigger type");
		cmAddTrigger.typeTrigger(typeTrigger);
		logger.info("Entering the minimum price for the trigger");
		cmAddTrigger.minPrice("2/1");
		logger.info("Inserting the trigger currency values");
		cmAddTrigger.insertCurrency(shortCurrency, "1.00");
		logger.info("Selecting the bet type for the trigger");
		cmAddTrigger.betTypes(betType);
		logger.info("Click on Add Trigger");
		cmAddTrigger.addTrigger();
		logger.info("Click on Delete Trigger");
		cmAddTrigger.deleteTrigger();
		logger.info("Click on OK");
		cmAddTrigger.acceptAlert();
		logger.info("Click on Delete Offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T413814 Amending an existing Trigge", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void triggerTest(String offerName, String currency, String shortCurrency,String language, String country, String typeTrigger, String betType) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click Back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Enter the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Click on search oferr");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on Add Triggers");
		cmAddOffer.addTriggers();
		logger.info("ENter the Trigger Type");
		cmAddTrigger.typeTrigger(typeTrigger);
		logger.info("Inserting the trigger currency values");
		cmAddTrigger.insertCurrency(shortCurrency,"2.00");
		logger.info("Selecting the bet type for the trigger");
		cmAddTrigger.betTypes(betType);
		logger.info("Click on Add Trigger");
		cmAddTrigger.addTrigger();
		logger.info("Click on Back to return to the offer page");
		cmAddTrigger.backTrigger();
		logger.info("Click on Generic bet trigger from the Triggers section");
		cmAddOffer.genTrigger();
		logger.info("Click on Delete Trigger");
		cmAddTrigger.deleteTrigger();
		logger.info("Click on OK");
		cmAddTrigger.acceptAlert();
		logger.info("Click on Delete Offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T547386 Create Reward", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void createReward(String offerName, String currency, String shortCurrency, String language, String country, String relativeExpiry, String betType) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Enter the offer sesarch name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on Add Reward");
		cmAddOffer.addReward();
	    logger.info("Inserting the currency reward values");
		cmAddReward.insertCurrency(shortCurrency, "1.00");
		logger.info("Entering the Relative Expiry value");
		cmAddReward.relativeExpiry(relativeExpiry);
		logger.info("Selecting the bet type");
		cmAddReward.betTypes(betType);
		logger.info("Click on insert reward");
		cmAddReward.insertReward();
		logger.info("Click on delete reward");
		cmAddReward.deleteReward();
		logger.info("Click on OK");
		cmAddReward.acceptAlert();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
}
