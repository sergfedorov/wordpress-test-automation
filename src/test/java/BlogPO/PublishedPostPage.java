package BlogPO;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.function.Predicate;

public class PublishedPostPage extends Page {

    String PAGE_URL = "https://sergeywebdrivertest.wordpress.com/2016/05/17/new-post-title-test-12/";

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


    public PublishedPostPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigate() {
        navigateToPageUrl(PAGE_URL);
    }

    public void clickHomeLink() {
        homeLink.click();
        waitForPage();
    }

    public void clickAboutLink() {
        aboutLink.click();
    }

    public void clickContactLink() {
        contactLink.click();
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

    public String getNewWindowPageURL() {
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //waitForPage();

        String url = driver.getCurrentUrl();
        driver.close();
        driver.switchTo().window(tabs.get(0));

        return url;
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

    public void waitForAttributeValue(final WebElement element, final String attributeName, final String attributeValue) {
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (element.getAttribute(attributeName).equals(attributeValue))
                    return true;
                else
                    return false;
            }
        });
    }

    public void waitForPage() {
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        });
    }
}
