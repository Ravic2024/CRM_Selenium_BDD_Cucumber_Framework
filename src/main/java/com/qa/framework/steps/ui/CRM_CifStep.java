package com.qa.framework.steps.ui;

import com.qa.framework.pages.PageObjectManager;
import com.qa.framework.pages.ui.CRM_CIFPage;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.When;

public class CRM_CifStep {

    private final PageObjectManager pages;

    public CRM_CifStep(PageObjectManager pages) {
        this.pages = pages;
    }

    private CRM_CIFPage crmCif() {
        return pages.getCrmCifPage();
    }

    @When("Click Application Tool Bar")
    public void click_application_tool_bar() throws Exception {
        crmCif().clickAccountOpening();
    }

    @When("Enter the english name to be {string}")
    public void enterTheEnglishNameToBe(String englishNames) throws Exception {
        crmCif().enter_english_names(englishNames);
    }

    @When("Open Identification Type drop down")
    public void open_Identification_Type_DropDown() throws Exception {
        crmCif().open_Identification_Type_DropDown();
    }

    @When("Click on Tasks Icon In Tool Bar")
    public void click_on_tasks_icon_in_tool_bar() throws Exception {
        crmCif().clickTaskIconInToolBar();
    }

    @When("Click on Account Opening Link")
    public void click_on_account_opening_link() throws Exception {
        crmCif().clickAccountOpeningLink();
    }

    @When("Check if switched to the Creation Page")
    public void check_if_switched_to_the_creation_page() throws Exception {
        crmCif().verifyAccountOpeningPage();
    }

    @When("Choose Identification Type To Be {string}")
    public void choose_identification_type_to_be(String identificationType) throws Exception {
        crmCif().enter_IdentificationType(identificationType);
    }

    @When("Enter National id to be {string}")
    public void enter_national_id_to_be(String identificationNumber) throws Exception {
        crmCif().enter_Identificationnumber(identificationNumber);
    }

    @When("Choose Action Type To Be {string}")
    public void choose_action_type_to_be(String actionType) throws Exception {
        crmCif().enter_ActionType(actionType);
    }

    @When("Choose Representer App Relationship to be {string}")
    public void choose_representer_app_relationship_to_be(String relationship) throws Exception {
        crmCif().enter_representerapp(relationship);
    }

    @When("Click Next")
    public void click_next() throws Exception {
        crmCif().click_nxtbtn();
    }

    @When("Enter the arabic names to be {string}")
    public void enter_the_arabic_names_to_be(String arabicName) throws Exception {
        crmCif().enterArabicName(arabicName);
    }

    @When("Enter the english name to be {string} {string} {string} {string}")
    public void enter_the_english_name_to_be(
            String englishName,
            String firstName,
            String middleName,
            String lastName) throws Exception {

        crmCif().enterEnglishName(englishName, firstName, middleName, lastName);
    }

    @When("Enter Identification Expiry Date to be {string}")
    public void enter_identification_expiry_date_to_be(String expiryDate) throws Exception {
        crmCif().enterIdentificationExpiryDate(expiryDate);
    }

    @When("Choose Marital Status to be {string}")
    public void choose_marital_status_to_be(String maritalStatus) throws Exception {
        crmCif().chooseMaritalStatus(maritalStatus);
    }

    @When("Choose Educational Status to be {string}")
    public void choose_educational_status_to_be(String educationalStatus) throws Exception {
        crmCif().chooseEducationalStatus(educationalStatus);
    }

    @When("Enter Mobile Number to be {string}")
    public void enter_mobile_number_to_be(String mobileNumber) throws Exception {
        crmCif().enterMobileNumber(mobileNumber);
    }

    @When("Check No Email Check Box")
    public void check_no_email_check_box() throws Exception {
        crmCif().checkNoEmail();
    }

    @When("Answer Do You Need To Subscribe Phonecash to be {string}")
    public void answer_do_you_need_to_subscribe_phonecash_to_be(String phoneCash) throws Exception {
        crmCif().choosePhoneCash(phoneCash);
    }

    @When("Choose the customer Type to be {string}")
    public void choose_the_customer_type_to_be(String customerType) throws Exception {
        crmCif().chooseCustomerType(customerType);
    }

    @When("Enter First Official Address {string}")
    public void enter_first_official_address(String address) throws Exception {
        crmCif().enterOfficialAddress(address);
    }

    @When("Choose Residential Type to be {string}")
    public void choose_residential_type_to_be(String residentialType) throws Exception {
        crmCif().chooseResidentialType(residentialType);
    }

    @When("Choose {string} to Other Residence Country")
    public void choose_to_other_residence_country(String country) throws Exception {
        crmCif().chooseOtherResidenceCountry(country);
    }

    @When("Choose {string} to Accommodation")
    public void choose_to_accommodation(String accommodation) throws Exception {
        crmCif().chooseAccommodation(accommodation);
    }

    @When("Choose {string} To Mailing Country")
    public void choose_to_mailing_country(String mailingCountry) throws Exception {
        crmCif().chooseMailingCountry(mailingCountry);
    }

    @When("Choose {string} To account operated via a power of attorney")
    public void choose_to_account_operated_via_a_power_of_attorney(String powerOfAttorney) throws Exception {
        crmCif().choosePowerOfAttorney(powerOfAttorney);
    }

    @When("Choose {string} Do You Have Other Accounts or Cards in Other Banks")
    public void choose_do_you_have_other_accounts_or_cards_in_other_banks(String cards) throws Exception {
        crmCif().chooseOtherAccountsCards(cards);
    }

    @When("Choose Job to be {string}")
    public void choose_job_to_be(String job) throws Exception {
        crmCif().chooseJob(job);
    }

