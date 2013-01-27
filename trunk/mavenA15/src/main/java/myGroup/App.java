package myGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class App {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		String eXPath, vURL, vText, vgetText, vgetAttribute, vAttribute; //vBrowser
		
		vAttribute = "value";
		long lTime = 2000;
			
		// TODO Auto-generated method stub
		//System.out.println("Webdriver1");
		//WebDriver myD = new InternetExplorerDriver();
		//WebDriver myD = new FirefoxDriver();
		//WebDriver myD = new HtmlUnitDriver();
		//WebDriver myD = new IphoneDriver();
		//WebDriver myD = new AndroidDriver();
		WebDriver myD = new FirefoxDriver();
		//vBrowser = "IE";
		
		/*vBrowser = "FF";
		if (vBrowser.equals("IE")){
			myD = new InternetExplorerDriver();
		} else {
			myD = new FirefoxDriver();				
		}
		*/
		
		
		
		vURL = "http://www.google.co.uk";
		vText = "Selenium Webdriver";
		//exPath = "//*[@id="gbqfq"]"
		//myD.get("http://www.chasestudentloans.com/");
		myD.navigate().to(vURL);
		
		
		//SENDKEYS
		eXPath = "//*[@id=\"gbqfq\"]";
		myD.findElement(By.xpath(eXPath)).sendKeys(vText);
		Thread.sleep(lTime);
		
		//CLICK search
		eXPath = "//*[@id='gbqfb']";
		myD.findElement(By.xpath(eXPath)).click();
		Thread.sleep(lTime);
		
		
		//GET_TEXT results
		eXPath = "//*[@id=\"resultStats\"]";
		vgetText = myD.findElement(By.xpath(eXPath)).getText();
		System.out.println("the first text searched is :" +vgetText);
		Thread.sleep(lTime);
		
		//GET_TEXT results"Search"
/*		eXPath = "//html/body/div[6]/div[2]/div/div[7]/div/div[4]/div/div[2]/div/ol/li/div/h3/a";
		vgetText = myD.findElement(By.xpath(eXPath)).getText();
		System.out.println("the second text is :" +vgetText);
		Thread.sleep(lTime);*/
		
		//GET_ATTRIBUTES
		eXPath = "//*[@id=\"gbqfq\"]";
		vgetAttribute = myD.findElement(By.xpath(eXPath)).getAttribute(vAttribute);
		System.out.println("the attribute is :" + vgetAttribute);
		Thread.sleep(lTime);
				
		//close browser
		myD.close();

	}

}

/*
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Hello world!
 *
 */
/*public class App 
{
	@Test
    public void myTest()
    {
		//String vURL = "http://pluto.orbis/drajago/admin";
		//String vURL = "www.yahoo.com";
		//String vText = "Selenium Webdriver";
       // System.out.println( "Hello World!" );
        WebDriver myD = new FirefoxDriver();
       //WebDriver myD = new ChromeDriver();
        myD.navigate().to("https://www.google.co.uk/");
      
        myD.findElement(By.xpath("//*[@id=\"lst-ib\"]")).sendKeys("Selenium WebDriver");
        myD.findElement(By.xpath("//*[@id=\"lst-ib\"]")).click();
        
        
       // Thread.sleep(300);
        
        
        
    }
}

*/

