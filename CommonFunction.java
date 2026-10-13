package com.qa.framework.pages.ui;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.framework.pages.BasePage;

public abstract class CommonFunction extends BasePage {

	private final Logger log = LogManager.getLogger(CommonFunction.class);

	private String outputvalue2 = "";
	private String outputvalue1 = "";
	public String refno;
	public String Inputvalue = "";
	private boolean verifymsg;
	private String parentWindow = "";

	@FindBy(xpath = "(//*[contains(text(),'Ok')])[1]")
	protected WebElement Okbutton;
	@FindBy(xpath = "//*[@class='user']")
	protected WebElement user_tab;
	@FindBy(xpath = "(//*[@id='userMenu']/preceding::span[@slot='endIcon'])[5]")
	protected WebElement usertab;
	@FindBy(xpath = "//li[contains(text(),'Sign Off')]")
	protected WebElement Sign_Off;
	@FindBy(id = "signOff")
	protected WebElement SignOff;
	@FindBy(xpath = "//*[@id='BtnSubSysNav']/child::span")
	protected WebElement Arrow_button;
	@FindBy(xpath = "//li[@id='EnterQuery']/a")
	protected WebElement EnterQuery;
	@FindBy(xpath = "//*[@id='EnterQuery']//button")
	protected WebElement EnterQuery_btn;
	@FindBy(xpath = "//*[@id='New']/a")
	protected WebElement New_Button;
	@FindBy(xpath = "//*[@id='New']/button")
	protected WebElement NewButton;
	@FindBy(id = "ifr_AlertWin")
	protected WebElement Alertframe;
	@FindBy(xpath = "//iframe[@title='Override Message']")
	protected WebElement OverrideMessage;
	@FindBy(xpath = "//*[contains(@src,'O&MESSAGE')]")
	protected WebElement Override_Message;
	@FindBy(xpath = "//input[@name='Accept']")
	protected WebElement Accept;
	@FindBy(id = "BTN_ACCEPT")
	protected WebElement Accept_btn;
	@FindBy(xpath = "//*[@class='login-logo']")
	protected WebElement Loginlogo;
	@FindBy(id = "LOGINUSERID|input")
	protected WebElement txtUser_Name;
	@FindBy(xpath = "//*[text()='Stop Cheque Request ']")
	protected WebElement StopChequeRequest;
	@FindBy(id = "user_pwd|input")
	protected WebElement txt_Password;
	@FindBy(id = "LOGINUSERID")
	protected WebElement txtUserName;
	@FindBy(id = "user_pwd")
	protected WebElement txtPassword;
	@FindBy(id = "fc_sbmit")
	protected WebElement btnLogin;
	@FindAll({ @FindBy(id = "TO_STATUS"), @FindBy(id = "1") })
	protected WebElement txt_Search_Sub_Screen;
	@FindBy(id = "2")
	protected WebElement txt_Search_Sub_Screenlocation;
	@FindBy(xpath = "//button[text()='Ok']")
	protected WebElement ok_button;
	@FindBy(className = "BTNtext")
	protected WebElement fetch_Search_Sub_Screen;
	@FindBy(className = "LBLstdLOV")
	protected WebElement error_in_MAQ;
	@FindBy(id = "BTN_OK")
	protected WebElement popuOkButton;
	@FindBy(xpath = "//*[@id='Branch_Menu']/label")
	protected WebElement current_branch_field;
	@FindBy(xpath = "//*[@id='Branch_Menu']/descendant::span[contains(text(),'Branch') and contains(@id,'ui-id')]")
	protected WebElement currentbranch_field;
	@FindBy(id = "BTN_SAVE")
	protected WebElement Re_Enter_Password_Ok_Btn;
	@FindBy(id = "newpwd")
	protected WebElement Re_Enter_Password;
	@FindBy(id = "newpwd|input")
	protected WebElement ReEnterPassword;
	@FindBy(id = "Branch_Menu")
	protected WebElement Branch;
	@FindBy(id = "fastpath")
	protected WebElement Searchbar;
	@FindBy(xpath = "//*[@id='searchInputContainer_fastpath']/div/input")
	protected WebElement Searchbartxt;
	@FindBy(id = "btnGo")
	protected WebElement Search_Go_Button;
	@FindBy(id = "select_branch")
	protected WebElement Select_Branch;
	@FindBy(id = "home_branch")
	protected WebElement Home_Branch;
	@FindBy(id = "1")
	protected WebElement Enterfield;
	@FindBy(id = "1|input")
	protected WebElement Enter_field;
	@FindBy(xpath = "//*[contains(text(),'Fetch')]")
	protected WebElement Fetch;
	@FindBy(id = "BTN_OK")
	protected WebElement PoP_Up_Ok_button;
	@FindBy(xpath = "//*[contains(text(),'Fetch')]")
	protected WebElement Fetch_btn;
	@FindBy(id = "ifrSubScreen")
	protected WebElement subframe;
	@FindBy(id = "BTN_EXIT_IMG")
	protected WebElement Exit;
	@FindBy(id = "ifr_LaunchWin")
	protected WebElement Searchscreen;
	@FindBy(id = "BTN_EXIT")
	protected WebElement Exitsummary;
	@FindBy(xpath = "(//span[contains(text(),'Confirm') and contains(@id,'text')])[1]")
	protected WebElement confirm_approval;
	@FindBy(xpath = "//*[@class='oj-message-summary oj-message-title']")
	protected WebElement title;
	@FindBy(xpath = "(//span[contains(text(),'Submit For Approval') and contains(@id,'text')])[1]")
	protected WebElement Submit_approval;
	@FindBy(xpath = "//*[contains(@id,'|text')]/span[@data-bind='text: labels.okLbl']")
	protected WebElement submitOkbtn;
	@FindBy(xpath = "(//*[contains(@id,'|text')]/span[@data-bind='text: labels.no'])[1]")
	protected WebElement No_btn;
	@FindBy(xpath = "//a[contains(text(),'Save')]")
	protected WebElement Save;
	@FindBy(xpath = "//*[contains(text(),'Save') and contains(@id,'Save')]")
	protected WebElement Save_btn;
	@FindBy(xpath = "(//*[@id='ifr_AlertWin'])[2]")
	protected WebElement Alert2;
	@FindBy(id = "ui-id-3")
	protected WebElement element;
	@FindBy(id = "okButton_oj3|text")
	protected WebElement proceedbutton;
	@FindBy(id = "branch-icon")
	protected WebElement branch_icon;
	@FindBy(xpath = "//*[@class='oj-inputtext-input oj-text-field-input oj-component-initnode']")
	protected WebElement branchcodtxt;
	@FindBy(xpath = "(//*[@data-bind='text: labels.fsgbuobcmnfdlov.fetchBtnLbl'])[1]")
	protected WebElement Fetch_button;
	@FindAll({ @FindBy(xpath = "(//*[contains(text(),'001')])[2]"), @FindBy(xpath = "//*[contains(text(),'121')]"),
			@FindBy(xpath = "//*[contains(text(),'003')]"), @FindBy(xpath = "//*[contains(text(),'004')]"),
			@FindBy(xpath = "//*[contains(text(),'096')]"), @FindBy(xpath = "//*[contains(text(),'002')]") })
	protected WebElement selectbranchcode;
	@FindBy(id = "confirmBtn")
	protected WebElement confirmbtn;
	@FindBy(xpath = "//*[contains(text(),'OBMA PRODUCTS')]")
	protected WebElement OBMA_btn;
	@FindBy(xpath = "//*[@id='SRV_TELLER']/a")
	protected WebElement teller_menu;
	@FindBy(xpath = "//*[contains(text(),'Electronic Journal')]")
	protected WebElement Electronicjournal;
	@FindBy(xpath = "//*[contains(text(),'Servicing Journal')]")
	protected WebElement servicingjournal;
	@FindBy(xpath = "//*[@id='branchDate']/span")
	protected WebElement trrefnotxt;
	@FindBy(xpath = "//*[@id='txnStatus|input']")
	protected WebElement pendingdropdown;
	@FindBy(xpath = "//*[contains(text(),'Completed')]")
	protected WebElement select_Completed;
	@FindBy(xpath = "(//input[@class='oj-inputtext-input oj-text-field-input oj-component-initnode'])[1]")
	protected WebElement user_id;
	@FindBy(xpath = "//*[contains(text(),'Pending Approval')]")
	protected WebElement selectpendingapproval;
	@FindBy(xpath = "//*[text()='Rejected']")
	protected WebElement Rejectoptiondd;
	@FindBy(id = "txnStatus|input")
	protected WebElement pendingtext;
	@FindAll({ @FindBy(xpath = "(//*[@id='wiz-custom-footer-next']/button)[1]"),
			@FindBy(xpath = "//*[@id='wiz-custom-footer-next']/button") })
	protected WebElement Submitbtn;
	@FindBy(xpath = "//*[@id='button1']/button")
	protected WebElement Fetchbtn;
	@FindBy(xpath = "//*[@id='genAdviceBtn']/child::*")
	protected WebElement Generateadvice;
	@FindBy(xpath = "//*[@title='Advice Name']/child::div/child::div[@class='oj-table-column-header-text']")
	protected WebElement adive_verify;
	@FindBy(xpath = "//*[@data-bind='text: labels.closeLbl']")
	protected WebElement generateadvice_Close;
	@FindBy(xpath = "//*[@id='rejectBtn']/button")
	protected WebElement Rejectlbtn;
	@FindBy(xpath = "(//*[@class='posRelative fsgbu-right-top'])[2]/../../..")
	protected WebElement View_transaction;
	@FindBy(id = "transactionReference")
	protected WebElement TransactionRefno;
	@FindBy(xpath = "//span[contains(@id,'wiz-cancel-btn-right')]/..")
	protected WebElement Clickcancel;
	@FindBy(xpath = "//*[text()='Error']")
	protected WebElement ErrorPopup;
	@FindBy(xpath = "//*[text()='Reject Transaction']")
	protected WebElement RejectTransaction;
	@FindBy(id = "txnRefNumber|input")
	protected WebElement trrefno;
	@FindBy(xpath = "//*[@id='approveBtn']/button")
	protected WebElement Approvelbtn;
	@FindBy(xpath = "//*[@data-bind='text: baseLebal.fsgbuobremosrvdselectronicjournalcash.confirm']")
	protected WebElement Override_ConfirmMSG;
	@FindBy(xpath = "//*[text()='Approve Transaction']/ancestor::div[@class='oj-dialog-container']/descendant::span/span[text()='Ok']")
	protected WebElement Okbtn;
	@FindBy(xpath = "//*[@data-bind='text: labelsCommon.okLbl']")
	protected WebElement Ok_confirmation;
	@FindBy(xpath = "//*[@id='showFilter']/child::*")
	protected WebElement filterbtn;
	@FindBy(className = "oj-panel-remove-icon")
	protected WebElement closebtn;
	@FindBy(xpath = "//*[contains(text(),'Next Gen UI')]")
	protected WebElement NextGenuibtn;
	@FindBy(xpath = "//label[text()='No Records Found for the Specified Criteria']")
	protected WebElement Assertion_NoRecordFound;
	@FindBy(xpath = "//a[@id='WNDbuttons']")
	protected WebElement close_button_NoRecordFound;
	@FindBy(id = "2")
	protected WebElement txt_Search_Sub_ScreenDescription;
	@FindBy(name = "SWEUserName")
	protected WebElement username_TF;
	@FindBy(name = "SWEPassword")
	protected WebElement password_TF;
	@FindBy(className = "siebui-login-btn")
	protected WebElement login_BTN;


