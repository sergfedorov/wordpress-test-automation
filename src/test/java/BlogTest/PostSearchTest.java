package BlogTest;

import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class PostSearchTest extends BaseTest{

    @Test
    public void numberOfResultsIsCorrect() {
        String searchText = "qwerty";

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);

        List<WebElement> searchRes = driver.findElements(By.xpath("//h4[contains(text(),'" +searchText + "')]"));
        int expectedResSearch = searchRes.size();
        int actualResSearch = postsPg.postSearch(searchText);

        Assert.assertEquals(actualResSearch, expectedResSearch);
        System.out.println("number of results returned: " +actualResSearch);

        if(actualResSearch == 0){
            Assert.assertTrue(driver.findElement(By.cssSelector(".no-results")).isDisplayed());
        }

    }

}
