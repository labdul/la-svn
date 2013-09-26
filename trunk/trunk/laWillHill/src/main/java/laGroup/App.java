package laGroup;

//import java.util.NoSuchElementException;
//import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class App 
{
	@Test
    public void myTest()
    {
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\lateef\\chromedriver.exe");
		WebDriver myD = new ChromeDriver();
       // WebDriver myD = new FirefoxDriver();
		
		myD.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        myD.get("http://54.247.40.95/");
        
       
        
        myD.findElement(By.xpath("//body/div/div/div/div/ul/li[4]/a")).click();
        
        System.out.println(myD.findElement(By.id("versionInfo")).getText());
       
       
      // String myText = myD.findElement(By.id("inputTitle")).getAttribute("name");
       
      // System.out.println(myText);
       
       //myD.findElement(By.id("inputTitle")).click();
   
     

       
   
       
		Select selectTitle = new Select (myD.findElement(By.xpath("//select[contains(@id, 'inputTitle') and contains(@name,'title')]")));
		
			selectTitle.selectByVisibleText("Prof");	
			
			System.out.println(myD.findElement(By.id("inputTitle")).getText());
			
			
			//System.out.println(myD.findElement(By.xpath("/html/body/div[2]/form/div/fieldset/div/div/select/option[5]")).getText());
			
			
			
			
	
			
			myD.findElement(By.xpath("//input[contains(@id, 'input-firstName')]")).sendKeys("Lateefa");
			
			try {
			myD.findElement(By.xpath("//input[contains(@id, 'input-lastName')]")).sendKeys("123Lateefb");
			} catch (NoSuchElementException e) {
				//System.err.println("NoSuchElementException: " + e.getMessage());
				e.printStackTrace();
			} 

			
			
			
			
			if (myD.findElement(By.xpath("//div[2]/form/div/fieldset[2]/div/div/div/span[3]/i")).isDisplayed()) {
				System.out.println("tick Displayed");
			}
			else {
				System.out.println("tick Not Displayed");
			};
			
			//Select selectDOBdate = new Select (myD.findElement(By.xpath("//select[contains(@class, 'ng-valid ng-dirty') and contains(@ng-options,'day for day in dates.days')and contains(@ng-models,'birthDateSelector.days')]")));
			//selectDOBdate.selectByVisibleText("20");
			
			
			
			myD.findElement(By.xpath("//form/div/fieldset[4]/div/div/div/select")).sendKeys("4");
			myD.findElement(By.xpath("//form/div/fieldset[4]/div/div/div[2]/select")).sendKeys("6");
			myD.findElement(By.xpath("//form/div/fieldset[4]/div/div/div[3]/select")).sendKeys("1980");

			
			if (myD.findElement(By.xpath("//form/div/fieldset[3]/div/div/div/span/i")).isDisplayed()) {
				System.out.println("error Displayed");
			}
			else {
				System.out.println("error Not Displayed");
			};
		
			
			
			myD.findElement(By.xpath("//input[contains(@id, 'input-email')]")).sendKeys("dede@gmailss.com");
			myD.findElement(By.xpath("//input[contains(@id, 'input-mobileNumber')]")).sendKeys("123456");
			myD.findElement(By.xpath("//select[contains(@id, 'input-country')]")).sendKeys("United Kingdom");
			myD.findElement(By.xpath("//input[contains(@id, 'input-address1')]")).sendKeys("12");
			myD.findElement(By.xpath("//input[contains(@id, 'input-address2')]")).sendKeys("Palace street");
			myD.findElement(By.xpath("//input[contains(@id, 'input-postCodeOrZip')]")).sendKeys("W4 5xt");
			myD.findElement(By.xpath("//input[contains(@id, 'input-townOrCity')]")).sendKeys("Hunslow");
			myD.findElement(By.xpath("//input[contains(@id, 'input-username')]")).sendKeys("dede1234");
			myD.findElement(By.xpath("//input[contains(@id, 'input-password')]")).sendKeys("dede123456de");
			myD.findElement(By.xpath("//input[contains(@id, 'input-password2')]")).sendKeys("dede123456de");
			
			
			myD.findElement(By.xpath("//select[contains(@id, 'input-currency')]")).sendKeys("EUR");

			System.out.println (myD.findElement(By.xpath("//input[contains(@id, 'input-acceptTerms')]")).isSelected());
					
			myD.findElement(By.xpath("//input[contains(@id, 'input-acceptTerms')]")).click();
					
			System.out.println (myD.findElement(By.xpath("//input[contains(@id, 'input-acceptTerms')]")).isSelected());		
			
			myD.findElement(By.xpath("//input[contains(@class, 'ng-pristine ng-valid') and contains(@type, 'checkbox')]")).click();
			
			myD.findElement(By.xpath("//button[contains(@type,'submit') and contains(@class,'btn btn-green btn-submit')]")).click();
			
			
			
			

        

    	       //String myText = myD.findElement(By.xpath(“//*[@id="inputTitle"]“)).getText();
    	  
    }
	

    public String randomName() {

        String s = "";

           String randomString = "abcdefghijklmno";

           Random randomGenerator = new Random();

           for (int i = 0; i < 5; i++) {
        	   s+= randomGenerator.nextInt(9);

                   //s += randomGenerator.nextInt(9);

                   s += randomString.charAt(randomGenerator.nextInt(randomString.length()));

           }

           System.out.println(s);

           return s;
	}
	
	
}
