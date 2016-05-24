package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FilterTest extends BaseTest{

    LoginPage loginPage = new LoginPage(driver);
    PostsPage postsPage = new PostsPage(driver);

    @BeforeClass
    public void blogLogin(){
        loginPage.navigate();
        loginPage.login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void filterByMe(){
        postsPage.navigate();
        postsPage.filterByMe();
        Assert.assertTrue(driver.getCurrentUrl().contains("/posts/my/"));
    }

    @Test
    public void filterByEveryone(){
        postsPage.navigate();
        postsPage.filterByEveryone();
        Assert.assertFalse(driver.getCurrentUrl().contains("/posts/my/"));
    }
}
