package BlogPO;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage extends Page{

    @FindBy(id = "wp-admin-bar-my-account")
    WebElement userMenu;

    @FindBy(className = "ab-sign-out")
    WebElement signOutButton;

    public void logOut(){
        userMenu.click();
        waitAndClick(signOutButton);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
