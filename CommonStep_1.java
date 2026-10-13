package com.qa.framework.steps.ui;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.framework.engine.DriverManager;
import com.qa.framework.engine.GlobalParams;
import com.qa.framework.pages.ui.CommonFunction;
import com.qa.framework.pages.ui.filemodify;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class CommonStep extends CommonFunction {

    private final Logger log = LogManager.getLogger(CommonStep.class);

    private void initializeWebDriverAndOpenUrl(String urlPropertyKey) {
        DriverManager.initializeDriver("WEB");
        DriverManager.getDriver().get(GlobalParams.getProperty(urlPropertyKey));
        log.info("Opened application URL configured under property: {}", urlPropertyKey);
    }

    private String getRequiredProperty(String propertyKey) {
        return GlobalParams.getProperty(propertyKey);
    }

    @Given("Open the login page")
    public void open_the_login_page() throws Exception {
        initializeWebDriverAndOpenUrl("UATURL");
    }

    @Given("Open CRM webpage")
    public void openCRMWebpage() throws Exception {
        initializeWebDriverAndOpenUrl("CRM_Pre_Prod");
    }

    @Given("Open The Login Page")
    public void Open_the_login_page() throws Exception {
        initializeWebDriverAndOpenUrl("OBCL_Syndication_URL");
    }

    @When("Enter the {string} and Password for reset")
    public void PwdReset(String username) throws Exception {
        userName(username)
                .passWord(getRequiredProperty("Maker_password"))
                .resetLogin();
    }

    @When("Enter the {string} and Password for reset New")
    public void PwdResetNew(String username) throws Exception {
        userName_New(username)
                .passWord_New(getRequiredProperty("MakerNew_password"))
                .resetLogin_new();
    }

    @Given("I update the Extent HTML report {string}")
    public void i_update_the_extent_html_report_after_execution(String filePath)
            throws IOException {
        filemodify.endmethod(filePath);
        log.info("Report modification completed successfully: {}", filePath);
    }

    @When("Enter the {string} and New Password")
    public void enterTheAndMakerNewPassword(String username) throws Exception {
        userName(username)
                .passWord(getRequiredProperty("Branch_Maker_password"));
        log.info("Username entered: {}", username);
    }

    @When("Enter the {string} and New Password in new window")
    public void enter_TheAndMakerNewPassword(String username) throws Exception {
        userName_New(username)
                .passWord_New(getRequiredProperty("Maker_New_password"));
        log.info("Username entered in the new window: {}", username);
    }

    @When("Click the new button {string}")
    public void clickTheNewLink(String pageTitle) throws Exception {
        click_New(pageTitle);
    }

    @When("Click the New Button {string}")
    public void click_TheNewLink(String pageTitle) throws Exception {
        clickNew(pageTitle);
    }

    @When("Click the login button to enter maker home page with New password")
    public void clickTheLoginButtonToEnterMakerHomePagewithNewpassword()
            throws Exception {
        click_loginbutton(
                getRequiredProperty("Maker_Newpassword"));
    }

    @When("Click the login button to enter maker home page with New password in new window")
    public void clickTheLoginButton_ToEnterMakerHomePagewithNewpassword()
            throws Exception {
        clickloginbutton(
                getRequiredProperty("Maker_New_password"));
    }

    @When("Click the login button to enter Checker home page with New password in new window")
    public void clickTheLoginButton_ToEnterCheckerHomePagewithNewpassword()
            throws Exception {
        clickloginbutton(
                getRequiredProperty("Maker_New_password"));
    }

    @When("Click the login button to enter checker home page with New password")
    public void clickTheLoginButtonToEnterCheckerHomePagewithNewpassword()
            throws Exception {
        click_loginbutton(
                getRequiredProperty("Maker_Newpassword"));
    }

    @When("Fetch the TD Branch Code")
    public void fetch_the_branch_code() throws Exception {
        Branchcode();
    }

    @When("Enter the {string} and Password")
    public void enterTheAndMakerPassword(String username) throws Exception {
        userName(username)
                .passWord(getRequiredProperty("Branch_Maker_password"));
        log.info("Username entered: {}", username);
    }

    @When("Enter the {string} and NewPassword")
    public void enterTheAndMakerNewpassword(String username) throws Exception {
        userName(username)
                .passWord(getRequiredProperty("Maker_Newpassword"));
        log.info("Username entered: {}", username);
    }

    @When("Click the login button to enter maker home page withNewpassword")
    public void clickTheLoginButtonToEnterMakerHomePagewithnewpassword()
            throws Exception {
        click_loginbutton(
                getRequiredProperty("Maker_Newpassword"));
    }

    @When("Enter the {string} and {string}")
    public void enterTheAndMakerPassword(String username, String password)
            throws Exception {
        userName(username)
                .passWord(password);
        log.info("Username entered: {}", username);
    }

    @When("Click the login button to enter checker home page")
    public void clickTheLoginButtonToEnterCheckerHomePage() throws Exception {
        click_loginbutton(
                getRequiredProperty("Maker_password"));
    }

    @When("Click the login button to enter maker home page")
    public void clickTheLoginButtonToEnterMakerHomePage() throws Exception {
        click_loginbutton(
                getRequiredProperty("Branch_Maker_password"));
    }

    @When("Click the login button to enter maker home page {string}")
    public void clickTheLoginButtonToEnterMakerHomePage(String password)
            throws Exception {
        click_loginbutton(password);
    }

    @When("Search the screenid {string} in home page")
    public void search_the_in_home_page(String value) throws Exception {
        Enter_ScreenID(value);
    }

    @When("Search the screenid {string} in homepage")
    public void search_the_in_homepage(String value) throws Exception {
        Enter_Screen_ID(value);
    }

    @When("Change the branch to home branch")
    public void changeTheBranchToHomeBranch() throws Exception {
        HomeBranch();
    }

    @When("Select the Sub branch code {string}")
    public void selectTheSubBranchCode(String branch) throws Exception {
        Branch(branch);
    }

    @When("Click Save button in Mainscreen")
    public void Click_Save_button_in_Mainscreen() throws Exception {
        clickSaveButton();
    }

    @When("Click Save and verify ErrorMessage {string}")
    public void Click_Save_button_in_Mainscreen(String errorMessage)
            throws Exception {
        clickSaveButton_ErrorMsg(errorMessage);
    }

    @When("Click Save button in the Mainscreen")
    public void Click_Save_button_in_the_Mainscreen() throws Exception {
        click_Save_Button();
    }

    @When("Click Save button in Mainscreen New")
    public void Click_Save_button_in_Mainscreen_new() throws Exception {
        clickSaveButton_new();
    }

    @When("Exit from the main page")
    public void Exit_from_the_main_page() throws Exception {
        Exit();
    }

    @When("Click EnterQuery button {string}")
    public void clickTheTDEnterQueryButton(String pageTitle) throws Exception {
        Click_Query(pageTitle);
    }

    @When("Click Enter Query button {string}")
    public void clickTheEnterQueryButton(String pageTitle) throws Exception {
        ClickEnterQuery(pageTitle);
    }

    @When("Logout from the User")
    public void Logout_from_the_User() throws Exception {
        Logout();
    }

    @When("Logout from the Current User")
    public void Logoutfrom_the_User() throws Exception {
        Log_out();
    }

    @When("Select Transaction Branch Code")
    public void selectThe_Transaction_BranchCode() throws Exception {
        Branchcode();
    }

    @When("Click the EnterQuery button {string}")
    public void ClicktheEnterQuerybutton(String pageTitle) throws Exception {
        Click_Query(pageTitle);
    }

    @When("Exit from the payment main page")
    public void Exit_from_the_Main_page() throws Exception {
        Exitpage();
    }

    @When("Exit from the Main page")
    public void Exit_from_the_main_Page() throws Exception {
        Exit1();
    }

    @When("Select the branch code {string} to Switch")
    public void selectTheBranchCode(String branch) throws Exception {
        Branch_Switch(branch);
    }

    @When("Exit from main page")
    public void Exit_from_main_page() throws Exception {
        ExitFrame();
    }

    @When("Click the login button to enter checker home page withNewpassword")
    public void ClicktheloginbuttontoentercheckerhomepagewithNewpassword()
            throws Exception {
        click_loginbutton(
                getRequiredProperty("Maker_Newpassword"));
    }
}
