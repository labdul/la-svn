package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.util.Helpers;



public class SearchBetTest extends TestBase{
	
	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminCustomerSearch adminCustomerSearch;
	AdminBetSearch adminBetSearch;
	AdminEventSelectionCriteria adminEventSelection;
	AdminSelectionCreation adminSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;

	@BeforeClass
	public void classInit() {
		webDriver.get(websiteUrl);
		login = PageFactory.initElements(webDriver, Login.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		adminNavigation = PageFactory.initElements(webDriver, AdminNavigationTree.class);
		adminCustomerSearch = PageFactory.initElements(webDriver, AdminCustomerSearch.class);
		adminBetSearch = PageFactory.initElements(webDriver, AdminBetSearch.class);
		adminEventSelection = PageFactory.initElements(webDriver, AdminEventSelectionCriteria.class);
		adminSelection = PageFactory.initElements(webDriver, AdminSelectionCreation.class);
		adminEvent = PageFactory.initElements(webDriver, AdminEventCreation.class);
		adminMarket = PageFactory.initElements(webDriver, AdminMarketCreation.class);
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}


	@Test(description ="C93962 - Find Bets with Receipt like")
	public void searchBetReceipt() throws Exception {
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
		String reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.betPlacement.receipt");
		tabs.clickAdmin();
		adminNavigation.clickQueries();
		adminNavigation.clickFixedOddsBet();
		adminBetSearch.insertReceipt(reqBet);
		adminBetSearch.clickFindBets();
		String toCompare = adminBetSearch.getReceipt();
		Assert.assertEquals(reqBet, toCompare);
		
	}

	@Test(description ="C141958 - Find Bets with Username like")
	public void searchBetUsername()  throws Exception {
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
		String reqBet = oxi.call("reqBet").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",outcome:"+eventid).withSingleReturn("oxip.response.respBetPlace.betPlacement.receipt");
		tabs.clickAdmin();
		adminNavigation.clickQueries();
		adminNavigation.clickFixedOddsBet();
		adminBetSearch.insertUsername("10001");
		adminBetSearch.clickFindBets();
		String toCompare = adminBetSearch.getReceipt();
		Assert.assertEquals(reqBet, toCompare);

	}

	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();

	}
}