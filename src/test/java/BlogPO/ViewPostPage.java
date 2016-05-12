package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;


public class ViewPostPage {

    WebDriver driver;
    @FindBy(how= How.CSS, using = ".entry-header")
    WebElement postTitle;
    @FindBy(how= How.CSS, using = ".entry-content>p")
    WebElement postDescription;
    @FindBy(how= How.CSS, using = "#comment")
    WebElement commentField;
    @FindBy(how= How.CSS, using = "#comment-submit")
    WebElement commentSubmit;


    public ViewPostPage(WebDriver driver){
        this.driver = driver;
    }

    public void testTitle (String expectedTitle){
        Assert.assertEquals(expectedTitle, postTitle.getText());
    }

    public void testDescription (String expectedDescription){
        Assert.assertEquals(expectedDescription, postDescription.getText());
    }

    public void addComment (String commentText){
        commentField.sendKeys(commentText);
        commentSubmit.click();
    }

}
