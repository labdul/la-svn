package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;

//This class contains all Tabs for PINT back-office functions
public class Tabs extends Page{

	public Tabs(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */

	private final String homeTabLocator = "//*[@id='current']/span";
	private final String adminTabLocator = "//span[contains(@onclick,'officeMenuSelect(this') and contains(@onclick,'admin')]";
	private final String cmTabLocator = "//*[@id='officeMenu']/ul/li[3]/span";
	private final String monitorTabLocator = "//*[@id='officeMenu']/ul/li[5]/span";
	private final String tiTabLocator = "//*[@id='officeMenu']/ul/li[7]/span";
	private final String logoutTabLocator = "//*[@id='officeMenu']/ul/li[8]/span";

	/*
	 * ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = homeTabLocator)
	private WebElement homeTabElement;

	@FindBy(how = How.XPATH, using = adminTabLocator)
	private WebElement adminTabElement;
		
	@FindBy(how = How.XPATH, using = cmTabLocator)
	private WebElement cmTabElement;

	@FindBy(how = How.XPATH, using = monitorTabLocator)
	private WebElement monitorTabElement;

	@FindBy(how = How.XPATH, using = tiTabLocator)
	private WebElement tiTabElement;

	@FindBy(how = How.XPATH, using = logoutTabLocator)
	private WebElement logoutTabElement;

	/*
	 * METHODS
	 */
	/**
	 * Clicks Home Tab
	 */
	public void clickHome() {
		homeTabElement.click();
	}
	/**
	 * Clicks Admin Tab
	 */
	public void clickAdmin() {
		Frames.selectTabsFrame(webDriver);
		adminTabElement.click();
	}
	/**
	 * Clicks Campaign Manager Tab
	 */
	public void clickCM() {
		Frames.selectTabsFrame(webDriver);
		cmTabElement.click();
	}
	/**
	 * Clicks Monitor Tab
	 */
	public void clickMonitor() {
		Frames.selectTabsFrame(webDriver);
		monitorTabElement.click();
	}
	/**
	 * Clicks TI Tab
	 */
	public void clickTI() {
		Frames.selectTabsFrame(webDriver);
		tiTabElement.click();
	}
	/**
	 * Click Logout
	 */
	public void clickLogout() {
		Frames.selectTabsFrame(webDriver);
		logoutTabElement.click();
	}
}
