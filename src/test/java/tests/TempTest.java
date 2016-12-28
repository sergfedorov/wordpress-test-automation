package tests;

import util.Pages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.BaseTest;

public class TempTest extends BaseTest {


    @BeforeClass
    public void blogLoginTest() {
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
    }

    String EXPECTED_TITLE = "new post title test";
    String EXPECTED_DESCRIPTION = "new description test";

    @Test
     public void createPost(){
        Pages.postsP().navigate();
        Pages.postsP().clickCreatePost();
        Pages.editorP().createPost(EXPECTED_TITLE, EXPECTED_DESCRIPTION);
        Pages.editorP().viewPublishedPostInNewTab();
    }

}