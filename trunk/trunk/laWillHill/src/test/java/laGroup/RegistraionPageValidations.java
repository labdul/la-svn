package laGroup;

import static org.junit.Assert.*;

//import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import org.junit.After;
//import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
//import org.openqa.selenium.NoSuchElementException;

//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.PageFactory;
//import org.openqa.selenium.support.ui.Select;

public class RegistraionPageValidations {
	static RegistrationPage RegistrationPage;
	static Util Util;
	static DepositPage DepositPage;
	static Logger Logger;
	static Helpers Helpers;

	
	
	@BeforeClass
	public static void ClassInit(){
	System.setProperty("webdriver.chrome.driver","C:\\Users\\General\\chromedriver.exe");
	WebDriver driver = new ChromeDriver();
    //WebDriver driver = new FirefoxDriver();
	
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    //driver.get("http://54.247.40.95/");
    driver.get("http://54.217.22.89/");    
    RegistrationPage = PageFactory.initElements(driver, RegistrationPage.class);
    Util = PageFactory.initElements(driver, Util.class);
    DepositPage = PageFactory.initElements(driver, DepositPage.class);
    Logger = PageFactory.initElements(driver, Logger.class);
    Helpers = PageFactory.initElements(driver, Helpers.class);
    
    }

	
	@Before
	public void DoThisstuff() {
    RegistrationPage.clickClassicTemplate();
    RegistrationPage.getversionID();
    }

	
	//@After
	//public void closebrowser(){
//	RegistrationPage.close();
	//}
	


	
	@Test
    public void RegistrationPageHappyPath() throws Exception{
		
		final String firstname = Util.firstname_valid();
		final String lastname = Util.lastname_valid();
		final String emailAddress = Util.emailAddress_valid();
		final String mobileNumber = Util.mobileNumber();
		final String addressline1 = Util.addressLine1_valid();
		final String postcode = Util.postcode_valid();
		final String city = Util.city_valid();
		final String username = Util.username_valid();
		final String password = Util.password_valid();
			
		//Check that title is My Account (InterruptedException )
		//assertEquals("Fail: Wrong Title", "My Account" , RegistrationPage.getTitle());
			
		RegistrationPage.selectatitle(5);
		Logger.LogInfo("Selected title as : " );//todo  method to check the value of the option selected);
		
		
		RegistrationPage.enterfirstname(firstname);
		Logger.LogInfo("Entered firstname as: " + firstname);
		
		if (RegistrationPage.isminimumlenghtfirstnameMsgVisible())
			System.out.println ("pass");
			else
				System.out.println ("fail");
		
		
		
		RegistrationPage.enterlastname(lastname);
		Logger.LogInfo("Entered lastname as: " + lastname);
		Thread.sleep(1000);
		RegistrationPage.enterDOBDay("03");
		RegistrationPage.enterDOBMonth("05");
		RegistrationPage.enterDOBYear("1980");
		
		
		RegistrationPage.enteremailaddress(emailAddress);
		Logger.LogInfo("Entered email address as: " + emailAddress);
		
		
		RegistrationPage.entermobilenumber(mobileNumber);
		Logger.LogInfo("Entered mobile number as: " + mobileNumber);
		
		RegistrationPage.entercountry("United Kingdom");
		
		
		RegistrationPage.enterfirstlineofmanualaddress(addressline1);
		Logger.LogInfo("Entered address line1 as: " + addressline1);
		
		
		RegistrationPage.enterpostcode(postcode);
		Logger.LogInfo("Entered postcode as: " + postcode);
		
		
		RegistrationPage.entercity(city);
		Logger.LogInfo("Entered city as: " + city);
		
		
		RegistrationPage.enterusername(username);
		Logger.LogInfo("Entered username as: " + username);
		
		
		RegistrationPage.enterpassword(password);
		Logger.LogInfo("Entered password as: " + password);
		
		RegistrationPage.enterconfirmpassword(password);
		Logger.LogInfo("Entered confirm password as: " + password);
		
		RegistrationPage.selectsecurityQuestion(5);
		Logger.LogInfo("Selected security question as : " );//tod +  method to check the value of the option selected);
		
		
		RegistrationPage.entersecurityanswer("BobbyBrown");
		Logger.LogInfo("Entered security answer as: BobbyBrown");
	
		RegistrationPage.entercurrency("EUR");
		Logger.LogInfo("Entered currency as: EUR");
		
		RegistrationPage.acceptTermsandConditions();
		Logger.LogInfo("Accepted Terms and Conditions");
				
		RegistrationPage.acceptOffers();
		Logger.LogInfo("Opted in to Offers");
		
		//Take screenshot . Note that thsis will display black blank screen when using chrome. Known bug as seen in 
		//the post http://code.google.com/p/chromedriver/issues/detail?id=294
		Helpers.takeAScreenshot("Test2");
		
		Thread.sleep(1000);
		RegistrationPage.clickSubmitButton();
		Logger.LogInfo("Clicked submit button");

		//assert that deposit page is displayed
		assertEquals("fail - Deposit page is not displayed ", "true", DepositPage.isDepositPagedisplayed());
		
	}
}

	
