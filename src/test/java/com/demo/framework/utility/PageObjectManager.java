package com.demo.framework.utility;

import com.demo.businessPages.*;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

public class PageObjectManager {
	private WebDriver driver;
	private Scenario scenario;


	//Page objects
	private HomePage homePageObj;
	private SearchResultPage searchResultPage;


	public PageObjectManager(WebDriver driver, Scenario scenario) {
		this.driver = driver;
		this.scenario = scenario;
	}

	public HomePage getHomePageObj() {
		return homePageObj == null ? homePageObj = new HomePage(driver,scenario) : homePageObj;
	}

	public SearchResultPage getSearchResultPageObj() {
		return searchResultPage == null ? searchResultPage = new SearchResultPage(driver, scenario) : searchResultPage;
	}

}
