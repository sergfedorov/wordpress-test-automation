package utils;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;

public class BaseTest {

    public WebDriver driver;

    public BaseTest(){
        driver = Driver.getInstance();
    }

    // Take a screenshot in case of failure and save it
/*    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("dd_MMM_yyyy__hh_mm_ss");
        Date date = new Date();
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File("D:\\ScreenShots\\ScreenShot_"+ dateFormat.format(date)+".jpg"));
            System.out.println("Test failed. Taking a screenshot...");
        }
    }*/

    @DataProvider
    public Object[][] userCreds(){
        Object[][] testDataArray = CustomReaders.excelConverter("src/test/resources/testdata/testdata.xls", "testdata");
        return (testDataArray);
    }
}
