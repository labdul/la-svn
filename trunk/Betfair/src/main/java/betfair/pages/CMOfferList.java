package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;

public class CMOfferList extends Page{

	public CMOfferList(WebDriver webDriver) {
		super(webDriver);
	}
	
	//Locators for Offers List
	
	private final String offerListNameLocator="//td[contains(@class,'string')]/a[contains(@href,'/campaign_manager')]";
	private final String addOfferListLocator="//table/tbody[2]/tr/td/a[1]";	
	private final String backOfferListLocator="//table/tbody[2]/tr/td/a[2]";
	private final String backEmptyLocator="//table/tbody/tr[2]/td/a[2]";
	
	//Elements for Offers List
	
	@FindBy(how = How.XPATH, using = offerListNameLocator)
	private WebElement offerListNameElement;
	
	@FindBy(how = How.XPATH, using = addOfferListLocator)
	private WebElement addOfferListElement;
	
	@FindBy(how = How.XPATH, using = backOfferListLocator)
	private WebElement backOfferListElement;
	
	@FindBy(how = How.XPATH, using = backEmptyLocator)
	private WebElement backEmptyListElement;
	
	/*
	 * Click methods for Offers List
	 */

	public void offerListName() {
		Frames.selectMainFrame(webDriver);
		offerListNameElement.click();
	}
	
	public void clickAddOfferList() {
		Frames.selectMainFrame(webDriver);
		addOfferListElement.click();
	}
	
	public void backOfferList() {
		Frames.selectMainFrame(webDriver);
		backOfferListElement.click();
	}	
	
	public void backEmptyList() {
		Frames.selectMainFrame(webDriver);
		backEmptyListElement.click();
	}	
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getFirstResultText(){
		return offerListNameElement.getText();
	}
	
}

