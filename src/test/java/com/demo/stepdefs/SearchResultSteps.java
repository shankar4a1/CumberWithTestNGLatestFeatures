package com.demo.stepdefs;

import com.demo.businessPages.HomePage;
import com.demo.businessPages.SearchResultPage;
import com.demo.context.TestContext;
import com.demo.framework.utility.Log;
import com.demo.framework.utility.PageObjectManager;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class SearchResultSteps {
    private Logger log = Log.getLogger(HomeSteps.class);
    private WebDriver driver;
    private PageObjectManager objectManager;
    private TestContext testContextObj;
    private Scenario scenario;
    private SearchResultPage searchResultPageObj;

    public SearchResultSteps(TestContext testContextObj) {
        this.testContextObj = testContextObj;
        driver = testContextObj.getDriver();
        objectManager = testContextObj.getObjectManager();
    }
    @Then("User sees valid search result count")
    public void userSeesValidSearchResultCount() {
        searchResultPageObj = objectManager.getSearchResultPageObj();
        searchResultPageObj.verifyResultCount();

    }
}
