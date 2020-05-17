package com.demo.stepdefs;

import com.demo.businessPages.SearchFrameworkPage;
import com.demo.context.TestContext;
import com.demo.framework.utility.Log;
import com.demo.framework.utility.PageObjectManager;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class SearchFrameworksPageSteps {
    private Logger log = Log.getLogger(SearchFrameworksPageSteps.class);
    private WebDriver driver;
    private PageObjectManager objectManager;
    private TestContext testContextObj;
    private Scenario scenario;
    private SearchFrameworkPage searchFrameworksPageObj;


    public SearchFrameworksPageSteps(TestContext testContextObj) {
        this.testContextObj = testContextObj;
        driver = testContextObj.getDriver();
        objectManager = testContextObj.getObjectManager();
    }

    @Then("User should be redirected to Search Framework page")
    public void user_should_be_redirected_to_search_framework_page() {
        searchFrameworksPageObj = objectManager.getSearchFrameworkPageObj();
        searchFrameworksPageObj.SearchFrameworkPage1();
        testContextObj.takeSnapShot();
    }
}
