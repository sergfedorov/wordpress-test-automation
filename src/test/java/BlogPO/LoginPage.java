package BlogPO;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Page {

    String PAGE_URL = "https://sergeywebdrivertest.wordpress.com/wp-login.php";

    @FindBy(how= How.ID, using = "user_login")
    WebElement usernameField;
    @FindBy(how= How.ID, using = "user_pass")
    WebElement passwordField;
    @FindBy(how= How.ID, using = "wp-submit")
    WebElement submitLoginButton;
    @FindBy(id = "login_error")
    WebElement loginError;

    public LoginPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void navigate(){
        navigateToPageUrl(PAGE_URL);
        customExplicitWait(submitLoginButton);
    }

    public void login(String username, String password){
        this.navigate();
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        submitLoginButton.click();
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("wp-admin"));
    }

    public void fillUsernameField(String username){
        usernameField.sendKeys(username);
    }

    public void fillPasswordField(String password){
        passwordField.sendKeys(password);
    }

    public void clickLogIn(){
        submitLoginButton.click();
    }

    public Boolean isErrorDisplayed(){
        return loginError.isDisplayed();
    }

    public String getErrorMessageText(){
        return loginError.getText();
    }



}
