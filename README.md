# CucumberWithTestNGLatestFeatures
CucumberWithTestNGLatestFeatures

Cucumber with DI(pico container) and TestNG

**_To Run on your own application:_**

Update appWelcomeURL in ApplicationConfig.properties
test -Dcucumber.filter.tags=@Test

**Frame work features:**

1. Cucumber Latest version with 5.5
2. TestNG
3. Dependency Injection using-PICO
4. Parallel execution
5. Cucumber report
6. Full screen shot for long pages
7. Log4j 
8. Supports cross browser
9. Table transformer
10. Retry failed scenarios with rerun plugin



**New Features will be added :**


1. TestNG-Soft assertions
2. Cloud - Sauce/Browser stack 
3. Integration with CSv/Excel
4. Logging customized messages
5. Retry failed scenarios with out re run plugin


**More Details:**
Migration From JUNIT to TestNG	
	Cucumber can be executed in parallel using TestNG and Maven test execution plugins by setting the dataprovider parallel option to true. In TestNG the scenarios and rows in a scenario outline are executed in multiple threads. One can use either Maven Surefire or Failsafe plugin for executing the runners.
		
		
		
		1.Update TestNG related dependencies
    
		  <dependency>
            <groupId>org.testng</groupId>
            <artifactId>testng</artifactId>
            <version>${testng.version}</version>
            <scope>test</scope>
        </dependency>
		<dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-testng</artifactId>
            <version>${cucumber.version}</version>
        </dependency>
		 <dependency>
            <groupId>org.apache.maven.surefire</groupId>
            <artifactId>surefire-testng</artifactId>
            <version>${maven.surefire.plugin.version}</version>
        </dependency>
        
		2.Update TEstNG runner file
		
		
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        strict = false,
        features = {"src/test/resources/FeatureFiles"},
        glue={"com.demo.stepdefs", "com.demo.context"},
        monochrome = true,
        plugin = { "pretty", "html:target/cucumber-html-reports", "json:target/cucumber-html-reports/cucumber.json"},

        tags = "@Table"
)






public class TestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
 
		3.Update POM.XML under maven to support parallel execution
		 <property>
                                    <name>dataproviderthreadcount</name>
                                    <value>${surefire.fork.count}</value>
                                </property>
