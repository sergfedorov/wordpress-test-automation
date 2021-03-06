package gui.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class PublishedPostPage extends Page {

    String PAGE_URL = "https://sergeywebdrivertest.wordpress.com/2016/11/01/aasd/";

    @FindBy(id = "menu-item-5")
    WebElement homeLink;
    @FindBy(id = "menu-item-6")
    WebElement aboutLink;
    @FindBy(id = "menu-item-7")
    WebElement contactLink;

    @FindBy(className = "share-twitter")
    WebElement shareTwitter;
    @FindBy(className = "share-facebook")
    WebElement shareFacebook;
    @FindBy(className = "share-google-plus-1")
    WebElement shareGoogle;

    @FindBy(id = "comment-form-comment")
    WebElement commentField;
    @FindBy(css = ".form-submit")
    WebElement commentSubmit;
    @FindBy(css = ".comment-form-email")
    WebElement commentFormEmail;
    @FindBy(xpath = "//label[@for='email']")
    WebElement commentFormEmailLabel;
    @FindBy(css = ".comment-form-author")
    WebElement commentFormAuthor;
    @FindBy(xpath = "//label[@for='author']")
    WebElement commentFormAuthorLabel;


    @FindBy(css = ".comment-form-url")
    WebElement commentFormURL;

    @FindBy(className = "widget-area")
    WebElement widgetArea;


/*    public PublishedPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }*/

    public void navigate() {
        navigateToPageUrl(PAGE_URL);
    }

    public void clickHomeLink() {
        homeLink.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlToBe("https://sergeywebdrivertest.wordpress.com/"));
    }

    public void clickAboutLink() {
        aboutLink.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("about"));
    }

    public void clickContactLink() {
        contactLink.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("contact"));
    }

    public void clickShareTwitter() {
        shareTwitter.click();
    }

    public void clickShareFacebook() {
        shareFacebook.click();
    }

    public void clickshareGoogle() {
        shareGoogle.click();
    }


    public void clickCommentField() {
        Actions actions = new Actions(driver);
        actions.moveToElement(widgetArea);
        actions.perform();
        commentField.click();
    }

    public void clickPostComment() {
        super.customExplicitWait(commentFormEmail);
        commentSubmit.click();
    }

    public boolean isCommentFormEmailFieldValidated() {
        waitForAttributeValue(commentFormEmailLabel, "class", "error");
        return commentFormEmailLabel.getAttribute("class").equalsIgnoreCase("error");
    }

    public boolean isCommentFormAuthorFieldValidated() {
        waitForAttributeValue(commentFormAuthorLabel, "class", "error");
        return commentFormAuthorLabel.getAttribute("class").equalsIgnoreCase("error");
    }

    public boolean isCommentFormFieldValidated() {
        waitForAttributeValue(commentField, "class", "error");
        return commentField.getAttribute("class").equalsIgnoreCase("error");
    }

    public String getTextCommentFormEmail(){
        return super.getText(commentFormEmailLabel);
    }

    public String getTextCommentFormAuthor(){
        return super.getText(commentFormAuthorLabel);
    }

    public String getNewWindowPageURL() {
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        //(new WebDriverWait(driver, 5)).until(ExpectedConditions.);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String url = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(tabs.get(0));

        return url;
    }

    public void waitForPage() {
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }
}
