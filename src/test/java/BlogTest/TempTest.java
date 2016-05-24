package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class TempTest extends BaseTest{

    LoginPage loginPage = new LoginPage(driver);
    PostsPage postsPage = new PostsPage(driver);

    @BeforeClass
    public void blogLoginTest(){
        loginPage.login("editorwebdrivertest", "EditorTest");
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
    }

    @Test
    public void mytest123() {
        postsPage.navigate();
        postsPage.deleteFirstPost();

    }

}
