package BlogPO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EditFirstPost {

    WebDriver driver;
    @FindBy(how= How.CSS, using = ".editor-title>input")
    WebElement postTitleField;
    @FindBy(how= How.CSS, using = ".editor-ground-control__publish-button")
    WebElement updateButton;
    @FindBy(how= How.ID, using = "tinymce-1_ifr")
    WebElement postEditor;
    @FindBy(how= How.ID, using = "tinymce")
    WebElement postDescriptionField;

    public EditFirstPost(WebDriver driver){
        this.driver = driver;
    }

    public void editPost(String postTitleTextUpdate, String postDescritpionTextUpdate){
        driver.switchTo().frame(postEditor);
        postDescriptionField.clear();
        postDescriptionField.sendKeys(postDescritpionTextUpdate);
        driver.switchTo().defaultContent();
        //(new WebDriverWait(driver, 10)).until(ExpectedConditions.elementToBeClickable(postTitleField));
        //(new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".editor-title>input")));
        postTitleField.clear();
        postTitleField.sendKeys(postTitleTextUpdate);
        updateButton.click();
    }

}
