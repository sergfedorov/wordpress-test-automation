package gui.tests;

import utils.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.BaseTest;

public class DeletePostTest extends BaseTest {

    @BeforeClass
    public void blogLogin(){
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test(priority = 1)
    public void deleteFirstPost(){
        Pages.postsP().navigate();
        int trashCounterBeforeTrash = Pages.postsP().trashCounter();
        System.out.println("trashCounterBeforeTrash " + trashCounterBeforeTrash);

        Pages.postsP().deleteFirstPost();

        int trashCounterAfterTrash = Pages.postsP().trashCounter();
        System.out.println("trashCounterAfterTrash " + trashCounterAfterTrash);

        Assert.assertTrue(Pages.postsP().isTrashConfirmationAlertDisplayed());
        Assert.assertEquals(trashCounterBeforeTrash, trashCounterAfterTrash-1);
    }

    @Test(priority = 2)
    public void deleteFirstPostFromTrash(){
        Pages.postsP().navigate();
        Pages.postsP().filterByTrashed();
        Pages.postsP().deletePostPermanently();
        Pages.postsP().acceptDeletePermanentlyAlert();
        Assert.assertTrue(Pages.postsP().isPostDeletedAlertDisplayed());
        Assert.assertEquals(Pages.postsP().getPostDeletedAlertText(), "Post Deleted");
    }

    @Test(priority = 3)
    public void trashedPostPage(){
        Pages.postsP().navigate();
        Pages.postsP().openTrashedPost();
        Assert.assertEquals(Pages.viewPostP().getPageTitleText() , "Page not found – sergeywebdrivertest");
    }

}
