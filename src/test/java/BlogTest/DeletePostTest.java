package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DeletePostTest extends BaseTest{

    @Test
    public void trashPost(){
        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);

        int trashCounterBeforeTrash = postsPg.trashCounter();

        postsPg.deleteFirstPost();
        WebElement trashConfirmationAlert = driver.findElement(By.cssSelector(".conf-alert"));
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(trashConfirmationAlert));

        int trashCounterAfterTrash = postsPg.trashCounter();

        Assert.assertTrue(trashConfirmationAlert.isDisplayed());
        Assert.assertEquals(trashCounterBeforeTrash, trashCounterAfterTrash-1);

    }

}
