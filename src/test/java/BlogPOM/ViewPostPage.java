package BlogPOM;

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

    public ViewPostPage(WebDriver driver){
        this.driver = driver;
    }

    public void testTitle (String expectedTitle){
        Assert.assertEquals(expectedTitle, postTitle.getText());
    }


}
