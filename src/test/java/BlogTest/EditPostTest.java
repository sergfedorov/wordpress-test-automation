package BlogTest;

import BlogPO.EditorPage;
import BlogPO.LoginPage;
import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class EditPostTest extends BaseTest {

    String expectedTilte = "title update";
    String expectedDescription = "description update";

    LoginPage loginPage = new LoginPage(driver);
    PostsPage postsPage = new PostsPage(driver);
    EditorPage editorPage = new EditorPage(driver);
    ViewPostPage viewPage = new ViewPostPage(driver);

    @BeforeClass
    public void blogLogin(){
        loginPage.login("editorwebdrivertest", "EditorTest");
    }

    @BeforeMethod
    public void editPost(){
        postsPage.navigate();
        postsPage.clickEditPost();
        editorPage.editPost(expectedTilte, expectedDescription);
    }

    @Test(priority = 1)
    public void verifyPreview(){
        editorPage.previewPost();
        viewPage.testTitle(expectedTilte);
        viewPage.testDescription(expectedDescription);
    }

    @Test(priority = 2)
    public void verifyViewPublished(){
        editorPage.viewPublishedPost();
        viewPage.testTitle(expectedTilte);
        viewPage.testDescription(expectedDescription);

    }



}
