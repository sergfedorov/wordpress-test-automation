package tests;

import pages.Pages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TempTest extends BaseTest {

    @BeforeTest
    public void initBrowserAndPageObjects() {
        driver = super.getDriver(getBrowserTypeFromProperty());
    }

    @BeforeClass
    public void blogLoginTest() {
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    String EXPECTED_TITLE = "new post title test";
    String EXPECTED_DESCRIPTION = "new description test";

    @Test
     public void createPost(){
        Pages.PostsP().navigate();
        Pages.PostsP().clickCreatePost();
        Pages.EditorP().createPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
        Pages.EditorP().viewPublishedPostInNewTab();
    }

}