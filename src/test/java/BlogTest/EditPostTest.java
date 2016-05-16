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
        String expectedTilte = "title update qwerty123";
        String expecteDescription = "description update";

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.editFirstPost(expectedTilte, expecteDescription);

        driver.findElement(By.cssSelector(".notice__action>span")).click();

        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        ViewPostPage viewPg = PageFactory.initElements(driver, ViewPostPage.class);
        viewPg.testTitle(expectedTilte);
        viewPg.testDescription(expecteDescription);

    }
}
