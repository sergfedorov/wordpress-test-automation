package BlogPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

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

        //System.out.println(postsPg.numberOfPosts());

        PostsPage postsPg = PageFactory.initElements(driver, PostsPage.class);
        postsPg.editFirstPost("title update", "description update");


    }
}
