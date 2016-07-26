package BlogPO;

import BlogTest.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public abstract class Page{

    WebDriver driver;
    private int explicitWaitTimeout = 5;



    public Page(){
        this.driver = BaseTest.driver;
    }

    public Page(WebDriver driver) {
        this.driver = driver;

    }

    protected void navigateToPageUrl(String pageUrl){
        if (!driver.getCurrentUrl().equals(pageUrl)) {
            driver.get(pageUrl);
        }
    }

    protected void customExplicitWait(WebElement elem){
        (new WebDriverWait(driver, explicitWaitTimeout)).until(ExpectedConditions.elementToBeClickable(elem));
    }

    protected void waitAndClick(WebElement elem){
        customExplicitWait(elem);
        elem.click();
    }

    protected void clearTheField(WebElement elem){
        elem.clear();
    }

    protected String getText(WebElement element){
        return element.getText();
    }

    protected void waitForAttributeValue(final WebElement element, final String attributeName, final String attributeValue) {
        (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (element.getAttribute(attributeName).equals(attributeValue))
                    return true;
                else
                    return false;
            }
        });
    }


}
