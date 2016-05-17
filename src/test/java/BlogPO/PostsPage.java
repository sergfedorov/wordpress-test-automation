package BlogPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

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
    @FindBy(xpath = "//a[@href='/posts/trashed/sergeywebdrivertest.wordpress.com']/span/span[@class='count']")
    WebElement trashCounterElem;
    /***Filter locators***/
    @FindBy(xpath = "//ul[@role='radiogroup']/li[1]")
    WebElement filterByMeElem;
    @FindBy(xpath = "//ul[@role='radiogroup']/li[2]")
    WebElement filterByEveryoneElem;
    @FindBy(xpath = "//ul[@class='section-nav-tabs__list']/li[1]")
    WebElement filterByPublishedElem;
    @FindBy(xpath = "//ul[@class='section-nav-tabs__list']/li[2]")
    WebElement filterByDraftsElem;
    @FindBy(xpath = "//ul[@class='section-nav-tabs__list']/li[3]")
    WebElement filterByTrashedElem;


    public PostsPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://wordpress.com/posts/sergeywebdrivertest.wordpress.com");
        //(new WebDriverWait(driver, 5)).until(ExpectedConditions.);
    }

    /***Filters***/
    public void filterByMe() {
        filterByMeElem.click();
    }

    public void filterByEveryone() {
        filterByEveryoneElem.click();
    }

    public void filterByPublished() {
        filterByPublishedElem.click();
    }

    public void filterByDrafts() {
        filterByDraftsElem.click();
    }

    public void filterByTrashed() {
        filterByTrashedElem.click();
    }


    public void createNewPost(String title, String descritpion) {
        createNewPostLoc.click();
        CreatePostPage postPg = PageFactory.initElements(driver, CreatePostPage.class);
        postPg.createPost(title, descritpion);
    }

    public void deleteFirstPost() {
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(deleteFirstPostButton));
        deleteFirstPostButton.click();
    }

    public int trashCounter() {
        return Integer.parseInt(trashCounterElem.getText());
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
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains(""+searchText+""));

        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


        List<WebElement> resultsList = driver.findElements(By.cssSelector("article.post"));
        return resultsList.size();

    }





}