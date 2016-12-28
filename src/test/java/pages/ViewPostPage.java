package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class ViewPostPage extends Page {

    @FindBy(how= How.CSS, using = ".entry-header")
    WebElement postTitle;
    @FindBy(how= How.CSS, using = ".entry-content>p")
    WebElement postDescription;
    @FindBy(how= How.CSS, using = "#comment")
    WebElement commentField;
    @FindBy(how= How.CSS, using = "#comment-submit")
    WebElement commentSubmit;



/*    public ViewPostPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }*/

    public String getTitleText(){
        return postTitle.getText();
    }

    public String getDescriptionText (){
        return postDescription.getText();
    }

    public void addComment (String commentText){
        commentField.sendKeys(commentText);
        commentSubmit.click();
    }

    public String getPageTitleText(){
        return driver.getTitle();
    }
}
