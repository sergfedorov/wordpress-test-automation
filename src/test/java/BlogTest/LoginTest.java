package BlogTest;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @BeforeTest
    public void initBrowserAndPageObjects(){
        driver = super.getDriver(getBrowserTypeFromProperty());
        super.pageObjectsInitializtion(driver);
    }

    @Test(priority = 1)
    public void loginNegativeTest1(){
        loginPage.navigate();
        loginPage.fillUsernameField("");
        loginPage.fillPasswordField("");
        loginPage.clickLogIn();
        Assert.assertEquals(driver.getCurrentUrl(), "https://sergeywebdrivertest.wordpress.com/wp-login.php");
    }

    @Test(priority = 2)
    public void loginNegativeTest2(){
        loginPage.navigate();
        loginPage.fillUsernameField("editorwebdrivertest");
        loginPage.fillPasswordField("");
        loginPage.clickLogIn();
        Assert.assertEquals(loginPage.getErrorMessageText(), "ERROR: The password field is empty.");
    }

    @Test(priority = 3)
    public void loginNegativeTest3(){
        loginPage.navigate();
        loginPage.fillUsernameField("");
        loginPage.fillPasswordField("somepass");
        loginPage.clickLogIn();
        Assert.assertEquals(loginPage.getErrorMessageText(), "ERROR: The email or username field is empty.");
    }

    @Test(priority = 4)
    public void loginNegativeTest4(){
        loginPage.navigate();
        loginPage.fillUsernameField("editorwebdrivertest123");
        loginPage.fillPasswordField("somepass");
        loginPage.clickLogIn();
        Assert.assertEquals(loginPage.getErrorMessageText(), "ERROR: Invalid email or username. Lost your password?");
    }

    @Test(priority = 5)
    public void loginNegativeTest5(){
        loginPage.navigate();
        loginPage.fillUsernameField("editorwebdrivertest");
        loginPage.fillPasswordField("wrongpass");
        loginPage.clickLogIn();
        Assert.assertEquals(loginPage.getErrorMessageText(), "ERROR: The password you entered for the email or username editorwebdrivertest is incorrect. Lost your password?");
    }

    @Test(priority = 6)
    public void loginNegativeTest6(){
        loginPage.navigate();
        loginPage.fillUsernameField("fedorovbuzzfeed@gmail.com");
        loginPage.fillPasswordField("wrongpass");
        loginPage.clickLogIn();
        Assert.assertEquals(loginPage.getErrorMessageText(), "ERROR: The password you entered for the email address fedorovbuzzfeed@gmail.com is incorrect. Lost your password?");
    }

    @Test(priority = 7)
    public void loginPositiveUsernameTest(){
        loginPage.navigate();
        loginPage.login("editorwebdrivertest", "EditorTest");
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("wp-admin"));
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
        dashboardPage.logOut();
    }

    @Test(priority = 8)
    public void loginPositiveEmailTest(){
        loginPage.navigate();
        loginPage.login("fedorovbuzzfeed@gmail.com", "EditorTest");
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("wp-admin"));
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
        dashboardPage.logOut();
    }

}
