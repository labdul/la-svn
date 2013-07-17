package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CMAddLevel extends Page{
	
	public CMAddLevel(WebDriver webDriver) {
		super(webDriver);
	}

	/**
	 * Locators for Add Level
	 */
	
	private final String horseRaceLocator = "//table/tbody/tr[50]/td[1]/input";
	
	/**
	 * Elements for Add Level
	 */
	
	@FindBy(how = How.XPATH, using = horseRaceLocator)
	private WebElement horseRaceElement;
	
	/**
	 * Click methods for Add Level buttons page
	 * Add new Offer Max Tokens field
	 */	
	
	public void horseRacing() {
		horseRaceElement.click();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}
