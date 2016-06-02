package BlogTest;

import BlogPO.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class SearchPostTest extends BaseTest{

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        pageObjectsInitializtion(driver);
    }

    @BeforeClass
    public void blogLogin(){
        loginPage.login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void numberOfResultsIsCorrect() {
        String searchText = "234234";

        postsPage.navigate();

        List<WebElement> searchRes = driver.findElements(By.xpath("//h4[contains(text(),'" +searchText + "')]"));
        int expectedResSearch = searchRes.size();
        int actualResSearch = postsPage.postSearch(searchText);

        Assert.assertEquals(actualResSearch, expectedResSearch);
        System.out.println("number of results returned: " +actualResSearch);

        if(actualResSearch == 0){
            Assert.assertTrue(driver.findElement(By.cssSelector(".no-results")).isDisplayed());
        }

    }

}
