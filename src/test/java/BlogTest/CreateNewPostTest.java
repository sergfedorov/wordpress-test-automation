package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import BlogPO.ViewPostPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class CreateNewPostTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
        loginPg.login();

        /*PostsPage postsPg1 = PageFactory.initElements(driver, PostsPage.class);
        System.out.println(postsPg1.numberOfPosts());

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.createNewPost("qwerty", "asdafaf");

        PostsPage postsPg2 = PageFactory.initElements(driver, PostsPage.class);
        postsPg2.deleteFirstPost();*/


        String actualTilte = "title update qwerty123";
        String actualDescription = "description update";

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.editFirstPost(actualTilte, actualDescription);

        driver.findElement(By.cssSelector(".notice__action>span")).click();

        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));

        ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        ViewPostPage viewPg = PageFactory.initElements(driver, ViewPostPage.class);
        viewPg.testTitle(actualTilte);
        viewPg.testDescription(actualDescription);

    }
}
