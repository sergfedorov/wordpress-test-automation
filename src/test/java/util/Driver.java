package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import util.CustomReaders;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getInstance() {
        if (driver == null) {
            System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }


/*    public static WebDriver getInstance(CustomReaders.BrowserType browserType) {
        if (driver == null) {
            if (browserType == CustomReaders.BrowserType.CHROME) {
                System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
                driver = new ChromeDriver();
            }
            if (browserType == CustomReaders.BrowserType.FIREFOX) {
                driver = new FirefoxDriver();
            }
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }*/


}