    @When("Choose Job Grade to be {string}")
    public void choose_job_grade_to_be(String jobGrade) throws Exception {
        crmCif().chooseJobGrade(jobGrade);
    }

    @When("Choose Business Sector to be {string}")
    public void choose_business_sector_to_be(String businessSector) throws Exception {
        crmCif().chooseBusinessSector(businessSector);
    }

    @When("Choose Annual Income to be {string}")
    public void choose_annual_income_to_be(String annualIncome) throws Exception {
        crmCif().chooseAnnualIncome(annualIncome);
    }

    @When("Choose Income Sources to be {string}")
    public void choose_income_sources_to_be(String incomeSource) throws Exception {
        crmCif().chooseIncomeSources(incomeSource);
    }

    @When("Choose Account Opening Reason to be {string}")
    public void choose_account_opening_reason_to_be(String reason) throws Exception {
        crmCif().chooseAccountOpeningReason(reason);
    }

    @When("Choose Account Dealing Type to be {string}")
    public void choose_account_dealing_type_to_be(String dealingType) throws Exception {
        crmCif().chooseAccountDealingType(dealingType);
    }

    @When("Choose Account Dealing Amount to be {string}")
    public void choose_account_dealing_amount_to_be(String dealingAmount) throws Exception {
        crmCif().chooseAccountDealingAmount(dealingAmount);
    }

    @When("Choose Account Statement Periodicity to be {string}")
    public void choose_account_statement_periodicity_to_be(String periodicity) throws Exception {
        crmCif().chooseStatementPeriodicity(periodicity);
    }

    @When("Choose Account Category to be {string}")
    public void choose_account_category_to_be(String accountCategory) throws Exception {
        crmCif().chooseAccountCategory(accountCategory);
    }

    @When("Choose Account Class to be {string}")
    public void choose_account_class_to_be(String accountClass) throws Exception {
        crmCif().chooseAccountClass(accountClass);
    }

    @When("Choose Account Currency to be {string}")
    public void choose_account_currency_to_be(String accountCurrency) throws Exception {
        crmCif().chooseAccountCurrency(accountCurrency);
    }

    @When("Enter Debit Card Empposing Name to be {string}")
    public void enter_debit_card_empposing_name_to_be(String debitCardName) throws Exception {
        crmCif().enterDebitCardEmposingName(debitCardName);
    }

    @When("Choose Additional Information to be {string}")
    public void choose_additional_information_to_be(String additionalInformation) throws Exception {
        crmCif().chooseAdditionalInformation(additionalInformation);
    }

    @When("Choose Do You Want to Open Another Account to be {string}")
    public void choose_do_you_want_to_open_another_account_to_be(String answer) throws Exception {
        crmCif().chooseOpenAnotherAccount(answer);
    }

    @When("Click Finish")
    public void click_finish() throws Exception {
        crmCif().clickFinish();
    }

    @When("Click Yes to Popup window")
    public void click_yes_to_popup_window() throws Exception {
        crmCif().acceptAlert();
    }

    @When("Store and Click on Application number")
    public void store_and_click_on_application_number() throws Exception {
        crmCif().storeApplicationNumber();
        crmCif().clickApplicationNumber();
    }

    @When("Validate Routing To The Correct Page")
    public void validate_routing_to_the_correct_page() throws Exception {
        crmCif().validateRoutingCorrectPage();
    }

    @When("Click on Primary Account Flag")
    public void click_on_primary_account_flag() throws Exception {
        crmCif().ClickOnPrimaryAccountFlag();
    }

    @When("Click on Linked To Debit Card")
    public void click_on_linked_to_debit_card() throws Exception {
        crmCif().ClickOnLinkedToDebitCard();
    }

    @When("Validate that the Current Application status To Be {string}")
    public void validate_that_the_current_application_status_to_be(String applicationStatus) throws Exception {
        crmCif().validateCurrentApplication(applicationStatus);
    }

    @When("Change Application Status To Be {string}")
    public void change_application_status_to_be(String applicationStatus) throws Exception {
        crmCif().changeCurrentApplicationStatus(applicationStatus);
    }


    @When("Logout from CRM Application")
    public void logout_from_crm_application() throws Exception {
        throw pending("Logout from CRM Application");
    }

    @When("Click on Appliction Icon From Home Screen")
    public void click_on_appliction_icon_from_home_screen() throws Exception {
        throw pending("Click on Appliction Icon From Home Screen");
    }

    @When("Enter Application Number for search")
    public void enter_application_number_for_search() throws Exception {
        throw pending("Enter Application Number for search");
    }

    @When("Click Go To Start Search")
    public void click_go_to_start_search() throws Exception {
        throw pending("Click Go To Start Search");
    }

    @When("Click on Acquire Application")
    public void click_on_acquire_application() throws Exception {
        throw pending("Click on Acquire Application");
    }

    @When("Click Create CIF")
    public void click_create_cif() throws Exception {
        throw pending("Click Create CIF");
    }

    @When("Click Open Account")
    public void click_open_account() throws Exception {
        throw pending("Click Open Account");
    }

    @When("Click Issue Debit Card")
    public void click_issue_debit_card() throws Exception {
        throw pending("Click Issue Debit Card");
    }

    @When("Validate that Account number has been generated")
    public void validate_that_account_number_has_been_generated() throws Exception {
        throw pending("Validate that Account number has been generated");
    }

    @When("Validate that Base number has been generated")
    public void validate_that_base_number_has_been_generated() throws Exception {
        throw pending("Validate that Base number has been generated");
    }

    private PendingException pending(String stepName) {
        return new PendingException("Step implementation is pending: " + stepName);
    }
}
