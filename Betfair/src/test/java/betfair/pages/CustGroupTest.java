package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.Helpers;

public class CustGroupTest extends TestBase{

	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManagerTree;
	CMOfferSearch cmOfferSearch;
	CMOfferList cmOfferList;
	CMAddOffer cmAddOffer;
	CMAddTrigger cmAddTrigger;
	CMAddReward cmAddReward;
	AdminEventCreation adminEvent;
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
		adminEvent = PageFactory.initElements(webDriver, AdminEventCreation.class);
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
	
	@Test(description = "C219091 Registration Trigger")
	public void custGroup() throws Exception {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String today = Helpers.getDateWithOffsetInMinutes(-178);
		String dayAfterTomorrow = Helpers.getDateWithOffsetInDays(2);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName("custGroup");
		logger.info("Selecting currencies");
		cmAddOffer.selAllCurr();
		logger.info("Selecting languages");
		cmAddOffer.selAllLang();
		logger.info("Selecting countries");
		cmAddOffer.selAllCountries();
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
		cmAddTrigger.typeTrigger("Generic Bet Trigger");
		logger.info("Inserting the trigger currency values");
		cmAddTrigger.insertCurrency("AUD", "1.00");
		cmAddTrigger.insertCurrency("CAD", "1.00");
		cmAddTrigger.insertCurrency("DKK", "1.00");
		cmAddTrigger.insertCurrency("EUR", "1.00");
		cmAddTrigger.insertCurrency("GBP", "1.00");
		cmAddTrigger.insertCurrency("HKD", "1.00");
		cmAddTrigger.insertCurrency("NOK", "1.00");
		cmAddTrigger.insertCurrency("SEK", "1.00");
		cmAddTrigger.insertCurrency("SGD", "1.00");
		cmAddTrigger.insertCurrency("USD", "1.00");
		logger.info("Adding the bet type");
		cmAddTrigger.betTypes("Single");
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
	    logger.info("Adding the cust group trigger");
	    cmAddOffer.addTriggers();
	    logger.info("Adding the trigger");
	    cmAddTrigger.addTrigger();
	    logger.info("Creating the CSV file");
	    cmAddTrigger.writecsv("..\\custgroup.csv", "username", "CCY CODE", "90001", "USD");
	    logger.info("Sending the file path");
	    cmAddTrigger.send("C:\\Work\\Betfair\\custgroup.csv");
	    logger.info("Clicking on Modify Trigger");
	    cmAddTrigger.modifyTrigger();
	    logger.info("Clicking on Back");
	    cmAddTrigger.backTrigger();
	    logger.info("Clicking on view called triggers");
	    cmAddOffer.viewcalledOffer();
		cmAddOffer.called();
		Assert.assertEquals(cmAddOffer.getAccountNumber(), "90001");
		cmAddOffer.backCalled();
		cmAddOffer.backCalled();
	    logger.info("Click on Add Reward");
	    cmAddOffer.addReward();
	    logger.info("Inserting the currency reward values");
	    cmAddReward.insertCurrency("AUD", "10.00");
	    cmAddReward.insertCurrency("CAD", "10.00");
	    cmAddReward.insertCurrency("DKK", "10.00");
	    cmAddReward.insertCurrency("EUR", "10.00");
	    cmAddReward.insertCurrency("GBP", "10.00");
	    cmAddReward.insertCurrency("HKD", "10.00");
	    cmAddReward.insertCurrency("NOK", "10.00");
	    cmAddReward.insertCurrency("SEK", "10.00");
	    cmAddReward.insertCurrency("SGD", "10.00");
	    cmAddReward.insertCurrency("USD", "10.00");
	    logger.info("Entering the Relative Expiry value");
	    cmAddReward.relativeExpiry("01 00:00");
	    logger.info("Selecting the bet type");
	    cmAddReward.selAllReward();
	    logger.info("Click on insert reward");
	    cmAddReward.insertReward();
	    logger.info("Click on Add Redemption");
	    cmAddReward.addRedemption();
	    logger.info("Click on Any");
	    cmAddReward.any();
	    logger.info("Returning to the offer page");
	    cmAddReward.backReward();
	    logger.info("Waiting for the offer to start");
	    Thread.sleep(120000);
	    logger.info("Generating the session id");
		String ssoid = Helpers.getSsoidForClient("90001");
		cmAddOffer.viewcalledOffer();
		cmAddOffer.called();
		Assert.assertEquals(cmAddOffer.getAccountNumber(), "90001");
		String eventid = "2015342";
		oxi.call("reqBet").withParams("user:openbet,pass:0p3nb3t,ssoid:"+ssoid+",outcome:"+eventid);
		logger.info("Sending the reqAcctGetFreebet request and Filtering the freebetID");
		String outcome="2015333";
		String reqAcctGetFFreebets = oxiLatest.call("reqAcctGetFFreebets").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.respAccountGetFreebets.freebetToken.freebetTokenId");
		String ssoid2 = Helpers.getSsoidForClient("90001");
		String reqBetPlaceWithFreeline = oxi.call("reqBetPlaceWithFreeline").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid2+",outcome:"+outcome+",freebetTokenId:"+reqAcctGetFFreebets).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqBetPlaceWithFreeline, "success");
		logger.info("Received success response");
	}
}
