package gui.tests;

import utils.Pages;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.BaseTest;


public class CreatePostTest extends BaseTest {

    String EXPECTED_TITLE = "new post title test";
    String EXPECTED_DESCRIPTION = "new description test";

    @BeforeClass
    public void blogLoginTest(){
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void createPost(){
        Pages.postsP().navigate();
        Pages.postsP().clickCreatePost();
        Pages.editorP().createPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
    }

    @Test(priority = 1)
    public void successBar(){
        Assert.assertEquals(Pages.editorP().getTextFromSuccessBar(), "Post published on");
        Assert.assertEquals(Pages.editorP().isSuccessBarDisplayed(), Boolean.TRUE);
    }

    @Test(priority = 2)
    public void publishTime(){
        Assert.assertEquals(Pages.editorP().getPublishedTime(), "A MINUTE AGO");
    }

    @Test(priority = 3)
    public void verifyPublishedPost(){
        Pages.editorP().viewPublishedPostInNewTab();
        Assert.assertEquals(Pages.viewPostP().getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.viewPostP().getDescriptionText(), EXPECTED_DESCRIPTION);
    }

    @Test(priority = 4)
    public void verifyPostList(){
        Pages.postsP().navigate();
        Pages.postsP().filterByPublished();
        Assert.assertEquals(Pages.postsP().getPostTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.postsP().getPostDescriptionText(), EXPECTED_DESCRIPTION);
    }

    @AfterMethod
    public void deletePost(){
        Pages.postsP().navigate();
        Pages.postsP().deleteFirstPost();
    }


}
