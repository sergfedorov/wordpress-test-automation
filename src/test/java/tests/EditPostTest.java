package tests;

import util.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.BaseTest;


public class EditPostTest extends BaseTest {

    String EXPECTED_TITLE = "title update";
    String EXPECTED_DESCRIPTION = "description update";


    @BeforeClass
    public void blogLogin(){
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void editPost(){
        Pages.postsP().navigate();
        Pages.postsP().clickEditPost();
        Pages.editorP().editPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
    }

    @Test(priority = 1)
    public void verifyPreview(){
        Pages.editorP().openPreviewPost();
        Assert.assertEquals(Pages.viewPostP().getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.viewPostP().getDescriptionText(), EXPECTED_DESCRIPTION);
    }

    @Test(priority = 2)
    public void verifyViewPublished(){
        Pages.editorP().viewPublishedPostInNewTab();
        Assert.assertEquals(Pages.viewPostP().getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.viewPostP().getDescriptionText(), EXPECTED_DESCRIPTION);

    }



}
