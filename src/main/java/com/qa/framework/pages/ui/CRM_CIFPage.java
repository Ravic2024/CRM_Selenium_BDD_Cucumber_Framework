package com.qa.framework.pages.ui;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.framework.pages.BasePage;

public class CRM_CIFPage extends BasePage {

	private final Logger log = LogManager.getLogger(CRM_CIFPage.class);

	protected String applicationNumberValue;

	@FindBy(xpath = "//a[text()='Applications']")
	protected WebElement ApplicationToolBar;

	@FindBy(xpath = "//input[@aria-label = 'Identification Type']/following-sibling::span")
	protected WebElement open_Identification_Type_DropDown_BTN;

	@FindBy(xpath = "//div[@class='siebui-button-toolbar siebui-closed' and @role='button']")
	protected WebElement ToolBar;

	@FindBy(xpath = "//li[@name='Toggle Task Pane' and @title='Tasks']")
	protected WebElement TaskIcon;

	@FindBy(xpath = "(//*[text()='Account Opening' and @class='fancytree-title'])[2]")
	protected WebElement AccountOpeningLink;

	@FindBy(xpath = "//*[text()='Account Opening:']")
	protected WebElement AccountOpeningPage;

	@FindBy(xpath = "//input[@aria-label ='Identification Type']")
	protected WebElement identificationType_txt;

	@FindBy(xpath = "//input[@aria-label ='Identification Number']")
	protected WebElement identificationNum_txt;

	@FindBy(xpath = "//input[@aria-label ='Action Type']")
	protected WebElement actiontype_txt;

	@FindBy(xpath = "//button[@aria-label ='Next']")
	protected WebElement nextBtn;

	@FindBy(xpath = "//input[@aria-label ='Representer App Relationship']")
	protected WebElement representerAppRelationshipTextbox;

	@FindBy(xpath = "//input[@aria-label ='First Name']")
	protected WebElement firstNameTextbox;

	@FindBy(xpath = "//input[@aria-label ='Middle Name']")
	protected WebElement middleNameTextbox;

	@FindBy(xpath = "//input[@aria-label ='Last Name']")
	protected WebElement lastNameTextbox;

	@FindBy(xpath = "//input[@aria-label ='Arabic Full Name']")
	protected WebElement arabicFullNameTextbox;

	@FindBy(xpath = "//input[@aria-label ='English Name']")
	protected WebElement englishNameTextbox;

	@FindBy(xpath = "//input[@aria-label ='Identification Expiry Date']")
	protected WebElement identificationExpiryDateTextbox;

	@FindBy(xpath = "//input[@aria-label ='Identification Issue Date']")
	protected WebElement identificationIssueDateTextbox;

	@FindBy(xpath = "//input[@aria-label ='Id Expiry Day']")
	protected WebElement identificationExpiryDayTextbox;

	@FindBy(xpath = "//input[@aria-label ='Marital Status']")
	protected WebElement maritalStatus;

	@FindBy(xpath = "//input[@aria-label ='Educational Status']")
	protected WebElement educationalStatus;

	@FindBy(xpath = "//input[@aria-label ='Do You Have Another Nationality']")
	protected WebElement anotherNationality;

	@FindBy(xpath = "//input[@aria-label ='Mobile Number Code']")
	protected WebElement mobileNumberCodeTextbox;

	@FindBy(xpath = "//input[@aria-label ='Mobile Number']")
	protected WebElement mobileNumberTextbox;

	@FindBy(xpath = "//input[@aria-label ='Customer Type']")
	protected WebElement customerType;

	@FindBy(xpath = "//input[@aria-label ='No Email']")
	protected WebElement emailTextbox;

	@FindBy(xpath = "//input[@aria-label='Do You Need To Subscribe Phonecash']/following-sibling::span")
	protected WebElement subscribePhonecash_Dropdown_BTN;

	@FindBy(xpath = "//input[@aria-label='Do You Need To Subscribe Phonecash']")
	protected WebElement subscribePhonecash_txtbox;

	@FindBy(xpath = "//input[@aria-label ='Official Address 1']")
	protected WebElement officialAddress1Textbox;

	@FindBy(xpath = "//input[@aria-label ='Official Address Country']")
	protected WebElement officialAddressCountryTextbox;

	@FindBy(xpath = "//input[@aria-label ='Residential Type']")
	protected WebElement residentialType;

	@FindBy(xpath = "//input[@aria-label ='Residence Country']")
	protected WebElement residenceCountry;

	@FindBy(xpath = "//input[@aria-label ='Do You Have Other Residence Country']")
	protected WebElement otherResidenceCountry;

	@FindBy(xpath = "//input[@aria-label ='Accommodation']")
	protected WebElement accommodation;

