package betfair.pages;

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

public class CMFreebet extends Page{
	public CMFreebet(WebDriver webDriver) {
		super(webDriver);
		// TODO Auto-generated constructor stub
	}

	private final String fileUploadLocator="//form[2]/table/tbody/tr[2]/td[2]/input";
	private final String uploadLocator="//form[2]/table/tbody/tr[3]/th/input";
	private final String listLocator="//body/table[1]/tbody/tr[3]/td[2]/a";
	private final String loadLocator="//body/table[2]/tbody/tr[4]/th/input[1]";
	private final String deleteLocator="//body/table[2]/tbody/tr[4]/th/input[2]";
	
	@FindBy(how = How.XPATH, using = fileUploadLocator)
	private WebElement fileUploadElement;

	@FindBy(how = How.XPATH, using = uploadLocator)
	private WebElement uploadElement;

	@FindBy(how = How.XPATH, using = listLocator)
	private WebElement listElement;

	@FindBy(how = How.XPATH, using = loadLocator)
	private WebElement loadElement;
	
	@FindBy(how = How.XPATH, using = deleteLocator)
	private WebElement deleteElement;
	
	public void send(String text){
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
	
	public void load() {
		Frames.selectMainFrame(webDriver);
		loadElement.click();
	}
	
	public void delete() {
		Frames.selectMainFrame(webDriver);
		deleteElement.click();
	}
	
	public void acceptAlert() {
		Alert alertAccept = webDriver.switchTo().alert();  
		alertAccept.accept();
	}
	
	public void writecsv(String path, String tokenID, String username, String ccyCode, String tokenIDdata, String usernameData, String ccyCodeData)  {
		String csv = path;
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(csv));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<String[]> data = new ArrayList<String[]>();
		data.add(new String[] {tokenID,username,ccyCode});
		data.add(new String[] {tokenIDdata,usernameData,ccyCodeData});

		writer.writeAll(data);
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

}

