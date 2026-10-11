@CRM_POC_APPs_Results
Feature: CRM Applications POC

  @APP_Create_CIF_Open_Account_High
  Scenario Outline: Create CIF Open Account Apllication
    Given Open CRM webpage
    When Enter the "<Maker_ID>" and Password
    And Click the login button to enter maker home page
    And Click Application Tool Bar
    And Click on Tasks Icon In Tool Bar
    And Click on Account Opening Link
    And Check if switched to the Creation Page
    And Open Identification Type drop down
    And Choose Identification Type To Be "<Identification Type>"
    And Enter National id to be "<NID>"
    And Choose Action Type To Be "<action Type>"
    And Choose Representer App Relationship to be "<Relationship>"
    And Click Next
    And Check if switched to the Creation Page
    And Enter the arabic names to be "<arabic_name>"
    And Enter the english name to be "<English name>"
    And Enter Identification Expiry Date to be "<Identification Expiry Date>"
    And Choose Marital Status to be "<Marital Status>"
    And Choose Educational Status to be "<Educational Status>"
    And Enter Mobile Number to be "<Mobile Number>"
    And Check No Email Check Box
    And Answer Do You Need To Subscribe Phonecash to be "<PhoneCash>"
    And Choose the customer Type to be "<customer Type>"
    And Click Next
    And Check if switched to the Creation Page
    And Enter First Official Address "<firstAddress>"
    And Choose Residential Type to be "<Residential Type>"
    And Choose "<otherResidenceChoice>" to Other Residence Country
    And Choose "<accommodationChoice>" to Accommodation
    And Choose "<Mailing Country>" To Mailing Country
    And Choose "<attorney Status>" To account operated via a power of attorney
    And Choose "<otherAccStatus>" Do You Have Other Accounts or Cards in Other Banks
    And Choose Job to be "<Job>"
    And Choose Job Grade to be "<jobGrade>"
    And Choose Business Sector to be "<busSec>"
    And Choose Annual Income to be "<AnnIncom>"
    And Choose Income Sources to be "<incomSrc>"
    And Choose Account Opening Reason to be "<otherRsn>"
    And Choose Account Dealing Type to be "<dealingType>"
    And Choose Account Dealing Amount to be "<dealingAmount>"
    And Choose Account Statement Periodicity to be "<Statement Periodicity>"
    And Click Next
    And Check if switched to the Creation Page
    And Choose Account Category to be "<Account category>"
    And Choose Account Class to be "<Account Class>"
    And Choose Account Currency to be "<Currency>"
    And Enter Debit Card Empposing Name to be "<English name>"
    And Choose Additional Information to be "<Add_Info>"
    And Choose Do You Want to Open Another Account to be "<other account>"
    And Click Next
    And Check if switched to the Creation Page
    And Click Finish
    And Click Yes to Popup window
    And Store and Click on Application number
    #Then Write Data from excel for islamic loan "<Sheetname1>" "<columnname1>" "<excelname1>"
    And Validate Routing To The Correct Page
    And Click on Primary Account Flag
    And Click on Linked To Debit Card
    And Validate that the Current Application status To Be "<CurrentStatus>"
    And Change Application Status To Be "<RequiredStatus>"
    And Logout from CRM Application
    When Enter the "<COMPLAINCEID>" and Password
    And Click the login button to enter maker home page
    And Click on Appliction Icon From Home Screen
    And Enter Application Number for search
    And Click Go To Start Search
    And Store and Click on Application number
    And Click on Acquire Application
    And Change Application Status To Be "Branch Checker"
    And Logout from CRM Application
    When Enter the "<CHECKERID>" and Password
    And Click on Appliction Icon From Home Screen
    And Enter Application Number for search
    And Click Go To Start Search
    And Store and Click on Application number
    And Click on Acquire Application
    And Click Create CIF
    And Click Open Account
    And Click Issue Debit Card
    And Validate that Account number has been generated
    And Validate that Base number has been generated
    And Change Application Status To Be "Account Opening Maker"
    And Logout from CRM Application

    Examples: 
      | Maker_ID     | Password      | Identification Type | NID            | Account category | Account Class | Add_Info                                      | Currency | action Type               | arabic_name | English name       | Identification Expiry Date | dealingType   | Marital Status | otherRsn | Educational Status | Mobile Number | PhoneCash | customer Type | firstAddress | Residential Type | accommodationChoice | attorney Status | jobGrade   | otherAccStatus | Job   | otherResidenceChoice | busSec | Relationship | AnnIncom             | Mailing Country | incomSrc | dealingAmount        | Statement Periodicity | other account | CurrentStatus    | RequiredStatus    | COMPLAINCEID         | CHECKERID     | First name | Middle name | Last name | Sheetname1 | columnname1        | excelname1   |
      | CSOFFICER990 | CS@@super2026 | National ID         | 30001111100374| توفير            | SA04          | خدمة فتح حساب بنفس عملة التحويل الوارد للعميل | EGP      | Create CIF & Open Account | محمد        | Mohmed Mohmed Mohm | 1/7/2027                   | نقداً وبشيكات | Single         | أخرى     | Graduate           |   01235613228 | NO        | RETAIL        | مصر          | Resident         | عن طريق العمل       | NO              | ادارة وسطى | NO             | محاسب | NO                   | بنوك   | Customer     | أكثر من 1,000,000 جم | Egypt           | راتب     | From 20000 To 200000 | Monthly               | NO            | Data Entry Maker | Branch Compliance | BRANCHCOMPLIANCE0001 | CSSUPERVISOR1 | Mohmed     | Mohmed      | Mohm      | CRM        | Application_Number | TestData_CRM |
