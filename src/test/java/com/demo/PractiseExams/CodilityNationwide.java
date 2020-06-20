package com.demo.PractiseExams;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class CodilityNationwide {

    WebDriver driver;


    @Before
    public void setup() {


        WebDriverManager.chromedriver().setup();
        //Uncomment below lines if you would like to run it in incognito mode
//                ChromeOptions option = new ChromeOptions();
//                option.addArguments("--incognito");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://codility-frontend-prod.s3.amazonaws.com/media/task_static/qa_csharp_login_page/9a83bda125cd7398f9f482a3d6d45ea4/static/attachments/reference_page.html");

    }

    private void assertWebElement(By by) {

        try{
            assertTrue(isExistsElement(by));
            System.out.println("Element is present  "+getText(by));
        }
        catch (AssertionError e){
            System.out.println("Element is not present");
            e.printStackTrace();

        }


    }


    private boolean isExistsElement(By by) {
        boolean isElementExists = true;
        try {
            driver.findElement(by);
        } catch (NoSuchElementException e) {
            return false;
        }
        return isElementExists;


    }

    private String getText(By by) {

        return driver.findElement(by).getText();


    }



    @After
    public void teadown(){
        driver.quit();
    }
}
