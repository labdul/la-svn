package laGroup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DepositPage extends Page{
	
	public DepositPage(WebDriver driver){
		super (driver);
	}
	
	/*
	 * Locators for Deposit page
	 */
private final String depositpageheaderLocator = "//*[contains(Text(), 'Deposit money']";



/*
 * WebElements
 */
@FindBy(how = How.XPATH, using = depositpageheaderLocator)
private WebElement depositpageheaderElement;	




/*
 * Methods for deposit page
 */
public boolean isDepositPagedisplayed(){
	if (depositpageheaderElement.isDisplayed()) {
		System.out.println("Deposit page is displayed");
		return true;
	}
	else {
		System.out.println("Deposit page is not displayed");
		return false;
	}	
}	


}
