package BlogTest;

import BlogPO.Pages;
import org.testng.Assert;
import org.testng.annotations.*;


public class CreatePostTest extends BaseTest{

    String EXPECTED_TITLE = "new post title test";
    String EXPECTED_DESCRIPTION = "new description test";

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        //pageObjectsInitialization(driver);
    }

    @BeforeClass
    public void blogLoginTest(){
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void createPost(){
        Pages.PostsP().navigate();
        Pages.PostsP().clickCreatePost();
        Pages.EditorP().createPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
    }

    @AfterMethod
    public void deletePost(){
        Pages.PostsP().navigate();
        Pages.PostsP().deleteFirstPost();
    }

    @Test(priority = 1)
    public void successBar(){
        Assert.assertEquals(Pages.EditorP().getTextFromSuccessBar(), "Post published on");
        Assert.assertEquals(Pages.EditorP().isSuccessBarDisplayed(), Boolean.TRUE);
    }

    @Test(priority = 2)
    public void publishTime(){
        Assert.assertEquals(Pages.EditorP().getPublishedTime(), "A MINUTE AGO");
    }

    @Test(priority = 3)
    public void verifyPublishedPost(){
        Pages.EditorP().viewPublishedPost();
        Assert.assertEquals(Pages.ViewPostP().getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.ViewPostP().getDescriptionText(), EXPECTED_DESCRIPTION);
    }

    @Test(priority = 4)
    public void verifyPostList(){
        Pages.PostsP().navigate();
        Pages.PostsP().filterByPublished();
        Assert.assertEquals(Pages.PostsP().getPostTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(Pages.PostsP().getPostDescriptionText(), EXPECTED_DESCRIPTION);
    }


}
