package BlogPO;

import BlogTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page{

    WebDriver driver;
    private int explicitWaitTimeout = 5;


    /***Constructor***/
    public Page(WebDriver driver) {
        this.driver = driver;
    }


    public void customExplicitWait(WebElement elem){
        (new WebDriverWait(driver, explicitWaitTimeout)).until(ExpectedConditions.elementToBeClickable(elem));
    }

    public void customClick(WebElement elem){
        customExplicitWait(elem);
        elem.click();
    }





}
