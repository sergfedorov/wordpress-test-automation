package tests;

import pages.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class EditPostTest extends BaseTest {

    String EXPECTED_TITLE = "title update";
    String EXPECTED_DESCRIPTION = "description update";

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        //pageObjectsInitialization(driver);
    }

    @BeforeClass
    public void blogLogin(){
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void editPost(){
        Pages.PostsP().navigate();
        Pages.PostsP().clickEditPost();
        Pages.EditorP().editPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
    }

    @Test(priority = 1)
    public void verifyPreview(){
        Pages.EditorP().openPreviewPost();
        Assert.assertEquals(Pages.ViewPostP().getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.ViewPostP().getDescriptionText(), EXPECTED_DESCRIPTION);
    }

    @Test(priority = 2)
    public void verifyViewPublished(){
        Pages.EditorP().viewPublishedPostInNewTab();
        Assert.assertEquals(Pages.ViewPostP().getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.ViewPostP().getDescriptionText(), EXPECTED_DESCRIPTION);

    }



}
