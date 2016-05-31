package BlogPO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Created by sfedorov on 31-May-16.
 */
public class PublishedPostPage {

    @FindBy(id = "menu-item-5")
    WebElement homeLink;
    @FindBy(id = "menu-item-6")
    WebElement aboutLink;
    @FindBy(id = "menu-item-7")
    WebElement contactLink;

    @FindBy(className = "share-twitter")
    WebElement shareTwitter;
    @FindBy(className = "share-facebook")
    WebElement shareFacebook;
    @FindBy(className = "share-google-plus-1")
    WebElement shareGoogle;


}
