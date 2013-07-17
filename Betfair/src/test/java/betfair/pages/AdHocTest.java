package betfair.pages;

import java.awt.AWTException;

import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import betfair.util.Helpers;

import com.openbet.webservices.oxi.Oxi;

public class AdHocTest extends TestBase{

	Login login;
	Tabs tabs;
	CampaignManagerTree campaignManagerTree;
	CMAdHoc cmAdHoc;
	
	@BeforeClass
	public void classInit() {
		webDriver.get(websiteUrl);
		oxi = PageFactory.initElements(webDriver, Oxi.class);
		login = PageFactory.initElements(webDriver, Login.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		campaignManagerTree = PageFactory.initElements(webDriver, CampaignManagerTree.class);
		cmAdHoc = PageFactory.initElements(webDriver, CMAdHoc.class);
	}
	
	@BeforeMethod
	public void loginAdministrator(){
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
		tabs.clickCM();
	}
	
	@Test(description = "C219096 Adhoc Tokens for Single Customer ")
	public void multiple() throws Exception {
		logger.info("Clicking Offers");
		campaignManagerTree.clickOffers();
		logger.info("Clicking Offers in the dropdown");
		campaignManagerTree.clickAdHoc();
		logger.info("Writing file");
		cmAdHoc.writecsv("..\\adhoc.csv","10008", "GBP","10009", "GBP");
		try {
			logger.info("Inputing the path");
			cmAdHoc.send("C:\\Work\\Betfair\\adhoc.csv");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Clicking the Upload Button");
		cmAdHoc.upload();
		logger.info("Clicking on the file in the list");
		cmAdHoc.list();
		logger.info("Clicking on the Grant Tokens");
		cmAdHoc.grant();
		logger.info("Clicking on the Delete button");
		cmAdHoc.delete();
		logger.info("Generating the session ID");
		String ssoid = Helpers.getSsoidForClient("10008");
		cmAdHoc.acceptAlert();
		logger.info("Sending the reqAcctGetFreeBets request");
		String reqAcctGetFFreebets = oxi.call("reqAcctGetFFreebets").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.respAccountGetFreebets.freebetToken.freebetTokenId");
		logger.info("Filtering the response file");
		logger.info("Sending the reqPlaceBetWithFreeline request");
		oxi.call("reqBetPlaceWithFreeline").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",freebetTokenId:"+reqAcctGetFFreebets);
		String ssoid2 = Helpers.getSsoidForClient("10009");
		cmAdHoc.acceptAlert();
		logger.info("Sending the reqAcctGetFreeBets request");
		String reqAcctGetFFreebets2 = oxi.call("reqAcctGetFFreebets").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid2).withSingleReturn("oxip.response.respAccountGetFreebets.freebetToken.freebetTokenId");
		logger.info("Filtering the response file");
		logger.info("Sending the reqPlaceBetWithFreeline request");
		oxi.call("reqBetPlaceWithFreeline").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",freebetTokenId:"+reqAcctGetFFreebets2);
	}
	
	@Test(description = "C220499 Adhoc Tokens for many customers simultaneously")
	public void single() throws Exception {
		logger.info("Clicking Offers");
		campaignManagerTree.clickOffers();
		logger.info("Clicking Offers in the dropdown");
		campaignManagerTree.clickAdHoc();
		logger.info("Writing file");
		cmAdHoc.writecsv("..\\adhoc.csv", "10008", "GBP","","");
		try {
			logger.info("Inputing the path");
			cmAdHoc.send("C:\\Work\\Betfair\\adhoc.csv");
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Clicking the Upload Button");
		cmAdHoc.upload();
		logger.info("Clicking on the file in the list");
		cmAdHoc.list();
		logger.info("Clicking on the Grant Tokens");
		cmAdHoc.grant();
		logger.info("Clicking on the Delete button");
		cmAdHoc.delete();
		logger.info("Generating the session ID");
		String ssoid = Helpers.getSsoidForClient("10008");
		cmAdHoc.acceptAlert();
		logger.info("Sending the reqAcctGetFreeBets request");
		String reqAcctGetFFreebets = oxi.call("reqAcctGetFFreebets").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid).withSingleReturn("oxip.response.respAccountGetFreebets.freebetToken.freebetTokenId");
		logger.info("Filtering the response file");
		logger.info("Sending the reqPlaceBetWithFreeline request");
		oxi.call("reqBetPlaceWithFreeline").withParams("user:Administrator,pass:1ncharge,ssoid:"+ssoid+",freebetTokenId:"+reqAcctGetFFreebets);
	}
	
	@AfterMethod
	public void logoutAdministrator(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
	
}
