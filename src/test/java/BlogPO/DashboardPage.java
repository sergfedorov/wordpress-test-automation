package BlogPO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage extends Page{

    @FindBy(id = "wp-admin-bar-my-account")
    WebElement userMenu;
    @FindBy(className = "ab-sign-out")
    WebElement signOutButton;

    public void logOut(){
        userMenu.click();
        waitAndClick(signOutButton);
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.titleIs("WordPress.com"));
    }
}
