package BlogTest;

import BlogPO.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTest {

    WebDriver driver;

    @BeforeSuite
    public void BrowserInit() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @BeforeClass
    public void blogLoginTest(){
        LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
        loginPg.login();
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
    }

}
