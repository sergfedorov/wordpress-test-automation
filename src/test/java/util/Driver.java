package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.CustomReaders;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", CustomReaders.getChromeDriverPathFromProperties());
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }


    public static WebDriver getInstance(CustomReaders.BrowserType browserType) {
        if (driver == null) {
            if (browserType == CustomReaders.BrowserType.CHROME) {
                System.setProperty("webdriver.chrome.driver", CustomReaders.getChromeDriverPathFromProperties());
                driver = new ChromeDriver();
                System.out.println("Browser is " + browserType);
            }
            if (browserType == CustomReaders.BrowserType.FIREFOX) {
                driver = new FirefoxDriver();
                System.out.println("Browser is " + browserType);
            }
            else {
                System.setProperty("webdriver.chrome.driver", CustomReaders.getChromeDriverPathFromProperties());
                driver = new ChromeDriver();
                System.out.println("Wrong browser type in properties file. Default browser will be used.");
            }
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }


}
