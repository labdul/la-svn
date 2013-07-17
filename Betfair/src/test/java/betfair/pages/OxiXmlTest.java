package betfair.pages;


import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.Helpers;

public class OxiXmlTest extends TestBase{


	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminEventSelectionCriteria adminEventSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;
	AdminSelectionCreation adminSelection;
	AdminEventSettle adminEventSettle;
	AdminEventUnsettle adminEventUnsettle;
	AdminCustomerSearch adminCustomerSearch;
	CMOfferSearch cmOfferSearch;
	CMAddOffer cmAddOffer;
	CMAddTrigger cmAddTrigger;
	CMAddReward cmAddReward;
	CMFreebet cmFreeBet;
	CampaignManagerTree campaignManagerTree;
	AdminModifyHorseRacingMarket adminModifyHorseRacingMarket;

	@BeforeClass
	public void classInit() {
		webDriver.get(websiteUrl);
		login = PageFactory.initElements(webDriver, Login.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		adminNavigation = PageFactory.initElements(webDriver, AdminNavigationTree.class);
		adminEvent = PageFactory.initElements(webDriver, AdminEventCreation.class);
		adminMarket = PageFactory.initElements(webDriver, AdminMarketCreation.class);
		adminSelection = PageFactory.initElements(webDriver, AdminSelectionCreation.class);
		adminEventSelection = PageFactory.initElements(webDriver, AdminEventSelectionCriteria.class);
		adminEventSettle = PageFactory.initElements(webDriver, AdminEventSettle.class);
		adminEventUnsettle = PageFactory.initElements(webDriver, AdminEventUnsettle.class);
		adminCustomerSearch = PageFactory.initElements(webDriver, AdminCustomerSearch.class);
		cmOfferSearch = PageFactory.initElements(webDriver, CMOfferSearch.class);
		cmAddOffer = PageFactory.initElements(webDriver, CMAddOffer.class);
		cmAddTrigger = PageFactory.initElements(webDriver, CMAddTrigger.class);
		cmAddReward = PageFactory.initElements(webDriver, CMAddReward.class);
		cmFreeBet = PageFactory.initElements(webDriver, CMFreebet.class);
		campaignManagerTree = PageFactory.initElements(webDriver, CampaignManagerTree.class);
		adminModifyHorseRacingMarket = PageFactory.initElements(webDriver, AdminModifyHorseRacingMarket.class);
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}

	@Test(description ="C217760 - reqTransGetBetDetail")
	public void reqTransGetBetDetail() throws Exception {
		String toCompare = "Settlement results";
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H3");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10004");
		logger.info("Placing Bet");
		List<String> reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withMultipleReturn("oxip.response.respBetPlace.token", "oxip.response.respBetPlace.betPlacement.betId");
		logger.info("Settling event");
		adminEventSettle.selectResult("Win");
		adminEventSettle.insertPlace("1");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickSecondSelection();
		adminEventSettle.selectResult("Lose");
		adminEventSettle.insertPlace("2");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickThirdSelection();
		adminEventSettle.selectResult("Void");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickConfirmResults();
		adminEventSettle.clickSettleMarket();
		logger.info("Settled");
		Assert.assertEquals(adminEventSettle.settlementResults(), toCompare);
		logger.info("Getting Bet Detail");
		String reqTransGetBetDetail = oxiLatest.call("reqTransGetBetDetail").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",token:"+reqBet.get(0)+",betId:"+reqBet.get(1)).withSingleReturn("oxip.response.respTransGetBetDetail.bet.winnings");
		Assert.assertEquals(reqTransGetBetDetail, "20.00");
		logger.info("Winnings Confirmed");

	}

	@Test(description ="C217759 - reqTransGetBetDetails")
	public void reqTransGetBetDetails() throws Exception {
		String toCompare = "Settlement results";
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H3");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10004");
		logger.info("Placing Bet");
		String reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.token");
		logger.info("Settling event");
		adminEventSettle.selectResult("Win");
		adminEventSettle.insertPlace("1");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickSecondSelection();
		adminEventSettle.selectResult("Lose");
		adminEventSettle.insertPlace("2");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickThirdSelection();
		adminEventSettle.selectResult("Void");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickConfirmResults();
		adminEventSettle.clickSettleMarket();
		logger.info("Settled");
		Assert.assertEquals(adminEventSettle.settlementResults(), toCompare);
		logger.info("Getting Bet Details");
		String reqTransGetBetDetails = oxiLatest.call("reqTransGetBetDetails").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",token:"+reqBet).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqTransGetBetDetails, "success");
		logger.info("Winnings Confirmed");
	}

	@Test(description ="C217757 - reqAccountAddStub")
	public void reqAccountAddStub() throws Exception {
		String ssoid = Helpers.getSsoidForClient("10001");;
		String currency = "GBP";
		String accountNo = adminCustomerSearch.randomAccount();
		String reqAccountAddStub = oxiLatest.call("reqAccountAddStub").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",currency:"+currency+",accountNo:"+accountNo).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqAccountAddStub, "success");
		logger.info("Received success response");
	}

	@Test(description ="C217761 - reqAcctGetFreebets")
	public void reqAcctGetFreebets() throws Exception{
		String now = Helpers.getDateWithOffsetInMinutes(-118);
		tabs.clickCM();
		campaignManagerTree.clickOffers();
		campaignManagerTree.clickOffer();
		cmOfferSearch.clickAddOffer();
		cmAddOffer.offerName("upload");
		cmAddOffer.startDate(now);
		cmAddOffer.selectCountries("United Kingdom");
		cmAddOffer.selectCurrencies("UK Pounds Sterling");
		cmAddOffer.selectLanguages("English");
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		cmAddOffer.addTriggers();
		cmAddTrigger.typeTrigger("Upload Trigger");
		cmAddTrigger.insertCurrency("GBP", "10");
		cmAddTrigger.addTrigger();
		cmAddTrigger.backTrigger();
		cmAddOffer.addReward();
		cmAddReward.insertCurrency("GBP", "10");
		cmAddReward.relativeExpiry("2 0:00");
		cmAddReward.betTypes("Single");
		cmAddReward.insertReward();
		logger.info("Waiting 2 minutes for the offer to start");
		Thread.sleep(120000);
		logger.info("Generating the session id");
		String ssoid = Helpers.getSsoidForClient("10001");;
		logger.info("Sending the reqAcctGetFreebet request and Filtering the freebetID");
		String reqAcctGetFFreebets = oxiLatest.call("reqAcctGetFFreebets").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqAcctGetFFreebets, "success");
		logger.info("Received success response");
	}

	@Test(description ="C217761 - reqUtilPing")
	public void reqUtilPing() throws Exception{
		logger.info("Generating the session id");
		String ssoid = Helpers.getSsoidForClient("10001");;
		logger.info("Sending the reqUtilPing request");
		String reqUtilPing = oxi.call("reqUtilPing").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqUtilPing, "success");
		logger.info("Received success response");
	}

	@Test(description ="C217758 - reqFreebetTrigger")
	public void reqFreebetTrigger() throws Exception{
		logger.info("Generating the session id");
		String ssoid = Helpers.getSsoidForClient("20001");
		logger.info("Sending the reqFreebetTrigger request");
		String reqDepFreeBetTrigger = oxiLatest.call("reqDepFreebetTrigger").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqDepFreeBetTrigger, "success");
		logger.info("Received success response");
	}

	@Test(description ="C217764 - reqBetCashout")
	public void reqBetCashout() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H3");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10004");
		logger.info("Placing Bet and Filtering betId and token");
		String reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.betPlacement.betId");
		logger.info("Setting results");
		adminEventSettle.selectResult("Win");
		adminEventSettle.insertPlace("1");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickSecondSelection();
		adminEventSettle.selectResult("Lose");
		adminEventSettle.insertPlace("2");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickThirdSelection();
		adminEventSettle.selectResult("Void");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickConfirmResults();
		String reqBetCashout = oxiLatestSecure.call("reqBetCashout").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "SUCCESS");
		logger.info("Received success response");
	}
	
	@Test(description ="C243516 - reqGetLanguages")
	public void reqGetLanguages() throws Exception{
		logger.info("Generating the session id");
		String ssoid = Helpers.getSsoidForClient("20001");
		logger.info("Sending the reqGetLanguages request");
		String reqGetLanguages = oxiLatest.call("reqGetLanguages").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals(reqGetLanguages, "success");
		logger.info("Received success response");
	}
	
	@Test(description = "reqBetStatus")
	public void reqBetStatus() throws Exception{
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String time = Helpers.getDateWithOffsetInMinutes(-120);
		adminEvent.insertStartTime(time);
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminModifyHorseRacingMarket.insertBirDelay("120");
		adminModifyHorseRacingMarket.selectBir("Yes");
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H3");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickBack();
		String past = Helpers.getDateWithOffsetInMinutes(-480);
		adminEvent.insertStartTime(past);
		adminEvent.clickUpdate();
		adminMarket.clickRaceWinnerMarket();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10008");
		logger.info("Placing Bet");
		List<String> reqBet = new ArrayList<String> (oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withMultipleReturn("oxip.response.respBetPlace.betDelay.status", "oxip.response.respBetPlace.betDelay.birToken", "oxip.response.respBetPlace.token"));
		Assert.assertEquals(reqBet.get(2), "PENDING");
		logger.info("Bet Status is PENDING");
		String birToken = reqBet.get(1);
		String token = reqBet.get(0);
		List <String> reqBetStatus = new ArrayList<String> (oxi.call("reqBetStatus").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",token:"+token+",birToken:"+birToken).withMultipleReturn("oxip.response.returnStatus.message", "oxip.response.respBetStatus.betStatus.status"));
		Assert.assertEquals(reqBetStatus.get(1), "PENDING");
		logger.info("PENDING status received");
		Assert.assertEquals(reqBetStatus.get(0), "success");
		logger.info("success message received");
			
	}
	
	@Test(description = "reqBetBuild")
	public void reqBetBuild() throws Exception{
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H3");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10004");
		String reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals("success", reqBet);
		logger.info("bet placed, success message received");
		String reqBetBuild = oxi.call("reqBetBuild").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.returnStatus.message");
		Assert.assertEquals("success", reqBetBuild);
		logger.info("success message received");

	}
	
			
	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}

}