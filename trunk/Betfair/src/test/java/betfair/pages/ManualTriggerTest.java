package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.DataProviders;
import betfair.util.Helpers;

public class ManualTriggerTest extends TestBase{

	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManager;
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
		campaignManager = PageFactory.initElements(webDriver, CampaignManagerTree.class);
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
	
	@Test(description = "T413803 Amend Offer - Country", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void manualTrigger2(String offerName, String currency, String shortCurrency, String language, String country, String typeTrigger, String betType) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String today = Helpers.getDateWithOffsetInDays(0);
		String todayStart = Helpers.getDateWithOffsetInMinutes(-117);
		String dayAfterTomorrow = Helpers.getDateWithOffsetInDays(2);
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
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Entering Entry Date");
		cmAddOffer.entryDate(tomorrow);
		logger.info("Entering end date");
		cmAddOffer.endDate(dayAfterTomorrow);
		logger.info("Entering start date");
		cmAddOffer.startDate(today);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click on Add Trigger");
		cmAddOffer.addTriggers();
		logger.info("Selecting the trigger type");
		cmAddTrigger.typeTrigger("First Deposit");
		logger.info("Inserting the trigger currency values");
		cmAddTrigger.insertCurrency(shortCurrency, "1.00");
	    logger.info("Selecting the bet type for the trigger");
		cmAddTrigger.betTypes(betType);
		logger.info("Click on Add Trigger");
	    cmAddTrigger.addTrigger();
	    logger.info("Returning to the offer page");
	    cmAddTrigger.backTrigger();
	    logger.info("Click on Add Reward");
	    cmAddOffer.addReward();
	    logger.info("Inserting the currency reward values");
	    cmAddReward.insertCurrency(shortCurrency, "1.00");
	    logger.info("Entering the Relative Expiry value");
	    cmAddReward.relativeExpiry("2 0:00");
	    logger.info("Selecting the bet type");
	    cmAddReward.betTypes(betType);
	    logger.info("Click on insert reward");
	    cmAddReward.insertReward();
	    logger.info("Returning to the offer page");
	    cmAddReward.backReward();
	    logger.info("Modifying the start date");
	    cmAddOffer.startDateIns(todayStart);
	    logger.info("Click on Modify offer");
	    cmAddOffer.modifyOffer();
	    logger.info("Click on the OK button in the alert pop-up");
	    cmAddOffer.acceptAlert();
	    logger.info("Returning to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select No from the well formed dropdown");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Select - from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search offer");
		cmOfferSearch.clickSearchOffer();
	}
	
}
