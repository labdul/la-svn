package laGroup;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Helpers extends Page {

	public Helpers(WebDriver driver) {
		super(driver);
	}
	
	
	/*
	 * Note that takesScreenshot method currently displays black blank screen when using chrome
	 * but it works well when using firefox
	 */
	public void takeAScreenshot(String fileName) throws IOException{
		File myImg = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String mFile = "C:\\tmp\\" + fileName + ".jpg";
		FileUtils.copyFile(myImg, new File(mFile));
	
	}
}

//public void takeAScreenshot(String fileName) throws IOException{
//File myImg = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//String mFile = "C:\\tmp\\" + fileName + ".jpg";
//FileUtils.copyFile(myImg, new File(mFile));
//}