package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;

public class EditorPage{

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
    @FindBy(css = ".editor-ground-control__preview-button")
    WebElement previewPostButton;
    @FindBy(css = ".web-preview__frame")
    WebElement previewFrame;
    @FindBy(css = ".notice__action>span")
    WebElement viewPostButton;
    @FindBy(css = ".is-success>div>span>span")
    WebElement publishedSuccessfullyText;
    @FindBy(css = ".editor-status-label>span")
    WebElement publishedTime;


    /***Constructor***/
    public EditorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        /*if (!driver.getCurrentUrl().contains("/post")) {
            throw new IllegalStateException("This is not New Post page, current page is: " + driver.getCurrentUrl());
        }*/
    }

/*    public void customClick(WebElement elem){
        super.customClick(elem);
    }*/

    public void createPost(String postTitleText, String postDescritpionText) {
        Assert.assertFalse(publishButton.isEnabled());
        Assert.assertEquals(publishButton.getText(), "Publish");

        postTitleField.sendKeys(postTitleText);
        driver.switchTo().frame(postEditor);
        postDescriptionField.sendKeys(postDescritpionText);
        driver.switchTo().defaultContent();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        publishButton.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(publishedSuccessfullyBar));
    }

    public void editPost(String postTitleTextUpdate, String postDescritpionTextUpdate){
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(publishButton));

        Assert.assertTrue(publishButton.isEnabled());
        Assert.assertEquals(publishButton.getText(), "Update");

        driver.switchTo().frame(postEditor);
        postDescriptionField.clear();
        postDescriptionField.sendKeys(postDescritpionTextUpdate);
        driver.switchTo().defaultContent();
        postTitleField.clear();
        postTitleField.sendKeys(postTitleTextUpdate);
        publishButton.click();
    }

    public ViewPostPage previewPost(){
        previewPostButton.click();
        //(new WebDriverWait(driver, 5)).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(previewFrame));
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(previewFrame));
        driver.switchTo().frame(previewFrame);
        return PageFactory.initElements(driver, ViewPostPage.class);
    }

    public ViewPostPage viewPublishedPost(){
        viewPostButton.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.numberOfWindowsToBe(2));
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return PageFactory.initElements(driver, ViewPostPage.class);
    }

    public String getTextFromSuccessBar(){
        return publishedSuccessfullyText.getText();
    }

    public Boolean isSuccessBarDisplayed(){
        return publishedSuccessfullyBar.isDisplayed();
    }

    public String getPublishedTime(){
        return publishedTime.getText();
    }

}
