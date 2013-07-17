package betfair.util;

import org.openqa.selenium.WebDriver;

public class Frames {

	public static void selectTabsFrame(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame("officeMenu");
	}

	public static void selectMenuFrame(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame("officePane");
		webDriver.switchTo().frame("TopBar");
	}
	
	public static void selectMainFrame(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame("officePane");
		webDriver.switchTo().frame("main");
	}
	public static void selectCMenuFrame(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame("officePane");
		webDriver.switchTo().frame(0);
	}
	public static void selectMainAreaFrame(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
		webDriver.switchTo().frame("officePane");
		webDriver.switchTo().frame("MainArea");
		
	}
	public static void selectDefault(WebDriver webDriver) {
		webDriver.switchTo().defaultContent();
	}
}
