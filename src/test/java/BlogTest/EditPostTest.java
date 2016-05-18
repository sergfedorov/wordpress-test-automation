package BlogTest;

import BlogPO.EditorPage;
import BlogPO.LoginPage;
import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class EditPostTest extends BaseTest {

    String expectedTilte = "title update";
    String expectedDescription = "description update";

    @BeforeMethod
    public void editPost(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.editFirstPost(expectedTilte, expectedDescription);
    }

    @Test(priority = 1)
    public void verifyPreview(){

        EditorPage editorPg = PageFactory.initElements(driver, EditorPage.class);
        editorPg.previewPost();

        ViewPostPage viewPg = PageFactory.initElements(driver, ViewPostPage.class);
        viewPg.testTitle(expectedTilte);
        viewPg.testDescription(expectedDescription);
    }

    @Test(priority = 2)
    public void verifyChangesOnPublishedPost(){

        EditorPage editorPg = PageFactory.initElements(driver, EditorPage.class);
        editorPg.viewPublishedPost();

        ViewPostPage viewPg = PageFactory.initElements(driver, ViewPostPage.class);
        viewPg.testTitle(expectedTilte);
        viewPg.testDescription(expectedDescription);

    }



}
