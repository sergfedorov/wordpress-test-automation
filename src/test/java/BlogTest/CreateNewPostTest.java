package BlogTest;

import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;


public class CreateNewPostTest extends BaseTest{

    @Test(priority = 0)
    public void emptyFieldsTest(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        driver.findElement(By.className("gridicons-create")).click();
        Assert.assertFalse(driver.findElement(By.cssSelector(".editor-ground-control__publish-button")).isEnabled());
    }

    @Test(priority = 1)
    public void createPostTest() {
        String expectedTilteForNewPost = "new post title test";
        String expectedDescriptionForNewPost = "new description title test";

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.createNewPost(expectedTilteForNewPost, expectedDescriptionForNewPost);

        WebElement publishedSuccessfullyBar = driver.findElement(By.cssSelector(".is-success"));
        WebElement publishedTime = driver.findElement(By.cssSelector(".editor-status-label>span"));

        Assert.assertTrue(publishedSuccessfullyBar.isDisplayed());
        Assert.assertEquals(publishedTime.getText(), "A MINUTE AGO");

        driver.findElement(By.cssSelector(".notice__action>span")).click();

        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        ViewPostPage viewPg = PageFactory.initElements(driver, ViewPostPage.class);
        viewPg.testTitle(expectedTilteForNewPost);
        viewPg.testDescription(expectedDescriptionForNewPost);

    }


}