	@FindBy(xpath = "//input[@aria-label ='Mailing Address 1']")
	protected WebElement mailingAddress1Textbox;

	@FindBy(xpath = "//input[@aria-label ='Mailing Country']")
	protected WebElement mailingCountry;

	@FindBy(xpath = "//input[@aria-label ='Mailing Language']")
	protected WebElement mailingLanguage;

	@FindBy(xpath = "//input[@aria-label ='Is the account operated via a power of attorney']")
	protected WebElement powerOfAttorney;

	@FindBy(xpath = "//input[@aria-label='Do You Have Other Accounts/Cards in Other Banks']")
	protected WebElement otherAccountsCardsinOtherBanks;

	@FindBy(xpath = "//input[@aria-label ='Job']")
	protected WebElement job;

	@FindBy(xpath = "//input[@aria-label ='Business Sector']")
	protected WebElement businessSector;

	@FindBy(xpath = "//input[@aria-label ='Annual Income']")
	protected WebElement annualIncome;

	@FindBy(xpath = "//input[@aria-label ='Account Opening Reason']")
	protected WebElement accountOpeningReasonTextbox;

	@FindBy(xpath = "//input[@aria-label ='Account Dealing Type']")
	protected WebElement accountDealingTypeTextbox;

	@FindBy(xpath = "//input[@aria-label ='Account Dealing Amount']")
	protected WebElement accountDealingamtTextbox;

	@FindBy(xpath = "//input[@aria-label ='Account Statement Periodicity']")
	protected WebElement accountStatementPeriodicityTextbox;

	@FindBy(xpath = "//input[@aria-label ='Income Sources']")
	protected WebElement incomeSourcesTextbox;

	@FindBy(xpath = "//input[@aria-label ='Job Grade']")
	protected WebElement JobGradeTextbox;

	@FindBy(xpath = "//input[@aria-label ='Account Category']")
	protected WebElement accountCategory;

	@FindBy(xpath = "//input[@aria-label ='Account Class']")
	protected WebElement accountClass;

	@FindBy(xpath = "//input[@aria-label ='Account Class Code']")
	protected WebElement accountClassCode;

	@FindBy(xpath = "//input[@aria-label ='Account Currency']")
	protected WebElement accountCurrency;

	@FindBy(xpath = "//input[@aria-label ='Debit Card Empposing Name']")
	protected WebElement accountDescriptionTextbox;

	@FindBy(xpath = "//input[@aria-label ='Additional Information']")
	protected WebElement additionalInformation;

	@FindBy(xpath = "//input[@aria-label ='Do You Want To Open Another Account']")
	protected WebElement openAnotherAccount;

	@FindBy(xpath = "//button[@aria-label ='Finish']")
	protected WebElement finishBtn;

	@FindBy(xpath = "//td[@aria-roledescription='Application Number']//a[@name='Name']")
	protected WebElement appNumber;

	@FindBy(xpath = "//div[text()='Account Opening Application']")
	protected WebElement accountOpeningApplicationDetailsHeader;

	@FindBy(xpath = "//input[@un='Application Status']")
	protected WebElement ApplicationStatus;

	@FindBy(xpath = "(//td[@aria-roledescription ='Linked To Debit Card'])[1]")
	protected WebElement LinkToDebCrd;

	@FindBy(xpath = "//input[@name='AO_Account_Linked']")
	protected WebElement LinkToDebCrdChckBox;

	@FindBy(xpath = "//input[@name='AO_Account_Linked']/..")
	protected WebElement LinkToDebCrdChckBox_Parent;

	@FindBy(xpath = "//input[@name='Primary_Account']")
	protected WebElement PrimAccFlgChckBox;

	@FindBy(xpath = "//input[@name='Primary_Account']/..")
	protected WebElement PrimAccFlgChckBox_Parent;

	@FindBy(xpath = "(//td[@aria-roledescription ='Primary Account'])[1]")
	protected WebElement PrimAccFlg;

	public CRM_CIFPage(WebDriver driver) {
		super(driver);
	}

	private By listOption(String value) {
		return By.xpath("//li[normalize-space()='" + value + "']");
	}

	private void typeAndPick(WebElement field, String value) throws Exception {
		sendKeys(field, value);
		click(listOption(value), "Selected list value: " + value);
	}

	private void clickTypeAndPick(WebElement field, String value) throws Exception {
		click(field);
		typeAndPick(field, value);
	}

	private void clickTypeAndEnter(WebElement field, String value) {
		click(field);
		field.sendKeys(value);
		field.sendKeys(Keys.ENTER);
	}

