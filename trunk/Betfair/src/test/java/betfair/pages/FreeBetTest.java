package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.util.Helpers;

public class FreeBetTest extends TestBase{

	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManagerTree;
	CMOfferSearch cmOfferSearch;
	CMAddOffer cmAddOffer;
	CMAddTrigger cmAddTrigger;
	CMAddReward cmAddReward;
	CMFreebet cmFreeBet;
	
	@BeforeClass
	public void classInit() {
		webDriver.get(websiteUrl);
		login = PageFactory.initElements(webDriver, Login.class);
		cmOfferSearch = PageFactory.initElements(webDriver, CMOfferSearch.class);
		cmAddOffer = PageFactory.initElements(webDriver, CMAddOffer.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		cmAddTrigger = PageFactory.initElements(webDriver, CMAddTrigger.class);
		cmAddReward = PageFactory.initElements(webDriver, CMAddReward.class);
		campaignManagerTree = PageFactory.initElements(webDriver, CampaignManagerTree.class);
		cmFreeBet = PageFactory.initElements(webDriver, CMFreebet.class);
	}
	
	@BeforeMethod
	public void loginAdministrator(){
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
		tabs.clickCM();
	}
	
	@Test(description = "C180839 Freebet Upload")
	public void createFile() throws Exception {
		String now = Helpers.getDateWithOffsetInMinutes(-118);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking on the Add Offer button");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the offer name");
		cmAddOffer.offerName("upload");
		logger.info("Setting the start date");
		cmAddOffer.startDate(now);
		logger.info("Entering the country");
		cmAddOffer.selectCountries("United Kingdom");
		logger.info("Entering the currency");
		cmAddOffer.selectCurrencies("UK Pounds Sterling");
		logger.info("Entering the language");
		cmAddOffer.selectLanguages("English");
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Clicking on Add Trigger");
		cmAddOffer.addTriggers();
		logger.info("Selecting the trigger type");
		cmAddTrigger.typeTrigger("Upload Trigger");
		logger.info("Entering the currency value");
		cmAddTrigger.insertCurrency("GBP", "10");
		logger.info("Clicking on Add Trigger");
		cmAddTrigger.addTrigger();
		logger.info("Clicking on Back");
		cmAddTrigger.backTrigger();
		logger.info("Clicking on Add Reward");
		cmAddOffer.addReward();
		logger.info("Emtering the currency value");
		cmAddReward.insertCurrency("GBP", "10");
		logger.info("Entering the relative expiry");
		cmAddReward.relativeExpiry("2 0:00");
		logger.info("Entering the bet type");
		cmAddReward.betTypes("Single");
		logger.info("Clicking on Insert reward");
		cmAddReward.insertReward();
		logger.info("Wwaiting 2 minutes for the offer to start");
		Thread.sleep(120000);
		logger.info("Clicking on Freebet");
		campaignManagerTree.clickFreebet();
		logger.info("Creating the CSV file");
		cmFreeBet.writecsv("..\\freebet.csv","TOKEN ID","USERNAME","CCY CODE","228","10010","GBP");
		logger.info("Writing the path in the field");
		cmFreeBet.send("C:\\Work\\Betfair\\freebet.csv");
		logger.info("Clicking on the Upload button");
		cmFreeBet.upload();
		logger.info("Clicking on the uploaded file");
		cmFreeBet.list();
		logger.info("Loading the token");
		cmFreeBet.load();
		cmFreeBet.acceptAlert();
		logger.info("Clicking on the Delete button");
		cmFreeBet.delete();
		logger.info("Generating the session id");
		String ssoid = Helpers.getSsoidForClient("10010");
		logger.info("Sending the reqAcctGetFreebet request");
		String reqAcctGetFFreebets = oxi.call("reqAcctGetFFreebets").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.respAccountGetFreebets.freebetToken.freebetTokenId");
		logger.info("Filtering the freebetID");
		logger.info("Sending the reqPlaceBetWithFreeline request");
		oxi.call("reqBetPlaceWithFreeline").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",freebetTokenId:"+reqAcctGetFFreebets);
	}
	
	@AfterMethod
	public void logoutAdministrator(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
	
}
