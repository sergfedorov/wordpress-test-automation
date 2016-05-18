package BlogTest;

import BlogPO.EditorPage;
import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class CreateNewPostTest extends BaseTest{

    String expectedTilteForNewPost = "new post title test";
    String expectedDescriptionForNewPost = "new description test";

    @BeforeMethod
    public void createPost(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        EditorPage editorPg = postsPg.clickCreatePost();
        editorPg.createPost(expectedTilteForNewPost, expectedDescriptionForNewPost);
    }

    @AfterMethod
    public void deletePost(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.deleteFirstPost();
    }

    @Test(priority = 1)
    public void successBar(){
        WebElement publishedSuccessfullyBar = driver.findElement(By.cssSelector(".is-success"));

        EditorPage editorPg = PageFactory.initElements(driver, EditorPage.class);
        String actualResult = editorPg.getTextFromSuccessBar();
        Assert.assertEquals(actualResult, "Post published");


        Assert.assertTrue(publishedSuccessfullyBar.isDisplayed());
    }

    @Test(priority = 2)
    public void publishTime(){
        WebElement publishedTime = driver.findElement(By.cssSelector(".editor-status-label>span"));

        Assert.assertEquals(publishedTime.getText(), "A MINUTE AGO");
    }

    @Test(priority = 3)
    public void verifyPublishedPost(){
        EditorPage editorPg = PageFactory.initElements(driver, EditorPage.class);
        ViewPostPage viewPg = editorPg.viewPublishedPost();

        viewPg.testTitle(expectedTilteForNewPost);
        viewPg.testDescription(expectedDescriptionForNewPost);
    }

    @Test(priority = 4)
    public void verifyPostList(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);

        postsPg.filterByPublished();
        postsPg.testTitleInList(expectedTilteForNewPost);
        postsPg.testDescriptionInList(expectedDescriptionForNewPost);

    }


}
