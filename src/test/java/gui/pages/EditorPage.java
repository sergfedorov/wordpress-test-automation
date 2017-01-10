package gui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;

public class EditorPage extends Page{

    @FindBy(how = How.CSS, using = ".editor-title__input")
    WebElement postTitleField;
    @FindBy(how = How.XPATH, using = "//div[@class='editor-ground-control__publish-combo']/button[1]")
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
    @FindBy(css = ".is-success>div>span")
    WebElement publishedSuccessfullyText;
    @FindBy(css = ".editor-status-label")
    WebElement publishedTime;

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
        customExplicitWait(publishButton);

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

    public ViewPostPage openPreviewPost(){
        previewPostButton.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(previewFrame));
        driver.switchTo().frame(previewFrame);
        return PageFactory.initElements(driver, ViewPostPage.class);
    }

    public ViewPostPage viewPublishedPostInNewTab(){
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
