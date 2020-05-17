package com.demo.framework.utility;

import com.demo.businessPages.*;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	private WebDriver driver;
	private Scenario scenario;


	//Page objects
	private HomePage homePageObj;
	private GMLandingPage gmLandingPage;
	private SearchFrameworkPage searchFrameworkPage;


	public PageObjectManager(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
	}

	public HomePage getHomePageObj() {
		return homePageObj == null ? homePageObj = new HomePage(driver,scenario) : homePageObj;
	}

	public GMLandingPage getGmLandingPageObj() {
		return gmLandingPage == null ? gmLandingPage = new GMLandingPage(driver,scenario) : gmLandingPage;
	}

	public SearchFrameworkPage getSearchFrameworkPageObj() {
		return searchFrameworkPage == null ? searchFrameworkPage = new SearchFrameworkPage(driver, scenario) : searchFrameworkPage;
	}


}
