package BlogTest;

import BlogPO.EditorPage;
import BlogPO.LoginPage;
import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.testng.Assert;
import org.testng.annotations.*;


public class CreateNewPostTest extends BaseTest{

    String expectedTilteForNewPost = "new post title test";
    String expectedDescriptionForNewPost = "new description test";

    LoginPage loginPage;
    PostsPage postsPage;
    EditorPage editorPage;
    ViewPostPage viewPage;

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.init();
        loginPage = new LoginPage(driver);
        postsPage = new PostsPage(driver);
        editorPage = new EditorPage(driver);
        viewPage = new ViewPostPage(driver);
    }


    @BeforeClass
    public void blogLoginTest(){
        loginPage.login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void createPost(){
        postsPage.navigate();
        postsPage.clickCreatePost();
        editorPage.createPost(expectedTilteForNewPost, expectedDescriptionForNewPost);
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
        viewPage.testTitle(expectedTilteForNewPost);
        viewPage.testDescription(expectedDescriptionForNewPost);
    }

    @Test(priority = 4)
    public void verifyPostList(){
        postsPage.navigate();
        postsPage.filterByPublished();
        postsPage.testTitleInList(expectedTilteForNewPost);
        postsPage.testDescriptionInList(expectedDescriptionForNewPost);

    }


}
