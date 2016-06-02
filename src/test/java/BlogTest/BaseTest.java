package BlogTest;

import BlogPO.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    WebDriver driver;

    LoginPage loginPage;
    PostsPage postsPage;
    EditorPage editorPage;
    ViewPostPage viewPage;
    DashboardPage dashboardPage;

    public void pageObjectsInitializtion(WebDriver driver){
        loginPage = new LoginPage(driver);
        postsPage = new PostsPage(driver);
        editorPage = new EditorPage(driver);
        viewPage = new ViewPostPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    public enum BrowserType {
        FIREFOX("firefox"),
        CHROME("chrome");

        private String value;
        BrowserType(String value) {
            this.value = value;
        }

        public String getBrowserName() {
            return this.value;
        }
    }

    public WebDriver getDriver(BrowserType browserType) {
        switch (browserType) {
            case FIREFOX:
                driver = new FirefoxDriver();
                break;
            case CHROME:
                System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
                driver = new ChromeDriver();
                break;
            default:
                driver = new FirefoxDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

    public BrowserType getBrowserTypeFromProperty() {
        BrowserType type = null;
        try {
            FileInputStream file = new FileInputStream("wordpress.properties");
            Properties properties = new Properties();
            properties.load(file);
            String browserName = properties.getProperty("browser");
            for (BrowserType bType : BrowserType.values()) {
                if (bType.getBrowserName().equalsIgnoreCase(browserName)) {
                    type = bType;
                    System.out.println("Browser is " + bType);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }
}
