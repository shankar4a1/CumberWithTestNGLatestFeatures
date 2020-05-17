package com.demo.businessPages;

import com.demo.framework.utility.ReusableLibrary;
import com.demo.framework.utility.ConfigurationReader;
import com.demo.framework.utility.Log;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchFrameworkPage extends ReusableLibrary {
    private WebDriver driver;
    private ConfigurationReader configReaderObj;
    private Logger log = Log.getLogger(HomePage.class);


    @FindBy(xpath = "//h1[contains(text(),'Search frameworks')]")
    private WebElement enterFrameworkDetails;

    public SearchFrameworkPage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 30);
    }

    public void SearchFrameworkPage1() {
        WebElement searchFrameworksPage = driver.findElement(By.xpath("//h1[contains(text(),'Search frameworks')]"));
        if (searchFrameworksPage.isDisplayed()) {
            searchFrameworksPage.click();
            waitForSeconds(2);
            String searchFrameworkPageTitle = searchFrameworksPage.getText();
        //    Assert.assertTrue(searchFrameworkPageTitle.contains("Search frameworks"));
            log.info("User is on Search Frameworks page");
            scenario.write("User is on Search Frameworks page");
        } else {
            log.info("User is not on Search Frameworks  page");
            scenario.write("User is not on Search Frameworks  page");
        }
    }
}
