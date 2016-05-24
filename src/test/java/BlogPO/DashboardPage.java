package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    WebDriver driver;

    @FindBy(id = "wp-admin-bar-my-account")
    WebElement userMenu;

    @FindBy(className = "ab-sign-out")
    WebElement signOutButton;


    public DashboardPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void logOut(){
        userMenu.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.elementToBeClickable(signOutButton));
        signOutButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
