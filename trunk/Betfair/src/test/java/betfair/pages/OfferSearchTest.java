package betfair.pages;

import junit.framework.Assert;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.DataProviders;
import betfair.util.Helpers;

public class OfferSearchTest extends TestBase{

	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManagerTree;
	CMOfferSearch cmOfferSearch;
	CMOfferList cmOfferList;
	CMAddOffer cmAddOffer;
	CMAddTrigger cmAddTrigger;
	CMAddReward cmAddReward;
	
	@BeforeClass
	public void classInit() {
		webDriver.get(websiteUrl);
		login = PageFactory.initElements(webDriver, Login.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		campaignManagerTree = PageFactory.initElements(webDriver, CampaignManagerTree.class);
		cmOfferSearch = PageFactory.initElements(webDriver, CMOfferSearch.class);
		cmOfferList = PageFactory.initElements(webDriver, CMOfferList.class);
		cmAddOffer = PageFactory.initElements(webDriver, CMAddOffer.class);
		cmAddTrigger = PageFactory.initElements(webDriver, CMAddTrigger.class);
		cmAddReward = PageFactory.initElements(webDriver, CMAddReward.class);
	}
	
	@BeforeMethod
	public void loginAdministrator(){
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}
	
	@AfterMethod
	public void logoutAdministrator(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
	
	@Test(description = "T328856 Create an Offer", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void createCampaign(String offerName, String currency, String language, String country) {
		String yesterday = Helpers.getDateWithOffsetInDays(-1);
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		tabs.clickCM();
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Search Offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Clicking Add Offer from the offer list page");
		cmOfferList.clickAddOfferList();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();		
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(yesterday);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Deleting the new offer");
		cmAddOffer.deleteOffer();
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T328839 Search by Offer Currently Running", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void searchCR(String offerName, String currency, String language, String country) {
		String yesterday = Helpers.getDateWithOffsetInDays(-1);
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String toCompare = offerName;
		tabs.clickCM();
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Search Offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Clicking Add Offer from the Offers List");
		cmOfferList.clickAddOfferList();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(yesterday);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to Yes");
		cmOfferSearch.selectCurrentlyRunning("Yes");
		logger.info("Click on Search Offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click Back from the empty Offer List");
		cmOfferList.backEmptyList();	
		logger.info("Enter the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer(); 
		Assert.assertEquals(toCompare, cmOfferList.getFirstResultText());
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T328842 Search by Offer Start Date", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void searchDate(String offerName, String currency, String language, String country) {
		String yesterday = Helpers.getDateWithOffsetInDays(-1);
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String today = Helpers.getDateWithOffsetInDays(0);
		String oneWeek = Helpers.getDateWithOffsetInDays(7);
		tabs.clickCM();
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on Add Offer from the Offers List");
		cmOfferList.clickAddOfferList();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(yesterday);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the offer search start date");
		cmOfferSearch.start(yesterday);
		logger.info("Entering the second search start date");
		cmOfferSearch.startAnd(today);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Enter the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click back from the empty offer list");
		cmOfferList.backEmptyList();
		logger.info("Entering the first search start date");
		cmOfferSearch.start(yesterday);
		logger.info("Entering the second search start date");
		cmOfferSearch.startAnd(oneWeek);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on delete offerr");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T328841 Search by Offer Language", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void searchLanguage(String offerName, String currency, String language, String country, String languageUpdate) {
		String yesterday = Helpers.getDateWithOffsetInDays(-1);
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		tabs.clickCM();
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on Add Offer from the Offers List");
		cmOfferList.clickAddOfferList();
		logger.info("Enter the offer name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(yesterday);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click on back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set search language");
		cmOfferSearch.selectLanguage(languageUpdate);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search Offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on back from the empty offer list");
		cmOfferList.backEmptyList();
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Setting the search language");
		cmOfferSearch.selectLanguage(language);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T328843 Search by Offer Name", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void searchName(String offerName, String currency, String language, String country) {
		String yesterday = Helpers.getDateWithOffsetInDays(-1);
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String toCompare = offerName;
		tabs.clickCM();
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("CLick on search");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on Add Offer from the Offers List");
		cmOfferList.clickAddOfferList();
		logger.info("Set the offer name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Set the start date");
		cmAddOffer.startDate(yesterday);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Set the start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Click on Add Offer");
		cmAddOffer.addNewOffer();
		logger.info("Click on back to return to the offer search page");
		cmAddOffer.backOffer();
		cmOfferSearch.offerSearchName(toCompare);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		Assert.assertEquals(toCompare, cmOfferList.getFirstResultText());
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T328847 Search by Offer Well Formed", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void searchWF(String offerName, String currency, String language, String country) {
		String yesterday = Helpers.getDateWithOffsetInDays(-1);
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String toCompare = offerName;
		tabs.clickCM();
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on Add Offer from the Offers List page");
		cmOfferList.clickAddOfferList();
		logger.info("Set the offer name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Set the start date ");
		cmAddOffer.startDate(yesterday);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Set the start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Click on Add Offer");
		cmAddOffer.addNewOffer();
		logger.info("Click on back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Set the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set well formed to Yes");
		cmOfferSearch.selectWellFormed("Yes");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on back from the empty offer list");
		cmOfferList.backEmptyList();
		logger.info("Set the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		Assert.assertEquals(toCompare, cmOfferList.getFirstResultText());
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T328840 Delete an un-used Offer", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void deleteCampaign(String offerName, String currency, String language, String country) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String toCompare = offerName;
		tabs.clickCM();
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on Add Pffer from the offers list");
		cmOfferList.clickAddOfferList();
		logger.info("Set the offer name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Set start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Click on Add Offer");
		cmAddOffer.addNewOffer();
		logger.info("Click on back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Set the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		Assert.assertEquals(toCompare, cmOfferList.getFirstResultText());
		logger.info("Click on the resulted offer");
		cmOfferList.offerListName();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on cancel");
		cmAddOffer.acceptCancel();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OKr");
		cmAddOffer.acceptAlert();
		logger.info("Set the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Set well formed to No");
		cmOfferSearch.selectWellFormed("No");
		logger.info("Set currently running to -");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on back from the empty offer list");
		cmOfferList.backEmptyList();
	}
	
}
