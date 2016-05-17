package BlogTest;

import BlogPO.PostsPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterTest extends BaseTest{

    @Test
    public void filterByMe(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.filterByMe();
        Assert.assertTrue(driver.getCurrentUrl().contains("/posts/my/"));
    }

    @Test
    public void filterByEveryone(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.filterByEveryone();
        Assert.assertFalse(driver.getCurrentUrl().contains("/posts/my/"));
    }
}
