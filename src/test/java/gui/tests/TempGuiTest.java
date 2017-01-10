package gui.tests;

import gui.Pages;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import gui.BaseGuiTest;

public class TempGuiTest extends BaseGuiTest {


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