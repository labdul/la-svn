package betfair.pages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utils.WEActions;

public class Calendar extends Page {

	public Calendar(WebDriver webDriver) {
		  super(webDriver);
		 }

	//Year Locators
	
	@FindBy(xpath="//div[1]/table/tbody/tr[1]/td/select[2]")
	private WebElement eYear;
	
	@FindBy(xpath = "//html/body/div[1]/table/tbody/tr[1]/td/a[4]")
	private WebElement eNextYear;
	
	@FindBy(xpath = "//html/body/div[1]/table/tbody/tr[1]/td/a[3]")
	private WebElement ePrevYear;
	
	//Month Loactors
	
	@FindBy(xpath = "//html/body/div[1]/table/tbody/tr[1]/td/a[2]")
	private WebElement eNextMonth;	 
	
	@FindBy(xpath = "//html/body/div[1]/table/tbody/tr[1]/td/a[1]")
	private WebElement ePrevMonth;	 
	
	@FindBy(xpath = "//html/body/div[1]/table/tbody/tr[1]/td/select[1]")
	private WebElement eMonth; 
	
	private final String TimeLocator="//html/body/div[1]/table/tbody/tr[9]/td/input";
	
	private static String dayLocator = "//html/body/div[1]/table/tbody/tr[%d]/td[%d]/a";
	
	@FindBy(how = How.XPATH, using = TimeLocator)
	private WebElement TimeElement;
	
	private enum calendarMonths {
		January, February, March, April, May, June, July, August, September, October, November, December
	}
	
	/**
	 * @param Set dateTime A date+time in the following format: Year-NoOfMonth-Day hour:minutes "2013-1-15 14:22"
	 * @throws Throwable
	 */
		
	public void setDate(String date) throws Throwable {
		Pattern p = Pattern.compile("(\\d+)-(\\d+)-(\\d+)");
		Matcher m = p.matcher(date);
        if (m.find()) {
    		setDate(Integer.valueOf(m.group(1)), calendarMonths.values()[Integer.valueOf(m.group(2))-1].toString(), Integer.valueOf(m.group(3)));
        }
	}
		
	public void setDate(Integer desiredYear, String desiredMonth, Integer desiredDay) throws Throwable{
		// ----- Month ---------
		while (!eMonth.getText().equals(desiredMonth)) {
			WEActions.Click(webDriver, eNextMonth);
		}
		// ----- Year ---------
		String yearText = eYear.getText();
		Integer actualYearInteger = Integer.valueOf(yearText);
		Integer delta = desiredYear - actualYearInteger;

		for (int i = 0; i < Math.abs(delta); i++) {
			if (delta < 0) {
				//Click Prev Year Arrow
				WEActions.Click(webDriver, ePrevYear);
			} else {
				//Click the Next Year Arrow
				WEActions.Click(webDriver, eNextYear);
			}
		}
		
		// ----- Day ---------
		WebElement eDay = webDriver.findElement(By.xpath(String.format(dayLocator, desiredDay)));
		WEActions.Click(webDriver, eDay);

	}
	
	public void setTime(String time) throws Throwable{
		WEActions.SendKeys(webDriver, TimeElement, time);
	}

	public String getTime() throws Throwable{
		return TimeElement.getAttribute("value");
	}

	public WebElement getTimeElement() throws Throwable {
		return TimeElement;
	}
		
	/**
	 * 
	 * @param desiredYear
	 * @param desiredMonth
	 * @param desiredDay
	 * @param desiredHour
	 * @param desiredMinutes
	 * @throws Throwable 
	 */
	
	public void setDateTime(Integer desiredYear, String desiredMonth, Integer desiredDay, String desiredTime) throws Throwable {

		//Set the date
		setDate(desiredYear, desiredMonth, desiredDay);
		setTime(desiredTime);
	}
	
	@Override
	public boolean isVisible() {
		return false;
	}
	
	

}
