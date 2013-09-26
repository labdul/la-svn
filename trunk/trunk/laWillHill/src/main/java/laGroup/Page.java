package laGroup;



import org.openqa.selenium.WebDriver;


public class Page {
	
	protected WebDriver driver;

	
	public Page(WebDriver driver) {
		this.driver = driver;
	}

	/**
	@Override
	public boolean isVisible() {
		return false;
	}
	**/
	
	/*
	 * method to launch page
	 */
	public void open(String url) {
		driver.get(url);
		}

	/*
	 * method to close URL
	 */
		public void close() {
		driver.quit();
		}

		/*
		 * method to get title of page
		 */
		public String getTitle() {
		return driver.getTitle();
		}
		
		
		/*
		 * Locators
		 */
}
