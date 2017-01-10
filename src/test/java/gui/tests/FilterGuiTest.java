package gui.tests;

import gui.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import gui.BaseGuiTest;

public class FilterGuiTest extends BaseGuiTest {

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
