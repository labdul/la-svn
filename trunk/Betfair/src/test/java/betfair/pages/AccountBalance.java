package betfair.pages;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import betfair.pages.MyAccountPage;
import betfair.util.DataProviders;


@Test
public class AccountBalance extends TestBase {

    MyAccountPage myAccount;

    @BeforeClass
    public void initPage() {
    	logger.info("Accessing website: " + websiteUrl);
        webDriver.get(websiteUrl);
        
        myAccount = PageFactory.initElements(webDriver, MyAccountPage.class);

        webDriver.navigate().to(websiteUrl);
    }

    @Test(description = "This is an example test description", dataProviderClass = DataProviders.class, dataProvider = "CSVDriver")
    public void myAccountBalance(String username, String password) throws InterruptedException {
        logger.info("This is an example info log line ...");
        myAccount.exampleMethodUsingDynamicElement();
        logger.debug("This is an example debug line ...");
        myAccount.exampleMethodUsingFixedElement();
        Assert.assertEquals(1, 1);
    }

}
