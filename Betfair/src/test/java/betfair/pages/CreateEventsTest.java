package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.pages.AdminDelete.Stuff;
import betfair.util.Helpers;




public class CreateEventsTest extends TestBase{

	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminEventSelectionCriteria adminEventSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;
	AdminSelectionCreation adminSelection;
	AdminDelete adminDelete;

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
		adminDelete = PageFactory.initElements(webDriver, AdminDelete.class);
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}

	@Test(description ="C141950 - Create an English Football Event")
	public void createEnglishFootballEvent() {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Barclays Premier League");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto test football");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.selectMarket("|Match Odds|");
		adminMarket.clickAddEventMarket();
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("home");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		adminDelete.deleteAllStuff(Stuff.SELECTION);

	}

	@Test(description ="C141963 - Create a Horse Racing Event")
	public void createHorseRacingEvent() {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto test race");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("H2");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		adminDelete.deleteAllStuff(Stuff.SELECTION);

	}

	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();

	}
}