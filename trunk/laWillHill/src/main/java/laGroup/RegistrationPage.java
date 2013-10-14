package laGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
//import static org.junit.Assert.assertThat;
//import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
//import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends Page {
	
	public RegistrationPage(WebDriver driver) {
		super(driver);
	}

	/**
	@Override
	public boolean isVisible() {
		return false;
	}
	**/

		   
		/*
		 * method to verify title of page
		 */
		//private void verifyTitle(String expectedTitle) {
		//get the title of the page
		//String actualTitle = driver.getTitle();
		       
		// verify title
		//assert 
		//}

	
	/*
	 * LOCATORS FOR FIELDS
	 */

		//myD.findElement(By.id("versionInfo")).getText())
	
//	private final String classictemplateLocator = "//a[contains(@href,'#/signup/simple/simple-classic')]";
	private final String classictemplateLocator = "//a[contains(@href,'#/signup/simple-classic')]";
	private final String versioninformationLocator = "versionInfo";
	//private final String versioninformationLocator = "//*[@id='versionInfo']";
	private final String titleLocator = "inputTitle";
	//private final String titleLocator = "//select[contains(@id, 'inputTitle') and contains(@name,'title')]";
	private final String firstnameLocator = "input-firstName";
	//private final String firstnameLocator = "//input[contains(@id, 'input-firstName')]";
	private final String lastnameLocator = "//input[contains(@id, 'input-lastName')]";
	//private final String DOBDayLocator = "//form/div/fieldset[3]/div/div/div/select"; 
	private final String DOBDayLocator = "select[ng-options='day for day in dates.days']";
