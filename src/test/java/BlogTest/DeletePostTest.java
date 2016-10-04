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
    }

    @BeforeClass
    public void blogLogin(){
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test(priority = 1)
    public void deleteFirstPost(){
        Pages.PostsP().navigate();
        int trashCounterBeforeTrash = Pages.PostsP().trashCounter();
        System.out.println("trashCounterBeforeTrash " + trashCounterBeforeTrash);

        Pages.PostsP().deleteFirstPost();

        int trashCounterAfterTrash = Pages.PostsP().trashCounter();
        System.out.println("trashCounterAfterTrash " + trashCounterAfterTrash);

        Assert.assertTrue(Pages.PostsP().isTrashConfirmationAlertDisplayed());
        Assert.assertEquals(trashCounterBeforeTrash, trashCounterAfterTrash-1);
    }

    @Test(priority = 2)
    public void deleteFirstPostFromTrash(){
        Pages.PostsP().navigate();
        Pages.PostsP().filterByTrashed();
    }

}
