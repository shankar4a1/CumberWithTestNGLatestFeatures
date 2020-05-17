package com.demo.stepdefs;

import com.demo.businessPages.GMLandingPage;
import com.demo.context.TestContext;
import com.demo.framework.utility.Log;
import com.demo.framework.utility.PageObjectManager;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public class GMPageSteps {
    private Logger log = Log.getLogger(com.demo.stepdefs.GMPageSteps.class);
    private WebDriver driver;
    private PageObjectManager objectManager;
    private TestContext testContextObj;
    private Scenario scenario;
    private GMLandingPage gmLandingPage;

    public GMPageSteps(TestContext testContextObj) {
        this.testContextObj = testContextObj;
        driver = testContextObj.getDriver();
        objectManager = testContextObj.getObjectManager();
    }

    @Then("User is displayed with GM landing page")
    public void user_is_displayed_with_GM_landing_page() throws MalformedURLException, InterruptedException {
        gmLandingPage = objectManager.getGmLandingPageObj();
        gmLandingPage.gmPage();
    }

    @And("User clicks \"Search Frameworks\" CTA from the page")
    public void user_selects_second_found_element_from_page() {
        gmLandingPage = objectManager.getGmLandingPageObj();
        gmLandingPage.clickSearchFrameworksCta();
    }
}
