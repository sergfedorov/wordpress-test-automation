package BlogTest;

import BlogPO.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DeletePostTest extends BaseTest{

    LoginPage loginPage;
    PostsPage postsPage;
    EditorPage editorPage;
    ViewPostPage viewPage;
    DashboardPage dashboardPage;

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.init();
        loginPage = new LoginPage(driver);
        postsPage = new PostsPage(driver);
        editorPage = new EditorPage(driver);
        viewPage = new ViewPostPage(driver);
        dashboardPage = new DashboardPage(driver);
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
