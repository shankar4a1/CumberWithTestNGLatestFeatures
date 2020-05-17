package com.demo.businessPages;

import com.demo.framework.utility.ReusableLibrary;
import com.demo.framework.utility.ConfigurationReader;
import com.demo.framework.utility.Log;
import io.cucumber.java.Scenario;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.apache.log4j.Logger;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends ReusableLibrary {

    private WebDriver driver;
    private ConfigurationReader configReaderObj;
    private Logger log = Log.getLogger(HomePage.class);


    /*@FindBy(how = How.XPATH, using = "//button[@class='homepage-hero__search-button']")
    private WebElement searchButton;*/

    @FindBy(xpath = "//button[@class='homepage-hero__search-button']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@id='framework_q']")
    private WebElement enterFrameworkDetails;

    public HomePage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 30);

    }

    public void homePage1() {
        WebElement homeLogo = driver.findElement(By.xpath("//a[@class='logo']//img"));
        if (homeLogo.isDisplayed()) {
            waitForSeconds(2);
            String homeLogoText = homeLogo.getAttribute("alt");
            Assert.assertTrue(homeLogoText.contains("CCS homepage"));
            log.info("User is on CCS home page");
            scenario.write("User is on CCS home page");
        } else {
            log.info("User is not on CCS home page");
            scenario.write("User is not on CCS home page");
        }
    }

    public void enterFrameworkDetails(String framework) {
        waitForSeconds(1);
        enterText(enterFrameworkDetails, framework);
        waitForSeconds(2);
        searchButton.click();
        //clickButton("search");
    }

}
