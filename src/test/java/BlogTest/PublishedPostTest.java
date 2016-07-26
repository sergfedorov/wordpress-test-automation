package BlogTest;

import BlogPO.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class PublishedPostTest extends BaseTest{

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        //pageObjectsInitialization(driver);
    }

    @Test
    public void verifyHomeLink(){
        Pages.PublishedPostP().navigate();
        Pages.PublishedPostP().clickHomeLink();
        Assert.assertEquals(driver.getTitle(), "sergeywebdrivertest");
    }

    @Test
    public void verifyAboutLink(){
        Pages.PublishedPostP().navigate();
        Pages.PublishedPostP().clickAboutLink();
        Assert.assertTrue(driver.getTitle().contains("About"), "Actual title is " + driver.getTitle());
    }

    @Test
    public void verifyContactLink(){
        Pages.PublishedPostP().navigate();
        Pages.PublishedPostP().clickContactLink();
        Assert.assertTrue(driver.getTitle().contains("Contact"), "Actual title is " + driver.getTitle());
    }

    @Test(priority = 1)
    public void shares(){
        Pages.PublishedPostP().navigate();
        Pages.PublishedPostP().clickShareTwitter();
        Assert.assertTrue(Pages.PublishedPostP().getNewWindowPageURL().contains("twitter"), "It is not Twitter");
        Pages.PublishedPostP().clickShareFacebook();
        Assert.assertTrue(Pages.PublishedPostP().getNewWindowPageURL().contains("facebook"), "It is not Facebook");
        Pages.PublishedPostP().clickshareGoogle();
        Assert.assertTrue(Pages.PublishedPostP().getNewWindowPageURL().contains("google"), "It is not Google");
    }

    @Test
    public void commentFormValidationNotLogged(){
        Pages.PublishedPostP().navigate();
        Pages.PublishedPostP().clickCommentField();
        Pages.PublishedPostP().clickPostComment();
        Assert.assertTrue(Pages.PublishedPostP().isCommentFormEmailFieldValidated());
        Assert.assertTrue(Pages.PublishedPostP().isCommentFormAuthorFieldValidated());
        Assert.assertTrue(Pages.PublishedPostP().isCommentFormFieldValidated());
        Assert.assertEquals(Pages.PublishedPostP().getTextCommentFormEmail(), "Please enter your email address here");
        Assert.assertEquals(Pages.PublishedPostP().getTextCommentFormAuthor(), "Please enter your name here");
    }

}
