package BlogTest;


import BlogPO.LoginPage;
import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestNGTest {

    WebDriver driver;

    @BeforeClass
    public void BrowserInit() {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void blogLoginTest(){
        LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
        loginPg.login();
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
        //driver.close();
    }

    @Test
    public void trashPost(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);

        int trashCounterBeforeTrash = postsPg.trashCounter();
        postsPg.deleteFirstPost();
        int trashCounterAfterTrash = postsPg.trashCounter();

        WebElement trashConfirmationAlert = driver.findElement(By.cssSelector(".conf-alert"));
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(trashConfirmationAlert));
        Assert.assertTrue(trashConfirmationAlert.isDisplayed());

        Assert.assertEquals(trashCounterBeforeTrash+1, trashCounterAfterTrash);

    }

}
