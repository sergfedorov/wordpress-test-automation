package gui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import gui.Driver;

public abstract class Page{

    WebDriver driver;
    private int explicitWaitTimeout = 8;

    public Page(){
        this.driver = Driver.getInstance();
    }

    protected void fillTheField(WebElement elem, String data){
        elem.sendKeys(data);
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
