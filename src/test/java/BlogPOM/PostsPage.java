package BlogPOM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PostsPage {
    WebDriver driver;
    @FindBy(className = "gridicons-create")
    WebElement createNewPostLoc;
    @FindBy(xpath = "//div[@class='posts__list']/article[1]//a[@class='post-controls__trash']")
    WebElement deleteFirstPostLoc;
    @FindBy(css = "article.post")
    WebElement postList;
    @FindBy(xpath = "//div[@class='posts__list']/article[1]//a[@class='post-controls__trash']")
    WebElement deleteFirstPostButton;
    @FindBy(css = "div[aria-controls='search-component-2']>svg")
    WebElement searchIcon;
    @FindBy(css = "#search-component-2")
    WebElement searchField;
    @FindBy(css = ".no-results")
    WebElement noResultsBar;
    @FindBy(css = "posts__list")
    WebElement postsList;
    @FindBy(xpath = "//div[@class='posts__list']/article[1]//a[@class='post-controls__edit']")
    WebElement editFirstPostButton;



    public PostsPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://wordpress.com/posts/sergeywebdrivertest.wordpress.com");
    }

    /**/
    public void createNewPost(String title, String descritpion) {
        createNewPostLoc.click();
        CreatePostPage postPg = PageFactory.initElements(driver, CreatePostPage.class);
        postPg.createPost(title, descritpion);
    }

    /**/
    public void deleteFirstPost() {
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(deleteFirstPostButton));
        deleteFirstPostButton.click();

    }

    public void editFirstPost(String titleUpdate, String descritpionUpdate) {
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(editFirstPostButton));
        editFirstPostButton.click();
        EditFirstPost editPg = PageFactory.initElements(driver, EditFirstPost.class);
        editPg.editPost(titleUpdate, descritpionUpdate);
    }

    public int numberOfPosts() {
        List<WebElement> postsList = driver.findElements(By.cssSelector("article.post"));
        return postsList.size();

    }

    public int postSearch(String searchText) {
        searchIcon.click();
        searchField.sendKeys(searchText);
        //(new WebDriverWait(driver, 5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("article.post")));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> resultsList = driver.findElements(By.cssSelector("article.post"));
        return resultsList.size();

    }
}