package betfair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import betfair.util.Frames;

public class CMAddRedemption extends Page{

	public CMAddRedemption(WebDriver webDriver) {
		super(webDriver);
	}

	private final String addAnyLocator="//form/table/tbody[1]/tr[1]/td[5]/a";
	private final String backRedemptionLocator="//form/table/tbody[2]/tr[2]/td/a";
	
	@FindBy(how = How.XPATH, using = addAnyLocator)
	private WebElement addAnyElement;
	
	@FindBy(how = How.XPATH, using = backRedemptionLocator)
	private WebElement backRedemptionElement;
	
	/**
	 * Click methods for Add Redemption Page
	 */
	
	public void addAny() {
		Frames.selectMainFrame(webDriver);
		addAnyElement.click();
	}
	
	public void backRedemption() {
		Frames.selectMainFrame(webDriver);
		backRedemptionElement.click();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
