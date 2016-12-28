package tests;

import util.Pages;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.BaseTest;

public class PublishedPostTest extends BaseTest {

    /*@BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        //pageObjectsInitialization(driver);
    }*/

    @Test
    public void verifyHomeLink(){
        Pages.publishedPostP().navigate();
        Pages.publishedPostP().clickHomeLink();
        Assert.assertEquals(driver.getTitle(), "sergeywebdrivertest");
    }

    @Test
    public void verifyAboutLink(){
        Pages.publishedPostP().navigate();
        Pages.publishedPostP().clickAboutLink();
        Assert.assertTrue(driver.getTitle().contains("About"), "Actual title is " + driver.getTitle());
    }

    @Test
    public void verifyContactLink(){
        Pages.publishedPostP().navigate();
        Pages.publishedPostP().clickContactLink();
        Assert.assertTrue(driver.getTitle().contains("Contact"), "Actual title is " + driver.getTitle());
    }

    @Test(priority = 1)
    public void shares(){
        Pages.publishedPostP().navigate();
        Pages.publishedPostP().clickShareTwitter();
        Assert.assertTrue(Pages.publishedPostP().getNewWindowPageURL().contains("twitter"), "It is not Twitter");
        Pages.publishedPostP().clickShareFacebook();
        Assert.assertTrue(Pages.publishedPostP().getNewWindowPageURL().contains("facebook"), "It is not Facebook");
        Pages.publishedPostP().clickshareGoogle();
        Assert.assertTrue(Pages.publishedPostP().getNewWindowPageURL().contains("google"), "It is not Google");
    }

    @Test
    public void commentFormValidationNotLogged(){
        Pages.publishedPostP().navigate();
        Pages.publishedPostP().clickCommentField();
        Pages.publishedPostP().clickPostComment();
        Assert.assertTrue(Pages.publishedPostP().isCommentFormEmailFieldValidated());
        Assert.assertTrue(Pages.publishedPostP().isCommentFormAuthorFieldValidated());
        Assert.assertTrue(Pages.publishedPostP().isCommentFormFieldValidated());
        Assert.assertEquals(Pages.publishedPostP().getTextCommentFormEmail(), "Please enter your email address here");
        Assert.assertEquals(Pages.publishedPostP().getTextCommentFormAuthor(), "Please enter your name here");
    }

}
