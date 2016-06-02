package BlogTest;

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
        pageObjectsInitialization(driver);
    }

    @BeforeClass
    public void blogLogin(){
        loginPage.login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void trashPost(){
        postsPage.navigate();
        int trashCounterBeforeTrash = postsPage.trashCounter();

        postsPage.deleteFirstPost();
        WebElement trashConfirmationAlert = driver.findElement(By.cssSelector(".conf-alert"));

        int trashCounterAfterTrash = postsPage.trashCounter();

        Assert.assertTrue(trashConfirmationAlert.isDisplayed());
        Assert.assertEquals(trashCounterBeforeTrash, trashCounterAfterTrash-1);
    }

}
