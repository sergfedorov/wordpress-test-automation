package tests;

import util.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.BaseTest;

public class FilterTest extends BaseTest {

    @BeforeClass
    public void blogLogin(){
        Pages.loginP().navigate();
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void filterByMe(){
        Pages.postsP().navigate();
        Pages.postsP().filterByMe();
        Assert.assertTrue(driver.getCurrentUrl().contains("/posts/my/"));
    }

    @Test
    public void filterByEveryone(){
        Pages.postsP().navigate();
        Pages.postsP().filterByEveryone();
        Assert.assertFalse(driver.getCurrentUrl().contains("/posts/my/"));
    }
}
