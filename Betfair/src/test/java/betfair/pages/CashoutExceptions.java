package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.util.Helpers;

public class CashoutExceptions extends TestBase{


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
	CMAddOffer cmaddoffer;

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
		cmaddoffer = PageFactory.initElements(webDriver, CMAddOffer.class);

	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}

	@Test(description ="C227807 - CASHOUT_VALUE_TOO_HIGH")
	public void reqBetCashout_CASHOUT_VALUE_TOO_HIGH() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_VALUE_TOO_HIGH").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_VALUE_TOO_HIGH");
		logger.info("Received CASHOUT_VALUE_TOO_HIGH response");
	}

	@Test(description ="C227808 - CASHOUT_OUTCOME_NOT_RESULTED")
	public void reqBetCashout_CASHOUT_OUTCOME_NOT_RESULTED() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		String reqBetCashout = oxiLatestSecure.call("reqBetCashout").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_NOT_RESULTED");
		logger.info("Received CASHOUT_OUTCOME_NOT_RESULTED response");

	}

	@Test(description ="C227809 - CASHOUT_BET_ALREADY_SETTLED")
	public void reqBetCashout_CASHOUT_BET_ALREADY_SETTLED() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		adminEventSettle.clickSettleMarket();
		String reqBetCashout = oxiLatestSecure.call("reqBetCashout").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_BET_ALREADY_SETTLED");
		logger.info("Received CASHOUT_BET_ALREADY_SETTLED response");
	}

	@Test(description ="C227810 - CASHOUT_OUTCOME_RESULTED")
	public void reqBetCashout_CASHOUT_OUTCOME_RESULTED() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_OUTCOME_RESULTED").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_RESULTED");
		logger.info("Received CASHOUT_OUTCOME_RESULTED response");
	}


	@Test(description ="C227811 - CASHOUT_OUTCOME_NOT_BETABLE")
	public void reqBetCashout_CASHOUT_OUTCOME_NOT_BETABLE() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminMarket.selectLive("No");
		adminMarket.selectSp("Yes");
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterSpGuide("1/2");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterSpGuide("1/2");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H3");
		adminSelection.enterSpGuide("1/2");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10004");
		String reqBet = oxi.call("reqBetSP").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.betPlacement.betId");
		adminSelection.selectStatus("Suspended");
		adminSelection.clickModify();
		cmaddoffer.acceptAlert();
		adminMarket.selectMarketStatus("Suspended");
		adminMarket.clickModifyMarket();
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_OUTCOME_NOT_BETABLE").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_NOT_BETABLE");
		logger.info("Received CASHOUT_OUTCOME_NOT_BETABLE response");

	}

	@Test(description ="C227812 - CASHOUT_OUTCOME_BETABLE")
	public void reqBetCashout_CASHOUT_OUTCOME_BETABLE() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_OUTCOME_BETABLE").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_BETABLE");
		logger.info("Received CASHOUT_OUTCOME_BETABLE response");
	}


	@Test(description ="C227813 - CASHOUT_OUTCOME_PRICE_CHANGED")
	public void reqBetCashout_CASHOUT_OUTCOME_PRICE_CHANGED() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminMarket.selectLive("No");
		adminMarket.selectSp("Yes");
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterSpGuide("1/2");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterSpGuide("1/2");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H3");
		adminSelection.enterSpGuide("1/2");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10004");
		logger.info("Placing Bet and Filtering betId and token");
		String reqBet = oxi.call("reqBetSP").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.betPlacement.betId");
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_OUTCOME_PRICE_CHANGED").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_PRICE_CHANGED");
		logger.info("Received CASHOUT_OUTCOME_PRICE_CHANGED response");
	}

	@Test(description ="C227814 - CASHOUT_INVALID_EXPECTATION")
	public void reqBetCashout_CASHOUT_INVALID_EXPECTATION() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_INVALID_EXPECTATION").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_INVALID_EXPECTATION");
		logger.info("Received CASHOUT_INVALID_EXPECTATION response");
	}

	@Test(description ="C227815 - CASHOUT_OUTCOME_PRICE_NOT_PROVIDED")
	public void reqBetCashout_CASHOUT_OUTCOME_PRICE_NOT_PROVIDED() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_OUTCOME_PRICE_NOT_PROVIDED").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_PRICE_NOT_PROVIDED");
		logger.info("Received CASHOUT_OUTCOME_PRICE_NOT_PROVIDED response");
	}

	@Test(description ="C227816 - CASHOUT_OUTCOME_HANDICAP_CHANGED")
	public void reqBetCashout_CASHOUT_OUTCOME_HANDICAP_CHANGED() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Barclays Premier League");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
		adminEvent.clickAddEvent();
		adminMarket.selectMarket("|Over/Under| |3.5 Goals|");
		adminMarket.clickAddEventMarket();
		adminMarket.insertHigherLower("2");
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("home");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10004");
		logger.info("Placing Bet and Filtering betId and token");
		String reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.betPlacement.betId");
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_OUTCOME_HANDICAP_CHANGED").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_HANDICAP_CHANGED");
		logger.info("Received CASHOUT_OUTCOME_HANDICAP_CHANGED response");

	}

	@Test(description ="C227817 - CASHOUT_OUTCOME_PRICE_EMPTY")
	public void reqBetCashout_CASHOUT_OUTCOME_PRICE_EMPTY() throws Exception {
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		String startTime = Helpers.getDateWithOffsetInYears(1);
		adminEvent.insertStartTime(startTime);
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
		adminSelection.enterFixedPrice("");
		adminSelection.clickForceRemoval();
		cmaddoffer.acceptAlert();
		adminSelection.selectStatus("Suspended");
		adminSelection.clickModify();
		cmaddoffer.acceptAlert();
		adminMarket.selectLive("No");
		adminMarket.clickModifyMarket();
		String reqBetCashout = oxiLatestSecure.call("reqBetCashoutCASHOUT_OUTCOME_PRICE_EMPTY").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",betId:"+reqBet+",eventid:"+eventid).withSingleReturn("oxip.response.respBetCashout.result");
		Assert.assertEquals(reqBetCashout, "CASHOUT_OUTCOME_PRICE_EMPTY");
		logger.info("Received CASHOUT_OUTCOME_PRICE_EMPTY response");

	}

	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}

}