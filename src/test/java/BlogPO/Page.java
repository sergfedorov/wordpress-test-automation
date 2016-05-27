package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page{

    WebDriver driver;
    private int explicitWaitTimeout = 5;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToPageUrl(String pageUrl){
        if (!driver.getCurrentUrl().equals(pageUrl)) {
            driver.get(pageUrl);
        }
    }

    public void customExplicitWait(WebElement elem){
        (new WebDriverWait(driver, explicitWaitTimeout)).until(ExpectedConditions.elementToBeClickable(elem));
    }

    public void waitAndClick(WebElement elem){
        customExplicitWait(elem);
        elem.click();
    }





}
