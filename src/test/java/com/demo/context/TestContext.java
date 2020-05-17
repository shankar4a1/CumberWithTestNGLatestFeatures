package com.demo.context;


import com.assertthat.selenium_shutterbug.core.PageSnapshot;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.assertthat.selenium_shutterbug.utils.web.ScrollStrategy;
import com.demo.framework.utility.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.MalformedURLException;
import java.util.List;


public class TestContext {
    private Logger log = Log.getLogger(TestContext.class);

    private BrowserFactory browserFactory;
    private PageObjectManager objectManager;
    public ScenarioContext scenarioContext;
    private ConfigurationReader configReader;


    private WebDriver driver;

    public Scenario scenario;

    private JSONUtility jsonUtilityObj;

    public String allPageScreenshotFlag;
    public SoftAssert softAssertion;




    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
         log.info("=================" + scenario.getName() + " execution starts" + "===================");
         this.scenario = scenario;
       // jsonUtilityObj = new JSONUtility();
        scenarioContext = new ScenarioContext();
        configReader = new ConfigurationReader();
        allPageScreenshotFlag = configReader.get("allPageScreenshot");
        browserFactory = new BrowserFactory();
        browserFactory.initiateDriver(configReader.getBrowserName());
        driver = browserFactory.getDriver();
        objectManager = new PageObjectManager(driver, scenario);

        softAssertion = new SoftAssert();
        long threadId = Thread.currentThread().getId();
        String processName = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println("Started in thread: " + threadId + ", in JVM: " + processName);
        log.info("Successfully lunched the chrome browser");
    }

    @Given("User open a chrome browser")
    public void user_open_a_chrome_browser() {
        log.info("Successfully lunched the chrome browser");
    }

    @When("User enter ccs url")
    public void user_enter_ccs_url() {
       // objectManager = new PageObjectManager(driver,scenario);
        browserFactory.launchURL("appWelcomeURL");
    }

    @Given("user logs in to the CCS application for \"([^\"]*)\"$")
    public void user_logs_in_to_the_CCS_application_for(String ScenarioID) throws MalformedURLException, InterruptedException {
        scenarioContext.setKeyValue("ScenarioID",ScenarioID);
        objectManager = new PageObjectManager(driver, scenario);
        browserFactory.launchURL("appWelcomeURL");
        scenario.write("CCS application is launched");
    }

    @After
    public void cleanUp() throws Exception {
        softAssertion.assertAll();
        if(configReader.get("browserName").equalsIgnoreCase("chrome_profile")||configReader.get("browserName").equalsIgnoreCase("CHROME_HEADLESS"))
        {browserFactory.deleteDirectory();}
        takeSnapShot();

        log.info("=================" + scenario.getName() + " execution ends" + "===================");
//      eyes.closeAsync();
        if (driver != null) {
            driver.quit();
            driver = null;
        }

        if (jsonUtilityObj != null) {
            jsonUtilityObj = null;
        }

        if (scenarioContext != null) {
            scenarioContext.clearContext();
        }

//      eyes.abortIfNotClosed();
    }

    public PageObjectManager getObjectManager() {
        return objectManager;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void takeSnapShot() {
        //Code to take full page screenshot
        ByteArrayOutputStream imageStream = new ByteArrayOutputStream();
        scenario.write("URL - "+driver.getCurrentUrl());
        PageSnapshot snapshot = Shutterbug.shootPage(driver, ScrollStrategy.BOTH_DIRECTIONS, true);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,0)");

        try {
            ImageIO.write(snapshot.getImage(), "png", imageStream);
            imageStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] source = imageStream.toByteArray();
        scenario.embed(source, "image/png");
    }

    public JSONUtility getJsonUtilityObj() {
        return jsonUtilityObj;
    }


}