	public CommonFunction userName(String value) throws Exception {
		waitForPage();
		sendKeys(username_TF, value);
		return this;
	}

	public CommonFunction userName_New(String value) throws Exception {
		return userName(value);
	}

	public CommonFunction passWord(String value) throws Exception {
		sendKeys(password_TF, value);
		return this;
	}

	public CommonFunction passWord_New(String value) throws Exception {
		return passWord(value);
	}

	public void Enter_ScreenID(String value) throws Exception {
		sendKeys(Searchbar, value);
		click(Search_Go_Button);
		waitForPage();
	}

	public void Enter_Screen_ID(String value) throws Exception {
		sendKeys(Searchbartxt, value);
		click(Search_Go_Button);
		waitForPage();
	}

	public void Branch(String branch) throws Exception {
		driver().switchTo().defaultContent();
		waitForPageLoad();
		click(Branch);
		jsmagnifierSearch(Select_Branch, "List of Values Branch Code", branch);
		handleBranchMessage(branch, true);
	}

	public void Branch_Switch(String branch) throws Exception {
		driver().switchTo().defaultContent();
		waitForPage();
		click(Branch);
		jsmagnifier_Search(Select_Branch, "List of Values Branch Code", branch);
		handleBranchMessage(branch, false);
	}

	private void handleBranchMessage(String branch, boolean legacy) throws Exception {
		try {
			if (legacy)
				switchToAlertFrame("Confirmation Message");
			else
				switchtoAlert_Frame("Confirm");
			jsclick(popuOkButton);
			driver().switchTo().parentFrame();
			AsserttextWithVisibility(legacy ? current_branch_field : currentbranch_field, "Branch : " + branch);
		} catch (AssertionError e) {
			driver().switchTo().defaultContent();
			if (legacy)
				switchToAlertFrame("Information Message");
			else
				switchtoAlert_Frame("Information");
			jsclick(popuOkButton);
			driver().switchTo().parentFrame();
			AsserttextWithVisibility(current_branch_field, "Branch : " + branch);
		}
	}

