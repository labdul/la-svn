package betfair.pages;


import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class SearchCustomerTest extends TestBase{

	Login login;
	Tabs tabs;
	AdminNavigationTree adminNavigation;
	AdminCustomerSearch adminCustomerSearch;

	@BeforeClass
	public void classInit() {
		webDriver.get(websiteUrl);
		login = PageFactory.initElements(webDriver, Login.class);
		tabs = PageFactory.initElements(webDriver, Tabs.class);
		adminNavigation = PageFactory.initElements(webDriver, AdminNavigationTree.class);
		adminCustomerSearch = PageFactory.initElements(webDriver, AdminCustomerSearch.class);
	}

	@BeforeMethod(description = "Logs in before method")
	public void login() {
		login.typeUsername();
		login.typePassword();
		login.clickLogin();
	}



	@Test(description ="C141960 - Search for a Customer with their Username")
	public void searchCustomerUsername() {
		String toCompare = " AUTO_TEST";
		tabs.clickAdmin();
		adminNavigation.clickQueries();
		adminNavigation.clickCustomers();
		adminCustomerSearch.insertUsername("AUTO_TEST");
		adminCustomerSearch.clickFindCustomers();
		Assert.assertEquals(toCompare, adminCustomerSearch.getUsername());

	}

	@Test(description ="C141961	- Search for a Customer with their Account No")
	public void searchCustomerAccount() {
		String toCompare = " 10001";
		tabs.clickAdmin();
		adminNavigation.clickQueries();
		adminNavigation.clickCustomers();
		adminCustomerSearch.insertAccountNumber("10001");
		adminCustomerSearch.clickFindCustomers();
		Assert.assertEquals(toCompare, adminCustomerSearch.getAccountNumber());

	}

	@AfterMethod(description = "Logs out after method")
	public void logout(ITestResult result){
		setScreenshot(result);
		tabs.clickLogout();
	}
}