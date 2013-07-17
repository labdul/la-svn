package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.PropertyLoader;

//This class contains Monitor functions
public class Login extends Page{

	public Login(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */

	private final String usernameFieldLocator = "//*[@id='loginUsername']";
	private final String passwordFieldLocator = "//*[@id='loginTable']/tbody/tr[2]/td[2]/input";
	private final String loginButtonLocator = "//*[@id='loginTable']/tbody/tr[3]/td/input";
	
	/*
	 * ELEMENTS
	 */

	@FindBy(how = How.XPATH, using = usernameFieldLocator)
	private WebElement usernameFieldElement;

	@FindBy(how = How.XPATH, using = passwordFieldLocator)
	private WebElement passwordFieldElement;

	@FindBy(how = How.XPATH, using = loginButtonLocator)
	private WebElement loginButtonElement;

	/*
	 * METHODS
	 */
	
	/**
	 * Types in the Username
	 */
	public void typeUsername() {
		usernameFieldElement.sendKeys(PropertyLoader.loadProperty("user.username"));
	}
	/**
	 * Types in the Password
	 */
	public void typePassword() {
		passwordFieldElement.sendKeys(PropertyLoader.loadProperty("user.password"));
	}
	/**
	 * Clicks Login Button
	 */
	public void clickLogin() {
		loginButtonElement.click();
	}
	
}