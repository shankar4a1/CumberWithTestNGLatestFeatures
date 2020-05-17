package com.demo.businessPages;

import com.demo.framework.utility.ReusableLibrary;
import com.demo.framework.utility.ConfigurationReader;
import com.demo.framework.utility.Log;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GMLandingPage extends ReusableLibrary {
    private WebDriver driver;
    private ConfigurationReader configReaderObj;
    private Logger log = Log.getLogger(HomePage.class);


    @FindBy(xpath = "//input[@id='framework_q']")
    private WebElement enterFrameworkDetails;

    @FindBy(xpath = "//a[@class='govuk-breadcrumbs__link'][contains(text(),'Search frameworks')]")
    private WebElement searchFrameworksCta;

    public GMLandingPage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 30);
    }

    public void gmPage() {
        WebElement gmPage = driver.findElement(By.xpath("//li[contains(text(),'Guided match')]"));
        if (gmPage.isDisplayed()) {
            gmPage.click();
            waitForSeconds(2);
            String gmPageTest = gmPage.getText();
            Assert.assertTrue(gmPageTest.contains("Guided match"));
            log.info("User is on Guided match landing page");
            scenario.write("User is on Guided match landing page");
        } else {
            log.info("User is not on Guided match landing page");
            scenario.write("User is not on Guided match landing page");
        }
    }

    public void clickSearchFrameworksCta() {
        this.clickElement(searchFrameworksCta);
    }

}
