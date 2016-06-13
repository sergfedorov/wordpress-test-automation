package BlogTest;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PublishedPostTest extends BaseTest{

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        pageObjectsInitialization(driver);
    }

    @Test
    public void verifyHomeLink(){
        publishedPostPage.navigate();
        publishedPostPage.clickHomeLink();
        Assert.assertEquals(driver.getTitle(), "sergeywebdrivertest");
    }

    @Test
    public void verifyAboutLink(){
        publishedPostPage.navigate();
        publishedPostPage.clickAboutLink();
        Assert.assertTrue(driver.getTitle().contains("About"), "Actual title is " + driver.getTitle());
    }

    @Test
    public void verifyContactLink(){
        publishedPostPage.navigate();
        publishedPostPage.clickContactLink();
        Assert.assertTrue(driver.getTitle().contains("Contact"), "Actual title is " + driver.getTitle());
    }

    @Test(priority = 1)
    public void shares(){
        publishedPostPage.navigate();
        publishedPostPage.clickShareTwitter();
        Assert.assertTrue(publishedPostPage.getNewWindowPageURL().contains("twitter"), "It is not Twitter");
        publishedPostPage.clickShareFacebook();
        Assert.assertTrue(publishedPostPage.getNewWindowPageURL().contains("facebook"), "It is not Facebook");
        publishedPostPage.clickshareGoogle();
        Assert.assertTrue(publishedPostPage.getNewWindowPageURL().contains("google"), "It is not Google");
    }

    @Test
    public void commentFormValidationNotLogged(){
        publishedPostPage.navigate();
        publishedPostPage.clickCommentField();
        publishedPostPage.clickPostComment();
        Assert.assertTrue(publishedPostPage.isCommentFormEmailFieldValidated());
        Assert.assertTrue(publishedPostPage.isCommentFormAuthorFieldValidated());
        Assert.assertTrue(publishedPostPage.isCommentFormFieldValidated());
        Assert.assertEquals(publishedPostPage.getTextCommentFormEmail(), "Please enter your email address here");
        Assert.assertEquals(publishedPostPage.getTextCommentFormAuthor(), "Please enter your name here");
    }

}
