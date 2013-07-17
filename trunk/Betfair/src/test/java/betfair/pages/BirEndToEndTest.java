package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.util.Helpers;

public class BirEndToEndTest extends TestBase{


	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminEventSelectionCriteria adminEventSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;
	AdminSelectionCreation adminSelection;
	AdminEventSettle adminEventSettle;
	AdminEventUnsettle adminEventUnsettle;
	AdminModifyHorseRacingMarket adminModifyHorseRacingMarket;
	AdminBetSearch adminBetSearch;

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
		adminModifyHorseRacingMarket = PageFactory.initElements(webDriver, AdminModifyHorseRacingMarket.class);
		adminBetSearch = PageFactory.initElements(webDriver, AdminBetSearch.class);
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}

	@Test(description ="C207483 - BIR")
	public void birEndToEndTest() throws Exception{
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
		String reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.betDelay.status");
		String toCompare = "PENDING";
		Assert.assertEquals(reqBet, toCompare);
		logger.info("Bet Status is PENDING");
		tabs.clickAdmin();
		adminNavigation.clickQueries();
		adminNavigation.clickFixedOddsBet();
		adminBetSearch.insertUsername("10008");
		adminBetSearch.clickFindBets();
		Assert.assertTrue(adminBetSearch.isNoBetsFoundElementDisplayed());
		logger.info("No bets have been found - Correct");
		logger.info("Sleeping until BIR delay expires");
		Thread.sleep(130000);
		tabs.clickAdmin();
		adminNavigation.clickQueries();
		adminNavigation.clickFixedOddsBet();
		adminBetSearch.insertUsername("10008");
		adminBetSearch.clickFindBets();
		Assert.assertTrue(adminBetSearch.isReceiptElementDisplayed());
		logger.info("Bet is found");
	}

	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
}