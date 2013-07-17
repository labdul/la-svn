package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.pages.AdminDelete.Stuff;
import betfair.util.Helpers;




public class WinLoseVoidTest extends TestBase{

	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminEventSelectionCriteria adminEventSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;
	AdminSelectionCreation adminSelection;
	AdminEventSettle adminEventSettle;
	AdminEventUnsettle adminEventUnsettle;
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
		adminEventSettle = PageFactory.initElements(webDriver, AdminEventSettle.class);
		adminEventUnsettle = PageFactory.initElements(webDriver, AdminEventUnsettle.class);
		adminDelete = PageFactory.initElements(webDriver, AdminDelete.class);
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}

	@Test(description ="C141956 - Set results (Win, Lose and Void) for a selections and settle Market")
	public void setResults() {
		String toCompare = "Settlement results";
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto set race");
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
		adminEventSettle.clickFirstSelection();
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
		adminEventSettle.clickBackButton();
		adminEventSettle.clickFirstSelection();
		adminDelete.clickDeleteSelection();
		adminEventSettle.clickFirstSelection();
		adminDelete.clickDeleteSelection();
		adminEventSettle.clickFirstSelection();
		adminDelete.deleteAllStuff(Stuff.SELECTION);

	}

	@Test(description ="C141957	- Unsettle market")
	public void unsetResults() {
		String toCompare ="Selection Unsettled";
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto unset race");
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
		adminEventSettle.clickFirstSelection();
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
		adminNavigation.clickUnsettlement();
		adminEventUnsettle.insertUnsettlementDates(Helpers.getDateWithOffsetInYears(1));
		adminEventUnsettle.clickContinue();
		adminEventUnsettle.clickCategoryHorseRacing();
		adminEventUnsettle.clickClassHorseRacing();
		adminEventUnsettle.clickTypeBath();
		adminEventUnsettle.clickShowMarkets();
		adminEventUnsettle.clickShowMarketSelections();
		adminEventUnsettle.clickSelectionOne();
		adminEventUnsettle.clickUnsettle();
		Assert.assertEquals(adminEventUnsettle.getUnsettledText(), toCompare);
		adminEventUnsettle.clickBack();
		adminEventUnsettle.clickSelectionTwo();
		adminEventUnsettle.clickUnsettle();
		Assert.assertEquals(adminEventUnsettle.getUnsettledText(), toCompare);
		adminEventUnsettle.clickBack();
		adminEventUnsettle.clickSelectionThree();
		adminEventUnsettle.clickUnsettle();
		Assert.assertEquals(adminEventUnsettle.getUnsettledText(), toCompare);
		adminEventUnsettle.clickBack();
		logger.info("Unsettled");
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.insertModDates();
		adminEventSelection.selectSettled("Either");
		adminEventSelection.clickShowEvents();
		adminEventUnsettle.clickAutoUnsetSearched();
		adminMarket.clickRaceWinnerMarket();
		adminEventSettle.clickFirstSelection();
		adminDelete.clickDeleteSelection();
		adminEventSettle.clickFirstSelection();
		adminDelete.clickDeleteSelection();
		adminEventSettle.clickFirstSelection();
		adminDelete.deleteAllStuff(Stuff.SELECTION);

	}

	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();

	}
}


