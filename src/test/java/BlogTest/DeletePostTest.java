package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DeletePostTest extends BaseTest{

    LoginPage loginPage  = new LoginPage(driver);
    PostsPage postsPage = new PostsPage(driver);

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
