package BlogTest;

import org.testng.Assert;
import org.testng.annotations.*;


public class CreatePostTest extends BaseTest{

    String EXPECTED_TITLE = "new post title test";
    String EXPECTED_DESCRIPTION = "new description test";

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        pageObjectsInitialization(driver);
    }

    @BeforeClass
    public void blogLoginTest(){
        loginPage.login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void createPost(){
        postsPage.navigate();
        postsPage.clickCreatePost();
        editorPage.createPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
    }

    @AfterMethod
    public void deletePost(){
        postsPage.navigate();
        postsPage.deleteFirstPost();
    }

    @Test(priority = 1)
    public void successBar(){
        Assert.assertEquals(editorPage.getTextFromSuccessBar(), "Post published on");
        Assert.assertEquals(editorPage.isSuccessBarDisplayed(), Boolean.TRUE);
    }

    @Test(priority = 2)
    public void publishTime(){
        Assert.assertEquals(editorPage.getPublishedTime(), "A MINUTE AGO");
    }

    @Test(priority = 3)
    public void verifyPublishedPost(){
        editorPage.viewPublishedPost();
        Assert.assertEquals(viewPage.getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(viewPage.getDescriptionText(), EXPECTED_DESCRIPTION);
    }

    @Test(priority = 4)
    public void verifyPostList(){
        postsPage.navigate();
        postsPage.filterByPublished();
        Assert.assertEquals(postsPage.getPostTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(postsPage.getPostDescriptionText(), EXPECTED_DESCRIPTION);
    }


}
