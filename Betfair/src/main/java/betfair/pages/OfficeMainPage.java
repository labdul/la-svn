package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//This class contains all Tabs for PINT back-office functions
public class OfficeMainPage extends Page{

	public OfficeMainPage(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * LOCATORS
	 */

	private final String HomeTabLocator = "//*[@id='current']/span";
	private final String AdminTabLocator = "//*[@id='officeMenu']/ul/li[2]/span";
	private final String CMTabLocator = "//*[@id='officeMenu']/ul/li[3]/span";
	private final String MonitorTabLocator = "//*[@id='officeMenu']/ul/li[5]/span";
	private final String TITabLocator = "//*[@id='officeMenu']/ul/li[7]/span";
	private final String LogoutTabLocator = "//*[@id='officeMenu']/ul/li[8]/span";

	/**
	 * ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = HomeTabLocator)
	private WebElement HomeTabElement;

	@FindBy(how = How.XPATH, using = AdminTabLocator)
	private WebElement AdminTabElement;

	@FindBy(how = How.XPATH, using = CMTabLocator)
	private WebElement CMTabElement;

	@FindBy(how = How.XPATH, using = MonitorTabLocator)
	private WebElement MonitorTabElement;

	@FindBy(how = How.XPATH, using = TITabLocator)
	private WebElement TITabElement;

	@FindBy(how = How.XPATH, using = LogoutTabLocator)
	private WebElement LogoutTabElement;

	/**
	 * METHODS
	 */

	public void homeTab() {
		HomeTabElement.click();
	}

	public void adminTab() {
		AdminTabElement.click();
	}

	public void CMTab() {
		CMTabElement.click();
	}

	public void monitorTab() {
		MonitorTabElement.click();
	}
	public void tiTab() {
		TITabElement.click();
	}

	public void logoutTab() {
		LogoutTabElement.click();
	}
}
