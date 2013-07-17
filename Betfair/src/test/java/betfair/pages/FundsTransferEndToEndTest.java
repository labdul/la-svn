package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.util.Helpers;

public class FundsTransferEndToEndTest extends TestBase{


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

	@Test(description ="C201986 - Funds transfer - Betfair wallet")
	public void fundsTransferEndtoEnd() throws Exception {
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
		logger.info("Getting initial account balance");
		String initialBalance = Helpers.getBalanceForClient("10004");
		logger.info("Placing Bet");
		oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid);
		logger.info("Settling event via void on bet");
		adminEventSettle.selectResult("Void");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickSecondSelection();
		adminEventSettle.selectResult("Lose");
		adminEventSettle.insertPlace("2");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickThirdSelection();
		adminEventSettle.selectResult("Win");
		adminEventSettle.insertPlace("1");
		adminEventSettle.clickSetResults();
		adminEventSettle.clickConfirmResults();
		adminEventSettle.clickSettleMarket();
		logger.info("Settled");
		Assert.assertEquals(adminEventSettle.settlementResults(), toCompare);
		logger.info("Sleeping 5 minutes to ensure refunding chronjob is complete");
		Thread.sleep(300000);
		logger.info("Getting final account balance");
		String finalBalance = Helpers.getBalanceForClient("10004");
		Assert.assertEquals(finalBalance, initialBalance);
		logger.info("Transfer funds and account balance confirmed");
	}
		
	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
	
}