//	private final String DOBMonthLocator = "//form/div/fieldset[3]/div/div/div[2]/select";
	private final String DOBMonthLocator = "select[ng-options='month.code as month.name for month in dates.months']";
	
	private final String DOBYearLocator = "birthdateselectoryear";
	private final String emailaddressLocator = "//input[contains(@id, 'input-email')]";
	private final String mobilenumberLocator = "//input[contains(@id, 'input-mobileNumber')]";
	private final String countryLocator = "//select[contains(@id, 'input-country')]";
	private final String firstlineManualaddressLocator = "//input[contains(@id, 'input-address1')]";
	private final String secondlineManualaddressLocator = "//input[contains(@id, 'input-address2')]";
	private final String postcodeManualaddressLocator = "//input[contains(@id, 'input-postCodeOrZip')]";
	private final String cityLocator = "//input[contains(@id, 'input-townOrCity')]";
	private final String usernameLocator = "//input[contains(@id, 'input-username')]";
	private final String passwordLocator = "//input[contains(@id, 'input-password')]";
	private final String confirmpasswordLocator = "//input[contains(@id, 'input-password2')]";
	//private final String securityquestionLocator = "//select[contains(@id, 'input-securityQuestion')and contains(@value,'3')]";
	private final String securityquestionLocator = "//select[contains(@id, 'input-securityQuestion')]";
	private final String securityanswerLocator = "//input[contains(@id, 'input-securityAnswer')]";
	private final String currencyLocator = "//select[contains(@id, 'input-currency')]";
	private final String TermsAndConditionsLocator = "//input[contains(@id, 'input-acceptTerms')]";
	//private final String offersLocator = "//input[contains(@class, 'checkbox') and contains(@type, 'checkbox')]";
	private final String offersLocator = "//*[contains(text(),'I want to receive free bets, bonuses & exclusive offer news from William Hill')]";
	private final String submitbuttonLocator = "//button[contains(@type,'submit') and contains(@class,'btn btn-green btn-submit')]";

	/*
	 * LOCATORS FOR GREEN CHECK SIGNS
	 */
	
	public final String firstnameChecksignLocator = "//form/div/fieldset[2]/div/div/div/span[3]/i";
	public final String lastnameChecksignLocator = "//form/div/fieldset[3]/div/div/div/span/i";
	//public final String dateChecksignLocator = "//";
	public final String emailChecksignLocator = "//form/div/fieldset[5]/div/div/div/span[2]/i";
	public final String mobilenumberChecksignLocator = "//form/div/fieldset[6]/div/div/div/span[2]/i";
	//public final String countryChecksignLocator = "//";
	public final String ManualAddressFirstLineChecksignLocator = "//form/div/fieldset[8]/div/div[2]/div/span[2]/i";
	//public final String ManualAddressSecondLineChecksignLocator = "//";
	public final String postcodeorZIPChecksignLocator = "//form/div/fieldset[9]/div/div/div/span[2]/i";
	public final String TownChecksignLocator = "//form/div/fieldset[10]/div/div/div/span[2]/i";
	public final String RegionorStateChecksignLocator = "//form/div/fieldset[11]/div/div/div/span[2]/i";
	//public final String usernameChecksignLocator = "";
	public final String passwordChecksignLocator = "//form/div/fieldset[13]/div/div/div/span[2]/i";
	public final String confimPasswordChecksignLocator = "//form/div/fieldset[14]/div/div/div/span[2]/i";
	//public final String securityQuestionChecksignLocator = "//";
	public final String securityAnswerChecksignLocator = "//form/div/fieldset[15]/div[2]/div/div/span[2]/i";
	

	/*
	 * LOCATORS FOR ERRORs
	 */
	
	public final String firstnameErrorLocator = "/form/div/fieldset[2]/div/div/div/span/i";
	
	public final String youmustenterfisrtnameLocator = "/form/div/fieldset[2]/div/div/div/span/span[text()='You must enter a First name']";
	public final String firstnameminimumlengthLocator = "//form/div/fieldset[2]/div/div/div/span/span[text()='Not enough characters entered, the minimum is 2 characters']";
	public final String firstnamemaximumlengthLocator = "//form/div/fieldset[2]/div/div/div/span/span[text()='Too many characters entered, the maximum is 60 characters']";
	public final String firstnameinvalidcharacterLocator = "//form/div/fieldset[2]/div/div/div/span/span[text()='You have entered an invalid character only letters . /' // - accepted']";
		
	
	public final String lastnameErrorLocator = "//form/div/fieldset[3]/div/div/div/span/i";
	//public final String dateErrorLocator = "//";
	public final String emailErrorLocator = "//form/div/fieldset[5]/div/div/div/span[2]/i";
	public final String mobilenumberErrorLocator = "//form/div/fieldset[6]/div/div/div/span[2]/i";
	//public final String countryErrorLocator = "//";
	public final String ManualAddressFirstLineErrorLocator = "//form/div/fieldset[8]/div/div[2]/div/span[2]/i";
	//public final String ManualAddressSecondLineErrorLocator = "//";
	public final String postcodeorZIPErrorLocator = "//form/div/fieldset[9]/div/div/div/span[2]/i";
	public final String TownErrorLocator = "//form/div/fieldset[10]/div/div/div/span[2]/i";
	public final String RegionorStateErrorLocator = "//form/div/fieldset[11]/div/div/div/span[2]/i";
	//public final String usernameChecksignLocator = "";
	public final String passwordErrorLocator = "//form/div/fieldset[13]/div/div/div/span[2]/i";
	public final String confimPasswordErrorLocator = "//form/div/fieldset[14]/div/div/div/span[2]/i";
	//public final String securityQuestionErrorLocator = "//";
	public final String securityAnswerErrorLocator = "//form/div/fieldset[15]/div[2]/div/div/span[2]/i";
	

	
	/*
	 * FIXED FIELD ELEMENTS
	 */
	
	@FindBy(how = How.XPATH, using = classictemplateLocator)
	private WebElement classictemplatefieldElement;	
	
	@FindBy(how = How.ID, using = titleLocator)
	private WebElement titleDropdownfieldElement;	

	@FindBy(how = How.ID, using = firstnameLocator)
	private WebElement firstnamefieldElement;	
	
	@FindBy(how = How.XPATH, using = lastnameLocator)
	private WebElement lastnamefieldElement;	
	
	@FindBy(how = How.CSS, using = DOBDayLocator)
	private WebElement DOBDayDropdownfieldElement;	
	
	@FindBy(how = How.CSS, using = DOBMonthLocator)
	private WebElement DOBMonthDropdownfieldElement;	
	
	@FindBy(how = How.NAME, using = DOBYearLocator)
	private WebElement DOBYearDropdownfieldElement;	
	
	@FindBy(how = How.XPATH, using = emailaddressLocator)
	private WebElement eamiladdressfieldElement;	
	
	@FindBy(how = How.XPATH, using = mobilenumberLocator)
	private WebElement mobilenumberfieldElement;	
	
	@FindBy(how = How.XPATH, using = countryLocator)
	private WebElement countryfieldDropdownElement;	
	
	@FindBy(how = How.XPATH, using = firstlineManualaddressLocator)
	private WebElement firstlineManualaddressfieldElement;	
	
	@FindBy(how = How.XPATH, using = secondlineManualaddressLocator)
	private WebElement secondlineManualaddressfieldElement;	
	
	@FindBy(how = How.XPATH, using = postcodeManualaddressLocator)
	private WebElement postcodefieldElement;	
	
	@FindBy(how = How.XPATH, using = cityLocator)
	private WebElement cityfieldElement;	

	@FindBy(how = How.XPATH, using = usernameLocator)
	private WebElement usernamefieldElement;	
	
	@FindBy(how = How.XPATH, using = passwordLocator)
	private WebElement passwordfieldElement;	
	
	@FindBy(how = How.XPATH, using = confirmpasswordLocator)
	private WebElement confirmpasswordfieldElement;	

	@FindBy(how = How.XPATH, using = securityquestionLocator )
	private WebElement securityquestionfieldElement;	
	
	@FindBy(how = How.XPATH, using = securityanswerLocator )
	private WebElement securityanswerfieldElement;	
	
	@FindBy(how = How.XPATH, using = currencyLocator)
	private WebElement currencyDropdownfieldElement;	
	
	@FindBy(how = How.XPATH, using = TermsAndConditionsLocator)
	private WebElement TermsAndConditionscheckboxfieldElement;	
	
	@FindBy(how = How.XPATH, using = offersLocator)
	private WebElement offerscheckboxfieldElement;
	
	@FindBy(how = How.XPATH, using = submitbuttonLocator)
	private WebElement submitbuttonfieldElement;
	
	
	
	/*
	 * FIXED GREEN_CHECK_SIGN ELEMENTS
	 */
	
	@FindBy(how = How.XPATH, using = firstnameChecksignLocator)
	private WebElement firstnameChecksignElement;	
	
	@FindBy(how = How.XPATH, using = lastnameChecksignLocator)
	private WebElement lastnameChecksignElement;	
	
	@FindBy(how = How.XPATH, using = emailChecksignLocator)
	private WebElement emailChecksignElement;	
	
	@FindBy(how = How.XPATH, using = mobilenumberChecksignLocator)
	private WebElement mobilenumberChecksignElement;	
	
	@FindBy(how = How.XPATH, using = ManualAddressFirstLineChecksignLocator)
	private WebElement ManualAddressFirstLineChecksignElement;	
	
	@FindBy(how = How.XPATH, using = postcodeorZIPChecksignLocator)
	private WebElement postcodeorZIPChecksignElement;	
	
	@FindBy(how = How.XPATH, using = TownChecksignLocator)
	private WebElement TownChecksignElement;	
	
	@FindBy(how = How.XPATH, using = RegionorStateChecksignLocator)
	private WebElement RegionorStateChecksignElement;	
	
	@FindBy(how = How.XPATH, using = passwordChecksignLocator)
	private WebElement passwordChecksignElement;	
	
	@FindBy(how = How.XPATH, using = confimPasswordChecksignLocator)
	private WebElement confimPasswordChecksignElement;	
	
	@FindBy(how = How.XPATH, using = securityAnswerChecksignLocator)
	private WebElement securityAnswerChecksignElement;	
	

	
	/*
	 * FIXED ERROR MSG ELEMENTS
	 */
	

	@FindBy(how = How.XPATH, using = firstnameminimumlengthLocator)
	private WebElement firstnameminimumlengthErrorElement;
	
	@FindBy(how = How.XPATH, using = firstnameErrorLocator)
	private WebElement firstnameErrorElement;	
	
	@FindBy(how = How.XPATH, using = lastnameErrorLocator)
	private WebElement lastnameErrorElement;	
	
	@FindBy(how = How.XPATH, using = emailErrorLocator)
	private WebElement emailErrorElement;	
	
	@FindBy(how = How.XPATH, using = mobilenumberErrorLocator)
	private WebElement mobilenumberErrorElement;	
	
	@FindBy(how = How.XPATH, using = ManualAddressFirstLineErrorLocator)
	private WebElement ManualAddressFirstLineErrorElement;	
	
	@FindBy(how = How.XPATH, using = postcodeorZIPErrorLocator)
	private WebElement postcodeorZIPErrorElement;	
	
	@FindBy(how = How.XPATH, using = TownErrorLocator)
	private WebElement TownErrorElement;	
	
	@FindBy(how = How.XPATH, using = RegionorStateErrorLocator)
	private WebElement RegionorStateErrorElement;	
	
	@FindBy(how = How.XPATH, using = passwordErrorLocator)
	private WebElement passwordErrorElement;	
	
	@FindBy(how = How.XPATH, using = confimPasswordErrorLocator)
	private WebElement confimPasswordErrorElement;	
	
	@FindBy(how = How.XPATH, using = securityAnswerErrorLocator)
	private WebElement securityAnswerErrorElement;	
	
		
	/*
	 * DYNAMIC ELEMENTS
	 */
	@FindBy(how = How.ID, using = versioninformationLocator)
	private WebElement versioninformationElement;	
	
	
	
	

	
	/*
	 * METHODS TO CLICK/SELECT
	 */

	/*
	 * Method to click the Template
	 */
	public void clickClassicTemplate(){
		classictemplatefieldElement.click();
		}
		
	
	/*
	 * Method to select title
	 * Note this type of method can be used to for selecting countries, security question and currency too.
	 */
	public void selectatitle(int num){
		Select sList = new Select (titleDropdownfieldElement);
		sList.selectByIndex(num);
		}
	
		
	/*
	 * Method to select security question
	 */
	public void selectsecurityQuestion(int num){
		Select sList = new Select (securityquestionfieldElement);
		sList.selectByIndex(num);
		}
	

	/*
	 * Method to Check tick-box for Terms and Conditions if not already checked
	 */
	public void acceptTermsandConditions(){
	if (driver.findElement(By.xpath(TermsAndConditionsLocator)).isSelected()) {
		System.out.println("Terms Already Ticked. No action to be done");
	}
	else {
		TermsAndConditionscheckboxfieldElement.click();
		}
	}
	

	/*
	 * Method to Check the tick-box for Offers if not already checked
	 */
	public void acceptOffers(){
	if (driver.findElement(By.xpath(offersLocator)).isSelected()) {
		System.out.println("Offers Already Ticked. No action to be done");
	}
	else {
		offerscheckboxfieldElement.click();
		}
	}
	
	/*
	 * Method to click submit button
	 */
	public void clickSubmitButton(){
		submitbuttonfieldElement.click();
		}
	
		
	/*
	 * METHODS TO ENTER DATA
	 */
	public void enterfirstname(String text){
		firstnamefieldElement.sendKeys(Keys.CLEAR);
		firstnamefieldElement.sendKeys(text);
	}
	
	public void enterlastname(String text){
		lastnamefieldElement.sendKeys(Keys.CLEAR);
		lastnamefieldElement.sendKeys(text);
	}
	
	public void enterDOBDay(String num){
		DOBDayDropdownfieldElement.sendKeys(num);
	}
	
	public void enterDOBMonth(String num){
		DOBMonthDropdownfieldElement.sendKeys(num);
	}

	public void enterDOBYear(String num){
		DOBYearDropdownfieldElement.sendKeys(num);
	}
	
	public void enteremailaddress(String text){
		eamiladdressfieldElement.sendKeys(Keys.CLEAR);
		eamiladdressfieldElement.sendKeys(text);
	}
	
	public void entermobilenumber(String text){
		mobilenumberfieldElement.sendKeys(Keys.CLEAR);
		mobilenumberfieldElement.sendKeys(text);
	}
	
	//Note that the method to select title can also be used for selecting country here
	public void entercountry(String text){
		countryfieldDropdownElement.sendKeys(text);
	}
	
	public void enterfirstlineofmanualaddress(String text){
		firstlineManualaddressfieldElement.sendKeys(Keys.CLEAR);
		firstlineManualaddressfieldElement.sendKeys(text);
	}
	
	public void enterpostcode(String text){
		postcodefieldElement.sendKeys(Keys.CLEAR);
		postcodefieldElement.sendKeys(text);
	}
	
	public void entercity(String text){
		cityfieldElement.sendKeys(Keys.CLEAR);
		cityfieldElement.sendKeys(text);
	}
	
	public void enterusername(String text){
		usernamefieldElement.sendKeys(Keys.CLEAR);
		usernamefieldElement.sendKeys(text);
	}
	
	public void enterpassword(String text){
		passwordfieldElement.sendKeys(Keys.CLEAR);
		passwordfieldElement.sendKeys(text);
	}
	
	public void enterconfirmpassword(String text){
		confirmpasswordfieldElement.sendKeys(Keys.CLEAR);
		confirmpasswordfieldElement.sendKeys(text);
	}
	
	public void entercurrency(String text){
		currencyDropdownfieldElement.sendKeys(text);
	}
	
	public void entersecurityanswer(String text){
		securityanswerfieldElement.sendKeys(text);
	}	
	
	
	/*
	 * METHODS TO CHECK THE GREEN_CHECK_SIGN AFTER EVERY CORRECT FIELD ENTERED
	 */

	public boolean isCheckDisplayedForFirstnameentry(){
		if (firstnameChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for firstname entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for firstname entry");
			return false;
		}	
	}	
	
	public boolean isCheckDisplayedForlastnameentry(){
		if (lastnameChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for lastname entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for lastname entry");
			return false;
		}
	}	

	public boolean isCheckDisplayedForEmailentry(){
		if (emailChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for Email entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Email entry");
			return false;
		}
	}	
	
	public boolean isCheckDisplayedForMobileNumberentry(){
		if (mobilenumberChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for Mobile Number entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Mobile Number entry");
			return false;
		}
	}	

	public boolean isCheckDisplayedForManualAddressFirstLineentry(){
		if (ManualAddressFirstLineChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for  Manual Address line1 entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Manual Address line1 entry");
			return false;
		}
	}	
	
	public boolean isCheckDisplayedForPostcodeentry(){
		if (postcodeorZIPChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for Postcode entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Postcode entry");
			return false;
		}
	}	
	
	public boolean isCheckDisplayedForTownentry(){
		if (TownChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for Town entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Town entry");
			return false;
		}
	}		

	public boolean isCheckDisplayedForRegionentry(){
		if (RegionorStateChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for Region entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Region entry");
			return false;
		}
	}		
	
	public boolean isCheckDisplayedForpasswordentry(){
		if (passwordChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for Password entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Password entry");
			return false;
		}
	}	

	public boolean isCheckDisplayedForConfirmpasswordentry(){
		if (confimPasswordChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for Confirm Password entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for Password entry");
			return false;
		}
	}	
	
	
	public boolean isCheckDisplayedForSecurityAnswerentry(){
		if (securityAnswerChecksignElement.isDisplayed()) {
			System.out.println("GreenCheck is Displayed for security answer entry");
			return true;
		}
		else {
			System.out.println("GreenCheck IS NOT Displayed for security answer entry");
			return false;
		}
	}	
	
	
	
	public boolean isminimumlenghtfirstnameMsgVisible(){
		String minlengthErrormsg;
		minlengthErrormsg = firstnameminimumlengthErrorElement.getText();
		System.out.println(minlengthErrormsg);
		if(minlengthErrormsg.equals("Not enough characters entered, the minimum is 2 characters"))
			return true;
		else
			return false;
	}
		
	
	
	/*
	 * METHODS TO GET TEXTS
	 */
	public void getversionID(){
		  System.out.println(versioninformationElement.getText());
	}
	
	

}
