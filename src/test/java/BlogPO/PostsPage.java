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

public class PostsPage extends Page{
    String PAGE_URL = "https://wordpress.com/posts/sergeywebdrivertest.wordpress.com";

    @FindBy(className = "gridicons-create")
    WebElement createNewPostLoc;
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
    @FindBy(css = ".conf-alert")
    WebElement trashConfirmationAlert;
    @FindBy(xpath = "//div[@class='posts__list']/article[1]//h4[@class='post__title']")
    WebElement postInListTitle;
    @FindBy(xpath = "//div[@class='posts__list']/article[1]//div[@class='post-excerpt']")
    WebElement postInListDescription;


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


    /***Constructor***/
/*    public PostsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }*/

    public void navigate() {
        navigateToPageUrl(PAGE_URL);
        customExplicitWait(filterByPublishedElem);
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

    public EditorPage clickCreatePost() {
        waitAndClick(createNewPostLoc);
        return PageFactory.initElements(driver, EditorPage.class);
    }

    public String getPostTitleText(){
        return postInListTitle.getText();
    }

    public String getPostDescriptionText(){
        return postInListDescription.getText();
    }

    public void deleteFirstPost() {
        waitAndClick(deleteFirstPostButton);
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(trashConfirmationAlert));
    }

    public int trashCounter() {
        return Integer.parseInt(trashCounterElem.getText());
    }

    public EditorPage clickEditPost(){
        waitAndClick(editFirstPostButton);
        return PageFactory.initElements(driver, EditorPage.class);
    }

    public int numberOfPosts() {
        List<WebElement> postsList = driver.findElements(By.cssSelector("article.post"));
        return postsList.size();
    }

    public int postSearch(String searchText) {
        searchIcon.click();
        searchField.sendKeys(searchText);
        //(new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains(""+searchText+""));

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<WebElement> resultsList = driver.findElements(By.cssSelector("article.post"));
        return resultsList.size();

    }

    public Boolean isTrashConfirmationAlertDisplayed(){
        return trashConfirmationAlert.isDisplayed();
    }





}