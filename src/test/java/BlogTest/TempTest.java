package BlogTest;

import BlogPO.LoginPage;
import BlogPO.PostsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TempTest{

    public static void main(String[] args) {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("https://sergeywebdrivertest.wordpress.com/2016/05/17/new-post-title-test-12/");
        driver.findElement(By.id("comment")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //driver.findElement(By.cssSelector(".form-submit")).click();

        WebElement element = driver.findElement(By.cssSelector(".form-submit"));

        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.perform();


        /*((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();*/

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(driver.findElement(By.xpath("//label[@for='email']")).getAttribute("class"));
    }

}
