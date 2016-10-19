package BlogTest;

import BlogPO.LoginPage;
import BlogPO.Pages;
import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TempTest extends BaseTest {

    @BeforeTest
    public void initBrowserAndPageObjects() {
        driver = super.getDriver(getBrowserTypeFromProperty());
    }

    @BeforeClass
    public void blogLoginTest() {
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    String EXPECTED_TITLE = "new post title test";
    String EXPECTED_DESCRIPTION = "new description test";

    @Test
     public void createPost(){
        Pages.PostsP().navigate();
        Pages.PostsP().clickCreatePost();
        Pages.EditorP().createPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
        Pages.EditorP().viewPublishedPostInNewTab();
    }

}