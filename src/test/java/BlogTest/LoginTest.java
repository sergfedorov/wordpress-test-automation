package BlogTest;

import BlogPO.LoginPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void blogLoginTest(){
        LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
        loginPg.login();
        Assert.assertEquals(driver.getTitle(), "Dashboard ‹ sergeywebdrivertest — WordPress");
    }

}
