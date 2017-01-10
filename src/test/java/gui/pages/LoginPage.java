package gui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

/*    public LoginPage(){

    }*/

    /*public LoginPage(WebDriver driver){
        super(driver);
        //PageFactory.initElements(driver, this);
    }*/

    public void navigate(){
        navigateToPageUrl(PAGE_URL);
        customExplicitWait(submitLoginButton);
    }

    public void login(String username, String password){
        this.navigate();
        clearTheField(usernameField);
        usernameField.sendKeys(username);
        clearTheField(passwordField);
        passwordField.sendKeys(password);
        submitLoginButton.click();
        (new WebDriverWait(driver, 10)).until(ExpectedConditions.urlContains("wp-admin"));
    }

    public void fillUsernameField(String username){
        clearTheField(usernameField);
        usernameField.sendKeys(username);
    }

    public void fillPasswordField(String password){
        clearTheField(passwordField);
        passwordField.sendKeys(password);
    }

    public void clickLogIn(){
        submitLoginButton.click();

        //(new WebDriverWait(driver, 5)).until(ExpectedConditions.urlContains("wp-admin"));

    }

    public Boolean isErrorDisplayed(){
        return loginError.isDisplayed();
    }

    public String getErrorMessageText(){
        (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOf(loginError));
        return loginError.getText();
    }

    public String getCurrentPageUrl(){
        return driver.getCurrentUrl();
    }

    //public void waitFor

}
