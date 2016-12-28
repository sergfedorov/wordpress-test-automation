package tests;

import util.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import util.BaseTest;

public class EditProfileTest extends BaseTest {

    @BeforeClass
    public void blogLogin(){
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void editProfile(){

        Pages.myProfilePage().navigate();
        Assert.assertFalse(Pages.myProfilePage().isSaveProfileButtonActive());
        Pages.myProfilePage().fillFirstNameField("name");
        Assert.assertTrue(Pages.myProfilePage().isSaveProfileButtonActive(), "Save profile button is inactive");
        Pages.myProfilePage().clickSaveProfileButton();
        Assert.assertTrue(Pages.myProfilePage().isSavedSuccessfullyBarAppears(), "Saved successfully bar does not appear");
        Assert.assertEquals(Pages.myProfilePage().getTextSavedSuccessfullyBar(), "Settings saved successfully!");
        Pages.myProfilePage().closeSavedSuccessfullyBar();

    }


}
