package com.demo.PractiseExams;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.*;

public class CodilityTest {
    WebDriver driver;

    By byEmailIDInputWebElement = By.id("email-input");
    By byPasswordInputWebElement = By.id("password-input");
    By byLoginBtnWebElement = By.id("login-button");

    //InvalidErrorMEssage
    By byXpthErrorMessageForInValidCredentials = By.xpath("//div[@class='message error' and text()='You shall not pass! Arr!']");


///Success message

    By byXpthSuccessMessageForValidCredentials = By.xpath("//div[@class='message success' and text()='Welcome to Codility']");

    By byXpthValidationErrorMesageForEmailInput = By.xpath("//div[@class='validation error' and text()='Email is required']");
    By byXpthValidationErrorMesageForPasswordInput = By.xpath("//div[@class='validation error' and text()='Password is required']");


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

   // @Test
    public void testLoginFormPresent() {

        if (isExistsElement(byEmailIDInputWebElement)) {
            System.out.println("Email ID Input text box is available");
        } else {
            System.out.println("Email ID Input text box is Not available");
        }

        if (isExistsElement(byPasswordInputWebElement)) {
            System.out.println("Password Input text box  is available");

        } else {
            System.out.println("Password Input text box is Not available");

        }
        if (isExistsElement(byLoginBtnWebElement)) {
            System.out.println("Login button is available");

        } else {
            System.out.println("Login button is Not available");

        }
    }

   // @Test
    public void testLoginWithValidCredentials()
    {


        driver.findElement(byEmailIDInputWebElement).sendKeys("login@codility.com");
        driver.findElement(byPasswordInputWebElement).sendKeys("password");
        driver.findElement(byLoginBtnWebElement).click();
        if(isExistsElement(byXpthSuccessMessageForValidCredentials)){
           System.out.println("Login success") ;
        }
        else{
           System.out.println("Login is not success") ;
        }

    }

    @Test
    public void testLoginWithInValidCredentials()
    {
        driver.findElement(byEmailIDInputWebElement).sendKeys("unknown@codility.com");
        driver.findElement(byPasswordInputWebElement).sendKeys("password");
        driver.findElement(byLoginBtnWebElement).click();
        if(isExistsElement(byXpthErrorMessageForInValidCredentials)){
           System.out.println("Validation success") ;

        }

    }
   // @Test
    public void testLoginWithInValidEmail()
    {
        // example usage of Selenium webDriver as driver
        driver.findElement(byEmailIDInputWebElement).sendKeys("unknowncodility.com");
        if(isExistsElement(byXpthValidationErrorMesageForEmailInput)){
           System.out.println("Validation success") ;
        }

    }

  //  @Test
    public void testLoginWithoutCredentials() {
        driver.findElement(byLoginBtnWebElement).click();
        if (isExistsElement(byXpthValidationErrorMesageForEmailInput)) {
            System.out.println("Validation success");

        } else {
            System.out.println("Validation not success");
        }


        if (isExistsElement(byXpthValidationErrorMesageForPasswordInput)) {
            System.out.println("Validation success");

        } else {
            System.out.println("Validation not success");
        }

    }



private boolean isExistsElement(By by) {
        boolean isElementExists = true;
        try {
            driver.findElement(by);
            System.out.println("Element is Present");
        } catch (NoSuchElementException e) {
            return false;
        }
        return isElementExists;


    }


    private void assertWebElement(By by) {

        assertTrue(isExistsElement(by));

    }

    private String getText(By by) {

       return driver.findElement(by).getText();


    }



    @After
    public void teadown(){
        driver.quit();
    }


}
