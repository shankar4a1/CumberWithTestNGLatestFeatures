package com.demo.PractiseExams;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class CodilityTestWIthAssert {
    WebDriver driver;

    By byEmailIDInputWebElement = By.id("email-input");
    By byPasswordInputWebElement = By.id("password-input");
    By byLoginBtnWebElement = By.id("login-button");

    //InvalidErrorMEssage
    By byXpthErrorMessageForInValidCredentials = By.xpath("//div[@class='message error' and text()='You shall not pass! Arr!']");


    //Success message

    By byXpthSuccessMessageForValidCredentials = By.xpath("//div[@class='message success' and text()='Welcome to Codility']");

    By byXpthValidationErrorMesageForInvalidEmailInput = By.xpath("//div[@class='validation error' and text()='Enter a valid email']");

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

  //  @Test
    public void testLoginFormPresent() {

        assertWebElement(byEmailIDInputWebElement);
        assertWebElement( byPasswordInputWebElement);
        assertWebElement( byLoginBtnWebElement);
    }

   // @Test
    public void testLoginWithValidCredentials()
    {


        driver.findElement(byEmailIDInputWebElement).sendKeys("login@codility.com");
        driver.findElement(byPasswordInputWebElement).sendKeys("password");
        driver.findElement(byLoginBtnWebElement).click();
        assertWebElement(byXpthSuccessMessageForValidCredentials);

    }

  //  @Test
    public void testLoginWithInValidCredentials()
    {
        driver.findElement(byEmailIDInputWebElement).sendKeys("unknown@codility.com");
        driver.findElement(byPasswordInputWebElement).sendKeys("password");
        driver.findElement(byLoginBtnWebElement).click();
        assertWebElement(byXpthErrorMessageForInValidCredentials);

    }
  //  @Test
    public void testLoginWithInValidEmail()
    {
        // example usage of Selenium webDriver as driver
        driver.findElement(byEmailIDInputWebElement).sendKeys("unknowncodility.com");
        driver.findElement(byPasswordInputWebElement).sendKeys("password");
        driver.findElement(byLoginBtnWebElement).click();
        assertWebElement(byXpthValidationErrorMesageForInvalidEmailInput);

    }

   @Test
    public void testLoginWithEmptyCredentials() {
        driver.findElement(byLoginBtnWebElement).click();

        assertWebElement(byXpthValidationErrorMesageForEmailInput);
        assertWebElement(byXpthValidationErrorMesageForPasswordInput);

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

    private String getText(By by) {

       return driver.findElement(by).getText();


    }



    @After
    public void teadown(){
        driver.quit();
    }


}
