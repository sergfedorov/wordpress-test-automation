package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class EditPostTest extends BaseTest {

    @Test
    public void editPostTest(){
        String expectedTilte = "title update qwerty";
        String expectedDescription = "description update";

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.editFirstPost(expectedTilte, expectedDescription);

        driver.findElement(By.cssSelector(".editor-ground-control__preview-button")).click();

        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".web-preview__frame"))));
        driver.switchTo().frame(driver.findElement(By.cssSelector(".web-preview__frame")));

        Assert.assertEquals(driver.findElement(By.cssSelector(".entry-header")).getText(), expectedTilte);
        Assert.assertEquals(driver.findElement(By.cssSelector(".entry-content>p")).getText(), expectedDescription);

        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".web-preview__close")).click();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.cssSelector(".notice__action>span")).click();

        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        ViewPostPage viewPg = PageFactory.initElements(driver, ViewPostPage.class);
        viewPg.testTitle(expectedTilte);
        viewPg.testDescription(expectedDescription);

    }
}