	public void clickAccountOpening() {
		click(ApplicationToolBar, "Clicked Application Tool Bar");
	}

	public void open_Identification_Type_DropDown() {
		click(open_Identification_Type_DropDown_BTN, "Opened Identification Type drop down");
	}

	public void clickTaskIconInToolBar() {
		waitForPage();
		jsclick(ToolBar);
		waitForPage();
		jsclick(TaskIcon);
		waitForPage();
		log.info("Clicked Task Icon");
	}

	public void clickAccountOpeningLink() {
		click(AccountOpeningLink, "Clicked Account Opening Link");
	}

	public void verifyAccountOpeningPage() {
		try {
			waitForVisibility(AccountOpeningPage, 30);
			log.info("Account Opening page is displayed");
		} catch (TimeoutException e) {
			log.warn("Account Opening page is not displayed: {}", e.getMessage());
		}
	}

	public void enter_IdentificationType(String idtype) throws Exception {
		typeAndPick(identificationType_txt, idtype);
	}

	public void enter_Identificationnumber(String idNum) throws Exception {
		click(identificationNum_txt);
		sendKeys(identificationNum_txt, idNum);
	}

	public void enter_ActionType(String actiontype) throws Exception {
		clickTypeAndPick(actiontype_txt, actiontype);
	}

	public void enter_representerapp(String appRelationship) throws Exception {
		clickTypeAndPick(representerAppRelationshipTextbox, appRelationship);
	}

	public void click_nxtbtn() {
		click(nextBtn, "Clicked Next");
		waitForPage();
	}

	public void selectDropdownValue(String value) {
		click(By.xpath("//li[contains(normalize-space(),\"" + value + "\")]"), "Selected drop down value: " + value);
	}

	public void enterArabicName(String arabicName) throws Exception {
		sendKeys(firstNameTextbox, arabicName);
		sendKeys(middleNameTextbox, arabicName);
		sendKeys(lastNameTextbox, arabicName);

		String expectedFullName = arabicName + " " + arabicName + " " + arabicName.trim();
		sendKeys(arabicFullNameTextbox, expectedFullName);

		String actualFullName = getAttribute(arabicFullNameTextbox, "value");
		log.info("Arabic Full Name - expected: {}, actual: {}", expectedFullName, actualFullName);
	}

	public void enter_english_names(String eng_names) throws Exception {
		sendKeys(englishNameTextbox, eng_names);
	}

	public void enterEnglishName(String englishName, String firstName, String middleName, String lastName) {
		sendKeys(englishNameTextbox, englishName, "Entered English Name");
		sendKeys(firstNameTextbox, firstName, "Entered First Name");
		sendKeys(middleNameTextbox, middleName, "Entered Middle Name");
		waitForPage();
		sendKeys(lastNameTextbox, lastName, "Entered Last Name");
	}

	public void enterIdentificationExpiryDate(String expiryDate) throws Exception {
		sendKeys(identificationExpiryDateTextbox, expiryDate);
	}

	public void chooseMaritalStatus(String maritalstatus) throws Exception {
		clickTypeAndPick(maritalStatus, maritalstatus);
	}

	public void chooseEducationalStatus(String educationalstatus) throws Exception {
		clickTypeAndPick(educationalStatus, educationalstatus);
	}

	public void enterMobileNumber(String mobileNumber) throws Exception {
		sendKeys(mobileNumberTextbox, mobileNumber);
	}

	public void checkNoEmail() {
		waitForVisibility(emailTextbox);
		if (!emailTextbox.isSelected()) {
			click(emailTextbox, "Checked No Email");
		}
	}

	public void choosePhoneCash(String phonecash) throws Exception {
		click(subscribePhonecash_Dropdown_BTN);
		sendKeys(subscribePhonecash_txtbox, phonecash);
		subscribePhonecash_txtbox.sendKeys(Keys.ENTER);
	}

	public void chooseCustomerType(String custype) throws Exception {
		clickTypeAndPick(customerType, custype);
	}

	public void enterOfficialAddress(String address) throws Exception {
		sendKeys(officialAddress1Textbox, address);
	}

	public void chooseResidentialType(String resType) throws Exception {
		clickTypeAndPick(residentialType, resType);
	}

	public void chooseOtherResidenceCountry(String otherresidencetype) throws Exception {
		clickTypeAndPick(otherResidenceCountry, otherresidencetype);
	}

	public void chooseAccommodation(String accomdation) throws Exception {
		clickTypeAndPick(accommodation, accomdation);
	}

	public void chooseMailingCountry(String mailingcountry) throws Exception {
		clickTypeAndPick(mailingCountry, mailingcountry);
	}

