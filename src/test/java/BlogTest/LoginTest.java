package BlogTest;

import BlogPO.Pages;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginTest extends BaseTest {

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
    }

    @Test(priority = 1)
    public void loginNegativeTest1() {
        Pages.LoginP().navigate();
        Pages.LoginP().fillUsernameField("");
        Pages.LoginP().fillPasswordField("");
        Pages.LoginP().clickLogIn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://sergeywebdrivertest.wordpress.com/wp-login.php");
    }

    @Test(priority = 2)
    public void loginNegativeTest2(){
        Pages.LoginP().navigate();
        Pages.LoginP().fillUsernameField("editorwebdrivertest");
        Pages.LoginP().fillPasswordField("");
        Pages.LoginP().clickLogIn();
        Assert.assertEquals(Pages.LoginP().getErrorMessageText(), "ERROR: The password field is empty.");
    }

    @Test(priority = 3)
    public void loginNegativeTest3(){
        Pages.LoginP().navigate();
        Pages.LoginP().fillUsernameField("");
        Pages.LoginP().fillPasswordField("somepass");
        Pages.LoginP().clickLogIn();
        Assert.assertEquals(Pages.LoginP().getErrorMessageText(), "ERROR: The email or username field is empty.");
    }

    @Test(priority = 4)
    public void loginNegativeTest4(){
        Pages.LoginP().navigate();
        Pages.LoginP().fillUsernameField("editorwebdrivertest123");
        Pages.LoginP().fillPasswordField("somepass");
        Pages.LoginP().clickLogIn();
        Assert.assertEquals(Pages.LoginP().getErrorMessageText(), "ERROR: Invalid email or username. Lost your password?");
    }

    @Test(priority = 5)
    public void loginNegativeTest5(){
        Pages.LoginP().navigate();
        Pages.LoginP().fillUsernameField("editorwebdrivertest");
        Pages.LoginP().fillPasswordField("wrongpass");
        Pages.LoginP().clickLogIn();
        Assert.assertEquals(Pages.LoginP().getErrorMessageText(), "ERROR: The password you entered for the email or username editorwebdrivertest is incorrect. Lost your password?");
    }

    @Test(priority = 6)
    public void loginNegativeTest6(){
        Pages.LoginP().navigate();
        Pages.LoginP().fillUsernameField("fedorovbuzzfeed@gmail.com");
        Pages.LoginP().fillPasswordField("wrongpass");
        Pages.LoginP().clickLogIn();
        Assert.assertEquals(Pages.LoginP().getErrorMessageText(), "ERROR: The password you entered for the email address fedorovbuzzfeed@gmail.com is incorrect. Lost your password?");
    }

    @Test(priority = 7)
    public void loginPositiveUsernameTest(){
        Pages.LoginP().navigate();
        Pages.LoginP().login("editorwebdrivertest", "EditorTest");
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("wp-admin"));
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
        Pages.DashboardP().logOut();
    }

    @Test(priority = 8)
    public void loginPositiveEmailTest(){
        Pages.LoginP().navigate();
        Pages.LoginP().login("fedorovbuzzfeed@gmail.com", "EditorTest");
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("wp-admin"));
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
        Pages.DashboardP().logOut();
    }

}
