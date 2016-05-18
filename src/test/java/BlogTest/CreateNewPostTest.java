package BlogTest;

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
    String expectedDescriptionForNewPost = "new description title test";

    @BeforeMethod
    public void createPost(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.createNewPost(expectedTilteForNewPost, expectedDescriptionForNewPost);
    }

    @AfterMethod
    public void deletePost(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.deleteFirstPost();
    }

    @Test(priority = 1)
    public void successBar(){
        WebElement publishedSuccessfullyBar = driver.findElement(By.cssSelector(".is-success"));
        Assert.assertTrue(publishedSuccessfullyBar.isDisplayed());
    }

    @Test(priority = 2)
    public void publishTime(){
        WebElement publishedTime = driver.findElement(By.cssSelector(".editor-status-label>span"));
        Assert.assertEquals(publishedTime.getText(), "A MINUTE AGO");
    }

    @Test(priority = 3)
    public void verifyPublishedPost(){
        driver.findElement(By.cssSelector(".notice__action>span")).click();

        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        ViewPostPage viewPg = PageFactory.initElements(driver, ViewPostPage.class);
        viewPg.testTitle(expectedTilteForNewPost);
        viewPg.testDescription(expectedDescriptionForNewPost);
    }


}
