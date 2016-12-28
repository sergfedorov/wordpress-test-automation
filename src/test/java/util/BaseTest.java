package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {

    public WebDriver driver;

    public BaseTest(){
        driver = Driver.getInstance();
    }

    // Take a screenshot in case of failure and save it
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ss");
        Date date = new Date();
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("D:\\ScreenShots\\ScreenShot_"+ dateFormat.format(date)+".jpg"));
            System.out.println("Test failed. Taking a screenshot...");
        }
    }
}
