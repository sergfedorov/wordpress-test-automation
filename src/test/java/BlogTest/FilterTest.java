package BlogTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FilterTest extends BaseTest{

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        pageObjectsInitialization(driver);
    }

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
