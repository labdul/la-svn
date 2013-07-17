package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.DataProviders;
import betfair.util.Helpers;

public class AmendActiveOfferTest extends TestBase{

	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManagerTree;
	CMOfferSearch cmOfferSearch;
	CMOfferList cmOfferList;
	CMAddOffer cmAddOffer;
	CMAddTrigger cmAddTrigger;
	CMAddReward cmAddReward;
	CMAddLevel cmAddLevel;
	
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
		cmAddLevel = PageFactory.initElements(webDriver, CMAddLevel.class);
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
	
	@Test(description = "T413805 Amend an Offer which has started, has no Tokens", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendTokens(String offerName, String currencies, String language, String shortCurrency, String country, String typeTrigger, String betType) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String today = Helpers.getDateWithOffsetInDays(0);
		String todayStart = Helpers.getDateWithOffsetInMinutes(-178);
		String dayAfterTomorrow = Helpers.getDateWithOffsetInDays(2);
		String tomorrowStart = Helpers.getDateWithOffsetInMinutes(60);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currencies);
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
		cmAddTrigger.typeTrigger(typeTrigger);
		logger.info("Entering the minimum price for the trigger");
		cmAddTrigger.minPrice("2/1");
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
		try {logger.info("Wait 2 minutes for the offer to start");
			Thread.sleep(120000);
			logger.info("Click on the offer from the offers list page");
			cmOfferList.offerListName();
			logger.info("Check that currencies are not selectable");
			cmAddOffer.isCurrVisible(); 
			logger.info("Check that languages are not selectable");
			cmAddOffer.isLangVisible();
			logger.info("Check that countries are not selectable");
			cmAddOffer.isCountriesVisible();
			logger.info("Check that start date cannot be modified");
			cmAddOffer.isStartDateVisible();
			logger.info("Modify the entry date");
			cmAddOffer.entryDate(tomorrowStart);
			logger.info("Modify the end date");
			cmAddOffer.endDate(dayAfterTomorrow);
			logger.info("Click on modify offer");
			cmAddOffer.modifyOffer();
			cmAddOffer.acceptAlert();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Clicking on the Trigger from the list");
		cmAddOffer.genTrigger();
		logger.info("Clicking on the Delete Trigger button");
		cmAddTrigger.deleteTrigger();
		cmAddTrigger.acceptAlert();
		logger.info("Clicking on the sportsbook token from the list");
		cmAddOffer.sportsToken();
		logger.info("Clicking on the Delete Reward button");
		cmAddReward.deleteReward();
		cmAddReward.acceptAlert();
		logger.info("Clicking on the Delete offer button");
		cmAddOffer.deleteOffer();
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T413806 Searching on Well Formed & Running for Active Offer", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void searchActive(String offerName, String currencies, String shortCurrency, String language, String country, String typeTrigger, String betType) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String today = Helpers.getDateWithOffsetInDays(0);
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
		cmAddOffer.selectCurrencies(currencies);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Entering Entry Date");
		cmAddOffer.entryDate(tomorrow);
		logger.info("Entering end date");
		cmAddOffer.endDate(dayAfterTomorrow);
		logger.info("Entering start date");
		cmAddOffer.startDate(today);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click on Add Trigger");
		cmAddOffer.addTriggers();
		logger.info("Selecting the trigger type");
		cmAddTrigger.typeTrigger(typeTrigger);
		logger.info("Inserting the trigger currency values");
		cmAddTrigger.insertCurrency(shortCurrency, "1.00");
		logger.info("Selecting the bet type for the trigger");
		cmAddTrigger.betTypes(betType);
		logger.info("Click on Add Trigger");
		cmAddTrigger.addTrigger();
		logger.info("Click on Add Level");
		cmAddTrigger.addLevel();
		Helpers.switchToWindow(webDriver,2);
		Helpers.resizeWindow(webDriver, 900, 880);
		logger.info("Click on Horse Racing");		
		cmAddLevel.horseRacing();
		Helpers.closeSwitched(webDriver);
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
	    logger.info("Click on Add Redemption");
		cmAddReward.addRedemption();
	    logger.info("Click on Any");
		cmAddReward.any();
		logger.info("Returning to the offer page");
		cmAddReward.backReward();
		logger.info("Click on offers from the Offers subsection");
		campaignManagerTree.clickOffer();
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select No from the well formed dropdown");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Select No from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("No");
		try {logger.info("Wait 2 minutes for the offer to start");
			Thread.sleep(120000);
			logger.info("Click on Search offer");
			cmOfferSearch.clickSearchOffer();
			logger.info("Clicking back from the empty offers list page");
			cmOfferList.backEmptyList();
			logger.info("Entering the offer search name");
			cmOfferSearch.offerSearchName(offerName);
			logger.info("Select Yes from the well formed dropdown");
			cmOfferSearch.selectWellFormed("Yes");
			logger.info("Select No from the currently running dropdown");
			cmOfferSearch.selectCurrentlyRunning("No");
			logger.info("Click on Search offer");
			cmOfferSearch.clickSearchOffer();
			logger.info("Clicking back from the empty offers list page");
			cmOfferList.backEmptyList();
			logger.info("Entering the offer search name");
			cmOfferSearch.offerSearchName(offerName);
			logger.info("Select No from the well formed dropdown");
			cmOfferSearch.selectWellFormed("No");
			logger.info("Select Yes from the currently running dropdown");
			cmOfferSearch.selectCurrentlyRunning("Yes");
			logger.info("Click on Search offer");
			cmOfferSearch.clickSearchOffer();
			logger.info("Clicking back from the empty offers list page");
			cmOfferList.backEmptyList();
			logger.info("Entering the offer search name");
			cmOfferSearch.offerSearchName(offerName);
			logger.info("Select Yes from the well formed dropdown");
			cmOfferSearch.selectWellFormed("Yes");
			logger.info("Select Yes from the currently running dropdown");
			cmOfferSearch.selectCurrentlyRunning("Yes");
			logger.info("Click on Search offer");
			cmOfferSearch.clickSearchOffer();
			logger.info("Click on the resulted offer");
			cmOfferList.offerListName();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		logger.info("Clicking on the trigger from the list");
		cmAddOffer.genTrigger();
		logger.info("Clicking on the delete level button");
		cmAddTrigger.deleteLevel();
		logger.info("Clicking the delete trigger button");
		cmAddTrigger.deleteTrigger();
		cmAddTrigger.acceptAlert();
		logger.info("Clicking the reward from the list");
		cmAddOffer.sportsToken();
		logger.info("Removing the redemption");
		cmAddReward.removeRedemp();
		logger.info("Clicking the delete reward button");
		cmAddReward.deleteReward();
		cmAddReward.acceptAlert();
		logger.info("Clicking the delete offer button");
		cmAddOffer.deleteOffer();
		cmAddOffer.acceptAlert();
	}
	
}
