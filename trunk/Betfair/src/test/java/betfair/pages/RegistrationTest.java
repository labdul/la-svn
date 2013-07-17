package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.Helpers;

public class RegistrationTest extends TestBase{
	
	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManagerTree;
	CMOfferSearch cmOfferSearch;
	CMOfferList cmOfferList;
	CMAddOffer cmAddOffer;
	CMAddTrigger cmAddTrigger;
	CMAddReward cmAddReward;
	AdminEventCreation adminEvent;

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
	public void registration() throws Exception {
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
		cmAddOffer.offerName("reg1");
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
		cmAddTrigger.typeTrigger("Registration");
		logger.info("Inserting the trigger currency values");
	    String id = adminEvent.randomName();
		cmAddTrigger.insertCurrency("Promotion Code", id);
		logger.info("Click on Add Trigger");
	    cmAddTrigger.addTrigger();
	    logger.info("Returning to the offer page");
	    cmAddTrigger.backTrigger();
	    logger.info("Click on Add Reward");
	    cmAddOffer.addReward();
	    logger.info("Inserting the currency reward values");
	    cmAddReward.insertCurrency("AUD", "20.00");
	    cmAddReward.insertCurrency("CAD", "20.00");
	    cmAddReward.insertCurrency("DKK", "20.00");
	    cmAddReward.insertCurrency("EUR", "20.00");
	    cmAddReward.insertCurrency("GBP", "20.00");
	    cmAddReward.insertCurrency("HKD", "20.00");
	    cmAddReward.insertCurrency("NOK", "20.00");
	    cmAddReward.insertCurrency("SEK", "20.00");
	    cmAddReward.insertCurrency("SGD", "20.00");
	    cmAddReward.insertCurrency("USD", "20.00");
	    logger.info("Entering the Relative Expiry value");
	    cmAddReward.relativeExpiry("01 00:00");
	    logger.info("Selecting the bet type");
	    cmAddReward.selAllReward();
	    logger.info("Click on insert reward");
	    cmAddReward.insertReward();
	    logger.info("Returning to the offer page");
	    cmAddReward.backReward();
	    logger.info("Waiting for the offer to start");
	    Thread.sleep(120000);
	    logger.info("Generating the session id");
		String ssoid = Helpers.getSsoidForClient("10002");
		String action = "REG";
		String value = id;
		logger.info("Sending the reqFreeBetTrigger request");
		String reqFreebetTrigger = oxi.call("reqFreebetTrigger").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",action:"+action+",value:"+value).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqFreebetTrigger, "success");
		logger.info("Received success response");
		cmAddOffer.viewcalledOffer();
		cmAddOffer.called();
		Assert.assertEquals(cmAddOffer.getAccountNumber(), "10002");
		logger.info("Sending the reqAcctGetFreebet request and Filtering the freebetID");
		String outcome="2014909";
		String reqAcctGetFFreebets = oxiLatest.call("reqAcctGetFFreebets").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.respAccountGetFreebets.freebetToken.freebetTokenId");
		String reqBetPlaceWithFreeline = oxi.call("reqBetPlaceWithFreeline").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+outcome+",freebetTokenId:"+reqAcctGetFFreebets).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqBetPlaceWithFreeline, "success");
		logger.info("Received success response");
	}
	
}
