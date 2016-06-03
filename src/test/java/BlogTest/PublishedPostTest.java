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
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.getCurrentUrl().equalsIgnoreCase("https://sergeywebdrivertest.wordpress.com/"), "Actual URL is " + driver.getCurrentUrl());
    }

    @Test
    public void verifyAboutLink(){
        publishedPostPage.navigate();
        publishedPostPage.clickAboutLink();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("about"), "Actual URL is " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getTitle().contains("About"), "Actual title is " + driver.getTitle());
    }

    @Test
    public void verifyContactLink(){
        publishedPostPage.navigate();
        publishedPostPage.clickContactLink();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(driver.getCurrentUrl().contains("contact"), "Actual URL is " + driver.getCurrentUrl());
        Assert.assertTrue(driver.getTitle().contains("Contact"), "Actual title is " + driver.getTitle());
    }

    @Test
    public void social(){
        publishedPostPage.navigate();
        publishedPostPage.clickShareTwitter();
        Assert.assertTrue(publishedPostPage.getPageURL().contains("twitter"), "It is not Twitter");

        publishedPostPage.clickShareFacebook();
        Assert.assertTrue(publishedPostPage.getPageURL().contains("facebook"), "It is not Facebook");

        publishedPostPage.clickshareGoogle();
        Assert.assertTrue(publishedPostPage.getPageURL().contains("google"), "It is not Google");
    }

}
