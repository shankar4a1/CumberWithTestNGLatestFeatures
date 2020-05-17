package com.demo.TestRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        strict = false,
        features = {"@rerun/failed_scenarios.txt"},
        glue={"com.demo.stepdefs", "com.demo.context"},
        monochrome = true,
        plugin = { "pretty", "html:target/cucumber-html-reports", "json:target/cucumber-html-reports/cucumber.json"}

        )

public class FailedRun extends AbstractTestNGCucumberTests{

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
