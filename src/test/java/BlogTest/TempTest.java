package BlogTest;

import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TempTest extends BaseTest{

    @Test
    public void numberOfResultsIsCorrect() {
        String expectedTilte = "title update qwerty";
        String expectedDescription = "description update";

        /*PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.editFirstPost(expectedTilte, expectedDescription);*/

    }

}
