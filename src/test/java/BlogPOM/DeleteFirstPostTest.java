package BlogPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

public class DeleteFirstPostTest {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        LoginPage loginPg = PageFactory.initElements(driver, LoginPage.class);
        loginPg.login();

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        System.out.println(postsPg.postSearch("qwerty"));

        /*PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.deleteFirstPost();

        System.out.println(postsPg.numberOfPosts());

        postsPg.deleteFirstPost();

        System.out.println(postsPg.numberOfPosts());*/

        /**/

    }
}
