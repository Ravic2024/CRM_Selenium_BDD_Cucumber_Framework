package com.qa.framework;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.qa.framework.engine.GlobalParams;
import com.qa.framework.pages.ui.filemodify;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features/CRM"},
        glue = {"com.qa.framework.steps"},
        plugin = {
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "com.qa.framework.engine.FailedScenarioLogger",
                "json:target/cucumber-results/cucumber.json"
        },
        tags = "@APP_Create_CIF_Open_Account_High",
        dryRun = false,
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = false
)
public class TestRunner extends AbstractTestNGCucumberTests {

    private final Logger log = LogManager.getLogger(TestRunner.class);

    @BeforeClass(alwaysRun = true)
    @Override
    public void setUpClass(ITestContext context) {
        GlobalParams.initializeGlobalParams();
        super.setUpClass(context);
    }

    @DataProvider(parallel = true)
    @Override
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @AfterSuite(alwaysRun = true)
    public void endmethod() throws IOException {
        filemodify.endmethod(null);
        log.info("Extent HTML report updated successfully.");
    }
}
