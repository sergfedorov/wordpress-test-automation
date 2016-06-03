package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page{

    WebDriver driver;
    private int explicitWaitTimeout = 5;

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


}
