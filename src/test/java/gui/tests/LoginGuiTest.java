package gui.tests;

import gui.Pages;
import gui.BaseGuiTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


public class LoginGuiTest extends BaseGuiTest {

    @Test(priority = 1, enabled = false)
    public void loginNegativeTest1() {
        Pages.loginP().navigate();
        Pages.loginP().fillUsernameField("");
        Pages.loginP().fillPasswordField("");
        Pages.loginP().clickLogIn();
        Assert.assertEquals(Pages.loginP().getCurrentPageUrl(), "https://sergeywebdrivertest.wordpress.com/wp-login.php");
    }

    @Test(priority = 2)
    public void loginNegativeTest2(){
        Pages.loginP().navigate();
        Pages.loginP().fillUsernameField("editorwebdrivertest");
        Pages.loginP().fillPasswordField("");
        Pages.loginP().clickLogIn();
        Assert.assertEquals(Pages.loginP().getErrorMessageText(), "Don't forget to enter your password.");
    }

    @Test(priority = 3)
    public void loginNegativeTest3(){
        Pages.loginP().navigate();
        Pages.loginP().fillUsernameField("");
        Pages.loginP().fillPasswordField("somepass");
        Pages.loginP().clickLogIn();
        Assert.assertEquals(Pages.loginP().getErrorMessageText(), "Please enter a username or email address.");
    }

    @Test(priority = 4)
    public void loginNegativeTest4(){
        Pages.loginP().navigate();
        Pages.loginP().fillUsernameField("editorwebdrivertest123");
        Pages.loginP().fillPasswordField("somepass");
        Pages.loginP().clickLogIn();
        Assert.assertEquals(Pages.loginP().getErrorMessageText(), "ERROR: Invalid email or username. Lost your password?");
    }

    @Test(priority = 5)
    public void loginNegativeTest5(){
        Pages.loginP().navigate();
        Pages.loginP().fillUsernameField("editorwebdrivertest");
        Pages.loginP().fillPasswordField("wrongpass");
        Pages.loginP().clickLogIn();
        Assert.assertEquals(Pages.loginP().getErrorMessageText(), "Oops, that's not the right password. Please try again!");
    }

    @Test(priority = 6, enabled = false)
    public void loginNegativeTest6(){
        Pages.loginP().navigate();
        Pages.loginP().fillUsernameField("fedorovbuzzfeed@gmail.com");
        Pages.loginP().fillPasswordField("wrongpass");
        Pages.loginP().clickLogIn();
        Assert.assertEquals(Pages.loginP().getErrorMessageText(), "ERROR: The password you entered for the email address fedorovbuzzfeed@gmail.com is incorrect. Lost your password?");
    }

    @Test(priority = 7)
    public void loginPositiveUsernameTest(){
        Pages.loginP().navigate();
        Pages.loginP().login("editorwebdrivertest", "EditorTest");
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
        Pages.dashboardP().logOut();
    }

    @Test(priority = 8)
    public void loginPositiveEmailTest(){
        Pages.loginP().navigate();
        Pages.loginP().login("fedorovbuzzfeed@gmail.com", "EditorTest");
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
        Pages.dashboardP().logOut();
    }

    @Test(priority = 9, dataProvider="userCreds", enabled = false)
    public void loginNegativeTestUsingExcel(String userName, String userPass, String expectedResult){
        Pages.loginP().navigate();
        Pages.loginP().fillUsernameField(userName);
        Pages.loginP().fillPasswordField(userPass);
        Pages.loginP().clickLogIn();
        Assert.assertEquals(Pages.loginP().getErrorMessageText(), expectedResult);
    }

}
