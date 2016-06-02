package BlogTest;

import BlogPO.EditorPage;
import BlogPO.LoginPage;
import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class EditPostTest extends BaseTest {

    String EXPECTED_TITLE = "title update";
    String EXPECTED_DESCRIPTION = "description update";

    LoginPage loginPage;
    PostsPage postsPage;
    EditorPage editorPage;
    ViewPostPage viewPage;

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        loginPage = new LoginPage(driver);
        postsPage = new PostsPage(driver);
        editorPage = new EditorPage(driver);
        viewPage = new ViewPostPage(driver);
    }

    @BeforeClass
    public void blogLogin(){
        loginPage.login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void editPost(){
        postsPage.navigate();
        postsPage.clickEditPost();
        editorPage.editPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
    }

    @Test(priority = 1)
    public void verifyPreview(){
        editorPage.openPreviewPost();
        Assert.assertEquals(viewPage.getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(viewPage.getDescriptionText(), EXPECTED_DESCRIPTION);
    }

    @Test(priority = 2)
    public void verifyViewPublished(){
        editorPage.viewPublishedPost();
        Assert.assertEquals(viewPage.getTitleText(), EXPECTED_TITLE);
        Assert.assertEquals(viewPage.getDescriptionText(), EXPECTED_DESCRIPTION);

    }



}
