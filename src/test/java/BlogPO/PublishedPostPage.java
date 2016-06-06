package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class PublishedPostPage extends Page{

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

    @FindBy(id = "comment")
    WebElement commentField;
    @FindBy(id = "comment-submit")
    WebElement commentSubmit;
    @FindBy(css = ".comment-form-email")
    WebElement commentFormEmail;
    @FindBy(css = ".comment-form-author")
    WebElement commentFormAuthor;
    @FindBy(css = ".comment-form-url")
    WebElement commentFormURL;



    public PublishedPostPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigate(){
        navigateToPageUrl(PAGE_URL);
    }

    public void clickHomeLink(){
        homeLink.click();
    }

    public void clickAboutLink(){
        aboutLink.click();
    }

    public void clickContactLink(){
        contactLink.click();
    }

    public void clickShareTwitter(){
        shareTwitter.click();
    }

    public void clickShareFacebook(){
        shareFacebook.click();
    }

    public void clickshareGoogle(){
        shareGoogle.click();
    }

    public String getNewWindowPageURL(){
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));

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

}
