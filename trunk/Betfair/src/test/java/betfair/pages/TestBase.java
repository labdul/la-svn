package betfair.pages;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.ScreenshotException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import betfair.services.MiscServices;
import betfair.util.Browser;
import betfair.util.Helpers;
import betfair.util.PropertyLoader;
import betfair.webdriver.WebDriverFactory;

import com.openbet.webservices.oxi.Oxi;

/**
 * Base class for all the test classes
 * 
 * @author Victor Vrinceanu
 */
public class TestBase {

	private static final String SCREENSHOT_FOLDER = "target/screenshots/";
	private static final String SCREENSHOT_FORMAT = ".png";

	protected WebDriver webDriver;
	protected String gridHubUrl;
	protected String websiteUrl;
	protected Browser browser;
	protected Logger logger;
	protected MiscServices miscServices;
	protected Oxi oxi;
	protected Oxi oxiLatest;
	protected Oxi oxiLatestSecure;
	protected Oxi oxiFeed;
	protected Oxi oxiSpin;
	protected Oxi dbPublish;
	
	@BeforeClass
	public void init() {
		websiteUrl = PropertyLoader.loadProperty("site.url");
		gridHubUrl = PropertyLoader.loadProperty("grid2.hub");

		browser = new Browser();
		browser.setName(PropertyLoader.loadProperty("browser.name"));
		browser.setVersion(PropertyLoader.loadProperty("browser.version"));
		browser.setPlatform(PropertyLoader.loadProperty("browser.platform"));

		String username = PropertyLoader.loadProperty("user.username");
		String password = PropertyLoader.loadProperty("user.password");
		String proxyusername = PropertyLoader.loadProperty("proxy.username");
		String proxypassword = PropertyLoader.loadProperty("proxy.password");
		
		webDriver = WebDriverFactory.getInstance(gridHubUrl, browser, username,
				password);
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		this.logger = Logger.getLogger(this.getClass().getCanonicalName());
		miscServices = new MiscServices(webDriver);
		logger.debug("Header PO initialising ...");
		oxi = new Oxi("http://sbg.cd2-integration.dev.betfair/oxixmlserver/", "custproxy.openbet.openbet.com","8080",proxyusername, proxypassword);
		oxiLatest = new Oxi("http://sbg.cd2-integration.dev.betfair/oxixmlserverLatest/", "custproxy.openbet.openbet.com","8080",proxyusername, proxypassword);
		oxiLatestSecure = new Oxi("http://sbg.cd2-integration.dev.betfair/oxixmlserverLatestSecure/", "custproxy.openbet.openbet.com","8080",proxyusername, proxypassword);
		oxiFeed = new Oxi("http://sbg.cd2-integration.dev.betfair/ob_feed/", "custproxy.openbet.openbet.com","8080",proxyusername, proxypassword);
		oxiSpin = new Oxi("http://sbg.cd2-integration.dev.betfair/feed_handler/spin/", "custproxy.openbet.openbet.com","8080",proxyusername, proxypassword);
		dbPublish = new Oxi("http://sbg.cd2-integration.dev.betfair/dbPublish/", "custproxy.openbet.openbet.com","8080",proxyusername, proxypassword);
		
	}

	@BeforeMethod
	protected void startTest(Method method) throws Exception {
		String testName = method.getName();
		Helpers.closeSwitched(webDriver);
		logger.info("-- Executing test method : " + testName + " --");
		webDriver.navigate().to(websiteUrl);
		logger.debug("Header PO accessing ...");
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		if (webDriver != null) {
			webDriver.quit();
		}
	}

	public void setScreenshot(ITestResult result) {
		if (!result.isSuccess()) {
			try {
				WebDriver returned = new Augmenter().augment(webDriver);
				if (returned != null) {
					File f = ((TakesScreenshot) returned)
							.getScreenshotAs(OutputType.FILE);
					try {
						File outFile = new File(SCREENSHOT_FOLDER
								+ result.getName() + SCREENSHOT_FORMAT);
						FileUtils.copyFile(f, outFile);
						System.out.println("Screenshot captured - "
								+ outFile.getAbsolutePath());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			} catch (ScreenshotException se) {
				se.printStackTrace();
			}
		}
	}

}
