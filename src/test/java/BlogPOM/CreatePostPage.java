package BlogPOM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CreatePostPage {
    WebDriver driver;
    @FindBy(how= How.CSS, using = ".editor-title>input")
            WebElement postTitleField;
    @FindBy(how= How.CSS, using = ".editor-ground-control__publish-button")
            WebElement publishButton;
    @FindBy(how= How.ID, using = "tinymce-1_ifr")
            WebElement postEditor;
    @FindBy(how= How.ID, using = "tinymce")
            WebElement postDescriptionField;

    public CreatePostPage(WebDriver driver){
        this.driver = driver;
    }

    public void createPost(String postTitleText, String postDescritpionText){
        postTitleField.sendKeys(postTitleText);
        driver.switchTo().frame(postEditor);
        postDescriptionField.sendKeys(postDescritpionText);
        driver.switchTo().defaultContent();
        publishButton.click();
    }
}
