package com.demo.businessPages;

import com.demo.framework.utility.ConfigurationReader;
import com.demo.framework.utility.Log;
import com.demo.framework.utility.ReusableLibrary;
import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SearchResultPage extends ReusableLibrary {
    private WebDriver driver;
    private ConfigurationReader configReaderObj;
    private Logger log = Log.getLogger(SearchResultPage.class);

    @FindBy(id = "js-result-count")
    private WebElement searchResultCount;

    public SearchResultPage(WebDriver driver, Scenario scenario) {
        this.driver = driver;
        this.scenario = scenario;
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(this.driver, 30);

    }

    public void verifyResultCount() {
        String resultText = getElementText(searchResultCount);
        int resultCount = Integer.parseInt(resultText.split(" ")[0]);
        Assert.assertTrue(resultCount>0);
    }
}
