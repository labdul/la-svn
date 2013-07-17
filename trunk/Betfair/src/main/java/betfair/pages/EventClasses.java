package betfair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import betfair.util.Frames;

public class EventClasses extends Page{

	public EventClasses(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */

	private final String horseRacingLocator = "//a[contains(@href, '/admin?action=ADMIN::CLASS::GoClass&ClassId=101')]";
	private final String englishFootballLocator = "//a[contains(@href, '/admin?action=ADMIN::CLASS::GoClass&ClassId=28')]";
	private final String idLocator = "//table[1]/tbody/tr[2]/td[2]"; 
	private final String typeLocator = "//a[contains(@href,'admin?action=ADMIN::TYPE::GoType&ClassId=101') and contains (text(),'%s')]";


	/*
	 * FIXED ELEMENTS
	 */
	@FindBy(how = How.XPATH, using = horseRacingLocator)
	private WebElement horseRacingElement;

	@FindBy(how = How.XPATH, using = englishFootballLocator)
	private WebElement englishFootballElement;

	@FindBy(how = How.XPATH, using = idLocator)
	private WebElement idElement;

	/*
	 * DYNAMIC ELEMENTS
	 */

	private WebElement typeElement(String text) {
		return webDriver.findElement(By.xpath(String.format(typeLocator, text)));

	}
	/*
	 * METHODS
	 */

	/**
	 * Click Methods
	 */

	public void clickHorseRacingClass() {
		Frames.selectMainAreaFrame(webDriver);
		horseRacingElement.click();
	}

	public void clickEnglishFootballClass() {
		Frames.selectMainAreaFrame(webDriver);
		englishFootballElement.click();
	}

	public void clickType(String text) {
		Frames.selectMainAreaFrame(webDriver);
		typeElement(text).click();
	}

	/**
	 * Get text methods 
	 */

	public String getId() {
		return idElement.getText();
	}

}