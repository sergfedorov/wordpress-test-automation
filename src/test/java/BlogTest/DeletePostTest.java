package BlogTest;

import BlogPO.Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeletePostTest extends BaseTest{

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        //pageObjectsInitialization(driver);
    }

    @BeforeClass
    public void blogLogin(){
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void trashPost(){
        Pages.PostsP().navigate();
        int trashCounterBeforeTrash = Pages.PostsP().trashCounter();

        Pages.PostsP().deleteFirstPost();
        WebElement trashConfirmationAlert = driver.findElement(By.cssSelector(".conf-alert"));

        int trashCounterAfterTrash = Pages.PostsP().trashCounter();

        Assert.assertTrue(trashConfirmationAlert.isDisplayed());
        Assert.assertEquals(trashCounterBeforeTrash, trashCounterAfterTrash-1);
    }

}
