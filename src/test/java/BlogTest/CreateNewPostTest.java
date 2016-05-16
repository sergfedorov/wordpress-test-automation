package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;


public class CreateNewPostTest extends BaseTest{

    @Test
    public void createPostTest() {
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.createNewPost("qwerty", "asdafaf");
    }

}