	public void click_New(String expectedTitle) throws Exception {
		waitForPage();
		switchToFrame(Mainframe_common);
		verifyLegacyTitle(expectedTitle);
		jsclick(New_Button);
	}

	public void clickNew(String expectedTitle) throws Exception {
		switchToFrame(Mainframe_common);
		By titleBy = By.xpath("//*[@id='wndtitle']/div[@class='oj-flex-bar-start']/h1");
		WebElement titleElement = new WebDriverWait(driver(), Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(titleBy));
		Assert.assertEquals(titleElement.getText().trim(), expectedTitle.trim());
		jsclick(NewButton);
	}

	private void verifyLegacyTitle(String expectedTitle) {
		By titleBy = By.className("WNDtitletxt");
		WebElement titleElement = new WebDriverWait(driver(), Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(titleBy));
		Assert.assertEquals(titleElement.getText().trim(), expectedTitle.trim());
	}

	public void click_on_MAQ_and_choose(WebElement maq, String title, String value) throws Exception {
		click(maq);
		switchto_Subscreen_Frame(title);
		chooseLovValue(txt_Search_Sub_Screen, fetch_Search_Sub_Screen, title, value, true);
	}

	public void click_on_MAQ_and_choose_New(WebElement maq, String title, String value) throws Exception {
		click(maq);
		switchToSub_Screen(title);
		chooseLovValue(Enter_field, Fetch, title, value, false);
	}

