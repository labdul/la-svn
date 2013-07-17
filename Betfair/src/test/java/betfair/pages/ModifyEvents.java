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


public class ModifyEvents extends TestBase {

	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminEventSelectionCriteria adminEventSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;
	AdminSelectionCreation adminSelection;
	AdminModifyFootballMarket adminModifyFootballMarket;
	AdminModifyHorseRacingMarket adminModifyHorseRacingMarket;
	AdminModifyHorseRacingSelection adminModifyHorseRacingSelection;
	AdminModifyFootballSelection adminModifyFootballSelection;
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
		adminModifyFootballMarket = PageFactory.initElements(webDriver, AdminModifyFootballMarket.class);
		adminModifyHorseRacingMarket = PageFactory.initElements(webDriver, AdminModifyHorseRacingMarket.class);
		adminModifyHorseRacingSelection = PageFactory.initElements(webDriver, AdminModifyHorseRacingSelection.class);
		adminModifyFootballSelection = PageFactory.initElements(webDriver, AdminModifyFootballSelection.class);
		adminDelete = PageFactory.initElements(webDriver, AdminDelete.class);

	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}

	@Test(description ="C141955 - Modify a Football Event and view changes in View Audit History")
	public void modifyFootballEvent() {
		String toCompare = "2016-01-01 00:00:00 ";
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Barclays Premier League");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto modify football");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(2));
		adminEvent.insertEventName("automodified");
		adminEvent.clickUpdate();
		adminEvent.clickViewAudit();
		Assert.assertEquals(toCompare, adminEvent.getAuditChange());
		adminModifyFootballSelection.clickBack();
		adminDelete.deleteAllStuff(Stuff.EVENT);
		
	}

	@Test(description ="C141954 - Modify a Football Market and view changes in View Audit History")
	public void modifyFootballMarket() {
		String toCompareDisplay = "Y ";
		String toCompareDisplayOrder = "15 ";
		String toCompareBlurb = "auto blurb ";
		String toCompareDeadHeat = "Y ";
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Barclays Premier League");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto modify fmarket");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.selectMarket("|Match Odds|");
		adminMarket.clickAddEventMarket();
		adminModifyFootballMarket.clickAddMarket();
		adminModifyFootballMarket.insertDisplayOrder("15");
		adminModifyFootballMarket.selectDisplayed("Yes");
		adminModifyFootballMarket.insertBlurb("auto blurb");
		adminModifyFootballMarket.selectDeadHeat("Yes");
		adminModifyFootballMarket.clickModifyMarket();
		adminModifyFootballMarket.clickMarket();
		adminModifyFootballMarket.clickViewAudit();
		Assert.assertEquals(toCompareDisplay, adminModifyFootballMarket.getAuditDisplay());
		Assert.assertEquals(toCompareDisplayOrder, adminModifyFootballMarket.getAuditDisplayOrder());
		Assert.assertEquals(toCompareBlurb, adminModifyFootballMarket.getAuditBlurb());
		Assert.assertEquals(toCompareDeadHeat, adminModifyFootballMarket.getAuditDeadHeat());
		adminModifyFootballSelection.clickBack();
		adminDelete.deleteAllStuff(Stuff.MARKET);

	}

	@Test(description ="C141953 Modify a Football Selection and view changes in View Audit History")
	public void modifyFootballSelection() {
		String toCompareTimeOne = adminModifyFootballSelection.getNowTime();
		String toCompareLP = "0 ";
		String toCompareDesc = "Fm ";
		String toCompareDisporder = "10 ";
		String toCompareChannel = "M ";
		String toCompareTimeTwo = adminModifyFootballSelection.getNowTime();
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Barclays Premier League");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto modify footballselection");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.selectMarket("|Match Odds|");
		adminMarket.clickAddEventMarket();
		adminMarket.clickAddMarket();
		adminSelection.clickAddSelection();
		adminSelection.enterDescription("F1");
		adminSelection.enterFixedPrice("1/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		adminSelection.enterDescription("Fm");
		adminSelection.enterFixedPrice("1/2");
		adminSelection.clickModify();
		adminSelection.acceptAlert();
		adminSelection.clickSelection();
		adminSelection.clickAudit();
		Assert.assertNotEquals(toCompareTimeOne, adminModifyFootballSelection.getAuditTime(5), "TimeOne Assert");
		Assert.assertNotEquals(toCompareLP, adminModifyFootballSelection.getAuditPrice(5), "LP Assert");
		Assert.assertEquals(toCompareDesc, adminModifyFootballSelection.getAuditDesc(5), "Description Assert");
		adminModifyFootballSelection.clickBack();
		adminSelection.insertDisplayOrder("10");
		adminSelection.clickInternet();
		adminSelection.clickModify();
		adminSelection.acceptAlert();
		adminSelection.clickSelection();
		adminSelection.clickAudit();
		Assert.assertNotEquals(toCompareTimeTwo, adminModifyFootballSelection.getAuditTime(6), "TimeTwo Assert");
		Assert.assertEquals(toCompareDisporder, adminModifyFootballSelection.getAuditDisporder(6), "Disporder Assert");
		Assert.assertEquals(toCompareChannel, adminModifyFootballSelection.getAuditChannel(6), "Channel Assert");
		adminModifyFootballSelection.clickBack();
		adminDelete.deleteAllStuff(Stuff.SELECTION);

	}

	@Test(description ="C141968 - Modify an HR Event and view changes in View Audit History")
	public void modifyHorseRacingEvent() {
		String toCompare = "2016-01-01 00:00:00 ";
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto modify hr");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(2));
		adminEvent.insertEventName("automodified");
		adminEvent.clickUpdate();
		adminEvent.clickViewAudit();
		Assert.assertEquals(toCompare, adminEvent.getAuditChange());
		adminModifyHorseRacingSelection.clickBack();
		adminDelete.deleteAllStuff(Stuff.EVENT);

	}

	@Test(description ="C141967 - Modify an HR Market to EachWay and view changes in Audit History")
	public void modifyHorseRacingMarket() {
		String toCompareEw = "Y ";
		String toComparePlaces = "3 ";
		String toCompareAtOne = "1 ";
		String toCompareAtTwo = "4 ";
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto modify hrmarket");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminMarket.clickAddMarket();
		adminModifyHorseRacingMarket.selectEw("Yes");
		adminModifyHorseRacingMarket.insertPlaces("3");
		adminModifyHorseRacingMarket.insertAtOne("1");
		adminModifyHorseRacingMarket.insertAtTwo("4");
		adminModifyHorseRacingMarket.clickModifyMarket();
		adminModifyHorseRacingMarket.clickMarket();
		adminModifyHorseRacingMarket.clickViewAudit();
		Assert.assertEquals(toCompareEw, adminModifyHorseRacingMarket.getAuditEw());
		Assert.assertEquals(toComparePlaces, adminModifyHorseRacingMarket.getAuditPlaces());
		Assert.assertEquals(toCompareAtOne, adminModifyHorseRacingMarket.getAuditAtOne());
		Assert.assertEquals(toCompareAtTwo, adminModifyHorseRacingMarket.getAuditAtTwo());
		adminModifyHorseRacingSelection.clickBack();
		adminDelete.deleteAllStuff(Stuff.MARKET);

	}

	@Test(description ="C141966 Modify a HR Selection and view changes in View Audit History")
	public void modifyHorseRacingSelection() {
		String toCompareTimeOne = adminModifyHorseRacingSelection.getNowTime();
		String toCompareLP = "0 ";
		String toCompareDesc = "Hm ";
		String toCompareDisporder = "10 ";
		String toCompareChannel = "M ";
		String toCompareTimeTwo = adminModifyHorseRacingSelection.getNowTime();
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("Horse Racing");
		adminEventSelection.selectType("Bath");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName("auto modify hrselection");
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.clickAddEventMarket();
		adminMarket.clickAddMarket();
		adminSelection.clickAddSelection();
		adminSelection.enterDescription("H1");
		adminSelection.enterFixedPrice("1/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		adminSelection.enterDescription("Hm");
		adminSelection.enterFixedPrice("1/2");
		adminSelection.clickModify();
		adminSelection.acceptAlert();
		adminSelection.clickSelection();
		adminSelection.clickAudit();
		Assert.assertNotEquals(toCompareTimeOne, adminModifyHorseRacingSelection.getAuditTime(5), "TimeOne Assert");
		Assert.assertNotEquals(toCompareLP, adminModifyHorseRacingSelection.getAuditPrice(5), "LP Assert");
		Assert.assertEquals(toCompareDesc, adminModifyHorseRacingSelection.getAuditDesc(5), "Description Assert");
		adminModifyHorseRacingSelection.clickBack();
		adminSelection.insertDisplayOrder("10");
		adminSelection.clickInternet();
		adminSelection.clickModify();
		adminSelection.acceptAlert();
		adminSelection.clickSelection();
		adminSelection.clickAudit();
		Assert.assertNotEquals(toCompareTimeTwo, adminModifyHorseRacingSelection.getAuditTime(6), "TimeTwo Assert");
		Assert.assertEquals(toCompareDisporder, adminModifyHorseRacingSelection.getAuditDisporder(6), "Disporder Assert");
		Assert.assertEquals(toCompareChannel, adminModifyHorseRacingSelection.getAuditChannel(6), "Channel Assert");
		adminModifyHorseRacingSelection.clickBack();
		adminDelete.deleteAllStuff(Stuff.SELECTION);

	}

	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
}