package tests;

import pages.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FilterTest extends BaseTest{

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
    }

    @BeforeClass
    public void blogLogin(){
        Pages.LoginP().navigate();
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void filterByMe(){
        Pages.PostsP().navigate();
        Pages.PostsP().filterByMe();
        Assert.assertTrue(driver.getCurrentUrl().contains("/posts/my/"));
    }

    @Test
    public void filterByEveryone(){
        Pages.PostsP().navigate();
        Pages.PostsP().filterByEveryone();
        Assert.assertFalse(driver.getCurrentUrl().contains("/posts/my/"));
    }
}
