package betfair.pages;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;



//This class contains Admin Page functions
public class AdminCustomerSearch extends Page{

	public AdminCustomerSearch(WebDriver webDriver) {
		super(webDriver);
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	/*
	 * LOCATORS
	 */
	private final String usernameFieldLocator = "//table[1]/tbody/tr[2]/td[2]/input[1]";
	private final String accountNumberFieldLocator = "//table[1]/tbody/tr[14]/td[2]/input[1]";
	private final String findCustomerButtonLocator = "//table[1]/tbody/tr[23]/th/input[1]";
	private final String usernameAssertLocator = "//table[1]/tbody/tr[2]/td[1]/table/tbody/tr[2]/td[2]";
	private final String accountAssertLocator = "//table[1]/tbody/tr[2]/td[1]/table/tbody/tr[3]/td[2]";
	/*
	 * ELEMENTS
	 */
	@FindBy(how = How.XPATH, using = usernameFieldLocator)
	private WebElement usernameFieldElement;
	
	@FindBy(how = How.XPATH, using = accountNumberFieldLocator)
	private WebElement accountNumberFieldElement;
	
	@FindBy(how = How.XPATH, using = findCustomerButtonLocator)
	private WebElement findCustomerButtonElement;
	
	@FindBy(how = How.XPATH, using = usernameAssertLocator)
	private WebElement usernameAssertElement;
	
	@FindBy(how = How.XPATH, using = accountAssertLocator)
	private WebElement accountAssertElement;
	/*
	 * METHODS
	 */

	/**
	 * Inserts Username
	 */
	public void insertUsername(String text) {
		Frames.selectMainAreaFrame(webDriver);
		usernameFieldElement.sendKeys(text);
	}
	/**
	 * Inserts Account number
	 */
	public void insertAccountNumber(String text) {
		Frames.selectMainAreaFrame(webDriver);
		accountNumberFieldElement.sendKeys(text);
	}
	/**
	 * Clicks Find customer button
	 */
	public void clickFindCustomers() {
		Frames.selectMainAreaFrame(webDriver);
		findCustomerButtonElement.click();
	}
	/**
	 * Gets Username text
	 * @return 
	 */
	public String getUsername() {
		Frames.selectMainAreaFrame(webDriver);
		return usernameAssertElement.getText();
	}
	/**
	 * Gets Account Number
	 * @return
	 */
	public String getAccountNumber() {
		Frames.selectMainAreaFrame(webDriver);
	return accountAssertElement.getText();
	}
	
	/**
	 * Generates a random account number 
	 * @return
	 */
	public String randomAccount() {
		Random randomGenerator = new Random();
		String accountNo = "";
		String randomStringNo = "123456789";
		for (int i = 0; i < 5; i++) {
		accountNo += randomStringNo.charAt(randomGenerator.nextInt(randomStringNo.length()));
		}
		System.out.println(accountNo);
		return accountNo;
		
	}
	
}