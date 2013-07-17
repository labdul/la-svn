package betfair.pages;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;



//This class contains Admin Page functions
public class AdminModifyHorseRacingSelection extends Page{

	public AdminModifyHorseRacingSelection(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String auditTimeLocator = "//table/tbody/tr[%d]/td[2]";
	private final String auditLpLocator = "//table/tbody/tr[%d]/td[21]";
	private final String auditDisporderLocator = "//table/tbody/tr[%d]/td[10]";
	private final String auditChannelLocator = "//table/tbody/tr[%d]/td[38]";
	private final String auditDescLocator = "//table/tbody/tr[%d]/td[6]";
	private final String backButtonLocator = "//input[contains(@type,'button') and contains (@onclick,'return fs') and contains (@value, 'Back')]";

	/*
	 * DYNAMIC ELEMENTS
	 */

	private WebElement auditTimeElement(Integer timeIndex) {
		return webDriver.findElement(By.xpath(String.format(auditTimeLocator, timeIndex)));
	}

	private WebElement auditLpElement(Integer priceIndex) {
		return webDriver.findElement(By.xpath(String.format(auditLpLocator, priceIndex)));
	}

	private WebElement auditDisporderElement(Integer orderIndex) {
		return webDriver.findElement(By.xpath(String.format(auditDisporderLocator, orderIndex)));
	}

	private WebElement auditChannelElement(Integer channelIndex) {
		return webDriver.findElement(By.xpath(String.format(auditChannelLocator, channelIndex)));
	}

	private WebElement auditDescElement(Integer descIndex) {
		return webDriver.findElement(By.xpath(String.format(auditDescLocator, descIndex)));
	}

	/*
	 * STATIC ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = backButtonLocator)
	private WebElement backButtonElement;

	/*
	 * METHODS
	 */

	/**
	 * Get Audit Column Info By index 
	 */

	public String getAuditTime(Integer timeIndex) {
		return auditTimeElement(timeIndex).getText();
	}

	public String getAuditPrice(Integer priceIndex) {
		return auditLpElement(priceIndex).getText();
	}

	public String getAuditDisporder(Integer orderIndex) {
		return auditDisporderElement(orderIndex).getText();
	}

	public String getAuditChannel(Integer channelIndex) {
		return auditChannelElement(channelIndex).getText();
	}

	public String getAuditDesc(Integer descIndex) {
		return auditDescElement(descIndex).getText();
	}

	/**
	 * Gets current date/time
	 */

	public String getNowTime() {
		final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		final String dateString = dateFormat.format(Calendar.getInstance().getTime());
		return dateString;
	}

	/**
	 * Click Methods
	 */
	public void clickBack() {
		backButtonElement.click();
	}

}

