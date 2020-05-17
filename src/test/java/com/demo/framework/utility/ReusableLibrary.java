package com.demo.framework.utility;

import io.cucumber.java.Scenario;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableLibrary {
	 private Logger log = Log.getLogger(ReusableLibrary.class);
	    protected Scenario scenario;
	    protected WebDriver driver;
	    protected WebDriverWait wait;
	    private static String otp = "";



	public void clickElement(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.click();
	    }

	    public void enterText(WebElement element, String text) {
	        waitForSeconds(1);
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        element.sendKeys(text);
	        element.sendKeys(Keys.TAB);
	    }



	    public String getAttributeValue(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element));
	        return element.getAttribute("value");
	    }







	    public void waitForLoad() {
	        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	        WebDriverWait wait = new WebDriverWait(driver, 30);
	        wait.until(pageLoadCondition);
	    }

	    public void clickElement(String elementName) {
	        String XPATH = ".//*[contains(text(),'" + elementName + "')]";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
	        element.click();
	        log.info("Clicked on " + elementName + " element");
	        scenario.write("User Clicked on " + elementName + " option");

	    }

	    public void clickButton(String buttonName) {
	        waitForSeconds(2);
	        String XPATH = "//button[contains(text(),'" + buttonName + "')]";
	        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(XPATH)));
//	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
//	        waitForSeconds(1);
	        element.click();
	        log.info("Clicked on " + buttonName + " button");
	        scenario.write(" User Clicked on " + buttonName + " button");
	    }





	    public void waitForSeconds(int seconds) {
	        try {
	            Thread.sleep(seconds * 1000);
	        } catch (Exception e) {
				e.printStackTrace();

	        }
	    }






}