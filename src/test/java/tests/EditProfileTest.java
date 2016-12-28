package tests;

import pages.Pages;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class EditProfileTest extends BaseTest{

    @BeforeClass
    public void blogLogin(){
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
    }

    @Test
    public void editProfile(){

        Pages.MyProfilePage().navigate();
        Assert.assertFalse(Pages.MyProfilePage().isSaveProfileButtonActive());
        Pages.MyProfilePage().fillFirstNameField("name");
        Assert.assertTrue(Pages.MyProfilePage().isSaveProfileButtonActive(), "Save profile button is inactive");
        Pages.MyProfilePage().clickSaveProfileButton();
        Assert.assertTrue(Pages.MyProfilePage().isSavedSuccessfullyBarAppears(), "Saved successfully bar does not appear");
        Assert.assertEquals(Pages.MyProfilePage().getTextSavedSuccessfullyBar(), "Settings saved successfully!");
        Pages.MyProfilePage().closeSavedSuccessfullyBar();

    }


}
