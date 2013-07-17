package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import betfair.util.DataProviders;
import betfair.util.Helpers;

public class AmendOfferTest extends TestBase{

	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManager;
	CampaignManagerTree campaignManagerTree;
	CMOfferSearch cmOfferSearch;
	CMOfferList cmOfferList;
	CMAddOffer cmAddOffer;
	
	@BeforeClass
	
	public void classInit() {
		webDriver.get(websiteUrl);
		login = PageFactory.initElements(webDriver, Login.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		campaignManager = PageFactory.initElements(webDriver, CampaignManagerTree.class);
		campaignManagerTree = PageFactory.initElements(webDriver, CampaignManagerTree.class);
		cmOfferSearch = PageFactory.initElements(webDriver, CMOfferSearch.class);
		cmOfferList = PageFactory.initElements(webDriver, CMOfferList.class);
		cmAddOffer = PageFactory.initElements(webDriver, CMAddOffer.class);
	}

	@BeforeMethod
	public void loginAdministrator(){
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
		tabs.clickCM();
	}
	
	@AfterMethod
	public void logoutAdministrator(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
	
	@Test(description = "T413798 Amend Offer - Setup Offer", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendSetup(String offerName, String currency, String language, String country) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering the start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Clicking back to the offer search page");
		cmAddOffer.backOffer();
	}
	
	@Test(description = "T413803 Amend Offer - Country", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendCountry(String offerName, String currency, String language, String country, String countryUpdate, String countryUpdated) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Clicking back to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the Offer Search Name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select - from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the offer from the offers list page");
		cmOfferList.offerListName();
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(countryUpdate);
		logger.info("Selecting countries already in offer");
		cmAddOffer.selCountriesinoffer(countryUpdate);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(countryUpdated);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();	
		logger.info("Click on cancel");
		cmAddOffer.acceptCancel();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T413801 Amend Offer - Currency", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendCurrency(String offerName, String currency, String language, String country, String currencyUpdate, String currencyUpdated) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Clicking back to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the Offer Search Name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select - from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the offer from the offers list page");
		cmOfferList.offerListName();
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currencyUpdate);
		logger.info("Selecting currencies in offer");
		cmAddOffer.selCurrinooffer(currencyUpdate);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currencyUpdated);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();	
		logger.info("Click on Ok");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T413800 Amend Offer - End Date", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendEnd(String offerName, String currency, String language, String country) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String today = Helpers.getDateWithOffsetInDays(0);
		String dayAfterTomorrow = Helpers.getDateWithOffsetInDays(2);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Clicking back to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the Offer Search Name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select - from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the offer from the offers list page");
		cmOfferList.offerListName();
		logger.info("Entering end date");
		cmAddOffer.endDate(today);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Modify the end date");
		cmAddOffer.endDate(dayAfterTomorrow);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();		
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T413799 Amend Offer - Expiry Date", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendExpiry(String offerName, String currency, String language, String country) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String today = Helpers.getDateWithOffsetInDays(0);
		String dayAfterTomorrow = Helpers.getDateWithOffsetInDays(2);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Clicking back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select - from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the offer from the offers list page");
		cmOfferList.offerListName();
		logger.info("Entering Entry Date");
		cmAddOffer.entryDate(today);
		logger.info("Modifying the Entry Date");
		cmAddOffer.entryDate(dayAfterTomorrow);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();	
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T413802 Amend Offer - Language", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendLanguage(String offerName, String currency, String language, String country, String languageUpdate, String languageUpdated) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Clicking back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Entering the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select - from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the offer from the offers list page");
		cmOfferList.offerListName();
		logger.info("Selecting languages in offer");
		cmAddOffer.selLanginoffer(language);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(languageUpdate);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(languageUpdated);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();	
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
	@Test(description = "T413804 Amend Offer - Start Date", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
	public void amendStart(String offerName, String currency, String language, String country) {
		String tomorrow = Helpers.getDateWithOffsetInDays(1);
		String oneWeek = Helpers.getDateWithOffsetInDays(2);
		logger.info("Clicking Offers from the Campaign Manager Menu");
		campaignManagerTree.clickOffers();
		logger.info("Clicking the Offers subsection from the Offers option");
		campaignManagerTree.clickOffer();
		logger.info("Clicking Add Offer from the Search Page");
		cmOfferSearch.clickAddOffer();
		logger.info("Entering the Offer Name");
		cmAddOffer.offerName(offerName);
		logger.info("Selecting currencies");
		cmAddOffer.selectCurrencies(currency);
		logger.info("Entering start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Selecting languages");
		cmAddOffer.selectLanguages(language);
		logger.info("Selecting countries");
		cmAddOffer.selectCountries(country);
		logger.info("Adding the new offer");
		cmAddOffer.addNewOffer();
		logger.info("Click back to return to the offer search page");
		cmAddOffer.backOffer();
		logger.info("Enter the offer search name");
		cmOfferSearch.offerSearchName(offerName);
		logger.info("Select - from the currently running dropdown");
		cmOfferSearch.selectCurrentlyRunning("-");
		logger.info("Click on Search offer");
		cmOfferSearch.clickSearchOffer();
		logger.info("Click on the offer from the offers list page");
		cmOfferList.offerListName();
		logger.info("Modify the start date");
		cmAddOffer.startDate(oneWeek);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Modify the start date");
		cmAddOffer.startDate(tomorrow);
		logger.info("Click on modify offer");
		cmAddOffer.modifyOffer();
		logger.info("Click on delete offer");
		cmAddOffer.deleteOffer();	
		logger.info("Click on OK");
		cmAddOffer.acceptAlert();
	}
	
}
