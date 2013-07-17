package betfair.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyAccountPage extends Page {

    /*
     * Locators
     */
    private final String exampleFieldLocator = "//form[@id='user-login']/div/div/input";

    /*
     * Dynamic Locators 
     */
    private final String exampleFieldDynamicLocator = "//form[@id='user-login']/div/div[%d]";

    /*
     * Elements
     */
    @FindBy(how = How.XPATH, using = exampleFieldLocator)
    private WebElement exampleFieldElement;
    
    /*
     * Dynamic Elements
     */
    private WebElement exampleDynamicField(Integer day){
    	return webDriver.findElement(By.xpath(String.format(exampleFieldDynamicLocator,day)));
    }

    /*
     * Methods
     */
    public MyAccountPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    public boolean isVisible() {
    	//Should implement a page-object specific  
        return true;
    }

    /**
     * This is an example method that uses a dynamic element 
     */
    public void exampleMethodUsingDynamicElement() {
    	exampleDynamicField(5).click();
    }
    
    /**
     * This is an example method that uses a fixed element
     */
    public void exampleMethodUsingFixedElement() {
    	exampleFieldElement.click();
    }
    
}