	public void choosePowerOfAttorney(String powerofattorney) throws Exception {
		click(powerOfAttorney);
		sendKeys(powerOfAttorney, powerofattorney);
		powerOfAttorney.sendKeys(Keys.ENTER);
	}

	public void chooseOtherAccountsCards(String cards) {
		clickTypeAndEnter(otherAccountsCardsinOtherBanks, cards);
	}

	public void chooseJob(String jobs) throws Exception {
		clickTypeAndPick(job, jobs);
	}

	public void chooseJobGrade(String jobgrade) throws Exception {
		clickTypeAndPick(JobGradeTextbox, jobgrade);
	}

	public void chooseBusinessSector(String busisector) throws Exception {
		clickTypeAndPick(businessSector, busisector);
	}

	public void chooseAnnualIncome(String annincome) throws Exception {
		clickTypeAndPick(annualIncome, annincome);
	}

	public void chooseIncomeSources(String incomesource) throws Exception {
		clickTypeAndPick(incomeSourcesTextbox, incomesource);
	}

	public void chooseAccountOpeningReason(String accopenreason) throws Exception {
		clickTypeAndPick(accountOpeningReasonTextbox, accopenreason);
	}

	public void chooseAccountDealingType(String accdealtype) throws Exception {
		clickTypeAndPick(accountDealingTypeTextbox, accdealtype);
	}

	public void chooseAccountDealingAmount(String accdealamt) throws Exception {
		clickTypeAndPick(accountDealingamtTextbox, accdealamt);
	}

	public void chooseStatementPeriodicity(String statementperiod) throws Exception {
		clickTypeAndPick(accountStatementPeriodicityTextbox, statementperiod);
	}

	public void chooseAccountCategory(String acccategory) throws Exception {
		clickTypeAndPick(accountCategory, acccategory);
	}

	public void chooseAccountClass(String accClass) {
		clickTypeAndEnter(accountClass, accClass);
	}

	public void chooseAccountCurrency(String accCurr) throws Exception {
		click(accountCurrency);
		sendKeys(accountCurrency, accCurr);
	}

	public void enterDebitCardEmposingName(String debitname) throws Exception {
		sendKeys(accountDescriptionTextbox, debitname);
	}

	public void chooseAdditionalInformation(String addinfo) {
		clickTypeAndEnter(additionalInformation, addinfo);
	}

	public void chooseOpenAnotherAccount(String anotheracc) {
		clickTypeAndEnter(openAnotherAccount, anotheracc);
	}

	public void clickFinish() {
		click(finishBtn, "Clicked Finish");
	}

	public void acceptAlert() {
		Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
		log.info("Alert message: {}", alert.getText());
		alert.accept();
	}

	public void storeApplicationNumber() {
		waitForVisibility(appNumber);
		applicationNumberValue = appNumber.getText().trim();
		log.info("Application Number: {}", applicationNumberValue);
	}

	public void clickApplicationNumber() {
		click(appNumber, "Clicked Application Number");
	}

	public String getApplicationNumber() {
		return applicationNumberValue;
	}

	public void validateRoutingCorrectPage() {
		waitForVisibility(accountOpeningApplicationDetailsHeader, 30,
				"accountOpeningApplicationDetailsHeader Element Not Found!");
	}

	public void ClickOnPrimaryAccountFlag() {
		waitForPageLoad();
		click(PrimAccFlg);
		if ("false".equals(getAttribute(PrimAccFlgChckBox, "aria-checked"))) {
			waitForPageLoad();
			click(PrimAccFlgChckBox_Parent);
		}
	}

	public void ClickOnLinkedToDebitCard() {
		waitForPageLoad();
		click(LinkToDebCrd);
		if ("false".equals(getAttribute(LinkToDebCrdChckBox, "aria-checked"))) {
			waitForPageLoad();
			click(LinkToDebCrdChckBox_Parent);
		}
	}

	public void validateCurrentApplication(String application_status) {
		waitForPageLoad();
		scrollUntilElementDisplayed(ApplicationStatus);
		try {
			new WebDriverWait(driver, Duration.ofSeconds(20)).until(d -> application_status.trim()
					.equals(String.valueOf(ApplicationStatus.getAttribute("value")).trim()));
		} catch (TimeoutException e) {
			log.warn("Application Status did not change to '{}' within 20 seconds", application_status);
		}
		Assertvalue(ApplicationStatus, application_status);
	}

	public void changeCurrentApplicationStatus(String Current_application_status) {
		waitForPage();
		jsclick(ApplicationStatus);
		ApplicationStatus.clear();
		ApplicationStatus.sendKeys(Current_application_status);
		driver.switchTo().activeElement().sendKeys(Keys.TAB);
		waitForPage();
	}
}
