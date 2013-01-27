package myGroup;

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
public class App 
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
      
        myD.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys("Administrator");
        myD.findElement(By.xpath("/html/body/form/div/table/tbody/tr[3]/td[2]/input")).sendKeys("1ncharge");
       // Thread.sleep(300);
        myD.findElement(By.xpath("/html/body/form/div/table/tbody/tr[4]/td/input")).click();
        
        
    }
}

