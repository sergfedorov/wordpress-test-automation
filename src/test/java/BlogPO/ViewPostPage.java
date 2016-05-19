package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
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
        PageFactory.initElements(driver, this);
    }

    public void testTitle (String expectedTitle){
        Assert.assertEquals(postTitle.getText(), expectedTitle);
    }

    public void testDescription (String expectedDescription){
        Assert.assertEquals(postDescription.getText(), expectedDescription);
    }

    public void addComment (String commentText){
        commentField.sendKeys(commentText);
        commentSubmit.click();
    }

}