	public void click_on_MAQ_and_chooselocation(WebElement maq, String title, String value) throws Exception {
		click(maq);
		switchto_Subscreen_Frame(title);
		chooseLovValue(txt_Search_Sub_Screenlocation, fetch_Search_Sub_Screen, title, value, true);
	}

	private void chooseLovValue(WebElement input, WebElement fetch, String title, String value, boolean link)
			throws Exception {
		try {
			waitForVisibility(input, 30);
			input.clear();
			input.sendKeys(value);
		} catch (Exception e) {
			throw new Exception("There is no Text field to enter: " + value + " in " + title, e);
		}
		if (!link)
			driver().switchTo().activeElement().sendKeys(Keys.ENTER);
		jsclick(fetch);
		try {
			click(By.xpath(link ? "//a[contains(text(),'" + value + "')]" : "//*[text()='" + value + "']"));
		} catch (Exception e) {
			throw new Exception(error_in_MAQ.getText() + " in " + title + " match: " + value, e);
		}
	}

	public void Branchcode() throws Exception {
		if (isDisplayed(subframe)) {
			switchToFrame(subframe);
			verifyLegacyTitle("Transaction Branch Code");
			click(ok_button);
			driver().switchTo().parentFrame();
		}
	}

	public void Click_Query(String title) throws Exception {
		switchToFrame(Mainframe_common);
		waitForPage();
		verifyLegacyTitle(title);
		jsclick(EnterQuery);
	}

	public void ClickEnterQuery(String title) throws Exception {
		switchToFrame(Mainframe_common);
		By by = By.xpath("//*[@id='wndtitle']/div/h1");
		WebElement e = new WebDriverWait(driver(), Duration.ofSeconds(20))
				.until(ExpectedConditions.visibilityOfElementLocated(by));
		Assert.assertEquals(e.getText().trim(), title.trim());
		jsclick(EnterQuery_btn);
	}

	public void jsmagnifierSearch(WebElement searchIcon, String title, String text) throws Exception {
		jsclick(searchIcon);
		switchToSubScreen(title);
		sendKeys(Enterfield, text);
		jsclick(Fetch);
		click(By.xpath("//a[contains(text(),'" + text + "')]"));
		driver().switchTo().parentFrame();
	}

