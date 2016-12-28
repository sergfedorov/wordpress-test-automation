package tests;

import pages.Pages;
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
        Pages.PostsP().deletePostPermanently();
        Pages.PostsP().acceptDeletePermanentlyAlert();
        Assert.assertTrue(Pages.PostsP().isPostDeletedAlertDisplayed());
        Assert.assertEquals(Pages.PostsP().getPostDeletedAlertText(), "Post Deleted");
    }

    @Test(priority = 3)
    public void trashedPostPage(){
        Pages.PostsP().navigate();
        Pages.PostsP().openTrashedPost();
        Assert.assertEquals(Pages.ViewPostP().getPageTitleText() , "Page not found – sergeywebdrivertest");
    }

}
