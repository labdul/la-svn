package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.Helpers;

public class DbPublishTest extends TestBase{


	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminEventSelectionCriteria adminEventSelection;
	AdminEventCreation adminEvent;
	AdminMarketCreation adminMarket;
	AdminSelectionCreation adminSelection;
	EventClasses eventClasses;
	AdminEventSettle adminEventSettle;

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
		eventClasses = PageFactory.initElements(webDriver, EventClasses.class);
		adminEventSettle = PageFactory.initElements(webDriver, AdminEventSettle.class);
		
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}
	
	@Test(description = "C244605 - getClasses")
	public void getClasses() throws Exception {
		String status = Helpers.dbPublish("?template=getClasses", "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244606 - getEventDetailsHR")
	public void getEventDetailsHR() throws Exception {
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
		String id = adminEvent.printID();
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
		adminMarket.selectDisplayed("Yes");
		adminMarket.clickModifyMarket();
		String status = Helpers.dbPublish("?template=getEventDetails&event=" + id, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244607 - getEventDetailsFootball")
	public void getEventDetailsFootball() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Npower League Two");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		String id = adminEvent.printID();
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
		adminMarket.selectDisplayed("Yes");
		adminMarket.clickModifyMarket();
		String status = Helpers.dbPublish("?template=getEventDetails&event=" + id, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244608 - getTypesByClassHR")
	public void getTypesByClassHR() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEventsClasses();
		eventClasses.clickHorseRacingClass();
		String classNumber = eventClasses.getId() ; 
		String status = Helpers.dbPublish("?template=getTypesByClass&class=" + classNumber, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244609 - getTypesByClassFootball")
	public void getTypesByClassFootball() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEventsClasses();
		eventClasses.clickEnglishFootballClass();
		String classNumber = eventClasses.getId() ; 
		String status = Helpers.dbPublish("?template=getTypesByClass&class=" + classNumber, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244610 - getResultsByEventHR")
	public void getResultsByEventHR() throws Exception {
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
		String id = adminEvent.printID();
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
		adminMarket.selectDisplayed("Yes");
		adminMarket.clickModifyMarket();
		logger.info("Settling event");
		adminMarket.clickFirstMarket("Race Winner");
		adminSelection.clickSelection();
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
		String status = Helpers.dbPublish("?template=getResultsByEvent&event=" + id, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244611 - getResultsByEventFootball")
	public void getResultsByEventFootball() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Npower League Two");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		String id = adminEvent.printID();
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
		adminMarket.selectDisplayed("Yes");
		adminMarket.clickModifyMarket();
		logger.info("Settling event");
		adminMarket.clickFirstMarket("|Match Odds|");
		adminSelection.clickSelection();
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
		String status = Helpers.dbPublish("?template=getResultsByEvent&event=" + id, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244612 - getEventsByType")
	public void getEventsByType() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEventsClasses();
		eventClasses.clickHorseRacingClass();
		eventClasses.clickType("|Bath|");
		String typeNumber = eventClasses.getId() ; 
		String status = Helpers.dbPublish("?template=getEventsByType&type=" + typeNumber, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244613 - getEventsHR")
	public void getEventsHR() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEventsClasses();
		eventClasses.clickHorseRacingClass();
		String classNumber = eventClasses.getId() ; 
		String status = Helpers.dbPublish("?template=getEvents&class=" + classNumber, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244614 - getEventsFootball")
	public void getEventsFootball() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEventsClasses();
		eventClasses.clickEnglishFootballClass();
		String classNumber = eventClasses.getId() ; 
		String status = Helpers.dbPublish("?template=getEvents&class=" + classNumber, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244615 - getOutcomeDetailsHR")
	public void getOutcomeDetailsHR() throws Exception {
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
		adminSelection.clickSelection();
		String outcomeId = adminSelection.getEventid();
		adminSelection.clickBack();
		adminMarket.selectDisplayed("Yes");
		adminMarket.clickModifyMarket();
		String status = Helpers.dbPublish("?template=getOutcomeDetails&outcome=" +outcomeId, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	@Test(description = "C244616 - getOutcomeDetailsFootball")
	public void getOutcomeDetailsFootball() throws Exception {
		tabs.clickAdmin();
		adminNavigation.clickBettingSetup();
		adminNavigation.clickEvents();
		adminEventSelection.selectClass("English Football");
		adminEventSelection.selectType("Npower League Two");
		adminEventSelection.clickAddEvent();
		adminEvent.insertEventName(adminEvent.randomName());
		adminEvent.insertStartTime(Helpers.getDateWithOffsetInYears(1));
		adminEvent.clickAddEvent();
		adminMarket.selectMarket("|Match Odds|");
		adminMarket.clickAddEventMarket();
		adminMarket.selectDisplayed("Yes");
		adminEvent.clickAddMarket();
		adminMarket.clickAddSelection();
		adminSelection.enterDescription("home");
		adminSelection.enterFixedPrice("3/1");
		adminSelection.clickAddFinalSelection();
		adminSelection.clickSelection();
		String outcomeId = adminSelection.getEventid();
		adminSelection.clickBack();
		adminMarket.selectDisplayed("Yes");
		adminMarket.clickModifyMarket();
		String status = Helpers.dbPublish("?template=getOutcomeDetails&outcome=" +outcomeId, "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	
	@Test(description = "C244617 - status")
	public void status() throws Exception {
		String status = Helpers.dbPublish("/status", "oxip.response.message");
		Assert.assertEquals(status, "success");
		logger.info("Success message returned");
	}
	
	
	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
		
	}
}