	public void jsmagnifier_Search(WebElement searchIcon, String title, String text) throws Exception {
		jsclick(searchIcon);
		switchToSub_Screen(title);
		sendKeys(Enter_field, text);
		driver().switchTo().activeElement().sendKeys(Keys.ENTER);
		jsclick(Fetch);
		click(By.xpath("//*[text()='" + text + "']"));
		driver().switchTo().parentFrame();
	}

	public void switchToSubScreen(String title) throws Exception {
		switchToFrameById("ifrSubScreen", 10);
		Asserttext(visibleElement(By.className("WNDtitletxt")), title);
	}

	public void switchToSub_Screen(String title) throws Exception {
		switchToFrameById("ifrSubScreen", 10);
		waitForPage();
		Asserttext(visibleElement(By.xpath("//*[@id='WNDtitlebar']/div/child::h3")), title);
	}

	public void switchToAlertFrame(String title) throws Exception {
		switchToFrameById("ifr_AlertWin", 30);
		Asserttext(visibleElement(By.className("WNDtitletxt")), title);
	}

	private void switchToFrameById(String id, long seconds) {
		new WebDriverWait(driver(), Duration.ofSeconds(seconds))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(id)));
	}

	public void click_loginbutton(String password) throws Exception {
		waitForVisibility(login_BTN);
		click(login_BTN);
		waitForPage();
	}

	public void clickloginbutton(String password) throws Exception {
		waitForVisibility(btnLogin);
		click(btnLogin);
		waitForPage();
		Checkpassword(password);
	}

	public void Check_password(String password) throws Exception {
		processPasswordChange(password, "newpwd", Re_Enter_Password, "Information Message", true);
	}

	public void Checkpassword(String password) throws Exception {
		processPasswordChange(password, "newpwd|input", ReEnterPassword, "Information", false);
	}

	private void processPasswordChange(String password, String id, WebElement passwordInput, String informationTitle,
			boolean legacy) throws Exception {
		waitForPage();
		waitForVisibility(Alertframe);
		switchToFrame(Alertframe);
		waitForVisibility(popuOkButton);
		click(popuOkButton);
		if (!driver().findElements(By.id(id)).isEmpty()) {
			waitForVisibility(passwordInput);
			sendKeys(passwordInput, password);
			click(Re_Enter_Password_Ok_Btn);
			driver().switchTo().defaultContent();
			if (legacy)
				switchto_Alert_Frame(informationTitle);
			else
				switchtoAlert_Frame(informationTitle);
			click(popuOkButton);
			driver().switchTo().defaultContent();
			waitForVisibility(Searchbar);
			Assert.assertTrue(Searchbar.isDisplayed(), "User is successfully logged in");
		}
		driver().switchTo().defaultContent();
	}

	public void fetch_method(String text) throws Exception {
		sendKeys(Enterfield, text);
		click(Fetch_btn);
		click(By.xpath("//a[contains(text(),'" + text + "')]"));
	}

	public void fetch_method_new(String text) throws Exception {
		switchToFrame(subframe);
		fetch_method(text);
		driver().switchTo().parentFrame();
	}

	public void HomeBranch() throws Exception {
		driver().switchTo().defaultContent();
		click(Branch);
		click(Home_Branch);
		switchToFrame(Alertframe);
		click(PoP_Up_Ok_button);
		driver().switchTo().defaultContent();
	}

	public void Logout() throws Exception {
		logout(user_tab, Sign_Off, "Confirmation Message", true);
	}

	public void Log_out() throws Exception {
		logout(usertab, SignOff, "Confirm", false);
	}

	private void logout(WebElement user, WebElement signOff, String title, boolean legacy) throws Exception {
		try {
			waitForVisibility(user);
			jsclick(user);
			jsclick(signOff);
			waitForDisapear(signOff);
			if (legacy)
				switchto_Alert_Frame(title);
			else
				switchtoAlert_Frame(title);
			click(PoP_Up_Ok_button);
			driver().switchTo().defaultContent();
		} catch (TimeoutException e) {
			log.warn("Logout field not displayed", e);
		}
	}

	public String Datemodify(String maturitydate, String amendantdate) {
		LocalDate maturity = LocalDate.parse(maturitydate);
		LocalDate amendment = LocalDate.parse(amendantdate);
		if (maturity.isAfter(amendment))
			return amendantdate;
		if (maturity.isBefore(amendment))
			return maturity.minusMonths(1).withDayOfMonth(amendment.getDayOfMonth()).toString();
		return amendantdate;
	}

	public void ClickNewButton() throws Exception {
		switchToFrame(Mainframe_common);
		click(New_Button);
	}

	public void Exit() throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common);
		jsclick(Exit);
	}

	public void clickSaveButton() throws Exception {
		saveWithMessages(Save, "Override Message", "Information Message", Accept, true);
	}

	public void click_Save_Button() throws Exception {
		saveWithMessages(Save_btn, "Override", "Information", Accept_btn, false);
	}

	public void clickSaveButton_new() throws Exception {
		saveWithMessages(Save, "Override Message", "Information Message", Accept, true);
	}

	private void saveWithMessages(WebElement save, String overrideTitle, String infoTitle, WebElement accept,
			boolean legacy) throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common);
		Click(save);
		waitForPage();
		if (isDisplayed(subframe)) {
			if (legacy)
				switchto_Subscreen_Frame("Remarks");
			else
				switchToSub_Screen("Remarks");
			click(popuOkButton);
			driver().switchTo().parentFrame();
		}
		if (isDisplayed(legacy ? OverrideMessage : Override_Message)) {
			if (legacy)
				switchto_Alert_Frame(overrideTitle);
			else
				switchtoAlert_Frame(overrideTitle);
			Click(accept);
			driver().switchTo().parentFrame();
		}
		if (legacy)
			switchto_Alert_Frame(infoTitle);
		else
			switchtoAlert_Frame(infoTitle);
		click(PoP_Up_Ok_button);
	}

	public void Clickok_Button() throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common);
		switchto_Alert_Frame("Information Message");
		click(PoP_Up_Ok_button);
	}

	public void clicknextgenerationuitab() {
		waitForPageLoad();
		click(NextGenuibtn);
	}

	public void clickobmaproducts() throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common);
		waitForPageLoad();
		jsclick(OBMA_btn);
	}

	public void selectbranchcode_Teller(String branchcode) throws Exception {
		waitForVisibility(branch_icon);
		click(branch_icon);
		waitForVisibility(branchcodtxt);
		sendKeys(branchcodtxt, branchcode);
		click(Fetch_button);
		By second = By.xpath("(//*[contains(text(),'" + branchcode + "')])[2]");
		By first = By.xpath("//*[contains(text(),'" + branchcode + "')]");
		waitFor().until(ExpectedConditions.presenceOfElementLocated(first));
		click(driver().findElements(second).isEmpty() ? first : second);
		if (!"A00".equals(branchcode)) {
			waitForVisibility(confirmbtn);
			click(confirmbtn);
		}
	}

	public void clickteller() {
		scrollUntilElementDisplayed(teller_menu);
		Asserttext(teller_menu, "Teller");
		click(teller_menu);
	}

	public void clickElectronicjournal() {
		click(Electronicjournal);
	}

	public void clickservicingjournal() {
		waitForVisibility(servicingjournal);
		click(servicingjournal);
	}

	public void submitapproval() {
		driver().switchTo().activeElement().sendKeys(Keys.ENTER);
		refno = extractReference(gettext(trrefnotxt));
		waitForVisibility(Submitbtn);
		jsclick(Submitbtn);
		outputvalue2 = refno;
		waitForVisibility(title);
		Asserttext(title, "Approval Confirmation");
		click(confirm_approval);
		click(Submit_approval);
		click(submitOkbtn);
		click(No_btn);
	}

	private String extractReference(String raw) {
		String first = raw.split(",")[0];
		String[] parts = first.split("-", 2);
		return parts.length == 2 ? parts[1].trim() : first.trim();
	}

	public void approval() throws Exception {
		selectTransactionStatus("Pending Approval");
		sendKeys(trrefno, refno);
		click(Fetchbtn);
		if (isDisplayed(Fetchbtn))
			jsclick(Fetchbtn);
		Click(Approvelbtn);
		confirmApproval();
		outputvalue2 = refno;
	}

	private void selectTransactionStatus(String status) throws Exception {
		waitForVisibility(pendingdropdown);
		sendKeys(pendingdropdown, status);
		waitforelementtopresent("xpath|//*[contains(text(),'" + status + "')]");
		if ("Pending Approval".equals(status))
			click(selectpendingapproval);
		else
			click(select_Completed);
		Assertvalue(pendingtext, status);
	}

	private void confirmApproval() throws Exception {
		if (isDisplayed(Override_ConfirmMSG))
			Override_ConfirmMSG.click();
		click(Okbtn);
		waitForDisapear(Okbtn);
		waitforelementtopresent("xpath|//div[@class='oj-message-summary oj-message-title']");
		click(Ok_confirmation);
		waitForDisapear(Ok_confirmation);
		if (isDisplayed(closebtn))
			jsclick(closebtn);
	}

	public void movetomainwindow() throws Exception {
		Set<String> windows = driver().getWindowHandles();
		for (String handle : windows)
			if (!handle.equals(parentWindow)) {
				driver().switchTo().window(handle);
				driver().close();
			}
		driver().switchTo().window(parentWindow);
	}

	public void click_proceedbtn() throws Exception {
		parentWindow = driver().getWindowHandle();
		Switch_Window(1);
		if (isDisplayed(element))
			click(proceedbutton);
	}

	public void switch_main_frame(String title) throws Exception {
		wait_for_Pulling(Mainframe_common);
		switchToFrame(Mainframe_common);
		verifyLegacyTitle(title);
	}

	public void waitForRetreivedData(WebElement textBox) throws Exception {
		try {
			new WebDriverWait(driver(), Duration.ofSeconds(10))
					.until(ExpectedConditions.attributeToBeNotEmpty(textBox, "value"));
		} catch (Exception e) {
			throw new Exception("There is no data retrieved", e);
		}
	}

	public void click_entry(String title) throws Exception {
		switch_main_frame(title);
		jsclick(EnterQuery);
	}

	public void Exit_from_Mainframe() throws Exception {
		jsclick(Exit);
		driver().switchTo().defaultContent();
	}

	/** Resolves a direct path, JVM property key, or environment-variable key. */
	private String resolveConfiguredPath(String pathOrKey) {
		if (pathOrKey == null || pathOrKey.isBlank())
			throw new IllegalArgumentException("Excel path or key cannot be blank");
		String configured = System.getProperty(pathOrKey);
		if (configured == null || configured.isBlank())
			configured = System.getenv(pathOrKey);
		if (configured == null || configured.isBlank())
			configured = pathOrKey;
		Path path = Paths.get(configured.trim());
		if (!path.isAbsolute())
			path = Paths.get(System.getProperty("user.dir")).resolve(path);
		return path.normalize().toAbsolutePath().toString();
	}

	public void WritedatainCASAOBMAexcel_output(String sheetname, String columnName1, String columnName2,
			String excelpath) throws IOException {
		String path = resolveConfiguredPath(excelpath);
		outputvalue1 = getLastInstrumentNo(path, sheetname, columnName1);
		writeToExcel(path, sheetname, columnName1, columnName2, outputvalue1, outputvalue2);
	}

	public void Excelwrite(String sheetname, String coloumnname, String excelname) throws Exception {
		writexcelcell(coloumnname, resolveConfiguredPath(excelname), Inputvalue, sheetname);
	}

	public void Exitpage() throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common1);
		jsclick(Exit);
		driver().switchTo().defaultContent();
		switchToFrame(Searchscreen);
		click(Exitsummary);
	}

	public void Exit1() throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common);
		click(Exitsummary);
	}

	public void resetLogin_new() throws Exception {
		resetPassword("Information", "oldpwd|input", "newpwd|input", "confnew|input", "Oracle@1", "Oracle@2", false);
	}

	public void resetLogin() throws Exception {
		resetPassword("Information Message", "oldpwd", "newpwd", "confnew", "Oracle@2", "Oracle@3", true);
	}

	private void resetPassword(String info, String oldId, String newId, String confirmId, String oldValue,
			String newValue, boolean legacy) throws Exception {
		jsclick(btnLogin);
		waitForPage();
		if (legacy)
			switchToAlertFrame(info);
		else
			switchtoAlert_Frame(info);
		click(By.id("BTN_OK"));
		driver().switchTo().parentFrame();
		if (legacy)
			switchToAlertFrame("Change Password");
		else
			switchtoAlert_Frame("Change Password");
		sendKeys(visibleElement(By.id(oldId)), oldValue);
		sendKeys(visibleElement(By.id(newId)), newValue);
		sendKeys(visibleElement(By.id(confirmId)), newValue);
		click(By.id("BTN_SAVE"));
		driver().switchTo().parentFrame();
	}

	public void reject_transaction() throws Exception {
		selectTransactionStatus("Pending Approval");
		sendKeys(trrefno, refno);
		click(Fetchbtn);
		click(Rejectlbtn);
		verifymsg = isDisplayed(ErrorPopup);
		if (verifymsg)
			Asserttext(ErrorPopup, "Approval Confirmation");
		else {
			Asserttext(RejectTransaction, "Reject Transaction");
			click(Okbutton);
			click(Ok_confirmation);
		}
	}

	public void Check_the_Reject_request() throws Exception {
		click(pendingdropdown);
		click(Rejectoptiondd);
		Assertvalue(pendingtext, "Rejected");
		sendKeys(trrefno, refno);
		click(Fetchbtn);
		click(View_transaction);
		scrollUntilElementDisplayed(TransactionRefno);
		assertEquals(get_Attribute_value(TransactionRefno), refno);
		click(Clickcancel);
	}

	public void Switchtourlwindow() throws Exception {
		parentWindow = driver().getWindowHandle();
		Switch_Window_handling();
		AssertExist(Loginlogo);
	}

	public void Stoppaymentrequest() {
		waitForPage();
		click(StopChequeRequest);
	}

	public void approval_GenerateAdvice() throws Exception {
		approval();
		click(filterbtn);
		selectTransactionStatus("Completed");
		user_id.clear();
		sendKeys(trrefno, refno);
		click(Fetchbtn);
		click(Generateadvice);
		Asserttext(adive_verify, "Advice Name");
		click(generateadvice_Close);
	}

	public void SwitchtoSubwindow() {
		String current = driver().getWindowHandle();
		new WebDriverWait(driver(), Duration.ofSeconds(10)).until(ExpectedConditions.numberOfWindowsToBe(2));
		for (String handle : driver().getWindowHandles())
			if (!handle.equals(current)) {
				driver().switchTo().window(handle);
				break;
			}
	}

	public void click_on_MAQ_and_chooseAssert(WebElement maq, String title, String value) throws Exception {
		click(maq);
		switchto_Subscreen_Frame(title);
		waitForVisibility(txt_Search_Sub_Screen, 30);
		txt_Search_Sub_Screen.clear();
		txt_Search_Sub_Screen.sendKeys(value);
		jsclick(fetch_Search_Sub_Screen);
		Asserttext(Assertion_NoRecordFound, "No Records Found for the Specified Criteria");
		click(close_button_NoRecordFound);
	}

	public void click_on_MAQ_and_chooseAssert_errorMsg(WebElement maq, String title, String value) throws Exception {
		click(maq);
		switchto_Subscreen_Frame(title);
		waitForVisibility(txt_Search_Sub_Screen, 30);
		txt_Search_Sub_Screen.clear();
		txt_Search_Sub_Screen.sendKeys(value);
		jsclick(fetch_Search_Sub_Screen);
		Asserttext(error_in_MAQ, "No Records Found for the Specified Criteria");
	}

	public void clickSaveButton_ErrorMsg(String errorMessage) throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common);
		Click(Save_btn);
		switchto_Alert_Frame("Error Message");
		List<WebElement> errors = driver().findElements(By.className("SPNtbltwoC"));
		boolean found = errors.stream().map(WebElement::getText).map(String::trim)
				.anyMatch(text -> text.contains(errorMessage));
		Assert.assertTrue(found, "Error message does not exist: " + errorMessage);
	}

	public void ExitFrame() throws Exception {
		driver().switchTo().defaultContent();
		switchToFrame(Mainframe_common1);
		jsclick(Exit);
	}

	public void click_on_MAQ_and_chooseDescription(WebElement maq, String title, String value) throws Exception {
		click(maq);
		switchto_Subscreen_Frame(title);
		chooseLovValue(txt_Search_Sub_ScreenDescription, fetch_Search_Sub_Screen, title, value, true);
	}

	private boolean isDisplayed(WebElement webElement) {
		try {
			return webElement != null && webElement.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
