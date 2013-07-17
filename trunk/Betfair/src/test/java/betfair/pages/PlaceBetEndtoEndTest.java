package betfair.pages;


import java.util.List;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.util.Helpers;

public class PlaceBetEndtoEndTest extends TestBase{


	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminEventSelectionCriteria adminEventSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;
	AdminSelectionCreation adminSelection;
	AdminEventSettle adminEventSettle;
	AdminEventUnsettle adminEventUnsettle;

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
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}

	@Test(description ="C201985 - Place bet - Horse Racing")
	public void placeBetEndtoEndHR() throws Exception {
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
		String ssoid = Helpers.getSsoidForClient("10001");
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
		String reqTransGetBetDetail = oxi.call("reqTransGetBetDetail").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",token:"+reqBet.get(0)+",betId:"+reqBet.get(1)).withSingleReturn("oxip.response.respTransGetBetDetail.bet.winnings");
		Assert.assertEquals(reqTransGetBetDetail, "20.00");
		logger.info("Winnings Confirmed");
	}
	
	@Test(description ="C201990 - Place bet - Football")
	public void placeBetEndtoEndFootball() throws Exception {
		String toCompare = "Settlement results";
		tabs.clickAdmin();
		logger.info("Creating Event");
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Barclays Premier League");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.selectMarket("|Match Odds|");
		adminMarket.clickAddEventMarket();
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("home");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("away");
		adminSelection.selectLocation("away");
		adminSelection.enterFixedPrice("1/2");
		adminSelection.clickAddFinalSelection();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("draw");
		adminSelection.selectLocation("draw");
		adminSelection.enterFixedPrice("1/2");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String eventid = adminSelection.getEventid();
		String ssoid = Helpers.getSsoidForClient("10001");
		logger.info("Placing Bet");
		List<String> reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withMultipleReturn("oxip.response.respBetPlace.token", "oxip.response.respBetPlace.betPlacement.betId");
		logger.info("Settling event");
		adminEventSettle.selectResult("Win");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickSecondSelection();
		adminEventSettle.selectResult("Lose");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickThirdSelection();
		adminEventSettle.selectResult("Void");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickConfirmResults();
		adminEventSettle.clickSettleMarket();
		logger.info("Settled");
		Assert.assertEquals(adminEventSettle.settlementResults(), toCompare);
		logger.info("Getting Bet Detail");
		String reqTransGetBetDetail = oxi.call("reqTransGetBetDetail").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",token:"+reqBet.get(0)+",betId:"+reqBet.get(1)).withSingleReturn("oxip.response.respTransGetBetDetail.bet.winnings");
		Assert.assertEquals(reqTransGetBetDetail, "20.00");
		logger.info("Winnings Confirmed");
		
	}
	
	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}

}