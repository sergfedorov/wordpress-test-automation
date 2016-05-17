package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CreatePostPage {
    WebDriver driver;
    @FindBy(how = How.CSS, using = ".editor-title>input")
    WebElement postTitleField;
    @FindBy(how = How.CSS, using = ".editor-ground-control__publish-button")
    WebElement publishButton;
    @FindBy(how = How.ID, using = "tinymce-1_ifr")
    WebElement postEditor;
    @FindBy(how = How.ID, using = "tinymce")
    WebElement postDescriptionField;
    @FindBy(css = ".is-success")
    WebElement publishedSuccessfullyBar;


    public CreatePostPage(WebDriver driver) {
        this.driver = driver;
        if (!driver.getCurrentUrl().contains("/post")) {
            throw new IllegalStateException("This is not New Post page, current page is: " + driver.getCurrentUrl());
        }
    }

    public void createPost(String postTitleText, String postDescritpionText) {
        Assert.assertFalse(publishButton.isEnabled());
        postTitleField.sendKeys(postTitleText);
        driver.switchTo().frame(postEditor);
        postDescriptionField.sendKeys(postDescritpionText);
        driver.switchTo().defaultContent();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publishButton.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(publishedSuccessfullyBar));
    }
}
