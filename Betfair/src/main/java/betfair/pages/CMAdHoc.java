package betfair.pages;

import java.awt.AWTException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import au.com.bytecode.opencsv.CSVWriter;
import betfair.util.Frames;

public class CMAdHoc extends Page{

	public CMAdHoc(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	private final String fileUploadLocator="//form[2]/table/tbody/tr[1]/td[2]/input[1]";
	private final String uploadLocator="//form[2]/table/tbody/tr[2]/td/input";
	private final String listLocator="//body/table/tbody/tr/td[2]/a";
	private final String grantLocator="//body/form/input[1]";
	private final String deleteLocator="//body/table/tbody/tr/td[3]/a";
	
	@FindBy(how = How.XPATH, using = fileUploadLocator)
	private WebElement fileUploadElement;

	@FindBy(how = How.XPATH, using = uploadLocator)
	private WebElement uploadElement;
	
	@FindBy(how = How.XPATH, using = listLocator)
	private WebElement listElement;
	
	@FindBy(how = How.XPATH, using = grantLocator)
	private WebElement grantElement;
	
	@FindBy(how = How.XPATH, using = deleteLocator)
	private WebElement deleteElement;
	
	public void send (String text) throws AWTException{
		 Frames.selectMainFrame(webDriver);
		 fileUploadElement.sendKeys(text);
		 }
	
	public void upload() {
		Frames.selectMainFrame(webDriver);
		uploadElement.click();
	}
	
	public void list() {
		Frames.selectMainFrame(webDriver);
		listElement.click();
	}
	
	public void grant() {
		Frames.selectMainFrame(webDriver);
		grantElement.click();
	}
	
	public void delete() {
		Frames.selectMainFrame(webDriver);
		deleteElement.click();
	}
	
	public void writecsv(String path, String userData, String ccyData, String userData2, String ccyData2)  {
		String csv = path;
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(csv));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {"USERNAME", "CCY CODE", "AMOUNT", "ABSOLUTE EXPIRY", "RELATIVE EXPIRY", "REDEMPTION ID"});
		data.add(new String[] {userData, ccyData, "10", "", "2 0:00", "1"});
		data.add(new String[] {userData, ccyData, "10", "", "2 0:00", "1"});
		writer.writeAll(data);
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void acceptAlert() {
		Alert alertAccept = webDriver.switchTo().alert();  
		alertAccept.accept();
	}
	
	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
