package com.demo.stepdefs;


import com.demo.context.TestContext;
import com.demo.businessPages.*;
import com.demo.datahandlers.SampleData;
import com.demo.framework.utility.Log;
import com.demo.framework.utility.PageObjectManager;

import com.google.common.collect.Iterables;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HomeSteps {
    private Logger log = Log.getLogger(HomeSteps.class);
    private WebDriver driver;
    private PageObjectManager objectManager;
    private TestContext testContextObj;
    private Scenario scenario;
    private HomePage homePageObj;

    public HomeSteps(TestContext testContextObj) {
        this.testContextObj = testContextObj;
        driver = testContextObj.getDriver();
        objectManager = testContextObj.getObjectManager();
    }
    @Then("User should be navigated to CCS home page")
    public void user_should_be_navigated_to_CCS_home_page() {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.homePage1();
        testContextObj.takeSnapShot();
    }

    @And("User is navigated to CCS home page")
    public void user_is_navigated_to_CCS_home_page() throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.homePage1();

    }

    @And("User enters \"([^\"]*)\" details")
    public void user_enters_details(String framework) throws MalformedURLException, InterruptedException {
        HomePage homePageObj = objectManager.getHomePageObj();
        homePageObj.enterFrameworkDetails(framework);
        testContextObj.takeSnapShot();

    }

    @And("User clicks on the \"([^\"]*)\" button")
    public void user_clicks_on_the_button(String testFieldName) throws InterruptedException{
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickButton(testFieldName);
    }

    @And("User selects \"([^\"]*)\" Option")
    public void user_selects_Option(String linkName) throws InterruptedException {
        homePageObj = objectManager.getHomePageObj();
        homePageObj.clickElement(linkName);

    }


    @Given("user logs in to the CCS application")
    public void user_logs_in_to_the_CCS_application(List<SampleData> sampleDataList) {

        SampleData  sampleData  = Iterables.getLast(sampleDataList);
        System.out.println(sampleData.getScenarioID());


    }



    @Given("User enters details")
    public void user_enters_details(List<SampleData> sampleDataList) {
        System.out.println(sampleDataList);
    }



}

