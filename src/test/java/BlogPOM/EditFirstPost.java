package BlogPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class EditFirstPost {

    WebDriver driver;
    @FindBy(how= How.CSS, using = ".editor-title>input")
    WebElement postTitleField;
    @FindBy(how= How.CSS, using = ".editor-ground-control")
    WebElement updateButton;
    @FindBy(how= How.ID, using = "tinymce-1_ifr")
    WebElement postEditor;
    @FindBy(how= How.ID, using = "tinymce")
    WebElement postDescriptionField;

    public EditFirstPost(WebDriver driver){
        this.driver = driver;
    }

    public void editPost(String postTitleTextUpdate, String postDescritpionTextUpdate){
        postTitleField.sendKeys("");
        postTitleField.sendKeys(postTitleTextUpdate);
        driver.switchTo().frame(postEditor);
        postDescriptionField.clear();
        postDescriptionField.sendKeys(postDescritpionTextUpdate);
        driver.switchTo().defaultContent();
        updateButton.click();
    }

}
