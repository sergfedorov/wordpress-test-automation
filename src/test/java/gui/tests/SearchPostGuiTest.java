package gui.tests;

import gui.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import gui.BaseGuiTest;

import java.util.List;

public class SearchPostGuiTest extends BaseGuiTest {


    @BeforeClass
    public void blogLogin(){
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void numberOfResultsIsCorrect() {
        String searchText = "234234";

        Pages.postsP().navigate();

        List<WebElement> searchRes = driver.findElements(By.xpath("//h4[contains(text(),'" +searchText + "')]"));
        int expectedResSearch = searchRes.size();
        int actualResSearch = Pages.postsP().postSearch(searchText);

        Assert.assertEquals(actualResSearch, expectedResSearch);
        System.out.println("number of results returned: " +actualResSearch);

        if(actualResSearch == 0){
            Assert.assertTrue(driver.findElement(By.cssSelector(".no-results")).isDisplayed());
        }

    }

}
