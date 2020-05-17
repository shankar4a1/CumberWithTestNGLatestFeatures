package com.demo.framework.utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class BrowserFactory {

    private Logger log = Log.getLogger(BrowserFactory.class);
    private WebDriver driver;
    private ConfigurationReader configReader;

    public static final String URL = "";
    String destination = null;
    DesiredCapabilities caps = null;

    public WebDriver initiateDriver(String browserName) throws MalformedURLException {
        configReader = new ConfigurationReader();
        String[] path = System.getProperty("user.dir").split("/");
      //  String actualPath = "/" + path[1] + "/" + path[2];

        log.info("Opening " + browserName + "browser");
        switch (browserName.toUpperCase()){
            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                System.setProperty("webdriver.gecko.driver", configReader.getGeckoDriverPath());
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                break;
            case "CHROME":
                //System.setProperty("webdriver.chrome.driver", configReader.getChromeDriverPath());
                WebDriverManager.chromedriver().setup();
                //Uncomment below lines if you would like to run it in incognito mode
//                ChromeOptions option = new ChromeOptions();
//                option.addArguments("--incognito");
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                break;
            case "SAFARI":
                driver = new SafariDriver();
                driver.manage().window().maximize();
                break;
            case "GRID_FIREFOX":
                caps = DesiredCapabilities.firefox();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
                break;
            case "GRID_CHROME":
                caps = DesiredCapabilities.chrome();
                driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);
                break;
            case "CHROME_HEADLESS":
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                // destination = actualPath + "/Library/Application Support/Google/Chrome/"+randomString(8);
                // copyFiles(actualPath + "/Library/Application Support/Google/Chrome/profile1",destination);
                // options.addArguments("user-data-dir=" + destination);
//                options.addArguments("user-data-dir=" + actualPath + "/Library/Application Support/Google/Chrome");
                options.addArguments("--no-sandbox");
                options.addArguments("--headless");
                options.setExperimentalOption("useAutomationExtension", false);
                // options.addArguments("disable-infobars");
                // options.addArguments("--disable-extensions");
                // options.addArguments("--disable-gpu");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("window-size=1920,1080");
                driver = new ChromeDriver(options);
                driver.manage().window().maximize();
                break;
        }

        return driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void launchURL(String portalName) {
        driver.get(configReader.get(portalName));
        log.info(URL + " launched");
    }


    public static String randomString(int count) {
        String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public static void copyFiles(String source,String destination){
        File src = new File(source);
        File dest = new File(destination);
        try {
            FileUtils.copyDirectory(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteDirectory(){
        try {
            if (!destination.isEmpty() || !destination.equalsIgnoreCase("")) {
                    FileUtils.deleteDirectory(new File(destination));
                }
            } catch (Exception e) {
                log.info(e.getMessage());
            }
    }



    public void launchURL() {
        driver.get(configReader.getApplicationURL());
    }
}
